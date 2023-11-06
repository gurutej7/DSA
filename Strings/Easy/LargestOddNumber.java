package Strings.Easy;
/* 1903. Largest Odd Number In a String
* Example 1:
Input: num = "52"
Output: "5"
Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
* Example 2:
Input: num = "4206"
Output: ""
Explanation: There are no odd numbers in "4206".
* Example 3:
Input: num = "35427"
Output: "35427"
Explanation: "35427" is already an odd number.
 */

public class LargestOddNumber {
    public static void main(String[] args) {
        String num = "420678";
        System.out.println(largestOddNum(num));
    }

    //Hint: just search for the last odd digit
    //Got this one after looking at the Hint (:
    public static String largestOddNum(String num) {
        int n = num.length()-1;
        for(int i=n; i>=0 ;i--){
            int a = num.charAt(i)-'0';
            if(a%2!=0) return num.substring(0,i+1);
        }
        return "";
    }
  
}
