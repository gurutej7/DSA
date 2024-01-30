package Heaps.Medium;

import java.util.Arrays;

/* Convert Min Heap To Max Heap

Problem statement
You are given an array of size ‘N’ which is an array representation of min-heap.
You need to convert this min-heap array representation to a max-heap array representation. 
Return the max-heap array representation.
For Example
Corresponding to given min heap : [1,2,3,6,7,8]
maxHeap : [8,7,3,6,2,1]
  
 */

public class MinToMaxHeap {
    public static void main(String[] args) {
        int arr[] = {1,2,3,6,7,8};

        System.out.println( Arrays.toString( MinToMaxHeap(arr.length, arr) )  );
        
    }

    // Helper method to perform heapify
    public static void heapify(int[] arr, int n, int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        // If left child is greater than replace it with node
        if (leftChild < n && arr[largest] < arr[leftChild]) {
            largest = leftChild;
        }

        // If right child is greater than replace it with node
        if (rightChild < n && arr[largest] < arr[rightChild]) {
            largest = rightChild;
        }

        // If any child has more value, call heapify on corresponding sub-tree.
        if (largest != index) {
            int temp = arr[largest];
            arr[largest] = arr[index];
            arr[index] = temp;
            heapify(arr, n, largest);
        }
    }
    /* Starting the loop from n / 2 ensures that you begin the heapification process from the 
    last non-leaf node in the binary tree and move upwards towards the root. The reason for 
    starting from the last non-leaf node is that leaf nodes (nodes with no children) are 
    already considered to be trivially heaps. */
    private static int[] MinToMaxHeap(int n, int[] arr) {
        // Calling heapify process in bottom-up manner.
        for (int i = n / 2; i >= 0; i--) {
            heapify(arr, n, i);
        }
        return arr;
    }
    
}
