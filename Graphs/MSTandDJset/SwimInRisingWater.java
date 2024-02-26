package Graphs.MSTandDJset;

import java.util.Arrays;
import java.util.PriorityQueue;

/* 778. Swim in Rising Water
{ https://leetcode.com/problems/swim-in-rising-water/description/ } 

You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square 
if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay 
within the boundaries of the grid during your swim.
Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

Example :   Input: grid = [[0,2],[1,3]]             Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
 */

public class SwimInRisingWater {
    static int delRow[] = {1,-1,0,0};
    static int delCol[] = {0,0,-1,1};
    public static void main(String[] args) {
        int[][] grid = {{0,2},{1,3}};

        System.out.println( swimInWater(grid) );
        
    }

    // Approach -1 
    // if there exist`s a path between (0,0) and (n-1,n-1) means they are connected
    // Given constraints , grid[i][j] = 0 to n*n 
    // At worst case to reach grid[n-1][n-1] , we will need n*n time
    // Now let us assume each cell as a node (value can be given using row*n + col)
    // Now we have to find in how much time we can make 0 and n*n-1 as a connected component
    // Approach is => we start from 0 , with time = 0 , and we will check the adjacent nodes (in 4 directions) if it is a valid cell and it`s value is less than time then , we will connect that node
    // Increment time , checking if node is less than time because , if node is greater than time then we can`t reach there with less time according to the problem
    // So every time we connect a node and choose the direction which can make us move forward in less time, increment time
    /*
     * Now here we can see the connectivity, Union and find intution, all the elements got connected at last,see if we could be connecting all the elements in increasing time,and at a particular time we see that the first and the last element also got connected then since we are increasing the time linearly
     we must have got the min time.
     */
    // { https://leetcode.com/problems/swim-in-rising-water/solutions/3385647/best-explaination-union-and-find-swin-in-rising-water/ }

    private static int swimInWater(int [][] grid){
        int n = grid.length;
        // make a copy of the grid with node values
        int copy[] = new int[n*n];
        UnionFind uf = new UnionFind(n*n);

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                copy[grid[i][j]] = i*n+j;
            }
        }

        // traverse through time linearly
        for(int time = 0 ; time < n*n ; time++){
            int idx = copy[time];
            int row = idx/n;
            int col = idx%n;

            for(int k = 0 ; k < 4 ; k++){
                int r = row+delRow[k];
                int c = col+delCol[k];
                if(r >= 0 && c >=0 && r < n && c < n && grid[r][c] < time) 
                    uf.unionByRank(idx, r*n+c);
            }

            if(uf.isConnected(0, n*n-1)) return time;
        }
        return 0;
    }
    

    // Approach - 2 , using dijkstra

    // same as [Path with minimum effort] { https://leetcode.com/problems/path-with-minimum-effort/description/ }

   
    public int swimInWater2(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        // Initial configuration of dijkstra
        int INF = (int)1e7;
        int dist[][] = new int[n][m];

        for(int row[] : dist) Arrays.fill(row,INF);
        dist[0][0] = heights[0][0] ;
        PriorityQueue<int []> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        // int [] arr in pq , arr[0] has mini effort till i,j arr[1] = i , arr[2] = j
        pq.offer(new int[]{heights[0][0],0,0}); // starting point

        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int currDist = curr[0];
            int currRow = curr[1];
            int currCol = curr[2];

            // could not come up with this for the first time 
            if(currDist > dist[currRow][currCol]) continue; // we already had a better value of dist[currRow][currCol] so skip greater values
            if(currRow == n-1 && currCol == m-1) return currDist; // reached desination
            
            

            for(int i = 0 ; i < 4 ; i++){
                int r = currRow + delRow[i];
                int c = currCol + delCol[i];

                if(r >= 0 && c >= 0 && r < n && c < m ){
                    int adjDist = Math.max(heights[r][c],currDist);
                    if(adjDist < dist[r][c] ){
                        dist[r][c] = adjDist;
                        pq.offer(new int[]{adjDist,r,c});
                    }
                }
            }
        }

        return dist[n-1][m-1];
    }
    
}
