package Graphs.MSTandDJset;

/* 547. Number of provinces
{ https://leetcode.com/problems/number-of-provinces/description/ } 

Example 1:  Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]  Output: 2
 */

public class NumberOfProvince {
    public static void main(String[] args) {
        int isConnected[][] = {{1,1,0},
                               {1,1,0},
                               {0,0,1}};


        System.out.println( findCircleNum(isConnected) );
        
    }

    private static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);  // also can be called as Disjoint set

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(isConnected[i][j] == 1) uf.unionByRank(i,j);
            }

        } // we can also count the number of unique ultimate parents to get our ans
        // in the parent array for which the parent is themselves parent[i] = i => ultimate parent

        int cnt = 0 ;
        for(int i = 0 ; i < n ; i++){
            if(uf.parent[i] == i) cnt++;

        }

        return cnt;

        // return uf.countComponents();
    }
    
}
