/*
 81. Search in Rotated Sorted Array - 2  (not necessarily with distinct values).

Given the array nums after the rotation and an integer target, return true if target is 
in nums, or false if it is not in nums.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 */
public class SearchInRotatedSortedTwo {
    public static void main(String[] args) {
        int [] nums ={2,5,6,0,0,1,2};
        System.out.println(search(nums, 0));
    }
    //Brute force Solution will be a Linear Search
    //Optimal Solution will be using Binary Search
    public static boolean search(int[] nums, int target) {
        //This Problem is similar as the previous problem
        //But due to the duplicates are allowed , Some edges should be taken care
        int low =0 ;
        int high = nums.length-1;
        while(low<=high){
            int mid = low + (high-low)/2 ;
            if(nums[mid]==target) return true;
            /*Ex: nums = [1,0,1,1,1] target = 0 (previous Solution will give "false" for
             this Example i.e, wrong answer). To avoid duplicates we can increment low and
              decrement high.
             Dry Run and Check for better understanding
             Rest of the solution is same as previous problem
            */
            else if(nums[low] == nums[mid] && nums[mid] == nums[high]){
                low++;
                high--;
            }
            else if(nums[low] <= nums[mid]){
                if(target >= nums[low] && target <= nums[mid])
                high = mid-1;
                else low = mid+1;
            }
            else{
                if(target >= nums[mid] && target <= nums[high])
                low = mid+1;
                else high = mid-1;
            }
        }
        return false;
    }
    
}
