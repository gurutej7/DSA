package Math.Easy;

/*
 * Given a integer n , return the sum of first n Natural numbers 
 * given n>=1
 */

public class SumOfNaturalNumbers{
    public static void main(String[] args) {
        long n = 10;

        System.out.println(sumFirstN(n));
    }

    public static long sumFirstN(long n) {
        // Write your code here.
       return n*(n+1)/2 ;
       //Formula to find sum of N natural numbers is n*(n+1)/2

    }

}