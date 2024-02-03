package Greedy.Medium;

/* Given a set of N jobs where each jobi has a deadline and profit associated with it.
Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit 
associated with job if and only if the job is completed by its deadline.
Find the number of jobs done and the maximum profit.
Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job. Deadline of the job is the
 time before which job needs to be completed to earn the profit.
 */

import java.util.Arrays;

public class JobSequencingProblem {
    public static void main(String[] args) {
        Job[] arr = {new Job(1, 4, 20), new Job(2, 1, 10) , new Job(3, 1, 40) , new Job(4, 1, 30)}; // expected [2,60]

        int ans[] = JobScheduling(arr, arr.length);

        System.out.println( Arrays.toString(ans) );

    }
    /*The strategy to maximize profit should be to pick up jobs that offer higher profits. Hence we should sort the 
    jobs in descending order of profit. Now say if a job has a deadline of 4 we can perform it anytime between day 
    1-4, but it is preferable to perform the job on its last day. This leaves enough empty slots on the previous 
    days to perform other jobs.
     */
    //Function to find the maximum profit and the number of jobs done.
    private static int[] JobScheduling(Job arr[], int n)
    {
        // Your code here
        // Sorting based on profits
        Arrays.sort(arr, (a, b) -> (b.profit - a.profit));

        int maxi = 0;
        // Finding the maximum deadline we have
        for (int i = 0; i < n; i++) {
           if (arr[i].deadline > maxi) {
              maxi = arr[i].deadline;
           }
        }
  
        int result[] = new int[maxi + 1];
  
        Arrays.fill(result,-1);
  
        int countJobs = 0, jobProfit = 0;
  
        for (int i = 0; i < n; i++) {
            
            // We will traverse from the back  , because it is obvious to do the current task as lately as possible , on or before its deadline
           for (int j = arr[i].deadline; j > 0; j--) {
  
              // Free slot found 
              if (result[j] == -1) {
                 result[j] = i;
                 countJobs++;
                 jobProfit += arr[i].profit;
                 break;
              }
           }
        }
        return new int[]{countJobs,jobProfit};
        
    }
}


class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}
    
