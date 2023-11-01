/*  Given two numbers N and M, find the Nth root of M. The nth root of a number M is 
defined as a number X when raised to the power N equals M. If the ‘nth root is not an
 integer, return -1.
    Find Nth Root of a Number
    Example 1: N= 3 , M= 27   
    output = 3 (3*3*3 = 27)
    Example 2: N=4 , M = 69 
    output = -1
 */

public class NthRoot {
    public static void main(String[] args) {
        int N = 5;
        int M = 32;
        System.out.println(nThRootOfaNum(N, M));
    }

    public static int nThRootOfaNum(int N,int M){
        
        int low = 0;
        int high = M;
        while(low<=high){
            int mid = low +(high-low)/2 ;
            int var = (int)Math.pow(mid,N);//calculate mid^N
            if(var == M) return mid; // mid^N == M  means we have found the perfect Nth root
            else if(var < M) low = mid+1; //move to the right half for higher values and vice versa
            else high = mid-1;
        }
        return -1;
    }
}
