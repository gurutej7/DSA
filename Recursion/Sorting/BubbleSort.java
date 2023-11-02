package Recursion.Sorting;

import java.util.Scanner;
import java.util.Arrays;

public class BubbleSort{
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
      bubbleSort(arr,arr.length-1,0);
      System.out.println(Arrays.toString(arr));
  }

  //Bubble Sort
  public static void bubbleSort(int[] arr , int lastIndex , int index){
      if(lastIndex<0) return;

      if(index< lastIndex){
        if(arr[index]>arr[index+1]){
          int temp = arr[index];
          arr[index] = arr[index+1];
          arr[index+1] = temp;
        }
        bubbleSort(arr, lastIndex, index+1);
      }
      else bubbleSort(arr, lastIndex-1, 0);
    }
  
}