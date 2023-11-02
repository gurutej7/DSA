package Recursion.Basic;

import java.util.*;
public class SumOfDigits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(sumOfDigits(n));
        System.out.println(productOfDigits(n));
    }
    //Function to Calculate the Sum of Digits of a Number
    public static int sumOfDigits(int n) {
        //Base condition
        if(n==0) return n;
        //Take the last Digit in the current Number n
        int rem = n%10;  
        // add it to the further current values in the further Function calls
        return rem+ sumOfDigits(n/10);
    }
    //Function to calculate the sum of Digits in a Number n
    public static int productOfDigits(int n){
        //Base Condition
        if(n==0) return 1;
        int rem = n%10;
        return rem*productOfDigits(n/10);
    }
  
}
