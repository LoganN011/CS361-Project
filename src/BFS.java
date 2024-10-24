public class BFS {
    public static Queue<GraphNode> test = new Queue();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};

    public static void bfs(GraphNode[][] matrix, int row, int col) {
        matrix[row][col].incrementSeen();
        test.enqueue(matrix[row][col]);
        while (!test.isEmpty()) {
            GraphNode node = test.dequeue();
            row = node.getRow();
            col = node.getCol();
            if (node.hasItem()) {
                System.out.println(node);
            }
            for (int k = 0; k < 4; k++) {
                if (isValid(matrix, row + rowMove[k], col + colMove[k])) {
                    if (matrix[row + rowMove[k]][col + colMove[k]].isDiscovered()) {
                        matrix[row + rowMove[k]][col + colMove[k]].incrementSeen();
                        matrix[row + rowMove[k]][col + colMove[k]].setPrevious(node);
                        test.enqueue(matrix[row + rowMove[k]][col + colMove[k]]);
                    }
                }
            }

            node.incrementSeen();
        }
    }

    public static boolean isValid(GraphNode[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }
}
