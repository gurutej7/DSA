package DP.LIS.LISfollowUps;

import java.util.Arrays;

/* Longest Bitonic Sequence

A Bitonic Sequence is a sequence of numbers that is first strictly increasing and then strictly decreasing.
A strictly ascending order sequence is also considered bitonic, with the decreasing part as empty, and same for a strictly descending order sequence.
For example, the sequences [1, 3, 5, 3, 2], [1, 2, 3, 4] are bitonic, whereas the sequences [5, 4, 1, 4, 5] and [1, 2, 2, 3] are not.
You are given an array 'arr' consisting of 'n' positive integers.
Find the length of the longest bitonic subsequence of 'arr'.
 */

public class LongestBitonicSequence {
    public static void main(String[] args) {
        int arr[] = {1, 2, 1, 2, 1}; // expected 3 

        System.out.println( longestBitonicSequence(arr, arr.length) );
    }

    /*
     * We have to create a dp[i] state such that the dp[i] represents the LIS from 0
     * to i th index + LDS(longest decreasing subsequence) from ith to n-1 index
     * We know how to find LIS , some how can we convert LIS to LDS
     * yes we can reverse of LDS is LIS ,
     * if we find LIS from back of the array then when we consider from right of the
     * array it would be the LDS
     * we will first find LIS and store it in dp1
     * then we will find LIS by traversing from the back of the array => (LDS)
     * then which index has max(LIS+LDS) is our answer
     * but that element at that index is common in the both the sequence but we have
     * to consider it only once so -1 to maxlength
     * 
     */
    private static int longestBitonicSequence(int[] arr, int n) {
        // Write your code here.
        int dp1[] = new int[n]; // ith index has LiS
        int dp2[] = new int[n]; // ith index has LDS

        // Base case
        Arrays.fill(dp1, 1); // At every index there will be minimum of length 1 LIS
        for (int index = 0; index < n; index++) {
            for (int reIndex = 0; reIndex <= index; reIndex++) {
                // We are comparing all elements from zero to current element
                // If they are less than current element increase length by
                // Update dp[index] only if the new length id greater than the old length
                // Then iterate over the dp array and find the max Element
                if (arr[reIndex] < arr[index] && dp1[index] < dp1[reIndex] + 1) {
                    dp1[index] = 1 + dp1[reIndex];
                }
            }
        }
        // LDS
        Arrays.fill(dp2, 1);
        for (int index = n - 1; index >= 0; index--) {
            for (int reIndex = n - 1; reIndex >= index; reIndex--) {
                // We are comparing all elements from zero to current element
                // If they are less than current element increase length by
                // Update dp[index] only if the new length id greater than the old length
                // Then iterate over the dp array and find the max Element
                if (arr[reIndex] < arr[index] && dp2[index] < dp2[reIndex] + 1) {
                    dp2[index] = 1 + dp2[reIndex];
                }
            }
        }

        int max = 1;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp1[i] + dp2[i], max);
        }

        return max - 1;
    }

}
