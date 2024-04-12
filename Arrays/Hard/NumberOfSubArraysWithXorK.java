package Arrays.Hard;

import java.util.HashMap;

public class NumberOfSubArraysWithXorK {

    /*
     * Example: Input: a = [1, 2, 3, 2] , k = 2
     * Output: 3
     * Explanation: Subarrays have bitwise xor equal to ‘2’ are: [1, 2, 3, 2], [2], [2].
     */

    private static int subarraysWithSumK(int[] a, int k) {
        int preXor = 0, cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int i : a) {
            preXor ^= i;
            // required
            int req = preXor ^ k; // req^k = preXor => req = preXor^k

            if (map.containsKey(req))
                cnt += map.get(req);

            map.put(preXor, map.getOrDefault(preXor, 0) + 1);

        }

        return cnt;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 2 };
        System.out.println(subarraysWithSumK(arr, 2));
    }

}
