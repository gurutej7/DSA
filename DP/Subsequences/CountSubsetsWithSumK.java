package DP.Subsequences;

import java.util.Arrays;

/* Count Subsets With Sum equal to K

You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.
Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.
Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.

Example:        Input: 'arr' = [1, 1, 4, 5]  , k = 5               Output: 3
Explanation: The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently. 
*/

public class CountSubsetsWithSumK {
    public static void main(String[] args) {
        int nums[] = {1,1,4,5};
        int k = 5;

        System.out.println( findWaysMemo(nums, k) );

        System.out.println( findWaysTabu(nums, k) );

        System.out.println( findWays(nums, k) );
    }

    static int mod = (int)1e9+7;
    
    static int findWaysUtil(int ind, int target,int[] arr, int[][] dp){
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
    
    static int findWays(int[] num, int k){
        int n = num.length;
        int dp[][]= new int[n][k+1];
        
        for(int row[]: dp)
        Arrays.fill(row,-1);
        
        return findWaysUtil(n-1,k,num,dp);
    }


    private static int findWaysTabu(int arr[] , int target){
        // Write your code here.
        int n = arr.length;
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
    // when nums has no 0 `s 
    // The below code works fine
    // If the given array has 0`s then we have to adjust the base cases
    private static int findWaysMemo(int nums[], int k) {
        // Write your code here.
        int n = nums.length;

        int dp[][] = new int [n][k+1];

        for(int i = 0 ; i< n ; i++) Arrays.fill(dp[i],-1);
        
        return fMemo(n-1,k,nums,dp);
    }

    private static int fMemo(int ind , int sum , int[] nums , int[][] dp){
        // Base case
        // Count ways will always have either 1 or 0 in the base case
        if(sum == 0) return 1; // One valid subset
        if(ind == 0) return nums[0] == sum ? 1 : 0 ; // If we are at the 1 st index then if sum is equal to that element then only valid
        if(ind < 0) return 0;

        if(dp[ind][sum] != -1) return dp[ind][sum] ; // Avoid recomputing for the same state

        int pick = 0 ;
        // We can only pick if it is less than or equal to current sum
        if(nums[ind] <= sum) pick = fMemo(ind-1, sum-nums[ind], nums, dp);
        int notPick = fMemo(ind-1, sum, nums, dp);
        // Store the result of current state
        dp[ind][sum] = (pick+notPick)%mod;

        return dp[ind][sum]%mod;
    }
    
}
