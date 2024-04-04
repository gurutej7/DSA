package Arrays.Medium;

public class MaximumSubArray {

    /* 53. Maximum SubArray
     * { https://leetcode.com/problems/maximum-subarray/description/ }
     * 
     * Example 1:   Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
     */

    private static int maxSubArray(int[] nums) {
        // int left = 0, right = 0, n = nums.length;
        int sum = 0, maxSum = Integer.MIN_VALUE;
        // while (right < n) {
        //     sum += nums[right];
        //     maxSum = Math.max(sum, maxSum);
        //     // carrying negative sum won`t help us to maximize the subarray sum
        //     while (left <= right && sum < 0) {
        //         sum -= nums[left];
        //         left++;
        //     }
        //     right++;
        // }

        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            maxSum = Math.max(sum, maxSum);
            // carrying negative sum won`t help us to maximize the subarray sum
            if(sum < 0) sum = 0 ; 
        }

        return maxSum;
    }

    private static void maxSubArrayPrint(int[] nums){
        int start = 0 , ansStart = -1 , ansEnd = -1;
        int sum = 0 , maxSum = Integer.MIN_VALUE;

        for(int i = 0 ; i < nums.length ; i++){
            if(sum == 0) start = i ;
            sum += nums[i];
            if(sum > maxSum){
                maxSum = sum ;
                ansStart = start;
                ansEnd = i;
            }

            if(sum < 0) sum = 0 ;
        }

        for(int i = ansStart ; i <= ansEnd ; i++)
            System.out.print(nums[i] + ", ");
        
    }
    public static void main(String[] args) {
        int nums[] = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray(nums));
        maxSubArrayPrint(nums);
    }
    
}
