package Arrays.Hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MergeOverlappingIntervals {

    /*
     * 56. Merge Intervals
     * 
     * { https://leetcode.com/problems/merge-intervals/description/ }
     * 
     * Example 1: Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     */

    private static List<List<Integer>> merge(int[][] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        int start = arr[0][0];
        int end = arr[0][1];
        for (int[] currRow : arr) {
            // merge the overlapping interval and update the end
            if (currRow[0] <= end) {
                end = Math.max(currRow[1], end);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(start);
                temp.add(end);
                ans.add(temp);
                start = currRow[0];
                end = currRow[1];
            }
        }
        List<Integer> last = new ArrayList<>();
        last.add(start);
        last.add(end);
        ans.add(last);

        return ans;
        // int resSize = ans.size();
        // int res[][] = new int[resSize][2];

        // for (int i = 0; i < resSize; i++) {
        //      res[i][0] = ans.get(i).get(0);
        //      res[i][1] = ans.get(i).get(1);
        // }
        // return res;
    }

    public static void main(String[] args) {
        int arr[][] = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println(merge(arr));

    }

}
