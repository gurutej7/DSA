package Greedy.Medium;

/* 55. Jump Game
{ https://leetcode.com/problems/jump-game/ } 

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example 1:      Input: nums = [2,3,1,1,4]       Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */

public class JumpGame1 {
    public static void main(String[] args) {
        int nums[] = {2,3,1,1,4};

        System.out.println( canJumpMemo(nums) );
        System.out.println( canJumpTabu(nums) );
        System.out.println( canJumpGreedy(nums) );
        
    }
    
    // At index i  we can try j jumps where  1 <= j <= nums[i]
    // I will try out all possible jumps and try whether I can reach or not
    // Time - O(n^2)  Space - O(n) + O(n)
    private static  boolean canJumpMemo(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;
        Boolean [] dp = new Boolean[n];

        return fMemo(nums,0,dp);
    }

    private static boolean fMemo(int[] nums , int i , Boolean[] dp){
        if(i == nums.length-1) return true;

        if(dp[i] != null) return dp[i];

        boolean flag = false;
        for(int j = 1 ; j <= nums[i] && (i+j <= nums.length-1) ; j++){
            flag = flag || fMemo(nums,i+j,dp);
        }

        dp[i] = flag;

        return dp[i];

    }

    private static boolean canJumpTabu(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;
        boolean dp[] = new boolean [n];
        // base case 
        dp[n-1] = true;
    

        for(int i = n-2 ; i >=0 ; i--){
            if(nums[i] == 0) continue;
            // copy the recurrence
            boolean flag = false;
            for(int j = 1 ; j <= nums[i] && (i+j <= nums.length-1) ; j++){
                
                flag = flag || dp[i+j];
            }

            dp[i] = flag;

        }

        return dp[0];
    }
    

    // Greedy Approach   time - O(N)  space - O(1)
    // I will have initial goal as n-1
    // I will traverse from the back and search for a nums[i] such that nums[i] can provide steps from ith position to my current goal
    // then change current goal with that position and continue the process
    // At the end your current goal should be 0 , the only there is a valid jumping path
    private static boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        if(n == 1) return true;
        int currGoal = n-1 ;
        for(int i = n-2 ; i >= 0 ; i--){
            if(nums[i] >= (currGoal - i) ){
                currGoal = i ;
            }
        }

        return currGoal == 0;

    }
}
