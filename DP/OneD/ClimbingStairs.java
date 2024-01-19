package DP.OneD;

import java.util.Arrays;

/* 70. Climbing Stairs
 link { https://leetcode.com/problems/climbing-stairs/description/ }

 You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:      Input: n = 3                    Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */

public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 3 ;
        System.out.println( climbStairsRecursive( n )); 
        int dp [] = new int[n+1];

        Arrays.fill(dp,-1);
        System.out.println( climbStairsMemo(n, dp) );

        System.out.println( climbStairsTabulation(n) );
        

    }
    // Key point to notice " Count ways "
    // In base case we should have 1 and 0
    // Try all possible ways
    private static int climbStairsRecursive(int n){
        // Base Case => We have climbed all the stairs => It is one of the way
        if(n == 0) return 1;

        // At each step we have two choices
        // Climb one step
        int oneStep = climbStairsRecursive(n-1); // The stairs will be reduced by one
        int twoSteps = 0;
        // We can only climb two only if there are 2 or more stairs
        if(n > 1) twoSteps = climbStairsRecursive(n-2); // The stairs are reduced by two

        // At each stage we are going one step and two steps so the total ways would be sum of these two

        return oneStep + twoSteps ;

    }

    // Use memoization to avoid recomputing
    private static int climbStairsMemo(int n , int[]dp){
        if(n == 0) return 1;

        if(dp[n] != -1) return dp[n]; // If a certain n is computed the just return 

        // Copy the recurrence
        int oneStep = climbStairsMemo(n-1,dp); // The stairs will be reduced by one
        int twoSteps = 0;
        // We can only climb two only if there are 2 or more stairs
        if(n > 1) twoSteps = climbStairsMemo(n-2,dp); // The stairs are reduced by two

        dp[n] = oneStep+twoSteps; // store the ans for current n

        return dp[n];
    }

    // Tabulation to avoid recursive stack space
    private static int climbStairsTabulation(int n){
        int dp[] = new int[n+1];
        // Write the base cases
        dp[0] = 1;
        dp[1] = 1; // For n = 1 also , steps is one
        // from Bottom to up
        for(int i = 2 ; i<= n ; i++){
            //copy the recurrence
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    // Space Optimized Solution
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;
         int prev = 1; 
         int curr = 1;
         
         for(int i = 2 ; i<=n ; i++ ){
             int temp = curr;
             curr = (prev+temp);
             prev = temp;
         }
         return curr;
     }
    
}
