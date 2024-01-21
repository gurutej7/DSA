package DP.Subsequences;

/* 416. Partition Equal Subset Sum
{ https://leetcode.com/problems/partition-equal-subset-sum/description/ }

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the 
elements in both subsets is equal or false otherwise.

Example 1:      Input: nums = [1,5,11,5]            Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */

public class PartitionEqualSubsetSum{
    public static void main(String[] args) {
        int nums[] = {1,5,11,5};

        System.out.println( canPartitionMemo(nums) );

        System.out.println( canPartitionTabu(nums) );
    }

    private static boolean canPartitionTabu(int[] nums) {
        int n = nums.length;
        // If there is only one element we cannot partition it into two subsets
        if(n == 1) return false;
        int sum = 0 ;
        for(int i : nums) sum += i;
        // If sum is odd we cannot divide it into two equal parts
        if(sum % 2 != 0) return false;
        // if the sum is even we can divide it into two equal parts
        // We can simply check for sum/2 can be formed or not
        // because if sum/2 exists then then the remaining sum always will be remaining sum/2
        // Tabulation
        int target = sum/2;
        boolean dp [][] = new boolean[n][target+1];
        //Write down the base case ( assume if there is only one element)
        if(nums[0] <= target) dp[0][nums[0]] = true;
        // Look at the changing parameters
        for(int ind = 1 ; ind < n ; ind++){
            for(int currSum = 0 ; currSum <= target ; currSum++){
                // Notpick
                boolean notPick = dp[ind-1][currSum];
                boolean pick = false;
                if(nums[ind] <= currSum) pick = dp[ind-1][currSum-nums[ind]];
                dp[ind][currSum] = pick || notPick ;
            }
        }
        return dp[n-1][target];
    }

    private static boolean canPartitionMemo(int nums[]){
        int n = nums.length;
        // If there is only one element we cannot partition it into two subsets
        if(n == 1) return false;
        int sum = 0 ;
        for(int i : nums) sum += i;
        // If sum is odd we cannot divide it into two equal parts
        if(sum % 2 != 0) return false;
        int target = sum/2;
        Boolean dp[][] = new Boolean[n][target+1];

        return fMemo(n-1,target,dp,nums);
    }

    private static boolean fMemo(int ind,int target,Boolean dp[][],int[] nums){
        if(target == 0) return true;

        if(ind == 0) return nums[0] == target;
        if(ind < 0) return false;

        if(dp[ind][target] != null) return dp[ind][target];

        // Pick
        boolean pick = false;
        if(nums[ind] <= target) pick = fMemo(ind-1, target - nums[ind], dp, nums);
        boolean notPick = fMemo(ind-1, target, dp, nums);

        dp[ind][target] = pick || notPick ;

        return dp[ind][target];
    }
}