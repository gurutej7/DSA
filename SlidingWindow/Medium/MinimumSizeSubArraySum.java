package SlidingWindow.Medium;


/* 209. Minimum Size Sub Array Sum

link { https://leetcode.com/problems/minimum-size-subarray-sum/description/ }
Given an array of positive integers nums and a positive integer target, return the minimal length of  
subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]                 Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
Input: target = 4, nums = [1,4,4]                       Output: 1

Example 3:
Input: target = 11, nums = [1,1,1,1,1,1,1,1]            Output: 0
*/

public class MinimumSizeSubArraySum{
    public static void main(String[] args) {
        int [] nums = {1,4,4};
        int target = 4;

        System.out.println(     minSubArrayLen(target, nums)  );
        
    }

    // Simple Sliding Window problem
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0 , right = 0 , sum = 0 ;
        int  n = nums.length , len = Integer.MAX_VALUE;
        while(right < n){
            // Add to the current Running Sum
            sum += nums[right];
            // If we have found the required condition , compute the Minimum length
            while(sum >= target){
                len = Math.min(len,right-left+1);
                // Remove the left most element in the subarray , since we have to minimize the length
                sum -= nums[left];
                left++;
            }

            right++;
        }
        return len == Integer.MAX_VALUE ? 0 : len ;
    }

}