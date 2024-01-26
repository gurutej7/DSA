package DP.Strings.StringMatching;

/*   44. Wildcard Matching
{ https://leetcode.com/problems/wildcard-matching/description/ }

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
=>  '?' Matches any single character.
=>  '*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:          Input: s = "aa", p = "a"                Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:          Input: s = "aa", p = "*"                Output: true
Explanation: '*' matches any sequence.
*/ 

public class WildcardMatching {
    public static void main(String[] args) {
        String s = "aa";
        String p = "*";

        System.out.println( isMatch(s, p) );

        System.out.println( wildCardMatchingTabu(s, p) );
    }

    private static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        Boolean dp[][] = new Boolean[n][m];

        return wildCardMatchingMemo(s,p,n-1,m-1,dp);
    }

    private static boolean wildCardMatchingMemo(String s , String p , int i , int j , Boolean[][] dp){
        if(j < 0 && i>=0) return false; // p gets exhausted , and s still has characters left
        if(j < 0 && i < 0) return true ; // If both of them exhauseted at same point
        if(i < 0) {// s gets exhausted
            // If there are only stars then they can compensate to zero characters 
            // If there is other than star then it is not a match
            for(int index = 0 ; index <= j ; index++) {
                if(p.charAt(index) != '*') return false;
            }

            return true;
        }
        // Avoid recomputing
        if(dp[i][j] != null) return dp[i][j];
        // Match 
        if(s.charAt(i) == p.charAt(j)  || p.charAt(j) == '?'){
            dp[i][j] =  wildCardMatchingMemo(s,p,i-1,j-1,dp);
            return dp[i][j];
        }
        if(p.charAt(j) == '*'){
            // skip zero characters  || compensate one character 
            // and in the next state again decide skip zero or one character
            // Like this we can go through all the choices at *
            boolean compensateZeroChar = wildCardMatchingMemo(s,p,i,j-1,dp);
            boolean compensateOneChar = wildCardMatchingMemo(s,p,i-1,j,dp);

            dp[i][j] = compensateZeroChar || compensateOneChar ;

            return dp[i][j];

        }

        // not match 
        dp[i][j] = false;
        return dp[i][j];
    }

    private static boolean wildCardMatchingTabu(String s , String p){
        int n = s.length();
        int m = p.length();
        // As the base cases have less than zero , since we can`t have negative index in the array , we will use 1 based indexing here
        boolean dp[][] = new boolean[n+1][m+1];
        // Write down the base cases
        // both the strings gets exhausted 
        dp[0][0] = true;
        for(int i = 1 ; i <= n ; i++) dp[i][0] = false; // for any value of i , j gets exhausted

        for (int j = 1; j <= m; j++) {
            boolean flag = true;
            for (int index = 1; index <= j; index++) {
                if (p.charAt(index - 1) != '*') {
                    flag = false;
                    break;
                }
            }

            dp[0][j] = flag;
        }

        // write for loops for the changing parameters
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                // Copy the recurrence
                // match
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){ // i-1 => because 1 based indexing
                    dp[i][j] = dp[i-1][j-1];
                }
                // special case
                else if(p.charAt(j-1) == '*'){
                    boolean compensateZeroChar = dp[i][j-1];
                    boolean compensateOneChar = dp[i-1][j];
                    dp[i][j] = compensateZeroChar || compensateOneChar ;
                }
                // not match
                else{
                    dp[i][j] = false;
                }

            }
        }

        return dp[n][m];
    }
    
}
