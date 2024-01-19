package DP.OneD;
/* 198. House Robber 
{ https://leetcode.com/problems/house-robber/description/ }
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money 
stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems
 connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:      Input: nums = [1,2,3,1]             Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4. 

Example 2 :     Input: nums = [2,1,1,2]             Output: 4

*/

import java.util.Arrays;

public class HouseRobber {
    public static void main(String[] args) {
        int [] nums = {2,1,1,2};
        
        System.out.println( robTabulation(nums, nums.length) );
    }
    
    public int robMemo(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        return f(nums,n-1,dp);
    }
    public int f(int [] nums , int n , int[] dp){
        // Base case
        if(n < 0) return 0 ;
        if(n == 0) return nums[0]; // If we are at the 0 th element we either pick or not pick 
        //but to maximize our sum we always choose to pick the 0 th element , when we are at it
        
        // Avoid recomputing
        if(dp[n] != -1) return dp[n];

        // Choose to pick the current element or not
        int pick = nums[n] + f(nums,n-2,dp); // If we pick we cannot pick next so we skip it and move forward
        int notPick = 0 + f(nums,n-1,dp); // If we do not pick current then we can move to the next adjacent one

        dp[n] = Math.max(pick,notPick);
        return dp[n];
    }

    public static int robTabulation(int nums[] , int n ){
        int dp[] = new int[n];
        // Write the base case
        dp[0] = nums[0];

        for(int i = 1 ; i< n ; i++){
            // pick
            int pick = nums[i];
            // Check if we have previous 
            if(i-2 >= 0) pick += dp[i-2];
            // Not pick
            int notPick = dp[i-1]; 

            dp[i] = Math.max(pick , notPick);   // Take the max among them
        }

        return dp[n-1];
    }

//{ https://leetcode.com/problems/house-robber/solutions/156523/from-good-to-great-how-to-approach-most-of-dp-problems/ }
}
