package BitManipulation.Easy;

/*     231. Power Of Two
Given an integer n, return true if it is a power of two. Otherwise, return false.
An integer n is a power of two, if there exists an integer x such that n == 2x.
*/
// Every Number which is a power of two has only one bit set ,return  CountSetBits == 1;

public class PowerOfTwo{
    public static void main(String[] args) {
        int n = 16;

        System.out.println(isPowerOfTwo(n));
        System.out.println(isPowerOfTwo2(n));
        System.out.println(oddOrEven(n));

    }
    private static boolean isPowerOfTwo(int n) {
        int ones  = 0;
        if(n == Integer.MIN_VALUE) return false;
        for(int i = 0 ; i<32 ;i++){
            if((n & 1) == 1) ones++; 
            n = n>>1 ; // Instead of changing n , We can int temp = 1<<i; if((n & temp) == temp) ones++;
        }
        return ones==1;
    }

    // Other Solution
    //7  - 000..0111
    //8  - 000..1000        => 8 & 7 = 0
    private static boolean isPowerOfTwo2(int n){
        return n > 0 && ((n & (n-1)) == 0);
    }
    //Other Approaches can be
    // n>0 && Integer.bitCount(n) == 1;
    // 2^31 % n ==0   => ^ represents power 
    // Create set and add ( 2^1 , 2^2 , 2^6 , 2^4 ....... , 2^31)
    //return set.contains(n)


    //Check if a number is odd or even 
    //For every odd number the right most bit is set where as for even number it is not set
    private static boolean oddOrEven(int n){ // true - odd ,, false - even
        return ((n) & (1)) == 1;
    }
}