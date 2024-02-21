package Graphs.ShortestPathAlgos;

import java.util.ArrayList;
import java.util.Arrays;

/* Distance from the Source (Bellman-Ford Algorithm)
 { https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1 }
 
Given a weighted and directed graph of V vertices and E edges, Find the shortest distance of all the vertex's 
from the source vertex S. If a vertices can't be reach from the S then mark the distance as 10^8. Note: If the 
Graph contains a negative cycle then return an array consisting of only -1. 

Example : E = [[0,1,9]]     S = 0
Output: 0 9
Explanation:    Shortest distance of all nodes from source is printed.
 */

public class BellmanFord{
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(0,1,9)));

        int dist[] = bellmanFord(2, edges, 0);

        System.out.println( Arrays.toString(dist) );
        
    }

    /* BellmanFord Algorithm
     * used to find the shortest dist from source node to all other nodes.
     * Dijkstra will fail in case of negative weight/ in graphs with negative weight cycle
     * Bellmanford helps in such cases to detect shortest path
     * only applicable  to DG
     * In case of UG  a--wt--b  => covert to     a--wt-->b  and   a<--wt--b
     * Step 1- `Relax All the edges N-1 times sequentially`
     * Relax : - If there is an edge between u--wt-->v   
     *              if(dist[u] + wt < dist[v]) dist[v] = dist[u] + wt
     * N - 1 times / iterations
     * on 1st iteration , we have to go across all the edges , and relax them
     * repeat the above process for n-1 times
     * Then again run the nth iteration separately , if still there is a scope to update distance then the graph has negative weight cycle
     * TC = O(E*V)
     */

    private static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int src) {
        int dist[] = new int[V];
        int INF = (int)1e8;
        Arrays.fill(dist,INF);
        
        dist[src] = 0 ; // source
        
        // relax all the edges N-1 times
        for(int i = 0 ; i < V-1 ; i++){
            for(ArrayList<Integer> list : edges){
                int u = list.get(0);
                int v = list.get(1);
                int wt = list.get(2);
                
                if(dist[u] != INF && dist[u]+wt < dist[v])
                    dist[v] = dist[u]+wt;
            }
            
        }
        
        // Nth relaxation to check for negative cycles
        for(ArrayList<Integer> list : edges){
                int u = list.get(0);
                int v = list.get(1);
                int wt = list.get(2);
                
                if(dist[u] != INF && dist[u]+wt < dist[v]) // meaning there exists a negative cycle
                    return new int[]{-1};
        }
        
        return dist;
        // TC -> O(V*E)
    }
}