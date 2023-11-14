package LinkedList.Medium;
//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;
/*  Delete All Occurrences of a key in a LinkedList
Sample Input 1:    { 10 4 10 3 5 20 10   k =10  } Sample Output 1:   { 4 3 5 20 }
Explanation Of Sample Input 1:
All the nodes having ‘data’ = 10 are removed from the linked list.

Sample Input 2:     {10 4 10 3 5 20 10    k = 30 }  Sample Output 2:    {10 4 10 3 5 20 10}
 */
public class DeleteAllOccurrencesOfK {
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(10);
        list.add(4);
        list.add(10);
        list.add(3);
        list.add(5);
        list.add(20);
        list.add(10);
    
        list.printList();

        LL.Node ans = deleteAllOccurrences(list.head, 10);

        ans.printListNode(ans);
    }

    public static LL.Node deleteAllOccurrences(LL.Node head, int k) {
        // Write your code here.
        // Delete the heads till value is not equal to k
        while(head != null && head.data == k) head = head.next;
        //edge case 
        if(head == null) return head;//If all the data are k then we have reached the end
        //Have a prev node to make connections when required
        LL.Node prev = head , curr = null ;
        if(head.next != null) curr = head.next;
        //edge case
        else return head;//After removing all heads with data k and we are having only one node means we have solved
        
        while(curr != null ){
            //Actual thing i.e, asked in the problem
            if(curr.data == k){
                prev.next = curr.next;
            }
            //Move Ahead
            prev = curr ;
            curr = curr.next;
        }
        return head;
    }
    
}
