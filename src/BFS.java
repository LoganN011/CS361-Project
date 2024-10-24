public class BFS {
    public static Queue<GraphNode> test = new Queue();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};

    public static void bfs(GraphNode[][] matrix, int row, int col) {
        matrix[row][col].incrementSeen();
        matrix[row][col].setDistance(0);
        test.enqueue(matrix[row][col]);
        while (!test.isEmpty()) {
            GraphNode node = test.dequeue();
            row = node.getRow();
            col = node.getCol();
            if (node.hasItem()) {
                //My guess for finding the shortest path is that we will then call
                //bfs from here and delete the item in that spot. and reset the
                //queue maybe Idk lol
                System.out.println(node);
            }
            for (int k = 0; k < 4; k++) {
                if (isValid(matrix, row + rowMove[k], col + colMove[k])) {
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

    public static boolean isValid(GraphNode[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }
}