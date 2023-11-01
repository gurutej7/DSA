/* 1283. Find the Smallest Divisor Given a Threshold
Example 1:
Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will 
be 5 (1+1+1+2).
Each result of the division is rounded to the nearest integer greater than or equal to 
that element. (For example: 7/3 = 3 and 10/2 = 5).
   
 */

public class SmallestDivisor {
    public static void main(String[] args) {
        int [] nums = {1,2,5,9};
        int threshold = 6;
        System.out.println(smallestDivisor(nums, threshold));
        
    }
    public static int smallestDivisor(int[] nums, int threshold) {
        int low = 1 ;
        //AT max the divisor can be max Element in the Array
        int high = Max(nums);
        while(low<=high){
            int mid = low + (high-low)/2 ;
            /*Binary search the result.
            If the sum > threshold, the divisor is too small.
            If the sum <= threshold, the divisor is big enough.
            Complexity
            Time O(NlogM), where M = max(nums)
            Space O(1)
            */
            if(calcSum(nums,mid) <= threshold) high = mid-1;
            else  low = mid+1;
        }
        return low;
    }
    public static int calcSum(int[] nums , int divisor){
        int TotSum = 0;
        for(int i = 0 ; i<nums.length ; i++){
            //Math.ceil is used due to the given condition
            TotSum += Math.ceil((double)(nums[i])/(double)(divisor));
        }
        return TotSum;

    }
    public static int Max(int [] nums){
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i<nums.length ; i++){
            if(nums[i] > max) max = nums[i];
        }
        return max;
    }
}
