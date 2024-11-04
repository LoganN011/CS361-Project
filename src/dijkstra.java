public class dijkstra {
/**
 * We will be utilizing the Dijkstra algorithm to find the shortest path to each
 * item consecutively from the source node
 *
 * 1) we need to iterate through the matrix of items, each item having a total
 * 'cost' of infinity except for the starting node, being 0.
 *
 * 2) we will need an array to represent if the node was visited, so when all
 * are visited we can take the total cost of that
 *
 * 3) we will then find the minimum summation of paths or total cost for each
 * path that has a fully visited array
 *
 * 4) since we do not have assigned weights, this will allso need to be
 * calculated with a sort of counter that then can store that weight and resent
 * the counter
 *
 *
 * https://www.baeldung.com/cs/shortest-path-visiting-all-nodes
 *
 */
}

/**
 * public class BFS {
 *     public static Queue<GraphNode> test = new Queue();
 *     public static int[] rowMove = {1, -1, 0, 0};
 *     public static int[] colMove = {0, 0, 1, -1};
 *     public static String path = "";
 *     public static int totalDistance = 0;
 *     private static GraphNode[][] original;
 *
 *     public static void bfs(GraphNode[][] matrix, int row, int col, boolean isFirst) {
 *         //FileIO.printGraph(matrix);
 *         if (isFirst) {
 *             path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
 *             original = GraphNode.copyMatrix(matrix);
 *         }
 *         matrix[row][col].incrementSeen();
 *         matrix[row][col].setDistance(0);
 *         test.enqueue(matrix[row][col]);
 *
 *         while (!test.isEmpty()) {
 *             GraphNode node = test.dequeue();
 *             row = node.getRow();
 *             col = node.getCol();
 *             //How does changing it from node.hasItem() to this fix it WTF
 *             //I do not understand objects
 *             if (original[row][col].hasItem()) {
 *                 //My guess for finding the shortest path is that we will then call
 *                 //bfs from here and delete the item in that spot. and reset the
 *                 //queue maybe Idk lol
 *                 // System.out.println(node);
 *                 //GraphNode.printPath(matrix[row][col]);
 *
 *                 //original[row][col].incrementSeen();
 *                 original[row][col].removeItem();
 *                 test.clear();
 *                 path += GraphNode.getStringPath(matrix[row][col]);
 *                 totalDistance += matrix[row][col].getDistance();
 *                 bfs(GraphNode.copyMatrix(original), row, col, false);
 *                 //This finds the path that I want but also like a bunch of diffrent ones
 *                 //The longest path is the one that finds all of the items
 *             }
 *             for (int k = 0; k < 4; k++) {
 *                 if (GraphNode.isValid(matrix, row + rowMove[k], col + colMove[k])) {
 *                     if (matrix[row + rowMove[k]][col + colMove[k]] != null && matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
 *                         matrix[row + rowMove[k]][col + colMove[k]].incrementSeen();
 *                         matrix[row + rowMove[k]][col + colMove[k]].setDistance(matrix[row][col].getDistance() + 1);
 *                         matrix[row + rowMove[k]][col + colMove[k]].setPrevious(node);
 *                         test.enqueue(matrix[row + rowMove[k]][col + colMove[k]]);
 *                     }
 *                 }
 *             }
 *
 *             node.incrementSeen();
 *         }
 *
 *     }
 *
 *     public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
 *         totalDistance = 0;
 *         bfs(matrix, row, col, isFirst);
 *         System.out.println(path);
 *         System.out.println(totalDistance);
 *     }
 *
 *
 * }
 */