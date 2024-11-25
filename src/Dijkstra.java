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
    public static void dijkstra(GraphNode[][] matrix, int startRow, int startCol, boolean isFirst) {
        if (isFirst) {
            matrix[startRow][startCol].removeItem();
            // add start to path
            path += "[" + matrix[startRow][startCol].getRow() + "," + matrix[startRow][startCol].getCol() + "] ";
            // copy the original matrix
            original = GraphNode.copyMatrix(matrix);
        }

        // set all weights to inf except current node
        initializeSingleSource(matrix);
        matrix[startRow][startCol].setDistance(0);

        // initialize min-heap / priority queue for weights
        // insert
        MinHeap<GraphNode> minHeap = new MinHeap<>();
        minHeap.insert(matrix[startRow][startCol]);

        // initialize closest item to null
        closestItem = null;

        // while the head is not empty
        while (!minHeap.isEmpty()) {
            // pop minimum weight node in heap
            GraphNode current = minHeap.extractMin();
            FileIO.addToNumberNodesVisited();
            // Relax neighbors
            for (int k = 0; k < 4; k++) {
                // get row and col after move implemented

                int row = current.getRow() + rowMove[k];
                int col = current.getCol() + colMove[k];

                // if the location of the move is within bounds and is not a barrier, set the node as a new node neighbor
                if (GraphNode.isValid(matrix, row, col) && matrix[row][col] != null) {
                    GraphNode newNode = matrix[row][col];

                    // relax distance for new node
                    if (newNode.getDistance() > current.getDistance() + 1) {
                        newNode.setDistance(current.getDistance() + 1);
                        newNode.setPrevious(current);

                        // Check if the new node has an item and is closer than the current closestNode and update
                        if (closestItem == null && newNode.hasItem()) {
                            closestItem = newNode;
                        } else if (closestItem != null && closestItem.getDistance() > newNode.getDistance() && newNode.hasItem()) {
                            closestItem = newNode;
                        }

                        minHeap.insert(newNode);
                    }
                }
            }
        }

        // If a closestNode with an item was found
        if (closestItem != null) {

            // remove item from original to mark it is found
            original[closestItem.getRow()][closestItem.getCol()].removeItem();

            // update the path & total distance
            path += GraphNode.getStringPath(closestItem);
            totalDistance += closestItem.getDistance();

            // reset for next Dijkstra call
            startRow = closestItem.getRow();
            startCol = closestItem.getCol();
            dijkstra(GraphNode.copyMatrix(original), startRow, startCol, false);
        }
    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        totalDistance = 0;
        path = "";
        dijkstra(matrix, row, col, isFirst);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }
}
