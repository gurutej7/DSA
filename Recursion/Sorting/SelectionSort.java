package Recursion.Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class SelectionSort {
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
    selectionSort(arr,n,0,0);
    System.out.println(Arrays.toString(arr));
  }


  //Selection Sort
  public static void selectionSort(int arr[] ,int lastIndex,int index , int maxIndex){
      if (lastIndex == 0) 
          return;
      if (index < lastIndex) {
      if (arr[index] > arr[maxIndex]) {
        selectionSort(arr, lastIndex,index+1 , index);
      } else {
        selectionSort(arr, lastIndex, index+1, maxIndex);
      }
      } else {
      int temp = arr[maxIndex];
      arr[maxIndex] = arr[lastIndex-1];
      arr[lastIndex-1] = temp;
      selectionSort(arr, lastIndex-1, 0, 0);
      }
    }
}

  

