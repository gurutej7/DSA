package DP.Subsequences;

import java.util.Arrays;

/*  0/1 Knapsack
 
Problem statement
A thief is robbing a store and can carry a maximal weight of W into his knapsack. There are N items and the ith 
item weighs wi and is of value vi. Considering the constraints of the maximum weight that a knapsack can carry, you 
have to find and return the maximum value that a thief can generate by stealing items.

Sample Input:  weight = [1 2 4 5]   value = [5 4 8 6]      maxWeight = 5
Sample Output:  13

*/

public class Knapsack {
    public static void main(String[] args) {
        int weight[] = {1,2,4,5};
        int value[]  = {5,4,8,6};
        int maxWeight = 5;
        int n = weight.length;

        System.out.println( knapsackMemo(weight, value, n, maxWeight) );

        System.out.println( knapsackTabu(weight, value, n, maxWeight) );

    }

    private static int knapsackTabu(int[] weight, int[] value, int n, int maxWeight){

        // declare the dp array by considering the changing parameters in the recursion
        int [][] dp = new int[n][maxWeight+1];

        // Write down the base cases
        for (int w = 0; w <= maxWeight; w++) { // For all bag weight which are in between 0th element weight and maxWeight => In all such cases it can be picked
            if (weight[0] <= w) {
                dp[0][w] = value[0];
            }
        }
        //Write the for loops for the changing parameters
        for(int ind = 1 ; ind < n ; ind++){
            for(int currWeight = 0 ; currWeight <= maxWeight ; currWeight++){
                //Copy the recurrence
                int notPick = dp[ind-1][currWeight];
                int pick = 0 ;
                if(weight[ind] <= currWeight) pick = value[ind] + dp[ind-1][currWeight-weight[ind]];

                dp[ind][currWeight] = Math.max(pick,notPick);
            }
        }
        return dp[n-1][maxWeight];
    }

    private static int knapsackMemo(int[] weight, int[] value, int n, int maxWeight) {
       
        int dp[][] = new int[n][maxWeight+1];

        for(int arr[] : dp) Arrays.fill(arr,-1);

        return fMemo(weight,value,n-1,maxWeight,dp);
    }

    private static int fMemo(int [] weight , int value[] , int ind , int maxWeight , int[][] dp){
        // Base case 
        if(ind == 0){ // If we are at 0th index , we will pick it if it fits in our bag
            if(weight[0] <= maxWeight) return value[0];
            else return 0;
        }
        // Avoid recomputing
        if(dp[ind][maxWeight] != -1) return dp[ind][maxWeight];
        // Without picking the current weight move to the next index
        int notPick = fMemo(weight, value, ind-1, maxWeight,dp); // Moving to the next item without taking current weight
        int pick = 0 ;
        // We can only pick if it fits in our bag
        // If we pick then we have to reduce the available weight also
        if(weight[ind] <= maxWeight) pick = value[ind] + fMemo(weight, value, ind-1, maxWeight-weight[ind],dp);

        dp[ind][maxWeight] = Math.max(pick,notPick);

        return dp[ind][maxWeight];
    }
    
}
