public class BFS {
    public static Queue<GraphNode> test = new Queue();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    private static GraphNode[][] original;
    public static void bfs(GraphNode[][] matrix, int row, int col) {
        //FileIO.printGraph(matrix);
        original = GraphNode.copyMatrix(matrix);
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
                System.out.println(node);
                GraphNode.printPath(matrix[row][col]);

                original[row][col].incrementSeen();
                original[row][col].removeItem();
                test.clear();
                bfs(original, row, col);
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


}
