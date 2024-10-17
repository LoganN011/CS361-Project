public class GraphNode {
    private int seen;
    private int row;
    private int col;
    private String label;
    private GraphNode up;
    private GraphNode down;
    private GraphNode left;
    private GraphNode right;
    public GraphNode(int row, int col, String label) {
        this.row = row;
        this.col = col;
        this.label = label;
        //0 means not discovered
        //1 means seen
        //2 means explored
        seen = 0;
    }

}
