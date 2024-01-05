package SlidingWindow.Medium;

import java.util.HashMap;

/* 930. Binary Subarrays with sum
link - { https://leetcode.com/problems/binary-subarrays-with-sum/description/ }

Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum
 goal.
A subarray is a contiguous part of the array.

Example 1:
Input: nums = [1,0,1,0,1], goal = 2                 Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[ "1,0,1"  ,0,1]
[ "1,0,1,0," ,1]
[1,  "0,1,0,1" ]
[1,0,  "1,0,1" ]

Example 2:
Input: nums = [0,0,0,0,0], goal = 0                 Output: 15
 */

public class BinarySubArraysWithSum {
    public static void main(String[] args) {
        int [] nums = { 1,0,1,0,1};
        int k = 2;

        System.out.println( numSubarraysWithSum1(nums, k) );
    }

    // Approach -> Sliding Window
    // COncept of ("Atmost sum <= k " - "Atmost sum <= k-1" ) == num of subArrays with sum "K"
    // { https://leetcode.com/problems/binary-subarrays-with-sum/solutions/2866679/easy-and-detailed-sliding-window-approach-at-most-method-detailed-explanation-c-o-n/ }

    public static int numSubarraysWithSum2(int[] nums, int k) {
        return atMostSubarraySum(nums,k) - atMostSubarraySum(nums,k-1);
    }

    public static int atMostSubarraySum(int[] nums , int k){
        // Sum cannot be negative
        if(k < 0) return 0;
        int left = 0 , right = 0 , currentSum = 0 ;
        int count = 0 ;
        while(right < nums.length ){
            currentSum += nums[right];
            // If the current sum is greater then decrease the window size from left
            while(currentSum > k){
                currentSum -= nums[left];
                left++;
            }
            // array Sum of ( arr[left , left+1,left+2... , right]) is less than K
            // Then all subarrays which can be formed with the above subarray will also have the sum less than K
            // that is [left , left+1] , [left,left+1,left+2] ,......[left,left+1,left+2,...right-1] will have sum less tha K
            count += right-left+1;
            right++;
        }

        return count;
    }


    


    // Instead of HashMap we can use Array because it has only 0`s and 1`s so the sum can`t exceed array length
    // Count the occurrence of all prefix sum
    public static int numSubarraysWithSum1(int[] A, int S) {
        int psum = 0, res = 0, count[] = new int[A.length + 1];
        count[0] = 1;
        for (int i : A) {
            psum += i;
            if (psum >= S)
                res += count[psum - S];
            count[psum]++;
        }
        return res;
    }

    //Same as SUbarray sum equals K can be used , the approach is using prefix sum
    public int numSubarraysWithSum(int[] nums, int k) {
       int n = nums.length;
        int prefix[] = new int[n];
        int sum = 0 , count = 0 ; 
        for(int i = 0 ; i < n ; i++){
            sum += nums[i];
            prefix[i] = sum;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i<n ; i++){
            if(prefix[i] == k)count++;
            if(map.containsKey(prefix[i] - k) ) count += map.get(prefix[i]-k) ;
            map.put(prefix[i],map.getOrDefault(prefix[i],0)+1);
        }
        return count;
    }
}
