package DP.Stocks;

import java.util.Arrays;

/* 122. Best time to Buy and Sell Stock 2
{ https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/ }

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

Example 1:      Input: prices = [7,1,5,3,6,4]               Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

Example 2:      Input: prices = [1,2,3,4,5]                 Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
 
*/

public class BuyAndSell2 {
    public static void main(String[] args) {

        int prices[] = {1,2,3,4,5};

        System.out.println( maxProfit(prices) );
        System.out.println( maxProfitMemo(prices) );
        System.out.println( maxProfitTabu(prices) );
        
    }

    // Greedy Approach
    //We want to buy all the stocks when the line is going up. And want to ignore all the lines when the line is going down.
    // Could not come up initially , need to improve more observation haki (The above line is from a leetcode discussion)

    private static int maxProfit(int[] prices) {
        int maxProfit = 0 ;

        for(int i = 1 ; i < prices.length ; i++){
              if(prices[i] > prices[i-1]) {
                maxProfit += (prices[i] - prices[i-1]);
            }
        }

        return maxProfit;
    }

    // Dynamic programming
    private static int maxProfitMemo(int[] prices){
        int n = prices.length;
        int dp[][] = new int[n][2];

        for(int row[] : dp) Arrays.fill(row,-1);

        return fMemo(prices,0,1,dp);
    }

    // We can either buy or sell
    // We cannot buy like buy => and again  buy  => same for sell also sell, => sell is not allowed
    // If we buy then we have to sell it to buy again
    // So if we want to buy today then we have to know what we have done previously
    // So keep a variable as buy => 1 = > we can buy => 0 => we can sell
    // We will try out all possible ways and take max among them

    private static int fMemo(int[] prices , int i , int buy , int [][] dp){
        // Base case
        if( i == prices.length) return 0 ;
         // If all the days to buy or sell the stock is over then we can`t do anything (either buy nor sell) ideally return 0

         if(dp[i][buy] != -1) return dp[i][buy];

         int profit = 0 ;
         if(buy == 1){ // We are allowed to buy today
            int buyToday = -prices[i] + fMemo(prices, i+1 , 0, dp); // I buy today
            int buyInFuture = 0 + fMemo(prices, i+1, buy, dp) ; // I will not buy today but I will buy in the future
            profit = Math.max(buyToday,buyInFuture);
         }
         else{ // We are allowed to sell
            int sellToday = prices[i] + fMemo(prices, i+1, 1, dp) ; //I will sell today
            int sellInFuture = 0 + fMemo(prices, i+1, buy, dp) ; // I won`t sell today

            profit = Math.max(sellToday,sellInFuture);

         }

         dp[i][buy] = profit;

         return dp[i][buy];

    }
    
    private static int maxProfitTabu(int prices[]){
        int n = prices.length;

        int dp[][] = new int[n+1][2];
        // Write down the base cases
        dp[n][0] = dp[n][1] = 0 ; // don`t need to write this , by default values are zeros any way
        // Write for loops for the changing parameters
        for(int i = n-1 ; i >= 0 ; i--){
            for(int buy = 0 ; buy <=1 ; buy++){
                // Copy the recurrence
                int profit = 0 ;
                if(buy == 1){ // We are allowed to buy today
                    int buyToday = -prices[i] + dp[i+1][0]; // I buy today
                    int buyInFuture = 0 + dp[i+1][buy] ; // I will not buy today but I will buy in the future
                    profit = Math.max(buyToday,buyInFuture);
                 }
                 else{ // We are allowed to sell
                    int sellToday = prices[i] + dp[i+1][1] ; //I will sell today
                    int sellInFuture = 0 + dp[i+1][buy] ; // I won`t sell today
        
                    profit = Math.max(sellToday,sellInFuture);
        
                 }
        
                 dp[i][buy] = profit;

            }
        }

        return dp[0][1];

    }
}
