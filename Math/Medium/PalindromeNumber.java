package Math.Medium;

/* 9. Palindrome Number
Given an integer x, return true if x is a palindrome, and false otherwise.

*   Example 1:      Input: x = 121      Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
*   Example 2:      Input: x = -121     Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
*   Example 3:      Input: x = 10       Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

*/

public class PalindromeNumber {
    public static void main(String[] args) {
        int x =121;

        System.out.println(isPalindrome(x));
        
    }

    public static boolean isPalindrome(int x) {
       //edge cases are x inegative(-252) and x is evenly divisible by 10 (100,1000,2000...)
        if (x<0 || (x!=0 && x%10==0)) return false;
        //We will reverse the current number and check if its equal or not
        int rev = 0;
        int copyX = x;
        //Make a copy of x on which we will be performing operations 
        //We need original value to check at the last
        while (copyX>0){
            //To get the unit digit of a num => modulo 10
            int lastDigit = copyX%10;
            //Use Pen and paper , you will get it
            rev = rev*10 + lastDigit;
            //Reduce the number by one digit
            copyX /= 10;
        }

        return x==rev;
    }
    
}
