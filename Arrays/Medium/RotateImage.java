package Arrays.Medium;

import java.util.Arrays;

public class RotateImage {

  /*
   * 48. Rotate Image
   *
   * { https://leetcode.com/problems/rotate-image/description/ }
   *
   */

  private static void rotate(int[][] matrix) {
    int n = matrix.length;
    // Find the transpose of the matrix
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) { // j starts from i + 1 to avoid unnecessary swaps
        // swap
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }

    // reverse the rows
    for (int i = 0; i < n; i++) {
      int left = 0, right = n - 1;
      while (left < right) {
        // swap
        int temp = matrix[i][left];
        matrix[i][left] = matrix[i][right];
        matrix[i][right] = temp;
        left++;
        right--;
      }
    }
  }

  public static void main(String[] args) {
    int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    rotate(matrix);
    for (int[] row : matrix)
      System.out.println(Arrays.toString(row));
  }
}
