package DP.TwoD;

import java.util.Arrays;

/*  931. Minimum Falling Path Sum
{ https://leetcode.com/problems/minimum-falling-path-sum/ }

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
A falling path starts at any element in the first row and chooses the element in the next row that is either 
directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, 
col - 1), (row + 1, col), or (row + 1, col + 1).

Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
 
 */

public class MinimumFallingPathSum {
    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};

        System.out.println( minFallingPathSumMemo(matrix) );

        System.out.println( minFallingPathSumTabu(matrix) );
    }

    private static int minFallingPathSumTabu(int[][] matrix) {
        int n = matrix.length ; // given rows == cols
        if(n == 1) return matrix[0][0];

        int dp[][] = new int [n][n];

        // Write down the base cases   // if(row == 0) return matrix[row][col] ;

        for(int j = 0 ; j < n ; j++) dp[0][j] = matrix[0][j];

        // What are the changing parameters
        for(int row = 1 ; row < n ; row++){
            for(int col = 0 ; col< n ; col++){ // col is dependent on row , for every row there will be 0 to n col`s possible
                // Copy the recurrence
                // diagonal left
                int left = (int)1e8;
                if(col > 0) left =  matrix[row][col] + dp[row-1][col-1];
                // diagonal right
                int right = (int)1e8;
                if(col < n-1) right = matrix[row][col] + dp[row-1][col+1];
                // Direct up
                int up = matrix[row][col] + dp[row-1][col];

                dp[row][col] = Math.min(left,Math.min(up,right));
            }
        }

        int mini = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++){
            mini = Math.min(dp[n-1][i] , mini);
        }
        return mini;
    }

     private static int minFallingPathSumMemo(int[][] matrix) {
        int n = matrix.length ; // given rows == cols
        if(n == 1) return matrix[0][0];

        int dp[][] = new int [n][n];

        for(int i[] : dp) Arrays.fill(i,(int)1e7);

        // It is  a variable starting point and variable ending point 
        // I will start from bottom
        // I can start from any index in the bottom row
        // I will try out all possibilities (starting from all points in the last row and minimum of them will be the answer)
        int mini = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++){
            mini = Math.min(mini,fMemo(n-1 , i , matrix,dp));

        }
        return mini;
    }

    private static int fMemo(int row , int col , int[][] matrix , int[][] dp){
        // Base cases
        if(col < 0 || row < 0 || col >matrix.length-1) return (int)1e8;// If it is a invalid path we are returning a max value because we are taking min of all paths

        if(row == 0) return matrix[row][col] ; // Reached the destination

        if(dp[row][col] != (int)1e7) return dp[row][col]; // Avoid recomputing

        // diagonal left
        int left = matrix[row][col] + fMemo(row-1,col-1,matrix,dp);
        // diagonal right
        int right = matrix[row][col] + fMemo(row-1,col+1,matrix,dp);
        // Direct up
        int up = matrix[row][col] + fMemo(row-1,col,matrix , dp);

        dp[row][col] = Math.min(left,Math.min(up,right));

        return dp[row][col];

    }
    
}
