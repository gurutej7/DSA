package DP.Subsequences;

import java.util.Arrays;

/* Unbounded Knapsack

You are given ‘n’ items with certain ‘profit’ and ‘weight’ and a knapsack with weight capacity ‘w’.
You need to fill the knapsack with the items in such a way that you get the maximum profit. You are allowed to take one item multiple times.

Example:
Input:                                  Output: 27
'n' = 3, 'w' = 10, 
'profit' = [5, 11, 13]
'weight' = [2, 4, 6]

Explanation:
We can fill the knapsack as:
1 item of weight 6 and 1 item of weight 4.
1 item of weight 6 and 2 items of weight 2.
2 items of weight 4 and 1 item of weight 2.
5 items of weight 2.

The maximum profit will be from case 3 = 11 + 11 + 5 = 27. Therefore maximum profit = 27.
  
*/

public class KnapsackUnbounded {
    public static void main(String[] args) {
        int weight[] = {2,4,6};
        int value[] = {5,11,13};
        int maxWeight = 10 ;
        int n = 3;

        System.out.println( knapsackUnboundedMemo(weight, value, n, maxWeight) );

        System.out.println( knapsackUnboundedTabu(weight, value, n, maxWeight) );
    }

    // The problem is same as 0/1 Knapsack
    // In 0/1 knapsack , when we pick one item we will move to the next item but here , we won`t move to the next item 
    private static int knapsackUnboundedMemo(int[] weight, int[] value, int n, int maxWeight) {
       
        int dp[][] = new int[n][maxWeight+1];

        for(int arr[] : dp) Arrays.fill(arr,-1);

        return fMemo(weight,value,n-1,maxWeight,dp);
    }

    private static int fMemo(int [] weight , int value[] , int ind , int maxWeight , int[][] dp){
        // Base case 
        if(ind == 0){ // If we are at 0th index , we will pick it if it fits in our bag
            int sum = 0 ; 
            if (weight[0] <= maxWeight) { // Little change here as long as bag weight is greater than weight[0] , we can pick it many times
                sum += (maxWeight/ weight[0]) * value[0];
            }
            return sum;
        }
        // Avoid recomputing
        if(dp[ind][maxWeight] != -1) return dp[ind][maxWeight];
        // Without picking the current weight move to the next index
        int notPick = fMemo(weight, value, ind-1, maxWeight,dp); // Moving to the next item without taking current weight
        int pick = 0 ;
        // We can only pick if it fits in our bag
        // If we pick then we have to reduce the available weight also

        /*  Index is not changed here that is the difference */
        if(weight[ind] <= maxWeight) pick = value[ind] + fMemo(weight, value, ind, maxWeight-weight[ind],dp);

        dp[ind][maxWeight] = Math.max(pick,notPick);

        return dp[ind][maxWeight];
    }

    private static int knapsackUnboundedTabu(int[] weight, int[] value, int n, int maxWeight) {
       
        int dp[][] = new int[n][maxWeight+1];

        if(maxWeight == 0) return 0;

        // Initialize base cases
        for (int currWeight = 0; currWeight <= maxWeight; currWeight++) {
            if (weight[0] <= currWeight) {
                dp[0][currWeight] = (currWeight / weight[0]) * value[0];
            }
        }

        // Write for loops for the changing parameters
        for(int ind = 1 ; ind < n ; ind++){
            for(int currWeight = 0 ; currWeight <= maxWeight ; currWeight++){
                // Copy the recurrence
                int notPick = dp[ind-1][currWeight];

                int pick = 0 ;
                if(weight[ind] <= currWeight) pick = value[ind] + dp[ind][currWeight-weight[ind]];

                dp[ind][currWeight] = Math.max(pick,notPick);
            }
        }

        return dp[n-1][maxWeight];
    
    }
    
}
