package DP.Strings;
// Same as LCS but here we have to print the LCS
/* 
Example 1:      Input: text1 = "abcde", text2 = "ace"           Output: "ace"  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:      Input: text1 = "abc", text2 = "def"             Output: ""
Explanation: There is no such common subsequence, so the result is 0. 

*/

import java.util.ArrayList;

public class LCSPrint {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";

        System.out.println( findLCSMemo(s1.length(), s2.length() , s1, s2) );

        System.out.println( longestCommonSubsequencePrintTabu(s1, s2) );
        
    }

    // My idea is to generate all LCS and store them in a List
    // Then finally traverse through the list and find the longest STring of all LCS and return it
    // Kind of backtracking approach
    private static String findLCSMemo(int n, int m, String s1, String s2){
        // Write your code here.
        int dp[][] = new int[n + 1][m + 1];
        ArrayList<StringBuilder> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        fMemo(s1,s2,n,m,dp,sb,list);

        StringBuilder ans = new StringBuilder("");
        for(StringBuilder s : list){
            if(s.length() > ans.length()) {
                ans = s;
            }
        }
        
        // I am starting from the end index and appending char`s from the back if they are same
        // So i am reversing it to make it normal
        return ans.reverse().toString();
    }

    private static void fMemo(String s1 , String s2 , int i , int j , int[][] dp ,StringBuilder sb , ArrayList<StringBuilder> list){
        //Base case
        // If any of the strings get exhausted then we cannot have any further common subsequrnce
        if(i <= 0 || j <= 0) {
            list.add(new StringBuilder(sb.toString()));
            return;
            
        }

        // Avoid recomputing
        if(dp[i][j] != 0) return; // By default the values in the dp are 0`s

        // If the characters are same in both strings then add 1(length) and decrement both the indices and call the function for the remaining strings
        //int same = 0 ; // characters are same 
        if(s1.charAt(i-1) == s2.charAt(j-1)){ // one based indexing, so take care here
            sb.append(s1.charAt(i-1));
            fMemo(s1,s2,i-1,j-1,dp,sb,list);
           // sb.remove(sb.length()-1);
           sb.deleteCharAt(sb.length()-1);
        }

        // If the characters are not same then we have two choices
        // either move i or move j
        // If we move both then we may miss some same characters , because s1.charAt(i) may be equal to s2.charAt(some other value of j in future)
         fMemo(s1,s2,i-1,j,dp,sb,list);
         fMemo(s1,s2,i,j-1,dp,sb,list);

         dp[i][j] = -1 ; // Some  value to mark this state as already solved

        return;
    }

    // Another Approach 
    // refer { https://takeuforward.org/data-structure/print-longest-common-subsequence-dp-26/ }
    // It is all in the Dp table 
    // The max length is at dp[n][m]
    // From there we will start traversing  // If characters are equal we will go to i-1 and j-1
    // Else we will go to where the current value is comming from +> max(i-1,j),(i,j-1)
    // Copy the LCS code 
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
            else if(dp[i-1][j] > dp[i][j-1]) i--;
            else j--;
        }

        return sb.reverse().toString();
    }
    
}
