package Graphs.BFSorDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* Detect Cycle in an Undirected Graph
{ https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1 }

Given an undirected graph with V vertices labelled from 0 to V-1 and E edges, check whether it contains any cycle 
or not. Graph is in the form of adjacency list where adj[i] contains all the nodes ith node is having edge with.

Example 1:  Input:  V = 5, E = 5
adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}} 
Output: true

 */

public class DetectCycleInUDG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        ArrayList<ArrayList<Integer>> adj = constructAdj(v, e);
        // System.out.println(adj);

        System.out.println(isCycleBFS(v, adj));
        System.out.println(isCycleDFS(v, adj));

        sc.close();;
    }

    // using BFS
    /* Intuition and Logic
     * In an undirected graph when we are traversing at a node the connected nodes will be where we are coming from 
     * and where we are going , we dont move to the node from where we came from
     * we will keep track of our parent , when moving forward if any node is visited , if it is our parent then ok
     * because we came from that path , 
     * if it is not parent but visisted means some other node already took this path which indicates a cycle. 
    * TC: O(N+2E)
    * SC: O(N)
     */
    private static boolean isCycleBFS(int v, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean vis[] = new boolean[v];
        
        for(int i = 0 ; i < v ; i++){
            if(!vis[i]){
                vis[i] = true;
                if(BFS(i,vis,adj)) return true;
            }
        }
        
        return false;
    }
    
    private static boolean BFS(int start , boolean[] vis ,ArrayList<ArrayList<Integer>> adj ){
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(start,-1));
        
        while(!q.isEmpty()){
            Pair curr = q.poll();
            int node = curr.getFirst();
            int parent = curr.getSecond(); // from where we are coming from
            
            for(int i : adj.get(node)){
                if(!vis[i]) {
                    vis[i] = true;
                    q.offer(new Pair(i,node));
                }
                else if(i != parent) return true;
            }
        }
        
        return false;
        
    }

    // using DFS same logic
    private static boolean isCycleDFS(int v, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean vis[] = new boolean[v];
        
        for(int i = 0 ; i < v ; i++){
            if(!vis[i]){
                vis[i] = true;
                if(DFS(i,vis,adj,-1)) return true;
            }
        }
        
        return false;
    }

    private static boolean DFS(int start , boolean[] vis , ArrayList<ArrayList<Integer>> adj , int parent ){
        for(int i  : adj.get(start)){
            if(!vis[i]){
                vis[i] = true;
                if(DFS(i, vis, adj, start)) return true;
            }

            else if(i != parent) return true;
        }

        return false;
    }



    private static ArrayList<ArrayList<Integer>> constructAdj(int n,int m){
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // given nodes 0 based so add n-1 array lists
        for(int i = 0 ; i< n ; i++){
            adj.add(new ArrayList<>());
        }
        // adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}} 
        /*
         * edges => [0,1] ,[1,2] ,[2,3] ,[3,4] ,[4,1]
         */
        
        for(int i = 0 ; i<m ; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        sc.close();
        
        return adj;
    }
}


    
