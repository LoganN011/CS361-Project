public class BFS {
    public static Queue<GraphNode> queue = new Queue();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;

    public static void bfs(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        //FileIO.printGraph(matrix);
        if (isFirst) {
            path += "[" + matrix[row][col].getRow() + "," + matrix[row][col].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);
        }
        matrix[row][col].incrementSeen();
        matrix[row][col].setDistance(0);
        queue.enqueue(matrix[row][col]);

        while (!queue.isEmpty()) {
            FileIO.addToNumberNodesVisited();
            GraphNode node = queue.dequeue();
            row = node.getRow();
            col = node.getCol();
            //How does changing it from node.hasItem() to this fix it WTF
            //I do not understand objects
            if (original[row][col].hasItem()) {
                //My guess for finding the shortest path is that we will then call
                //bfs from here and delete the item in that spot. and reset the
                //queue maybe Idk lol
                // System.out.println(node);
                //GraphNode.printPath(matrix[row][col]);

                //original[row][col].incrementSeen();
                original[row][col].removeItem();
                queue.clear();
                path += GraphNode.getStringPath(matrix[row][col]);
                totalDistance += matrix[row][col].getDistance();
                bfs(GraphNode.copyMatrix(original), row, col, false);
            }
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

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        totalDistance = 0;
        bfs(matrix, row, col, isFirst);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }


}
