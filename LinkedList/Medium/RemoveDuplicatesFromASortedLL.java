package LinkedList.Medium;
//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;
/* Remove Duplicates From a Sorted Linked List
Sample Input 1 :    1 2 2 2 3
Sample Output 1 :   1 2 3
Explanation For Sample Input 1 :
We will delete the duplicate values ‘2’ present in the linked list.
Sample Input 2 :    1 2 3 4
Sample Output 2 :   1 2 3 4
 */
public class RemoveDuplicatesFromASortedLL {
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        
        list.printList();

        LL.Node ans = uniqueSortedList(list.head);

        ans.printListNode(ans);
    }

    public static LL.Node uniqueSortedList(LL.Node head) {
        // Write your code here.
        if(head == null || head.next == null) return head;
        LL.Node curr = head ;
        while(curr != null){
            LL.Node temp = curr.next;
            while(temp != null && temp.data == curr.data) temp = temp.next;
            curr.next = temp;
            curr = temp;
        }
        return head;
    }
}
