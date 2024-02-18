package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import Graphs.BFSorDFS.Pair;

/* Shortest path in Directed Acyclic Graph
{ https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1 }

Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array(or vector) edges[ ][ ] of length 
M, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.
Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then 
return -1 for that vertex.

Example 1:  Input:      N = 6, M = 7    edge = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
Output:
0 2 3 6 1 5
Explanation:
Shortest path from 0 to 1 is 0->1 with edge weight 2. 
Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3.
Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6.
Shortest path from 0 to 4 is 0->4 with edge weight 1.
Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
 */
public class ShortestPathInDAG {
    public static void main(String[] args) {
        int N = 6 , M = 7 ;
        int[][] edges = {{0,1,2},
                         {0,4,1},
                         {4,5,4},
                         {4,2,2},
                         {1,2,3},
                         {2,3,6},
                         {5,3,1}};
        int shortestpath[] = shortestPath(N, M, edges);

        System.out.println(Arrays.toString(shortestpath)); // expected [0,2,3,6,1,5]
    }

    private static int[] shortestPath(int N,int M, int[][] edges) {
	    // Construct adj matrix 
	    ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
	    
	    for(int i = 0 ; i < N ; i++) adj.add(new ArrayList<>());
	    
	    for(int i = 0 ; i < M ; i++){
	        for(int j = 0 ; j < 3 ; j++){
	            int a = edges[i][0];
	            int b = edges[i][1];
	            int w = edges[i][2];
	            // edge =   a --w--> b
	            adj.get(a).add(new Pair(b,w));
	            
	        }
	    }
	    
	    // Prepare the stack using the toposort
	    Stack<Integer> st = new Stack<>();
		boolean vis[] = new boolean[N];
		
		for(int i = 0 ; i < N ; i++){
		    if(!vis[i]) dfsTopoSort(i,vis,adj,st);
		}
		
		int INF = (int)1e7;
		int dist[] = new int[N];
		Arrays.fill(dist,INF);
		// mark the distance of source node as 0
		dist[0] = 0 ; // given 0 is always the source in this problem
		
		// Now take one by one from the stack and update the dist[i] with min value possible
		// O(N+M)
		while(!st.isEmpty()){
		    int currNode = st.pop();
		    int currDist = dist[currNode];  
		    
		    for(Pair p : adj.get(currNode)){
		        int v = p.getFirst(); // getFirst returns the node and getSecond returns the weight
		        int w = p.getSecond();
		        dist[v] = Math.min(dist[v] , currDist+w);
		    }
		}
		
		for(int i = 0 ; i < N ; i++){
		    if(dist[i] == INF) dist[i] = -1 ; // this node cannot be reached from the source
		}
		
		return dist;
		
	}
	
	private static void dfsTopoSort(int src , boolean[] vis , ArrayList<ArrayList<Pair>> adj , Stack<Integer> st){
	    vis[src] = true;
	    
	    for(Pair p : adj.get(src)){
	        int curr = p.getFirst() ; // getFirst returns the node and getSecond returns the weight
	        if(!vis[curr]) dfsTopoSort(curr,vis,adj,st);
	    }
	    
	    st.push(src);
	}
    
}
