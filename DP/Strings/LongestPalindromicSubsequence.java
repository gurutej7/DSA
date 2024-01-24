package DP.Strings;

import java.util.Arrays;

/* 516. Longest Palindromic Subsequence
{ https://leetcode.com/problems/longest-palindromic-subsequence/description/ }

Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without 
changing the order of the remaining elements.

Example 1:      Input: s = "bbbab"                  Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:      Input: s = "cbbd"                   Output: 2
Explanation: One possible longest palindromic subsequence is "bb".

  
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        String s  = "bbbab";

        System.out.println( longestPalindromeSubseq(s) );

        System.out.println( longestPalindromeSubseq2(s) );
        
    }
    /* Approach - 1
     If the two ends of a string are the same, then they must be included in the longest palindrome subsequence. 
     Otherwise, both ends cannot be included in the longest palindrome subsequence.
    */
    private static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] mem = new int[n][n];
        for(int row[] : mem) Arrays.fill(row,-1);
        return longestPalindromeSubseq(0, n-1, s, mem);
    }

    private static int longestPalindromeSubseq(int l, int r, String s, int[][] mem) {
        // Base cases
        if (l == r) return 1;
        if (l > r) return 0;

        if (mem[l][r] != -1) return mem[l][r];

        int sameChar = 0 ;
        int notSameOption1 = 0 , notSameOption2 = 0;
        if(s.charAt(l) == s.charAt(r)){
            sameChar = 2 + longestPalindromeSubseq(l+1, r-1, s, mem);
        }
        else{
            notSameOption1 = longestPalindromeSubseq(l+1, r, s, mem);
            notSameOption2 = longestPalindromeSubseq(l, r-1, s, mem) ;
        }
        mem[l][r] = Math.max(sameChar,Math.max(notSameOption1,notSameOption2));
        return mem[l][r];
    }
    /* Intuition :
        We have to find the longest Subsequence which is a palindrome
        Can we some how relate it to LCS , but in LCS there are 2 strings in the input
        is there a chance here for us to use a second string
        Palindrome means a string which is same as its reverse string
        If we observe carefully the LCS of given String and reverse String of it will be our answer
    */
    private static int longestPalindromeSubseq2(String s){
        String t = reverse(s);

        return longestCommonSubsequenceTabu(s, t);
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

    private static String reverse(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        return sb.toString();
    }
    
}
