package LinkedList.Medium;

import LinkedList.Easy.LL;
/*
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
   There is a cycle in a linked list if there is some node in the list that can be reached again by continuously 
   following the next pointer.
* Example 1:
Input:  Head = [1,2,3,4]
Output: true
Explanation: Here, we can see that we can reach node at position 1 again by following the next pointer. Thus, we 
return true for this case.
 */

public class LLcycle {
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

        // createCycle(list.head, 2, 3);

        System.out.println(hasCycle(list.head));



    }
    /*  We will take two pointers, namely fast and slow. Fast pointer takes 2 steps ahead and slow pointer takes 2 points ahead.Iterate through the list until the fast pointer is equal to NULL. This is because NULL indicates that there is no cycle present in the given list.Cycle can be detected when fast and slow pointers collide.
     */
    public static  boolean hasCycle(LL.Node head) {
        if(head == null || head.next == null) return false;
        LL.Node sp = head;
        LL.Node fp = head;
        while(fp.next!=null && fp.next.next!=null){
            sp = sp.next;
            fp = fp.next.next;
            if(sp == fp) return true;
        }
        return false;
    
    }

    // Function to create cycle  a,b are positions(0-indexed) where we need to create a cycle
    public static void createCycle(LL.Node head,int a,int b) {
        int cnta = 0,cntb = 0;
        LL.Node p1 = head;
        LL.Node p2 = head;
        while(cnta != a || cntb != b) {
            if(cnta != a) {
                p1 = p1.next; ++cnta;
            }
            if(cntb != b) {
                p2 = p2.next; ++cntb;
            }
        }
        p2.next = p1;
    }
    
}
