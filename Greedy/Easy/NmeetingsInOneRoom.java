package Greedy.Easy;

import java.util.Arrays;

/* 
{ https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1 }
There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] is 
start time of meeting i and end[i] is finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be 
held in the meeting room at a particular time?

Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting. 

Input:  start[] = {1,3,0,5,8,5}         end[] =  {2,4,6,7,9,9}
Output:     4
Explanation:
Maximum four meetings can be held with
given start and end timings.
The meetings are - (1, 2),(3, 4), (5,7) and (8,9)
 */

public class NmeetingsInOneRoom {
    public static void main(String[] args) {

        int start[] = {1,3,0,5,8,5};
        int end[] = {2,4,6,7,9,9};

        System.out.println( maxMeetings(start, end, start.length) );
        
    }

    // Obviously if we have meetings based on their ending time then we can maximize the number of meetings
    // We have to sort the given data based on the duration
    private static int maxMeetings(int start[], int end[], int n){
        // add your code here
        Meetings[] meet = new Meetings[n];
        for(int i = 0;i<n;i++){
            meet[i] = new Meetings(start[i],end[i]);
        }
        // custom sort which sorts based on the ending time
        Arrays.sort(meet,(a,b)->a.end-b.end);
        int cnt = 1;
        int endTime = meet[0].end;
        for(int i = 0;i<n;i++){
            // If we have a valid start i.e, after our previous end => then update required parameters
            if(meet[i].start > endTime){
                cnt++;
                endTime = meet[i].end;
            }
        }
        return cnt;
    }
    
}
class Meetings{
    int start;
    int end;
    public Meetings(int start, int end){
        this.start = start;
        this.end = end;
    }
}

