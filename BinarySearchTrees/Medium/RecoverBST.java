package BinarySearchTrees.Medium;

import BinarySearchTrees.TreeNode;

/* 99. Recover Binary Search Tree
{ https://leetcode.com/problems/recover-binary-search-tree/description/ }

You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 */

public class RecoverBST{
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(1);   // it is a violated tree we have to fix it
        // Before fixing inorder 4 3 1

        FixBST(root);
        
        // after fixing inorder 1 3 4
        root.inorder();
        System.out.println( root.list );
    }
    // video explanation {https://www.youtube.com/watch?v=ZWGW7FminDM&t=897s }
    private static TreeNode first;
    private static TreeNode last;
    private static TreeNode middle;
    private static TreeNode prev;
    private static void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);

        if(prev != null && root.val < prev.val){
            if(first == null){
                first = prev;
                middle = root;
            }
            else last = root;
        }
        prev = root;
        inorder(root.right);
    }
    public static void FixBST(TreeNode root) {
        // Write your code here
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorder(root);
        if(first != null && last != null){
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        }
        else if(first != null && middle != null){
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }

        return ;
    }

}