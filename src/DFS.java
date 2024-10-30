public class DFS {
    /*
    procedure DFS(G, v) is
    label v as discovered
    for all directed edges from v to w that are in G.adjacentEdges(v) do
        if vertex w is not labeled as discovered then
            recursively call DFS(G, w)
     */
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};

    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;

    public static void dfs(GraphNode[][] matrix, int row, int col, int distance, boolean isFirst) {
        //This finds all of the items not the shortest path because that is not
        //what dfs does so I am confused how I would update it to work with
        //finding the shortest path. Or if that is the requirement im not sure

        //I want to know if we are supposed to find the same path for all methods???
        if (isFirst) {
            original = GraphNode.copyMatrix(matrix);
        }
        matrix[row][col].setDistance(distance);
        if (original[row][col].hasItem()) {
            //System.out.println(matrix[row][col]);
            //GraphNode.printPath(matrix[row][col]);
            //Saves the last path found as the whole path of the searching
            //Working but not a short path
            //Also need something to sum the distance of the nodes but that should be easy
            path = GraphNode.getStringPath(matrix[row][col]);
            original[row][col].removeItem();
            totalDistance += distance;
            dfs(original, row, col, 0, false);
        }
        matrix[row][col].incrementSeen();
        for (int k = 0; k < 4; k++) {
            if (GraphNode.isValid(matrix, row + rowMove[k], col + colMove[k])) {
                if (matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
                    matrix[row + rowMove[k]][col + colMove[k]].setPrevious(matrix[row][col]);
                    dfs(matrix, row + rowMove[k], col + colMove[k], distance + 1, false);
                }
            }
        }
    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, int distance, boolean isFirst) {
        dfs(matrix, row, col, distance, isFirst);
        System.out.println(path);
        System.out.println(totalDistance);
    }
}
