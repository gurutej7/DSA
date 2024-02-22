package Graphs.MSTandDJset;

import java.util.ArrayList;
import java.util.Collections;

/* Minimum Spanning Tree
{ https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1 }

Given a weighted, undirected and connected graph of V vertices and E edges. The task is to find the sum of weights 
of the edges of the Minimum Spanning Tree. Given adjacency list adj as input parameters . Here adj[i] contains 
vectors of size 2, where the first integer in that vector denotes the end of the edge and the second integer 
denotes the edge weight. 

Example : Input:
3 3
0 1 5
1 2 3
0 2 1           Output : 4

 */
public class KruskalsAlgo {
    public static void main(String[] args) {
        int V = 3 , E = 3 ;
        int[][] edges = {{0,1,5},
                         {1,2,3},
                         {0,2,1}};

        System.out.println(spanningTree(V, E, edges));
    }

    /* Kruskal`s Algorithm
     * Helps us to find the Minimum Spanning Tree
     * 1. Sort all the edges according to their weight
     * use UnionFind to check if they belong to the same component or not
     * if not then uf.union(u,v) and add weight to the MSTsum
     * If yes, then skip because it is already in the component with a less weight,(because weight`s are sorted)
     */


    private static int spanningTree(int V, int E, int edges[][]){
	     // construct adjacency list
	     ArrayList<Edge> adj = new ArrayList<>();
	     
	     for(int i = 0 ; i < E ; i++){
	         int u = edges[i][0];
	         int v = edges[i][1];
	         int wt = edges[i][2];  // edge  u<--wt-->v
	         adj.add(new Edge(u,v,wt));
	       //  adj.add(new Edge(v,u,wt)); if it`s first occurrence is used then DJset will anyway ignore the second occurrence
	     }
	     
	     Collections.sort(adj); // O(MlogM) , M-edges
	     
	     UnionFind uf = new UnionFind(V);
	     int MSTsum = 0 ;
	     
	     for(Edge e : adj){ // O(M*(4*alpha))
	         int u = e.src;
	         int v = e.dest;
	         int wt = e.weight;
	         
	         // If we want to construct MST graph here we have u,v,wt ,we can use them to do so
	         
	         if(uf.unionByRank(u,v)){ // will return false if they are already in the same component
	             MSTsum += wt;
	         }
	     }
	     
	     return MSTsum;
	}
    
}
class Edge implements Comparable<Edge> {
    int src, dest, weight;
    Edge(int src, int dest, int wt) {
        this.src = src; 
        this.dest = dest; 
        this.weight = wt;
    }
    // Comparator function used for
    // sorting edgesbased on their weight
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}
