package MonotonicStack;

import java.util.Stack;
import java.util.Arrays;

/* 503. Next Greater Element - 2
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next
greater number for every element in nums.
The ** next greater number ** of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this 
number.

Example :
Input: nums = [1,2,3,4,3]               Output: [2,3,4,-1,4]

*/


public class NextGreaterElement2{
    public static void main(String[] args) {
        int nums[] = {1,2,3,4,3};

        System.out.println(  Arrays.toString(  nextGreaterElementII(nums) )   );
    }
    // Same as Next Greater Element 1 but consider as array extended by the another same array.
    // For making the array circular we can create a new array and copy the original array twicw in the new Array
    // But for that we are using extra space to create the new Array .
    // Instead we can create a imaginary extended array by playing with the index
    // The elements in the extended part has  the  original array elements only
    // [i]th index  in the extended array has  [i%n] th index element from the original array
    public static int[] nextGreaterElementII(int []nums) {
         // Write your code here.
         int n = nums.length;
         int nge[] = new int[n];
         Stack < Integer > st = new Stack < > ();
         for (int i = 2 * n - 1; i >= 0; i--) {
             int currNum = nums[i % n] ;
              // We will remove previous elements in the stack which are lesser than the current element
             while (!st.isEmpty() && st.peek() <= currNum) {
                 st.pop();
             }
                // If stack is empty means there is no next greater element for current element
                 if (st.isEmpty()) nge[i % n] = -1;
                 // else the Top element will be the next Greater  element of current element
                 else nge[i % n] = st.peek();

             // Push the current element because in future it may become the next greater element for other element
             st.push(currNum);
         }
         return nge;
     
    }
}