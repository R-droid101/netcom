// Raggav Subramani - 20BCT0127

/*
Algorithm:
1) Read the number of nodes, the cost matrix and the network matrix from the user.
2) Repeat steps 3 and 4 until all the rows and columns of the matrix is covered.
3) If cost[i][j] > 0 then, print out the details of that node.
4) Move to the next element in the matrix.
*/

import java.util.*;

class LinkState {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of nodes:");
        int n = sc.nextInt();
        System.out.println("Input nodes for LSP:");
        System.out.println("Input cost matrix(Enter -1 for all inputs corresponding to itself for matrix):");
        int cost[][] = new int[n][n];
        int net[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();
        System.out.println("Input network matrix:");
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                net[i][j] = sc.nextInt();
        System.out.println("|   Advertiser  |    Network    |      Cost     |   Neighbour   |");
        System.out.println("-----------------------------------------------------------------");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(cost[i][j] > 0) {
                    System.out.print("|\t" + (char)(65+i) + "\t|\t" + net[i][j] + "\t|\t" + cost[i][j] + "\t|\t" + (char)(65+j) + "\t|");
                    System.out.println();
                }
            }
        }
        sc.close();
    }
}
