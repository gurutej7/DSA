package Math.Easy;

/*
You are Given a Number 'n'
Find the Number of digits of 'n' that evenly divide 'n' 

*A digit evenly divides 'n' if it leaves no remainder when dividing 'n'.

Example : n = 336  output = 3   Explanation : 3,3,6 all digits evenly divide n
Example : n = 35   output = 1   Explanation : only 5 evenly divides n
 */

public class CountDigitsTwo {
    public static void main(String[] args) {

        int n = 336;

        System.out.println(countDigits(n));
        
    }

    public static int countDigits(int n){
        // Write your code here.
        int cnt = 0;
        //Make a Copy of N in which the value will be changing
        int copyN = n ;
        while(copyN >0){
            int lastDigit = copyN%10;
            //check the condition for the current digit
            if(lastDigit != 0 && n % lastDigit == 0) cnt++;
            copyN /= 10;
        }
        return cnt;
    }
    
}
