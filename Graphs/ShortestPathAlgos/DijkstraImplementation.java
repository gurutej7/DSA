package Graphs.ShortestPathAlgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import Graphs.BFSorDFS.Pair;

/* Dijkstra`s Algorithm ->(Shortest Path)
{ https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1 }
Given a source node , find the shortest path from the source Node to every other Node.
Then Edge has three parameters a, b, wt
edge a <--wt--> b  wt, distance to travel from a to b
Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where 
adj[i] is a list of lists containing two integers where the first integer of each list j 
denotes there is edge between i and j , second integers corresponds to the weight of that  
edge . You are given the source vertex S and You to Find the shortest distance of all the 
vertex's from the source vertex S. You have to return a list of integers denoting shortest 
distance between each node and Source vertex S.

Note: The Graph doesn't contain any negative weight cycle.

Example 1:  Input:  V = 3, E = 3
adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
S = 2
Output:  [4 3 0]
 */

public class DijkstraImplementation {
    public static void main(String[] args) {
        // Then Edge has three parameters a, b, wt
        // edge a <--wt--> b  wt, distance to travel from a to b
        int edges[][] = {{0,1,1},
                         {0,2,6},
                         {1,2,3}};
        ArrayList<ArrayList<Pair>> adj = constructAdj(edges);
         // ith list has , the nodes and corresponding weights in the form of pair ,to which the i is connected to 
        int dist[] = dijkstra(adj.size(), adj, 2);
        
        System.out.println(Arrays.toString(dist));        
    }

    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    private static int[] dijkstra(int V, ArrayList<ArrayList<Pair>> adj, int src){
        // initial configuration for dijkstra
        int INF = (int)1e7;
        int dist[] = new int[V];
        Arrays.fill(dist,INF);
        dist[src] = 0 ; // the distance from sorce to source is 0
        // Pair.first = distance , Pair.second = node 
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> a.getFirst()-b.getFirst()); // making the pq to work based on distance , smallere distance should be on the top
        pq.offer(new Pair(0,src));
        
        // process
        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int currNode = curr.getSecond();
            int currDist = curr.getFirst(); // Pair.getFirst() returns distance and Pair.getSecond() returns the node
            
            for(Pair p : adj.get(currNode)){
                int adjNode = p.getFirst();
                int adjNodeDist = p.getSecond();
                
                if(dist[adjNode] > adjNodeDist+currDist){
                    dist[adjNode] = currDist+adjNodeDist;
                    pq.offer(new Pair(dist[adjNode],adjNode)); // distance , node
                }
            }
            
        }
        
        return dist;
        // List<Integer> res = new ArrayList<>();

        // for(int i : dist) res.add(i);
        
        // return res;
    }

    private static ArrayList<ArrayList<Pair>> constructAdj(int edges[][] ){
        int V = edges.length;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());

        for(int i = 0 ; i < V ; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            int wt = edges[i][2];
            adj.get(a).add(new Pair(b,wt));
            adj.get(b).add(new Pair(a,wt));
        }

        return adj;

    }
}
