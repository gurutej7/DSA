package Recursion.Easy;


import java.util.Scanner;
public class IsSorted {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Length of the Array: ");
        int n = in.nextInt();
        int arr[] = new int [n];
        //Array input
        for(int i = 0 ; i<n ; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(isSortedArray(arr,0));

        in.close();
    }
    //Function to check if the Array is Sorted or Not
    public static boolean isSortedArray(int [] arr,int index){
        //Base Condition
        if(index==arr.length-1) return true;
        //Check for the current Element and Call recursively for the Further Elements
        return arr[index]<arr[index+1] && isSortedArray(arr,index+1); 
    }
}
