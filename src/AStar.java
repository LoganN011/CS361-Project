public class AStar {

    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;
    private static GraphNode goalNode;

    // Heuristic function: Manhattan distance
    public static int manhattanDistance(GraphNode current, GraphNode goal) {
        return Math.abs(current.getRow() - goal.getRow()) + Math.abs(current.getCol() - goal.getCol());
    }

    // Initialize the graph nodes for A* (set distance to infinity initially)
    public static void initializeSingleSource(GraphNode[][] matrix, int startRow, int startCol) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].setDistance(Integer.MAX_VALUE);  // Set all distances to infinity initially
                    matrix[i][j].setPrevious(null);  // No previous node at start
                }
            }
        }
        matrix[startRow][startCol].setDistance(0);  // Set start node distance to 0
    }

    // A* Algorithm implementation
    public static void aStar(GraphNode[][] matrix, int startRow, int startCol, boolean isFirst) {
        if (isFirst) {
            path += "[" + matrix[startRow][startCol].getRow() + "," + matrix[startRow][startCol].getCol() + "] ";
            original = GraphNode.copyMatrix(matrix);
            goalNode = findGoalNode(matrix);  // Find the goal node (the node with an item)
        }

        // Initialize all distances to infinity except the start node
        initializeSingleSource(matrix, startRow, startCol);

        // Create a priority queue to store nodes based on their f = g + h value
        MinHeap<GraphNode> minHeap = new MinHeap<>();

        // Set the f value for the start node and add it to the priority queue
        matrix[startRow][startCol].setDistance(manhattanDistance(matrix[startRow][startCol], goalNode));
        minHeap.insert(matrix[startRow][startCol]);

        while (!minHeap.isEmpty()) {
            // Get the node with the smallest f value
            GraphNode current = minHeap.extractMin();

            // If we have reached the goal, reconstruct the path
            if (current == goalNode) {
                path = reconstructPath(current);
                totalDistance += current.getDistance();
                return;
            }

            // Explore neighbors
            for (int k = 0; k < 4; k++) {
                int row = current.getRow() + rowMove[k];
                int col = current.getCol() + colMove[k];

                if (GraphNode.isValid(matrix, row, col) && matrix[row][col] != null) {
                    GraphNode neighbor = matrix[row][col];
                    int tentativeG = current.getDistance() + 1;  // g value (cost from start)

                    // Calculate the heuristic (h value)
                    int h = manhattanDistance(neighbor, goalNode);

                    // Calculate the total f value (f = g + h)
                    int f = tentativeG + h;

                    // If a shorter path to the neighbor is found
                    if (tentativeG < neighbor.getDistance()) {
                        neighbor.setDistance(tentativeG);
                        neighbor.setPrevious(current);

                        // Add the neighbor to the priority queue with the updated f value
                        minHeap.insert(neighbor);
                    }
                }
            }
        }
    }

    // Reconstruct the path by backtracking from the goal node
    private static String reconstructPath(GraphNode goal) {
        StringBuilder result = new StringBuilder();
        GraphNode current = goal;
        while (current != null) {
            result.insert(0, "[" + current.getRow() + "," + current.getCol() + "] ");
            current = current.getPrevious();
        }
        return result.toString().trim();
    }

    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst) {
        totalDistance = 0;
        path = "";
        aStar(matrix, row, col, isFirst);
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }

    // Helper method to find the goal node (node with item)
    private static GraphNode findGoalNode(GraphNode[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null && matrix[i][j].hasItem()) {
                    return matrix[i][j];
                }
            }
        }
        return null;  // No goal node found
    }
}
