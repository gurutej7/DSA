package Arrays.Medium;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubArrayWithSumK {
    
    /* Longest Sub Array With Sum K
     * { https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1 }
     * 
     * Given an array containing N integers and an integer K., 
     * Your task is to find the length of the longest Sub-Array with the sum of the elements equal to the given value K.
     */

    // Function for finding maximum and value pair
    private static int lenOfLongSubarr (int arr[], int K) {
        int n = arr.length;
        HashMap<Long,Integer> map = new HashMap<>();
        
        long sum = 0 , k =1L*K;
        int maxLen = 0 ;
        for(int i = 0 ; i < n ; i++){
            sum += 1L*arr[i];
            if(sum == k) maxLen = Math.max(maxLen,i+1);
            
            long rem = sum-k;
            
            if(map.containsKey(rem))
                maxLen = Math.max(maxLen , i - map.get(rem));
            
            if(!map.containsKey(sum))
                map.put(sum,i);
        }
        
        return maxLen;
    }

    // Optimal solution (When array has only positive integers)
    private static int  lenOfLongSubarrPos(int arr[] , int K){
        long sum = 1L*0 , k = 1L*K;
        int left = 0 , right = 0 , n = arr.length, maxLen = 0 ;
        while(right < n){
            // shrink the window
            while(left <= right && sum > k){
                sum -= 1L*arr[left];
                left++;
            }

            if(sum == k) maxLen = Math.max(maxLen, right-left);

            sum += 1L*arr[right];
            right++;
        }

        return maxLen;

    }

    /* 1. Two Sum
     * { https://leetcode.com/problems/two-sum/description/ }
     * 
     * Example 1:   Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     * 
     */

    private static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int req = target - nums[i];
            if (map.containsKey(req))
                return new int[] { map.get(req), i };
            map.put(nums[i], i);
        }

        return new int[] { -1, -1 };
    }
    
    // if indices are not required , just say target sum exists or not
    private static boolean twoSumBool(int[] nums, int target){
        Arrays.sort(nums);
        int left = 0 , right = nums.length-1;

        while(left < right){
            int sum = nums[left] + nums[right];

            if(sum == target) return true;
            else if(sum > target) right--;
            else left++;
        }
        return false;
    }

    public static void main(String[] args) {
        int nums[]  = new int[]{1,2,3,4,5,6,7};
        System.out.println(lenOfLongSubarr(nums, 6));
        System.out.println(lenOfLongSubarrPos(nums, 6));

        System.out.println(Arrays.toString(twoSum(nums, 11)));
        System.out.println(twoSumBool(nums, 3));

    }
    
}
