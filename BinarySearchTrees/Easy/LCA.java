package BinarySearchTrees.Easy;

import BinarySearchTrees.TreeNode;

/* 235. Lowest common Ancestor of a Binary Tree
{ https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/ }

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q 
as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6. 
 */

public class LCA{
    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.insert(2);
        root.insert(8);
        root.insert(0);
        root.insert(4);
        root.insert(7);
        root.insert(9);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);

        System.out.println( lowestCommonAncestor(root, p, q).val  );
        
    }
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        
        int curr = root.val;
        // Check on which sub tree both the nodes are existing
        if(curr > p.val && curr > q.val) return lowestCommonAncestor(root.left,p,q);
        if(curr < p.val && curr < q.val) return lowestCommonAncestor(root.right,p,q);
        // if they did not exist on the same tree then the path to them splits here so return 
        return root;

    }
}