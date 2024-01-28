package DP.LIS.LISfollowUps;

import java.util.Arrays;
// import java.util.Comparator;

/*  1048. Longest String Chain
{ https://leetcode.com/problems/longest-string-chain/ } 

Example 1:      Input: words = ["a","b","ba","bca","bda","bdca"]        Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].

Example 2:      Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]     Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Example 3:      Input: words = ["abcd","dbqca"]                         Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
  
*/

public class LongestStringChain {
    public static void main(String[] args) {
        String[] words = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };

        // Arrays.sort(words);

        // Sorting based on word length
        // Arrays.sort(words, Comparator.comparing(String::length));
        // System.out.println(Arrays.toString(words));

        System.out.println(longestStrChain(words));
    }

    private static int longestStrChain(String[] words) {
        int n = words.length;

        if (n == 1)
            return 1;
        // Sorting based on word length
        // Arrays.sort(words, Comparator.comparing(String::length));

        // Sorting based on word length , this function is performing better than the
        // above one
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int dp[] = new int[n];
        // the state dp[i] represents the largest len => with given conditions
        // If we are at a current element , then we will check the previous elements ,
        // if it is predecessor or not
        // if it is a predecessor of curr , then it`s predecessors will also join the
        // lis
        Arrays.fill(dp, 1); // At every index itself can be subsequence of length 1

        // Standard LIS pattern with some extra condition
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (isPredecessor(words[i], words[j]) && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (dp[i] > maxLength)
                maxLength = dp[i];
        }

        return maxLength;
    }

    // Function to check if it is a redecessor or not
    private static boolean isPredecessor(String curr, String prev) {

        int n = curr.length();
        int m = prev.length();
        if (n - m != 1)
            return false;
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (curr.charAt(i) != prev.charAt(j))
                i++;
            else {
                i++;
                j++;
            }
        }

        return (i == n && j == m) || (i == n - 1 && j == m);
    }

}
