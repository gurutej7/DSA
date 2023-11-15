package Math.Easy;

/* Armstrong Numbers
For a given 3 digit number, find whether it is armstrong number or not. An Armstrong number of three digits is an integer such that the sum of the cubes of its digits is equal to the number itself. Return "Yes" if it is a armstrong number else return "No".
NOTE: 371 is an Armstrong number since 33 + 73 + 13 = 371
 */

public class ArmstrongNumber {
    public static void main(String[] args) {
        int n = 371 ;
        int m = 1634 ; // 1634 is an armstrong number, as 1^4 + 6^4 + 3^4 + 4^4 = 1634

        System.out.println(armstrongNumber(n));

        System.out.println(armStrongnum(m));
    }
    
    private static String armstrongNumber(int n){
        // code here
       //Make a copy of n as N
		int N = n , sum = 0;
		while(N>0){
            //Get the unit digit add it to the sum
			int digit = N%10 ;
			sum += (digit*digit*digit);
            //Decrease N by one Digit
			N /= 10;
		}
		//If sum of cubes of digits is equal to the number then it is a armstrong number
		return n==sum ? "Yes" : "No";
    }

    //For Length More than Three
    private static String armStrongnum(int n){
        String strNum = Integer.toString(n);
		int len = strNum.length();

		int N = n , sum = 0;
		while(N>0){
			int digit = N%10 ;
			sum += Math.pow(digit,len);
			N /= 10;
		}
        return n==sum ? "Yes" : "No";
    }
}
