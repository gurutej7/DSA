package Math.Medium;

public class MyPow {
    public static void main(String[] args) {
        double x = 5;
        int n = 10;

        System.out.println(myPow(x,n));

        System.out.println(myPowiterative(x, n));
    }
    public static double myPow(double x, int n) {
        double ans = 1;
        long N = n; // Change the type of N to long to handle the case when n is Integer.MIN_VALUE
        /*The statement long N = n; is intended to address potential issues that may arise when dealing with the minimum value of the int data type (Integer.MIN_VALUE). In Java, the minimum value of an int is -2147483648, and taking the absolute value of this number (-(-2147483648)) would result in an overflow, as the positive equivalent 2147483648 is beyond the range of representable integers.*/
    
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return customPow(x, N, ans);
    }
    
    public static double customPow(double x, long n, double ans) {
        if (n == 0) 
            return ans;
        if (n % 2 == 0) 
        //if pow is even double the value of x and n /=2
        //Example : 5^10 => 25^5 => (5*5)^(10/2)
            return customPow(x * x, n / 2, ans);
        else 
        //if pow is odd then we have to make it even
        //25^5 => 25*(25^4)
            return customPow(x, n - 1, x*ans);
    
            /*Consider 5^10
            step 1 : even => x = 25(5*5) , n = 5 (10/2) , ans = 1
            step 2 : odd => x = 25 , n = 4 , ans = 25
            step 3 : even => x = 625(25*25) , n =2 , ans = 25
            step 4 : even => x = 390625(625*625) , n =1, ans = 25
            step 5 : odd => x = 390625 , n = 0 , ans = 9765625(25*390625)
            step 6 : zero => return ans i.e, 9765625 == 5^10
            
            */
    }

    //Iterative approach
    public static double myPowiterative(double x, int n) {
        double ans = 1.0;
        long N = n;
        if (N < 0) N = -1 * N;
        while (N > 0) {
          if (N % 2 == 1) {
            ans = ans * x;
            N = N - 1;
          } else {
            x = x * x;
            N = N / 2;
          }
        }
        if (n < 0) ans = (double)(1.0) / (double)(ans);
        return ans;
      }
    
}
