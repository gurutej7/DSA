
/*
 * What is Lower Bound?
The lower bound algorithm finds the first or the smallest index in a sorted array where 
the value at that index is greater than or equal to a given key i.e. x.
The lower bound is the smallest index, ind, where arr[ind] >= x. But if any such index is
not found, the lower bound algorithm returns n i.e. size of the given array.
 */

public class LowerBound {

    public static void main(String[] args) {

        int arr [] = {2,5,8,6,9,15};
        int n = arr.length;
        int x = 9;

        System.out.println(lowerBound(arr, n, x));

        System.out.println(lowerBoundBinary(arr, n, x));
        
    }

    //brute force by linear Search
    public static int lowerBound(int []arr, int n, int x) {
        for (int i = 0; i < n; i++) {
            if (arr[i] >= x) {
                // lower bound found:
                return i;
            }
        }
        return n;
    }
    //Optimal using Binary Search
    public static int lowerBoundBinary(int []arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] >= x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {//curr mid value is less than x move to right
                low = mid + 1;
            }
        }
        return ans;
    }
    
}
