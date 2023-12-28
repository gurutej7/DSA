package Stacks.Easy;

import java.util.HashMap;
import java.util.Stack;

/*  20. Valid Parentheses
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
* Open brackets must be closed by the same type of brackets.
* Open brackets must be closed in the correct order.
* Every close bracket has a corresponding open bracket of the same type.

Example 1:      Input: s = "()"         Output: true
Example 2:      Input: s = "()[]{}"     Output: true
Example 3:      Input: s = "(]"         Output: false
Example 4:      Input: s = "[()]{}"     Output: true
*/
public class ValidParentheses{
    public static void main(String[] args) {
        String s = "[()]{}";

        System.out.println(isValidParenthesis(s));

        System.out.println(isValid(s));
        
    }
    //When you encounter an opening bracket, push it to the top of the stack.
    //When you encounter a closing bracket, check if the top of the stack was the opening for it. If yes, pop
    //it from the stack. Otherwise, return false.

    //My first time solution that I submitted
    public static boolean isValid(String s) {
        if(s.length() < 2) return false;
        Stack<Character> st = new Stack<>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        for(int i = 0 ; i< s.length() ; i++){
            if(!map.containsKey(s.charAt(i))) st.push(s.charAt(i));
            else{
                if(st.isEmpty()) return false;
                char ch = st.pop();
                if(map.get(s.charAt(i)) != ch) return false;

            }
        }
        return st.isEmpty();

    }

    //Leetcode top solution 
    public static boolean isValidParenthesis(String s) {
        Stack<Character> st = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == '(') st.push(')');
            else if(ch == '[') st.push(']');
            else if(ch == '{') st.push('}');
            else if(st.isEmpty() || ch != st.pop()) return false;
        }
        return st.isEmpty();
    }
}