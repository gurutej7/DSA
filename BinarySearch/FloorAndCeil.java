
/*
  Given a Sorted Array
  and a target "x"

  * Floor = largest num in Array <= x
  * Ceil = Smallest num in Array >= x

  Ceil is Same as Lower Bound
 */

public class FloorAndCeil{
    public static void main(String[] args) {
        int arr[] ={10,20,30,40,50};
        int x = 25;

        System.out.println(Ceil(arr, arr.length, x));

        System.out.println(Floor(arr, arr.length, x));

        
    }

    //Ceil
    public static int Ceil(int []arr, int n, int x) {
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
        return arr[ans];
    }

    //Floor
    public static int Floor(int[] arr, int n ,int x){
        int low = 0;
        int high = n - 1;
        int ans = n;
         while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] <= x) {
                ans = mid;
                //look for larger index on the right
                low = mid + 1;
            } else {//curr mid value is greater than x move to right
                high = mid-1;
                
            }
        }
        return arr[ans];

    }
}