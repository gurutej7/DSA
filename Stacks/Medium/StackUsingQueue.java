package Stacks.Medium;

import java.util.LinkedList;
import java.util.Queue;

// 225. Implement Stack using Queue

public class StackUsingQueue {
    public static void main(String[] args) {

        stack st = new stack();

        st.push(2);
        st.push(3);
        st.pop();
        
    }
    
}
// Using only one queue
class stack {
    Queue < Integer > q = new LinkedList < > ();
    void push(int x) {
        q.add(x); // Works like a circle , All the elements infront of the new element will move and stand 
        //  back at the back of the new element
        for (int i = 0; i < q.size() - 1; i++) {    // O(n)
            q.add(q.remove());
        }
    }
    int pop() {
        return q.remove();                           // O(1)
    }
    int top() {
        return q.peek();                             // O(1)
    }
    int size() {
        return q.size();                             // O(1)
    }
}

// My approach that came to mind when I solved this for the first time
// Using Two Queue`s
class Stack {
    // Define the data members.
    Queue<Integer> q1;
    Queue<Integer> q2;

    // Constructor
    public Stack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /*----------------- Public Functions of Stack -----------------*/

    // Returns the size of the stack
    public int getSize() {
        return q1.size();
    }

    // Checks if the stack is empty
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    // Adds an element to the stack
    public void push(int element) {
        q1.add(element);
    }

    // Removes and returns the top element from the stack
    public int pop() {
        while (q1.size() > 1) {
            q2.add(q1.poll());
        }
        if (q1.isEmpty()) {
            return -1; // Stack is empty
        }
        int returnVal = q1.poll();
        while (!q2.isEmpty()) q1.add(q2.poll());
        return returnVal;
    }

    // Returns the top element of the stack without removing it
    public int top() {
        while (q1.size() > 1) {
            q2.add(q1.poll());
        }
        if (q1.isEmpty()) {
            return -1; // Stack is empty
        }
        int returnVal = q1.peek();
        q2.add(q1.poll());
        while (!q2.isEmpty()) q1.add(q2.poll());
        return returnVal;
    }
}

