/**
 * Dijkstra Shortest Path to all Items
 *
 * Steps :
 * 1) check if this is the first algorithm call; if it is we remove the item
 *    from the first place, add the starting place to the path, copy the matrix
 *    to the original matrix initialize the graph weights
 *
 * 2) initialize the graph weights to inf except for current node is 0
 *
 * 3) We will use a priority queue (min heap) to iterate through nodes by
 *    distance; we will add the starting node first. We will also initialize the
 *    closest item to null as it has not yet been discovered
 *
 * 4) We will then continuously go through the nodes in the heap, extracting
 *    them by least distance until the queue is empty. Each node that is visited
 *    and is valid (not null and within bounds) will have its distance relaxed
 *    and added to the heap. We will go through the whole graph in order to find
 *    the closest item node.
 *
 * 5) Once the queue is empty we check if there is a closest item and add the
 *    path to that item to the total shortest path.
 *
 * 6) If there is no closest item found, this means all items have been
 *    collected. At this point we check for a robot call. If robot is true, find
 *    the shortest path using dijkstra again back to the begging. If no robot is
 *    called, then the current path is the returned result.
 *
 */

public class Dijkstra {

    /**
     * rowMove & colMove : movements to traverse the grid and find neighbor
     *                     nodes
     * path              : string that will hold grid points representing the
     *                     path taken
     * totalDistance     : int to count the steps taken along the final shortest
     *                     path
     * original          : stores the GraphNode objects from the original matrix
     * closestItem       : stores next target node -> closest node with an item
     */

    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;
    private static GraphNode closestItem;
    private static int originalRow = 0, originalCol = 0;

    /**
     * Initializes a matrix with HUGE wight vals
     * @param matrix : initialized matrix with inf weight vals
     */
    public static void initializeSingleSource(GraphNode[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].setDistance(Integer.MAX_VALUE);
                }
            }
        }
    }

    /**
     *
     * @param matrix : grid matrix of warehouse
     * @param startRow : start row
     * @param startCol : start col
     * @param isFirst : is this the first time we are calling the algorithm?
     */
    public static void dijkstra(GraphNode[][] matrix, int startRow,
                                int startCol, boolean isFirst, boolean robot) {
        if (isFirst) {
            matrix[startRow][startCol].removeItem();
            // add start to path & copy original matrix to avoid update issues
            path += "[" + matrix[startRow][startCol].getRow() + "," +
                    matrix[startRow][startCol].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);
        }

        // set all weights to inf except current node
        initializeSingleSource(matrix);
        matrix[startRow][startCol].setDistance(0);

        // initialize min-heap / priority queue for weights and add first node
        MinHeap<GraphNode> minHeap = new MinHeap<>();
        minHeap.insert(matrix[startRow][startCol]);

        // initialize closest item to null
        closestItem = null;

        // while the heap is NOT empty
        while (!minHeap.isEmpty()) {
            FileIO.addToNumberNodesVisited();
            // extract min and set to current node
            GraphNode current = minHeap.extractMin();

            // here we will start relaxing the
            for (int k = 0; k < 4; k++) {
                // get row and col after move implemented
                int row = current.getRow() + rowMove[k];
                int col = current.getCol() + colMove[k];

                // if the location of the move is within bounds and is not a
                // barrier, set the node as a new node neighbor
                if (GraphNode.isValid(matrix, row, col) &&
                        matrix[row][col] != null) {
                    GraphNode newNode = matrix[row][col];

                    // relax distance for new node
                    if (newNode.getDistance() > current.getDistance() + 1) {
                        newNode.setDistance(current.getDistance() + 1);
                        newNode.setPrevious(current);

                        // Check if the new node has an item and is closer than
                        // the current closestNode and update
                        if (closestItem == null && newNode.hasItem()) {
                            closestItem = newNode;
                        } else if (closestItem != null &&
                                closestItem.getDistance() > newNode.getDistance() && newNode.hasItem()) {
                            closestItem = newNode;
                        }

                        minHeap.insert(newNode);
                    }
                }
            }
        }

        // if a closestNode with an item was found...
        if (closestItem != null) {

            // remove item from original to mark it is found
            original[closestItem.getRow()][closestItem.getCol()].removeItem();

            // update the path & total distance
            path += GraphNode.getStringPath(closestItem);
            totalDistance += closestItem.getDistance();

            // reset for next dijkstra call
            startRow = closestItem.getRow();
            startCol = closestItem.getCol();
            dijkstra(GraphNode.copyMatrix(original), startRow, startCol, false, robot);
        }
        // if all items were found && robot is called to return to start...
        else if (robot) {
            if (originalRow == 0 && originalCol == 0) {
                originalRow = -1;
                originalCol = -1;
                path += GraphNode.getStringPath(matrix[0][0]);
                totalDistance += matrix[0][0].getDistance();
            }
//            // reinitialize matrix and set current node distance to 0
//            initializeSingleSource(matrix);
//            matrix[startRow][startCol].setDistance(0);
//
//            // add initial node to heap
//            minHeap.insert(matrix[startRow][startCol]);
//
//            GraphNode startNode = null;
//
//            // reuse dijkstra to find the shortest path to the beginning
//            while (!minHeap.isEmpty()) {
//                FileIO.addToNumberNodesVisited();
//                GraphNode current = minHeap.extractMin();
//
//                for (int k = 0; k < 4; k++) {
//                    int row = current.getRow() + rowMove[k];
//                    int col = current.getCol() + colMove[k];
//
//                    if (GraphNode.isValid(matrix, row, col) && matrix[row][col] != null) {
//                        GraphNode newNode = matrix[row][col];
//
//                        if (newNode.getDistance() > current.getDistance() + 1) {
//                            newNode.setDistance(current.getDistance() + 1);
//                            newNode.setPrevious(current);
//                            minHeap.insert(newNode);
//
//                            // check if we are at the starting node
//                            if (row == 0 && col == 0) {
//                                startNode = newNode;
//                            }
//                        }
//                    }
//                }
//            }
//
//            // add robot trip to path
//            if (startNode != null) {
//                path += GraphNode.getStringPath(startNode);
//                totalDistance += startNode.getDistance();
//            }
        }
    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst, boolean robot) {
        totalDistance = 0;
        path = "";
        originalRow = 0;
        originalCol = 0;
        dijkstra(matrix, row, col, isFirst, robot);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }
}
