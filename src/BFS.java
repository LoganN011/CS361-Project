public class BFS {
    public static Queue<GraphNode> queue = new Queue<>();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;

    public static void bfs(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        //First run copies the matrix to be used in recursion
        if (isFirst) {
            path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);
        }
        matrix[row][col].incrementSeen();
        matrix[row][col].setDistance(0);
        queue.enqueue(matrix[row][col]);

        while (!queue.isEmpty()) {
            //mark node as visited
            FileIO.addToNumberNodesVisited();
            GraphNode node = queue.dequeue();
            row = node.getRow();
            col = node.getCol();
            //if the node as an item start remove the item and start again from
            // the point with the item
            if (original[row][col].hasItem()) {
                original[row][col].removeItem();
                queue.clear();
                //saving the path to get here and the distance as well
                path += GraphNode.getStringPath(matrix[row][col]);
                totalDistance += matrix[row][col].getDistance();
                bfs(GraphNode.copyMatrix(original), row, col, false);
            }
            //Looking in all 4 direction you could move from a node (up, down, left, and right)
            // and adding each of the nodes that have not been discovered to the queue to be explored
            for (int k = 0; k < 4; k++) {
                if (GraphNode.isValid(matrix, row + rowMove[k], col + colMove[k])) {
                    if (matrix[row + rowMove[k]][col + colMove[k]] != null && matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
                        matrix[row + rowMove[k]][col + colMove[k]].incrementSeen();
                        matrix[row + rowMove[k]][col + colMove[k]].setDistance(matrix[row][col].getDistance() + 1);
                        matrix[row + rowMove[k]][col + colMove[k]].setPrevious(node);
                        queue.enqueue(matrix[row + rowMove[k]][col + colMove[k]]);
                    }
                }
            }

            node.incrementSeen();
        }

    }

    /**
     * Prints the info of BFS
     * Finds the path to all items in the provided matrix and prints that
     * also tells the distance of the path
     *
     * @param matrix  graph that is being searched
     * @param row     starting row
     * @param col     starting col
     * @param isFirst is this the first time bfs is being called on this graph
     */
    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        totalDistance = 0;
        bfs(matrix, row, col, isFirst);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }

}