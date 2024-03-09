package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;


public class RemoveKDigits {

    /* 402. Remove K digits
     * https://leetcode.com/problems/remove-k-digits/description/
     * 
     * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
     * Example 1:
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     */
    
    
    private static String removeKdigits(String num, int k) {
        int n = num.length();
        if(k >= n) return "0";

        Deque<Character> st = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++){
            char ch = num.charAt(i);
            // whenever meet a digit which is less than the previous digit, discard the previous one
            while(k > 0 && !st.isEmpty() && st.peekLast() > ch){
                st.pollLast();
                k--;
            }
            st.offerLast(ch);
        }

        // if we still have operations 
        while(k-- > 0)st.pollLast();

        // Remove all zeros from the front of the stack and then if stack is empty, return "0"
        while(!st.isEmpty() && st.peekFirst() == '0') st.pollFirst();
        if(st.isEmpty()) return "0";

        // construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pollFirst());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String num = "1432219";
        System.out.println(removeKdigits(num, 3));
    }
}
