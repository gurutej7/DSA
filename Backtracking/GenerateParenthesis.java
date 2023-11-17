/* 22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:      Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:      Input: n = 1
Output: ["()"]

 */

import java.util.ArrayList;
import java.util.List;


public class GenerateParenthesis {
    public static void main(String[] args) {
        int n = 3;

        System.out.println(generateParenthesis(n));
    }

     public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
    public static void backtrack(List<String> list, String str, int openCnt, int closeCnt, int n){
        // both open ( and close ) combined for given n we can only generate a string of length n,
        if(str.length() == n*2){
            list.add(str);
            return;
        }
        //Simple pick or not pick with some conditions
        
        //We can add only n open (  since we are starting with 0 it is < n
        if(openCnt < n)
            backtrack(list, str+"(", openCnt+1, closeCnt, n);
            //We can add )  if there are more ( than )
        if(closeCnt < openCnt)
            backtrack(list, str+")", openCnt, closeCnt+1, n);
    }
    
}
