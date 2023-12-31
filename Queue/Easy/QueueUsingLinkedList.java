package Queue.Easy;

public class QueueUsingLinkedList{
    public static void main(String[] args) {
        q lq = new q();

        lq.push(5);
        lq.push(4);
        lq.push(3);
        lq.push(2);
        lq.printQueue();
        lq.pop();
        System.out.println(lq.Size());
        lq.pop();
        lq.printQueue();
    }
}
class Node{
    Node next;
    int val;
    public Node(int x){
        this.val = x;
        this.next = null;
    }
}

class q{
    Node front;
    Node rear;
    int size ;
    public q(){
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    public void push(int x) {
        // 1 st element -> Front and rear will be pointing to same node
        size++;
        if(front == null && rear == null){
         front = new Node(x);
         rear = front;
        }
        // If it is not 1st element update rear to "last in" Value
        // Front will be still at the first in value
        else{
            Node newNode = new Node(x);
            rear.next = newNode;
            rear = newNode;
        }
    }
    public int pop() {
        // The queue is Empty
        if(front == null) return -1;
        // We have only one element , We need to reset both front and rear
        size--;
        if(front == rear){
            rear = null;
            int returnVal = front.val;
            front = null;
            return returnVal;
        }
        //else we just return the current front value and change the front to the next value
        int returnVal = front.val;
        front = front.next;
        return returnVal;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public int Size(){
        return size;
    }

    public void printQueue(){
        if(front == rear && rear == null) System.out.println("Queue is Empty");

        Node curr = front;
        System.out.print("Front -> ");
        while(curr != null){
            System.out.print(curr.val + "-> ");
            curr = curr.next;
        }
        System.out.print(" - rear");
        System.out.println();
    }
}