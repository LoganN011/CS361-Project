public class lizWorkInProgress {

    private GraphNode[][] graph;
    private int totalRows;
    private int totalCols;

    /**
     * Here is where I will be working on my functions... it is currently pretty
     * rough haha but the idea is to have a function for each method (I know it
     * will be lengthy) and then from there I can modify and present them to the
     * rest of the group... basically this is my work space
     *
     * First we have our private varibles of the class, these are standard to
     * what I expect I will need. I am thinking maybe having a list or some sort
     * of count of amount of items could prove beneficial
     *      @param  graph : the current graph/case that is being worked on
     *      @int totalRows : num of rows on current graph
     *      @int cols : num of cols on current graph
     */

    public lizWorkInProgress(GraphNode [][] graph){
        this.graph = graph;
        this.totalRows = graph.length;
        this.totalCols = graph[0].length;
    }

    public void bfs(GraphNode[][] graph){
        /**
         * Main Idea with the BFS function is...
         *  1) iterate through each node as per usual with BFS while utilizing a
         *     queue
         *  2) once an item is found, that item is now to new source node;;
         *     however the queue of nodes that have been added to the queue
         *     already must be maintained or the search will become infinite
         *  3) Now, lets say that an item is sort of baricaded and that there is
         *     no other way from this item to the other items without going
         *     backwards, I am thinking to then go back to the previous source
         *     and somehow compensate for this issue,, this is a point that I am
         *     quite puzzled with
         *  4) we will take all of these shortest paths and sum them up
         *
         *  thinking to have an array for...
         *          - path distanace
         *          - previous node
         *          - if path to item was found
         *      (used source a for this distribution of arrays, I want to try
         *       and see if it is usful. This is pretty typical and Humayra has
         *       used a similar approach in lecture)
         *
         * Sources :
         *         a) https://www.youtube.com/watch?v=oDqjPvD54Ss
         *         b) https://www.youtube.com/watch?v=T_m27bhVQQQ
         */
    }

    public void dfs(GraphNode[][] graph){
        /**
         * Main Idea with the DFS function is...
         *  1) iterate through each node as per usual with DFS,, I am wondering
         *     if we need to do all three types (postorder, preorder, and
         *     inorder) and then see which is most efficient. This would add to
         *     the time but I think it would be a case by case outcome and maybe
         *     interesting to investigate,, this would mean 3 different versions
         *     of the code. We could maybe run all three and then return the one
         *     with either the shortest path or smallest time complexity. Also,
         *     writing this out brings a thought into question of how accurate
         *     the shortest path answer has to be, what if its is less accurate
         *     for some algorithm (it may still return a short-ish path but
         *     maybe not the shortest, but maybe another superior algorithm
         *     returns a more short path,, is that a problem???)
         *  2) once an item is found, that item is now to new source node;;
         *     however the queue of nodes that have been added to the queue
         *     already must be maintained or the search will become infinite
         *  3) Now, lets say that an item is sort of barricaded and that there
         *     is no other way from this item to the other items without going
         *     backwards, I am thinking to then go back to the previous source
         *     and somehow compensate for this issue,, this is a point that I am
         *     quite puzzled with
         *  4) we will take all of these shortest paths and sum them up
         */
    }
}