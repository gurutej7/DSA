package Arrays.Hard;

public class MaximumProductSubArray {

    /* 152. Maximum Product SubArray
     * 
     * { https://leetcode.com/problems/maximum-product-subarray/description/ }
     * 
     * Example 1:   Input: nums = [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     */

     private static int maxProduct(int[] nums) {

        /*
         * The Approach to this question is exactly same as Kadane's Algo the only
         * differece is, we will be traversing the array from both sides i.e left to
         * right ( lets say the max prod is maxi1 ) and from right to left ( say max
         * prod is maxi2 ), now the final answer will we max of maxi1 and maxi2.
         * 
         * Ques : Why do we have a need to traverse from right to left ??
         * Ans : lets take an example
         * arr {-8,5,3,1,6}
         * 
         * // By traversing left to right we have an answer maxi1.
         * maxi1 = -720 , which is clearly not the max prod value.
         * -> The whole concept lies in this statement - As we can see that there is
         * only 1 negative no. ( if we consider that no. then the final answer can never
         * be positive ), therefore to avoid this situation we also traverse from right
         * to left to get the final maximum prod.
         */
        int prefixProd = 1, suffixProd = 1;
        int n = nums.length;
        int res = nums[0]; // if there is only one element with negative value
        for (int i = 0; i < n; i++) {
            prefixProd = prefixProd == 0 ? 1 : prefixProd;
            suffixProd = suffixProd == 0 ? 1 : suffixProd;

            prefixProd *= nums[i];
            suffixProd *= nums[n - i - 1];

            res = Math.max(res, Math.max(prefixProd, suffixProd));

        }
        return res;
    } 
    public static void main(String[] args) {
        int nums[] = {-8,5,3,1,6};

        System.out.println(maxProduct(nums));
    }
    
}
