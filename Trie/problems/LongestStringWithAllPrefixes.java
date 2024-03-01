package Trie.problems;

import java.util.Arrays;

import Trie.Trie;

/* Complete String
{ https://www.codingninjas.com/studio/problems/complete-string_2687860 }

A string is called a complete string if every prefix of this string is also present in the array ‘A’. Ninja is 
challenged to find the longest complete string in the array ‘A’.If there are multiple strings with the same 
length, return the lexicographically smallest one and if no string exists, return "None".

Example 1 : Input : ["n", "ni", "nin", "ninj", "ninja" , "ninga"] 
            Output : "ninja"
Example 2 : Input : ["ab" , "bc"]
            Output : "None"

 */

public class LongestStringWithAllPrefixes {
    public static void main(String[] args) {
        String[] test1 = {"n", "ni", "nin", "ninj", "ninja" , "ninga"};
        String[] test2 = {"ab" , "bc"};

        System.out.println(completeString(test1)); 
        System.out.println(completeString(test2));
    }

    private static String completeString(String[] a) {
        Trie root = new Trie();
        // O(N)*O(len) // len = average length of the words in the array
        for(String word : a) root.insert(word);

        int maxLen = 0 ;
        StringBuilder res = new StringBuilder();
        Arrays.sort(a); // we want lexicographically smaller one
        // so if i get ans of same length as existing one , i don`t need to compare and get the lexicographically smaller one
        // because they are in the sorted order , lexicographically smallest one will be 1st in the order of words with same lengths

        for(String word : a){
            // if all the prefixes of the current word , exists in the trie , the function will return true
            if(root.checkAllPrefixes(word) &&  word.length() > maxLen){
                maxLen = word.length();
                res = new StringBuilder(word);
            }
        }

        return maxLen == 0 ? "None" : res.toString();

    }
    
}
