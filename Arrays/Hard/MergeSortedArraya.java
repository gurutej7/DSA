package Arrays.Hard;

import java.util.Arrays;

public class MergeSortedArraya {
    /*
     * 88. Merge Sorted Array
     * 
     * { https://leetcode.com/problems/merge-sorted-array/description/ }
     * 
     * Example 1: Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     */

    // naive solution using extra space
    public void mergeBrute(int[] nums1, int m, int[] nums2, int n) {
        // make a copy of nums1
        int[] temp = new int[m];

        for (int i = 0; i < m; i++)
            temp[i] = nums1[i];

        // perform a typical merge sort
        int ind1 = 0, ind2 = 0, i = 0;
        while (ind1 < m && ind2 < n) {
            if (temp[ind1] <= nums2[ind2])
                nums1[i++] = temp[ind1++];
            else
                nums1[i++] = nums2[ind2++];
        }

        // merge if there are any remaining
        while (ind1 < m)
            nums1[i++] = temp[ind1++];
        while (ind2 < n)
            nums1[i++] = nums2[ind2++];

    }

    private static void mergeOptimal(int nums1[], int nums2[] , int m , int n) {
        int ptr1 = m - 1, ptr2 = n - 1, ptr3 = n + m - 1;

        while (ptr1 >= 0 && ptr2 >= 0) {
            // compare
            if (nums1[ptr1] >= nums2[ptr2]) {
                nums1[ptr3] = nums1[ptr1];
                ptr1--;
            } else {
                nums1[ptr3] = nums2[ptr2];
                ptr2--;
            }
            ptr3--;
        }

        while (ptr1 >= 0)
            nums1[ptr3--] = nums1[ptr1--];
        while (ptr2 >= 0)
            nums1[ptr3--] = nums2[ptr2--];
    }

    public static void main(String[] args) {
        int nums1[] = new int[] { 1, 2, 3, 0, 0, 0 };
        int nums2[] = new int[] { 2, 5, 6 };

        mergeOptimal(nums1, nums2, 3, 3);

        System.out.println(Arrays.toString(nums1)); // expected : [1, 2, 2, 3, 5, 6]
    }

}
