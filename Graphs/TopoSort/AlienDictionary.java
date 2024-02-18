package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/* Alien Dictionary
{ https://www.geeksforgeeks.org/problems/alien-dictionary/1 } 

Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary. Find 
the order of characters in the alien language.
Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will 
be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.

Example 1:  Input:  N = 5, K = 4    dict = {"baa","abcd","abca","cab","cad"}
Output: "bdac"
Explanation:
Here order of characters is 
'b', 'd', 'a', 'c' Note that words are sorted 
and in the given language "baa" comes before 
"abcd", therefore 'b' is before 'a' in output.
Similarly we can find other orders.
 */

public class AlienDictionary {
    public static void main(String[] args) {
        String[] dict = {"baa" , "abcd" , "abca" , "cab" , "cad"};
        int n = dict.length;
        int k = 4 ;

        System.out.println( findOrder(dict, n, k) );
        
    }

    // Key observation if there is a line in the problem , or requirement that one should always come before other one
    // Topo Sort comes in handy
    private static String findOrder(String [] dict, int N, int K){
        // Write your code here
        // If we write down the examples , then we can observe that
        // there is a `x` , which should always appear before `y`
        // We can create a directed graph based on the alphabets which should come before which
        // Then if we print the topological sort of that order , it will be equal to alien dict order
        // To form the graph with alphabets , it is somewhat complicated 
        // to simplify let 0 = a and so on 25 = z ;
        // Given there will be only First K alphabets
        // our vertices count V = k-1 (we are considering 0 based)
        
        // How we figure out which appears before which
        // compare dict[i] and dict[i+1] , first char which is different 
        // then there is an edge between that char in dict[i] -> corresponding char in dict[i+1];
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i = 0 ; i < K ; i++) adj.add(new ArrayList<>());
        
        // constructing the adj list
        for(int ind = 0 ; ind < N-1 ; ind++){
            String word1 = dict[ind];
            String word2 = dict[ind+1];
            int len = Math.min(word1.length(),word2.length());
            int i = 0 ;
            while(i < len && word1.charAt(i) == word2.charAt(i)) i++;
            
            if(i < len) {
                int a = word1.charAt(i) - 'a';
                int b = word2.charAt(i) - 'a';
                
                // there is a  edge between a -> b
                adj.get(a).add(b);
            }
        }
        // Toposort logic goes here
        int indegree[] = new int[K];
        // List<Integer> topoSort = new ArrayList<>();
        StringBuilder topoSort = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0 ; i < K ; i++){
            for(int j : adj.get(i)) indegree[j]++;
        }
        
        for(int i = 0 ; i < K ; i++ ) 
            if(indegree[i] == 0) q.offer(i);
        
        while(!q.isEmpty()){
            int curr = q.poll();
            char currChar = (char)(curr+97); // coverting int to char =>  0 - a ... 25 - z
            topoSort.append(currChar);
            
            for(int i : adj.get(curr)){
                indegree[i]--;
                if(indegree[i] == 0) q.offer(i);
            }
        }
        // System.out.print(topoSort);
        
        return topoSort.toString();
    }
    
}
