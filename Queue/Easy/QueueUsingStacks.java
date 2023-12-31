package Queue.Easy;
import java.util.Stack;

public class QueueUsingStacks {
    public static void main(String[] args) {
        
    }
    
}

// My code that I submitted when I solved this for the first time
class MyQueue {
    //Using Two stacks
     Stack<Integer> st1 ;
     Stack<Integer> st2 ;
    public MyQueue() {
            st1 = new Stack<>();
            st2 = new Stack<>();
        
    }
    //St1 acts as our original  queue, So directly push it
    public void push(int val) {
        st1.push(val);
    }
    //To pop , We need to get the first element which was inserted into the stack
    // Which will be at the bottom of the stack st1 , By popping out till the end and putting it into the stack St2
    // Now st2 top has the st1 bottom element , now we can pop the first in element from st2 

    public int pop() {
            while(!st1.isEmpty())   st2.push(st1.pop());
            int returnVal = st2.pop();
            while(!st2.isEmpty())   st1.push(st2.pop());
            return returnVal;
    }
    // Same logic as pop
    public int peek() {
            while(!st1.isEmpty())   st2.push(st1.pop());
            int returnVal = st2.peek();
            while(!st2.isEmpty())   st1.push(st2.pop());
            return returnVal;
    }
    
    public boolean empty() {
        return st1.isEmpty();
    }
}

// Leetcode top solution



