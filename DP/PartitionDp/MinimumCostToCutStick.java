package DP.PartitionDp;

import java.util.Arrays;

/* 1547. Minimum cost to cut a Stick 
{ https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/ } 

Example :       Input: n = 9, cuts = [5,6,1,4,2]            Output: 22
Explanation: If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.
 */

public class MinimumCostToCutStick {
    public static void main(String[] args) {
        int cuts[] = {5,6,1,4,2};
        int length = 9 ;

        System.out.println( minCostMemo(length, cuts) );

        System.out.println( minCostTabu(length, cuts) );
    }

    private static int minCostMemo(int length, int[] cuts) {
        int n = cuts.length;
        // To make subProblems independent on each other
        Arrays.sort(cuts);
        // TO append 0 and n 
        int newCuts[] = new int [n+2];
        newCuts[0] = 0 ;
        for(int i = 0 ; i < n ; i++) newCuts[i+1] = cuts[i];

        newCuts[n+1] = length ;

        // for memoization
        int dp[][] = new int [n+2][n+2] ;

        for(int [] row : dp) Arrays.fill(row,-1);

        return fMemo(newCuts , 1 , n , dp);
    }

    private static int fMemo(int[] cuts , int i , int j , int[][] dp){
        // base case
        // i > j is not a valid partition
        if( i > j) return 0 ;

        if(dp[i][j] != -1) return dp[i][j];

        int mini = (int)1e9;

        // we can start from any index 
        for(int ind = i ; ind <= j ; ind++){
            // This idea comes by priorly solving similar problems
            int currCost = cuts[j+1] - cuts[i-1] + fMemo(cuts,i,ind-1,dp) + fMemo(cuts,ind+1,j,dp);
            mini = Math.min(currCost , mini);

        }

        dp[i][j] = mini ; 

        return dp[i][j];
    }
    
    // Tabulation
    private static int minCostTabu(int length, int[] cuts) {
        int n = cuts.length;
        // To make subProblems independent on each other
        Arrays.sort(cuts);
        // TO append 0 and n 
        int newCuts[] = new int [n+2];
        newCuts[0] = 0 ;
        for(int i = 0 ; i < n ; i++) newCuts[i+1] = cuts[i];

        newCuts[n+1] = length ;

        // for memoization
        int dp[][] = new int [n+2][n+2] ;

        // write down the base cases 
        // any way by default values are 0`s => so no need to write

        // write down the for loops for changing parameters in the opposite manner
        for(int i = n ; i >= 1 ; i--){
            for(int j = 1 ; j < n+1 ; j++){
                // copy the recurrence
                if(i > j) continue;
                int mini = (int)1e9;
                for(int ind = i ; ind <= j ; ind++){
                    int cost = newCuts[j+1] - newCuts[i-1] + dp[i][ind-1] + dp[ind+1][j];
                    mini = Math.min(mini,cost);

                }

                dp[i][j] = mini;

            }
        }

        return dp[1][n];
    }
}
