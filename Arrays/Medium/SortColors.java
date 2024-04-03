package Arrays.Medium;

import java.util.Arrays;

public class SortColors {
    /* 75. Sort Colors
     * { https://leetcode.com/problems/sort-colors/description/ }
     * 
     * Example 1:       Input: nums = [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     */

     // Dutch National Flag Algorithm
     // https://users.monash.edu/~lloyd/tildeAlgDS/Sort/Flag/

    private static void sortColors(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, mid = 0;

        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left, mid);
                mid++;
                left++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, right);
                right--;
            } else
                mid++;
        }
    }

    private static void swap(int nums[], int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    public static void main(String[] args) {
        int nums [] = new int[]{2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
    
}
