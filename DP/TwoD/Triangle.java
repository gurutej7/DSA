package DP.TwoD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*  120. Triangle
{ https://leetcode.com/problems/triangle/description/ }
Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the
 current row, you may move to either index i or index i + 1 on the next row.
Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]                 Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
*/

public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println( minimumTotalMemo(triangle) );

        System.out.println( minimumTotalTabu(triangle) );
    }

    private static int minimumTotalMemo(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Som checks according to problem
        if(n == 1) return triangle.get(0).get(0);
        if(n == 2) return triangle.get(0).get(0) + Math.min(triangle.get(1).get(0),triangle.get(1).get(1));

        int dp[][] = new int[n][n];

        for(int arr[] : dp) Arrays.fill(arr,-1);
        
        int mini = Integer.MAX_VALUE;
        // I am considering that I will start from the bottom and reach the top
        // It can be done vice versa also
        for(int i = 0  ; i < n ; i++){ // Variable starting point 
        // In the last row we can start from any index
           mini = Math.min(mini,fMemo(n-1,i,triangle,dp) );

        }
        return mini;
    }

    private static int fMemo(int rowIndex ,int valIndex ,List<List<Integer>> triangle , int dp[][]){
        // Base case 
        if(valIndex > rowIndex) return (int)1e7; // If it is not a valid path we are returning some max value because we are considering minimum of all paths
        if(rowIndex == 0) { // We have reached the last row
            return triangle.get(0).get(0);
        }
        if(dp[rowIndex][valIndex] != -1) return dp[rowIndex][valIndex]; // Avoid recomputing

        // pick i th in next row
        int picki = triangle.get(rowIndex).get(valIndex) + fMemo(rowIndex-1,valIndex,triangle,dp);
        // pick i - 1 th
        int pickIminus1 = (int)1e7;
        if(valIndex-1 >= 0) pickIminus1 = triangle.get(rowIndex).get(valIndex) + fMemo(rowIndex-1,valIndex-1,triangle,dp);

        // Store the answer of the current state
        dp[rowIndex][valIndex] = Math.min(picki , pickIminus1);

        return dp[rowIndex][valIndex];
    }

    private static int minimumTotalTabu(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 1) return triangle.get(0).get(0);
        if(n == 2) return triangle.get(0).get(0) + Math.min(triangle.get(1).get(0),triangle.get(1).get(1));

        int dp[][] = new int[n][n];
        // Tabulation
        // Write down the base cases
        for(int i = 0 ; i< n ; i++){ // Row index
            for(int j = 0 ; j < n ; j++){ // Val index
                if(j > i)// Val Index greater than row index
                    dp[i][j] = (int)1e8;
            }
        }
        dp[0][0] = triangle.get(0).get(0);
        for(int rowIndex = 1 ; rowIndex < n ; rowIndex++){
                for (int valIndex = 0; valIndex <= rowIndex; valIndex++) { // Include valIndex == 0
                    // Copy the recurrence
                    // pick i th in next row
                    int picki = triangle.get(rowIndex).get(valIndex) + dp[rowIndex - 1][valIndex];
                    // pick i - 1 th
                    int pickIminus1 = (valIndex - 1 >= 0) ? triangle.get(rowIndex).get(valIndex) + dp[rowIndex - 1][valIndex - 1] : (int) 1e8;

                    // Store the answer of the current state
                    dp[rowIndex][valIndex] = Math.min(picki, pickIminus1);
                }
            
        }
        int mini = Integer.MAX_VALUE;
            for(int i = 0 ; i < n ; i++){
                mini = Math.min(mini,dp[n-1][i]);

            }
            return mini;
    }

    // Other approach with fixed starting point

    public int minimumTotal(List<List<Integer>> triangle) {
        int maxIndex = triangle.size();

        int colMaxIndex = triangle.get(maxIndex-1).size();

        int dp[][] = new int[maxIndex][colMaxIndex];

//Filling the all the values to maximum so that it will be updated with minimum path 
        for(int i=0;i<maxIndex;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        
        return getMinimumSum(triangle,maxIndex,colMaxIndex,0,0,dp);
    }

    public int getMinimumSum(List<List<Integer>> triangle, int rowMaxIndex,int colMaxIndex,int rowindex,int colIndex, int dp[][]){

// out of boundary condition we might have to return zero
        if(rowindex==rowMaxIndex || colIndex==colMaxIndex ){
            return 0;
        }

// return the stored value if we already computed that path
        if(dp[rowindex][colIndex]!=Integer.MAX_VALUE){
            return dp[rowindex][colIndex];
        }

// this will remain same for what ever value we are computing 
        int num = triangle.get(rowindex).get(colIndex);

// We are addign the minimum to the current number got from the next row 
        int minimum = num + Math.min(getMinimumSum(triangle , rowMaxIndex,colMaxIndex, rowindex+1,colIndex,dp),
                                    getMinimumSum(triangle , rowMaxIndex,colMaxIndex, rowindex+1,colIndex+1, dp));

// Storing the minimum path in the array 
        dp[rowindex][colIndex] = minimum;

    //returning the minimum path found there 
        return dp[rowindex][colIndex] ;
    }
}
