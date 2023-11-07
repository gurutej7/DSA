package Strings.Easy;
/* 242. Valid Anagram
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or
phrase, typically using all the original letters exactly once.
* Example 1:
Input: s = "anagram", t = "nagaram"
Output: true
* Example 2:
Input: s = "rat", t = "car"
Output: false
   
 */

public class ValidAnagram{
	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";

		System.out.println(isAnagram(s, t));
	}

	public static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        int [] map = new int[26];
        for(int i = 0 ; i<s.length() ;i++){
            map[s.charAt(i)-'a']++;
            map[t.charAt(i)-'a']--;
        }
		/* Above iteration provides us with count array having all values to zero then we can say we found an anagram.
        Every element of count has to be equal to 0.
        If it is greater than 0 it means s has a character whose occurrence is greater than its occurrence in t.
        And if its less than 0 then, s has a character whose occurrence is smaller than its occurrence in t. */
        
        for(int i = 0 ; i<26 ;i++)
        if(map[i]!=0) return false;

        return true;
    }
}