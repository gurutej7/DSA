package DP.Stocks;

/*  188. Best time to buy and Sell Stock - 4
{ https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/ }

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
  
*/

public class BuyAndSell4 {
    public static void main(String[] args) {
        int prices[] = {3,2,6,5,0,3};
        int k = 2;

        System.out.println( maxProfitTabu(prices, k) );
        System.out.println( maxProfitTabu1(k, prices) );
        
    }

    // Same as previous question instead of 2 transactions , here it is k transactions
    private static int maxProfitTabu1(int k, int[] prices) {
        int n = prices.length;
        if( n == 1) return 0;
        int dp[][][] = new int[n+1][2][k+1];
        // Write down base cases day == n => return 0
        for(int trans = 0 ; trans <= k ; trans++){
            dp[n][0][trans] = 0 ; // sell
            dp[n][1][trans] = 0 ; // buy
        }

        // for any day if trans == k return 0
        for(int day = 0 ; day <= n ; day++){
            dp[day][0][k] = 0;
            dp[day][1][k] = 0;
        }

        // Write for loops for the changing parameters
        for(int day = n-1 ; day >= 0 ; day--){
            for(int buy = 0 ; buy <= 1 ; buy++){
                for(int trans = 0 ; trans < k ; trans++){
                    //Copy the recurrence
                    int profit = 0;
                    if(buy == 1){ // If we are allowed to buy
                        int buyToday = -prices[day] + dp[day+1][0][trans];
                        int buyInFuture = 0 + dp[day+1][buy][trans];
                        profit = Math.max(buyToday,buyInFuture);
                    }
                    else{ // We should sell
                        int sellToday = prices[day] + dp[day+1][1][trans+1];
                        int sellInFuture = 0 + dp[day+1][0][trans];
                        profit = Math.max(sellToday,sellInFuture);

                    }

                    dp[day][buy][trans] = profit;
                }
            }
        }
        return dp[0][1][0];
    }

    // Little modification in the same code in the previous problem
    private static int maxProfitTabu(int prices[],int k){
        // Some checks according to question
        int n = prices.length ;
        if(n == 1) return 0 ; // With in only one day we can`t do anything
        
        int dp[][] = new int[n+1][2*k+1];

        // Copy the base cases
        for(int j = 0 ; j < 2*k ; j++) dp[n][j] = 0;

        for(int i = 0 ; i<= n ; i++) dp[i][2*k] = 0 ;

        // Write for loops for changing parameters in the opposite fashion
        for(int day = n-1 ; day >= 0 ; day--){
           for(int trans = 2*k-1 ; trans >=0 ; trans--){
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
    
}
