package MonotonicStack;

import java.util.Stack;

/* 42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 water it can trap after raining.

Example 1 : 
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]                   Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this
 case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]                               Output: 9

Problem Link : { https://leetcode.com/problems/trapping-rain-water/description/ }

 */

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        
        System.out.println(trap(height));

    }

     public static  int trap(int[] height) {
         // Stores the indices of the bars 
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i = 0; i < height.length; i++) {
             // Remove bars from the stack 
            // until the condition holds 
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                // store the height of the top and pop it.
                int top = height[stack.peek()];
                stack.pop();
                // If the stack does not have any  bars or the popped bar  has no left boundary 
                if(stack.isEmpty()) break; 
                // Get the distance between the left and right boundary of  popped bar 
                int width = i - stack.peek() - 1;
                // Calculate the min. height 
                int length = Math.min(height[i], height[stack.peek()]) - top;

                ans += length * width;
                
            }
            // If the stack is either empty or height of the current bar is less than or equal to the top bar of stack 
            stack.push(i);
            //Just Dry run once you will get it
        }
        return ans;
    }
    
}
