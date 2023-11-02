package Recursion.Easy;

import java.util.Scanner;
import java.util.ArrayList;

public class LinearSearchUsingRecursion {
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
        System.out.println(linearSearch(arr,target,0));
        System.out.println(linearSearchIndex(arr,target,0));
        //Ans list to return all indices of occurrences
        ArrayList<Integer> ans = new ArrayList<>();
        ans = findAllIndex(arr, target, 0, ans);
        System.out.println(ans);
    }

    //Recursive Function to Linear Search in the Array
    public static boolean linearSearch(int[] arr,int target,int index){
        //Base Condition
        if(index==arr.length-1) return arr[index]==target;
        //Check For the Current Element and call the Function for Further Elements
        return arr[index]==target || linearSearch(arr,target,index+1);
    }

    //Function to return the Index if Element is Found
    public static int linearSearchIndex(int arr[] , int target, int index){
        //Base condition If element is not present in the array then the index reaches end
        if(index==arr.length) return -1;
        //Element is Found
        else if ( arr[index]==target) return index;
        // If not found in above case Search for further indices
        else  return linearSearchIndex(arr,target,index+1);
    }

    //Function to return all Occurrences indices in the form of list
    public static ArrayList<Integer> findAllIndex(int [] arr, int target, int index , ArrayList<Integer> ans){
        if(index==arr.length) return ans;
        if(arr[index]==target) ans.add(index);
        return findAllIndex(arr,target,index+1,ans);
    }

}
