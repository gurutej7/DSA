package DP.Subsequences;

import java.util.Arrays;

/*  Partition with Given Difference

Given an array ‘ARR’, partition it into two subsets (possibly empty) such that their union is the original array.
 Let the sum of the elements of these two subsets be ‘S1’ and ‘S2’.
Given a difference ‘D’, count the number of partitions in which ‘S1’ is greater than or equal to ‘S2’ and the 
difference between ‘S1’ and ‘S2’ is equal to ‘D’. Since the answer may be too large, return it modulo ‘10^9 + 7’.

For example :
If N = 4, D = 3, ARR = {5, 2, 5, 1}
There are only two possible partitions of this array.
Partition 1: {5, 2, 1}, {5}. The subset difference between subset sum is: (5 + 2 + 1) - (5) = 3
Partition 2: {5, 2, 1}, {5}. The subset difference between subset sum is: (5 + 2 + 1) - (5) = 3
These two partitions are different because, in the 1st partition, S1 contains 5 from index 0, and in the 2nd 
partition, S1 contains 5 from index 2.
*/

public class PartitionWithGivenDifference {

    public static void main(String[] args) {
        int arr[] = {5,2,5,1};
        int diff = 3;
        int n = arr.length;

        System.out.println( countPartitionsMemo(n, diff, arr) );

        System.out.println( countPartitionsTabu(n, diff, arr) );
    }

    // S1 - S2  = D
    // S1 + S2  = TotalSum
    // =>  2*S1 = D + TotalSum   => S1 = ( D+TotalSum / 2 )
    // Now the question comes down to count the number of subsets with Sum S1
    static int mod = (int)1e9+7;
    public static int countPartitionsMemo(int n, int diff, int[] arr) {
		// Write your code here.

        int totalSum =  0; 
        for(int i : arr) totalSum += i;

        // Some checks according to question
        if( totalSum < diff) return 0 ;

        if( (totalSum+diff) %2 != 0) return 0; // If it is odd on dividing by 2 it will result in decimal

        int target = (totalSum+diff)/2 ;

        int dp[][] = new int[n][target+1];

        for(int i = 0 ; i< n ; i++) Arrays.fill(dp[i] , -1);

        return fMemo(n-1,target,arr,dp);

	}

    private static int fMemo(int ind , int target , int arr[] , int [][] dp){
        // Base cases
        if(ind == 0){
            if(arr[0] == 0 && target == 0) return 2 ; // Since we have two choices can pick the zero at 0th index or not pick
            if(target == 0 || target == arr[0]) return 1 ; // Not pick => target == 0  pick => target == arr[0]
            return 0 ;
        }

        if(dp[ind][target] != -1) return dp[ind][target] ;// Avoid recomputing for the same state

        int notPick = fMemo(ind-1, target, arr, dp);
        int pick = 0 ;
        // We can only pick if it is less than equal to target
        if(arr[ind] <= target) pick = fMemo(ind-1, target - arr[ind] , arr, dp);

        dp[ind][target] = (pick + notPick ) % mod ;

        return dp[ind][target];
    }

    private static int countPartitionsTabu(int n , int diff , int arr[]){
        int totalSum =  0; 
        for(int i : arr) totalSum += i;

        // Some checks according to question
        if( totalSum < diff) return 0 ;

        if( (totalSum+diff) %2 != 0) return 0; // If it is odd on dividing by 2 it will result in decimal

        int target = (totalSum+diff)/2 ;

        int dp[][] = new int[n][target+1];

        // Write down the base cases
        if(arr[0] == 0) dp[0][0] = 2; // pick and not pick both are valid
        else dp[0][0] = 1 ; // Target = 0  we donot pick 0th index

        if(arr[0] != 0 && arr[0] <= target) dp[0][arr[0]] = 1; // base case where ind == 0 && target == arr[0]  , we will pick it

        

        // Write for loops for the changing parameters

        for(int ind = 1 ; ind<n ; ind++){
            for(int currSum = 0 ; currSum <= target ; currSum++){
                // Copy the recurrence
                int notPick = dp[ind-1][currSum];
                int pick =  0 ;
                if(arr[ind] <= currSum) pick = dp[ind-1][currSum - arr[ind]] ;

                dp[ind][currSum] = (pick+notPick) % mod;
            }
        }

        return dp[n-1][target];
    }

}
