package Stacks.Medium;
import java.util.Stack;
/* 155. Min Stack
*  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.
*/

public class MinStackImplementation {
    public static void main(String[] args) {
        MinStack st = new MinStack();
        st.push(-2);
        st.push(-10);
        st.push(0);
        st.push(5);
        System.out.println(st.getMin()); // -10
    }
}

//My solution that I first submitted 
// The Idea is Simple
// While pushing elements we will be tracking the minimum value till that point in the stack
// To have two integers as one I have created a custom class called Pair which has integers a and b
// Where the "a" represents the actual value and "b" represents the Min value till that point
class Pair {
    int a ;
    int b ; 
    public Pair(int A,int B){
        this.a = A;
        this.b = B;
    }
    public int getMini(){
        return b ;
    }
    public int getVal(){
        return a;
    }
}

class MinStack1 
{
    Stack<Pair> st;
    public MinStack1() {
         st = new Stack<>();
    }
    
    public void push(int val) {
        if(st.isEmpty()) st.push(new Pair(val,val)); // FOr 1st element min is same as value;
        else{
            int currMin =  Math.min(val,st.peek().getMini()); // Check the current value with the last Minimum
            st.push(new Pair(val,currMin));
        }
        
    }
    
    // We are not checking edge cases because
    // In the problem it is clearly stated that
    // Methods pop, top and getMin operations will always be called on non-empty stacks.
    public void pop() {
           st.pop(); 
    }
    public int top() {
        return st.peek().getVal();
    }
    public int getMin() {
        return st.peek().getMini();
        
    }
}

// In the above case I am using a stack 
// I am accessing a method of method (st.peek().getMini()) , which is not a good practice
// The below is the leetcode top solution 
// Implemented a stack using linked list with elements which also has a track over minimum till that point
class MinStack {
	private Node head;
        
    public void push(int x) {
        if (head == null) 
            head = new Node(x, x, null);
        else 
            head = new Node(x, Math.min(x, head.min), head);
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
        
    private class Node {
        int val;
        int min;
        Node next;
            
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}

