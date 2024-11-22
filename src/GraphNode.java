public class GraphNode implements Comparable<GraphNode> {
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

    /**
     *
     * @param row
     * @param col
     * @param label
     * @param hasItem
     *
     * might be useful to add a graph row and col size
     */

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

    //String to represent each node
    public String toString() {
        return "[" + row + "," + col + "],Dist=" + distance;
    }

    //gets the current row
    public int getRow() {
        return row;
    }

    //gets the current column
    public int getCol() {
        return col;
    }

    //Checks if the node has an item
    public boolean hasItem() {
        return hasItem;
    }

    //removes the item from the node
    public void removeItem() {
        hasItem = false;
    }

    //Checks if the current node is discovered
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

    //sets the previous node
    public void setPrevious(GraphNode previous) {
        this.previous = previous;
    }

    public GraphNode getPrevious() {
        return previous;
    }

    //sets the distance of the node
    public void setDistance(int distance) {
        this.distance = distance;
    }

    //gets the distance of the node
    public int getDistance() {
        return distance;
    }

    //Checks if a provided row and col is with in the matrix
    public static boolean isValid(GraphNode[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    //copies a provided matrix and the info about it
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

    //Prints the path of the node
    public static void printPath(GraphNode current) {
        String output = getStringPath(current);
        System.out.println(output);
    }

    //gets string version of the path of the current node
    public static String getStringPath(GraphNode current) {
        String output = "";
        while (current.previous != null) {
            output = "[" + current.getCol() + "," + current.getRow() + "] " + output;
            current = current.previous;
        }
        return output;
    }

    @Override
    public int compareTo(GraphNode o) {
        return Integer.compare(this.distance, o.distance);
    }
}
