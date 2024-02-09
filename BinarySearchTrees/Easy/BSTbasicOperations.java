package BinarySearchTrees.Easy;

import BinarySearchTrees.TreeNode;

public class BSTbasicOperations {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);

        insertIntoBST(root,15);
        insertIntoBST(root, 5);
        insertIntoBST(root, 2);
        insertIntoBST(root, 1);
        insertIntoBST(root, 18);
        insertIntoBSTrecusive(root, 20);
        root.inorder();
        System.out.println( root.list ) ; // expected [1,2,5,10,15,18,20]
        System.out.println(  findCeil(root, 3) ); // expected 5 
        System.out.println(  findFloor(root, 10)); // expected 10
        System.out.println( searchInBST(root, 15) ); // expected true
        System.out.println( searchInBST(root, 3) ); // expected false
        root = deleteinBST(root, 10);
        root = deleteinBST(root, 1);
        // root.deleteNode(15); // used methods of class TreeNode to check if they are working are not
        // root.deleteNode(18);
        // root.insert(25);
        // root.insert(30);
        root.inorder();
        System.out.println( root.list ); // after deleting expected => [2,5,15,18,20]


    }

    // smallest number that is greater than equal to given value
    // Ceil of an integer is the closest integer greater than or equal to a given number.
    // For example: arr[] = {1, 2, 5, 7, 8, 9}, key = 3.
    // The closest integer greater than 3 in the given array is 5. So, its ceil
    // value in the given array is 5.
    private static int findCeil(TreeNode node, int x) {
        int ans = -1;
        while (node != null) {
            if (node.val >= x) {
                ans = node.val;
                node = node.left;
            } else {
                node = node.right;
            }

        }
        return ans;
    }

    // (If k lies in BST then is floor equal to k,else floor is equal to previous
    // greater value) Note: k would never be less than the minimum element of tree.
    private static int findFloor(TreeNode node, int x) {
        int ans = Integer.MIN_VALUE;
        while (node != null) {
            if (node.val > x) {
                node = node.left;
            } else {
                ans = Math.max(ans, node.val);
                node = node.right;
            }

        }
        return ans;
    }

    // insert a value into the binary search tree // recursive approach 
    // Time - O(log(N))  and space - O(log(N))
    private static TreeNode insertIntoBSTrecusive(TreeNode root, int val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }

        TreeNode curr = root;

        insert(val,curr);

        return root;
    }

    private static void insert(int data,TreeNode root) {
        if (data < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(data);
                return;
            }
             else 
                insert(data,root.left);
        }
         else {
            if (root.right == null) {
                root.right = new TreeNode(data);
                return;
            } 
            else 
                insert(data,root.right);
        }
    }

    // Iterative Time - O(log(N)) space - O(1)
    private static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }

        TreeNode curr = root;
        while(curr != null ){
            if(curr.val > val){
                if(curr.left != null) 
                    curr = curr.left;
                else{
                    curr.left = new TreeNode(val);
                    break;
                }
            }
            else{ // root.val < val
                if(curr.right != null) curr = curr.right;
                else {
                    curr.right = new TreeNode(val);
                    break;
                }

            }

        }
        return root;
    }

    private static boolean searchInBST(TreeNode root ,int val){
        while(root != null && root.val != val){
            root = root.val > val ? root.left : root.right;
        }

        return root != null && root.val == val;

        // recursive approach
        // if(root == null) return null;
        // if(root.val == val) return root;

        // if(root.val > val) return searchBST(root.left,val);
        // return searchBST(root.right,val);
    }

    // refer { https://www.youtube.com/watch?v=kouxiP_H5WE }
    private static TreeNode deleteinBST(TreeNode root , int key){
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

    private static TreeNode deleteHelper(TreeNode root){
        if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else{
            TreeNode rightChild = root.right;
            TreeNode lastRight = findLastRight(root.left);
            lastRight.right = rightChild;
            return root.left;
        }
    }
    
    private static TreeNode findLastRight(TreeNode root){
        if(root.right == null) return root;

        return findLastRight(root.right);
    }
}
