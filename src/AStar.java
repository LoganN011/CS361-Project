public class AStar {

    // Directions for neighbor exploration
    public static int[] rowMove = {1, -1, 0, 0};
    public static int[] colMove = {0, 0, 1, -1};
    public static String path = "";
    public static int totalDistance = 0;
    private static GraphNode[][] original;
    private static GraphNode closestItem;
    private static boolean allItemsCollected = false; // Track if all items are collected

    /**
     * Initializes a matrix with HUGE weight values (infinity)
     * @param matrix : initialized matrix with inf weight values
     */
    public static void initializeSingleSource(GraphNode[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    matrix[i][j].setDistance(Integer.MAX_VALUE); // Set distance to infinity
                    matrix[i][j].setPrevious(null); // No previous node at the start
                }
            }
        }
    }

    /**
     * Heuristic function: Manhattan distance (h value)
     * @param current : current node
     * @param goal : goal node
     * @return the Manhattan distance (h value)
     */
    public static int manhattanDistance(GraphNode current, GraphNode goal) {
        return absoluteDifference(current.getRow(), goal.getRow()) + absoluteDifference(current.getCol(), goal.getCol());
    }

    // Custom absolute difference function
    public static int absoluteDifference(int a, int b) {
        return (a >= b) ? a - b : b - a;
    }

    /**
     * A* algorithm to find the shortest path to all items (targets)
     * @param matrix : grid matrix of the warehouse
     * @param startRow : start row position
     * @param startCol : start column position
     * @param isFirst : whether it's the first call to the algorithm (indicating start of pathfinding)
     * @param robot : whether it's the robot's turn to navigate
     */
    public static void aStar(GraphNode[][] matrix, int startRow, int startCol, boolean isFirst, boolean robot) {
        if (isFirst) {
            // Remove item from the start point if it's there
            matrix[startRow][startCol].removeItem();

            // Add starting point to the path
            path += "[" + matrix[startRow][startCol].getRow() + "," + matrix[startRow][startCol].getCol() + "] ";

            // Copy the original matrix to preserve the initial state
            original = GraphNode.copyMatrix(matrix);
        }

        // Initialize all nodes with infinite distance
        initializeSingleSource(matrix);

        // Set the starting node's distance to 0
        matrix[startRow][startCol].setDistance(0);

        // Initialize min-heap (priority queue) for A* processing
        MinHeap<GraphNode> minHeap = new MinHeap<>();
        minHeap.insert(matrix[startRow][startCol]);

        // Find the closest item to be the goal
        closestItem = findClosestItem(matrix);

        // If no items are found, stop the search
        if (closestItem == null) {
            allItemsCollected = true;
            return;
        }

        // Process nodes in the priority queue until it's empty
        while (!minHeap.isEmpty()) {
            // Pop the node with the smallest f value (f = g + h)
            GraphNode current = minHeap.extractMin();
            FileIO.addToNumberNodesVisited(); // Count visited nodes

            // If current node is the target (item), break out of the loop
            if (current == closestItem) {
                break;
            }

            // Explore neighbors
            for (int k = 0; k < 4; k++) {
                // Calculate neighboring node's row and column
                int row = current.getRow() + rowMove[k];
                int col = current.getCol() + colMove[k];

                // Check if the move is valid and within bounds
                if (GraphNode.isValid(matrix, row, col) && matrix[row][col] != null) {
                    GraphNode newNode = matrix[row][col];

                    // g is the distance from the start node
                    int g = current.getDistance() + 1;
                    // h is the heuristic (Manhattan distance)
                    int h = manhattanDistance(newNode, closestItem); // This now checks a non-null closestItem
                    int f = g + h; // f = g + h

                    // Relax the distance for the new node
                    if (newNode.getDistance() > g) {
                        newNode.setDistance(g);
                        newNode.setPrevious(current);

                        // Insert the updated node into the heap
                        minHeap.insert(newNode);
                    }
                }
            }
        }

        // If a closest node with an item is found, update path and distance
        if (closestItem != null) {
            // Remove the item from the original matrix (mark as collected)
            original[closestItem.getRow()][closestItem.getCol()].removeItem();

            // Add the closest item node to the path and update total distance
            path += GraphNode.getStringPath(closestItem);
            totalDistance += closestItem.getDistance();

            // Update start position for next A* call
            startRow = closestItem.getRow();
            startCol = closestItem.getCol();

            // Continue A* to find the next closest item
            aStar(GraphNode.copyMatrix(original), startRow, startCol, false, robot);
        }

        // If the robot has collected all items, consider returning to the origin
        if (robot && allItemsCollected) {
            GraphNode origin = original[0][0];
            aStar(GraphNode.copyMatrix(original), startRow, startCol, false, false);
            path += "Return to Origin [" + origin.getRow() + "," + origin.getCol() + "]";
        }
    }

    /**
     * Finds the closest item to the current position of the robot
     * @param matrix : grid matrix
     * @return closest item (GraphNode) or null if no items found
     */
    public static GraphNode findClosestItem(GraphNode[][] matrix) {
        GraphNode closestItem = null;
        int minDistance = Integer.MAX_VALUE;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                GraphNode node = matrix[row][col];

                if (node != null && node.hasItem()) {
                    // Calculate Manhattan distance from the starting point
                    int distance = manhattanDistance(node, new GraphNode(0, 0, "Label", false));

                    if (distance < minDistance) {
                        minDistance = distance;
                        closestItem = node;
                    }
                }
            }
        }

        return closestItem; // Return null if no item is found
    }

    /**
     * Entry point to print the path and total distance for A*
     * @param matrix : grid matrix of the warehouse
     * @param row : starting row
     * @param col : starting column
     * @param isFirst : indicates if this is the first call to the algorithm
     */
    public static void printInfo(GraphNode[][] matrix, int row, int col, boolean isFirst, boolean robot) {
        totalDistance = 0; // Reset the total distance
        path = ""; // Reset the path string

        // Run A* algorithm
        aStar(matrix, row, col, isFirst, robot);

        // Output the path and total distance
        System.out.println("Path Taken:\n" + path);
        System.out.println("Total Distance traveled: " + totalDistance);
    }
}

