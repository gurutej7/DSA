/* Binary search Algorithm
 * Used to search efficiently in a sorted array
 * Time Complexity - O(log N)
 * Space Complexity - O(1)
 * Constraint - The Search space must be sorted
 * Every time the codition is checked the search space reduces by half
 
 */

public class Binarysearch {
    public static void main(String[] args) {
        int arr [] = {2,5,6,8,9,10,15,18,19,25};
        int target = 18;

        System.out.println(binarySearchBool(arr,target));

        System.out.println(binarySearchBoolRecursive(arr, 0, arr.length-1, target));

        System.out.println(binarySearchIndex(arr, target));

        System.out.println(binarySearchIndexRecursive(arr, 0, arr.length-1, target));

        
    }
    //Funtion returns true if the element is present else returns false
    private static boolean binarySearchBool(int[] arr, int target) {
        //low is the starting index and high is the ending index of the sorted array
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = low + (high-low) / 2;
            //found
            if(arr[mid]==target) return true;
            //mid is less than target means that target lies on the right half else left half
            else if(arr[mid]<target) low=mid+1;
            else high = mid-1;

        }
        return false;
    }

    //Recursive Function
    private static boolean binarySearchBoolRecursive(int[] arr,int low,int high,int target){
        if(!(low<=high)) return false;
        int mid = low + (high-low)/2;
        if(arr[mid]==target) return true;
        else if(arr[mid]<target) return binarySearchBoolRecursive(arr, mid+1, high, target);
        else return binarySearchBoolRecursive(arr, low, mid-1, target);

    }

    //Function to return the index if the element is present else returns -1;
    private static int binarySearchIndex(int[] arr, int target) {
        //low is the starting index and high is the ending index of the sorted array
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = low + (high-low) / 2;
            //found
            if(arr[mid]==target) return mid;
            //mid is less than target means that target lies on the right half else left half
            else if(arr[mid]<target) low=mid+1;
            else high = mid-1;

        }
        return -1;
    }

    //Recursive Function
    private static int binarySearchIndexRecursive(int[] arr,int low,int high,int target){
        if(!(low<=high)) return -1;
        int mid = low + (high-low)/2;
        if(arr[mid]==target) return mid;
        else if(arr[mid]<target) return binarySearchIndexRecursive(arr, mid+1, high, target);
        else return binarySearchIndexRecursive(arr, low, mid-1, target);

    }

}
