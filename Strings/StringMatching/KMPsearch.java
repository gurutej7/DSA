package Strings.StringMatching;

public class KMPsearch {

    /* 
     * Compute LPS array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(m) where m = len(pattern)
     */ 

    private static int[] computeLPSArray(char[] pattern){
        int m = pattern.length;
        int lps[] = new int[m];
        int j = 0 , i = 1 ;
        while(i < m){
            if(pattern[j] == pattern[i]){
                lps[i] = j+1;
                i++;
                j++;
            }
            else if(j > 0) j = lps[j-1];
            else {
                lps[i] = 0 ;
                i++;
            }
        }

        return lps;

    }

    // KMP
    // O(m+n) where n = len(text)  , m = len(pattern)
    private static int KMP(char[] text , char[] pattern){
        int n = text.length;
        int m = pattern.length;
        // if(m > n) return -1;
        int[] lps = computeLPSArray(pattern);

        int i = 0 , j = 0 ; // i tracks text and j tracks pattern
        while(i < n && j < m){
            if(text[i] == pattern[j]) {
                i++;
                j++;
            }
            else if(j > 0) j = lps[j-1];
            else i++;

            if(j == m) return (i-m); // the index where the pattern starts => i-m
        }

        return -1;
    }

    /* 28. Find the Index of First Occurence in a String
     * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
     * 
     * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Example 1:   Input: haystack = "sadbutsad", needle = "sad"
     * Output: 0
     * Explanation: "sad" occurs at index 0 and 6.
     * The first occurrence is at index 0, so we return 0.
     */

    private static int strStr(String haystack, String needle) {
        char text [] = haystack.toCharArray();
        char pattern[] = needle.toCharArray();

        return KMP(text, pattern);
    }

    /* 459. Repeated Substring Pattern
     * https://leetcode.com/problems/repeated-substring-pattern/description/
     * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
     * Example 1:       Input: s = "aba"                Output: false
     * Example 2:       Input: s = "abcabcabcabc"        Output: true
     * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
     */

    private static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        char[] pattern = s.toCharArray();
        int[] lps = computeLPSArray(pattern);
        // really good explanation is in the discussion section , check out while revising
        return (lps[n-1] != 0 ) && (n% (n-lps[n-1]) == 0);

    }


    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";

        System.out.println(strStr(haystack, needle));
        System.out.println(repeatedSubstringPattern(haystack));
    }
    
}
