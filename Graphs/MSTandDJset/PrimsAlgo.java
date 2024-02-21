package Graphs.MSTandDJset;

import java.util.ArrayList;
import java.util.PriorityQueue;

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

public class PrimsAlgo {
    public static void main(String[] args) {
        int V = 3 , E = 3 ;
        int[][] edges = {{0,1,5},
                         {1,2,3},
                         {0,2,1}};

        System.out.println(spanningTree(V, E, edges));
        
    }

    private static int spanningTree(int V, int E, int edges[][]){
	     // construct adjacency list
	     ArrayList<ArrayList<int []>> adj = new ArrayList<>();
	     
	     for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());
	     
	     for(int i = 0 ; i < E ; i++){
	         int u = edges[i][0];
	         int v = edges[i][1];
	         int wt = edges[i][2];  // edge  u<--wt-->v
	         adj.get(u).add(new int[]{v,wt});
	         adj.get(v).add(new int[]{u,wt});
	     }
	     
	     // initial configuration of prims algorithm
	     PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> (a[0]-b[0])); // priority based on wt`s
	     boolean vis[] = new boolean[V];
	     int sum = 0 ;
	     int[][] mst = new int[V-1][]; // mst graph with n nodes and n-1 edges , 
	     int mstIndex = 0 ;
	     pq.offer(new int[]{0,0,-1}) ; // {wt,node,parent}
	     
	     
	     while(!pq.isEmpty()){
	         int[] curr = pq.poll();
	         int wt = curr[0] , node = curr[1] , parent = curr[2];
	         
	         if(vis[node]) continue; // if already visisted 
	         
	         vis[node] = true;
	         sum += wt;
	         if(parent != -1) {
	             mst[mstIndex] = new int[]{parent,node};
	             mstIndex++;
	         }
	         
	         for(int arr[] : adj.get(node)){
	             int adjNode = arr[0];
	             int adjWt = arr[1];
	             if(!vis[adjNode]) pq.offer(new int[]{adjWt,adjNode,node});
	         }
	         
	     }
	   //  for(int row[] : mst) System.out.println(Arrays.toString(row));
	     // Tc -> ElogE
	     return sum;
	   //  return mst;   if mst is asked
	}
}
