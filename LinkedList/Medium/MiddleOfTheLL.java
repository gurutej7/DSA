package LinkedList.Medium;

import LinkedList.Easy.*;

/* 876. Middle of the Linked List
    Given the head of a singly linked list, return the middle node of the linked list.
    If there are two middle nodes, return the second middle node.

*/

public class MiddleOfTheLL{

    public static void main(String[] args) {

        //Create a Linked List 
        LL list = new LL();
        // add elements to the LinkedList
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

    public static LL.Node middleNode(LL.Node head) {
        if(head==null || head.next==null){
            return head;
        }
        LL.Node sp = head;
        LL.Node fp = head;
         while(fp != null && fp.next!= null && fp.next.next!=null ){
            sp = sp.next ;
            fp = fp.next.next ;
        }
        if(fp.next !=null) return sp.next;
        else return sp;
        
    }
}