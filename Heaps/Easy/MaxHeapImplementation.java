package Heaps.Easy;

import java.util.ArrayList;

public class MaxHeapImplementation {
    public static void main(String[] args) {
        MaxHeap mHeap = new MaxHeap();

        mHeap.push(5);
        mHeap.push(100);
        mHeap.push(45);
        mHeap.push(105);
        mHeap.push(0);

        System.out.println( mHeap.pop() );    // 105
        System.out.println( mHeap.pop() );    // 100
        System.out.println( mHeap.pop() );    // 45

        System.out.println( mHeap.getMax() );  // 5

    }
    
}

class MaxHeap{
    ArrayList<Integer> heap ;

    public MaxHeap(){
        heap = new ArrayList<>();
    }

    public int getMax(){
        return heap.size() == 0 ? -1 : heap.get(0);
    }

    // Removes the maximum element from the heap
    public  int pop() {
        // heap is empty
        if(heap.size() == 0) return -1;
        // As it is a heap the max element will be at the 0 th index
        int ans = heap.get(0);
        int lastElement = heap.remove(heap.size()-1);
        // Checking if heap has only one element case
        if(!(heap.size() == 0) ){
            // Now set the last element at the 0 th index and reconstruct the heap
            heap.set(0,lastElement);
        }
        // Assume currPos has max value 
        int currPos = 0;
        int maxIndex = currPos;
        // down heap
        while(maxIndex == currPos){
            int left = currPos*2+1;
            int right = currPos*2+2;
            // If we encounter a maximum element than the current max then update the max index
            if(left < heap.size() && heap.get(maxIndex) < heap.get(left))
                maxIndex = left;
            if(right < heap.size() && heap.get(maxIndex) < heap.get(right))
                maxIndex = right;
            // If the maxIndex is changed then again do the same thing again , with new current position
            if(maxIndex != currPos){
                int temp = heap.get(maxIndex);
                heap.set(maxIndex , heap.get(currPos) );
                heap.set(currPos , temp);
                currPos = maxIndex;
            }
            // If after checking , if there is no max then the heap is good , so break out
            else break;
            
        }
        return ans;
    }

    public void push(int x) {
             heap.add(x);
    
             //  Position of the current inserted element.
             int pos = heap.size() - 1;
    
             // Shifting the element up until it reaches the topmost node if it is larger than its parent.
             // also called as upheap
             while (pos > 0) {
                 int parent = (pos - 1) / 2;
                 if (heap.get(pos) > heap.get(parent)) {
                     // Swapping the elements.
                     int temp = heap.get(parent);
                     heap.set(parent, heap.get(pos));
                     heap.set(pos, temp);
                     pos = parent;
                 } 
                 else {
                     // As parent is larger, the element is now in its correct position.
                     break;
                 }
             }
         }

    public void printHeap(){
        System.out.println(heap);
    }
}

// practice { https://rb.gy/cy55fh }

