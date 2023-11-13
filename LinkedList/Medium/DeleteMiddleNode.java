package LinkedList.Medium;

//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;
/* 2095. Delete the Middle Node of a Linked List
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Input: head = [1,2,3,4]
Output: [1,2,4]
 */

public class DeleteMiddleNode{
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.printList();

        LL.Node ans = deleteMiddle(list.head);

        ans.printListNode(ans);
    }

    public static LL.Node deleteMiddle(LL.Node head) {
        if(head == null || head.next == null) return null;
        LL.Node sp = head , fp = head , prev = null;
        while(fp != null && fp.next != null){
            prev = sp;
            sp = sp.next;
            fp = fp.next.next;
        }
        prev.next = sp.next ;
        return head;
        
    }
}