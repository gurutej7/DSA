package Stacks.Easy;

//Question Link
// https://www.codingninjas.com/studio/problems/stack-implementation-using-array_3210209?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf

class StackArray {
    int [] a ;
    int insertElementIndex;
        
    StackArray(int capacity) {
        // Write your code here.
        this.a = new int[capacity];
        this.insertElementIndex =0;
    }
    public void push(int num) {
        if(insertElementIndex == a.length) return;
        a[insertElementIndex] = num;
        insertElementIndex++;
    }
    public int pop() {
        if(insertElementIndex == 0) return -1;
        else {
            int poppedValue = a[insertElementIndex-1];
            insertElementIndex--;
            return poppedValue;
        }   
    }
    public int top() {
        if(insertElementIndex == 0) return -1;
        else return a[insertElementIndex-1];
    }
    public int isEmpty() {
        if(insertElementIndex == 0) return 1;
        else return 0;
    }
    public int isFull() {
        if(insertElementIndex == a.length) return 1;
        else return 0;
    }

    public void printStack(){
        if(insertElementIndex == 0) {
            System.out.println("Stack is Empty");
        }
        System.out.print("Top -> ");
        for(int i = insertElementIndex-1 ; i >=0 ; i--){
            System.out.print(a[i]+"-> ");
        }
        System.out.print(" - Bottom");
        System.out.println();
    }
    public int size(){
        return insertElementIndex;
    }
}

public class StackUsingArray {
    public static void main(String[] args) {
        StackArray st = new StackArray(5);

        st.push(5);  // 5
        st.push(4);  // 4 -> 5
        st.push(3);  // Top -> 3 -> 4 -> 5 -> - Bottom
        st.printStack();
        System.out.println(st.size());  // three
        st.pop();   // 4 -> 5
        st.pop();   // 5
        st.push(2); // 2 -> 5
        System.out.println(st.top());   // 2
        st.push(1);  
        st.printStack();  // Top -> 1 -> 2 -> 5 -> - Bottom
        System.out.println(st.size());  // three

    }
    
}
