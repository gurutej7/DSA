package Recursion.Easy;

import java.util.Scanner;

public class RotatedBS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Length of the Array: ");
        int n = in.nextInt();
         System.out.println("Enter Array Elements : ");
        int arr[] = new int [n];
        //Array input
        for(int i = 0 ; i<n ; i++){
            arr[i] = in.nextInt();
        }
        System.out.println("Enter the Target to Search : ");
        int target = in.nextInt();
        System.out.println(rotatedBinarySearch(arr,target,0,n-1));

        in.close();
    }
    //Example Rotated Sorted Array {5,6,7,8,9,1,2,3}
    public static boolean rotatedBinarySearch(int arr[], int s,int e, int target){
        //Where s = starting Index , e = ending Index
        if(s>e) return false; // Element is not found Condition
        int m = s + (e-s)/2 ; //Calculate Mid
        if(arr[m] == target) return true; // Element is Found
        if(arr[s]<=arr[m]){ // Left half is Sorted
            //case 1
            if(target>=arr[s] && target<=arr[m]) // target lies in the left half
            return rotatedBinarySearch(arr, s, m-1, target); // Search in left half
            else return rotatedBinarySearch(arr, m+1, e, target);// Search in right half
        }
        
        //case 2
        if(target>=arr[m] && target<=arr[e]) 
        return rotatedBinarySearch(arr, m+1, e, target);
        return rotatedBinarySearch(arr, s, m-1, target);

    }
}
