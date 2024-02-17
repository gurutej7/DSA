package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*  { https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1 }
 * Sample Input 1 :
n = 5   edges = 6
[0 1
3 0
1 3
2 3
4 1
0 2]
Sample Output 1 :
true
Explanation For Input 1 :
The given graph contains cycle 0 -> 2 -> 3 -> 0 or the cycle 0 -> 1 -> 3 -> 0.
 */

public class DetectCycleInDG {
    public static void main(String[] args) {
        int edges[][] = {{0,1},
                         {3,0},
                         {1,3},
                         {2,3},
                         {4,1},
                         {0,2}};
        int V = 5 ;
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());

        for(int i = 0 ; i < V ; i++){
            int a = edges[i][0];
            int b = edges[i][1];  // edge = a -> b
            adj.get(a).add(b);
        }

        System.out.println(isCyclicDFS(V, adj));
        System.out.println(isCyclicBFS(V, adj));
    }

    // Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.
    private static boolean isCyclicDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean vis[] = new boolean[V];
        boolean path[] = new boolean[V];
        
        for(int i = 0 ; i < V ; i++){
            if(!vis[i]){
                if(dfs(i,vis,adj,path)) return true;
            }
        }
        
        return false;
    
    }
    
    private static boolean dfs(int node ,boolean[] vis ,ArrayList<ArrayList<Integer>> adj , boolean path[]){
        vis[node] = true;
        path[node] = true;
        for(int i : adj.get(node)){
            // we travelling in a path and we found a node which is already visited 
            if(path[i]) return true;
            if(!vis[i]){
                if(dfs(i,vis,adj,path)) return true;
                
            }
        }
        // at the end of the path if we didnot find a cycle before taking the new path revert the changes
        // because via a new path also you may come to same node , yet it need not to be a cycle
        path[node] = false;
        
        return false;
    }

    private static boolean isCyclicBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0 ;
        
        int indegree[] = new int[V];
        
        for(int i = 0 ; i < V ; i++){
            for(int node : adj.get(i)) indegree[node]++;
        }
        
        for(int i = 0 ; i < V ; i++) {
            if(indegree[i] == 0) {
                cnt++;
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()){
            int curr = q.poll();
            
            for(int i : adj.get(curr)){
                indegree[i]--;
                if(indegree[i] == 0){
                    cnt++;
                    q.offer(i);
                }
            }
        }
        
        return cnt != V;
    }

    // TC - O(V+E)
    // SC - O(2N)
}
