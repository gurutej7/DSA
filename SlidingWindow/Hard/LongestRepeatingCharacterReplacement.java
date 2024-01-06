package SlidingWindow.Hard;

/*  424. Longest Repeating Character Replacement
You are given a string s and an integer k. You can choose any character of the string and change
 it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing
 the above operations.

 link { https://leetcode.com/problems/longest-repeating-character-replacement/description/ }

Example 1:
Input: s = "ABAB", k = 2                                Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1                             Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


*/

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;

        System.out.println(  characterReplacement(s, k)  );
        
    }

    /* The problem says that we can make at most k changes to the string (any character can be replaced with any
     other character). So, let's say there were no constraints like the k. Given a string convert it to a string
      with all same characters with minimal changes. The answer to this is

*  length of the entire string - number of times of the maximum occurring character in the string
*  Given this, we can apply the at most k changes constraint and maintain a sliding window such that
*  (length of substring - number of times of the maximum occurring character in the substring) <= k

    */
    public static int characterReplacement(String s, int k) {
        int left = 0 , right = 0 ;
        int freq [] = new int[26];
        int n = s.length();
        int maxLength = 0 , maxCharCount = 0;
        while(right < n ){
            // Increse the freq/ count of the current char
            freq[s.charAt(right)-'A']++;
            // Update/Change the hero based on the most occurence in the current window
            if(maxCharCount < freq[s.charAt(right)-'A'])
                maxCharCount = freq[s.charAt(right)-'A'];
            // We can have least replacements if we dont replace the most occuring character
            // Instead we should replace the character which are not most Occuring in the window
            // that is the most occuring character is hero then in a window we cannot have more than K Non heros
            // To calculate the number of non heros we can simply substract the heros from total
            // NonHeros  = total characters - heros
            // If Non heros > K then it is not a valid window , shrink it
            while(right-left + 1 - maxCharCount > k){
                // If we are skipping the char At left then we have to decrease its count in the freq also
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            // For every valid window keep track of maxLen
            maxLength = Math.max(maxLength, right-left+1);
            right++;
        }

        return maxLength;
    }
    
}
