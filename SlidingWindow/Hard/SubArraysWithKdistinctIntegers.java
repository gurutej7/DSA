package SlidingWindow.Hard;

import java.util.HashMap;

/* 992. Subarrays with K different Numbers

link { https://leetcode.com/problems/subarrays-with-k-different-integers/description/ }
Given an integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.

Example 1:
Input: nums = [1,2,1,2,3], k = 2                    Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Example 2:
Input: nums = [1,2,1,3,4], k = 3                    Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 
*/

public class SubArraysWithKdistinctIntegers {
    public static void main(String[] args) {
        int [] nums = {1,2,1,3,4};
        int k = 3;

        System.out.println(  subarraysWithKDistinct(nums, k)  );

    }

     public static int subarraysWithKDistinct(int[] nums, int k) {
          return Atmost(nums,k) - Atmost(nums,k-1);
        }

        // Exactly K times = at most K times - at most K - 1 times

        private  static int Atmost(int[] nums, int k) {
            int left = 0 , right = 0 , distinctCount = 0 , res = 0;

            HashMap<Integer,Integer> map = new HashMap<>();

            if(k == 0) return 0;

            while(right < nums.length){
                // If it is distinct (appearing for 1st time)
                if(map.getOrDefault(nums[right],0) == 0)
                    distinctCount++;
                // Record the occurrence ( no. of times ) in the map
                // As long as distinct count is under then the same number can appear any number of times
                map.put(nums[right] , map.getOrDefault(nums[right],0)+1);

                // If the distinct count is above K we have to shrink the window from left till distinctCount <= k
                while( distinctCount > k){
                        // We will reduce the count of occurence of the every element we are skipping
                        map.put(nums[left], map.get(nums[left])-1);
                        // If we have skipped all the occurences of a certain number then only we can reduce the distinctCount
                        if(map.get(nums[left]) == 0) distinctCount--;

                        left++;
                }
                
                // Number of subarrays in the current window which has distinct characters < = k
                res += right-left+1;

                right++;
            }
            return res;
        }
    
        // { https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/523136/java-c-python-sliding-window/ }


    
}
