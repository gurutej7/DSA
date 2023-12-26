package Math.Easy;

public class isPrimeNumber {
    public static void main(String[] args) {

        System.out.println(isPrimebit(101));
        System.out.println(isPrime(101));
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false; // 0 and 1 are not prime numbers
        }

        if (n == 2 || n == 3) {
            return true; // 2 and 3 are prime numbers
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false; // Numbers divisible by 2 or 3 are not prime
        }

        // Check odd divisors from 5 up to the square root of n
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false; // n is divisible by i or i+2, so it's not prime
            }
        }

        return true; // If no divisors are found, n is prime
    }

    // Function to check if a number is prime using bit manipulation
    private static boolean isPrimebit(int n) {
        if (n <= 1) {
            return false; // 0 and 1 are not prime numbers
        }

        // Check if the rightmost bit is set (1)
        if ((n & 1) == 0) {
            return n == 2; // If n is even, it's prime only if it's 2
        }

        // Check odd divisors from 3 up to the square root of n
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false; // n is divisible by i, so it's not prime
            }
        }

        return true; // If no divisors are found, n is prime
    }
    
}
