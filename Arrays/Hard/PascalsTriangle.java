package Arrays.Hard;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    /*
     * 118. Pascal`s Triangle
     * 
     * { https://leetcode.com/problems/pascals-triangle/description/ }
     * 
     * Example 1:
     * 
     * Input: numRows = 5
     * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     */

    // 1st variation Given R and C , return the value at Rth row and Cth column

    // nCr = n!/r!*(n-r)!

    private static long calculateNcR(int n, int r) {
        long res = 1;
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }

        return res;
    }

    // 2nd variation Print any Nth row of the pascal`s triangle
    private static List<Integer> printNthRow(int n) {
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            ans = ans * (n - i);
            ans = ans / i;
            temp.add(ans);
        }
        return temp;
    }

    // 3rd variation Given N print the entire Triangle
    private static List<List<Integer>> generate(int n) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            ans.add(printNthRow(i + 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(calculateNcR(3, 1));
        System.out.println(generate(5));
    }

}
