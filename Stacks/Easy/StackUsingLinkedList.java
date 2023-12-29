package Stacks.Easy;

// We are going to design a custom stack using Linked List
class Node{
    int val;
    Node next;
    public Node(){
        this.val = 0 ;
        this.next = null;
    }
    public Node (int data){
        this.val = data;
        this.next = null;
    }
}
class stackUsingLL{
    Node head;
    int size;

    public stackUsingLL(){
        this.head = null;
        this.size = 0;   
    }
    public boolean isEmpty(){
           return head == null; //empty
           // return size == 0
    }

    public void push(int data){
        Node newNode = new Node(data); //Create a new node with given data
        if(head == null ){   //If the stack is empty make the new Node as head
            head = newNode;
        }
        else{
            newNode.next = head;  //Else point the new Node to the old head and make the newNode as new Head
            head  = newNode;
        }
        size++;
    }

    public int pop(){
        if(head == null) return -1;  //Stack is empty
        size--;
        if(head.next == null){  //Only one element in stack
            head = null;
            return -1;
        }
        else{
            int poppedValue = head.val;
            head = head.next;
            return poppedValue;
        }    
    }

    public int peek(){
        if(head == null) return -1;
        else return head.val;
    }
    public void printStack(){
        if(head == null) System.out.println("stack is empty");
        Node curr = head;
        System.out.print("Top ->");
        while(curr != null){
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.print(" - Bottom");
        System.out.println();
    }
}      

public class StackUsingLinkedList {
    
    public static void main(String[] args) {


        stackUsingLL st = new stackUsingLL();

        System.out.println(st.isEmpty()); // true

        System.out.println(st.size);     // Zero
        st.push(5);  //5
        st.push(4);  // 4-> 5
        st.push(3);  //3-> 4-> 5
        st.printStack();

        st.pop();        //4-> 5
        st.push(2); //2-> 4-> 5
        st.printStack();
        st.peek();       //2
        st.push(1);  // 1-> ->2 -> 4 -> 5

        System.out.println(st.size); // four

        st.printStack(); // 1-> ->2 -> 4 -> 5
    }
    
}
