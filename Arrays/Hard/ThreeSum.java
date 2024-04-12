package Arrays.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

    /*
     * 15. 3 Sum
     * 
     * { https://leetcode.com/problems/3sum/description/ }
     * 
     * Example 1: Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     * Explanation:
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
     * The distinct triplets are [-1,0,1] and [-1,-1,2].
     * Notice that the order of the output and the order of the triplets does not
     * matter.
     */

    public static List<List<Integer>> threeSumBruteForce(int[] a) {
        int n = a.length, sum = 0; // TC = O(nlogn + n^3) ~= O(n^3)
        Arrays.sort(a);
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    sum = a[i] + a[j] + a[k];
                    if (sum == 0) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(a[i]);
                        temp.add(a[j]);
                        temp.add(a[k]);
                        Collections.sort(temp);
                        // To return unique pairs we are using set
                        set.add(temp);
                    }
                }
            }
        }
        return new ArrayList<>(set);

    }

    /*
     * So, we essentially need to find three numbers x, y, and z such that they add
     * up to the given value.
     * If we fix one of the numbers say x, we are left with the two-sum problem at
     * hand!
     */
    public static List<List<Integer>> threeSumBetter(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;// left pointer
            int r = nums.length - 1; // right pointer
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0)
                    res.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                else if (sum > 0)
                    r--; // If current sum is grester than zero then decrease the larger value else vice
                         // versa
                else if (sum < 0)
                    l++;
            }

        }
        return new ArrayList<>(res); // TC = O(n^2) and SC = O(n)

    }

    public static List<List<Integer>> threeSum(int[] a) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(a);
        int n = a.length, left, right, sum = 0;
        for (int i = 0; i < n - 2; i++) {
            // We can eliminate the usage of set by including some checks to avoid
            // duplicates
            if (i > 0 && a[i] == a[i - 1])
                // skip same result
                continue;

            left = i + 1;
            right = n - 1;
            while (left < right) {
                sum = a[left] + a[right] + a[i];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(a[i]);
                    temp.add(a[left]);
                    temp.add(a[right]);
                    ans.add(temp);
                    while (left < right && a[left] == temp.get(1))
                        left++; // skip the same value at left
                    while (left < right && a[right] == temp.get(2))
                        right--;// skip the same value at right
                } else if (sum > 0)
                    right--;
                else
                    left++;

            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] a = { -1, 0, 1, 2, -1, -4 };

        System.out.println(threeSum(a));

        System.out.println(threeSumBruteForce(a));

        System.out.println(threeSumBetter(a));
    }

}
