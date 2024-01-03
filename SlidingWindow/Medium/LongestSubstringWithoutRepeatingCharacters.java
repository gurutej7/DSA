package SlidingWindow.Medium;

import java.util.HashMap;

/* 3. Longest Substring Without Repeating Characters
link { https://leetcode.com/problems/longest-substring-without-repeating-characters/description/ }
Example 1:
Input: s = "abcabcbb"                       Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"                          Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"                         Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "pwwkew";
        String S = "tmmzuxt" ; // output - 5
        System.out.println(  lengthOfLongestSubstring(s)  );
        System.out.println(  lengthOfLongestSubstring2(S)   );

        System.out.println(  lengthOfLongestSubstring3(S)  );


    }

    // Naive Solution will be using Two for loops  O(N^2) (will give TLE)

    //My solution that I submitted for the 1st Time after knowing sliding window concept
     public static int lengthOfLongestSubstring(String s) {
        int left = 0 , right = 0 , maxLength = 0 , n = s.length();
        if(n <=1 ) return n ;
        HashMap<Character,Integer> map  = new HashMap<>();
        while(right < n){
            //the current char is appearing for the first time , consider it to be part of substring  
            if(!map.containsKey(s.charAt(right))){
                // Record or save its appearance so we can identify when we encounter it again
                map.put(s.charAt(right), 1);
                // compute length and update maxLength
                maxLength = Math.max(maxLength , right-left + 1);
                right++;
            }
            else{
                //Remove starting characters until we skip the repeated character`s first instance
                map.remove(s.charAt(left));
                left++;
            }

        }
        return maxLength;
    }

    //Another solution is to play with indices 
    //In the above approach , in the else condition we are looping till we skip the repeated char`s first instance
    // This can be easily done without looping , if we have the index of its first occurence
    // SO in the 1st if condition while putting in to the map , include the corresponding index
    public static int lengthOfLongestSubstring2(String s) {
        int left = 0 , right = 0 , maxLength = 0 , n = s.length();
        if(n <=1 ) return n ;
        HashMap<Character,Integer> map  = new HashMap<>();
        while(right < n){
            
            // If it has already appeared then then skip it first instance and update left
            if (map.containsKey(s.charAt(right))) 
                // We are taking max because the current character`s first occurrence may be skipped while skipping
                 //the first instance of some other character ,if We dont need to repeat some operations ,take max
                left = Math.max(map.get(s.charAt(right)) + 1, left);

            map.put(s.charAt(right), right);
            maxLength = Math.max(maxLength , right-left + 1);
            right++;
            

        }
        return maxLength;
    }

    // Another approach withour using map
    public static int lengthOfLongestSubstring3(String S) {
        
        //SLIDING WINDOW  - TIME COMPLEXITY O(2n)
        //                  SPACE COMPLEXITY O(m)   //size of array
        char s[] = S.toCharArray();
        int store[]=new int[256]; //array to store the occurences of all the characters
        int l=0;    //left pointer
        int r=0;    //right pointer
        int ans=0;  //initializing the required length as 0
        
        while(r<S.length())     //iterate over the string till the right pointer reaches the end of the string 
        {
            store[s[r]]++;      //increment the count of the character present in the right pointer 
            
            while(store[s[r]]>1)    //if the occurence become more than 1 means the char is repeated
            { 
                store[s[l]]--;   //reduce the occurence of temp as it might be present ahead also in the string
                l++;         //contraction of the present window till the occurence of the 't' char becomes 1
            }
            
            ans = Math.max(ans,r-l+1);    //As the index starts from 0 , ans will be (right pointer-left pointer + 1)
            r++;        // now will increment the right pointer 
        }
        return ans;
    }
}
