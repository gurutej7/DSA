package Trie.binaryTrie;

public class BinaryTrieNode {
    public BinaryTrieNode left ;
    public BinaryTrieNode right; 
    int freq;

    public BinaryTrieNode(){
        this.left = null;
        this.right= null;
        this.freq = 0 ;
    }
}