package Graphs.ShortestPathAlgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* 787. Cheapest Flights with in K stops
{ https://leetcode.com/problems/cheapest-flights-within-k-stops/description/ }

There are n cities connected by some number of flights. You are given an array flights where flights[i] = 
[fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k 
stops. If there is no such route, return -1. 

Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 */

public class CheapestFlightsWithInKstops {
    public static void main(String[] args) {
        int n = 4 , src = 0 , dst = 3 , k = 1 ;
        int flights[][] = {{0,1,100},
                           {1,2,100},
                           {2,0,100},
                           {1,3,600},
                           {2,3,200}};
        
        System.out.println( findCheapestPrice(n, flights, src, dst, k) );
        
    }

    private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int edges = flights.length;

        // construct Adjacency
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) adj.add(new ArrayList<>());

        for(int i = 0 ; i < edges ; i++ ){
            int a = flights[i][0];
            int b = flights[i][1];
            int price = flights[i][2]; // given edge is a -price--> b
            adj.get(a).add(new int[]{b,price});
        }

        // Treat prices as distance
        // Now the problem becomes given source and destination find the shortest path
        // we have Dijkstra but Dijkstra won`t work , after 2 wrong submissions I observed it (:
        // because we will put it into the pq if the curr values is less than the existing value
        // suppose a node X has a value 5 assigned to it , but if we take that path we will never be able to reach dest within K stops
        // another path which has a value 7 , when goes through node X it can reach the dest
        // But pq won`t allow, X will say I have 5 why would I take 7 which is greater for me
        // But we are not considered about X we need to rach dest
        // So a typical dijkstra with pq fails

        // we will use dijkstra with a normal q ,

        // Initial configuration of dijkstra
        int INF = (int)1e9;
        int prices[] = new int[n];
        Arrays.fill(prices,INF);
        prices[src] = 0 ;
        Queue<int[]> q = new LinkedList<>();
        // here we have K as a extra thing which we need to keep track
        // {a,b,c} -> a = price , b = stop , c = node
        // we will use BFS to travel level wise , i.e, at node , we will take all the adjacent nodes
        q.offer(new int[]{0,0,src});

        while(!q.isEmpty()){
            int curr[] = q.poll();
            int currPrice = curr[0];
            int currK = curr[1];
            int currNode = curr[2];

            if(currK > k) break; // next all the nodes are reachable only if we have more than K stops
            for(int [] adjNode : adj.get(currNode)){
                int node = adjNode[0];
                int nodePrice = adjNode[1];
                if(currPrice + nodePrice < prices[node]){
                    prices[node] = currPrice + nodePrice;
                    if(node == dst)
                    q.offer(new int[] {prices[node],currK,node}); // dest is not considered as a stop
                    else
                    q.offer(new int[] {prices[node],currK+1,node}); // if we are not dest , it is counted as stop
                }
            }
        }

        return prices[dst] == INF ? -1 : prices[dst];

    }
}
