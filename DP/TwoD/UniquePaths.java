package DP.TwoD;

import java.util.Arrays;

/*  62. Unique Paths
link { https://leetcode.com/problems/unique-paths/description/ }
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The 
robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the
 bottom-right corner.

Example 1:                  Input: m = 3, n = 7                 Output: 28

Example 2:                  Input: m = 3, n = 2                 Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 
*/

public class UniquePaths {
    public static void main(String[] args) {
        int m = 3 , n = 7;
          // Tabulation
        int dp[][] = new int[m][n];
        // Write down the base case
        //Fill first row
        //From (0,0) to reach any row with column 0 (ie. first row) 
        //there is only 1 way ie. Down
        for(int i = 0 ; i< m ; i++) dp[i][0] = 1;
        //Fill first column
        //From (0,0) to reach any column with row 0 (ie. first column) 
        //there is only 1 way ie. Right
        for(int j = 0 ; j < n ; j++) dp[0][j] = 1;

        for(int row = 1 ; row < m ; row++){
            for(int col = 1 ; col < n ; col++){
                  // Copy the recurrence
                int up = dp[row-1][col];
                int left = dp[row][col-1];
                dp[row][col] = up+left ;
            }
        }
         

        // For which the memoization is called m-1,n-1

        System.out.println( dp[m-1][n-1] );

        System.out.println( uniquePaths(m, n) );
    }

    public static int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for(int arr[] : dp) Arrays.fill(arr,-1);


        return fMemo(m-1,n-1,dp);


        // return f(m-1,n-1); recursive 
    }
    // Recursive approach
    // Try out all ways
    public static int f(int row , int col ){
        // Count ways will always have 0 and 1 in the base case
        if(row < 0 || col < 0) return 0 ; // Not a possible way so return 0
        if(row == 0 && col == 0) return 1; // Reached our destination => one of the way
        // Choose to move up
        int up = f(row-1,col);
        // Choose to move left;
        int left = f(row,col-1);

        return up+left;
    }

    public static int fMemo(int row , int col ,int[][] dp){
        // Count ways will always have 0 and 1 in the base case
        
        if(row < 0 || col < 0) return 0 ;
        if(row == 0 && col == 0) return 1;

        if(dp[row][col] != -1) return dp[row][col]; // Avoid recomputing for the same state
        // Choose to move up
        int up = fMemo(row-1,col,dp);
        // Choose to move left;
        int left = fMemo(row,col-1,dp);

        dp[row][col] = up+left;
        return dp[row][col];
    }
}
