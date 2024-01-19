package DP.OneD;

/* 213. House Robber - 2
Same as House Robber - 1 , But 0th and n-1th houses are adjacent , we cannot rob adjacent houses

Example 1:      Input: nums = [1,2,3,1]                 Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

link { https://leetcode.com/problems/house-robber-ii/description/ }

*/

public class HouseRobberTwo {
    public static void main(String[] args) {
        int nums[] = {1,2,3,1};

        System.out.println(rob(nums));
    }

    /* We need to use more observation Haki
    Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes to rob 
    either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers more money. Now the problem 
    has degenerated to the House Robber, which is already been solved.
     */
    // I haven`t written the space optimized code in house robber 1 
    // Below one is the space optimized one
    public static int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0],nums[1]);
        

        return Math.max(robHelper(nums,0,n-2) , robHelper(nums,1,n-1));
    }
// Whennever we have i-2 or i-1 we can definitely space optimize it , by using variables to keep track of values
// For Example [1,2,3,1]
//             [  .       .        .        .   ] => dots represent a dp array
// prev = 0     prev                                i = 0 
//              prev2   prev                        i = 1
//                      prev2   prev                i = 2
//                              prev2      prev     i = 3

    public static int robHelper(int[] nums , int start , int high){
        int prev2 = nums[start];
        int prev = 0 ;
        for(int i = start+1 ; i <=high ; i++){
            int pick = nums[i]; // Pick has the current element
            if(i-2 >=0) pick += prev;// If we add the maxsum till last picked   => dp[i-2] = prev
            int notPick = prev2 ; // The max Sum till last element without current element   => dp[i-1] = prev2
            int curri = Math.max(pick ,notPick); // dp[i] = curri
            prev = prev2 ;  // For next state
            prev2 = curri ;
        }
        return prev2;
    }
    // Refer House Robber- 1 for => Memoization and Tabulation
}
