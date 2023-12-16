package Strings.Medium;
/* 8. String to Integer ( atoi )
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s 
 * atoi function).
 */

public class atoi {
    public static void main(String[] args) {
        String s = "    -4256guru";

        System.out.println(myAtoi(s));
    }

    public static int myAtoi(String s) {
        int i = 0  , sign = 1 , num = 0;
        int n = s.length() ;
        
        if(n == 0) return 0;
        //Skip the leading whiteSpaces
        while(i<n && s.charAt(i) == ' ') i++;
        //Check if the string is empty after avoiding white spaces
        if(i == n) return 0;

        //Check for the sign and save it , to use it in the end
        if(s.charAt(i) == '-' || s.charAt(i) == '+'){
            sign =  s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        //Now the actual task beigins => to covert string to integer
        int MAX = Integer.MAX_VALUE;
        int MIN = Integer.MIN_VALUE;
        while(i< n){
            int currDigit = s.charAt(i)-'0';
            i++;

            //Check if it is not a integer then we have to stop
            if(!(currDigit >=0 && currDigit <=9)) break;

            // Now check for the overflow cases that may occur after adding the current digit
            /* num > MAX/10 => if the running num is already greater than what the maximum integer value would be
             after appending the current digit. If this condition is true, it means adding the next digit would 
             result in an overflow */
            /* (num == Integer.MAX_VALUE / 10 ): Checks if the total is at the maximum possible value just before adding the current digit.

            Integer.MAX_VALUE % 10 < digit: Checks if adding the current digit will not cause an overflow in the 
            units place.
              */

            if(num > MAX/10 || ( num == MAX/10 && currDigit > MAX % 10) ){
                return sign == 1 ? MAX : MIN ;
            }

            //Update the num with adding the current digit  at the end
            num = num*10 + currDigit;

        } 
        return sign*num;
    }
    
}
