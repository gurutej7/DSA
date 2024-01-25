package DP.Strings.LCSfollowUps;

/*  583. Delete Operations for two Strings 
{ https://leetcode.com/problems/delete-operation-for-two-strings/ }

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.

Example 1:      Input: word1 = "sea", word2 = "eat"             Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Example 2:      Input: word1 = "leetcode", word2 = "etco"       Output: 4
*/

public class MinimuInsertionOrDeletions {
    public static void main(String[] args) {
        String word1 = "leetcode";
        String word2 = "etco" ;

        System.out.println( minDistance(word1, word2) );
        
    }

    /*  Let n = len(str1)  and m = len(str2)
    => Is the give task that is to covert str1 to str2 is always possible ?
    => Yes , Delete all characters is str1 , and insert all characters of str2 in str1 => with (n+m) operations we can do the given task for any input
    => But it is the maximum , we need to minimize it 
    => To minimize we need to reduce deletions and insertions , how can we do that
    => What can we do not delete , => those characters of str2 which are in str1 
    => (we don`t need to delete them , If they are not deleted then insertions for that place is also reduced)
    => To find the common part in both the Strings we can use LCS
    => Therefore , the ans = Total operations - (reduced opertions (deletions of LCS) (and Insertions of LCS))
    => ans = (n+m) - 2*LCS 
     */

    private static int minDistance(String word1, String word2) {
        int n = word1.length() , m = word2.length();

        return n+m - 2*longestCommonSubsequenceTabu(word1, word2);
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
    
}
