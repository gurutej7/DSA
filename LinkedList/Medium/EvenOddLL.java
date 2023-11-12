package LinkedList.Medium;

//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;

/* 328. Odd Even Linked List
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return 
the reordered list.
The first node is considered odd, and the second node is even, and so on.
Example 1:  Input: head = [1,2,3,4,5]       Output: [1,3,5,2,4]
Example 2:  Input: head = [2,1,3,5,6,4,7]   Output: [2,3,6,7,1,5,4]
 */


public class EvenOddLL {
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

        LL.Node ans = oddEvenList(list.head);

        ans.printListNode(ans);
    }
    /*
      The idea is to split the linked list into 2 : one containing all odd nodes and other containing all even nodes. And finally,
      attach the even node linked list at the end of the odd node linked list.
     */
    public static LL.Node oddEvenList(LL.Node head) {
        if(head == null || head.next == null) return head;
        //0th index node(head) is head of the odd indices list and 1st index (head.next) is the head of the even indices list
        LL.Node odd = head , even = head.next , evenHead = head.next ;
        while(even !=null && even.next !=null){
            //skip even indices for odd and vice versa
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        //connect the end of the odd list to the head of the even list
        odd.next = evenHead;
        return head;
    }
}
