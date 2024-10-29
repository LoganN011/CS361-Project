public class DFS {

    public static void searchDFS(GraphNode[][] grid, GraphNode current) {
        // Base case: check if the current node is already seen or is an obstacle
        if (current == null || !current.isDiscovered()) {
            return;
        }

        // Mark the current node as seen
        current.incrementSeen();

        // Process the node (collect item if it has one)
        if (current.hasItem()) {
            System.out.println("Collected item at (" + current.getRow() + ", " + current.getCol() + ")");
        }

        // Define the directions (right, down, left, up)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = current.getRow() + dir[0];
            int newCol = current.getCol() + dir[1];

            // Check if the new position is within bounds
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {
                GraphNode neighbor = grid[newRow][newCol];

                // Only visit unvisited and valid neighbors
                if (neighbor != null && neighbor.isDiscovered()) {
                    neighbor.setPrevious(current);  // Set current node as previous to track the path
                    searchDFS(grid, neighbor);  // Recursively explore the neighbor
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a grid with nodes and initialize with items, obstacles
        GraphNode[][] matrix = FileIO.buildGraph();
        GraphNode start = matrix[0][0];  // Define the start point (could be any node)

        // Execute DFS
        searchDFS(matrix, start);

        // Optionally print the grid after DFS for debugging
        FileIO.printGraph(matrix);
    }
}
