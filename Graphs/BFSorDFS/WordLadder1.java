package Graphs.BFSorDFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/* 127. Word Ladder 1
{ https://leetcode.com/problems/word-ladder/description/ }

Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of 
equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord.
Keep the following conditions in mind:
* A word can only consist of lowercase characters.
* Only one letter can be changed in each transformation.
* Each transformed word must exist in the wordList including the targetWord.
* startWord may or may not be part of the wordList
* The second part of this problem can be found here.
Note: If no possible way to transform sequence from startWord to targetWord return 0
Example 1:
Input:
wordList = {"des","der","dfr","dgt","dfs"}
startWord = "der", targetWord= "dfs",
Output:
3
Explanation:
The length of the smallest transformation
sequence from "der" to "dfs" is 3
i,e "der" -> "dfr" -> "dfs". 
 */

public class WordLadder1 {
    public static void main(String[] args) {
        String startWord = "der";
        String targetWord ="dfs";
        String[] wordList = {"des" ,"der" , "dfr" ,"dgt" ,"dfs"};

        System.out.println( wordLadderLength(startWord, targetWord, wordList) );
        
    }

    private static int wordLadderLength(String startWord, String targetWord, String[] wordList)
    {
        HashSet<String> set = new HashSet<>(); // To keep track of the words that we have transformed
        
        for( String s : wordList) set.add(s);
        
        if(set.contains(startWord)) set.remove(startWord); // If startWord is in the set remove it
        // because there is no point in transforming to the startWord
        if(!set.contains(targetWord)) return 0; 
        // If the final word is not in the list then we will never be able to make it to there
        
        
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(1,startWord)); // initially the transformation starts from level 1
        
        while(!q.isEmpty()){
            Pair curr = q.poll();
            int level = curr.getFirst();
            String s = curr.getStr();
            // at any point if we have made it to our target return the corresponding level
            if(s.equals(targetWord)) return level;
            
            for(int i = 0 ; i< s.length() ; i++){ // Time - O(s.length() * 26) * wordList.length
                // for our word , ford each character we will try out all possible ways 
                // i.e, replacing each index with chars 'a' - 'z'
                // if after replacing the word exist in the list , then it is a valid transformation , so push that into the q
                // with new level , and remove it from the set , because at any point in the future 
                // we dont want to transform to the word that we have already did in the past
                char [] arr = s.toCharArray();
                for(char ch = 'a' ; ch <= 'z' ; ch++){
                    arr[i] = ch;
                    String currStr = new String(arr);
                    if(set.contains(currStr)) {
                        q.offer(new Pair(level+1,currStr));
                        set.remove(currStr);
                    }
                }
            }
        }
        return 0 ;
    }
}
