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

        System.out.println( countPartitionsTabu(nums.length, target, nums) );
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
    // Breaking down the problem into count partition with given difference problem
    /*The original problem statement is equivalent to:
    Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
    Let P be the positive subset and N be the negative subset
    For example:
    Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
    Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

    Then let's see how this can be converted to a subset sum problem:
                                    sum(P) - sum(N) = target => Same as count partition with given difference
                                    S1 - S2 = diff
     */

   // I will copy paste the count partition with given diff problem

    private static int countPartitionsTabu(int n , int diff , int arr[]){
        if(diff < 0) diff = -1*diff; // Extra line because the given target can be negative 
        int totalSum =  0; 
        for(int i : arr) totalSum += i;

        // Some checks according to question
        if( totalSum < diff) return 0 ;

        if( (totalSum+diff) %2 != 0) return 0; // If it is odd on dividing by 2 it will result in decimal

        int target = (totalSum+diff)/2 ;

        int dp[][] = new int[n][target+1];

        // Write down the base cases
        if(arr[0] == 0) dp[0][0] = 2; // pick and not pick both are valid
        else dp[0][0] = 1 ; // Target = 0  we donot pick 0th index

        if(arr[0] != 0 && arr[0] <= target) dp[0][arr[0]] = 1; // base case where ind == 0 && target == arr[0]  , we will pick it

        

        // Write for loops for the changing parameters

        for(int ind = 1 ; ind<n ; ind++){
            for(int currSum = 0 ; currSum <= target ; currSum++){
                // Copy the recurrence
                int notPick = dp[ind-1][currSum];
                int pick =  0 ;
                if(arr[ind] <= currSum) pick = dp[ind-1][currSum - arr[ind]] ;

                dp[ind][currSum] = (pick+notPick);
            }
        }

        return dp[n-1][target];
    }
}
