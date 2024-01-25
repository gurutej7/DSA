package DP.Strings.StringMatching;

import java.util.Arrays;

/* 115. Distinct Subsequences
{ https://leetcode.com/problems/distinct-subsequences/description/ } 

Given two strings s and t, return the number of distinct subsequences of s which equals t.
The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:      Input: s = "babgbag", t = "bag"                 Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
BAbGbag
BAbgbaG
BabgbAG
baBgbAG
babgBAG
*/

public class DistinctSubsequences {
    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        
        int n = s.length();
        int m = t.length();

        int dp[][] = new int[n][m];

        for(int row[] : dp) Arrays.fill(row,-1);

        System.out.println( countDistinctMemo(s, t, dp, n-1, m-1) );

        System.out.println( countDistinctTabu(s, t) );
    }
    /* if(s[i] == s[j]) // If the characters match what choices we have
    we can i-- , and j-- and look for remaining t in remaining s
    we can i-- and look for another occurrence of whole (t)
     * If the did not Match (s[i] != t[j])
    we dont have choice we have find t 
    so we will i-- and look for t in the remaining s 
     */

     // The question states count ways
     // In recursion count ways will always have 0 and 1 int the base case
     
     private static int countDistinctMemo(String s , String t, int dp[][], int i , int j){
        // Base cases
        // if(i<0 && j<0) return 1 ; //Both strings gets exhausted then it is a valid
        if(j < 0 ) return 1 ; // t has been found
        if(i < 0 && j >=0 ) return 0 ; // s got exhausted with i still >= 0

        if(dp[i][j] != -1) return dp[i][j]; // avoid re-computing for the same state
        // If the characters match
        int match = 0 ; 
        if(s.charAt(i) == t.charAt(j)){
            match = countDistinctMemo(s, t, dp, i-1, j-1)  + countDistinctMemo(s, t, dp, i-1, j);
            dp[i][j] = match;
            return dp[i][j];
        }
        // else (the characters did not match)
        int notMatch = countDistinctMemo(s, t, dp, i-1, j);

        dp[i][j] = notMatch ;

        return dp[i][j];
    }
    
    private static int countDistinctTabu(String s , String t){
        int n = s.length();
        int m = t.length();
        int dp[][] = new int[n+1][m+1];

        // Write down the base cases
        // if t gets exhausted then it is a valid one
        for(int i = 0 ; i <=n ; i++) dp[i][0] = 1; // for any value of i , if j == 0 then return 1

        for(int j = 1 ; j <= m ; j++) dp[0][j] = 0 ;
        // Write for loops for the changing parameters
        for(int i = 1 ; i<= n ; i++){
            for(int j = 1 ; j<= m ; j++){
                // Copy the recurrence
                // if they match we have two choices
                // i-1,j-1 and i-1,j
                int match = 0 , notMatch = 0;
                if(s.charAt(i-1) == t.charAt(j-1)){
                    match = dp[i-1][j-1] + dp[i-1][j];
                }
                else{
                    notMatch = dp[i-1][j];
                }
                dp[i][j] = match + notMatch ;

            }
        }

        return dp[n][m];
    }
}
