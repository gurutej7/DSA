/* 35. Search Insert Position
    Given a sorted array of distinct integers and a target value, return the index if the 
    target is found. If not, return the index where it would be if it were inserted in 
    order.
    You must write an algorithm with O(log n) runtime complexity.
    Example 1:
    Input: nums = [1,3,5,6], target = 5
    Output: 2

    Example 2:
    Input: nums = [1,3,5,6], target = 2
    Output: 1
 */

 //This question is Similar to Lower Bound 
 //We have to return the minimum index where nums[i]>= x (x=given number)

public class SearchInsertPos{
    public static void main(String[] args) {
        int arr [] = {1,3,5,6};
        int target = 4;

        System.out.println(searchInsert(arr, target));
        
    }

    public static int searchInsert(int[] arr, int target) {
         //low is the starting index and high is the ending index of the sorted array
         int low = 0;
         int high = arr.length-1;
         int idx = arr.length;
         while(low <= high){
             int mid = low + (high-low) / 2;
             //found
             if(arr[mid]>=target) {
                idx = mid;
                high = mid-1; // To get minimum move to left side
             }
             //mid is less than target means that target lies on the right half else left half
             else  low=mid+1;
 
         }
         return idx;
    }
}