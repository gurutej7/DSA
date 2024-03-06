package Greedy.Medium;

import java.util.ArrayList;
import java.util.Arrays;


public class MergeIntervals {

    /* 56. Merge Intervals
     * https://leetcode.com/problems/merge-intervals/description/
     * 
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
     *  and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * Example 1:       Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     *                   Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     * 
     */


    private static int[][] merge(int[][] arr) {
        int n = arr.length;
        if(n == 1) return arr;
        Arrays.sort(arr , (a,b)-> a[0]-b[0]);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(arr[0][0],arr[0][1])));
        
        /* check If current interval overlaps with the previous one or not
         * if overlaps , then merge accorndingly
         * else append the current interval
         */
        for(int i = 1 ; i < n ; i++){
            int prevUpperBound = adj.get(adj.size()-1).get(1);
            int currL = arr[i][0] , currR = arr[i][1];
            if(currL <= prevUpperBound &&  currR >= prevUpperBound){
                adj.get(adj.size()-1).set(1,currR);
            }
            else if(currL > prevUpperBound ){
                adj.add(new ArrayList<>(Arrays.asList(arr[i][0],arr[i][1])));
            }
        }
        int resSize = adj.size();
        int res[][] = new int[resSize][2];

        for(int i = 0 ; i < resSize ; i++){
            res[i][0] = adj.get(i).get(0);
            res[i][1] = adj.get(i).get(1);
        }
        return res;
    }

    /* 57. Insert Interval
     * https://leetcode.com/problems/insert-interval/description/
     * 
     * Example 2:
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     * 
     */

    private static int[][] insert(int[][] arr, int[] newInterval) {
        ArrayList<int[]> temp = new ArrayList<>();
        int i = 0 , n = arr.length;
        // add All intervals ending before new Interval start
        while(i < n && arr[i][1] < newInterval[0]){
            temp.add(new int[]{arr[i][0],arr[i][1]});
            i++;
        }
        // merge all intervals including the new interval
        while(i < n && arr[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0] , arr[i][0]); // set newLowerBound
            newInterval[1] = Math.max(newInterval[1] , arr[i][1]); // set newUpperBound
            i++;
        }
        temp.add(new int[]{newInterval[0],newInterval[1]});

        // add the remaining
        while(i < n ) {
            temp.add(new int[]{arr[i][0],arr[i][1]});
            i++;
        }

        int resSize = temp.size();
        int res[][] = new int[resSize][2];
        for(i = 0 ; i < resSize ; i++) res[i] = temp.get(i);

        return res;
    }

    /* 435. Non Overlapping Intervals
     * https://leetcode.com/problems/non-overlapping-intervals/description/
     * 
     * Given an array of intervals intervals where intervals[i] = [starti, endi], 
     * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     * Example 1:       Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
     * 
     */

    private static int eraseOverlapIntervals(int[][] arr) {
        Arrays.sort(arr , (a,b) -> a[1]-b[1]);
        int n = arr.length;
        int  end = arr[0][1] , cnt = 1 ;

        for(int i = 1 ; i < n ; i++){
            if(arr[i][0] >= end){
                end = arr[i][1];
                cnt++;
            }
        }
        return n - cnt;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2},{2,3},{3,4},{1,3}};
        int newInterval[] = {1,4};

        merge(arr);
        System.out.println(eraseOverlapIntervals(arr));
        insert(arr, newInterval);

    }
    
}