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
    public static int dis = 0;
    private static GraphNode[][] original;

    public static void dfs(GraphNode[][] matrix, int row, int col) {
        //This finds all of the items not the shortest path because that is not
        //what dfs does so I am confused how I would update it to work with
        //finding the shortest path. Or if that is the requirement im not sure

        //I want to know if we are supposed to find the same path for all methods???
        original = GraphNode.copyMatrix(matrix);
        matrix[row][col].setDistance(dis++);
        if (original[row][col].hasItem()) {
            //The first n number of calls is right but the distance is wrong so
            //might pass that is as a parameter to the method
            System.out.println(matrix[row][col]);
            GraphNode.printPath(matrix[row][col]);
            original[row][col].incrementSeen();
            original[row][col].removeItem();
            dfs(original, row, col);
        }
        matrix[row][col].incrementSeen();
        for (int k = 0; k < 4; k++) {
            if (GraphNode.isValid(matrix, row + rowMove[k], col + colMove[k])) {
                if (matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
                    matrix[row + rowMove[k]][col + colMove[k]].setPrevious(matrix[row][col]);
                    dfs(matrix, row + rowMove[k], col + colMove[k]);
                }
            }
        }

    }
}
