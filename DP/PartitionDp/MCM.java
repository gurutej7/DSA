package DP.PartitionDp;

import java.util.Arrays;

/* Matrix Chain Multiplication
Problem statement
Given a chain of matrices A1, A2, A3,.....An, you have to figure out the most efficient way to 
multiply these matrices. In other words, determine where to place parentheses to minimize the 
number of multiplications.
You will be given an array p[] of size n + 1. Dimension of matrix Ai is p[i - 1]*p[i]. You 
need to find minimum number of multiplications needed to multiply the chain. 

Sample Input 1:     10 15 20 25
Sample Output 1:    8000
Sample Output Explanation:
There are two ways to multiply the chain - A1*(A2*A3) or (A1*A2)*A3.
If we multiply in order- A1*(A2*A3), then number of multiplications required are 11250.
If we multiply in order- (A1*A2)*A3, then number of multiplications required are 8000.
Thus minimum number of multiplications required are 8000. 
 */

public class MCM {
    public static void main(String[] args) {
        int p[]= {10,15,20,25};

        System.out.println( mcmMemo(p) );

        System.out.println( mcmTabu(p) );

    }

    // refer { https://www.youtube.com/watch?v=vRVfmbCFW7Y }

    private static int mcmMemo(int[] p) {
        int n = p.length;
        int dp[][] = new int[n][n];

        for (int row[] : dp)
            Arrays.fill(row, -1);

        return fMemo(p, 1, n - 1, dp);
    }

    private static int fMemo(int[] p, int i, int j, int[][] dp) {
        // Base case
        if (i == j)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j]; // memoization
        int mini = (int) 1e9;

        for (int k = i; k < j; k++) {
            int steps = p[i - 1] * p[k] * p[j] + fMemo(p, i, k, dp) + fMemo(p, k + 1, j, dp);
            mini = Math.min(steps, mini);
        }

        dp[i][j] = mini;

        return dp[i][j];
    }

    private static int mcmTabu(int p[]) {
        int n = p.length;

        int dp[][] = new int[n+1][n+1];

        // Write down the base cases
        for (int i = 0; i < n; i++)
            dp[i][i] = 0; // just writing for understanding , (in java arrays by default values are 0`s)
        
        // write for loops for the changing parameters  in the opposite manner
        
        for(int i = n-1 ; i >= 1 ; i--){
            // be practical while writing j , don`t follow the rules blindly
            for(int j = i+1 ; j < n ; j++){
                // copy the recurrence
                int mini = (int)1e9;
                for(int k = i ; k < j ; k++){
                    int steps = p[i-1]*p[k]*p[j] + dp[i][k] + dp[k+1][j];
                    mini = Math.min(mini,steps);
                }

                dp[i][j] = mini;
                
            }

        }

        return dp[1][n-1];
    }
}