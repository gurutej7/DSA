/*
   What is Upper Bound?
The upper bound algorithm finds the first or the smallest index in a sorted array where
 the value at that index is greater than the given key i.e. x.

The upper bound is the smallest index, ind, where arr[ind] > x.

But if any such index is not found, the upper bound algorithm returns n i.e. size of the
 given array. The main difference between the lower and upper bound is in the condition. 
 For the lower bound the condition was arr[ind] >= x and here, in the case of the upper 
 bound, it is arr[ind] > x.
 */

public class UpperBound {

    public static void main(String[] args) {

        int arr [] = {2,5,8,6,9,15};
        int n = arr.length;
        int x = 9;

        System.out.println(upperBound(arr, n, x));

        System.out.println(upperBoundBinary(arr, n, x));
        
    }

    //Using Linear Search 
    public static int upperBound(int[] arr, int n, int x) {
        for (int i = 0; i < n; i++) {
            if (arr[i] > x) {
                // upper bound found:
                return i;
            }
        }
        return n;
    }

    //Using Binary Search
    public static int upperBoundBinary(int[] arr, int n, int x) {
        int low = 0 ;
        int high = n - 1;
        int index = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                index = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return index;
    }
    
}
