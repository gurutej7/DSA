package Graphs.ShortestPathAlgos;

import java.util.Arrays;
import java.util.PriorityQueue;

/* 1631. Path with Minimum Effort
{ https://leetcode.com/problems/path-with-minimum-effort/description/ }

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] 
represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, 
(rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum 
effort.
A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
Return the minimum effort required to travel from the top-left cell to the bottom-right cell. 

Example 1:  Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

 */

public class PathWithMinimumEffort {
    public static void main(String[] args) {
        int[][] heights = {{1,2,3},
                           {3,8,4},
                           {5,3,5}};
        
        System.out.println( minimumEffortPath(heights) );

    }

    // Problem description is bit confusing but it is a straight forward application of Dijkstra
    static int delRow[] = {1,-1,0,0};
    static int delCol[] = {0,0,-1,1};
    private static int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        // Initial configuration of dijkstra
        int INF = (int)1e7;
        int dist[][] = new int[n][m];

        for(int row[] : dist) Arrays.fill(row,INF);
        dist[0][0] = 0 ;
        PriorityQueue<int []> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        // int [] arr in pq , arr[0] has mini effort till i,j arr[1] = i , arr[2] = j
        pq.offer(new int[]{0,0,0}); // starting point

        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int currDist = curr[0];
            int currRow = curr[1];
            int currCol = curr[2];

            /* could not come up with this for the first time 
            if(currDist > dist[currRow][currCol]) continue; // we already had a better value of dist[currRow][currCol] so skip greater values
            if(currRow == n-1 && currCol == m-1) return currDist; // reached desination 
            // why above line works .. because pq gives us the min value , if destination has the min of all , then the remaining values will never help us to minimize the value of destination
             */
            

            for(int i = 0 ; i < 4 ; i++){
                int r = currRow + delRow[i];
                int c = currCol + delCol[i];

                if(r >= 0 && c >= 0 && r < n && c < m ){
                    int absDiff = Math.abs(heights[r][c] - heights[currRow][currCol]);
                    int adjDist = Math.max(absDiff,currDist);
                    if(adjDist < dist[r][c] ){
                        dist[r][c] = adjDist;
                        pq.offer(new int[]{adjDist,r,c});
                    }
                }
            }
        }

        return dist[n-1][m-1];
        // Tc -> O(ElogV)
    }

    // There are other approaches in the Discussion section 
    // { https://leetcode.com/problems/path-with-minimum-effort/solutions/ }
    // { https://leetcode.com/problems/path-with-minimum-effort/solutions/1036518/java-3-clean-codes-dijkstra-s-algo-union-find-binary-search/ }
    // { https://www.geeksforgeeks.org/problems/path-with-minimum-effort/1 }
}
