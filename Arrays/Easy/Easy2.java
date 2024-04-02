package Arrays.Easy;

public class Easy2 {
    /*
     * 268. Missing Number
     * { https://leetcode.com/problems/missing-number/description/ }
     * 
     * Input: nums = [3,0,1]    Output: 2
     * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range
     * [0,3]. 2 is the
     * missing number in the range since it does not appear in nums.
     */
    private static int missingNumber(int[] nums) {
        // Math -> Sum of N natural numbers N(N+1)/2-Sum(nums) = missingNumber
        int sum = 0, n = nums.length;
        for (int i : nums)
            sum += i;
        return (n * (n + 1) / 2) - sum;

    }

    /*
     * 485. Max Consecutive Ones
     * { https://leetcode.com/problems/max-consecutive-ones/description/ }
     * 
     * Input: nums = [1,1,0,1,1,1] Output: 3
     * Explanation: The first two digits or the last three digits are consecutive
     * 1s. The maximum number of consecutive 1s is 3.
     */

    private static int findMaxConsecutiveOnes(int[] nums) {
        int cnt = 0, ans = 0;
        for (int i : nums) {
            if (i == 1) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        ans = Math.max(ans, cnt);
        return ans;
    }

    /*
     * 136. Single Number
     * { https://leetcode.com/problems/single-number/description/ }
     * 
     * Input: nums = [4,1,2,1,2]    Output: 4
     */

    private static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i : nums)
            ans ^= i;
        return ans;
    }

    public static void main(String[] args) {
        int nums[] = new int[] { 3, 0, 1 };
        System.out.println(missingNumber(nums));
        nums = new int[] { 1, 1, 0, 1, 1, 1 };
        System.out.println(findMaxConsecutiveOnes(nums));
        nums = new int[] { 4, 1, 2, 1, 2 };
        System.out.println(singleNumber(nums));
    }

}
