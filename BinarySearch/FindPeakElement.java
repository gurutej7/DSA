/* 162. Find Peak Element
A peak element is an element that is strictly greater than its neighbors.
Given a 0-indexed integer array nums, find a peak element, and return its index. If the 
array contains multiple peaks, return the index to any of the peaks.
Example 1:
Input: nums = [1,2,3,1]
Output: 2
Example 2:
Input: nums = [1,2,1,3,5,6,4]
Output: 5
*/
public class FindPeakElement {
    public static void main(String[] args) {
        int [] nums = {1,2,3,1};
        System.out.println(findPeakElement(nums));

    }
    public static int findPeakElement(int[] nums) {
/*
 * We can do simple binary search that check [mid] point and [mid+1] point and take half 
 * which has higher value, i.e. take left half if nums[mid]>nums[mid+1].
 * Dry Run Once and check why this works
 */
        if(nums.length == 1) return 0;
        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            
            if(mid < nums.length-1 && nums[mid] < nums[mid+1]) low = mid+1;
            else high = mid-1;
        }
        return low;
    }
    
}
