package SlidingWindow.Medium;

import java.util.HashMap;

/*  560. Subarray Sum equals K 

link { https://leetcode.com/problems/subarray-sum-equals-k/description/ }
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2                            Output: 2

Example 2:
Input: nums = [1,2,3], k = 3                            Output: 2

Example 3:
Input: nums = [0,0,0,0,0,0,0,0,0,0],  k = 0             Output: 55

*/

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        int [] nums = {1,2,3};
        int k = 3;

        System.out.println( subarraySum(nums, k) );
    }
    public static int subarraySum(int[] nums, int k) {
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
    // Checkout Below Links for detailed Explanantion of the idea
    // { https://leetcode.com/problems/subarray-sum-equals-k/solutions/1759909/c-full-explained-every-step-w-dry-run-o-n-2-o-n-two-approache }

    // Video 
    // { https://youtu.be/xvNwoz-ufXA?feature=shared }
}
