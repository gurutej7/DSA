package Recursion.Basic;


import java.util.*;
public class Factorial {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    System.out.println(fact(n));
    long m  = in.nextLong();
    System.out.println(factLong(m));
  }
  //Function to return the factorial of a number
  static int fact(int n){
    if(n==1) return 1;
    return n*fact(n-1);
  }
  //The Integer value may overflow use long for larger input i.e, n>10 or more
  static long factLong(long n){
    if(n==1) return 1;
    return n*factLong(n-1);
  }
}
