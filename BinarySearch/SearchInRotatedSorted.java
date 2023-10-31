/*
33. Search In Rotated Sorted Array  (with distinct values).

There is an integer array nums sorted in ascending order


Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.
Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
public class SearchInRotatedSorted{
    public static void main(String[] args) {
        int [] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }
    //Bruteforce solution will be using a Linear Search  O(n)
    
    //Optimal Solution using Binary Search
    public static int search(int[] nums, int target) {
        int low =0 ;
        int high = nums.length-1;
        while(low<=high){
            int mid = low + (high-low)/2 ;
            //If the target is found
            if(nums[mid]==target) return mid;
            //check If the Left half is Sorted or not
            if(nums[low] <= nums[mid]){
                //Check whether the target lies in the range or not
                if(target >= nums[low] && target <= nums[mid])
                //If it lies then Eliminate the right half and vice versa
                high = mid-1;
                else low = mid+1;
            }
            //If the left half is not sorted, check in the Right half
            else{
                //If the target lies in the right half then Eliminate the left half and vice versa
                if(target >= nums[mid] && target <= nums[high])
                low = mid+1;
                else high = mid-1;
            }
        }
        return -1;
    }
}