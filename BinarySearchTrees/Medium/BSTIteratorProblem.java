package BinarySearchTrees.Medium;

// import java.util.ArrayList;
import java.util.Stack;

import BinarySearchTrees.TreeNode;

/* 173. Binary Search Tree Iterator
{ https://leetcode.com/problems/binary-search-tree-iterator/description/ }

Example : Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]

Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False
 */

public class BSTIteratorProblem {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.insert(7);
        root.insert(9);
        root.insert(15);
        root.insert(20);

        BSTIterator it = new BSTIterator(root);

        System.out.println(it.next());// return 3
        System.out.println(it.next());// return 7
        System.out.println(it.hasNext());// true
        System.out.println(it.next());// return 9
        System.out.println(it.hasNext());//return True
        System.out.println(it.next()); //15
        System.out.println(it.hasNext());//return True
        System.out.println(it.next());//return 20
        System.out.println(it.hasNext());//return False
        
    }
    
}


// naive approach 
// using O(N) space
// class BSTIterator {
//     private int index = 0 ;
//     private ArrayList<Integer> list;

//     public BSTIterator(TreeNode root) {
//         list = new ArrayList<>();
//         inorder(root,this.list);
//     }

//     public void inorder(TreeNode root , ArrayList<Integer> list){
//         if(root == null) return  ;
//         inorder(root.left,list);
//         list.add(root.val);
//         inorder(root.right,list);
//     }
    
//     public int next() {
//         index++;
//         return list.get(index-1);
        
//     }
    
//     public boolean hasNext() {
//         return index < list.size();
        
//     }
// }
// Space - O(H)
class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        pushAll(root);
    }
    
    public int next() {
        TreeNode temp = stack.pop();
        pushAll(temp.right);
        return temp.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
        
    }

    private void pushAll(TreeNode root){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        return;
    }
}