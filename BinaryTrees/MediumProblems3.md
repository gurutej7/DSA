### 1. [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/)
* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
* Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
* We can use any traversal on the given root to traverse the tree and store the nodes and `null` values also included , because while constructing from the string if we dont have null values then we dont know when to terminate a subtree.
* The apply the reverse logic of traversal on the string to construct the tree.
    ```java
     // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return " ";
        StringBuilder tree = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr != null){
                tree.append(curr.val);
                tree.append(' '); // to separate between nodes
            }
            else{
                tree.append('n');
                tree.append(' '); // to separate between nodes
                continue;
            }
            q.offer(curr.left);
            q.offer(curr.right);
        }
        return tree.toString();    
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String [] str = data.split(" "); // str[i] has strings (node values) without spaces
        int n = str.length;
        if( n == 0 ) return null;
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        int i = 1 ;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty() && i < n){
            TreeNode curr = q.poll();
            if(str[i].equals("n")){
                curr.left = null;
                i++;
            }
            else{
                curr.left = new TreeNode(Integer.parseInt(str[i]));
                q.offer(curr.left);
                i++;
            }
            if(i < n && str[i].equals("n")){
                curr.right = null;
                i++;
            }
            else{
                curr.right = new TreeNode(Integer.parseInt(str[i]));
                q.offer(curr.right);
                i++;
            }
        }
        return root;
    }
    ```



* [Different Approaches](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/solutions/)



### 2. [Time to burn Tree](https://www.codingninjas.com/studio/problems/time-to-burn-tree_1469067?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf)
* You have a binary tree of 'N' unique nodes and a Start node from where the tree will start to burn. Given that the Start node will always exist in the tree, your task is to print the time (in minutes) that it will take to burn the whole tree.
* It is given that it takes 1 minute for the fire to travel from the burning node to its adjacent node and burn down the adjacent node.
* _For Example :_
For the given binary tree: [1, 2, 3, -1, -1, 4, 5, -1, -1, -1, -1]
Start Node: 3
<pre> 
  1  
 / \  
2   3   
   / \  
   4  5   </pre>

_Output:_ 2

* _Explanation :_
    * In the zeroth minute, Node 3 will start to burn.
    * After one minute, Nodes (1, 4, 5) that are adjacent to 3 will burn completely.
    * After two minutes, the only remaining Node 2 will be burnt and there will be no nodes remaining in the binary tree. 
    * So, the whole tree will burn in 2 minutes.

* _Approach :_
    * First we have to find where the start node is , which can be done using a tree traversal.
    * Once we have figured out the `Start Node` the we have to travel in all directions (`upward` , `right` , `left`).
    * But Binary tree has only left and right pointers . So we have to manually introduce the parent pointer ,
    While searching for start node use a map to store parent node of the current node during the traversal.
    * Now we have start node and , pointers to travel in all direction.
    * Now use a `bfs` traversal for every node check  its left,right,parent if they are burnt or not ,( use a set data structure to keep track of the burnt nodes).
    * If any one of the corresponding node of current node is not burnt then increase the time by 1 ;
    * at the end return final value of time.
* Why not [DFS](https://www.youtube.com/watch?v=2r5wLmQfD6g&t=710s)


### 3.[Morris Inorder Traversal of a Binary Tree](https://takeuforward.org/data-structure/morris-inorder-traversal-of-a-binary-tree/)
* Normal Traversals `Time - O(N)`  and `Space - O(N)`
* Morris Traversals `Time -O(N)` and `Space - O(1)`
* Morris Traversals uses the concept of Threaded Binary Tree.
* Normal Inorder => `left ->  root   -> right ` .


### 4.[Morris PreOrder Traversal of a Binary Tree](https://takeuforward.org/data-structure/morris-preorder-traversal-of-a-binary-tree/)


### 5.[Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/)
* Given the `root` of a binary tree, flatten the tree into a "linked list":
* The "linked list" should use the same `TreeNode` class where the `right` child pointer points to the next node in the list and the `left` child pointer is always `null`.
* The "linked list" should be in the same order as a **pre-order traversal** of the binary tree.
* _Example :_ 


    * ![flattenExample](resources/imagesMD/flaten.jpg)


    * _Input:_  root = [1,2,5,3,4,null,6]
    * _Output:_  [1,null,2,null,3,null,4,null,5,null,6]
* _Observation :_ If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal. (Could no notice until I saw the hint ( `(:` need to improve more observation haki) )
* Code using a Extra Data Structure :
    ```java
     public void flatten(TreeNode root) {
        if(root == null) return;
        ArrayList<TreeNode> list = new ArrayList<>();
        pre(root,list);
        TreeNode curr = root;
        for(int i = 1 ; i < list.size() ; i++){
            curr.right = list.get(i);
            curr.left = null;
            curr = curr.right;
        }

        return ;
    }
     private static void pre(TreeNode root , ArrayList<TreeNode> list){
         if(root == null) return ;
         list.add(root);
         pre(root.left,list);
         pre(root.right,list);
    }
    ```

* But the Interviewer wont be happy with that , Follow up is to do the above task without using any Extra Space `O(1)`
* Basically, the traversing order after flattening is `pre order traversal` in `(root, left, right)`, like
<pre>
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6 </pre>
* From the original Tree
<pre>
    1
   / \
  2   5
 / \   \
3   4   6 </pre>

* If we traverse the flattened tree in the reverse way, we would notice that `[6->5->4->3->2->1]` is in `(right, left, root)` order of the original tree. So the reverse order after flattening is `post order traversal` in `(right, left, root)` order like `[6->5->4->3->2->1]`.
* The idea is to traverse the original tree in this order by
*   ```java
    public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    }
    ```

* and then set each node's right pointer as the previous one in `[6->5->4->3->2->1]`, as such the right pointer behaves similar to a link in the flattened tree(though technically, it's still a right child reference from the tree data structure's perspective) and set the left child as null before the end of one recursion by
*   ```java
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
    ```




