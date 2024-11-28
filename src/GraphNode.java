public class GraphNode implements Comparable<GraphNode> {
    private int seen;
    private int row;
    private int col;
    private String label;
    private boolean hasItem;
    private GraphNode previous;
    private int distance; // g value: Distance from start
    private int f;        // f value: Total cost (g + h)

    public GraphNode(int row, int col, String label, boolean hasItem) {
        this.row = row;
        this.col = col;
        this.label = label;
        this.hasItem = hasItem;
        this.seen = 0; // 0 means not discovered
        this.distance = Integer.MAX_VALUE; // Default distance is "infinity"
        this.f = Integer.MAX_VALUE;        // Default f value is "infinity"
    }

    @Override
    public String toString() {
        return "[" + row + "," + col + "], Dist=" + distance + ", f=" + f;
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

    public void setF(int f) {
        this.f = f;
    }

    public int getF() {
        return f;
    }

    public static boolean isValid(GraphNode[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    public static GraphNode[][] copyMatrix(GraphNode[][] matrix) {
        GraphNode[][] newMatrix = new GraphNode[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != null) {
                    newMatrix[i][j] = new GraphNode(i, j, matrix[i][j].label, matrix[i][j].hasItem());
                } else {
                    newMatrix[i][j] = null;
                }
            }
        }
        return newMatrix;
    }

    public static String getStringPath(GraphNode current) {
        String output = "";
        while (current.previous != null) {
            output = "[" + current.getCol() + "," + current.getRow() + "] " + output;
            current = current.previous;
        }
        return output;
    }

    @Override
    public int compareTo(GraphNode other) {
        return Integer.compare(this.distance, other.distance); // Compare by f value
    }
}
