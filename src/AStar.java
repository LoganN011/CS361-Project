public class AStar {

    public static int[] rowMove = {1, -1, 0, 0};  // Moves for up, down, left, right
    public static int[] colMove = {0, 0, 1, -1};

    public static String path = "";
    public static int totalDistance = 0;

    public static void aStar(GraphNode[][] matrix, int startRow, int startCol, int goalRow, int goalCol) {
        Queue<GraphNode> openList = new Queue<>();
        boolean[][] closedList = new boolean[matrix.length][matrix[0].length];

        // Initialize the starting node
        GraphNode startNode = matrix[startRow][startCol];
        startNode.setG(0);
        startNode.setH(calculateHeuristic(startRow, startCol, goalRow, goalCol));
        openList.enqueue(startNode);

        while (!openList.isEmpty()) {
            // Get the node with the lowest f = g + h manually
            GraphNode current = getMinNode(openList);
            openList.dequeue();  // Remove the node with the lowest f

            closedList[current.getRow()][current.getCol()] = true;

            // If goal is reached, reconstruct the path and return
            if (current.getRow() == goalRow && current.getCol() == goalCol) {
                path = GraphNode.getStringPath(current);
                totalDistance = current.getG();
                System.out.println("Path: " + path);
                System.out.println("Total Distance: " + totalDistance);
                return;
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newRow = current.getRow() + rowMove[i];
                int newCol = current.getCol() + colMove[i];

                if (GraphNode.isValid(matrix, newRow, newCol) && !closedList[newRow][newCol]) {
                    GraphNode neighbor = matrix[newRow][newCol];
                    if (neighbor == null) continue;  // Skip invalid or blocked cells

                    int tentativeG = current.getG() + 1;

                    // If the node is not in the open list or we found a shorter path
                    if (tentativeG < neighbor.getG() || !contains(openList, neighbor)) {
                        neighbor.setG(tentativeG);
                        neighbor.setH(calculateHeuristic(newRow, newCol, goalRow, goalCol));
                        neighbor.setPrevious(current);

                        openList.enqueue(neighbor);
                    }
                }
            }
        }

        System.out.println("No path found to the goal.");
    }

    // Heuristic: Manhattan distance
    public static int calculateHeuristic(int currentRow, int currentCol, int goalRow, int goalCol) {
        return Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
    }

    // Custom function to find the node with the smallest f = g + h
    private static GraphNode getMinNode(Queue<GraphNode> queue) {
        GraphNode minNode = queue.front();
        int minF = minNode.getG() + minNode.getH();

        for (GraphNode node : queue) {
            int f = node.getG() + node.getH();
            if (f < minF) {
                minNode = node;
                minF = f;
            }
        }
        return minNode;
    }

    // Check if a node is already in the queue
    private static boolean contains(Queue<GraphNode> queue, GraphNode node) {
        for (GraphNode n : queue) {
            if (n.equals(node)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] grid = {
                "00000000000000I00000000000000000000000",
                "0000000000000000000000000000I000000000",
                "00I0000000I000000000000000000000000000",
                "000I0000000000000I00000000000000I00000",
                "00000000000000000000000000000000000000",
                "000000I0000000000000000I00000000000000",
                "00000000000000000000000000000000000000",
                "0000000000000I000000000000000000I00000",
                "00000000000000000000000000000000000000",
                "00000000000000000000000000000000000000",
                "000I000I00000000000000000000I000000000",
                "0000000000000000000000II00000000000000"
        };

        GraphNode[][] matrix = parseGrid(grid);

        // Define start and goal
        int startRow = 0, startCol = 0;
        int goalRow = 11, goalCol = 24;

        aStar(matrix, startRow, startCol, goalRow, goalCol);
    }

    public static GraphNode[][] parseGrid(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        GraphNode[][] matrix = new GraphNode[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'I') {
                    matrix[i][j] = new GraphNode(i, j, "Item", true); // Node with an item
                } else if (cell == '0') {
                    matrix[i][j] = new GraphNode(i, j, "Empty", false); // Empty node
                } else {
                    matrix[i][j] = null; // Invalid or blocked node
                }
            }
        }
        return matrix;
    }
}
