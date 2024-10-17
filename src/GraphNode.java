public class GraphNode {
    private int seen;
    private int row;
    private int col;
    //Do we want a label for each node??
    private String label;
    private boolean hasItem;
    private GraphNode up;
    private GraphNode down;
    private GraphNode left;
    private GraphNode right;

    public GraphNode(int row, int col, String label, boolean hasItem) {
        this.row = row;
        this.col = col;
        this.label = label;
        this.hasItem = hasItem;
        //0 means not discovered
        //1 means seen
        //2 means explored
        seen = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public GraphNode up() {
        return up;
    }

    public GraphNode down() {
        return down;
    }

    public GraphNode left() {
        return left;
    }

    public GraphNode right() {
        return right;
    }

    public boolean hasItem() {
        return hasItem;
    }

}
