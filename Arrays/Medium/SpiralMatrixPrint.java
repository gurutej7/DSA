package Arrays.Medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixPrint {

    /*
     * 54. Spiral Matrix
     * 
     * { https://leetcode.com/problems/spiral-matrix/description/ }
     * 
     * Example : Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,2,3,6,9,8,7,4,5]
     */

    private static List<Integer> spiralOrder(int[][] matrix) {
        // Define res list to store the result.
        List<Integer> res = new ArrayList<>();

        int n = matrix.length; // no. of rows
        int m = matrix[0].length; // no. of columns

        // Initialize the pointers required for traversal.
        int top = 0, left = 0, bottom = n - 1, right = m - 1;

        // Loop until all elements are not traversed.
        while (top <= bottom && left <= right) {

            // For moving left to right
            for (int i = left; i <= right; i++)
                res.add(matrix[top][i]);

            top++;

            // For moving top to bottom.
            for (int i = top; i <= bottom; i++)
                res.add(matrix[i][right]);

            right--;

            // For moving right to left.
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    res.add(matrix[bottom][i]);

                bottom--;
            }

            // For moving bottom to top.
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    res.add(matrix[i][left]);

                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        System.out.println(spiralOrder(matrix));
    }

}
