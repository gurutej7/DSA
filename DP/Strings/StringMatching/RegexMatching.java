package DP.Strings.StringMatching;

/* 10. Regular Expression Matching
{ https://leetcode.com/problems/regular-expression-matching/description/ }

Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

=>  '.' Matches any single character.​​​​
=>  '*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:      Input: s = "aa", p = "a*"               Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 2:      Input: s = "ab", p = ".*"               Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
 */

public class RegexMatching {
    public static void main(String[] args) {
        String s = "mississippi";
        String p = "mis*is*ip*." ; // Expected true

        System.out.println( isMatch(s, p) );

        System.out.println( regexTabu(s, p) );
        
    }
    
    private static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        Boolean dp[][] = new Boolean[n][m];

        return regexMemo(s,p,n-1,m-1,dp);
    }

    private static boolean regexMemo(String s, String p , int i , int j , Boolean[][] dp){
        // Base cases
        if(i < 0 && j < 0) return true; // Both the strings get exhausted

       /* if the second last element is '*' then also we can call it a match, as given in the question, 
		we can have 0 occurences of the previous element */
        if(i < 0 && p.charAt(j) == '*') return regexMemo(s,p,i,j-2,dp);
        // even now if element's are left in s or p, then return false
        if( i < 0 || j < 0) return false;


        if(dp[i][j] != null) return dp[i][j];

        // If match ,  the skip it, cause it exists in both of them
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            dp[i][j] = regexMemo(s,p,i-1,j-1,dp);
            return dp[i][j];
        }
        // Special case
        if(p.charAt(j) == '*'){
            // as it is given , when we encounter '*', we can choose 0 or n number of previous element
            if(p.charAt(j-1) == s.charAt(i) || p.charAt(j-1) == '.'){
                // zero match || single match || multiple matches
                    dp[i][j] =  ( regexMemo(s,p,i,j-1,dp) || regexMemo(s,p,i-1,j,dp) ) || regexMemo(s,p,i,j-2,dp);
                    return dp[i][j];

            }
            // If the chracters are not same then we can`t do single match or multiple matches
            if(p.charAt(j-1) != s.charAt(i)){
                 dp[i][j] = regexMemo(s,p,i,j-2,dp) ; // considered as zero match
                 return dp[i][j];
            }
        }

        // If it is not a match and also not a special case
        dp[i][j] = false;
        return dp[i][j];
    }

    private static boolean regexTabu(String s, String p){
        int n = s.length();
        int m = p.length();
    
        // Since base case has < 0, we can't have array index as -1, so consider one-based indexing
        boolean dp[][] = new boolean[n + 1][m + 1];
        // Now < 0 becomes == 0
        // Copy the base cases
        dp[0][0] = true;
    
        for (int j = 2; j <= m; j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }
    
        // Write for loops for the changing parameters
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Copy the recurrence
                // If match, then skip it because it exists in both of them
                if (j <= m && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // Special case
                else if (j >= 2 && p.charAt(j - 1) == '*') {
                    // As it is given, when we encounter '*', we can choose 0 or n number of previous element
                    if (j >= 2 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                        // Zero match || single match || multiple matches
                        dp[i][j] = (dp[i][j - 1] || dp[i - 1][j]) || dp[i][j - 2];
                    }
                    // If the characters are not the same, then we can't do a single match or multiple matches
                    else if (j >= 2 && p.charAt(j - 2) != s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j - 2]; // Considered as zero match
                    }
                }
                // If it is not a match and also not a special case
                else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }
    
}
