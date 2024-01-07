package SlidingWindow.Medium;


/*  Longest Substring with Atmost K Distinct Characters

link  { https://rb.gy/4t3vka }

You are given a string 'str' and an integer ‘K’. Your task is to find the length of the largest substring with at
 most ‘K’ distinct characters.

For example:
You are given ‘str’ = ‘abbbbbbc’ and ‘K’ = 2, then the substrings that can be formed are [‘abbbbbb’, ‘bbbbbbc’].
 Hence the answer is 7.



*/
public class LongestSubstringWithatMostKDistinctCharacters {
    public static void main(String[] args) {

        String str = "abbbbbbc";
        int k = 2;

        System.out.println( kDistinctChars(k, str) );
        
    }


    // Simple sliding window approach
    // The current window characteristics is corresponding to the current substring
    public static int kDistinctChars(int k, String str) {
		// Write your code here
        // count array to store the frequency of the current character in the window 
		int count[] = new int[26];
		int distinctCount  = 0 ;
		int left = 0 , right = 0 , n = str.length();
		int maxLength = 0 ;
		while(right < n){
            // If the character is new increment the distinctCount
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
            // Keeping track of the maxLength in the every window
			maxLength = Math.max(maxLength , right-left+1);
			right++;
		}
		return maxLength;
	}
    
}
