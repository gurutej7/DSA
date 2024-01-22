package DP.Subsequences;

/* 494. Target Sum
{ https://leetcode.com/problems/target-sum/description/ }

You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums 
and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the 
expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3                   Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

*/

public class TargetSum {
    public static void main(String[] args) {
        int nums [] = {1,1,1,1,1};
        int target = 3 ;

        System.out.println( findTargetSumWaysMemo(nums, target) );
    }

    private static int findTargetSumWaysMemo(int[] nums, int target) {
        int n = nums.length;
        // Observing the constraints is very important
        // Given the sum can be at max 1000 
        int[][] memo = new int[n][2001]; // 2001 is used as the range of possible sums
        // currSum => Range(-1000 to -1) is stored in memo indices range ( 0 to 999)
        // currSum => Range(0 to 1000) is stored in memo indices range (1000 to 2000)

        return fMemo(n - 1, 0, target, nums, memo);
    }

    private static int fMemo(int ind, int currSum, int target, int[] nums, int[][] memo) {
        // Base cases
        if (ind == 0) {
            if(nums[0] == 0 && currSum == target ) return 2; // We can add and sub , we can do both
            if (currSum + nums[0] == target) return 1;
            if (currSum - nums[0] == target) return 1;
            return 0;
        }
        //The current Running sum can be negative so we are using + 1000 to make it positive
        if (memo[ind][currSum + 1000] != 0) return memo[ind][currSum + 1000];

        // We have two choices
        // add the curr index
        int add = fMemo(ind - 1, currSum + nums[ind], target, nums, memo);
        // Subtract the current index
        int sub = fMemo(ind - 1, currSum - nums[ind], target, nums, memo);

        memo[ind][currSum + 1000] = add + sub;

        return memo[ind][currSum + 1000];
    }

    // Another approach 
    // Breaking down the problem into subset Sum problem
    /*The original problem statement is equivalent to:
    Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
    Let P be the positive subset and N be the negative subset
    For example:
    Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
    Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

    Then let's see how this can be converted to a subset sum problem:
                                    sum(P) - sum(N) = target
            sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                                2 * sum(P) = target + sum(nums)
    So the original problem has been converted to a subset sum problem as follows:
    Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
     */

    // Refer { https://leetcode.com/problems/target-sum/solutions/455024/dp-is-easy-5-steps-to-think-through-dp-questions/ }
     // https://leetcode.com/problems/target-sum/solutions/97335/short-java-dp-solution-with-explanation/

}
