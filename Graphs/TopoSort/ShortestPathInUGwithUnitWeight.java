package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* Shortest path in Undirected Graph having unit distance
{ https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1 }

You are given an Undirected Graph having unit weight, Find the shortest path from src to all the vertex and if it 
is unreachable to reach any vertex, then return -1 for that vertex.

Example:  Input: V = 9,  E= 10      src=0
edges=[[0,1],[0,3],[3,4],[4 ,5],[5, 6],[1,2],[2,6],[6,7],[7,8],[6,8]] 
Output:
0 1 2 1 2 3 3 4 4
 * 
 */

public class ShortestPathInUGwithUnitWeight {
    public static void main(String[] args) {
        int edges[][] = {{0,1},{0,3},{3,4},{4,5},{5,6},{1,2},{2,6},{6,7},{7,8},{6,8}};
        int V = 9  , src = 0 ;
        int E = edges.length;

        int shortesPath[] = shortestPath(edges, V, E, src);

        System.out.println( Arrays.toString(shortesPath) );
        
    }

    private static int[] shortestPath(int[][] edges,int V,int E ,int src) {
        
        // construct the adj list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());
        
        for(int i = 0 ; i < E ; i++){
            int a = edges[i][0];
            int b = edges[i][1]; // edges a -> b && b -> a
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        
        
        // initialize distance array with a max value
        int INF = (int)1e7;
        int dist[] = new int[V];
        Arrays.fill(dist,INF);
        dist[src] = 0 ; // mark the dist for source node as 0
        
        // BFS
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        
        while(!q.isEmpty()){
            int curr = q.poll();
            int currDist = dist[curr];
            
            for(int i : adj.get(curr)){
                if(dist[i] > currDist + 1){
                    dist[i] = currDist + 1;
                    q.offer(i);
                }
            }
        }
        
        // check for nodes which can`t be reached with current source as starting point
        for(int i = 0 ; i < V ; i++){
            if(dist[i] == INF) dist[i] = -1 ;
        }
        
        return dist;
    }
    
}
