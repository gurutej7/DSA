package LinkedList.Medium;

//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;

/* 234. Palindrome Linked List
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
* Example 1:    Input: head = [1,2,2,1]     Output: true
* Example 2:    Input: head = [1,2]         Output: false

 */

public class PalindromeLinkedList{
    public static void main(String[] args) {
        //Create a Linked List 
        LL list = new LL();
        // add nodes to the LinkedList
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(1);

        list.printList();

        System.out.println(isPalindrome(list.head));
        
    }
    /* Naive Approach - is using an extra array and store the values of the LL and check if the array is palindrome or not.
    
    The answer is to reverse the back half of the linked list to have the next attribute point to the previous
    node instead of the next node.
     */

    public static  boolean isPalindrome(LL.Node head) {
        LL.Node sp = head , fp = head ;
        //To find the Middle of the LL
        while(fp!=null && fp.next !=null){
            sp = sp.next;
            fp = fp.next.next;
        }
        //sp is pointing at middle Node 
        //Reverse the second half
        LL.Node curr = sp;
        LL.Node prev = null , nextNode =null ;
        
        //we are splitting the list into two so the currNode should point to null
        while(curr!= null){
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode ;
        }
        // By the end of the above loop the prev will be pointing to new head of the second half which is reversed
        // Now we can traverse and compare the two lists
        // We can also use the prev directly to traverse 
        sp = prev ;
        fp = head;
        while(sp != null) {
            if(sp.data != fp.data ) return false;
            sp = sp.next;
            fp = fp.next;
        }
        return true;
        
    }
}