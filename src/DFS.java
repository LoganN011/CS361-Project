public class DFS {
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};

    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;
    private static int startRow = 0, startCol = 0;

    public static void dfs(GraphNode[][] matrix, int row, int col, int distance, boolean isFirst, boolean robot) {
        //Each call to dfs is a new node visited
        FileIO.addToNumberNodesVisited();
        //The first call to dfs it copies the matrix
        if (isFirst) {
            path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);
        }
        //Sets the distance of the current node
        matrix[row][col].setDistance(distance);
        //If the current node has an item then we remove it and start the process
        // again from the current node. It also saves the path take to get to the
        // node and the distance of the path taken
        if (original[row][col].hasItem()) {
            path += GraphNode.getStringPath(matrix[row][col]);
            original[row][col].removeItem();
            totalDistance += distance;
            dfs(GraphNode.copyMatrix(original), row, col, 0, false, robot);
        }
        matrix[row][col].incrementSeen();
        //For each of the adjacent nodes there is a new recursive call while increase the distance
        //goes in the same direction for changing to the next one
        for (int k = 0; k < 4; k++) {
            if (GraphNode.isValid(matrix, row + rowMove[k], col + colMove[k]) && matrix[row + rowMove[k]][col + colMove[k]] != null) {
                if (matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
                    matrix[row + rowMove[k]][col + colMove[k]].setPrevious(matrix[row][col]);
                    dfs(matrix, row + rowMove[k], col + colMove[k], distance + 1, false, robot);
                }
            }
        }
        if (robot) {
            if (row == startRow && col == startCol) {
                path += GraphNode.getStringPath(matrix[row][col]);
                totalDistance += distance;
                startRow = -1;
                startCol = -1;
            }
        }

    }

    /**
     * Prints the info of DFS
     * Finds the path to all items in the provided matrix and prints that
     * also tells the distance of the path
     *
     * @param matrix  graph that is being searched
     * @param row     starting row
     * @param col     starting col
     * @param isFirst is this the first time bfs is being called on this graph
     */
    public static void printInfo(GraphNode[][] matrix, int row, int col, int distance, boolean isFirst, boolean robot) {
        path = "";
        totalDistance = 0;
        original = null;
        dfs(matrix, row, col, distance, isFirst, robot);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }
}
