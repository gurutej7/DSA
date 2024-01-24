package DP.Strings;

import java.util.Arrays;

/*  1143. Longest Common Subsequence
{ https://leetcode.com/problems/longest-common-subsequence/description/ }

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common 
subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none) 
deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:      Input: text1 = "abcde", text2 = "ace"           Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:      Input: text1 = "abc", text2 = "def"             Output: 0
Explanation: There is no such common subsequence, so the result is 0.

*/

public class LCS {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        System.out.println( longestCommonSubsequenceMemo(text1, text2) );

        System.out.println( longestCommonSubsequenceTabu(text1, text2) );
    }

    private static int longestCommonSubsequenceMemo(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();

        int dp[][] = new int[n1 + 1][n2 + 1];

        for(int row[] : dp) Arrays.fill(row,-1);

        return fMemo(text1,text2,n1,n2,dp);
    }

    // Try out all possibilities => recursion
    // f(i,j) represents length of longest common subsequence till index i in Str1 and index j in Str2
    // i => index with respect to String1
    // j => index with respect to String2
    private static int fMemo(String s1 , String s2 , int i , int j , int[][] dp){
        //Base case
        // If any of the strings get exhausted then we cannot have any further common subsequrnce
        if(i <= 0 || j <= 0) return 0;

        // Avoid recomputing

        if(dp[i][j] != -1) return dp[i][j];

        // If the characters are same in both strings then add 1(length) and decrement both the indices and call the function for the remaining strings
        int same = 0 ; // characters are same 
        if(s1.charAt(i-1) == s2.charAt(j-1)){ // one based indexing, so take care here
            same = 1 + fMemo(s1,s2,i-1,j-1,dp);
        }

        // If the characters are not same then we have two choices
        // either move i or move j
        // If we move both then we may miss some same characters , because s1.charAt(i) may be equal to s2.charAt(some other value of j in future)
        int notSame1 = fMemo(s1,s2,i-1,j,dp);
        int notSame2 = fMemo(s1,s2,i,j-1,dp);

        dp[i][j] = Math.max(same,Math.max(notSame1,notSame2));

        return dp[i][j];
    }

    private static int longestCommonSubsequenceTabu(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();

        int dp[][] = new int[n1 + 1][n2 + 1];

        //Write down the base case
        for(int i = 0 ; i<=n1 ; i++) dp[i][0] = 0;

        for(int j = 0 ; j<=n2 ; j++) dp[0][j] = 0;

        // We need not to write the above cases because in java by default values in int array are 0`s

        // Write the for loops for the changing parameters
        for(int i = 1 ; i<=n1 ; i++){  // String1 indexing
            for(int j = 1 ; j<=n2 ; j++){ // String2 indexing
                // Copy the recurrence
                int same = 0;
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    same = 1 + dp[i-1][j-1];
                }
                int notSame1 = dp[i-1][j];
                int notSame2 = dp[i][j-1];

                dp[i][j] = Math.max(same,Math.max(notSame1,notSame2));

            }
        }

        return dp[n1][n2];
    }

    // { https://leetcode.com/problems/longest-common-subsequence/solutions/351689/java-python-3-two-dp-codes-of-o-mn-o-min-m-n-spaces-w-picture-and-analysis/ }
    
}
