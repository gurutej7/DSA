package Arrays.Hard;

public class ReversePairs {

    /* 493.Reverse Pairs
     * 
     * { https://leetcode.com/problems/reverse-pairs/description/ }
     * 
     * Example 1:   Input: nums = [1,3,2,3,1]
     * Output: 2
     * Explanation: The reverse pairs are:
     * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
     * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
     * 
     */

    // Function to divide the Array into two halves
    // si = starting index
    // ei = ending index
    private static int divide(int arr[], int si, int ei) {
        int cnt = 0;
        if (si >= ei)
            return cnt;
        int mid = si + (ei - si) / 2;
        cnt += divide(arr, si, mid);
        cnt += divide(arr, mid + 1, ei);
        cnt += conquer(arr, si, mid, ei);

        return cnt;
    }

    // Function to merge the array
    private static int conquer(int arr[], int si, int mid, int ei) {
        int merge[] = new int[ei - si + 1];
        int cnt = 0;
        int idx1 = si;
        int idx2 = mid + 1;
        int x = 0;
        // count pairs
        // si to mid is one sorted array and mid+1 to ei is another sorted array
        int right = mid + 1;
        for (int i = si; i <= mid; i++) {
            // go till elements on the right side array which can satisfy for the current element in the left side array
            while (right <= ei && 1L*arr[i] > 1L*2 * arr[right])
                right++;

            cnt += (right - (mid + 1));
        }
        while (idx1 <= mid && idx2 <= ei) {
            if (arr[idx1] <= arr[idx2])
                merge[x++] = arr[idx1++];
            else
                merge[x++] = arr[idx2++];

        }
        // it may be possible that one of the arrays is not complete
        // copy the remaining elements
        while (idx1 <= mid) {
            merge[x++] = arr[idx1++];

        }
        while (idx2 <= ei) {
            merge[x++] = arr[idx2++];

        }
        // copy to original array
        for (int i = 0, j = si; i < merge.length; i++, j++) {
            arr[j] = merge[i];
        }

        return cnt;
    }

    private static int reversePairs(int[] nums) {
        int n = nums.length;
        return divide(nums, 0, n - 1);
    }

    public static void main(String[] args) {
        int nums[] = {1,3,2,3,1};
        System.out.println(reversePairs(nums));
    }
    
}
