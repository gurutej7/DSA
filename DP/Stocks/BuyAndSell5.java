package DP.Stocks;

/* 309. Best time to buy and sell stock with cool down
{ https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/ }

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell 
one share of the stock multiple times) with the following restrictions:
After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:          Input: prices = [1,2,3,0,2]             Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

 */
public class BuyAndSell5 {
    public static void main(String[] args) {
        int prices[] = {1,2,3,0,2};

        System.out.println( maxProfitTabu(prices) );
    }

    // This problem is exactly same as BuyAndSell2 , a small change is when you sell => you go to day after day with buy (in old version you go to the immediate next day)
    // Copy paste BuyAndSell2
    private static int maxProfitTabu(int prices[]){
        int n = prices.length;

        int dp[][] = new int[n+2][2];  // when we do n-1 + 2 => n+1 , so increse array size by 1
        // Write down the base cases
        dp[n][0] = dp[n][1] = 0 ; // don`t need to write this , by default values are zeros any way
        // Write for loops for the changing parameters
        for(int i = n-1 ; i >= 0 ; i--){
            for(int buy = 0 ; buy <=1 ; buy++){
                // Copy the recurrence
                int profit = 0 ;
                if(buy == 1){ // We are allowed to buy today
                    int buyToday = -prices[i] + dp[i+1][0]; 
                    int buyInFuture = 0 + dp[i+1][buy] ; 
                    profit = Math.max(buyToday,buyInFuture);
                 }
                 else{ // We are allowed to sell
                    int sellToday = prices[i] + dp[i+2][1] ; /* change i+1  to => i+2 , when we sell */
                    int sellInFuture = 0 + dp[i+1][buy] ; 
        
                    profit = Math.max(sellToday,sellInFuture);
        
                 }
        
                 dp[i][buy] = profit;

            }
        }

        return dp[0][1];

    }
    
}
