/* 34. First and Last Position of a Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the starting and 
ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */

public class FirstAndLastOccurrence{
    public static void main(String[] args) {
        int [] nums = {5,7,7,8,8,10};
        int target = 4;

        int [] ans = new int [2];
        ans = searchRange(nums, target);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
            
        }

    }

    public static int[] searchRange(int[] nums, int target) {
        int f = first(nums,target);
        int l = last(nums,target);
        return new int[]{f,l};
    }

    private static int last(int[] nums, int target) {
        int l = -1;
        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = low +(high-low)/2;
            if(nums[mid]==target){
                l = mid;
                low = mid+1; // To find last occurence move Right 
            }
            else if(nums[mid]>target) high = mid-1;
            else low = mid+1;    
        }
        return l;
        
    }

    private static int first(int[] nums, int target) {
        int f = -1;
        int low = 0;
        int high = nums.length-1;
        while(low<=high){
            int mid = low +(high-low)/2;
            if(nums[mid]==target){
                f = mid;
                high = mid-1; // To find first occurence move Left 
            }
            else if(nums[mid]>target) high = mid-1;
            else low = mid+1;    
        }
        return f;
    }

}
