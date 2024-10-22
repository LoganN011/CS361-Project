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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasItem() {
        return hasItem;
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

}
