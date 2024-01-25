package DP.Strings.LCSfollowUps;

/* 1312. Minimum Insertions to make a String Palindrome
{ https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/ }

Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
A Palindrome String is one that reads the same backward as well as forward.

Example 1:      Input: s = "zzazz"              Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.

Example 2:      Input: s = "mbadm"              Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:      Input: s = "leetcode"           Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".
*/

public class MinimumInsertions {
    public static void main(String[] args) {

        String s = "mbadm";

        System.out.println( minInsertions(s) );
        
    }

    // We can make a every string a palindrome by adding its reverse at the end => at max with n(length of given string ) we can make every string a palindrome
    // To minimize this we won`t be adding a whole copy of the string
    // Let`s observe what part of the string that we don`t need to add again
    // If there is a palindrome part in the string then we will keep it as it is and we will try to compensate the remaining characters
    // If we figure out the longest palindromic subsequence, then we can tell the miminum number of characters to add or remove to make the string a palindrome.
    // By observing we can tell that if we compare the given string with its reverse string , We can find the LCpalindromic
    
    private static  int minInsertions(String s) {
        String t = reverse(s);

        return s.length() - longestCommonSubsequenceTabu(s, t);
    }
    // Longest palindromic subsequence code
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
