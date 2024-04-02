package Arrays.Easy;

import java.util.ArrayList;

public class Easy1 {

    /*
     * 1752. Check if the array is sorted and rotated
     * { https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
     * description/ }
     * 
     * Example 1: Input: nums = [3,4,5,1,2] Output: true
     * Explanation: [1,2,3,4,5] is the original sorted array.
     * You can rotate the array by x = 3 positions to begin on the the element of
     * value 3: [3,4,5,1,2].
     * 
     * Example 2: Input: nums = [2,1,3,4] Output: false
     * Explanation: There is no sorted array once rotated that can make nums.
     */

    /*****
     * 
     * NonSequence = nums[i] > nums[i+1] i.e. number is greater than its right.
     * Find non sequences count.
     * -- In case of sorted array there will be 0 nonsequences.
     * [1,2,3,4,5]
     * -- In case of rotated sorted array there will be 1 nonsequence.
     * [3,4,5,1,2]
     * -- In other cases like [2,1,3,4] nonsequences > 1 ==> 2
     * 
     * How to count 4 > 2, in that case we need nums[i] > nums[(i+1)%len] , given
     * last and first are also connected
     * 
     *****/

    private static boolean check(int[] nums) {
        int n = nums.length;

        int nonSeq = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n])
                nonSeq++;
            if (nonSeq > 1)
                return false;
        }

        return true;
    }

    /*
     * 26. Remove Duplicates from the sorted array
     * { https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     * description/ }
     * 
     * Example 1: Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     * Explanation: Your function should return k = 5, with the first five elements
     * of nums being 0, 1, 2, 3, and 4 respectively.
     * It does not matter what you leave beyond the returned k (hence they are
     * underscores).
     */

    private static int removeDuplicates(int[] nums) {
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                left++;
                nums[left] = nums[right];
            }
        }

        return left + 1;
    }

    /*
     * 189. Rotate Array
     * { https://leetcode.com/problems/rotate-array/description/ }
     * 
     * Example 1: Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     */

    // Brute , store the first k elements in a data structure , then shift n-k
    // elements to front then put the stored elements at the back
    // Optimal => Observation Haki => Reverse (0 to k) , Reverse (n-k to n) ,Reverse (0 to n)
    // Play with the examples
    private static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverseArray(nums, 0, n - k - 1);
        reverseArray(nums, n - k, n - 1);
        reverseArray(nums, 0, n - 1);

    }

    private static void reverseArray(int arr[], int low, int high) {
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }

    /*
     * 283. Move Zeros
     * { https://leetcode.com/problems/move-zeroes/description/ }
     * 
     * Example 1: Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */

    private static void moveZeroes(int[] nums) {
        int ind = -1, ptr = -1, n = nums.length;
        // find the index of first zero
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                ind = i;
                ptr = i + 1;
                break;
            }
        }

        if (ind == -1)
            return; // there are no zeros

        while (ptr < n) {
            if (nums[ptr] != 0) {
                // swap
                nums[ind] = nums[ptr];
                nums[ptr] = 0;
                ind++;
            }
            ptr++;
        }
        // return nums;
    }

    /*
     * Union of two Sorted Arrays
     * {
     * https://www.geeksforgeeks.org/problems/union-of-two-sorted-arrays-1587115621/1 }
     * 
     * Input: n = 5, arr1[] = {1, 2, 3, 4, 5}
     * m = 3, arr2 [] = {1, 2, 3, 6, 7}
     * Output: 1 2 3 4 5 6 7
     * Explanation: Distinct elements including both the arrays are: 1 2 3 4 5 6 7
     */

    private static ArrayList<Integer> findUnion(int arr1[], int arr2[]) {
        // take the smaller element put it into the result
        int i = 0, j = 0, n = arr1.length, m = arr2.length;
        ArrayList<Integer> res = new ArrayList<>();

        while (i < n && j < m) {
            // if 1st array element is smaller
            if (arr1[i] <= arr2[j]) {
                if (res.size() == 0 || res.get(res.size() - 1) != arr1[i]) {
                    res.add(arr1[i]);
                }
                i++;
            } else {
                if (res.size() == 0 || res.get(res.size() - 1) != arr2[j]) {
                    res.add(arr2[j]);
                }
                j++;
            }
        }

        // we may have exhausted one of the arrays , and the other array may still have
        // elements left out
        while (i < n) {
            if (res.size() == 0 || res.get(res.size() - 1) != arr1[i]) {
                res.add(arr1[i]);
            }
            i++;
        }

        while (j < m) {
            if (res.size() == 0 || res.get(res.size() - 1) != arr2[j]) {
                res.add(arr2[j]);
            }
            j++;
        }
        return res;
    }

    public static void main(String[] args) {
        int nums1 [] = {1,2,3,4,4,4,5,6,7};
        System.out.println(check(nums1));
        System.out.println(removeDuplicates(nums1));
        rotate(nums1, 0);
        moveZeroes(nums1);
        int nums2 [] = {4,5,6,7,8};
        System.out.println(findUnion(nums1, nums2));
    }

}
