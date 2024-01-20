package DP.TwoD;

/* Problem statement
Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities.
 (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. As Ninja has
  to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find out the
   maximum merit points Ninja can earn?
You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is
 to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.

link { https://www.codingninjas.com/studio/problems/ninja%E2%80%99s-training_3621003?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM }
 */

public class NinjaTraining{
    public static void main(String[] args) {
        int points[][] = {{1,2,5},{3,1,3},{3,3,3}};
        int n = points.length;
        int dp[] [] = new int[n][4];
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < 4; j++) {
				dp[i][j] = -1;
			}
		}

        System.out.println(   ninjaTrainingMemo(n-1, 3, points, dp)  );

        System.out.println( ninjaTrainingTabu(n, points) );

    }

    // Memoization
    public static int ninjaTrainingMemo(int day,int lastTask,int points[][],int[][] dp){
        
        if(dp[day][lastTask] != -1) return dp[day][lastTask]; // Avoid recomputing
        // Base case when we have reached the last day
        // As it is the last day we will pick maxi points , of remaining task (leaving the last task)
        if(day == 0){
            int maxi = (int)-1e9;
            for(int i = 0 ; i<=2 ; i++){
                if(i!=lastTask){
                    maxi = Math.max(maxi,points[day][i]);
                }
            }
            return maxi;
        }
        if(day < 0) return 0;

        // We can choose any task on this day , which is not a previous task
        // After choosing we will call the same function with changed day and last task values
        int maxi = (int)-1e9;
        for(int task = 0 ; task <= 2 ; task++){
            if(task != lastTask){ // If current task is not done previously we can do it
                int currentPoints = points[day][task] + ninjaTrainingMemo(day-1, task, points, dp);
                maxi = Math.max(maxi,currentPoints); // Out of all the tasks we have done from a particular day , we will pick the task which gives maximum among them
            }
        }
        // Store the ans for current sub Problem
        dp[day][lastTask] = maxi;
        return dp[day][lastTask];
       
    }

    // Tablation
    public static int ninjaTrainingTabu(int n , int points[][]){
        // Write down the base cases
        int dp[][] = new int[n][4];
        // 0th day
        dp[0][0] = Math.max(points[0][1],points[0][2]); 
        dp[0][1] = Math.max(points[0][0],points[0][2]);
        dp[0][2] = Math.max(points[0][0],points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day and each activity
        for (int day = 1; day < n; day++) {
            for (int lastTask = 0; lastTask < 4; lastTask++) {
                int maxi = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != lastTask) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        int currentPoints = points[day][task] + dp[day - 1][task];
                        // Update the maximum points for the current day and last activity
                        maxi = Math.max(maxi, currentPoints);
                    }
                }
                dp[day][lastTask] = maxi;
            }
        }

        // Return the maximum points achievable after all days (last activity is 3)
        return dp[n - 1][3];
    }

    // Refer space optimized solution here
    // { https://takeuforward.org/data-structure/dynamic-programming-ninjas-training-dp-7 }


    // The below methods are other ways to solve the problem , code is taken from coding Ninjas solution
    public static int ninjaTrainingTabulation(int n, int points[][]) {
		// DP table to memoize the solution.
		int dp[][] = new int[n + 1][4];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < 4; j++) {
				dp[i][j] = -1;
			}
		}

		for (int i = 0; i < n; i++) {

			if (i == 0) {
				dp[i][1] = points[i][0];
				dp[i][2] = points[i][1];
				dp[i][3] = points[i][2];
				continue;
			}

			dp[i][1] = Math.max(dp[i - 1][2], dp[i - 1][3]) + points[i][0];
			dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][3]) + points[i][1];
			dp[i][3] = Math.max(dp[i - 1][1], dp[i - 1][2]) + points[i][2];
		}

		int ans = 0;
		ans = Math.max(ans, dp[n - 1][1]);
		ans = Math.max(ans, dp[n - 1][2]);
		ans = Math.max(ans, dp[n - 1][3]);
		return ans;
	}

    private static int rec(int n, int i, int[][] points,int dp[][]) {
		if (n == 0) {
			// No more days left.
			return 0;
		}

		if (dp[n][i] != -1) {
			return dp[n][i];
		}

		// Merit points of ith task on nth day.
		int ans = points[n - 1][i - 1];
		int mx = 0;
		if (i == 1) {
			mx = Math.max(rec(n - 1, 2, points,dp), rec(n - 1, 3, points,dp));
		}

		if (i == 2) {
			mx = Math.max(rec(n - 1, 1, points,dp), rec(n - 1, 3, points,dp));
		}

		if (i == 3) {
			mx = Math.max(rec(n - 1, 1, points,dp), rec(n - 1, 2, points,dp));
		}

		dp[n][i] = ans + mx;
		return ans + mx;
	}

	public static int ninjaTraining(int n, int points[][]) {
		// DP table to memoize the solution.
        
		int [][] dp = new int[n + 1][4];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < 4; j++) {
				dp[i][j] = -1;
			}
		}
		int ans = 0;
		ans = Math.max(ans, rec(n, 1, points,dp));
		ans = Math.max(ans, rec(n, 2, points,dp));
		ans = Math.max(ans, rec(n, 3, points,dp));

		return ans;
	}
}