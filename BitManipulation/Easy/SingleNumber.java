package BitManipulation.Easy;

/* 136. Single Number
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
Example 1:      Input: nums = [2,2,1]           Output: 1
 */

public class SingleNumber {
    public static void main(String[] args) {
        int [] nums = {2,2,1};

        System.out.println(singleNumber(nums));
    }


    // Xor operator working
    // n^n = 0 ,  0^n = n  where n can be any number
    private static int singleNumber(int[] nums) {
        int ans = 0;
        for(int i : nums) ans^=i;
        return ans;
    }
    //There are other approaches that can be used
    //1. Use a Map to store the frequency of each element and return the element with frequency 1
    //2. Use set to store all the unique numbers and calulate sum of them = SUM => return 2*SUM - ArraySum
    //3. Sort and check adjacent elements
}
