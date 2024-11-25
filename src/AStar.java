import java.util.ArrayList;
import java.util.List;

public class AStar{

    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;

    // List of target nodes (nodes with items)
    private static List<GraphNode> targetNodes = new ArrayList<>();
    private static GraphNode[][] original;

    // Heuristic function: Manhattan distance
    public static int manhattanDistance(GraphNode current, GraphNode goal) {
        return Math.abs(current.getRow() - goal.getRow()) + Math.abs(current.getCol() - goal.getCol());
    }

    // Initialize graph nodes for A*
    public static void initializeSingleSource(GraphNode[][] matrix, int startRow, int startCol) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].setDistance(Integer.MAX_VALUE); // Infinity
                    matrix[i][j].setPrevious(null);
                }
            }
        }
        matrix[startRow][startCol].setDistance(0);
    }

    // A* algorithm
    public static void aStar(GraphNode[][] matrix, int startRow, int startCol) {
        while (!targetNodes.isEmpty()) {
            // Find the closest target
            GraphNode goalNode = findClosestTarget(matrix[startRow][startCol]);

            // Initialize distances
            initializeSingleSource(matrix, startRow, startCol);

            // Priority queue for A*
            MinHeap<GraphNode> minHeap = new MinHeap<>();
            minHeap.insert(matrix[startRow][startCol]);

            while (!minHeap.isEmpty()) {
                // Extract the node with the smallest f value
                GraphNode current = minHeap.extractMin();

                // If we reached the goal, reconstruct the path
                if (current == goalNode) {
                    path += reconstructPath(current);
                    totalDistance += current.getDistance();

                    // Remove item from original matrix and the target list
                    original[goalNode.getRow()][goalNode.getCol()].removeItem();
                    targetNodes.remove(goalNode);

                    // Update start for next iteration
                    startRow = goalNode.getRow();
                    startCol = goalNode.getCol();
                    break;
                }

                // Explore neighbors
                for (int k = 0; k < 4; k++) {
                    int row = current.getRow() + rowMove[k];
                    int col = current.getCol() + colMove[k];

                    if (GraphNode.isValid(matrix, row, col) && matrix[row][col] != null) {
                        GraphNode neighbor = matrix[row][col];
                        int tentativeG = current.getDistance() + 1; // g value
                        int h = manhattanDistance(neighbor, goalNode); // h value
                        int f = tentativeG + h; // f = g + h

                        // Update neighbor if shorter path is found
                        if (tentativeG < neighbor.getDistance()) {
                            neighbor.setDistance(tentativeG);
                            neighbor.setPrevious(current);
                            minHeap.insert(neighbor);
                        }
                    }
                }
            }
        }
    }

    // Reconstruct path
    private static String reconstructPath(GraphNode goal) {
        StringBuilder result = new StringBuilder();
        GraphNode current = goal;
        while (current != null) {
            result.insert(0, "[" + current.getRow() + "," + current.getCol() + "] ");
            current = current.getPrevious();
        }
        return result.toString().trim() + " ";
    }

    // Find the closest target
    private static GraphNode findClosestTarget(GraphNode start) {
        GraphNode closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (GraphNode target : targetNodes) {
            int distance = manhattanDistance(start, target);
            if (distance < minDistance) {
                minDistance = distance;
                closest = target;
            }
        }

        return closest;
    }

    // Prepare the list of target nodes
    private static void prepareTargets(GraphNode[][] matrix) {
        targetNodes.clear();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null && matrix[i][j].hasItem()) {
                    targetNodes.add(matrix[i][j]);
                }
            }
        }
    }

    // Entry point
    public static void printInfo(GraphNode[][] matrix, int row, int col) {
        totalDistance = 0;
        path = "";
        original = GraphNode.copyMatrix(matrix);

        // Prepare the list of target nodes
        prepareTargets(matrix);

        // Run A* for multiple targets
        aStar(matrix, row, col);

        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }
}
