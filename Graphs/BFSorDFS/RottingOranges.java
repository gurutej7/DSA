package Graphs.BFSorDFS;

import java.util.LinkedList;
import java.util.Queue;

/* { https://leetcode.com/problems/rotting-oranges/description/ } 

Example 1 : Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
 */

public class RottingOranges {
    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}}; 
        System.out.println(orangesRotting(grid));
    }

    private static int orangesRotting(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();

        int n = grid.length , m = grid[0].length;
        int vis[][] = new int[n][m];
        int minTime = 0 ;
        // we can`t use dfs because we have to start at all rotten oranges at a time and move simultaneously
        // In dfs we will go in depth for one after completing it only then we go for next 
        // but here we have a third variable time , which changes level wise so bfs
        // all the rooten oranges initially at level/time = 0
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == 2) {
                    q.offer(new Pair(i,j,0));
                    vis[i][j] = 1;
                }
            }
        }

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int i = curr.u;
            int j = curr.v;
            int t = curr.time;
            if(t > minTime) minTime = t;
            // check up
            if(i-1 >=0 && grid[i-1][j] == 1 && vis[i-1][j] == 0){
                vis[i-1][j] = 1 ;
                q.offer(new Pair(i-1,j,t+1)); // time increased by 1
            }
            // check down
            if(i+1 < n && grid[i+1][j] == 1 && vis[i+1][j] == 0){
                vis[i+1][j] = 1 ;
                q.offer(new Pair(i+1,j,t+1));
            }
            // check left
            if(j-1 >=0 && grid[i][j-1] == 1 && vis[i][j-1] == 0){
                vis[i][j-1] = 1 ;
                q.offer(new Pair(i,j-1,t+1)); // time increased by 1
            }
            // check down
            if(j+1 < m && grid[i][j+1] == 1 && vis[i][j+1] == 0){
                vis[i][j+1] = 1 ;
                q.offer(new Pair(i,j+1,t+1));
            }
        }

        // if there is any of the fresh orange which is not visited then return -1
        for(int i = 0 ; i< n ;i++){
            for(int j = 0 ; j< m ; j++){
                if(grid[i][j] == 1 && vis[i][j] == 0) return -1;
            }
        }

        return minTime;
    }
    // SC -> O(N*M)
    // TC -> O(N*M)
}

class Pair{
    int u , v, time;
    public Pair(int u ,int v , int time){
        this.u = u ;
        this.v = v ;
        this.time = time;
    }
}
    