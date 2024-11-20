public class BellmanFord {
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;
    private static GraphNode closestNode;

    public static void initializeSingleSource(GraphNode[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].setDistance(Integer.MAX_VALUE);
                }
            }
        }
    }

    public static void BellmanFord(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        if (isFirst) {
            matrix[row][col].removeItem();
            path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);

        }
        initializeSingleSource(matrix);
        matrix[row][col].setDistance(0);
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

        if (closestNode != null) {
            original[closestNode.getRow()][closestNode.getCol()].removeItem();
            path += GraphNode.getStringPath(closestNode);
            totalDistance += closestNode.getDistance();
            row = closestNode.getRow();
            col = closestNode.getCol();
            closestNode = null;
            BellmanFord(GraphNode.copyMatrix(original), row, col, false);
        }


    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        totalDistance = 0;
        BellmanFord(matrix, row, col, isFirst);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }

}