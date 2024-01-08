package SlidingWindow.Hard;

/*  Minimum Window Subsequence
link { https://rb.gy/18yntj }

You are given two strings ‘S’ and ‘T’. Your task is to find the minimum (Contiguous) substring ‘W’ of ‘S’, such
 that ‘T’ is a subsequence of ‘W’
A subsequence is a sequence that can be derived from another sequence by removing zero or more elements, without
 changing the order.
A substring is a contiguous part of a string.

Sample Input 1 :
s = "rdew"  t = "u"                                 output : ""
s = "abcdebdde"  t = "bde"                          output : "bcde"

For test case 1 :
Since there is no window in ‘S’ which covers all characters of ‘T’ so therefore we returned an empty string.
For test case 2 :
“bcde” is the substring of minimum length in which we find “bde”. “bdde” is also a substring of minimum length
 however the index of “bcde” occurs first, therefore we returned bcde
*/

public class MinimumWindowSubsequence {
    public static void main(String[] args) {
        String s = "abcdebdde";
        String t = "bde" ;

        System.out.println(   minWindow(s, t)  );
    }

    public static String minWindow(String s, String t) {
        int sIndex = 0, tIndex = 0;    // Keep track of index in s and t
        int ansStart = 0, ansEnd = 0;  // Final ans subString , starting and ending index
        // We are using these variables , to avoid generating substring for every valid case (to save time)
        int currAnsStart = 0, currAnsEnd = 0;      // If we have found a valid window, to store indices of that window

        int m = s.length(), n = t.length();
        int minLength = Integer.MAX_VALUE;
    
        while (sIndex < m) {
            // If we have a character match
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                // and also it is the last charater in the String t , it means it is a valid window 
                if (tIndex == n - 1) {
                    currAnsEnd = sIndex;
                    // TO find starting index, traverse backwards till all the characters of t is found i.e, tIndex == 0
                    while (tIndex >= 0) {
                        if (s.charAt(sIndex) == t.charAt(tIndex)) // if characters are equal then only decrease tIndex
                            tIndex--;
                        
                        sIndex--;
                    }
                    // After we have seen all the characters of t  , update startIndex
                    // After 
                    tIndex = 0;
                    sIndex++; // Since we have went one char backward after finding the all characters , set it to correct position 
                    currAnsStart = sIndex ;
                    // If We have found the starting index and ending index , its time to calculate the length of the window
                    // Update minLength if required
                    if (currAnsEnd - currAnsStart + 1 < minLength) {  
                        ansStart = currAnsStart;
                        ansEnd = currAnsEnd;
                        minLength = currAnsEnd - currAnsStart + 1;
                    }
                    
                } 
                // If it is a character match but not end , so just increment tIndex
                else {
                    tIndex++;
                }
    
            }
            sIndex++;
        }
    
        return minLength == Integer.MAX_VALUE ? "" : s.substring(ansStart, ansEnd + 1);
    }
    
}
