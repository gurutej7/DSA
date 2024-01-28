package DP.LIS.LISfollowUps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 368. Largest Divisible Subset
{ https://leetcode.com/problems/largest-divisible-subset/description/ } 

Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], 
answer[j]) of elements in this subset satisfies:
*   answer[i] % answer[j] == 0, or
*   answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

Example 1:  Input: nums = [1,2,3]       Output: [1,2]
Explanation: [1,3] is also accepted.

Example 2:  Input: nums = [1,2,4,8]     Output: [1,2,4,8]

*/

public class LargestDivisibleSubset {
    public static void main(String[] args) {
        int nums[]= {2,3,4,9,8}; // expected  [2,4,8]

        System.out.println( largestDivisibleSubset(nums) );
    }

    private static  List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        // dp[i] => represents the length of largest divisible subset till ith index
        // The idea behind sorting is 
        // If we are at a element X , Then if there exixts previous element Y , 
        // If(Y divides X) then all the values before Y which divides Y , will also divide X

        int dp[]  = new int[n];
        Arrays.fill(dp, 1);

        // The concept of path array is same as from LISprint
        int path[] = new int[n]; 
        for (int i = 0; i < n; i++) path[i] = i;

        List<Integer> lis = new ArrayList<>();
    
        int maxIndex = 0, maxLength = 0;
        for (int i = 1; i < n; i++) {
            // Reverse looping it can be from 0 to j also
            for (int j = i - 1; j >= 0; j--) {
                // If current prev of i divides i , then check if yields a Length greater than current len or not
                if (nums[i] % nums[j] == 0 && dp[i] <= dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    path[i] = j;
                }
                // If the current element crosses the previous max then update
                if (dp[i] > maxLength) {
                    maxLength = dp[i];
                    maxIndex = i;
                }
            }
            
        }
        
        // print LIS 
        lis.add(nums[maxIndex]);
        int prev = path[maxIndex];
        while (lis.size() < maxLength) {
            lis.add(nums[prev]);
            prev = path[prev];
        }
    
      
        return lis;
    }
}
