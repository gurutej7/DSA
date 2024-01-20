package DP.TwoD;

import java.util.Arrays;
/* 64. Minimum Path Sum 

link { https://leetcode.com/problems/minimum-path-sum/description/ }
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the 
sum of all numbers along its path.

Example 1:      Input: grid = [[1,3,1],[1,5,1],[4,2,1]]          Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum. 
Note: You can only move either down or right at any point in time. 
*/

public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int m = grid.length; // No. of rows
        int n = grid[0].length; // No. of columns

        // Tabulation
        // Write down the base cases
        int dp [][] = new int[m][n];

        dp[0][0] = grid[0][0];
        // Fill the first Row  => consider if there is only one row in the grid (for thinking)
        for(int j = 1 ; j < n ; j++) dp[0][j] = grid[0][j] + dp[0][j-1];

        // Fill the first column
        for(int i = 1 ; i< m ; i++) dp[i][0]  =  grid[i][0] + dp[i-1][0] ;

        for(int row = 1 ; row < m ; row++){
            for(int col = 1 ; col < n ; col ++){
                // Copy the reccurrence
                dp[row][col] = grid[row][col]+ Math.min(dp[row-1][col] , dp[row][col-1]); // Math.min(up,left)
                // dp[row][col] represents minimum path sum till grid[row][col]
            }
        }

        System.out.println( dp[m-1][n-1] );

        System.out.println( minPathSum(grid) );
        
    }

    private static int minPathSum(int[][] grid) {
        int m = grid.length; // No. of rows
        int n = grid[0].length; // No. of columns
        int[][] dp = new int[m][n];

        for(int arr[] : dp) Arrays.fill(arr,-1);

        return fMemo(m-1,n-1,grid,dp);
    }

    private static int fMemo(int row,int col , int[][] grid , int[][] dp){
        // Base cases 
        if(row < 0 || col < 0) return (int)1e7; // If it is a wrong path return a maximum value because we are considering min of all the paths
        if(row == 0 && col == 0) return grid[0][0] ; // Reached our destination

        if(dp[row][col] != -1) return dp[row][col] ; // Avoid recomputing

        // Move Up ; current value + function(remaining Path)
        int up = grid[row][col] + fMemo(row-1,col,grid,dp);
        // Move left
        int left = grid[row][col] + fMemo(row,col-1,grid,dp);
        // Consider max of both the paths
        dp[row][col] = Math.min(up,left);

        return dp[row][col];
    }
}
