package Trie.problems;

import Trie.Trie;
import Trie.TrieNode;

/* 14. Longest Common Prefix
{ https://leetcode.com/problems/longest-common-prefix/description/ }

Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:      Input: strs = ["flower","flow","flight"]
                Output: "fl"
 */

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};

        System.out.println(longestCommonPrefix(strs));
    }

    private static String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if(n == 0) return "";
        TempTrie root = new TempTrie();

        for(String word : strs) root.insert(word);

        return root.longestCommonPrefix(strs[0] , n);

        // Another Greedy approach is , sort the array
        // and compare strs[0] and strs[n-1] , the common prefix of them is the answer
    }
    
}

class TempTrie extends Trie{

    public String longestCommonPrefix(String word , int n){
        TrieNode curr = this.root;
        for(int i = 0 ; i < word.length() ; i++){
            char ch = word.charAt(i);
            curr = curr.getKidsof(ch);
            
            // getPrefixCount returns the number of words , that are having the current wordSubstring(0,i) as prefix
            if(curr.getPrefixCount() != n) // if at any char if we don`t have n(array length) as prefixCount , till there then return from there
                return word.substring(0,i);
            
        }
        return word;
    }
}
