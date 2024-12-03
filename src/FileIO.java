import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {
    public static long startTime = -1;
    public static long numberNodesVisited = 0;

    // Method to build graph from file (if needed)
    public static GraphNode[][] buildGraph(String fileName) {
        Scanner sc = new Scanner(fileName);
        return getGraphNodes(sc);
    }

    // Method to build graph from user input (console)
    public static GraphNode[][] buildGraph() {
        Scanner sc = new Scanner(System.in);
        return getGraphNodes(sc);
    }

    // Helper method to process graph nodes from the scanner input
    private static GraphNode[][] getGraphNodes(Scanner sc) {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        GraphNode[][] matrix = new GraphNode[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = sc.next();
            for (int j = 0; j < cols; j++) {
                if (line.charAt(j) == '0') {
                    matrix[i][j] = new GraphNode(i, j, "LABEL??", false);
                } else if (line.charAt(j) == 'I') {
                    matrix[i][j] = new GraphNode(i, j, "LABEL??", true);
                }
            }
        }
        return matrix;
    }

    // Method to print the graph (matrix)
    public static void printGraph(GraphNode[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == null) {
                    System.out.print("1");
                } else if (matrix[i][j].hasItem()) {
                    System.out.print("I");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    // Timer methods for performance tracking
    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public static void addToNumberNodesVisited() {
        numberNodesVisited++;
    }

    public static void stopTimer() {
        long endTime = System.currentTimeMillis();
        System.out.println("TIME taken: " + (endTime - startTime) + " ms");
        System.out.println("Number of nodes visited: " + numberNodesVisited);
        startTime = -1;
        numberNodesVisited = 0;
    }

    // Main method with all algorithms (BFS, DFS, Bellman-Ford, Dijkstra, A*)
    public static void main(String[] args) throws FileNotFoundException {
        // Build the graph from input (e.g., user input or file)
        Scanner sc;
        if (args.length == 0) {
            sc = new Scanner(System.in);
        } else {
            sc = new Scanner(new File(args[0]));
        }
        while (sc.hasNext()) {
            GraphNode[][] matrix = getGraphNodes(sc);
            printGraph(matrix);

            // Copy matrix for each algorithm to avoid mutating the original matrix
            GraphNode[][] bfsWithouRobot = GraphNode.copyMatrix(matrix);
            System.out.println("\nBFS: ");
            startTimer();
            BFS.printInfo(bfsWithouRobot, 0, 0, true, false);
            stopTimer();

            GraphNode[][] bfsWithRobot = GraphNode.copyMatrix(matrix);
            System.out.println("\nBFS With robot: ");
            startTimer();
            BFS.printInfo(bfsWithRobot, 0, 0, true, true);
            stopTimer();

            // For DFS
            GraphNode[][] dfs = GraphNode.copyMatrix(matrix);
            System.out.println("\nDFS: ");
            startTimer();
            DFS.printInfo(dfs, 0, 0, 0, true, false);
            stopTimer();

            GraphNode[][] dfsWithRobot = GraphNode.copyMatrix(matrix);
            System.out.println("\nDFS with robot: ");
            startTimer();
            DFS.printInfo(dfsWithRobot, 0, 0, 0, true, true);
            stopTimer();

            // For Bellman-Ford
            GraphNode[][] bell = GraphNode.copyMatrix(matrix);
            System.out.println("\nBellman-Ford: ");
            startTimer();
            BellmanFord.printInfo(bell, 0, 0, true, false);
            stopTimer();

            GraphNode[][] bellWithRobot = GraphNode.copyMatrix(matrix);
            System.out.println("\nBellman-Ford With robot: ");
            startTimer();
            BellmanFord.printInfo(bellWithRobot, 0, 0, true, true);
            stopTimer();

            // For Dijkstra
            GraphNode[][] dijkstraWithoutRobot = GraphNode.copyMatrix(matrix);
            System.out.println("\nDijkstra without Robot: ");
            startTimer();
            Dijkstra.printInfo(dijkstraWithoutRobot, 0, 0, true, false);
            stopTimer();

            GraphNode[][] dijkstraWithRobot = GraphNode.copyMatrix(matrix);
            System.out.println("\nDijkstra with Robot: ");
            startTimer();
            Dijkstra.printInfo(dijkstraWithRobot, 0, 0, true, true);
            stopTimer();

            // A* algorithm for multiple targets
            GraphNode[][] aStar = GraphNode.copyMatrix(matrix); // Copy of matrix for A*
            System.out.println("\nA*:  ");
            startTimer();
            AStar.printInfo(aStar, 0, 0, true, false);
            stopTimer();

            GraphNode[][] aStarWithRobot = GraphNode.copyMatrix(matrix); // Copy of matrix for A*
            System.out.println("\nA* with robot:  ");
            startTimer();
            AStar.printInfo(aStarWithRobot, 0, 0, true, true);
            stopTimer();
        }
    }

}