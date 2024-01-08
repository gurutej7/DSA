package SlidingWindow.Hard;

/*  1358. Number of Substrings Containing all three characters
{ https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/ }

Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:   Input: s = "abcabc"                        Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:  Input: s = "aaacb"                          Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:  Input: s = "abc"                            Output: 1

*/

public class NumberOfSubstringsContainingAll3Char {
    public static void main(String[] args) {

        String s = "abcabc";

        System.out.println(   numberOfSubstrings3(s)  );
        
    }
    
    // Approach one sliding window
    public static int numberOfSubstrings1(String s) {
        int count[] = {0, 0, 0} ; // to keep track of count of a,b,c
        int res = 0 , n = s.length();
        int left = 0  , right = 0;
        while(right < n){
            count[s.charAt(right)-'a']++;

            while(count[0] > 0 && count[1] > 0 && count[2] > 0){ // we have a,b,c in our current window
                res += n-right; // The substrings that can be formed with repect to current position of right
                count[s.charAt(left)-'a']--;
                left++;
            }

            //res += left;   // [left-1 .. right] represents minimum length sub-array ending at right which has all three characters.
            // this subarray could be extended left till idx == 0 without compromising the count constraint
            // which is a total of length([0..left-1]) = left subarrays ending at right

            right++;
        }
        return res;
    }

    // Approach two three pointers
    /*
Take three pointers l1, l2, l3 for a, b and c respectively.
Now as you iterate over the string of length n, you can count the number of sub-strings ending at that particular index.
How can we do that is here ->
*   Keep on updating l1, l2 and l3.
*   And take the minimum of l1, l2 and l3.
*   Now from this min-index (min(l1, l2, l3) to the curr index i this is the smallest possible sub-string ending at 
    curr-index i which follows the constraint.
*   If the smallest sub-string is from min-index to curr-index, then for every sub-string starting from index 0, 1, 
2, 3, ......min-index and ending at curr-index is valid, (So how many are these...... So there are min-index + 1 
sub-strings)
*   Add this min-index + 1 to the count.
     */
    public static int numberOfSubstrings2(String s) {
        int n = s.length(), count = 0;
        int l1 = -1, l2 = -1, l3 = -1;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a') l1 = i;
            else if(s.charAt(i) == 'b') l2 = i;
            else l3 = i;
            if(l1 == -1 || l2 == -1 || l3 == -1) continue;
            int min = Math.min(l1, Math.min(l2, l3));
            count += min + 1;
        }
        return count;
    }

    // Approach 3 
    // Exactly K times = at most K times - at most K - 1 times
    // Exactly 3 times = at most 3 times - at most 2 times
    // Since the String has only a,b,c => that is atmost 3 will result in total number of substrings
    // Total number of substrings = n*(n+1)/2

    public static int numberOfSubstrings3(String s){
        int n = s.length();

        return n*(n+1)/2  - atMost(s,2);
    }

    public static int atMost(String s , int k){
        int left = 0 , right = 0;
        int count = 0 ;   // Use long for count in leetcode the testcase are very long strings
        int n = s.length();
        int distinctCount = 0;
        int map[] = new int [3] ; // Since we have only 3 characters
        while(right < n){
            if(map[s.charAt(right) - 'a'] == 0) // Appearing for the first time
                distinctCount++;

            map[s.charAt(right) - 'a']++;
            while(distinctCount > k){
                map[s.charAt(left) - 'a']--;
                if(map[s.charAt(left)-'a'] == 0) distinctCount--;
                left++;
            }

            count += right-left+1;
            right++;
        }
        return count;
    }

}
