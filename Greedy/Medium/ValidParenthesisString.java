package Greedy.Medium;

import java.util.Stack;

/* 678. Valid Parenthesis String
{ https://leetcode.com/problems/valid-parenthesis-string/description/ } 

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
The following rules define a valid string:
* Any left parenthesis '(' must have a corresponding right parenthesis ')'.
* Any right parenthesis ')' must have a corresponding left parenthesis '('.
* Left parenthesis '(' must go before the corresponding right parenthesis ')'.
* '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:      Input: s = "()"         Output: true
Example 2:      Input: s = "(*)"        Output: true

 */
@SuppressWarnings("unused") 
public class ValidParenthesisString{
    public static void main(String[] args) {
        String s = "()";

        System.out.println(checkValidString(s));
    }

    
    private static boolean checkValidString(String s) {
       // approach using stacks
       Stack<Integer> open = new Stack<>(); // keeps track of open brackets
       Stack<Integer> star = new Stack<>(); // keeps strack of the asterk * symbol
       for(int i = 0 ; i < s.length() ; i++){
           if(s.charAt(i) == '(') open.push(i);
           else if(s.charAt(i) == '*') star.push(i);
           else{ // s.charAt(i) == '('
                // first check if exist`s a ( on left , if exist`s use it to balance
                if(!open.isEmpty()) open.pop();
                // if we don`t have ( check for * , and use it to balance
                else if(!star.isEmpty()) star.pop();
                // if we don`t have either ( or * to balance then it is invalid string 
                else return false;
           }
       }

       // if we are here means the all the closed ) are balanced
       // but there can be extra open ( , this can be compensated if * exist on the right side of available (
       // Return true if. we can close the remaining '(' with the remaining '*'. We can only close an '(' if there is an '*' that occurs at a greater index in s.    
       while(!open.isEmpty() && !star.isEmpty()){
           if(open.pop() > star.pop()) return false; // if any one of the ( exist after the available * , then it can`t be balanced
       } 

       return open.isEmpty();
    }

    // dp approach  - TC- O(N^2)
    private static boolean checkValidStringTabu(String s) {
        int n = s.length();
        // Boolean[][] dp = new Boolean[n+1][n+1];
        boolean[][] dp = new boolean[n+1][n+2];
        dp[n][1] = true;//base case

        for(int ind = n-1 ; ind >= 0 ; ind--){
            for(int cnt = 1 ; cnt <= n ; cnt++){
                //copy the recurrence
                 boolean case1 = false , case2 = false , case3 = false ;
                if(s.charAt(ind) == '(') case1 = dp[ind+1][cnt+1];
                else if(s.charAt(ind) == ')') case2 = dp[ind+1][cnt-1];
                else{ // s.charAt(ind) == '*'
                    boolean subcase3 = dp[ind+1][cnt]; // ""
                    boolean subcase1 = dp[ind+1][cnt+1]; // (
                    boolean subcase2 = dp[ind+1][cnt-1]; // )
                    case3 = (subcase1) || (subcase2 || subcase3);
                    
                }
                dp[ind][cnt] = (case1) || (case2 || case3);

                // return dp[ind][cnt];
            }
        }

        return dp[0][1];
    //    return  memo(dp,0,s,0);
    }

    private static boolean memo(Boolean[][] dp , int ind , String s , int cnt){
        if(cnt == 0 && ind == s.length()) return true;
        if(ind == s.length()) return false;
        if(cnt < 0 ) return false; 

        if(dp[ind][cnt] != null) return dp[ind][cnt];

        boolean case1 = false , case2 = false , case3 = false ;
        if(s.charAt(ind) == '(') case1 = memo(dp,ind+1,s,cnt+1);
        else if(s.charAt(ind) == ')') case2 = memo(dp,ind+1,s,cnt-1);
        else{ // s.charAt(ind) == '*'
            boolean subcase3 = memo(dp,ind+1,s,cnt); // ""
            boolean subcase1 = memo(dp,ind+1,s,cnt+1); // (
            boolean subcase2 = memo(dp,ind+1,s,cnt-1); // )
            case3 = (subcase1) || (subcase2 || subcase3);
            
        }
        dp[ind][cnt] = (case1) || (case2 || case3);

        return dp[ind][cnt];
    }
}