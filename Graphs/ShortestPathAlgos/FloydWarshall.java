package Graphs.ShortestPathAlgos;

import java.util.Arrays;

/* Floyd Warshall
{ https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1 }

 The problem is to find the shortest distances between every pair of vertices in a given edge-weighted 
 directed graph. The graph is represented as an adjacency matrix of size n*n. Matrix[i][j] denotes the weight of 
 the edge from i to j. If Matrix[i][j]=-1, it means there is no edge from i to j.
    Do it in-place.
Ex : Input: matrix = {{0,1,43},{1,0,6},{-1,-1,0}}
Output: {{0,1,7},{1,0,6},{-1,-1,0}}
Explanation: We can reach 2 from 0 as 0->1->2
and the cost will be 1+6=7 which is less than 
43.
 */

public class FloydWarshall {
    public static void main(String[] args) {
        int [][] matrix = {{0,1,43},
                           {1,0,6},
                           {-1,-1,0}};
        
        shortestDistance(matrix);

        for(int row[] : matrix) System.out.println( Arrays.toString(row) );
        
    }

    //  Multisource Shortest Path Algorithm , also helps us to detect negative cycles as well
    //  The Algorithm is not much intuitive as the other one`s . It is more of a bruteforce, where all combinations
    // of the paths have been tried to get the shortest paths.
    private static void shortestDistance(int[][] matrix){
        int n = matrix.length;
        int INF = (int)1e9;
        for(int i = 0 ; i < n ; i++ ){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == -1) matrix[i][j] = INF ; // unreachable nodes
                // if(i == j) matrix[i][j] = 0 ;
            }
        }
        
        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(matrix[i][k] != INF && matrix[k][j] != INF) // to avoid going via unreachable nodes
                        matrix[i][j] = Math.min(matrix[i][j] , matrix[i][k] + matrix[k][j]);
                        
                }
            }
        }
        
        // To detect negative cycles 
        // for(int i = 0 ; i < n ; i++) 
        //     if(matrix[i][i] < 0) => negative cycle
        
        
        // Due to problem requirement we have to mark the unreachable nodes as -1
        for(int i = 0 ; i < n ; i++ ){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == INF) matrix[i][j] = -1 ; 
            }
        }
        
        // TC -> O(N^3)
    }

    // Problem
    // { https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/ }
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int adj[][] = new int[n][n];
        int INF = (int)1e9;
        for(int row[] : adj) Arrays.fill(row,INF);
        for(int i = 0 ; i < edges.length ; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2]; // edge => u<--wt-->v
            adj[u][v] = wt;
            adj[v][u] = wt;
        }

        for(int i = 0 ; i < n ; i++) adj[i][i] = 0 ;  // node to same node , dost is zero
        // Approach -> Use Floyd-Warshall's algorithm to compute any-point to any-point distances. 
        // (Or can also do Dijkstra from every node due to the weights are non-negative).

        // Floyd Warshall
        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(adj[i][k] != INF && adj[k][j] != INF) // to avoid going via unreachable nodes
                        adj[i][j] = Math.min(adj[i][j] , adj[i][k] + adj[k][j]);
                        
                }
            }
        }

        // For each city calculate the number of reachable cities within the threshold, 
        // then search for the optimal city.

        int cityCnt = n , city = -1 ;
        for(int i = 0 ; i < n ; i++){
            int currCnt = 0 ;
            for(int j = 0 ; j < n ; j++){
                if(i != j && adj[i][j] <= distanceThreshold ) currCnt++;
            }

            if(currCnt <= cityCnt){
                city = i ;
                cityCnt = currCnt;
            }
        }
        
        return city;
    }
    
}
