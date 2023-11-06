package Strings.Easy;
/* 14. Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".
* Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"
* Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
  
 */

import java.util.Arrays;

public class LongestCommonprefix {
    public static void main(String[] args) {
        String [] strs = {"flower","flow","flight"};

        System.out.println(longestCommonPrefix(strs));
    }

    public static  String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        int len = strs.length-1;
        int n = strs[0].length();
        int m = strs[len].length();
        int i = 0 ;
        String ans = "";
        while(i<n && i<m){ 
            if(strs[0].charAt(i)==strs[len].charAt(i)){
                 ans = ans + strs[0].charAt(i);   
                 /*This  can be done in many ways without using a ans variable just by tracking the index upto
                  which is equal and can return a substring of the string upto that index .

                  while(i<n && strs[0].charAt(i)==strs[len].charAt(i)) i++;
                  return strs[0].substring(0,i);
                 */
                 i++;
            }
            else break;
            
        }
        return ans;
    }

}
