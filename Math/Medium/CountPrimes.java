package Math.Medium;

import java.util.Arrays;

/* 204. Count Primes
Given an integer n, return the number of prime numbers that are strictly less than n.
Example 1:      Input: n = 10           Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

public class CountPrimes {
    public static void main(String[] args) {
        

        System.out.println(countPrimes(10));
    }
    
    /* Hints
     * Checking all the integers in the range [1, n - 1] is not efficient. Think about a better approach.
     * Since most of the numbers are not primes, we need a fast approach to exclude the non-prime integers.
     * Use Sieve of Eratosthenes.
     */

     private static int countPrimes(int n) {
        if(n <=2) return 0 ;
        boolean primeMap[] = new boolean[n];
        //primeMap[i] == true -> i is not a prime
        //else -> i is a prime
        primeMap[0] = true;
        primeMap[1] = true;
        for(int i = 2 ; i*i < n ; i++){
            if(!primeMap[i]){ // If it is a prime then mark all its multiples as Not prime
                for(int j = i*i ; j < n ; j+=i){
                    primeMap[j]=true;
                }
            }
        }

        int cnt = 0 ;
        for(boolean f : primeMap){
            if(!f) ++cnt;
        }
        return cnt;
    }

    public int countPrimes2(int n) {
        if (n <= 2) return 0;

        // Use an array to mark whether a number is prime or not
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        // 0 and 1 are not prime
        isPrime[0] = isPrime[1] = false;

        // Iterate up to the square root of n
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // Mark multiples of i as non-prime
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Count the number of primes
        int count = 0;
        for (boolean prime : isPrime) {
            if (prime) count++;
        }

        return count;
    }

}
