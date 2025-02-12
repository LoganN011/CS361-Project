public class BellmanFord {
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;
    private static GraphNode closestNode;
    private static int startRow = 0, startCol = 0;

    public static void initializeSingleSource(GraphNode[][] matrix) {
        //Sets all nodes distance to infinity
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].setDistance(Integer.MAX_VALUE);
                }
            }
        }
    }

    public static void BellmanFord(GraphNode[][] matrix, int row, int col, boolean isFirst, boolean robot) {
        //Save the original matrix
        if (isFirst) {
            matrix[row][col].removeItem();
            path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);

        }
        initializeSingleSource(matrix);
        matrix[row][col].setDistance(0);
        //Loop through all nodes and relax all edges for each node
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        FileIO.addToNumberNodesVisited();
                        for (int k = 0; k < 4; k++) {
                            if (GraphNode.isValid(matrix, i + rowMove[k], j + colMove[k]) && (matrix[i + rowMove[k]][j + colMove[k]] != null) && matrix[i][j] != null) {
                                if (matrix[i + rowMove[k]][j + colMove[k]].getDistance() > matrix[i][j].getDistance() + 1 && matrix[i][j].getDistance() + 1 >= 0) {
                                    matrix[i + rowMove[k]][j + colMove[k]].setDistance(matrix[i][j].getDistance() + 1);
                                    matrix[i + rowMove[k]][j + colMove[k]].setPrevious(matrix[i][j]);
                                    if (closestNode == null && matrix[i + rowMove[k]][j + colMove[k]].hasItem()) {
                                        closestNode = matrix[i + rowMove[k]][j + colMove[k]];
                                    } else if (closestNode != null && closestNode.getDistance() > matrix[i + rowMove[k]][j + colMove[k]].getDistance() && matrix[i + rowMove[k]][j + colMove[k]].hasItem()) {
                                        closestNode = matrix[i + rowMove[k]][j + colMove[k]];
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        //once all is relaxed start again from the closets node saving the path and total distance traveled
        if (closestNode != null) {
            original[closestNode.getRow()][closestNode.getCol()].removeItem();
            path += GraphNode.getStringPath(closestNode);
            totalDistance += closestNode.getDistance();
            row = closestNode.getRow();
            col = closestNode.getCol();
            closestNode = null;
            BellmanFord(GraphNode.copyMatrix(original), row, col, false, robot);
        }
        if (robot) {
            if (startRow == 0 && startCol == 0) {
                startRow = -1;
                startCol = -1;
                path += GraphNode.getStringPath(matrix[0][0]);
                totalDistance += matrix[0][0].getDistance();
            }
        }


    }

    /**
     * Prints the info of Bellman-ford
     * Finds the path to all items in the provided matrix and prints that
     * also tells the distance of the path
     *
     * @param matrix  graph that is being searched
     * @param row     starting row
     * @param col     starting col
     * @param isFirst is this the first time bfs is being called on this graph
     */
    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst, boolean robot) {
        totalDistance = 0;
        path = "";
        original = null;
        closestNode = null;
        startRow = 0;
        startCol = 0;
        BellmanFord(matrix, row, col, isFirst, robot);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }

}
