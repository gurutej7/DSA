package DP.PartitionDp;

import java.util.Arrays;

/* 132. Palindrome Partitioning - 2
{ https://leetcode.com/problems/palindrome-partitioning-ii/description/ }

Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:      Input: s = "aab"                Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class PalindromePartitioningTwo{
    public static void main(String[] args) {

        String s = "aab";

        System.out.println( minCutMemo(s) );

        System.out.println( minCutTabu(s) );
        
    }

    private static int minCutMemo(String s) {
        int n = s.length();

        int dp[] = new int [n+1];

        Arrays.fill(dp,-1);

        return fMemo(s,0,dp)-1;
    }
    // our function counts a partititon at the end of the string after all characters so -1 to the final answer
    private static int fMemo(String s , int i , int[] dp){
        // base case
        if(i == s.length()) return 0 ;

        if(dp[i] != -1) return dp[i];

        int maxi = Integer.MAX_VALUE;
        for(int ind = i ; ind < s.length() ; ind++){
            if(isPalindrome(s,i,ind)){
                int cnt = 1 + fMemo(s,ind+1,dp);
                maxi = Math.min(cnt,maxi);
            }
        }

        dp[i] = maxi;

        return dp[i];
    }

    private static boolean isPalindrome(String s, int start , int end){
        while(start < end){
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    private static int minCutTabu(String s) {
        int n = s.length();

        int dp[] = new int [n+1];

        for(int i = n-1 ; i>= 0 ; i--){
            // copy the recurrence
            int mini = Integer.MAX_VALUE ;
            for(int ind = i ; ind < n ; ind++){
                if(isPalindrome(s,i,ind)){
                    int cnt = 1 + dp[ind+1];
                    mini = Math.min(mini,cnt);
                }

            }
            dp[i] = mini;

        }
        return dp[0]-1;
    }
    // { https://leetcode.com/problems/palindrome-partitioning-ii/solutions/42198/my-solution-does-not-need-a-table-for-palindrome-is-it-right-it-uses-only-o-n-space/ }

}