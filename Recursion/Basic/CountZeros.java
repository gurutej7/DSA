package Recursion.Basic;

import java.util.Scanner;
public class CountZeros {
    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    System.out.println(countZeros(n,0));
    }
    
    //Function to Count the Number of Zeros in a Number
    public static int countZeros(int n,int count){
        //Base Condition
        if(n==0) return count;
        int rem = n%10;
        //if the remainder is zero when divided by 10 means it is a 0 , So increase count by 1
        if(rem==0) return countZeros(n/10,count+1);
        //if rem is not zero then the digit is also not 0 so dont increase the count
        else return countZeros(n/10,count);
    }
}


