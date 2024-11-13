public class BFS {
    public static Queue<GraphNode> test = new Queue();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;

    public static void bfs(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        //FileIO.printGraph(matrix);
        if (isFirst) {
            path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);
        }
        matrix[row][col].incrementSeen();
        matrix[row][col].setDistance(0);
        test.enqueue(matrix[row][col]);

        while (!test.isEmpty()) {
            GraphNode node = test.dequeue();
            row = node.getRow();
            col = node.getCol();
            //How does changing it from node.hasItem() to this fix it WTF
            //I do not understand objects
            if (original[row][col].hasItem()) {
                //My guess for finding the shortest path is that we will then call
                //bfs from here and delete the item in that spot. and reset the
                //queue maybe Idk lol
                // System.out.println(node);
                //GraphNode.printPath(matrix[row][col]);

                //original[row][col].incrementSeen();
                original[row][col].removeItem();
                test.clear();
                path += GraphNode.getStringPath(matrix[row][col]);
                totalDistance += matrix[row][col].getDistance();
                bfs(GraphNode.copyMatrix(original), row, col, false);
                //This finds the path that I want but also like a bunch of diffrent ones
                //The longest path is the one that finds all of the items
            }
            for (int k = 0; k < 4; k++) {
                if (GraphNode.isValid(matrix, row + rowMove[k], col + colMove[k])) {
                    if (matrix[row + rowMove[k]][col + colMove[k]] != null && matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
                        matrix[row + rowMove[k]][col + colMove[k]].incrementSeen();
                        matrix[row + rowMove[k]][col + colMove[k]].setDistance(matrix[row][col].getDistance() + 1);
                        matrix[row + rowMove[k]][col + colMove[k]].setPrevious(node);
                        test.enqueue(matrix[row + rowMove[k]][col + colMove[k]]);
                    }
                }
            }

            node.incrementSeen();
        }

    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        totalDistance = 0;
        bfs(matrix, row, col, isFirst);
        System.out.println(path);
        System.out.println(totalDistance);
    }


}
/**
 * import java.util.*;
 *
 * public class BFS {
 *     public static Queue<GraphNode> test = new LinkedList<>();  // Updated to LinkedList for Queue
 *     public static int[] rowMove = {1, -1, 0, 0};
 *     public static int[] colMove = {0, 0, 1, -1};
 *     public static int[][] distanceMatrix;  // To store the distances in the complete graph
 *     private static List<GraphNode> itemNodes;  // List to hold the start and item nodes
 *     private static GraphNode[][] original;
 *
 *     public static void initialize(GraphNode[][] matrix, List<int[]> itemCoordinates) {
 *         // Initialize the item nodes list with start and item positions
 *         itemNodes = new ArrayList<>();
 *         for (int[] coords : itemCoordinates) {
 *             itemNodes.add(matrix[coords[0]][coords[1]]);
 *         }
 *
 *         // Initialize the distance matrix for the complete graph
 *         int itemCount = itemNodes.size();
 *         distanceMatrix = new int[itemCount][itemCount];
 *
 *         // Fill the distance matrix with BFS results for each item node
 *         for (int i = 0; i < itemCount; i++) {
 *             GraphNode startNode = itemNodes.get(i);
 *             bfsForDistance(matrix, startNode, i);
 *         }
 *     }
 *
 *     public static void bfsForDistance(GraphNode[][] matrix, GraphNode startNode, int startIndex) {
 *         // Reset the BFS queue and distances in the matrix for each BFS call
 *         for (GraphNode[] row : matrix) {
 *             for (GraphNode node : row) {
 *                 if (node != null) {
 *                     node.setDistance(Integer.MAX_VALUE);  // Reset distances
 *                     node.setDiscovered(false);  // Reset discovered status
 *                 }
 *             }
 *         }
 *
 *         // Initialize BFS from the start node
 *         startNode.setDistance(0);
 *         startNode.setDiscovered(true);
 *         test.add(startNode);
 *
 *         while (!test.isEmpty()) {
 *             GraphNode node = test.poll();
 *             int row = node.getRow();
 *             int col = node.getCol();
 *
 *             // Check if this node has an item (and is not the start node itself)
 *             if (node.hasItem() || node.equals(startNode)) {
 *                 int endIndex = itemNodes.indexOf(node);
 *                 distanceMatrix[startIndex][endIndex] = node.getDistance();
 *             }
 *
 *             // Traverse neighboring nodes
 *             for (int k = 0; k < 4; k++) {
 *                 int newRow = row + rowMove[k];
 *                 int newCol = col + colMove[k];
 *
 *                 if (GraphNode.isValid(matrix, newRow, newCol) && matrix[newRow][newCol] != null) {
 *                     GraphNode neighbor = matrix[newRow][newCol];
 *
 *                     // Process unvisited neighbors
 *                     if (!neighbor.isDiscovered()) {
 *                         neighbor.setDiscovered(true);
 *                         neighbor.setDistance(node.getDistance() + 1);
 *                         test.add(neighbor);
 *                     }
 *                 }
 *             }
 *         }
 *     }
 *
 *     public static void printDistanceMatrix() {
 *         System.out.println("Distance Matrix:");
 *         for (int i = 0; i < distanceMatrix.length; i++) {
 *             for (int j = 0; j < distanceMatrix[i].length; j++) {
 *                 System.out.print(distanceMatrix[i][j] + "\t");
 *             }
 *             System.out.println();
 *         }
 *     }
 * }
 */