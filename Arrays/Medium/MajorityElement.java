package Arrays.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElement {

    /* 169. Majority Element
     * { https://leetcode.com/problems/majority-element/description/ }
     * 
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     * Example 1:   Input: nums = [2,2,1,1,1,2,2]
     * Output: 2
     */

    private static int majorityElement(int[] nums) {
        // Moore`s voting algorithm
        int hero = 0, cnt = 0;

        for (int num : nums) {
            if (cnt == 0)
                hero = num;

            if (num == hero)
                cnt++;
            else
                cnt--;
        }

        return hero;

        // check if the hero is the majority element or not
        // in this case not necessary because it is mentioned that majority element always exists
        // cnt = 0;
        // for (int num : nums) {
        //     if (num == hero)
        //         cnt++;
        // }

        // return cnt > n / 2 ? hero : -1;
    }

    private static int majorityElementBetter(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        for (int num : map.keySet()) {
            if (map.get(num) > n / 2)
                return num;
        }

        return -1;
    }

    /* 229. Majority Element - 2
     * { https://leetcode.com/problems/majority-element-ii/description/ }
     * 
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     * Example 1:       Input: nums = [3,2,3]
     * Output: [3]
     */

    private static List<Integer> majorityElement2(int[] nums) {
        int n = nums.length;
        int hero1 = Integer.MAX_VALUE, hero2 = Integer.MAX_VALUE;
        int cnt1 = 0, cnt2 = 0;

        for (int num : nums) {
            if (cnt1 == 0 && num != hero2) {
                cnt1++;
                hero1 = num;
            } else if (cnt2 == 0 && num != hero1) {
                cnt2++;
                hero2 = num;
            } else if (num == hero1)
                cnt1++;
            else if (num == hero2)
                cnt2++;
            else {
                cnt1--;
                cnt2--;
            }
        }

        List<Integer> res = new ArrayList<>();
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == hero1)
                cnt1++;
            if (num == hero2)
                cnt2++;
        }

        if (cnt1 > n / 3)
            res.add(hero1);
        if (cnt2 > n / 3)
            res.add(hero2);

        return res;
    }
    public static void main(String[] args) {
        int nums[] = new int[] {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
        System.out.println(majorityElementBetter(nums));
        nums = new int[]{3,2,3};
        System.out.println(majorityElement2(nums));
    }
    
}
