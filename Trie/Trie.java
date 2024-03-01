package Trie;
// Leetcode Practice { https://leetcode.com/problems/implement-trie-prefix-tree/description/ }

public class Trie {
    public TrieNode root;
    public Trie(){
        this.root = new TrieNode();
    }

    // inserts a word into the trie
    // TC -> O(n) , where n = word.length()
    public void insert(String word){
        TrieNode curr = this.root;
        for(int i = 0 ; i < word.length() ; i++){
            char ch = word.charAt(i);
            // if curr node does`nt have a kid(ch) , the assign kid(ch) which comes along with 26 kids of its own
            if(!curr.hasKid(ch)) curr.assignKids(ch, new TrieNode()); 
            curr = curr.getKidsof(ch); // move curr pointer to the next one
            curr.increaseCountOfPrefix();
        }
        curr.setIsEnd(); // mark the word is ending here
        curr.increaseCountOfEndsWith();
    }

    // Checks if there is a word in the trie that matches the given word as a prefix
    public TrieNode prefixChecker(String word){
        TrieNode curr = this.root;
        for(int i = 0 ; i < word.length() ; i++){
            char ch = word.charAt(i);
            if(!curr.hasKid(ch)) return null; // meaning we were not able to find the entire word
            curr = curr.getKidsof(ch); 
        }
        return curr;
    }

    // checks if there is a word in the trie that matches , the given word till provided length
    // useful  when checking for substrings of a word , without generating the actual substring of the word
    public TrieNode prefixChecker(String word,int len){
        TrieNode curr = this.root;
        for(int i = 0 ; i < len ; i++){
            char ch = word.charAt(i);
            if(!curr.hasKid(ch)) return null; // meaning we were not able to find the entire word
            curr = curr.getKidsof(ch); 
        }
        return curr;
    }

    // returns if the given word is present in the trie or not
    public boolean search(String word){
        TrieNode temp = prefixChecker(word);
        return temp != null && temp.isEnd();
    }

    // returns true if the given word , till the provided length is present in the trie or not
    public boolean search(String word,int len){
        TrieNode temp = prefixChecker(word,len);
        return temp != null && temp.isEnd();
    }

    // returns true if all prefixes of the given word exist`s in the trie
    public boolean checkAllPrefixes(String word){
        TrieNode curr = this.root;
        for(int i = 0 ; i < word.length() ; i++){
            char ch = word.charAt(i);
            if(curr.hasKid(ch)){ // if current char exist , then go to the reference trie , and check if word ends there or not
                curr = curr.getKidsof(ch);
                if(curr.isEnd() == false) return false;
            }
            else return false;
        }
        return true;
    }

    // returns if there exists a word in the trie that starts with the given trie
    public boolean startsWith(String word){
        return prefixChecker(word) != null;
    }

    // returns the number of words in the trie that are equal to the given word
    public int countWordsEqualTo(String word) {
        TrieNode temp = prefixChecker(word);
        return temp != null ?  temp.getEndsWithCount() : 0 ;
    }

    // returns the number of words in the trie that has a given word as their prefix
    public int countWordsStartingWith(String word) {
        TrieNode temp = prefixChecker(word);
        return temp != null ? temp.getPrefixCount() : 0;
    }    
}

