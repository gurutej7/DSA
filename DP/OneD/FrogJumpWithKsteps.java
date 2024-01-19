package DP.OneD;

import java.util.Arrays;

/* There is an array of heights corresponding to 'n' stones. You have to reach from stone 1 to stone ‘n’.
From stone 'i', it is possible to reach stones 'i'+1, ‘i’+2… ‘i’+'k' , and the cost incurred will be | Height[i]-Height[j] |, where 'j'
 is the landing stone.
Return the minimum possible total cost incurred in reaching the stone ‘n’.
Example:
For 'n' = 3 , 'k' = 1, height = {2, 5, 2}.
Answer is 6.
Initially, we are present at stone 1 having height 2. We can only reach stone 2 as ‘k’ is 1. So, cost incurred is |2-5| = 3. Now, we are
 present at stone 2, we can only reach stone 3 as ‘k’ is 1. So, cost incurred is |5-2| = 3. So, the total cost is 6.
 */

public class FrogJumpWithKsteps {
    public static void main(String[] args) {
        int n = 3;
        int k = 1;
        int heights[] = {2,5,2};

        System.out.println( minimizeCostMemo(n, k, heights) );
        System.out.println( minimumCostTabulation(n, heights, k) );
    }


//{ https://www.codingninjas.com/studio/problems/minimal-cost_8180930?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION }
     public static int minimizeCostMemo(int n, int k, int []heights){
        // Write your code here.
        if(n <= 1) return 0;
        int dp[] = new int [n];
        Arrays.fill(dp,-1);
        return frogJumpMemo(n-1, heights, dp,k);
    }

     public static int frogJumpMemo(int n , int heights[] , int dp[] , int k ){
        // Base case we have reached the end
        if(n == 0) return 0 ; 

        if(dp[n] != -1) return dp[n]; // To avoid recomputing

        int mini = Integer.MAX_VALUE;
        for(int i = 1 ; i <= k ; i++){ // At each step we have k choices
            if(n-i < 0 ) break; // Index out of bound 
            // After making a choice out of k choices 
            // call the function for the remaining n steps
            // Take minimum of them all
            int currEnergy = Math.abs(heights[n] - heights[n-i]) + frogJumpMemo(n-i, heights, dp, k);
            mini = Math.min(mini , currEnergy); 

        }
        // We need minimum of all the choices we made earlier that is either one step nor two steps
        dp[n] = mini;
        return dp[n];
    }

    public static int minimumCostTabulation(int n , int heights[] , int k){
        int dp[] = new int[n];
        // Write the base case
        dp[0] = 0 ;
        for(int i = 1 ; i < n ; i++){
            // We have k coices to consider
            // Copy the recurrence
            int mini = Integer.MAX_VALUE;
            for(int j = 1 ; j <= k ; j++){
                if(i-j < 0) break;
                int currEnergy = Math.abs(heights[i] - heights[i-j]) + dp[i-j];
                mini = Math.min(mini,currEnergy);
            }

            dp[i] = mini;
        }

        return dp[n-1];
    }
    
}
