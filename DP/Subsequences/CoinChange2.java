package DP.Subsequences;

import java.util.Arrays;

/*  518. Coin Change 2
{ https://leetcode.com/problems/coin-change-ii/description/ }

You are given an integer array coins representing coins of different denominations and an integer amount 
representing a total amount of money.
Return the number of combinations that make up that amount. If that amount of money cannot be made up by any
 combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:      Input: amount = 5, coins = [1,2,5]              Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:      Input: amount = 3, coins = [2]                  Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:      Input: amount = 10, coins = [10]                Output: 1
*/

public class CoinChange2 {
    public static void main(String[] args) {
        int coins[] = {1,2,5};
        int amount = 5 ;

        System.out.println( changeMemo(amount, coins) );

        System.out.println( changeTabu(amount, coins));
    }

    // The problem is exactly same as coin change 1 
    // Key word to observe => count ways => The base case will always have 1 and 0
    // Just change the base case in coin change 1 code

    private static int changeMemo(int amount, int[] coins) {
        int n = coins.length;

        // Some checks according to the problem
        if(amount == 0) return 1 ;
        if(n == 1) {
            if(amount % coins[0] != 0) return 0;
            else return 1;
        }

        int dp[][] = new int[n][amount+1];

        for(int row[] : dp) Arrays.fill(row,-1);
        
        int ret = fMemo(n-1,coins,amount,dp);

        return  ret == -1 ? 0 : ret;
    }

    private static int fMemo(int ind,int[] coins,int amount , int[][] dp ){
        // Base case
        if(amount == 0) return 1;
        if(ind == 0){ // If we are at the last index => our aim is to make the amount zero
            // for that we will take many number of times if it is a valid choice
            if(amount % coins[0] == 0){
                return 1;
            }
            // if the coin at 0th index does not help us to make the amount 0 then it is not a valid combination of coins
            // We will return some max value because we are taking minimum of different combinations
            else return 0;
        }

        if(dp[ind][amount] != -1) return dp[ind][amount]; // To avoid recomputing
        //We can only use the current coin if it is less than or equal to our amount
        // We won`t decrement the index because we can use same coin any number of times
        int pick = 0 ;
        if(coins[ind] <= amount) pick =  fMemo(ind,coins,amount-coins[ind] , dp);
        // We dont use the current coin and move to the next coin with same amount
        int notPick = fMemo(ind-1 , coins , amount , dp);

        dp[ind][amount] = pick + notPick;

        return dp[ind][amount];
    }

    private static int changeTabu(int amount, int[] coins) {
        int n = coins.length;

        // Some checks according to the problem
        if(amount == 0) return 1 ;
        if(n == 1) {
            if(amount % coins[0] != 0) return 0;
            else return 1;
        }

        int dp[][] = new int[n][amount+1];

        // Write down the base cases
        // At index = 0, initialize dp[0][currAmount] to either dp[0][currAmount - coins[0]] + 1 or Integer.MAX_VALUE if not possible
        for(int currAmount = 0 ; currAmount <= amount ; currAmount++) {
            if(currAmount % coins[0] == 0) {
                dp[0][currAmount] = 1;
            } 
        }

		for(int i = 0 ; i< n ; i++) dp[i][0] = 1;

        // Write for loops for the changing parameters
        for(int ind = 1 ; ind < n ; ind++) {
            for(int currAmount = 1 ; currAmount <= amount ; currAmount++) {
                // Copy the recurrence
                int notPick = dp[ind-1][currAmount];
                int pick = 0 ; // Initialize to maximum value

                if(coins[ind] <= currAmount) {
                    pick =  dp[ind][currAmount - coins[ind]];
                }

                dp[ind][currAmount] = pick + notPick;
            }
        }

        return dp[n-1][amount];	
    }
    
}
