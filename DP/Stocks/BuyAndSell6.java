package DP.Stocks;

/* 714. Best time to Buy and sell Stock with Transaction fee
{ https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/ } 

Example 1:      Input: prices = [1,3,2,8,4,9], fee = 2          Output: 8

Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 */

public class BuyAndSell6 {
    public static void main(String[] args) {
        int prices[] = {1,3,2,8,4,9};
        int fee = 2;

        System.out.println( maxProfit(prices, fee) );
        
    }

    // The problem is exactly same as BuyAndSell2 , with slight modification
    // When a transation is considered as complete , when we sell a stock
    // That`s it when we sell , subtract fee from the profit

    private static int maxProfit(int[] prices, int fee) {
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
                   int sellToday = prices[i]-fee + dp[i+1][1] ; /* change i+1  to => i+2 , when we sell */
                   int sellInFuture = 0 + dp[i+1][buy] ; 
       
                   profit = Math.max(sellToday,sellInFuture);
       
                }
       
                dp[i][buy] = profit;

           }
       }

       return dp[0][1];
   }
    
}
