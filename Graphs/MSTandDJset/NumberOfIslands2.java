package Graphs.MSTandDJset;

import java.util.ArrayList;
import java.util.List;

/* { https://www.geeksforgeeks.org/problems/number-of-islands/1 }
 * 
 * Input: n = 4     m = 5   k = 4       A = {{1,1},{0,1},{3,3},{3,4}}
   Output: 1 1 2 2
   You are given a n,m which means the row and column of the 2D matrix and an array of  size k denoting the number
    of operations. Matrix elements is 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0 
    which means there is no land in the matrix. The array has k operator(s) and each operator has two integer A[i]
    [0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. Return how many 
    island are there in the matrix after each operation.You need to return an array of size k.
Note : An island means group of 1s such that they share a common side.
 */

public class NumberOfIslands2 {
    public static void main(String[] args) {
        int n = 4 , m = 5 ;
        int queries[][] = {{1,1},{0,1},{3,3},{3,4}};

        System.out.println( numOfIslands(n, m, queries) );
    }
    
    private static List<Integer> numOfIslands(int n, int m, int[][] queries) {
        int len = queries.length;
        List<Integer> res = new ArrayList<>();
        int  cnt = 0 ;
        boolean vis[][] = new boolean[n][m];
        // formula to compute the node for every row and col , node = > row * m + col
        // refer { https://www.youtube.com/watch?v=Rn6B-Q4SNyA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=51  }
        UnionFind uf = new UnionFind(m*n);

        int delRow[] = {0,0,-1,1};
        int delCol[] = {1,-1,0,0};
        for(int i = 0 ; i < len ; i++){
            int currRow = queries[i][0];
            int currCol = queries[i][1];

            if(vis[currRow][currCol]) {
                res.add(cnt) ;
                continue;
            }

            vis[currRow][currCol] = true;
            cnt++;
            for(int j = 0 ; j < 4 ; j++){
                int r = delRow[j] + currRow;
                int c = delCol[j] + currCol;
                if(r >= 0 && c >= 0 && r < n && c < m && vis[r][c]){
                    int node = currRow * m + currCol;
                    int adjNode = r * m + c;
                    if(!uf.isConnected(node, adjNode)){
                        uf.unionByRank(node, adjNode); // if they are adjacent and does`nt belong to the same parent , then merge them and reduce count
                        cnt--;
                    }
                }
            }

             res.add(cnt) ;
        }

        return res;
    }
}
