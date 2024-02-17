package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// { https://www.geeksforgeeks.org/problems/topological-sort/1 }

// Linear ordering of vertices Such that if there is an edge between u and v , u always appears before v in that order
// It only exists in Directed Acyclic Graph

/* v = 5 , edges =  4
[0 2
1 2
3 1
0 4]
Sample Output 1 Explanation:
One correct sort order is : 3 1 0 4 2.
 */

public class TopologicalSort {
    public static void main(String[] args) {
        int[][] eges = {{0,2},
                        {1,2},
                        {3,1},
                        {0,4}};
        System.out.println(topologicalSortDFS(eges, 4, 5));
        System.out.println(topologicalSortBFS(eges, 4, 5));
        
    }

    private static List<Integer> topologicalSortDFS(int[][] edges, int e, int v) {
        // Write your code here!
        Stack<Integer> st = new Stack<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < v ; i++) adj.add(new ArrayList<>());

        for(int i = 0 ; i < e ; i++){
            adj.get(edges[i][0]).add(edges[i][1]);
        }

        boolean vis[] = new boolean[v];

        for(int i = 0 ; i < v ; i++){
            if(!vis[i]) dfs(i,vis,adj,st);
        }

        List<Integer> topoSort = new ArrayList<>();

        for( ; !st.isEmpty() ; st.pop()) topoSort.add(st.peek());

        // while(!st.isEmpty()) {
        //     res.add(st.peek());
        //     st.pop();
        // }
        return topoSort;
    }

    private static void dfs(int node , boolean[] vis ,ArrayList<ArrayList<Integer>> adj ,Stack<Integer> st ){
        vis[node] = true;

        for(int i : adj.get(node))
            if(!vis[i])dfs(i,vis,adj,st);

        
        st.push(node);
    }

    private static List<Integer> topologicalSortBFS(int[][] edges, int e, int v) {
        // Write your code here!
        Queue<Integer> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < v ; i++) adj.add(new ArrayList<>());

        int indegree[] = new int[v];

        for(int i = 0 ; i < e ; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            adj.get(a).add(b);
            indegree[b]++;
            // a -> b , so indegree[b]++
        }

        for(int i = 0 ; i < v ; i++){
            if(indegree[i] == 0) q.offer(i);
        }

        List<Integer> topoSort = new ArrayList<>();

        while(!q.isEmpty()){
            int curr = q.poll();
            topoSort.add(curr);

            for(int vertex : adj.get(curr)){
                indegree[vertex]--;
                if(indegree[vertex] == 0) q.offer(vertex);
            }
        }

        return topoSort;
        
    }

    // TC - O(V + E)
    // SC - O(N)
    
}
