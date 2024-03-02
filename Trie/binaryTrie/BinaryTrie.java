package Trie.binaryTrie;

public class BinaryTrie {
    public BinaryTrieNode root;
    int[] masks;

    public BinaryTrie(){
        this.root = new BinaryTrieNode();
        this.masks = new int[31];
        for(int i = 0 ; i < 31 ; i++) masks[i] = (1<<i);
    }

    // given a integer , the function will insert the number into the trie in the binary form
    public void insert(int num){
        BinaryTrieNode curr = this.root;

        for(int i = 30 ; i >= 0 ; i--){
            // int currBit = ((num>>>i) & (1));  // ==  (num) & (1<<i) != 0 ? 1 : 0 ;
            
            if((masks[i] & num) == 0){
                if(curr.left == null) curr.left = new BinaryTrieNode();
                curr = curr.left;
            }
            else{
                if(curr.right == null) curr.right = new BinaryTrieNode();
                curr = curr.right;
            }
            curr.freq++;
        }
    }

    public void delete(int num){
        BinaryTrieNode curr = this.root;
        for(int i = 30 ; i >= 0 ; i--){
            // int currBit = (masks[i]&num) != 0 ? 1 : 0;
            if((masks[i] & num) == 0) curr = curr.left;
            else curr = curr.right;
            curr.freq--;
        }

    }

    // given a array of integers , insert them into the trie
    public void insertArrayOfInt(int[] nums){
        for(int num : nums) this.insert(num);
    }

    public int getMaxXor(int x){
        int max = 0 ;
        BinaryTrieNode curr = this.root;
        for(int i = 30 ; i >= 0 ; i--){
            if((masks[i]&x) == 0){
                if(curr.right!=null && curr.right.freq>0) {
                    max += masks[i];
                    curr = curr.right;
                }
                else curr = curr.left;
            }
            else{
                if(curr.left != null && curr.left.freq>0){
                    max += masks[i];
                    curr = curr.left;
                }
                else curr = curr.right;
            }
        }
        
        return max;
    }
}
