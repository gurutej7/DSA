package LinkedList.Medium;

import LinkedList.Easy.LL;

/*  206. Reverse Linked List
Given the head of a singly linked list, reverse the list, and return the reversed list.
Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:
Input: head = [1,2]
Output: [2,1]

 */

public class ReverseLL {

    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.printList();
        LL.Node ans = reverseList(list.head);

        ans.printListNode(ans);
    }


    /*
     * We will use three-pointers to traverse through the entire list and interchange links between nodes. One 
     pointer to keep track of the current node in the list. The second one is to keep track of the previous node to 
     the current node and change links. Lastly, a pointer to keep track of nodes in front of current nodes.
     */
    public static LL.Node reverseList(LL.Node head) {
        if(head == null || head.next == null) return head;
        LL.Node curr = head; 
        LL.Node prev = null;
        LL.Node Next = null;

        while(curr!=null) {
            Next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = Next;
        }
        return prev;
    }
    
}
