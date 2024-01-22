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
    }
    
    static int findWaysUtil(int ind, int target,int[] arr, int[][] dp){

        if(ind == 0){
                if(target==0 && arr[0]==0) // Edge cases 
                // refer =>{ https://takeuforward.org/data-structure/count-partitions-with-given-difference-dp-18/ }
                    return 2;
                if(target==0 || target == arr[0])
                    return 1;
                return 0;
        }
        
        if(dp[ind][target]!=-1)
            return dp[ind][target];
            
        int notTaken = findWaysUtil(ind-1,target,arr,dp);
        
        int taken = 0;
        if(arr[ind]<=target)
            taken = findWaysUtil(ind-1,target-arr[ind],arr,dp);
            
        return dp[ind][target]= notTaken + taken;
    }
    
    static int findWays(int[] num, int k){
        int n = num.length;
        int dp[][]= new int[n][k+1];
        
        for(int row[]: dp)
        Arrays.fill(row,-1);
        
        return findWaysUtil(n-1,k,num,dp);
    }

    static int mod = (int)1e9+7;
    private static int findWaysTabu(int nums[] , int k){
        // Write your code here.
        int n = nums.length;

        int dp[][] = new int [n][k+1];

        // Write down the base cases
        if(nums[0] <= k) dp[0][nums[0]] = 1;

        for(int i = 0 ; i< n ; i++) dp[i][0] = 1;
         // Look at the changing parameters
        for(int ind = 1 ; ind < n ; ind++){
            for(int currSum = 1 ; currSum <= k ; currSum++){
                // Notpick
                int notPick = dp[ind-1][currSum];
                int pick = 0;
                if(nums[ind] <= currSum) pick = dp[ind-1][currSum-nums[ind]];
                dp[ind][currSum] = (pick + notPick)%mod ;
            }
        }
        return dp[n-1][k]%mod;
    }
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
