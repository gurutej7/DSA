package Graphs.ShortestPathAlgos;

import java.util.LinkedList;
import java.util.Queue;

import Graphs.BFSorDFS.Pair;

/* 1091. Shortest Path in Binary Matrix
{ https://leetcode.com/problems/shortest-path-in-binary-matrix/description/ } 

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the 
bottom-right cell (i.e., (n - 1, n - 1)) such that:
All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and 
they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example : Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
 */

public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0},
                        {1,1,0},
                        {1,1,0}};
        
        System.out.println(shortestPathBinaryMatrix(grid));
        
    }

    static int delRow[] = {-1,1,0,0,1,1,-1,-1};
    static int delCol[] = {0,0,1,-1,-1,1,-1,1};
    private static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(grid[0][0] != 0 ) return -1; // it is not valid to start from non-zero

        boolean vis[][] = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        int minDist = Integer.MAX_VALUE;
        q.offer(new Pair(0,0,1)); // i,j,dist
        vis[0][0] = true;

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int currRow = curr.getFirst();
            int currCol = curr.getSecond();
            int distance = curr.getThird();
            if(currRow == n-1 && currCol == m-1){ // we have reached our destination in one of the path
                if(distance < minDist) minDist = distance;
            }

            for(int i = 0 ; i < 8 ; i++){
                int r = currRow + delRow[i];
                int c = currCol + delCol[i];

                if(r >=0 && c >=0 && r < n && c < m && !vis[r][c] && grid[r][c] == 0){
                    vis[r][c] = true;
                    q.offer(new Pair(r,c,distance+1));
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
        
        // Typical dijkstra with pq is not necessary because of the unit distance , from one point to the other
        // we can use dijkstra with a normal q , and keep a 2D distance array and keep on updating dist[i][j] whenever needed
        // return dist[destination]
        // other version of the problem -> { https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1 }
    }
    
}
