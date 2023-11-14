package LinkedList.Medium;
//importing custom LinkedList class implementation from LinkedList.Easy folder
import LinkedList.Easy.LL;
/* 2. Add Two Numbers 
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse 
order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:      Input: l1 = [0], l2 = [0]       Output: [0]
Example 3:      Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]     Output: [8,9,9,9,0,0,0,1]
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        //Create a Linked List 
        LL l1 = new LL();
        LL l2 = new LL();
        // add nodes to the LinkedList
        l1.add(2);
        l1.add(4);
        l1.add(3);
        

        l2.add(5);
        l2.add(6);
        l2.add(4);
        
    
        l1.printList();
        l2.printList();

        LL.Node ans = addTwoNumbers(l1.head, l2.head);

        ans.printListNode(ans);


    }
    
    public static LL.Node addTwoNumbers(LL.Node l1, LL.Node l2) {
        LL dummy = new LL(); // A dummy listNode to keep track of the head of the ans LL
        dummy.add(0);
        LL.Node temp = dummy.head;
        LL.Node curr1 = l1 , curr2 = l2;
        int sum = 0 ,carry = 0 ;
/*
Loop through lists l1 and l2 until you reach both ends, and until carry is present.
Set sum=l1.val+ l2.val + carry.
Update carry=sum/10.
Create a new node with the digit value of (sum%10) and set it to temp node’s next, then advance temp node to next.
 */
        while(curr1 !=null || curr2 != null || carry != 0){
            sum = 0 ;
            if(curr1 != null) {
                sum += curr1.data;
                curr1 = curr1.next;
            }
            if(curr2 != null){
                sum += curr2.data;
                curr2 = curr2.next ;
            }

            sum += carry ;
            carry = sum/10 ;
            LL newList = new LL();
            newList.add(sum%10);
            temp.next = newList.head ;
            temp = temp.next;

        }
        return dummy.head.next;
        
    }
}
