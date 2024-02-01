package DP.PartitionDp;

import java.util.Arrays;

/* 1043. Partition Array for Maximum Sum
{ https://leetcode.com/problems/partition-array-for-maximum-sum/ } 

Given an integer array arr, partition the array into (contiguous) subarrays of length at most 
k. After partitioning, each subarray has their values changed to become the maximum value of 
that subarray.
Return the largest sum of the given array after partitioning. Test cases are generated so that
 the answer fits in a 32-bit integer.

Example 1:      Input: arr = [1,15,7,9,2,5,10], k = 3               Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]

Example 2:      Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4         Output: 83
 */

public class PartitionArrayForMaxSum {
    public static void main(String[] args) {
        int arr[] = {1,4,1,5,7,3,6,1,9,9,3} ;
        int k = 4 ;

        System.out.println( maxSumAfterPartitioningMemo(arr, k) );
        System.out.println( maxSumAfterPartitioningTabu(arr, k) );

    }

    private static int maxSumAfterPartitioningMemo(int[] arr, int k) {
        int n = arr.length;

        int dp[] = new int[n];

        Arrays.fill(dp,-1);

        return fMemo(0,arr,k,dp);
    }

    private static int fMemo(int i, int[] arr, int k, int[] dp) {
        // Base case
        if(i >= arr.length) return 0 ;

        // Memo
        if(dp[i] != -1) return dp[i];

        // At any index we can do atMost K partitions
        int currLen = 0 , currMax = Integer.MIN_VALUE , ansSum = Integer.MIN_VALUE;
        for(int ind = i ; ind < Math.min(arr.length , i+k) ; ind++){ // i+k may go out of bound so take min of n,i+k
            currLen++;
            currMax = Math.max(arr[ind],currMax);

            int sum = (currLen*currMax) + fMemo(ind+1, arr, k, dp);

            ansSum = Math.max(ansSum,sum);

        }

        dp[i] = ansSum ;

        return dp[i];
    }

    private static int maxSumAfterPartitioningTabu(int[] arr, int k) {
        int n = arr.length;

        int dp[] = new int[n+1];

        for(int i = n-1 ; i >= 0 ; i--){
            // copy the recurrence
             // At any index we can do atMost K partitions
            int currLen = 0 , currMax = Integer.MIN_VALUE , ansSum = Integer.MIN_VALUE;
            for(int ind = i ; ind < Math.min(n , i+k) ; ind++){ // i+k may go out of bound so take min of n,i+k
                currLen++;
                currMax = Math.max(arr[ind],currMax);

                int sum = (currLen*currMax) + dp[ind+1];

                ansSum = Math.max(ansSum,sum);

            }

            dp[i] = ansSum ;

        }

       

        return dp[0];
    }

}
