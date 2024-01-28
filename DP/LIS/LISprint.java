package DP.LIS;

import java.util.ArrayList;
import java.util.Arrays;

public class LISprint {
    public static void main(String[] args) {
        int nums[] = {0,1,0,3,2,3};  // expected 0 , 1 , 2 , 3

        System.out.println( lengthOfLIS2(nums) );

    }

    // To print the LIS , we have to know the path from where the LIS is coming from
    /*
     * If we consider the Tabulation code from LIS.java => There every index in the
     * dp has the length of LIS till that index
     * We also update the index when we encounter a element less than the curr in
     * the previous values
     * that is for i => previous elements are from 0 - i-1 ;
     * At some index we will have the max Length
     * but the value would have updated when compared to some previous value , we
     * have to know the index of the previous value
     * If dp[i] = maxLength , the track[i] = will have index of the element to which
     * the currennt element is grester to give maxLength
     * and that element will also have track[i] point to another previous element of
     * it`s own
     * Kind of updating the path when updating the length
     */

    // Copy the Tabulation code and do the changes

    private static ArrayList<Integer> lengthOfLIS2(int nums[]) {
        int n = nums.length;
        int dp[] = new int[n];
        int path[] = new int[n+1]; // Ther can be a lis of len N
        for(int i = 0 ; i<= n ; i++) path[i] = i ;
        ArrayList<Integer> lis = new ArrayList<>();


        // Base case
        Arrays.fill(dp, 1); // At every index there will be minimum of length 1 LIS
        int maxLength = 1;
        int maxLengthIndex = 0 ;
        for (int index = 0; index < n; index++) {
            for (int reIndex = 0; reIndex <= index; reIndex++) {
                // We are comparing all elements from zero to current element
                // If they are less than current element increase length by
                // Update dp[index] only if the new length id greater than the old length
                // Then iterate over the dp array and find the max Element
                if (nums[reIndex] < nums[index] && dp[index] < dp[reIndex] + 1) {
                    dp[index] = 1 + dp[reIndex];
                    path[index] = reIndex;
                    if (dp[index] > maxLength)
                        maxLength = dp[index];
                        maxLengthIndex = index;

                }
            }
        }

        
        lis.add(nums[maxLengthIndex]);
        int prev = path[maxLengthIndex];
        while(lis.size() < maxLength){
            lis.add(nums[prev]);
            prev = path[prev];
        }

        
        // If the order of elements is necessary , then sort and return 
        return lis;
    }

}
