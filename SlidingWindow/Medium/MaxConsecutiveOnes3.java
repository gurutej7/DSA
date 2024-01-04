package SlidingWindow.Medium;
/* 1004. Max Consecutive Ones - 3

link - { https://leetcode.com/problems/max-consecutive-ones-iii/description/ }
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can 
flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2                    Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 
*/

public class MaxConsecutiveOnes3 {
    public static void main(String[] args) {
        int [] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;

        System.out.println(longestOnes(nums, k));
    }
    
    /* If we translate the question a little bit 
    It is asking the longest subarray with at most K 0(s);

     * We will increment right till the subArray has less then K 0(s)
     * if 0(s) > K => increment both left and right , if(left encounters a zero ) (decrease no. of zeros in the window)  
     * 
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0 , right = 0 , cntZeros = 0  , max = Integer.MIN_VALUE;
        int n = nums.length;
        while(right < n){
            //Keep track of no. of zeros in the current Window (i.e., between left and right pointer)
            if(nums [right] == 0 )cntZeros++;

            if(cntZeros > k){
                if(nums[left] == 0)cntZeros--;
                left++;
            }

            max = Math.max(max, right-left +1);
            right++;
            
        }
       
        return max;
    }
}
