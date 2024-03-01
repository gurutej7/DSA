package Trie;

public class TrieNode{
    private TrieNode[] kids;
    private boolean isEnd; // by default false
    private int countEndsWith;
    private int countPrefix;
    public TrieNode(){
        this.kids = new TrieNode[26];  // by default values here are null
        this.countEndsWith = 0 ;
        this.countPrefix = 0 ;
    }
    // if current node has a kid (ch = in range (a-z)) or not
    public boolean hasKid(char ch){
        return this.kids[ch-'a']!=null;
    }
    // get the kids of a particular kid in the current node 
    public TrieNode getKidsof(char ch){
        return this.kids[ch-'a'];
    }
    // assing new kids to the already existing kid in our current node
    public void assignKids(char ch , TrieNode newKids){
        this.kids[ch-'a'] = newKids;
    }

    // set that the word is ending here
    public void setIsEnd(){
        this.isEnd = true;
    }
    // returns if the word ends here or not
    public boolean isEnd(){
        return this.isEnd;
    }

    // increase the count of prefix
    public void increaseCountOfPrefix(){
        this.countPrefix++;
    }

    // increase the count of words Ending here
    public void increaseCountOfEndsWith(){
        this.countEndsWith++;
    }

    // returns the number of words that has a prefix till this char
    public int getPrefixCount(){
        return this.countPrefix;
    }

    // returns the number of words has this node as a end
    public int getEndsWithCount(){
        return this.countEndsWith;
    }
}

// Instead of using the array we can also use a HashMap<Character,TrieNode> , if the characters are not in range a-z