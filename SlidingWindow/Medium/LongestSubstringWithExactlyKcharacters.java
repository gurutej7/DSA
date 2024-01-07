package SlidingWindow.Medium;

/* Longest Substring with exactly K distinct Characters

link { https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1 }

Given a string you need to print the size of the longest possible substring that has exactly K unique characters.
 If there is no possible substring then print -1.

Example 1:
Input: S = "aabacbebebe", K = 3                Output: 7
Explanation: 
"cbebebe" is the longest substring with 3 distinct characters.

Example 2:
Input: S = "aaaa", K = 2                        Output: -1
Explanation: 
There's no substring with 2 distinct characters.

*/


public class LongestSubstringWithExactlyKcharacters {

    public static void main(String[] args) {

        String str = "aabacbebebe";
        int k = 3;

        System.out.println( longestkSubstr(str, k) );
        
    }


    // Simple sliding window approach
    // The current window characteristics is corresponding to the current substring
    public static int longestkSubstr(String str, int k) {
        // count array to store the frequency of the current character in the window 
		int count[] = new int[26];
		int distinctCount  = 0 ;
		int left = 0 , right = 0 , n = str.length();
		int maxLength = 0 ;
		while(right < n){
             // If the character is new then increment the distinctCount
			if(count[str.charAt(right) - 'a'] == 0){
				distinctCount++;
			}
			count[str.charAt(right) - 'a']++;
            // If the distinctCount is greater than k the we have to remove one char
            // To remove one character we have to skip all the occurences of that character in the window
            // Then only we can reduce distinctCount
			while(distinctCount > k){
				count[str.charAt(left)-'a']--;
				if(count[str.charAt(left) - 'a'] == 0) distinctCount--;
				left++; 
			}
			
            // If the current window has exactly K unique characters then update maxlength with the best value
			if( distinctCount == k)
			maxLength = Math.max(maxLength , right-left+1);
            // If the problem states atmost K unique rather than exactly k , then we should remove the if statement that`s it
			
			right++;
		}
		return maxLength == 0 ? -1 : maxLength;
    }
    
}
