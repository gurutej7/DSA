package LinkedList.Medium;
import LinkedList.Easy.LL;

/*
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Example : 3->2->0->4   (4->2)
 */

public class LLcycleTwo{
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(3);
        list.add(2);
        list.add(0);
        list.add(4);
        list.printList();
        // The cycle starts at index 1 that is the output should be 2
        createCycle(list.head, 1, 3);

        LL.Node ans = detectCycle(list.head);

        System.out.println(ans.data);

    }

    public static LL.Node detectCycle(LL.Node head) {
        if(head == null || head.next == null) return null;
        // sp = slow pointer (1-step)
        // fp = fast pointer (2-step)

        LL.Node sp = head ;
        LL.Node fp = head ;
        //While fp not reaching the end 
        while(fp!= null && fp.next!=null ){
            sp = sp.next;
            fp = fp.next.next;
            if(sp == fp){ // Cycle exixts
                LL.Node temp = head ; // We will start from the head with a temp node and move one step at a time 
                // at the same time sp will move one step ahead from where the cycle was detected then the will meet at the start of the cycle
                while(temp != sp){
                    temp = temp.next;
                    sp = sp.next;
                }
                return temp;
            }
        }
        return null;
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