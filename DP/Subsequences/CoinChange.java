package DP.Subsequences;

import java.util.Arrays;

/*   322. Coin Change 
{ https://leetcode.com/problems/coin-change/ }

You are given an integer array coins representing coins of different denominations and an integer amount 
representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up 
by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:      Input: coins = [1,2,5], amount = 11             Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:      Input: coins = [2], amount = 3                  Output: -1
*/

public class CoinChange {
    public static void main(String[] args) {
        int coins[] = {1,2,5};
        int amount = 11 ;

        System.out.println( coinChangeMemo(coins, amount) );

        System.out.println( coinChangeTabu(coins, amount) );
    }

    private static int coinChangeMemo(int[] coins, int amount) {
        int n = coins.length;

        // Some checks according to the problem
        if(amount == 0) return 0 ;
        if(n == 1) {
            if(amount % coins[0] != 0) return -1;
            else return amount/coins[0];
        }

        int dp[][] = new int[n][amount+1];

        for(int row[] : dp) Arrays.fill(row,-1);
        
        int ret = fMemo(n-1,coins,amount,dp);

        return  ret >= 1e9 ? -1 : ret;
    }

    private static int fMemo(int ind,int[] coins,int amount , int[][] dp ){
        // Base case
        if(ind == 0){ // If we are at the last index => our aim is to make the amount zero
            // for that we will take many number of times if it is a valid choice
            if(amount % coins[0] == 0){
                return amount/coins[0];
            }
            // if the coin at 0th index does not help us to make the amount 0 then it is not a valid combination of coins
            // We will return some max value because we are taking minimum of different combinations
            else return (int)1e9;
        }

        if(dp[ind][amount] != -1) return dp[ind][amount]; // To avoid recomputing
        //We can only use the current coin if it is less than or equal to our amount
        // We won`t decrement the index because we can use same coin any number of times
        int pick = (int)1e9 ;
        if(coins[ind] <= amount) pick = 1 + fMemo(ind,coins,amount-coins[ind] , dp);
        // We dont use the current coin and move to the next coin with same amount
        int notPick = fMemo(ind-1 , coins , amount , dp);

        dp[ind][amount] = Math.min(pick,notPick);

        return dp[ind][amount];
    }

    private static int coinChangeTabu(int[] coins, int amount) {
        int n = coins.length;

        // Some checks according to the problem
        if(amount == 0) return 0 ;
        if(n == 1) {
            if(amount % coins[0] != 0) return -1;
            else return amount/coins[0];
        }

        int dp[][] = new int[n][amount+1];

        // Write down the base cases
        // At index = 0, initialize dp[0][currAmount] to either dp[0][currAmount - coins[0]] + 1 or Integer.MAX_VALUE if not possible
        for(int currAmount = 0 ; currAmount <= amount ; currAmount++) {
            if(currAmount % coins[0] == 0) {
                dp[0][currAmount] = currAmount / coins[0];
            } else {
                dp[0][currAmount] = Integer.MAX_VALUE;
            }
        }

        // Write for loops for the changing parameters
        for(int ind = 1 ; ind < n ; ind++) {
            for(int currAmount = 0 ; currAmount <= amount ; currAmount++) {
                // Copy the recurrence
                int notPick = dp[ind-1][currAmount];
                int pick = Integer.MAX_VALUE; // Initialize to maximum value

                if(coins[ind] <= currAmount && dp[ind][currAmount - coins[ind]] != Integer.MAX_VALUE) {
                    pick = 1 + dp[ind][currAmount - coins[ind]];
                }

                dp[ind][currAmount] = Math.min(pick, notPick);
            }
        }

        return dp[n-1][amount] == Integer.MAX_VALUE ? -1 : dp[n-1][amount];
    }
    
}
