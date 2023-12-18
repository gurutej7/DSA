package TwoPointers.Easy;
/* 167. Two Sum - II (Input Array is Sorted)
Example 1:
Input: numbers = [2,7,11,15], target = 9            Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

Example 2:
Input: numbers = [2,3,4], target = 6                Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].

The output is returned as one-based indexing
 */

import java.util.Arrays;

public class TwoSum2{
    public static void main(String[] args) {
        int [] a = {2,3,4};
        int k = 6;
        System.out.println(a);

       int ans [] = twoSum(a, k);

       // System.out.println(ans); 
       /*The println method in Java does not automatically convert an array to a string representation. If you try
        to print an array directly using System.out.println(a), it will print the hash code of the array object, 
        not its contents. To print the elements of the array, you should use a loop or Arrays.toString() method.*/

        System.out.println(Arrays.toString(ans));


    }

    public static int[] twoSum(int[] a, int k) {
        int ans [] = new int[2];
        int n = a.length , sum = 0 , left = 0 , right = n-1;
        
        while(left < right){
            sum = a[left]+a[right];
            if(sum == k){
                ans[0] = left+1;
                ans[1] = right+1;
                return ans;
            }
            else if(sum > k) right--;
            else left++;

        }
        return ans;
    }
}
/*
In an interview, whenever you're given a question where the input array is sorted, here are some super useful things to consider:

Binary Search
Two (or three) pointers
A sliding window
Traversing from the right
 */