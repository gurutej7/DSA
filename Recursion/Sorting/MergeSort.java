package Recursion.Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
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
        divide(arr, 0, n-1);
        System.out.println(Arrays.toString(arr));

    }
    //Function to divide the Array into two halves
    // si = starting index 
    // ei = ending index
    public static void divide(int arr[],int si,int ei){
        if(si>=ei)
        return;
        int mid=si +  (ei-si)/2;
        divide(arr,si,mid);
        divide(arr,mid+1,ei);
        conquer(arr,si,mid,ei);
    }
    //Function to merge the array
    public static void conquer(int arr[],int si,int mid,int ei){

        int merge [] = new int[ei-si+1];
        int idx1=si;
        int idx2=mid+1;
        int x=0;
        while(idx1<=mid && idx2<=ei){
            if(arr[idx1]<=arr[idx2])
            merge[x++]=arr[idx1++]; 
            else
            merge[x++]=arr[idx2++];

        }
        // it may be possible that one of the arrays is not complete
        // copy the remaining elements
        while(idx1<=mid){
            merge[x++]=arr[idx1++]; 

        }
        while(idx2<=ei){
            merge[x++]=arr[idx2++];

        }
        for(int i=0,j=si;i<merge.length;i++,j++){
            arr[j]=merge[i];
        }
    }
    
}
