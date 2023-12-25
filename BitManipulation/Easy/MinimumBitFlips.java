package BitManipulation.Easy;

/* 2220. Minimum Bit Flips to Covert a Number
A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1
 or 1 to 0.
Example 1:
Input: start = 10, goal = 7                     Output: 3
Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
- Flip the first bit from the right: 1010 -> 1011.
- Flip the third bit from the right: 1011 -> 1111.
- Flip the fourth bit from the right: 1111 -> 0111.
It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.
 */

public class MinimumBitFlips {
    public static void main(String[] args) {
        int a = 10 , b = 7;

        System.out.println(minBitFlips1(a, b));
         System.out.println(minBitFlips2(a, b));
    }
    //Naive approach is to count the number of bits which are not same
    public static int minBitFlips1(int a, int b) {
        int cnt = 0;
        for(int i = 0 ; i<32 ; i++){
            //ith bit at a is 0  AND ith bit at b is 1
            if(( (a >> i) & (1) ) == 0 && ((b >> i) & (1))  != 0)cnt++;
            //ith bit at a is 1  AND ith bit at b is 0
            else if(( (a >> i) & (1) )  == 1 && ((b >> i) & (1) ) != 1)cnt++;
        }
        return cnt;
    }

    //Other approach is using XOR 
    //Xor has 0 for same bit and 1 for different bit 
    //We can have Xor of a,b and count the number of 1 bits in it

    private static int minBitFlips2(int a, int b){
        int Xor = a^b , count = 0;
        while(Xor >0 ){
            count++;
            //To unset the right most set bit 
            //n =1101    n-1 = 1100      n & (n -1) = 1100 (we eliminated the right most 1)
            //TO set the right most unset bit
            //n| (n+1)
            Xor = ((Xor)&(Xor-1));
        }
        return count;
    }
}
