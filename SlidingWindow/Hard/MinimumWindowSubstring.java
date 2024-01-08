package SlidingWindow.Hard;

import java.util.HashMap;

/* 76. Minimum Window SubString
link { https://leetcode.com/problems/minimum-window-substring/description/ }

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
 character in t (including duplicates) is included in the window. If there is no such substring, return the empty 
 string "".
The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"                   Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"                                 Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"                                Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
*/

public class MinimumWindowSubstring{
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println( minWindow(s, t) );
    }

    public static  String minWindow(String s, String t) {
        // Base Cases
        if(s == null || s.length() < t.length() || s.length() == 0){
        return "";
        }

        int left = 0 , right = 0 ;
        int m = s.length() , n = t.length();
        int currCharCount = 0 ; // To track the number of characters of String t that we are having in the current window
        int minLength = Integer.MAX_VALUE;
        int ansStart = 0 ; // Instead of updating ans with substring every time we encounter less length will result in increase in time complexity , 
        // So we will just update the starting index
        int ansEnd = 0 ;


        // Store the frequencies of characters of String t into the  map
        HashMap<Character,Integer> map = new HashMap<>();
        // Map will be tracking the characters count of string t 

        for(int i = 0 ; i < n ; i++) {
            char ch = t.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        while(right < m){
            char sRightChar = s.charAt(right);
            if(map.containsKey(sRightChar)){ // If the current char of s is in the map then it is a required character because it is also in t
                // After considering it to be a part of thw window decrease its frequency in the map
                map.put(sRightChar,map.get(sRightChar)-1);
                // After decreasing the freq , and the current freq is >= 0
                // then it can be used to compensate the corresponding char in t
                // update current character count of t that we are having 
                if(map.get(sRightChar) >= 0) currCharCount++;
            }
            // If the current Char count is equal to t.length() then we have all the characters of t in the current window
            //We have to return minimum , so we shrink from left until the window is still valid
            while(currCharCount == n){ // n = t.length()
                // update length if it is less than the previous valid window length
                int currLength  = right - left + 1;
                if(currLength  <= minLength){
                    minLength = currLength;
                    // Keep track of the starting index of the minimum window , it will be useful at the end
                    ansStart = left ;
                    ansEnd = right;
                }
                char sLeftChar = s.charAt(left);
                // If we are skipping a character in the map
                // then we are skipping one of the valid characters of t , So increase its freq in the map
                if(map.containsKey(sLeftChar)){
                    map.put(sLeftChar,map.get(sLeftChar)+1);
                    // But there can be multiple same characters in the window
                    // So after skipping one occurence also , it can still be a window
                    // If we have skipped all the occurenes of a valid character the only we should decrement currCharCount
                    if(map.get(sLeftChar) > 0) currCharCount-- ;
                }
                left++;
            }

            right++;
        }
        // If the minLength is still MAX_VALUE it means that we have not found any valid window so return ""
        String res = minLength == Integer.MAX_VALUE ? "" : s.substring(ansStart , ansEnd + 1);

        return res;
    }

    // { https://leetcode.com/problems/minimum-window-substring/solutions/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems/ }
    // check java version in the comments

    // Same approach using array instead of a HashMap
    public static String minWindow2(String s, String t) {
        int [] map = new int[128];
        for (char c : t.toCharArray()) {
          map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
          final char c1 = s.charAt(end);
          if (map[c1] > 0) counter--;
          map[c1]--;
          end++;
          while (counter == 0) {
            if (minLen > end - start) {
              minLen = end - start;
              minStart = start;
            }
            final char c2 = s.charAt(start);
            map[c2]++;
            if (map[c2] > 0) counter++;
            start++;
          }
        }
    
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
      }
}