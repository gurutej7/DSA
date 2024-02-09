package BinarySearchTrees.Easy;

import java.util.ArrayList;

import BinarySearchTrees.TreeNode;

/* 98. Validate Binary Search Tree

Given the root of a binary tree, determine if it is a valid binary search tree (BST).
A valid BST is defined as follows:
* The left subtree of a node contains only nodes with keys less than the node's key.
* The right subtree of a node contains only nodes with keys greater than the node's key.
* Both the left and right subtrees must also be binary search trees. 
 */

public class ValidateBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.insert(3);
        root.insert(1);
        root.insert(4);
        root.insert(6);
        root.insert(2);

        System.out.println( isValidBSTNaive(root) );
        System.out.println(isValidBST(root));

    }

    /*
     * naive Approach :
     * use a list to store the inorder of the given tree
     * if it is sorted then a BST else not
     */
    private static boolean isValidBSTNaive(TreeNode root) {
        if (root == null)
            return true;
        ArrayList<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        // check if list is sorted or not
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1))
                return false;
        }
        return true;
    }

    private static void inorderTraversal(TreeNode root, ArrayList<Integer> list) {
        if (root == null)
            return;

        inorderTraversal(root.left, list); // Traverse left subtree
        list.add(root.val); // Add current node value
        inorderTraversal(root.right, list); // Traverse right subtree
    }

    // better approach is providing the range in which the current node value should lie Time - O(N)
    private static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, long low, long high) {
        if (root == null)
            return true;

        if (root.val <= low || root.val >= high)
            return false;

        return isValidBST(root.left, low, (long) root.val) && isValidBST(root.right, (long) root.val, high);
    }
}
