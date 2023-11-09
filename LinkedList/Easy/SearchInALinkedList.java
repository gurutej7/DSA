package LinkedList.Easy;
/*
 *  Given a Linked List and a value to search 
 * if the value is present in the list return true else false
 */

public class SearchInALinkedList {
    
    public static void main(String[] args) {
        LL list = new LL();

        list.addFirst(1);
        list.add(10);
        list.add(20);
        list.add(35);
        list.add(45);
        

        System.out.println(searchInLinkedList(list.head, 35));

        System.out.println(searchInLinkedList(list.head, 50));
    }

    public static boolean searchInLinkedList(LL.Node head, int k)
    {
        // Write Your Code Here.
        if(head == null) return false;
        LL.Node curr = head;
        while(curr!=null){
            if(curr.data==k) return true;
            curr = curr.next;
        }
        return false;
    }
    
}
