package Math.Easy;

import java.util.ArrayList;

/* Given a Integer n we have to return a list of numbers that are factorial numbers less than equal to n.
A Factorial number is a factorial of a positive integer like, 24 is a factorial of 4.
Given n >= 1
Input: ‘n’ = 7      Output: 1 2 6
Explanation: Factorial numbers less than or equal to ‘7’ are ‘1’, ‘2’, and ‘6’.
 */

public class FactorialNumbersNotGreaterThanN {
    public static void main(String[] args) {
        int n = 7 ;

        System.out.println(factorialNumbers(n));
    }

    public static ArrayList<Long> factorialNumbers(long n) {
        // Write Your Code Here
        ArrayList<Long> ans = new ArrayList<>();
        long i = 1;
        long factorial = 1;
        //we will begin with one and keep on going till factorial <= n
        while (factorial <= n) {
            ans.add(factorial);
            i++;
            factorial *= i;
        }

        return ans;
    }
    
}
