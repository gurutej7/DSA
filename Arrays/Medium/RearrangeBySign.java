package Arrays.Medium;

import java.util.Arrays;

public class RearrangeBySign {
    /* 2149. ReArrange Array Elements by sign
     * { https://leetcode.com/problems/rearrange-array-elements-by-sign/description/ }
     * 
     * Example 1:   Input: nums = [3,1,-2,-5,2,-4]
     * Output: [3,-2,1,-5,2,-4]
     */

    private static int[] rearrangeArray(int[] nums) {
        // keep track of the positive and negative indices
        int pos = 0, neg = 1, n = nums.length;
        int res[] = new int[n];

        for (int v : nums) {
            if (v < 0) {
                res[neg] = v;
                neg += 2;
            } else {
                res[pos] = v;
                pos += 2;
            }
        }

        return res;
    }

    // second variation , where there may not be equal number of pos and neg
    // in that case just brute force
    public static void main(String[] args) {
        int nums[] = {3,1,-2,-5,2,-4};
        
        System.out.println(Arrays.toString(rearrangeArray(nums)));
    }
    
}
