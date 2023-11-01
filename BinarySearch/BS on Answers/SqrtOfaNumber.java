/*
   Max Integer which on squaring <= N
   Given a Integer N we have to find its square root if exists else the nearest value
   of the squareroot.
Example 1:
Input Format: n = 36
Result: 6
Explanation: 6 is the square root of 36.

Example 2:
Input Format: n = 28
Result: 5
Explanation: Square root of 28 is approximately 5.292. So, the floor value will be 5.
 */
public class SqrtOfaNumber{
    public static void main(String[] args){
        int N = 100;
        System.out.println(sqrt(N));
    }

    public static int sqrt(int N){
        int low = 0;
        int high = N;
        int ans = 0;
        while(low<=high){
            int mid = low + (high-low)/2 ;
            if(mid <= N/mid) { // mid*mid <= N  condition can also be used
                ans = mid;
                low = mid+1; // Move to the Right half to find the Maximum nearer value
            }
            else{
        //If the current value square is greater then move to the left half for smaller values
            high = mid-1;
            }
        }
        return ans;
    }
}