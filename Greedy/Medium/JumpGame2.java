package Greedy.Medium;

import java.util.Arrays;

/*  45. Jump Game - 2
{ https://leetcode.com/problems/jump-game-ii/description/ } 

Same as Jump Game 1 , here we have to return the minimum number of steps required to reach n-1 index

Example 1:      Input: nums = [2,3,1,1,4]           Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */

public class JumpGame2 {
    public static void main(String[] args) {
        int nums[] = {2,3,1,1,4};

        System.out.println( jumpMemo(nums) );
        System.out.println( jumpGreedy(nums) );
    }

    // Trying out all possible ways and and returning the min number of steps
    // time - O(n^2) space - O(n) + O(n)
    private static int jumpMemo(int[] nums) {
       int n = nums.length;
       int dp[] = new int[n];
       
       Arrays.fill(dp,-1);

       return fMemo(nums,0,dp);
    }

    private static int fMemo(int[] nums , int i , int[] dp){
        if(i == nums.length-1) return 0;

        if(dp[i] != -1) return dp[i];

        int mini = (int)1e7;
        for(int j = 1 ; j <= nums[i] && (i+j <= nums.length-1) ; j++){
            if(nums[i+j] == 0 && i+j != nums.length-1) continue;
            int steps = 1 + fMemo(nums,i+j,dp);
            mini = Math.min(steps,mini);
        }

        dp[i] = mini;

        return dp[i];

    }

    // Greedy approach
    private static int jumpGreedy(int[] nums) {
        int n = nums.length;
        // we traverse from beginning and we will set our end based on the nums[i] , we have seen so far
        // for every item we will check if it can cover moresteps than our previous max element
        // If it cannot , we don`t jump there because we are at a element which can cover the distance of current 
        // element then why would we want to jump twice for the same distance while we can do it in single jump
        // On same range don`t do multiple jumps , range get`s updated when we have covered the previous range entirely 
        // which is represented by currEnd
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > curFarthest) return -1; // if we have not reached our goal with the max steps we had
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i < n-1 && i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

    
}
