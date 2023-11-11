package LinkedList.Medium;
import LinkedList.Easy.LL;

/* Find out if the LinkedList has a cycle or not and length of the cycle if it does
    Example : 3->2->0->4   (4->2) output : 3  Explanation : cycle (2->0->4 (4->2) repeat)
 */

public class LLcycleLength {
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

         System.out.println(lengthOfLoop(list.head));
         
    }

    public static int lengthOfLoop(LL.Node head) {
        // Write your code here
        if(head == null || head.next == null) return 0;
        // sp = slow pointer (1-step)
        // fp = fast pointer (2-step)
        LL.Node sp = head ;
        LL.Node fp = head ;
        while(fp!= null && fp.next!=null ){
            sp = sp.next;
            fp = fp.next.next;
            if(sp == fp){ // Cycle has been detected
                int cnt = 0; // keep the fp there and start moving sp until it reaches the fp again and on the way count the number of nodes
                do{
                    sp = sp.next ;
                    cnt++;

                }while(sp != fp);
                return cnt;
            }
        }
        return 0;
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
