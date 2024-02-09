package BinarySearchTrees;

import java.util.ArrayList;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;
    public ArrayList<Integer> list = new ArrayList<>();

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public void insert(int data) {
        if (data < val) {
            if (left == null) {
                left = new TreeNode(data);
            } else {
                left.insert(data);
            }
        } else {
            if (right == null) {
                right = new TreeNode(data);
            } else {
                right.insert(data);
            }
        }
    }

    public void deleteNode(int key) {
        TreeNode root = this;
        root = deleteNode(root,key);
        
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) return deleteHelper(root);

        TreeNode curr = root;

        while(curr != null){
            if(curr.val > key){
                if(curr.left != null && curr.left.val == key){
                    curr.left = deleteHelper(curr.left);
                    break;
                }
                else curr = curr.left;
            }
            else{
                if(curr.right != null && curr.right.val == key){
                    curr.right = deleteHelper(curr.right);
                    break;
                }
                else curr = curr.right;
            }
        }

        return root;
    }

    public TreeNode deleteHelper(TreeNode root){
        if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else{
            TreeNode rightChild = root.right;
            TreeNode lastRight = findLastRight(root.left);
            lastRight.right = rightChild;
            return root.left;
        }
    }
    
    public TreeNode findLastRight(TreeNode root){
        if(root.right == null) return root;

        return findLastRight(root.right);
    }


    public int minValue() {

        TreeNode current = this;
        while (current.left != null) {
            current = current.left;
        }
        return current.val;
    }

    public int maxValue() {
        TreeNode current = this;
        while (current.right != null) {
            current = current.right;
        }
        return current.val;
    }

    public void inorder() {
        list.clear(); // Clear the list before traversing
        inorderTraversal(this);
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null)
            return;

        inorderTraversal(root.left); // Traverse left subtree
        list.add(root.val); // Add current node value
        inorderTraversal(root.right); // Traverse right subtree
    }

    public void preorder() {
        list.clear();
        preorderTraversal(this);
    }

    private void preorderTraversal(TreeNode root) {
        if (root == null)
            return;
        // root -> left -> right
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public void postorder() {
        list.clear();
        postorderTraversal(this);
    }

    private void postorderTraversal(TreeNode root) {
        if (root == null)
            return;
        // left -> right -> root
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);
    }
}
