package Recursion.Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
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
        quickSort(arr, 0, n-1);
        System.out.println(Arrays.toString(arr));
        in.close();

    }
    public static void quickSort(int[] nums, int low, int hi) {
        if (low >= hi) {
            return;
        }

        int s = low;
        int e = hi;
        int m = s + (e - s) / 2;
        int pivot = nums[m];

        while (s <= e) {
            // also a reason why if its already sorted it will not swap
            while (nums[s] < pivot) {
                s++;
            }
            while (nums[e] > pivot) {
                e--;
            }

            if (s <= e) {
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                s++;
                e--;
            }
        }
        // now my pivot is at correct index, please sort two halves now
        quickSort(nums, low, e);
        quickSort(nums, s, hi);
    }
}
    

