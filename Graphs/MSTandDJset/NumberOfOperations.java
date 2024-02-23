package Graphs.MSTandDJset;

/* 1319. Number of operations to make network connected 
{ https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/ }

Example : Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
 */

public class NumberOfOperations {
    public static void main(String[] args) {
        int n = 6 ;
        int connections[][] = {{0,1},{0,2},{0,3},{1,2},{1,3}};

        System.out.println(makeConnected(n, connections));
    }

    private static int makeConnected(int n, int[][] connections) {
        int m = connections.length;

        if(m < n-1) return -1; // we cannot make a single component of n nodes with less than n-1 edges
        
        UnionFind uf = new UnionFind(n);

        for(int i = 0 ; i < m ; i++) uf.unionByRank(connections[i][0],connections[i][1]);

        int cnt = 0 ; // if we observe the examples clearly
        for(int i = 0 ; i< n ; i++){
            if(uf.parent[i] == i) cnt++;
        }

        return cnt-1;
    }
    /* Hints by Leetcode
    * As long as there are at least (n - 1) connections, there is definitely a way to connect all computers.
    * Use DFS to determine the number of isolated computer clusters.

    * For n components we need minimum of n-1 edges to make it a single graph

    * Note for me:
    1) DSU will ignore the  extra edge of same component .
    2) DSU is always yield in connected graph with minimum edges ( for visualization )
    3) If there is talk about component of graph, then think of DSU once
    */
    
}
