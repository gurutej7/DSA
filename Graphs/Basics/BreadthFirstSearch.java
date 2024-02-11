package Graphs.Basics;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
// { https://www.geeksforgeeks.org/problems/bfs-traversal-of-graph/1 }

/* Example : Input: V = 5, E = 4    adj = {{1,2,3},{},{4},{},{}}

Output: 
0 1 2 3 4
Explanation: 
0 is connected to 1 , 2 , 3.
2 is connected to 4.
so starting from 0, it will go to 1 then 2
then 3. After this 2 to 4, thus bfs will be
0 1 2 3 4.
 
 */
public class BreadthFirstSearch {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = inputAdj(5);

        ArrayList<Integer> bfs = bfsOfGraph(5, adj);

        System.out.println( bfs ); // expected 0 1 2 3 4 
        
    }
    
    private static ArrayList<Integer> bfsOfGraph(int n, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] visited = new int[n];
        Queue<Integer> q = new LinkedList<>();
        visited[0] = 1;
        q.offer(0);
        while(!q.isEmpty()){
            int curr = q.poll();
            res.add(curr);

            for(Integer i : adj.get(curr)){
                if(visited[i] == 0){ // 0 => not visited
                    q.offer(i);
                    visited[i] = 1 ; // 1 => visited
                }
            }
        }

        return res;
    }

    private static ArrayList<ArrayList<Integer>> inputAdj(int n){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // given nodes 0 based so add n array lists
        for(int i = 0 ; i< n ; i++){
            adj.add(new ArrayList<>());
        }
        // adj = {{1,2,3},{},{4},{},{}}

        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(0).add(3);

        adj.get(2).add(4);

        return adj;
    }
}
