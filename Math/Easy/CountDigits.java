package Math.Easy;
/*
 * Given a integer N print the number of digits in N
 * Example : N= 7789 output = 4
 * Example : N= 450  output = 3
 */
public class CountDigits {
    public static void main(String[] args) {
        int N = 78955552 ;
        int cntDigit = 0;
        while(N>0){
            ++cntDigit;
            N /= 10;
        }
        System.out.println(cntDigit);
    }
    
}
