package DP.Stocks;

import java.util.Arrays;

/*  123. Best time to buy and sell stock - 3
{ https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/ }

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again). 

Example 1:      Input: prices = [3,3,5,0,0,3,1,4]           Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
  
  
*/
public class BuyAndSell3 {
    public static void main(String[] args) {
        int prices[] = {3,3,5,0,0,3,1,4};

        System.out.println( maxProfitMemo(prices) );
        System.out.println( maxProfitTabu(prices) );
    }

    /* We can      B S B S  => B = buy S = sell
     * trans =>    0 1 2 3  => At max four transactions are allowed which can be done any where
     * By observing if trans => even => buy  ,  else sell
     * we will try out all possible ways with the given constraints and keeping track of the transaction count
     */
    private static int maxProfitMemo(int [] prices){
        // Some checks according to question
        int n = prices.length ;
        if(n == 1) return 0 ; // With in only one day we can`t do anything
        
        int dp[][] = new int[n][4];

        for(int [] row : dp) Arrays.fill(row,-1);

        return fMemo(prices,0,0,dp);

    }
    private static int fMemo(int[] prices , int day , int trans , int[][] dp){
        // Base cases 
        if(day == prices.length) return 0;
        if(trans == 4) return 0 ; // We have completed our transactions , so no further allowed

        if(dp[day][trans] != -1) return dp[day][trans];

        int profit = 0 ;
        // We can buy
        if(trans % 2 == 0){
            int buyToday = -prices[day] + fMemo(prices, day+1, trans+1, dp) ; // Go to next day with new trans count
            int buyInFuture = 0 + fMemo(prices, day+1, trans, dp); // go to next day with same trans count
            profit = Math.max(buyToday,buyInFuture);

        }
        else{ // we can sell
            int sellToday = prices[day] + fMemo(prices, day+1, trans+1, dp) ;
            int sellInFuture = 0 + fMemo(prices, day+1 , trans, dp) ;
            profit = Math.max(sellToday,sellInFuture);

        }

        dp[day][trans] = profit;

        return dp[day][trans];
    }

    private static int maxProfitTabu(int prices[]){
         // Some checks according to question
         int n = prices.length ;
         if(n == 1) return 0 ; // With in only one day we can`t do anything
         
         int dp[][] = new int[n+1][5];

         // Copy the base cases
         for(int j = 0 ; j < 4 ; j++) dp[n][j] = 0;

         for(int i = 0 ; i<= n ; i++) dp[i][4] = 0 ;

         // Write for loops for changing parameters in the opposite fashion
         for(int day = n-1 ; day >= 0 ; day--){
            for(int trans = 3 ; trans >=0 ; trans--){
                //copy the recurrence
                int profit = 0 ;
                // We can buy
                if(trans % 2 == 0){
                    int buyToday = -prices[day] + dp[day+1][trans+1] ; // Go to next day with new trans count
                    int buyInFuture = 0 + dp[day+1][trans]; // go to next day with same trans count
                    profit = Math.max(buyToday,buyInFuture);

                }
                else{ // we can sell
                    int sellToday = prices[day] + dp[day+1][trans+1] ;
                    int sellInFuture = 0 + dp[day+1][trans] ;
                    profit = Math.max(sellToday,sellInFuture);

                }

                 dp[day][trans] = profit;

            }
         }

         return dp[0][0];
    }
    //We can use a 3 D array also to individually keep track of trans, buy OR sell , day
    // Update trans when we do sell => B S B S => one sell means one trans 
    // dp[n][2][2] => dp[day][buyORsell][trans]
    
}
