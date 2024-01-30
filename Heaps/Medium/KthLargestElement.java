package Heaps.Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/* 215. Kth Largest Element in an Array
{ https://leetcode.com/problems/kth-largest-element-in-an-array/description/ }

Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?

Example 1:  Input: nums = [3,2,1,5,6,4], k = 2          Output: 5
Example 2:  Input: nums = [3,2,3,1,2,4,5,5,6], k = 4    Output: 4

 */

public class KthLargestElement {
    public static void main(String[] args) {
        int nums[] = {3,2,1,5,6,4};
        int k = 2;

        System.out.println( findKthLargest1(nums, k) );
        System.out.println( findKthLargest2(nums, k) );
        System.out.println( findKthLargest3(nums, k) );
        System.out.println( findKthLargest4(nums, k) );
        
    }
    // Naive Approach O(NlogN) time and O(1) space
    private static  int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    // Using heap  O(NlogK) time and O(k) space
    private static int findKthLargest2(int[] nums , int k){
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);
            // remove element when pq size is greater than k
            // that is the smallest element is removed
            // by the end n-k smallest elements are remove , which is equivalent to k largest elements
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // O(N) average case , O(N^2) , O(1) Memory => worst case quick select  => used in quick sort algorithm
    // refer { https://en.wikipedia.org/wiki/Quickselect }
    // recursive approach O(N) stack space
    private static int findKthLargest3(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
      }
      
    private static int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = low;
      
        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        for (int j = low; j < high; j++) {
          if (nums[j] <= nums[high]) {
            swap(nums, pivot++, j);
          }
        }
        swap(nums, pivot, high);
        
        // count the nums that are > pivot from high
        int count = high - pivot + 1;
        // pivot is the one!
        if (count == k) return nums[pivot];
        // pivot is too small, so it must be on the right
        if (count > k) return quickSelect(nums, pivot + 1, high, k);
        // pivot is too big, so it must be on the left
        return quickSelect(nums, low, pivot - 1, k - count);
    }

    private static void swap(int [] arr , int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Iterative version
    private static int findKthLargest4(int[] A, int k) {
        k = A.length - k; // convert to index of k largest
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int i = l; // partition [l,r] by A[l]: [l,i]<A[l], [i+1,j)>=A[l]
            for (int j = l + 1; j <= r; j++)
                if (A[j] < A[l]) swap(A, j, ++i);
            swap(A, l, i);

            if (k < i) r = i - 1;
            else if (k > i) l = i + 1;
            else return A[i];
        }
        return -1; // k is invalid
    }

    // 3 and 4 approaches will give TLE because of O(N^2) , when the pivot is always the first element

}
