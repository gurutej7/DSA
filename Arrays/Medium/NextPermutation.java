package Arrays.Medium;

import java.util.Arrays;

public class NextPermutation {

  /* 31. Next Permutation
   * { https://leetcode.com/problems/next-permutation/description/ }
   *
   * Example 1:     Input: nums = [1,2,3]
   * Output: [1,3,2]
   */

  private static void nextPermutation(int[] nums) {
    int n = nums.length;
    // iterate from the left where the increasing order from left breaks , get that index
    int ind = -1;

    for (int i = n - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        ind = i;
        break;
      }
    }
    // if we were not able to break , then it is the last permutation reverse the array and return
    if (ind == -1) {
      reverseArray(nums, 0, n - 1);
      return;
    }

    // find the just next greater element of the breakpoint on the left and swap
    for (int i = n - 1; i >= ind; i--) {
      if (nums[i] > nums[ind]) {
        swap(nums, i, ind);
        break;
      }
    }

    // sort the part of the array after the break point , which can be achieved by reversing it , in this case
    reverseArray(nums, ind + 1, n - 1);
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static void reverseArray(int arr[], int low, int high) {
    // int n = arr.length;
    int left = low, right = high;

    while (left <= right) {
      swap(arr, left, right);

      right--;
      left++;
    }
  }

  public static void main(String[] args) {
    int nums[] = new int[] { 1, 2, 3 };
    nextPermutation(nums);
    System.out.println(Arrays.toString(nums));
  }
}
