package DP.OneD;
/*
  There is a frog on the '1st' step of an 'N' stairs long staircase. The frog wants to reach the 'Nth' stair. 
  'HEIGHT[i]' is the height of the '(i+1)th' stair.If Frog jumps from 'ith' to 'jth' stair, the energy lost in the 
  jump is given by absolute value of ( HEIGHT[i-1] - HEIGHT[j-1] ). If the Frog is on 'ith' staircase, he can jump
   either to '(i+1)th' stair or to '(i+2)th' stair. Your task is to find the minimum total energy used by the frog 
   to reach from '1st' stair to 'Nth' stair.

For Example
If the given ‘HEIGHT’ array is [10,20,30,10], the answer 20 as the frog can jump from 1st stair to 2nd stair 
(|20-10| = 10 energy lost) and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost). So, the
 total energy lost is 20.
 */

// import java.util.Arrays;

// { https://www.codingninjas.com/studio/problems/frog-jump_3621012?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM }

public class FrogJump {
    public static void main(String[] args) {
        int heights[] = {10,20,30,10};
        int n = 4;

        System.out.println(frogJump(n, heights));
    }

    public static int frogJump(int n, int heights[]) {

         // Write your code here..
         if(n <= 1) return 0;
         int dp[] = new int [n];
 
         // Tabulation
         dp[0] = 0 ; // Base cases
         
         for(int i = 1 ; i<n ; i++){
             // Copy the recurrence
             int one = Math.abs(heights[i] - heights[i-1]) + dp[i-1];
             int two = (int)1e9+7;
             if(i>1) two = Math.abs(heights[i] - heights[i-2]) + dp[i-2];
             dp[i] = Math.min(one,two);
         }
         return dp[n-1];


        // Arrays.fill(dp,-1);
        // return frogJumpMemo(n-1, heights, dp);

    }

    public static int frogJumpMemo(int n , int heights[] , int dp[]){
        // Base case we have reached the end
        if(n == 0) return 0 ; 

        if(dp[n] != -1) return dp[n]; // To avoid recomputing

// We choose to jump one step ;
// If we are jumping one step then we have to calculate energy for that and call recursively for the remaining
        int one = Math.abs(heights[n] - heights[n-1]) + frogJumpMemo(n-1, heights,dp);
        int two = (int)1e8+7;
        if(n > 1){ // We can only jump two steps if there are more than one step
            two = Math.abs(heights[n] - heights[n-2]) + frogJumpMemo(n-2, heights, dp);

        }
        // We need minimum of the two choices we made earlier that is either one step nor two steps
        dp[n] = Math.min(one,two);
        return dp[n];
    }
    
}
