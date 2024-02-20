package Graphs.ShortestPathAlgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/* 1976. Number of ways to Reach the destination

You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between 
some intersections. The inputs are generated such that you can reach any intersection from any other 
intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a 
road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you 
can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer 
may be large, return it modulo 10^9 + 7.

Example : Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 */

public class NumberOfWaysToArriveAtDest {
    public static void main(String[] args) {
        int n = 7 ;
        int[][] roads = {{0,6,7},
                         {0,1,2},
                         {1,2,3},
                         {1,3,3},
                         {6,3,3},
                         {3,5,1},
                         {6,5,1},
                         {2,5,1},
                         {0,4,5},
                         {4,6,2}};
                
        System.out.println( countPaths(n, roads) );
        
    }

    // { https://www.geeksforgeeks.org/problems/number-of-ways-to-arrive-at-destination/1 }
    // This problem is slight different from other problems
    // {  https://www.youtube.com/watch?v=_-0mx0SmYxA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=40 }
    // My thought process initially was find the shortest dist to n 
    // then again repeat the process , but every time if u are at n , if dist is = shortest , then count
    // which is a wrong approach
    // suppose i have reached from , 2-> n and 3-> n with the shortest distance , count = 2 
    // But there are different ways to reach 2 as well while satisfying the shortest dist
    // could`nt understand watch the video
    // We use Dijkstra algorithm to find the Shortest Path from src = 0 to dst = n - 1.
    //  While dijkstra, we create additional ways array, where ways[i] keeps the number of shortest path from src = 0 to dst = i. Then the answer is ways[n-1].
    // https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/solutions/1417576/c-python-dijkstra-clean-concise/

    private static int countPaths(int n, int[][] roads) {
        // Approach 1st we will find the shortest path from 0 -> n-1
         // construct adj
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) adj.add(new ArrayList<>());
        int mod = (int)1e9 + 7 ;// 1000000000+7

        int E = roads.length; // no. of edges
        for(int i = 0 ; i < E ; i++){
            int a = roads[i][0];
            int b = roads[i][1];
            int wt = roads[i][2]; // edge a--wt-->b  and also  b--wt-->a
            adj.get(a).add(new int[]{b,wt});
            adj.get(b).add(new int[]{a,wt});
        }

        // Dijkstra initial config
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a,b)-> (int)(a[0]-b[0])); // priority based on wt`s
        // int dist[] = new int[n]; // 1 <= timei <= 10^9
        long dist[] = new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[0] = 0 ; 
        int ways[] = new int[n] ;
        ways[0] = 1 ; // src
        pq.offer(new long[]{0,0});

        while(!pq.isEmpty()){
            long curr[] = pq.poll();
            long currDist = curr[0];
            long currNode = curr[1];
            int currIndex = (int)currNode;

            if(currDist > dist[currIndex]) continue; // if the current distance is not the shortest for current node then calculating for other nodes with this distance is no use

            for(int [] adjNodes : adj.get(currIndex)){
                int adjNode = adjNodes[0];
                int adjDist = adjNodes[1];

                if(adjDist + currDist < dist[adjNode]){
                    dist[adjNode] = (long)adjDist + currDist;
                    ways[adjNode] = ways[currIndex]; // if it is for the first time then number of ways will be eqaul
                    pq.offer(new long[]{dist[adjNode],(long)adjNode});
                }
                else if(adjDist + currDist == dist[adjNode]){ // this path also gives the shortest distance to dest
                // then number of ways to dest will be equal to current ways to dest + number of ways to this(adjNode) currently which is giving the shortest path
                    ways[adjNode] = (ways[currIndex] + ways[adjNode])%mod; // mod is used to avoid integer overflow because of Leetcode testcases
                }
            }
        }
        return ways[n-1];
    }
    // Tc -> O(ElogV)
    
}
