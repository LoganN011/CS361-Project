import java.util.Scanner;

public class FileIO {
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


    public static void main(String[] args) {
        GraphNode[][] matrix = buildGraph();
        printGraph(matrix);
        BFS.printInfo(matrix, 0, 0, true);
        DFS.printInfo(matrix, 0, 0, 0, true);

    }
}
