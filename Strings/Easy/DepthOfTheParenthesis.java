package Strings.Easy;
/*  1614. Maximum Nesting Depth of the Parentheses
* Example 1:
Input: s = "(1+(2*3)+((8)/4))+1"
Output: 3
Explanation: Digit 8 is inside of 3 nested parentheses in the string.
* Example 2:
Input: s = "(1)+((2))+(((3)))"
Output: 3
 */

public class DepthOfTheParenthesis {
	public static void main(String[] args) {
		System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
		
	}

	/*
	 * Ignore digits and signs,only count the current open parentheses cur.The depth equals to the maximum open parentheses.
	 */
	public static int maxDepth(String s) {
        int ans = 0;
        int cntParentesis = 0;
        for(int i=0 ; i<s.length() ; i++){
            if(s.charAt(i)=='('){ 
            cntParentesis++;
            ans = Math.max(ans,cntParentesis);
            }
            if(s.charAt(i)==')') cntParentesis--;
        }
        return ans;
    }
  
  
}
