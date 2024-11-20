import java.util.Scanner;

public class FileIO {
    public static long startTime = -1;
    public static long numberNodesVisited = 0;
    public static GraphNode[][] buildGraph(String fileName) {
        Scanner sc = new Scanner(fileName);
        return getGraphNodes(sc);
    }

    public static GraphNode[][] buildGraph() {
        Scanner sc = new Scanner(System.in);
        return getGraphNodes(sc);
    }

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


    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public static void addToNumberNodesVisited() {
        numberNodesVisited++;
    }

    public static void stopTimer() {
        long endTime = System.currentTimeMillis();
        System.out.println("TIME take: " + (endTime - startTime) + " ms");
        System.out.println("Number of nodes visited: " + numberNodesVisited);
        startTime = -1;
        numberNodesVisited = 0;
    }


    public static void main(String[] args) {
        GraphNode[][] matrix = buildGraph();
        printGraph(matrix);
        GraphNode[][] bfs = GraphNode.copyMatrix(matrix);
        System.out.println("BFS: ");
        startTimer();
        BFS.printInfo(bfs, 0, 0, true);
        stopTimer();
        System.out.println("DFS: ");
        GraphNode[][] dfs = GraphNode.copyMatrix(matrix);
        startTimer();
        DFS.printInfo(dfs, 0, 0, 0, true);
        stopTimer();
        GraphNode[][] bell = GraphNode.copyMatrix(matrix);
        System.out.println("Bellman-Ford: ");
        startTimer();
        BellmanFord.printInfo(bell, 0, 0, true);
        stopTimer();

    }
}