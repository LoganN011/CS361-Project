import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class FileIO {
    public static int numOfItems = 1;
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
        int count = 1;
        for (int i = 0; i < rows; i++) {
            String line = sc.next();
            for (int j = 0; j < cols; j++) {
                if (line.charAt(j) == '0') {
                    if(i == 0 && j == 0){
                        matrix[i][j] = new GraphNode(i, j, "START", false);
                        matrix[i][j].setAdjacencyPlace(0);
                    }
                    matrix[i][j] = new GraphNode(i, j, "EMPTY", false);
                }
                else if (line.charAt(j) == 'I') {
                    matrix[i][j] = new GraphNode(i, j, "ITEM", true);
                    matrix[i][j].setAdjacencyPlace(count);
                    System.out.println(matrix[i][j].getAdjacencyPlace());
                    numOfItems++;
                    count ++;
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
        BFS.printInfo(GraphNode.copyMatrix(matrix), 0, 0, true);
        DFS.printInfo(GraphNode.copyMatrix(matrix), 0, 0, 0, true);
        dijkstra.dijkstra(GraphNode.copyMatrix(matrix), numOfItems);
        System.out.println(numOfItems);

    }
}
