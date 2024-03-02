package Trie.problems.mapImplementation;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class TrieUsingMap {

    private class TrieNode{
        private Map<Character,TrieNode> kids;
        private boolean end;
    
        public TrieNode(){
            this.kids= new HashMap<>();
        }
    
       
        public boolean hasKids(char c){
            return kids.containsKey(c);
        }
    
        public TrieNode getKidsOf(char c){
            return kids.get(c);
        }
    
        public void assignKids(char c){
            kids.put(c,new TrieNode());
        }
    
        public boolean isEnd(){
            return end;
        }
    
        public void setEnd(){
            end = true;
        }
    }
 
    private class Trie{
        private TrieNode root;
    
        public Trie(){
            this.root = new TrieNode();
        }
    
        public void insert(String word){
            TrieNode node = root;
            for(int i=0;i<word.length();i++){
                char cur = word.charAt(i);
                if(!node.hasKids(cur)){
                    node.assignKids(cur);
                }
                node = node.getKidsOf(cur);
            }
            node.setEnd();
        }
    
        private TrieNode prefixChecker(String word){
            TrieNode node = root;
            for(int i=0;i<word.length();i++){
                char cur = word.charAt(i);
                if(!node.hasKids(cur)) return null;
                node = node.getKidsOf(cur);
            }
            return node;
        }
    
        public boolean search(String word){
            TrieNode node = prefixChecker(word);
            return node!=null && node.isEnd();
        }
    
        public boolean startsWith(String word){
            TrieNode node = prefixChecker(word);
            return node!=null;
        }
    }
}
