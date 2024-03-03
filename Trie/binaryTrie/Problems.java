package Trie.binaryTrie;

import java.util.Arrays;


public class Problems {
   
    /* 421. Maximum Xor of two numbers in an Array
     * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
     * 
     * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
     * 
     * Example 1:   Input: nums = [3,10,5,25,2,8]   Output: 28
     * Explanation: The maximum result is 5 XOR 25 = 28.
     */
    private static int findMaximumXOR(int[] nums) {
        BinaryTrie root = new BinaryTrie();
        root.insertArrayOfInt(nums);

        int max = 0 ;
        for(int i : nums){
            max = Math.max(root.getMaxXor(i), max);
        }

        return max;
    }

    /* 2935. Maximum Strong Pair Xor II
     * https://leetcode.com/problems/maximum-strong-pair-xor-ii/description/
     * 
     * You are given a 0-indexed integer array nums. A pair of integers x and y is called a strong pair if it satisfies the condition:
     * |x - y| <= min(x, y)
     * You need to select two integers from nums such that they form a strong pair and their bitwise XOR is the  
     * maximum among all strong pairs in the array.
     * Return the maximum XOR value out of all possible strong pairs in the array nums.
     * Note that you can pick the same integer twice to form a pair.
     * 
     * Example 1:   Input: nums = [1,2,3,4,5]   Output: 7
     * Explanation: There are 11 strong pairs in the array nums: (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 3), 
     * (3, 4), (3, 5), (4, 4), (4, 5) and (5, 5).
     * The maximum XOR possible from these pairs is 3 XOR 4 = 7.
     */

     /*
      * In 421, we only care about the binary prefix of element A[i].
      * In this problem, we have one more condition that |x - y| <= min(x, y).
      * Assume x >= y,
      * then x - y <= y
      * then x <= y * 2. 
      */

    private static int maximumStrongPairXor(int[] nums) {
        int n = nums.length;
        int res = 0 ;
        Arrays.sort(nums);
        int right = 0 ; 
        BinaryTrie root = new BinaryTrie();
        for(int i = 0 ; i < n ; i++){
            // here the value at i is the min , assume x
            // then the condition |x-y| <= Min(x,y) will be satified till the curr(num) at right <= 2*x
            // Add those numbers which satisfy the condition into th trie
            int x = nums[i];
            while(right < n && nums[right] <= 2*x){
                root.insert(nums[right]);
                right++;
            }
            // Now the trie has all the values , which can form strong pair(|x-y| <= Min(x,y)) with x
            // Find the maxXor
            int currMaxXor = root.getMaxXor(x);
            res = Math.max(res,currMaxXor);
            root.delete(x);
            // Then the current x , because it cannot be valid to form a strong pair with any of the upcoming numbers , because the array is sorted 
        }
        return res;
    }

    /* 1707. Maximum Xor with an element from the array
     * https://leetcode.com/problems/maximum-xor-with-an-element-from-array/description/
     * 
     * Example 1:   Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]        
     * Output: [3,3,7]
     * Explanation:
     * 1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
     * 2) 1 XOR 2 = 3.
     * 3) 5 XOR 2 = 7.
     */

    private static int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        int n = nums.length;
        int res[] = new int[len];
        Arrays.sort(nums);

        BinaryTrie root = new BinaryTrie();
        
        int temp[][] = new int[len][3];
        for(int i = 0 ; i < len ; i++){
            temp[i][0] = queries[i][0];
            temp[i][1] = queries[i][1];
            temp[i][2] = i ;// save the index;; // can`t figure out this in my first attempt and did the brute forece and got TLE in 57th testcase (:
        }

        Arrays.sort(temp , (a,b)->(a[1]-b[1])); // sort based on limit
       
        int ind = 0 ; 
        for(int i = 0 ; i < len ; i++){
            int limit = temp[i][1];
            // System.out.println("limit " +limit);
            int x = temp[i][0];
            int ansIndex = temp[i][2] ; // instead of i , i put 0 here , spent 15 minutes trying to find it 
            while(ind < n && nums[ind] <= limit){
                // System.out.println("num " +nums[ind]);
                root.insert(nums[ind]);
                ind++;
            }
            int currXor = -1 ;

            if(ind != 0) {
                currXor = root.getMaxXor(x);
                // System.out.println("currXor " + currXor + "   ansIndex :" + ansIndex);
            }
            res[ansIndex] = currXor ; 
        }
        return res;
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,5};

        System.out.println(findMaximumXOR(nums));
        System.out.println(maximumStrongPairXor(nums));

        int nums2[] = {0,1,2,3,4};
        int queries[][] = {{3,1},{1,3},{5,6}};

        System.out.println(  Arrays.toString(maximizeXor(nums2, queries)) );
    }
}

