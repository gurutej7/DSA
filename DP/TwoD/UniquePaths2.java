package DP.TwoD;

import java.util.Arrays;

/* 63. Unique Paths - 2
link { https://leetcode.com/problems/unique-paths-ii/description/ }
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0]
[0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either
 down or right at any point in time.
An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any
 square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
Example 1: 
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

public class UniquePaths2 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};

        // Some checks, according to the question
       // if (obstacleGrid[0][0] == -1 || obstacleGrid[m - 1][n - 1] == -1) return 0;
       // if (m == 1 || n == 1) return 1;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        // Initialize base cases
        dp[0][0] = (obstacleGrid[0][0] == 0) ? 1 : 0;

        // Fill the first row
        for (int col = 1; col < n; col++) {
            if (obstacleGrid[0][col] == 0) {
                dp[0][col] = dp[0][col - 1];
            }
            // else obstacleGrid[0][col] == 1 dp[0][col] = 0 // Not written because by default the values are zeros
        }

        // Fill the first column
        for (int row = 1; row < m; row++) {
            if (obstacleGrid[row][0] == 0) {
                dp[row][0] = dp[row - 1][0];
            }
        }

        // Build the dp table
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (obstacleGrid[row][col] == 0) {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                
                }
            }
        }
        // For which the memoization is called m-1,n-1
        System.out.println( dp[m-1][n-1] );

        System.out.println( uniquePathsWithObstacles(obstacleGrid) );
    
    }
       
// The problem is same as Unique Paths - 1 , with one extra condition
 private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // Some checks , according to question
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0 ;
        if(m == 1 || n == 1) return 1;

        int dp[][] = new int[m][n];

        for(int arr[] : dp) Arrays.fill(arr,-1);

        return fMemo(obstacleGrid,m-1,n-1,dp);
        
    }

    private static int fMemo(int [][] grid , int row , int col,int[][] dp){
        if(row < 0 || col < 0) return 0; // There cannot be a path
        if(grid[row][col] == 1) return 0; // obstacle
        if(row == 0 && col == 0) return 1; // It is a Path

        // Avoid recomputing
        if(dp[row][col] != -1) return dp[row][col];

        // Move up
        int up = fMemo(grid,row-1,col,dp);
        // Move Left
        int left = fMemo(grid,row,col-1,dp);

        // Store the ans for current state
        dp[row][col] = up+left;
        return dp[row][col];
    }
    
}
