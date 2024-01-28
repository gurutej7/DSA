package DP.LIS;

import java.util.ArrayList;
import java.util.Arrays;

/* 300. Longest Increasing Subsequence
{ https://leetcode.com/problems/longest-increasing-subsequence/description/ } 

Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:      Input: nums = [10,9,2,5,3,7,101,18]             Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.  
*/

public class LIS{
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println( lISbinarySearh(nums) );
        System.out.println( lengthOfLIS(nums) );
        System.out.println( lengthOfLIS2(nums) );
        System.out.println( lengthOfLISTabu(nums) );
        
    }

    // We can generate all subsequences while checking the condition of increasing
    private static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int dp[][] = new int [n+1][n+1];
        // At every index
        for(int row[] : dp) Arrays.fill(row,-1);

        // int prevIndex = -1; cannot have -1 index in a array 
        // so i will consider one based indexing
        // If we are considering a element to increasing sequence then it should be greater than the last element in the sequence
        // If previous element index is 0 then current element is the 1st elementt in the sequence
        return fMemo(1,0,nums,dp);
   
}

    private static int fMemo(int index , int prevIndex , int [] nums , int[][] dp){
        //  Base case
        if(index > nums.length) return 0;

        if(dp[index][prevIndex] != -1) return dp[index][prevIndex];

        // Take  we can only take if the current element is greater than the previous element in the sequence
        // Then we will update the prev index as current index , because for new coming element it will be the previouse element with which it has to check the condition
        int take = 0 ,  notTake =  0;
        if(prevIndex == 0 || nums[index-1] > nums[prevIndex-1]){
            take = 1 + fMemo(index+1,index,nums,dp);
        }

        notTake = fMemo(index+1,prevIndex,nums,dp);

        dp[index][prevIndex] = Math.max(take,notTake);

        return dp[index][prevIndex];
    }

    private static int lengthOfLISTabu(int nums[]){
        int n = nums.length;
        int dp[][] = new int[n+2][n+1];
        // Write down the base cases
        for(int prevIndex = 0 ; prevIndex <= n ; prevIndex++) dp[n+1][0] = 0 ;

        // Write for loops for the changing parameters in opposite fashion
        for(int index = n ; index >= 1 ; index--){
            for(int prevIndex = 0 ; prevIndex <= n ; prevIndex++){
                // Copy the recurrence
                int take = 0 ,  notTake =  0;
                if(prevIndex == 0 || nums[index-1] > nums[prevIndex-1]){
                    take = 1 + dp[index+1][index];               // fMemo(index+1,index,nums,dp);
                }

                notTake = 0 + dp[index+1][prevIndex] ;       //fMemo(index+1,prevIndex,nums,dp);

                dp[index][prevIndex] = Math.max(take,notTake);              
            }
        }

        return dp[1][0];
    }

    // The above approaches are of O(N^2) and space of O(n*n)


    // To decrease space we can use a 1 D array
    // Where dp[i] signifies => maxLength of LIS till ith index
    private static int lengthOfLIS2(int nums[]){
        int n = nums.length;
        int dp[] = new int[n];

        // Base case
        Arrays.fill(dp,1) ; // At every index there will be minimum of length 1 LIS
        int maxLength = 1;
        for(int index = 0 ; index < n ; index++){
            for(int reIndex = 0 ; reIndex <= index ; reIndex++){
                // We are comparing all elements from zero to current element
                // If they are less than current element increase length by 
                // Update dp[index] only if the new length id greater than the old length
                // Then iterate over the dp array and find the max Element
                if(nums[reIndex] < nums[index] && dp[index] < dp[reIndex]+1){
                    dp[index] = 1 + dp[reIndex];
                    if(dp[index] > maxLength) maxLength = dp[index];
                }
            }
        }

        return maxLength;
    }

    // We have decreased space but we can still decrease time complexity
    // This approach needs some high level intuition or already should have learnt about it
    // Uses binary search   
    // { https://www.youtube.com/watch?v=on2hvxBXJH4&t=22s }  refer 
    //  O(NlogN)  , we can`t print LIS with this approach but only we can get the length

    private static int lISbinarySearh(int [] nums){
        int n = nums.length;

        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(nums[0]);

        int len = 1 ;

        for(int i = 1 ; i < n ; i++){
            if(arr.get(arr.size()-1) < nums[i]) {
                arr.add(nums[i]);
                len++;
            }
            else{
                int ind = lowerBoundBinary(arr, len+1, nums[i]);
                arr.set(ind,nums[i]);
            }
        }

        return len;
    }

    public static int lowerBoundBinary(ArrayList<Integer> arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        int ans = n-1;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr.get(mid) >= x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {//curr mid value is less than x move to right
                low = mid + 1;
            }
        }
        return ans;

	}
}