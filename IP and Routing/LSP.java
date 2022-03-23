import java.util.*;

class LinkState {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of nodes:");
        int n = sc.nextInt();
        System.out.println("Input nodes for LSP:");
        System.out.println("Input matrix(Enter -1 for all inputs corresponding to itself for matrix):");
        int cost[][] = new int[n][n];
        int net[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();
        System.out.println("Input network matrix:");
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                net[i][j] = sc.nextInt();
        System.out.println("|\tAdvertiser\t|\tNetwork\t|\tCost\t|\tNeighbour\t|");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(cost[i][j] > 0) {
                    System.out.print("|\t" + (char)(65+i) + "\t|\t" + net[i][j] + "\t|\t" + cost[i][j] + "\t|\t" + (char)(65+j) + "\t|");
                }
                System.out.println();
            }
        }
        sc.close();
    }
}
