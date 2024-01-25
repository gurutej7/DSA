package DP.Strings.LCSfollowUps;

/* 1092. Shortest Common Supersequence
{ https://leetcode.com/problems/shortest-common-supersequence/description/ }

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there 
are multiple valid strings, return any of them.
A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the 
string s.

Example 1:      Input: str1 = "abac", str2 = "cab"              Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.


*/

public class ShortestCommonSuperSequence {
    public static void main(String[] args) {
        String str1 = "abac";
        String str2 = "cab";

        System.out.println( shortestCommonSupersequence(str1, str2) );
    }

    // First let`s check what will be supersequence at worst case
    // If we combine both strings the it will be a super sequence but the length is (n+m)
    // So at worst case it will n+m length , but we have to decrease its length
    // How do we do that , if there are some characters same in both strings then we can take them only once
    // Therefore , the best possible length would be n+m - LCS
    // To print the supersequence , we can refer to print LCS
    // In print LCS we are only printing the LCS but here we have to print characters other than the LCS also , so a slight modification is required
    // { https://www.youtube.com/watch?v=xElxAuBcvsU }

    private static String shortestCommonSupersequence(String str1, String str2) {
        return longestCommonSubsequencePrintTabu(str1, str2);
    }

    
    private static String longestCommonSubsequencePrintTabu(String text1, String text2) {
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

        int maxLen =  dp[n1][n2];

        if(maxLen == 0) return "";

        StringBuilder sb = new StringBuilder("");

        int i = n1 , j = n2;

        while(i > 0 && j > 0){
            if(text1.charAt(i-1) == text2.charAt(j-1)){
                sb.append(text1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]) { // Move to from where the larger value is coming , that is towards the common character
                sb.append(text1.charAt(i-1));
                i--;
            }
            else {
                sb.append(text2.charAt(j-1));
                j--;
            }
        }

        // After some time some characters may be left off in any of the strings
        while(i > 0) {
            sb.append(text1.charAt(i-1));
            i--;
        }

        while( j > 0){
            sb.append(text2.charAt(j-1));
            j--;
        }

        return sb.reverse().toString();
    }
    
}
