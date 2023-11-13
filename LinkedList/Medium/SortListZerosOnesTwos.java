package LinkedList.Medium;

//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;
/* Given a LL consisting of 0,1,2 sort the list and return
Sample Input 1:
1 0 2 1 0 2 1
Sample Output 1:
0 0 1 1 1 2 2
 */
public class SortListZerosOnesTwos {
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(1);
        list.add(0);
        list.add(2);
        list.add(1);
        list.add(0);
        list.add(2);
        list.add(1);
    
        list.printList();
    
        LL.Node ans = sortList(list.head);

        ans.printListNode(ans);
    }

    public static LL.Node sortList(LL.Node head) {
        /*
         The approach would be counting the number of occurrences of 0, 1, and 2. Then updating the data of the 
         linked list in sorted order.
         */
        LL.Node curr = head;
        int[] cnt = new int[3];

        // Iterate while curr is not empty
        while(curr != null)
        {
            cnt[curr.data]++;
            curr = curr.next;
        }

        curr = head;
        int i = 0;

        // Updating data.
        while(curr != null)
        {
            while(cnt[i] == 0)
            {
                i++;
            }

            curr.data = i;
            cnt[i]--;
            curr = curr.next;
        }

        // Return head
        return head;
        
    }
}
