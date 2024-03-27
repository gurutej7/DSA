package Arrays.Easy;

import java.util.Arrays;

public class ArrayUtils {
    /*
     * Given a Integer Array find the second largest and second smallest in an array
     * Example :
     * Input: ‘n’ = 5, ‘a’ = [1, 2, 3, 4, 5]
     * Output: [4, 2]
     */

    // to get second largest and smallest elements in an array
    private static int[] secondLargestAndSmallest(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            } else if (arr[i] > secondMax && arr[i] != max)
                secondMax = arr[i];
            if (arr[i] < min) {
                secondMin = min;
                min = arr[i];
            } else if (arr[i] < secondMin && arr[i] != min)
                secondMin = arr[i];
        }

        return new int[] { secondMax, secondMin };

    }

    // to get maximum element from an array
    private static int getMax(int arr[]) {
        int max = Integer.MIN_VALUE;

        for (int i : arr)
            max = Math.max(max, i);

        return max;
    }

    // to get minimum element from an array
    private static int getMin(int arr[]) {
        int min = Integer.MAX_VALUE;

        for (int i : arr)
            min = Math.min(i, min);

        return min;
    }

    // to reverse an array
    private static void reverseArray(int arr[]) {
        int n = arr.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            swap(arr, left, right);

            right--;
            left++;
        }
    }

    // to swap two elements in place in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // to check if the array is sorted or not
    private static boolean isSorted(int[] arr) {
        int n = arr.length;
        if (n == 1)
            return true;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }

    // to get the prefix sum array of an given array
    private static int[] getPrefixSumArray(int arr[]) {
        int n = arr.length;
        if (n == 0)
            return new int[] {};
        int prefixSum[] = new int[n];
        prefixSum[0] = arr[0];
        if (n == 1)
            return prefixSum;

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        return prefixSum;
    }

    // from right to left
    private static int[] getPrefixSumArray2(int arr[]) {
        int n = arr.length;
        if (n == 0)
            return new int[] {};
        int prefixSum[] = new int[n];
        prefixSum[n - 1] = arr[n - 1];
        if (n == 1)
            return prefixSum;

        for (int i = n - 2; i >= 0; i--) {
            prefixSum[i] = prefixSum[i + 1] + arr[i];
        }
        return prefixSum;
    }

    // to get the product of all elements in an array
    private static int arrayProduct(int[] arr) {
        int product = 1;
        for (int num : arr) {
            product *= num;
        }
        return product;
    }

    // to get the sum of all elements in an array
    private static int arraySum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    // to check whether a elements exists in the array or not
    private static boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };

        System.out.println(Arrays.toString(secondLargestAndSmallest(arr)));
        System.out.println(getMax(arr));
        System.out.println(getMin(arr));
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(isSorted(arr));
        System.out.println(arrayProduct(arr));
        System.out.println(arraySum(arr));
        System.out.println(contains(arr, 1));
        System.out.println(Arrays.toString(getPrefixSumArray(arr)));
        System.out.println(Arrays.toString(getPrefixSumArray2(arr)));

    }
}