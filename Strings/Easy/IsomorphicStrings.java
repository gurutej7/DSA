package Strings.Easy;
/* 205. Isomorphic Strings
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving 
the order of characters. No two characters may map to the same character, but a character 
may map to itself.
* Example 1:
Input: s = "egg", t = "add"
Output: true
* Example 2:
Input: s = "foo", t = "bar"
Output: false
* Example 3:
Input: s = "paper", t = "title"
Output: true
 */

import java.util.HashMap; 
public class IsomorphicStrings {
    public static void main(String[] args) {
        String s = "foth";
        String t = "bark";

        System.out.println(isIsomorphic(s, t));

        System.out.println(isIsomorphicTwo(s, t));
    }
    // 1st solution using HashMap
    public static boolean isIsomorphic(String s, String t) {
        if(s==null || s.length()<=1) return true;
        if(s.length() != t.length()) return false;
        //We will map for each char in s to its corresponding char in t
        HashMap<Character,Character> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            //If the char exists check if it mapping to the same char before or not
            if(map.containsKey(a)){
                if(map.get(a).equals(b)) continue;
                else return false;
            }
            //check if char in t is mapped by any other key or not
            //if it is already mapped by a key before we should not further map with other key
            else{
                if(!map.containsValue(b)) map.put(a,b);
                else return false;
            }
        }
        return true;
    }

    //2nd Solution using map arrays
    public static  boolean isIsomorphicTwo(String s, String t) {
        // Base case: for different length of two strings...
        if(s.length() != t.length())
            return false;
        // Create two maps for s & t strings...
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        // Traverse all elements through the loop...
        for(int idx = 0; idx < s.length(); idx++){
            // Compare the maps, if not equal, return false...
            if(map1[s.charAt(idx)] != map2[t.charAt(idx)])
                return false;
            // Insert each character if string s and t into seperate map...
            map1[s.charAt(idx)] = idx + 1;
            map2[t.charAt(idx)] = idx + 1;
        }
        return true;    // Otherwise return true...
    }
  
}
