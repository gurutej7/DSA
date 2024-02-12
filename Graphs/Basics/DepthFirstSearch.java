package Graphs.Basics;

import java.util.ArrayList;
import java.util.Scanner;

/* { https://www.codingninjas.com/studio/problems/dfs-traversal_630462 }

Given an undirected and disconnected graph G(V, E), containing 'V' vertices and 'E' edges, the information 
about edges is given using 'GRAPH' matrix, where i-th edge is between GRAPH[i][0] and GRAPH[i][1]. print its DFS traversal.
V is the number of vertices present in graph G and vertices are numbered from 0 to V-1. 
E is the number of edges present in graph G.
Note :
The Graph may not be connected i.e there may exist multiple components in a graph. 

Sample Input 1:
5 4
0 2
0 1
1 2
3 4
Sample Output 1:
0 1 2
3 4
 */

public class DepthFirstSearch {
    public static void main(String[] args) {
        // Given m edges and n nodes we have to construct the adjacency matrix
       
        int n = 5 , m = 4 ;
        ArrayList<ArrayList<Integer>> adj = constructAdj(n,m);

        ArrayList<ArrayList<Integer>> res = depthFirstSearch(n, adj);

        System.out.println(res);
    }
    private static ArrayList<ArrayList<Integer>> depthFirstSearch(int v,ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int visited[] = new int[v];
        // since given the graph may are may not have connected componenets I am keeping every node as a starting node 
        for(int i = 0 ; i < v ; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            if(visited[i] == 0)     dfs(i,visited,adj,temp);
            // if it is a separate componenet then add list of it
            if(temp.size() > 0) res.add(temp);
        }

        return res;
    }

    private static void dfs(int node , int[] visited , ArrayList<ArrayList<Integer>> adj ,ArrayList<Integer> list){
        visited[node] = 1 ;
        list.add(node);

        for(Integer i : adj.get(node)){
            if(visited[i] == 0) dfs(i, visited, adj, list);
        }
    }
    // function to construct the adjacency matrix of a graph given v nodes and e edges
    public static ArrayList<ArrayList<Integer>> constructAdj(int v , int e){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for(int i = 0 ; i< v ; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0 ; i < e ; i++){
            int U = sc.nextInt();
            int V = sc.nextInt();
            adj.get(U).add(V);
            adj.get(V).add(U);
        }

        sc.close();

        return adj;
    }
}
    

