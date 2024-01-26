package DP.Stocks;

/* 121. Best time to buy and sell stock
{ https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/ } 

You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:      Input: prices = [7,1,5,3,6,4]           Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:      Input: prices = [7,6,4,3,1]             Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 */

public class BuyAndSell1 {

    public static void main(String[] args) {
        int prices[] = {7,1,5,3,6,4};

        System.out.println( maxProfit(prices) );
        
    }

    // If we want to increase our profit we obviously try to buy and minimum price and sell at maximum price
    // We can sell on any => If we are selling on i`th day , how can we get max profit => obviously if we have bought the stock for it`s minimum price from 0 - i-1 th day
    // My keeping track of the last minimum price of the stock , we will sell if it gives more profit than the previous profit
    private static  int maxProfit(int[] prices) {
        int lastMinPrice = prices[0];
        int maxProfit = 0 ;
        for (int i = 0; i < prices.length; i++) {
            lastMinPrice = Math.min(lastMinPrice , prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - lastMinPrice);
        }

        return maxProfit;

    }
    
}
