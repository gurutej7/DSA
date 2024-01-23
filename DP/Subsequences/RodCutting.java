package DP.Subsequences;

import java.util.Arrays;

/* Rod Cutting Problem 

Given a rod of length ‘N’ units. The rod can be cut into different sizes and each size has a cost associated with
 it. Determine the maximum cost obtained by cutting the rod and selling its pieces.
Note:
1. The sizes will range from 1 to ‘N’ and will be integers.
2. The sum of the pieces cut should be equal to ‘N’.
3. Consider 1-based indexing.

Sample Input 1:     int n = 5        price =[2 5 7 8 10]
Sample Output 1:    12
Explanation :
All possible partitions are:
1,1,1,1,1           max_cost=(2+2+2+2+2)=10
1,1,1,2             max_cost=(2+2+2+5)=11
1,1,3               max_cost=(2+2+7)=11
1,4                 max_cost=(2+8)=10
5                   max_cost=(10)=10
2,3                 max_cost=(5+7)=12
1,2,2               max _cost=(1+5+5)=12    

Clearly, if we cut the rod into lengths 1,2,2, or 2,3, we get the maximum cost which is 12.
*/

public class RodCutting {
    public static void main(String[] args) {
        int price[] = {2,5,7,8,10};
        int n = 5 ;

        System.out.println( cutRodMemo(price, n) );

        System.out.println( cutRodTabu(price, n) );
    }

    // The probem description is little bit confusing 
    // If we try out the given example on paper
    // We have two choices at each index , either to cut there or move forward and cut somewhere else
    // But when we cut we need to calculate the price for the length that we cut
    // To calculate the length of rod at a certain point we have to know where the rod was cut previously
    // We will keep track of the last Cutting point with a extra variable

    private static int cutRodMemo(int price[], int n) {
		int dp[][] = new int[n][n];

		for(int[] row : dp) Arrays.fill(row,-1);

		return fMemo(price,n-1,n-1,dp);
    
	}
	
	private static int fMemo(int price[] , int ind , int lastCut , int[][] dp){

		if(ind == 0){ // If we are at the last index then we have no choice but to sell the rod (with its corresponding length)
			return price[lastCut-ind];
		}


		if(dp[ind][lastCut] != -1) return dp[ind][lastCut]; // Avoid recomputing

		// I choose to cut here
		int cut = price[lastCut-ind] + fMemo(price,ind-1,ind-1,dp);
		// I did not choose to cut this length
		int notCut = fMemo(price,ind-1,lastCut,dp);

		dp[ind][lastCut] = Math.max(cut,notCut);

		return dp[ind][lastCut];
	}

    private static int cutRodTabu(int price[], int n){
        int dp[][] = new int[n][n];
		// Write down the base cases
		for(int lastCut = 0 ; lastCut < n ; lastCut++){
			dp[0][lastCut] = price[lastCut-0];
		}

        // Write for loops for changing parameters in the recursion
		for(int ind = 1 ; ind < n ; ind++){
			for(int lastCut = 0 ; lastCut < n ; lastCut++){
				// Copy the recurrence
				int cut = 0 ;
				if(lastCut >= ind) cut = price[lastCut-ind] + dp[ind-1][ind-1];
				int notCut = dp[ind-1][lastCut];

				dp[ind][lastCut] = Math.max(cut,notCut);
			}
		}
        // Return for which the memoization was called
		return dp[n-1][n-1];
    }
    
}
