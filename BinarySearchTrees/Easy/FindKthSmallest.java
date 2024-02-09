package BinarySearchTrees.Easy;

import java.util.ArrayList;

import BinarySearchTrees.TreeNode;

/* 230. Kth Smallest Element in a BST
{ https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/ }
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
Example 1 : Input: root = [3,1,4,null,2], k = 1             Output: 1
Example 2 : Input: root = [5,3,6,2,4,null,null,1], k = 3    Output: 3
 */

public class FindKthSmallest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.insert(3);
        root.insert(1);
        root.insert(4);
        root.insert(6);
        root.insert(2);
        int k = 3 ;

        System.out.println( kthSmallestNaive(root, k) );
        System.out.println( kthSmallest(root, k) ); // expected 3
    }
    
    // naive approach using a list to store the inorder of the BST (inorder is in sorted order => property of BST)
    // Time - O(N) , Space - O(N)
    private static int kthSmallestNaive(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderTraversal(root,list);

        return list.get(k-1);
    }
    private static void inorderTraversal(TreeNode root,ArrayList<Integer> list) {
        if (root == null)
            return;

        inorderTraversal(root.left,list); // Traverse left subtree
        list.add(root.val); // Add current node value
        inorderTraversal(root.right,list); // Traverse right subtree
    }

    // without using a list
    private static int kthSmallest(TreeNode root, int k) {
        TreeNode curr = kthSmallestHelper(root, new int[]{k});
        return curr.val;
    }
    
    private static TreeNode kthSmallestHelper(TreeNode root, int k[]) {
        if (root == null)
            return null;
    
        TreeNode left = kthSmallestHelper(root.left, k);
        // left not null means we have found the kth guy
        if (left != null)
            return left;
    
        k[0]--;// when we are at kth  element then return
        if (k[0] == 0)
            return root;
    
        return kthSmallestHelper(root.right, k);
    }

    // To find the kth max , first find th n -> number of nodes then use the above approach to find (n-k)th smallest element
}
