package Stacks.Medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class SlidingWindowMaximum{
    
    /* 239. Sliding Window Maximum
     * https://leetcode.com/problems/sliding-window-maximum/description/
     * 
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
     * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     * Return the max sliding window.
     * Example 1:

        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        Output: [3,3,5,5,6,7]
        Explanation: 
        Window position                Max
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
        1 [3  -1  -3] 5  3  6  7       3
        1  3 [-1  -3  5] 3  6  7       5
        1  3  -1 [-3  5  3] 6  7       5
        1  3  -1  -3 [5  3  6] 7       6
        1  3  -1  -3  5 [3  6  7]      7
     */


    private static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int res[] = new int[n-k+1];
        if(n == 0) return res;
        Deque<Integer> dq  = new ArrayDeque<>();

        for(int i = 0 ; i < n ; i++){
            // remove the front elements which are not part of our window
            if(!dq.isEmpty() && dq.peekFirst() == i-k) dq.pollFirst();

            // insert elements in the decreasing order i.e, max element is always in the front , and the order is followed
            // from the back check if there are any smaller elements
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();

            dq.offerLast(i);

            // if it is a window have a maxi updated in the result
            if(i >= k-1) res[i-k+1] = nums[dq.peekFirst()];
        }

        return res;
    }

    public static void main(String[] args) {
        int nums[] = {1,3,-1,-3,5,3,6,7};
        int k = 3 ;

        System.out.println( Arrays.toString(maxSlidingWindow(nums, k)));
    }


    /* 901. Online stock Span
     * https://leetcode.com/problems/online-stock-span/description/
     * 
     */
    static class StockSpanner {
        Stack<int[]> st ;
        public StockSpanner() {
            this.st = new Stack<>();
        }
        
        public int next(int price) {
            int cnt = 1 ;
    
            while(!st.isEmpty() && st.peek()[0] <= price){
                cnt += st.pop()[1];
            }
            st.push(new int[]{price,cnt});
    
            return cnt;
        }
    }

}