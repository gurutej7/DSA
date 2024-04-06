package Arrays.Medium;

import java.util.Arrays;

public class SetMatrixZeros {

  /* 73. Set Matrix Zeros
   *
   * { https://leetcode.com/problems/set-matrix-zeroes/description/ }
   *
   * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
   * You must do it in place.
   */

  private static void setZeroes(int[][] matrix) {
    int n = matrix.length, m = matrix[0].length;
    // we are using the first row and column as a memory to keep track of all the 0's in the entire matrix.

    boolean isFirstRow = false, isFirstCol = false;

    // check first col
    for (int i = 0; i < n; i++) {
      if (matrix[i][0] == 0) {
        isFirstCol = true;
        break;
      }
    }

    // check first row
    for (int j = 0; j < m; j++) {
      if (matrix[0][j] == 0) {
        isFirstRow = true;
        break;
      }
    }

    // process the matrix and store the respective markings in the first row and column
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // with the use of markings, reset the values in the matrix
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
      }
    }

    /*
     * suppose you find 0 in fist row or column then you will end up changing all values to 0 in first row and first column,
     * this will end up in all the subsequent operations checking top column/row referring those values and all cell values will change to 0
     * so that's why we are starting from index 1 while changing values and in the last we are changing first row/col values.
     */

    if (isFirstRow) {
      for (int j = 0; j < m; j++) matrix[0][j] = 0;
    }

    if (isFirstCol) {
      for (int i = 0; i < n; i++) {
        matrix[i][0] = 0;
      }
    }
  }

  public static void main(String[] args) {
    int matrix[][] = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };

    setZeroes(matrix);

    for (int[] row : matrix) System.out.println(Arrays.toString(row));
  }
}
