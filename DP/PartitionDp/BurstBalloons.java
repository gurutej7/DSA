package DP.PartitionDp;

import java.util.Arrays;

/* 312. Burst Balloons
{ https://leetcode.com/problems/burst-balloons/description/ }

Example 1:      Input: nums = [3,1,5,8]         Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 */

public class BurstBalloons {
    public static void main(String[] args) {
        int nums[] = {3,1,5,8};

        System.out.println( maxCoinsMemo(nums) );

        System.out.println( maxCoinsTabu(nums) );
    }

    private static int maxCoinsMemo(int[] nums) {
        int n = nums.length ; 

        // TO simplify the process add 1 at end and start
        int newNums[] = new int[n+2];

        newNums[0] = 1 ;
        newNums[n+1] = 1 ;

        for(int i = 0 ; i < n ; i++) newNums[i+1] = nums[i];

        // dp array to memoize

        int dp[][] = new int[n+2][n+2] ; 

        for(int row[] : dp) Arrays.fill(row , -1);

        return fMemo(newNums , 1 , n , dp);
    }

    private static int fMemo(int [] nums , int i , int j , int[][] dp){
        // base case
        if( i > j) return 0 ; // it is not a valid partititon

        // Avoid re-computing same subProblems

        if(dp[i][j] != -1) return dp[i][j];

        int maxi = (int)-1e9 ;

        // The last guy for the current sub problem can anyone from the range i - j
        for(int ind = i ; ind <= j ; ind++){
            int coins = nums[i-1]*nums[ind]*nums[j+1] + fMemo(nums,i,ind-1,dp) + fMemo(nums,ind+1,j,dp);
            maxi = Math.max(maxi,coins);
        }

        dp[i][j] = maxi;

        return dp[i][j];
    }

    // Tabulation
    private static int maxCoinsTabu(int[] nums) {
        int n = nums.length ; 

        // TO simplify the process add 1 at end and start
        int newNums[] = new int[n+2];

        newNums[0] = 1 ;
        newNums[n+1] = 1 ;

        for(int i = 0 ; i < n ; i++) newNums[i+1] = nums[i];

        // dp array to memoize

        int dp[][] = new int[n+2][n+2] ; 

        // Write down the base cases => by default values are 0`s 

        // Write for loops for the changing parameters in the opposite manner
        for(int i = n ; i >= 1 ; i--){
            for(int j = 1 ; j < n+1 ; j++){
                if( i > j) continue;
                // copy the recurrence
                int maxi = Integer.MIN_VALUE;
                for(int ind = i ; ind <= j ; ind++){
                    int coins = newNums[i-1]*newNums[ind]*newNums[j+1] + dp[i][ind-1] + dp[ind+1][j];

                    maxi = Math.max(coins,maxi);
                }

                dp[i][j] = maxi;
            }
        }

        return dp[1][n];
    }
    
}
