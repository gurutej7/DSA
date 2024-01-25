package DP.Strings.StringMatching;

import java.util.Arrays;

/*  72. Edit Distance
{ https://leetcode.com/problems/edit-distance/description/ }

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
* Insert a character
* Delete a character
* Replace a character

Example 1:      Input: word1 = "horse", word2 = "ros"           Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
*/

public class EditDistance {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        System.out.println( minDistance(word1, word2) );

        System.out.println( editDistanceTabu(word1, word2) );
    }

    // What is the max number of operations we need to do , to complete the given task
    // Delete all characters in word1 and insert all characters of word2 => n+m operations (n deletions + m insertions) => n = word1.length()  and m = word2.length();
    // We have to minimize this operations
    // We can try out all ways with the given choices at each position

    private static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int dp[][] = new int [n][m];

        for(int row[] : dp) Arrays.fill(row,-1);

        return editDistanceMemo(word1,word2,n-1,m-1,dp);
        
    }
    // i , j => represents index of s1 and s2 repectively
    private static int editDistanceMemo(String s1,String s2,int i,int j,int [][] dp){
        // Base cases 
        // Little tricky use pen and paper
        if( i < 0) return j+1;  // if s1 gets exhausted then we have to insert all the remaining characters of s2
        if( j < 0) return i+1; // if s2 gets exhausted then we have to delete all the remaining characters of s1

        if(dp[i][j] != -1) return dp[i][j];

        // if characters match then we dont perform any operation and move to the next index in both the strings
        if(s1.charAt(i) == s2.charAt(j)) {
            int match = editDistanceMemo(s1, s2, i-1, j-1, dp);
            dp[i][j] = match;
            return dp[i][j];
        }
        // If they did not match we have 3 options
        // Delete char in s1
        int delete = 1 + editDistanceMemo(s1, s2, i-1, j, dp);
        // Insert char in s1 => what character we will insert , obviously the char at s2 => then they will match and reduce j
        int insert = 1 + editDistanceMemo(s1, s2, i, j-1, dp);
        // Replace char => if we replace char in s1 with the same char int s2 then they will match
        int replace = 1 + editDistanceMemo(s1, s2, i-1, j-1, dp);

        // Take minimum of all the ways
        dp[i][j] = Math.min(delete,Math.min(insert, replace));

        return dp[i][j];

    }

    private static int editDistanceTabu(String word1 , String word2){
        int n = word1.length();
        int m = word2.length();

        // one based indexing here
        int dp[][] = new int[n+1][m+1];
        // Write down the base cases
        for(int i = 0 ; i<= n ; i++) dp[i][0] = i+1-1;

        for(int j = 0 ; j<= m ; j++) dp[0][j] = j+1-1;

        // Write for loops for the changing parameters
        for(int i = 1 ; i<=n ; i++){
            for(int j = 1 ; j<=m ; j++){
                // copy the recurrence
                if(word1.charAt(i-1) == word2.charAt(j-1)){ // if the match
                    dp[i][j] = dp[i-1][j-1];
                }
                else{ // not match
                    int delete = 1 + dp[i-1][j];
                    int insert = 1 + dp[i][j-1];
                    int replace = 1 + dp[i-1][j-1];
                    dp[i][j] = Math.min(delete,Math.min(insert,replace));
                }
            }
        }

        return dp[n][m];
    }

}
