package Heaps.Easy;

import java.util.ArrayList;

// practice  { https://rb.gy/ay27ec }

public class MinHeapImplementation {
    public static void main(String[] args) {
        
        MinHeap mHeap = new MinHeap();

        mHeap.insert(45);
        mHeap.insert(96);
        mHeap.insert(4);
        mHeap.insert(100);
        mHeap.insert(12);

        mHeap.printHeap();
        // ArrayList<Integer> sortedHeap = mHeap.heapSort();

        // System.out.println(sortedHeap); // [4, 12, 45, 96, 100]

        System.out.println(mHeap.remove());  // 4
        System.out.println(mHeap.remove());  // 12
        System.out.println(mHeap.remove());  // 45

        System.out.println(mHeap.getMin());  // 96
         
    }
    
}

class MinHeap{
    public ArrayList<Integer> heapList ;

    public MinHeap(){
        heapList = new ArrayList<>();
    }
    // Function to swap two indices in the array list
    public void swap(int firstIndex , int secondIndex){
        int temp = heapList.get(secondIndex);
        heapList.set(secondIndex,heapList.get(firstIndex));
        heapList.set(firstIndex,temp);
    }

    // Returns the minimum element in the heap
    public int getMin(){
        if(heapList.isEmpty()) return -1;

        return heapList.get(0);
    }
    
    // returns the size of the heap
    public int getSize(){
        return heapList.size();
    }
    // Function to get parent index of any other index
    public int parentIndex(int currIndex){
        return (currIndex-1)/2;
    }
    // Function to get left index of any other index
    public int leftIndex(int currIndex){
        return 2*currIndex+1;
    }
    // Function to get right index of any other index
    public int rightIndex(int currIndex){
        return 2*currIndex+2;
    }

    public void insert(int data){
        // If it is the first element just add it and return 
        if(heapList.isEmpty()) {
            heapList.add(data);
            return;
        }

        // First just add it at the last and do upheap to correct the structure // upheap going from bottom to up
        heapList.add(data);
        int currPos = heapList.size()-1;
        upheap(currPos);

    }

    public void upheap(int currPos){
        //Base condition , we have reached the top most position / level
        if(currPos == 0) return;


        int parent = parentIndex(currPos);
        
        // If the current element is smaller than its parent then we have to swap
        if(heapList.get(currPos) < heapList.get(parent)){
            swap(currPos,parent);
            // Call the same function again after performing operation at one level
            // These levels are from bottom to up
            upheap(parent);
        }
    }

    public int remove(){
        // It is empty
        if(heapList.isEmpty()) return -1;

        int temp = heapList.get(0);

        int last = heapList.remove(heapList.size()-1);
        // We are checking is Empty because it may possible the heap has only one element
        if(!heapList.isEmpty()){
            // Now set the last element at first position 
            // Then again reconstruct the heap ,downheap
            // down heap => from top to bottom
            heapList.set(0,last);

            downheap(0);
        }

        return temp;
    }

    public void downheap(int currPos){
        int heapSize = heapList.size();
        // We have reached the last position so there will be no elements further
        if(currPos == heapSize-1) return;

        // Assume currPos has min value 
        int minIndex = currPos;
        int left = leftIndex(currPos);
        int right = rightIndex(currPos);

        // Now compare with both left and right childs if there exists a minimum then swap
        if(left < heapSize && heapList.get(minIndex) > heapList.get(left)){
            minIndex = left;
        }
        if(right < heapSize && heapList.get(minIndex) > heapList.get(right)){
            minIndex = right;
        }

        // Check if the minIndex is changed are not , if it is changed then we have to swap the current pos element with that minIndex position
        if(minIndex != currPos){
            swap(minIndex, currPos);
            // Now again call down heap with new position of min element
            downheap(minIndex);
        }
        // If there is no change in index then it is in correct position
        else return;

    }

    public ArrayList<Integer> heapSort(){
        // Keep removing elements from the heap and add it to the list 
        // Every time the minimum element is removed , So the list will be sorted
        ArrayList<Integer> sortedList = new ArrayList<>();

        // This one will remove in the original heap to sort /* 
        while(!heapList.isEmpty()){
            sortedList.add(this.remove());
        }

        return sortedList;
 

        // O ( N logN)
    }

    public void printHeap(){
        System.out.println(heapList);
    }
}

// Using fixed size Array
class MinHeap2{
        private int[] heapList;
        private int currSize;
        private int capacity;
        
        MinHeap2(int size) {
            heapList = new int[size];
            this.currSize=0;
            this.capacity=size;
        }

        void downheap(int currPos){
            int minIndex = currPos;
            int left = 2*currPos+1;
            int right = 2*currPos+2;

            // Now compare with both left and right childs if there exists a minimum then swap
            if(left < currSize && heapList[minIndex] > heapList[left]){
                minIndex = left;
            }
            if(right < currSize && heapList[minIndex] > heapList[right]){
                minIndex = right;
            }

            // Check if the minIndex is changed are not , if it is changed then we have to swap the current pos element with that minIndex position
            if(minIndex != currPos){
                swap(minIndex, currPos);
                // Now again call down heap with new position of min element
                downheap(minIndex);
            }
        }

        void swap(int i , int j ){
            int temp = heapList[i];
            heapList[i]= heapList[j];
            heapList[j]= temp;
        }


        // Implement the function to remove minimum element.
        int extractMinElement() {
            if(currSize == 0){
                return -1;
            }
            int ans = heapList[0];
            swap(0 , currSize-1);
            currSize--;
            downheap(0);
            return ans;
        }

        // Implement the function to delete an element.
        void deleteElement(int ind) {
            if(ind >= currSize){return;}
            swap(ind , currSize-1);
            currSize--;
            downheap(ind);
        }

        // Implement the function to insert 'val' in the heap.
        void insert(int val) {
            if(currSize == capacity){
                return;
            }
            heapList[currSize] =val;
            currSize++;
            int ind =currSize-1;
            while(ind >=0){
                int parent=(ind-1)/2;
                if(heapList[parent] > heapList[ind]){
                    swap(parent , ind);
                    ind = parent;
                }else break;
            }
        }
}


