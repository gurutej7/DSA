package DP.OneD;

/* 509. Fibonacci Number
link { https://leetcode.com/problems/fibonacci-number/description/ }

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number
 is the sum of the two preceding ones, starting from 0 and 1. That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).
 */
public class Fibanocci{
    public static void main(String[] args) {
        int n = 5 ;
        int dp[] = new int[n+1];

        System.out.println( fibMemo(n, dp) );

        System.out.println( fibTabulation(n) );

    }

    // Recursive approach
    public static int fibRecursive(int n) {
        if(n == 0 || n == 1 ) return n;
        // Draw recursion tree
        return fibRecursive(n-1)+fibRecursive(n-2);
    }
    // Memoization
    private static int fibMemo(int n,int dp[]){
        if(n == 0 || n == 1 ) return n;

        // If already computed avoid re-computing
        if(dp[n] != 0) return dp[n];
        // Store the value after calculating
        return dp[n] = fibMemo(n-1, dp) + fibMemo(n-2, dp);
    }

    // Tabulation
    private static int fibTabulation(int n){
        if(n == 0 || n == 1) return n;
        int dp[] = new int[n+1];
        // Write the base cases
        dp[0] = 0;
        dp[1] = 1; // For n = 1 
        // from Bottom to up
        for(int i = 2 ; i<= n ; i++){
            //copy the recurrence
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    // Space Optimized Solution
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
         int prev = 0;  // 0th
         int curr = 1;  // 1st
         
         for(int i = 2 ; i<=n ; i++ ){
             int temp = curr;
             curr = prev+temp;
             prev = temp;
         }
         return curr;
     }
}