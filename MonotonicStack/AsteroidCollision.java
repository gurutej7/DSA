package MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

/* 735. Asteroid Collision
We are given an array asteroids of integers representing asteroids in a row.
For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning 
right, negative meaning left). Each asteroid moves at the same speed.
Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If 
both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: asteroids = [5,10,-5]                    Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

*/

public class AsteroidCollision {
    public static void main(String[] args) {
        int[] asteroids = {5,10,-5};

        System.out.println(  Arrays.toString(  asteroidCollision(asteroids)  )  );
    }
    
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int curr : asteroids) {
            if (curr > 0)  // previous one does not matter, no collision forever
                stack.push(curr);
            
            else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -curr) {
                     // destroy the previous positive one(s)  which are less than the absolute value of curr
                    stack.pop();
                }
                //After destroying all the weak ones if stack is empty or the last value is -ve just push the current
                if (stack.isEmpty() || stack.peek() < 0) 
                    stack.push(curr);

                else if (stack.peek() == -curr) 
                    stack.pop();
                //We have skipped the push operation where the value of -curr < stack.peek() // skip it
                // We did not cover the above skip case ,  because the above cases already take care of every other case
                
            }
        }
        int res [] = new int[stack.size()];
        for(int i=stack.size() - 1 ; i>=0 ; i--){
            res[i] = stack.pop();
        }
        return res;        
    }
}
