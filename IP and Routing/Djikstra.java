// Raggav Subramani - 20BCT0127

/*
Algorithm 
1) Create a set sptSet (shortest path tree set) that keeps track of vertices included in the shortest-path tree, i.e., whose minimum distance from the source is calculated and finalized. Initially, this set is empty. 
2) Assign a distance value to all vertices in the input graph. Initialize all distance values as INFINITE. Assign distance value as 0 for the source vertex so that it is picked first. 
3) While sptSet doesn’t include all vertices 
	a) Pick a vertex u which is not there in sptSet and has a minimum distance value. 
	b) Include u to sptSet. 
	c) Update distance value of all adjacent vertices of u. To update the distance values, iterate through all adjacent vertices. For every adjacent vertex v, if the sum of distance value of u (from source) and weight of edge u-v, is less than the distance value of v, then update the distance value of v. 
*/

import java.util.*;

class Djikstra {
	int minDistance(int dist[], Boolean sptSet[], int n) {
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < n; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}

	void printSolution(int dist[], int n) {
		System.out.println("\nShortest distance from source node:");
		System.out.println("Vertex \t\t Distance from Source");
		for (int i = 0; i < n; i++)
			System.out.println((i+1) + " \t\t " + dist[i]);
	}

	void dijkstra(int graph[][], int src, int n) {
		int dist[] = new int[n]; 
		Boolean sptSet[] = new Boolean[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		dist[src] = 0;
		for (int count = 0; count < n - 1; count++) {
			int u = minDistance(dist, sptSet, n);
			sptSet[u] = true;
			for (int v = 0; v < n; v++)
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}
		printSolution(dist, n);
	}

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of nodes in graph:");
        int n = sc.nextInt();
		System.out.println("Input node details as matrix:");
		int graph[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();  
		System.out.println("Input source node:");
		int src = sc.nextInt();
		new Djikstra().dijkstra(graph, src-1, n);
        sc.close();
	}
}
