package MonotonicStack;

import java.util.Stack;
import java.util.Arrays;

/* 739. Daily Temperatures
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer
[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day
 for which this is possible, keep answer[i] == 0 instead.
Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]                 Output: [1,1,4,2,1,1,0,0]
Example 2:
Input: temperatures = [30,40,50,60]                             Output: [1,1,1,0]

 */

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};

        System.out.println(  Arrays.toString(  dailyTemperatures(temperatures)  )  );
    }
    
    // Similar to Next Greater Element
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length ;
        int [] res = new int[n];
        // A typical stack problem
        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i<n ; i++){
            //For all values in stack which are less than the current temperature will have the current temperature as the next warmer day
            while(!st.isEmpty() && temperatures[st.peek()] < temperatures[i]){
                //Number of days  = Warmer day - old Day
                res[st.peek()] = i-st.peek();
                //After computing the days for a temperature then remove it
                st.pop();
            }
            st.push(i);
        }

        return res;

    }
}
