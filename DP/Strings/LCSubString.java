package DP.Strings;

/*  Longest Common Substring

You are given two strings, 'str1' and 'str2'. You have to find the length of the longest common substring.
A substring is a continuous segment of a string. For example, "bcd" is a substring of "abcd", while "acd" or "cda" are not.

Example:        Input: ‘str1’ = “abcjklp” , ‘str2’ = “acjkp”.           Output: 3
Explanation:  The longest common substring between ‘str1’ and ‘str2’ is “cjk”, of length 3.

*/
public class LCSubString {
    public static void main(String[] args) {
        String text1 = "abcjklp";
        String text2 = "acjkp" ;

        System.out.println( longestCommonSubsequenceTabu(text1, text2) );
        
    }

    // Again Using Observation haki on the Tabulation table of LCS
    // refer { https://www.youtube.com/watch?v=_wP9mWNPL5w&list=PPSV }
    // I will copy the LCS code
    private static int longestCommonSubsequenceTabu(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();

        int dp[][] = new int[n1 + 1][n2 + 1];

        //Write down the base case
        for(int i = 0 ; i<=n1 ; i++) dp[i][0] = 0;

        for(int j = 0 ; j<=n2 ; j++) dp[0][j] = 0;

        // We need not to write the above cases because in java by default values in int array are 0`s
        int maxLen = 0;
        // Write the for loops for the changing parameters
        for(int i = 1 ; i<=n1 ; i++){  // String1 indexing
            for(int j = 1 ; j<=n2 ; j++){ // String2 indexing
                // Copy the recurrence
                // int same = 0;
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    maxLen = Math.max(maxLen,dp[i][j]);
                }
                else dp[i][j] = 0 ;
                // int notSame1 = dp[i-1][j];
                // int notSame2 = dp[i][j-1];

                // dp[i][j] = Math.max(same,Math.max(notSame1,notSame2));

            }
        }

        // return dp[n1][n2];
        return maxLen;
    }
}
