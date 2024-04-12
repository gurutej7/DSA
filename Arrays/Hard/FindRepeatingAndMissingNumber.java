package Arrays.Hard;

import java.util.Arrays;

public class FindRepeatingAndMissingNumber {

    /* 2965. Find the repeating and missing values
     * 
     * { https://leetcode.com/problems/find-missing-and-repeated-values/description/ }
     * 
     * Example 1:   Input: grid = [[9,1,7],[8,9,2],[3,4,6]]
     * Output: [9,5]
     * Explanation: Number 9 is repeated and number 5 is missing so the answer is [9,5].
     */

    // approach using extra space
    private static int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] counts = new int[n * n + 1];
        int repeated = -1, missing = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                counts[grid[i][j]]++;
            }
        }

        for (int i = 1; i <= n * n; i++) {
            if (counts[i] == 0) {
                missing = i;
            } else if (counts[i] == 2) {
                repeated = i;
            }
        }

        return new int[] { repeated, missing };
    }

    // approach using math
    private static int[] findMissingAndRepeatedValuesMath(int[][] grid) {
        int N = grid.length;
        long n = 1L * N * N;
        long sum1 = (n * (n + 1) / 2); // sum 1 to n
        long squaresSum1 = ((n * (n + 1) * (2 * n + 1)) / 6); // sum of sqaures from 1 to n
        long repeated = -1, missing = -1;

        long sum2 = 0, squaresSum2 = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long val = 1L * grid[i][j];
                sum2 += val;
                squaresSum2 += val * val;
            }
        }

        /*
         * Example : [1 2 3 4 1 6]
         * => sum2 - sum1
         * => (1+2+3+4+1+6) - (1+2+3+4+5+6)
         * => 1 - 5 => repeating - missing => let x - y = -4 --- (i)
         */
        long eq1 = sum2 - sum1;
        /*
         * do the same for square as well
         * => squaresSum2 - sqauresSum1
         * => (1+4+9+16+36+1) - (1+4+9+16+25+36)
         * => 1 - 25 => x^2 - y^2 = -24 --- (ii)
         * => (x+y)(x-y) => x+y = -24/-4 (from eq -- (i))
         * => x+y = 6
         */
        long eq2 = squaresSum2 - squaresSum1;
        eq2 = (eq2 / eq1);

        /*
         * => adding eq --- (i) and (ii)
         * => 2x = 2 => x = 1 ;
         * => from --(i) y = x + 4
         */

        repeated = (eq1 + eq2) / 2; // x
        missing = (repeated - eq1); // y

        return new int[] { (int) repeated, (int) missing };
    }

    public static void main(String[] args) {
        int grid[][] = {{9,1,7},{8,9,2},{3,4,6}};
        System.out.println(Arrays.toString(findMissingAndRepeatedValuesMath(grid)));
        System.out.println(Arrays.toString(findMissingAndRepeatedValues(grid)));
        
    }
    
}
