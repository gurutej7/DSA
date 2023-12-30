package Queue.Easy;

public class QueueUsingArray {
    public static void main(String[] args) {
        Queue q = new Queue(5);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.printQueue();
        q.dequeue();
        q.dequeue();
        q.printQueue();
        
    }
    
}
class Queue {
        int front, rear;
        int []arr;

        Queue(int size) {
            front = 0;
            rear = 0;
            arr = new int[size];
        }

        // Enqueue (add) element 'e' at the end of the queue.
    public void enqueue(int e) {
        if(rear == arr.length) return ;
        arr[rear] = e;
        rear++;
    }

        // Dequeue (retrieve) the element from the front of the queue.
    public int dequeue() {
        if(rear == 0) return -1;
        int returnVal = arr[front];
        for(int i = 0 ; i < rear ; i++){
            arr[i] = arr[i+1];
        }
        rear--;
        return returnVal;
            
    }
    public void printQueue(){
        System.out.print("Front -> ");
        for(int i = front ; i < rear ; i++){
            System.out.print(arr[i]+" -> ");
        }
        System.out.println("<- Rear");
        System.out.println();
    }
}

