public class GraphNode {
    //She recommends that we use a 2d array to represent the grid and
    // just use that to look at the nodes next to each one
    //https://www.geeksforgeeks.org/graph-adjacency-matrix-in-java/
    //https://youcademy.org/graph-breadth-first-search/
    private int seen;
    private int row;
    private int col;
    //Do we want a label for each node??
    private String label;
    private boolean hasItem;
    private GraphNode previous;
    private int distance;

    public GraphNode(int row, int col, String label, boolean hasItem) {
        //Will also need a distance
        //Will need other stuff based the type of searching
        this.row = row;
        this.col = col;
        this.label = label;
        this.hasItem = hasItem;
        //0 means not discovered
        //1 means seen
        //2 means explored
        seen = 0;
    }

    public String toString() {

        return "[" + row + "," + col + "],Dist=" + distance;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasItem() {
        return hasItem;
    }

    public void removeItem() {
        hasItem = false;
    }

    public boolean isDiscovered() {
        return seen == 0;
    }

    public boolean isSeen() {
        return seen == 1;
    }

    public boolean isExplored() {
        return seen == 2;
    }

    public void incrementSeen() {
        seen++;
    }

    public void setPrevious(GraphNode previous) {
        this.previous = previous;
    }

    public GraphNode getPrevious() {
        return previous;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public static boolean isValid(GraphNode[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    public static GraphNode[][] copyMatrix(GraphNode[][] matrix) {
        GraphNode[][] newMatrix = new GraphNode[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != null) {
                    newMatrix[i][j] = new GraphNode(i, j, "LABEL??", matrix[i][j].hasItem());
                } else {
                    newMatrix[i][j] = null;
                }

            }
        }
        return newMatrix;
    }

    public static void printPath(GraphNode current) {
        String output = "";
        while (current != null) {
            output = "[" + current.getRow() + "," + current.getCol() + "] " + output;
            current = current.previous;
        }
        System.out.println(output);
    }

}
