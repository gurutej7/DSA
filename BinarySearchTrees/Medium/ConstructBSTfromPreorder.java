package BinarySearchTrees.Medium;

import BinarySearchTrees.TreeNode;

/* 1008. Construct Binary Search tree from Preorder Traversal
{ https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/ }

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root
It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
Input: preorder = [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
 */

public class ConstructBSTfromPreorder {
    public static void main(String[] args) {

        int preorder [] = {8,5,1,7,10,12};

        TreeNode root = bstFromPreorder1(preorder);
        // to check after the operation for the tree whether we get the same preorder or not
        root.preorder();
        System.out.println( root.list ); // expected [8,5,1,7,10,12];

        root = bstFromPreorder2(preorder);

        root.postorder();
        System.out.println( root.list ); // expected [1,7,5,12,10,8]
    }

    // Approach 1 -> we know that in preorder the 1st element is the root -> we can create root and insert the remaining elements in to the tree
    private static TreeNode bstFromPreorder1(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }

        return root;

    }

    // for every node we are determining where to insert O(N*N) -> in case of skewed BT
    private static void insert(TreeNode root, int data) {
        if (data < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(data);
            } else {
                insert(root.left, data);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(data);
            } else {
                insert(root.right, data);
            }
        }
    }

    // Approach 2 -> sort the preorder then we will get the inorder -> then construct the Binary tree from preorder and inorder
    // Time O(NlogN) + O(N)

    // Approach -3 , by keeping a upper bound for each node to decide whether the current value goes on the right side or the left side
    private static TreeNode bstFromPreorder2(int[] preorder) {
        return bstFromPreorder(preorder,Integer.MAX_VALUE,new int[]{0});
    }

    private static TreeNode bstFromPreorder(int[] preorder , int bound ,int[] index){
        if(index[0] == preorder.length || preorder[index[0]] > bound) return null;

        TreeNode root = new TreeNode(preorder[index[0]++]);
        root.left = bstFromPreorder(preorder,root.val,index);
        root.right = bstFromPreorder(preorder,bound,index);

        return root;
    }

}
