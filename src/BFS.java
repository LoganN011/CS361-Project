import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void searchBFS(GraphNode[][] grid, GraphNode start) {
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(start);
        start.incrementSeen();  // Mark start as seen

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();

            // Check if this node has an item
            if (current.hasItem()) {
                System.out.println("Collected item at (" + current.getRow() + ", " + current.getCol() + ")");
            }

            // Explore neighbors (up, down, left, right)
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] dir : directions) {
                int newRow = current.getRow() + dir[0];
                int newCol = current.getCol() + dir[1];

                // Ensure new position is within bounds and not yet seen
                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {
                    GraphNode neighbor = grid[newRow][newCol];
                    if (neighbor != null && neighbor.isDiscovered()) {
                        neighbor.incrementSeen();  // Mark as seen
                        neighbor.setPrevious(current);  // Track path
                        neighbor.setDistance(current.distance + 1);  // Update distance
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Assume the file or input contains the grid information
        GraphNode[][] matrix = FileIO.buildGraph();  // Use your FileIO class for grid input
        GraphNode start = matrix[0][0];  // Starting point, can be set to any point in the grid

        // Run BFS
        searchBFS(matrix, start);
        FileIO.printGraph(matrix);  // Optional: Print the graph after BFS for debugging
    }
}
