public class GraphNode {
    private int seen;
    private int row;
    private int col;
    private String label;
    private boolean hasItem;
    private GraphNode previous;
    private int distance;
    private int g; // Cost from start to this node
    private int h; // Heuristic cost to the goal

    public GraphNode(int row, int col, String label, boolean hasItem) {
        this.row = row;
        this.col = col;
        this.label = label;
        this.hasItem = hasItem;
        this.seen = 0;
        this.g = Integer.MAX_VALUE; // Initialize g to a high value (unreachable)
        this.h = Integer.MAX_VALUE; // Initialize h to a high value
    }

    public String toString() {
        if (hasItem) {
            return "I [" + row + "," + col + "],Dist=" + distance;
        }
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
        String output = getStringPath(current);
        System.out.println(output);
    }

    public static String getStringPath(GraphNode current) {
        String output = "";
        while (current.previous != null) {
            output = "[" + current.getRow() + "," + current.getCol() + "] " + output;
            current = current.previous;
        }
        return output;
    }

    // New method for A* algorithm to calculate f(n)
    public int getF() {
        return this.g + this.h; // f(n) = g(n) + h(n)
    }

    // New methods for g and h
    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
