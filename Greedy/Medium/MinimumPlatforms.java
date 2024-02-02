package Greedy.Medium;

import java.util.Arrays;

/* Minimum Platforms

Given arrival and departure times of all trains that reach a railway station. Find the minimum number of platforms 
required for the railway station so that no train is kept waiting.
Consider that all the trains arrive on the same day and leave on the same day. Arrival and departure time can never
 be the same for a train but we can have arrival time of one train equal to departure time of the other. At any 
 given instance of time, same platform can not be used for both departure of a train and arrival of another train.
  In such cases, we need different platforms.

Input: n = 6 
arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
Output: 3
Explanation:        Minimum 3 platforms are required to safely arrive and depart all trains.
 
 */
public class MinimumPlatforms {
    public static void main(String[] args) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200 , 1120 , 1130 , 1900 , 2000};

        System.out.println( findPlatform(arr, dep, arr.length) );



    }

     //railway station such that no train waits.
   private static int findMinimumPlatforms(int arr[],int dep[]){
        int platform=1 , depIndex=0;

        for(int i=1;i<arr.length;i++){
            // if last departure is less than current arrival then it won`t need extra platform , update last departure
            if(dep[depIndex]<arr[i]){
                depIndex++;
            }
            // If the last departure is greater than current arrival then we need a extra platform
            else if(dep[depIndex]>=arr[i]){
                platform +=1;
            }
        }
        return platform;
    }
    
    private static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        Arrays.sort(arr);
        Arrays.sort(dep);

        return findMinimumPlatforms(arr,dep);
    }

}
