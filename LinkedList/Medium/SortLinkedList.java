package LinkedList.Medium;
//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;

/* 148. Sort List
    Given the head of a linked list, return the list after sorting it in ascending order.
    Input: head = [4,2,1,3]
    Output: [1,2,3,4]
    Input: head = [-1,5,3,4,0]
    Output: [-1,0,3,4,5]
 */
public class SortLinkedList {
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(-1);
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(0);
    
        list.printList();

        LL.Node ans = sortList(list.head);

        ans.printListNode(ans);


    }
    public static LL.Node sortList(LL.Node head) {

        if(head == null || head.next == null) return head;
        // Divide the list into two 
        LL.Node sp = head , fp = head , prev = null ;
        while(fp != null && fp.next != null){
            prev = sp;
            sp = sp.next;
            fp = fp.next.next;
        }

        prev.next = null;

        //Keep on Dividing into further halves until then length is one
        LL.Node firstHalf = sortList(head);
        LL.Node secondHalf = sortList(sp);
        // Merge the divided lists
        return merge(firstHalf, secondHalf);
     
    }
    public static  LL.Node merge(LL.Node h1, LL.Node h2){
        LL dummy = new LL(); // A dummy listNode to keep track of the head of the merged LL
        dummy.add(0);
        //Temp is the node that traverses and collects  values from both the lists in ascending order
        //Dummy lists head is pointing to the temp
        LL.Node temp = dummy.head;  // 0->temp(from here the ans list starts ) -> so in the end we will return dummy lists head`s next  i.e, temp (head of the ans list)

        //Here on the general merge of values by comparing
        while(h1 != null && h2 != null){
            if(h1.data < h2.data){
                temp.next = h1 ;
                h1 = h1.next ;
                temp = temp.next;
            }
            else{
                temp.next = h2 ;
                h2 = h2.next ;
                temp = temp.next;
            }
        }

        while(h1 != null){
            temp.next = h1 ;
            h1 = h1.next ;
            temp = temp.next;
        }
        while(h2 != null){
            temp.next = h2 ;
            h2 = h2.next ;
            temp = temp.next;
        }
        return dummy.head.next;
    }
    
}
