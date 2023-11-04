/*  Agressive Cows
Problem Statement: You are given an array ‘arr’ of size ‘n’ which denotes the position of 
stalls.
You are also given an integer ‘k’ which denotes the number of aggressive cows.
You are given the task of assigning stalls to ‘k’ cows such that the minimum distance 
between any two of them is the maximum possible.
Find the maximum possible minimum distance.
Example 1:
Input Format: N = 6, k = 4, arr[] = {0,3,4,7,10,9}
Result: 3
Explanation: The maximum possible minimum distance between any two cows will be 3 when 4
cows are placed at positions {0, 3, 7, 10}. Here the distances between cows are 3, 4, and
3 respectively. We cannot make the minimum distance greater than 3 in any ways. 
 */

import java.util.Arrays; 

public class AgressiveCows {
    public static void main(String[] args) {
        int [] stalls = {0,3,4,7,10,9};
        int cows = 4;
        System.out.println(aggressiveCows(stalls, cows));
    }
    public static int aggressiveCows(int []stalls, int k) {
        // Sort the Array So that the given cordinates will be in Ascending Order
        Arrays.sort(stalls);
        if(stalls.length==2) return  stalls[stalls.length-1]-stalls[0];
        //Startig point will be Minimum distance of 1 
        int low = 1;
        //Maximum distance can be the MAX - MIN elements from the array
        int high = stalls[stalls.length-1]-stalls[0];
        while(low<=high){
            int mid = low + (high-low)/2 ;
            //If possible at the current coordinate Move right to find larger value and vice versa
            if(possibleToPlaceCows(stalls,mid,k)) low=mid+1;
            else high = mid-1;
        }
        //Dry run and check at the end the high pointer will be at the answer
        return high;

    }
    public static boolean possibleToPlaceCows(int[] stalls,int diff,int cows){
        //Initially the one cow is placed at coordinate One
        int cowsPlaced = 1 , lastPoint = stalls[0];
        for(int i = 1 ; i<stalls.length ; i++){
            //If the distance between the current co-ordinate and the last co-ordinate 
            //satisfies the current distance then Place the cow
            if(stalls[i]-lastPoint >= diff){
                cowsPlaced++;
                lastPoint = stalls[i];
            }
        }
        return cowsPlaced >= cows ;
    }
  
}
