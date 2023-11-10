package LinkedList.Medium;

import LinkedList.Easy.*;

/* 876. Middle of the Linked List
    Given the head of a singly linked list, return the middle node of the linked list.
    If there are two middle nodes, return the second middle node.
    Example 1:
    Input: head = [1,2,3,4,5]
    Output: [3,4,5]
    Explanation: The middle node of the list is node 3.
*/

public class MiddleOfTheLL{

    public static void main(String[] args) {

        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        LL.Node ans = middleNode(list.head);

        list.printList();

        System.out.println(ans.data);

        ans.printListNode(ans);
        
    }
    /*
      we increment slow ptr by 1 and fast ptr by 2, so if take a close look fast ptr will travel double that of 
      the slow pointer. So when the fast ptr will be at the end of the Linked List, slow ptr would have covered 
      half of the Linked List till then. So slow ptr will be pointing towards the middle of Linked List.
     */
    public static LL.Node middleNode(LL.Node head) {
        if(head==null || head.next==null){
            return head;
        }
        LL.Node sp = head;
        LL.Node fp = head;
         while(fp != null && fp.next!= null ){
            sp = sp.next ;
            fp = fp.next.next ;
        }
        
        return sp;
        
    }
}