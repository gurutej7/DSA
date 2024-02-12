package Graphs.BFSorDFS;

import java.util.ArrayList;

/* 547. Number of provinces 
{ https://leetcode.com/problems/number-of-provinces/description/ }
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly 
connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
Example : Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
  
 */

public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},
                               {1,1,0},
                               {0,0,1}};
        System.out.println(findCircleNum(isConnected));

        System.out.println(findCircleNum2(isConnected));
        
    }

    // Approach-1 coverting the given matrix into a adjacency list and doing dfs
     // question is indirectly asking to find the number of connected componenets
    private static int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adj = constructAdj(isConnected);
        int n = isConnected.length;

        int visited[] = new int[n];

        int cnt = 0 ;
        // Approach if we start dfs at a node then all the nodes connected directly or indirectly to that start node
        for(int i = 0 ; i < n ; i++){ 
            if(visited[i] == 0){ // if we are here then we are at new component which is separate from the previous component
                cnt++;
                dfs(i,visited,adj);
            }
                
        }

        return cnt;
    }

    private static void dfs(int node , int[] visited , ArrayList<ArrayList<Integer>> adj ){
        visited[node] = 1 ;

        for(Integer i : adj.get(node)){
            if(visited[i] == 0) dfs(i, visited, adj);
        }
    }

    private static ArrayList<ArrayList<Integer>> constructAdj(int[][] matrix){
        int n = matrix.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i< n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ;j++){
                if(matrix[i][j] == 1){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        return adj;
    }


    // Using the same approach directly on the matrix
     // question is indirectly asking to find the number of connected componenets
     private static int findCircleNum2(int[][] adj) {
        int n = adj.length;

        int visited[] = new int[n];

        int cnt = 0 ;
        // Approach if we start dfs at a node then all the nodes connected directly or indirectly to that start node
        for(int i = 0 ; i < n ; i++){ 
            if(visited[i] == 0){ // if we are here then we are at new component which is separate from the previous component
                cnt++;
                dfs(i,visited,adj);
            }
                
        }

        return cnt;
    }

    private static void dfs(int node , int[] visited , int[][] adj ){
        visited[node] = 1 ;

        for(int j = 0 ; j < adj.length ; j++){
            if(adj[node][j] == 1 && visited[j] == 0){
                dfs(j,visited,adj);
            }
        }
    }
}
