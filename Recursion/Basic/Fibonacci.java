package Recursion.Basic;

import java.util.*;

public class Fibonacci{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    System.out.println(fibo(n));
  }
  //Find the nth Fibonacci Number
  //Fibonacci Series 0,1,1,2,3,5,8,13,21...
  //nth fibo = (n-1)th fibo + (n-2)th fibo
  public static int fibo(int n){
    //base condition
    if(n<2) return n;
    return fibo(n-1)+fibo(n-2);
  }
}