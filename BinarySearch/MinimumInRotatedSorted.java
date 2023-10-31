/* 153. Find Minimum in Rotated Sorted Array

Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.

Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.
Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
 */
public class MinimumInRotatedSorted {
    public static void main(String[] args) {
        int [] nums = {3,4,5,1,2};
        System.out.println(findMin(nums));
    }
    // Naive Solution would be Linear Search the array and find Min
    // Better Solution is using Binary Search as the array is Sorted
    public static int findMin(int[] nums) {
        //Pick one half of the Array find Minimum in that half and Eliminate that half
        //Then check in the another half and update Min if necessary
        int ans = Integer.MAX_VALUE;
        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            //If the left half of the Array is Sorted then definitely nums[low] is the minimum value 
            if(nums[low] <= nums[mid]) {
                ans = Math.min(ans, nums[low]);
                low = mid+1;
            }
            else{
                ans = Math.min(ans,nums[mid]);
                high = mid-1;
            }
        }
        return ans;
    }
    

}
