package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 802. Find Eventual Safe States
{ https://leetcode.com/problems/find-eventual-safe-states/description/ } 

There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is 
represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes 
adjacent to node i, meaning there is an edge from node i to each node in graph[i].
A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible 
path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 */

public class FindEventualSafeStates {
    public static void main(String[] args) {
        int graph [][] = {{1,2},
                          {2,3},
                          {5},
                          {0},
                          {5},
                          {},
                          {}};
        
        System.out.println(eventualSafeNodesBFS(graph));
        System.out.println(eventualSafeNodesDFS(graph));
        
    }

    private static List<Integer> eventualSafeNodesDFS(int[][] graph) {
        // by observing the examples the  nodes which are part of the cycle 
        // and the nodes which are having outgoing edges to the any of the nodes in the cycle will never be able to reach the terminal nodes
        // Approach 
        // Use a array to keep track of the possible answers
        // cycle detection can be done using dfs or bfs
        // DFS approach - We start at a node and if there is a cycle then all the nodes in that path will be marked as 0
        // if there is no cycle , then we can mark it as 1
        // if we found a cycle we should not revert the path vis 
        // so that in future we can keep track of nodes that may take that path and end up not reaching the terminal node
        int V = graph.length;
        boolean vis[] = new boolean[V];
        boolean pathVis[] = new boolean[V];
        boolean isValid[] = new boolean[V];
        List<Integer> res = new ArrayList<>();

        for(int i = 0 ; i < V ; i++){
            if(!vis[i])
                if( dfs(i,vis,pathVis,isValid,graph)) isValid[i] = false;
        }

        for(int i = 0 ; i < V ; i++){
            if(isValid[i] == true) res.add(i);
        }

        return res;
    }

    // returns true if there is a cycle , if there is a cycle means mark the nodes in that path as not Valid
    private static boolean dfs(int src , boolean[] vis ,boolean pathVis[] , boolean isValid[] , int[][] graph){
        vis[src] = true;
        pathVis[src] = true;

        for(int i : graph[src]){
            if(!vis[i]){
                if(dfs(i,vis,pathVis,isValid,graph)){
                    isValid[i] = false;
                    return true;
                }
            }
            else if(pathVis[i]){
                isValid[i] = false;
                 return true;
            } // if it is path visited means cycle
        }

        // if we didnot returned above then there is no cycle and the node can be reached to terminal node
        isValid[src] = true;
        pathVis[src] = false; // revert the changes
        return false ; // no cycle
    }

    private static List<Integer> eventualSafeNodesBFS(int[][] graph) {
        // by observing the examples the  nodes which are part of the cycle 
        // and the nodes which are having outgoing edges to the any of the nodes in the cycle will never be able to reach the terminal nodes
        // Approach 
        // Use a array to keep track of the possible answers
        // cycle detection can be done using dfs or bfs
        // BFS approach - it is a slight modification of the topo sort
        // We know that the nodes with out degree with 0 are terminal nodes
        // we start from there then we cut the edges of the nodes that are coming to the curr node
        // after cutting if it`s out degree becomes zero then it can be reached to the terminal node
        int V = graph.length;
        int outdegree[] = new int[V];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();

        for(int i = 0 ; i < V ; i++) revAdj.add(new ArrayList<>());

        // creating a revAdj , where ith list has the all the nodes which are having i as the outgoing edge
       
        for(int i = 0 ; i < V ; i++ ) {
            outdegree[i] = graph[i].length;
            for(int v : graph[i]) revAdj.get(v).add(i);   

            if(outdegree[i] == 0) {
                q.offer(i);
            } 
        }

        while(!q.isEmpty()){
            int curr = q.poll();
            res.add(curr);

            for(int i : revAdj.get(curr)){
                outdegree[i]--;
                if(outdegree[i] == 0) q.offer(i);
            }
        }

        // They want us to return in the ascending order
        Collections.sort(res);
        return res;
    }
}

    
