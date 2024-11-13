import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dijkstra {
    /**
     * We will be utilizing the Dijkstra algorithm to find the shortest path to each
     * item consecutively from the source node

     * 1) we need to iterate through the matrix of items, each item having a total
     * 'cost' of infinity except for the starting node, being 0.

     * 2) we will need an array to represent if the node was visited, so when all
     * are visited we can take the total cost of that

     * 3) we will then find the minimum summation of paths or total cost for each
     * path that has a fully visited array

     * 4) since we do not have assigned weights, this will allso need to be
     * calculated with a sort of counter that then can store that weight and resent
     * the counter

     * https://www.baeldung.com/cs/shortest-path-visiting-all-nodes
     */

    public static Queue<GraphNode> que = new Queue();
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    public static int[][] distanceMatrix;
    private static GraphNode[][] original;



    public static void dijkstra(GraphNode[][] matrix, int numOfItems) {
        // make distance adjacency matrix
        distanceMatrix = new int[numOfItems][numOfItems];
        // no self-loop
        for (int i = 0; i < numOfItems; i++) {
            for (int j = 0; j < numOfItems; j++){
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                }
                else{
                    distanceMatrix[i][j] = -1;
                }
            }
        }

        for(int i = 0; i < numOfItems; i++){
            for(int j = 0; j < matrix.length; j++){
                for(int k = 0; k < matrix[1].length; k++){
                    if(matrix[j][k].getAdjacencyPlace() == i) {
                        GraphNode startNode = matrix[j][k];
                        bfsWeights(matrix, startNode, i, numOfItems);
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(distanceMatrix));

        // take adjacency matrix and run dijakstra
    }

    public static void bfsWeights(GraphNode[][] matrix, GraphNode startNode, int startIndex, int numOfItems){
        for (GraphNode[] row : matrix) { // iterate through rows of graphnodes
            for (GraphNode node : row) { // iterate throguh each node in the row
                if (node != null) {
                    node.setWeight(0); // set weight to inf
                    node.setSeen(0);  // set seen to 0
                }
            }
        }
        startNode.setWeight(0);
        startNode.incrementSeen();
        que.enqueue(startNode);

        int itemsVisited = 0;
        while (!que.isEmpty()){
            GraphNode node = que.dequeue();
            int currentWeight = 0;
            boolean targetHit = false;
            int row = node.getRow();
            int col = node.getCol();
            for (int k = 0; k < 4 ; k++) {
                int newRow = row + rowMove[k];
                int newCol = col + colMove[k];
                if (GraphNode.isValid(matrix, newRow, newCol)) {
                    GraphNode neighbor = matrix[newRow][newCol];
                    if (neighbor != null && neighbor.getSeen() == 0) {
                        neighbor.incrementSeen();
                        int newWeight = node.getWeight() + 1; // Add 1 to the current path weight
                        //if (newWeight < neighbor.getWeight()) {  // Only update if the new path is shorter
                            neighbor.setWeight(newWeight);
                            // System.out.println(neighbor.getWeight());
                            neighbor.setPrevious(node);
                            que.enqueue(neighbor);
                       // }
                        if(neighbor.hasItem()) {
                            itemsVisited ++;

                            // clear path, but leave the items as marked as visited or not
                            for (GraphNode[] graphNodes : matrix) {
                                for (int j = 0; j < matrix[1].length; j++) {
                                    if (!graphNodes[j].hasItem()) {
                                        graphNodes[j].setSeen(0);
                                    }
                                }
                            }
                            System.out.println(node);
                            System.out.println(neighbor);
                            System.out.println(node.getAdjacencyPlace());
                            System.out.println(neighbor.getAdjacencyPlace());
                            distanceMatrix[node.getAdjacencyPlace()][neighbor.getAdjacencyPlace()] = 3;

                            // when item is reached, clear queue to
                            que.clear();
                            if(itemsVisited != numOfItems-1){
                                // stop while loop if we have reached every item; else place startnode to restart search
                                que.enqueue(startNode);
                            }
                        }

                    }
                }

            }
        }
    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst, int numOfItems) {
        dijkstra(matrix, numOfItems);
    }

}

