package Math.Medium;
/* 7. Reverse Integer
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside 
the signed 32-bit integer range [-231, 231 - 1], then return 0.
*   Example 1:      Input: x = 123      Output: 321
*   Example 2:      Input: x = -123     Output: -321
*   Example 3:      Input: x = 120      Output: 21
*/

public class ReverseNumber{
    public static void main(String[] args) {

        int n = 1230;

        System.out.println(reverse(n));
        
    }

    public static int reverse(int n) {
        if(n == 0 ) return 0;
        //Based On Given Constraints The reversed Integer may Overflow the Integer limits ,So take it as long
        long result = 0 ;
        while(n!=0){
            //To get the last Digit
            int lastDigit = n%10 ;
            //Decrease n by one digit
            n /= 10;
            //Use pen and paper to understand the below line better
            result = (result*10)+lastDigit ;
        }
        //Check for the edge case stated in the problem 
        //"If reversing n causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0."
        if(result >= Integer.MAX_VALUE || result<= Integer.MIN_VALUE) return 0;

        return (int)result ;
    }
}