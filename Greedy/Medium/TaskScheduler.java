package Greedy.Medium;

/* 621. Task Scheduler
{ https://leetcode.com/problems/task-scheduler/description/ } 

You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or 
interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.

​Return the minimum number of intervals required to complete all tasks.

Example 1:      Input: tasks = ["A","A","A","B","B","B"], n = 2
                Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd 
interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
 
 */

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2 ;
        System.out.println(leastInterval(tasks, n));
    }

    private static int leastInterval(char[] tasks, int n) {
        int freq[] = new int[26];
        int maxFreq = 0 , maxFreqCount = 0 ;
        for(char ch : tasks) {
            freq[ch-'A']++;

            if(freq[ch-'A'] == maxFreq) maxFreqCount++;
            else if(freq[ch-'A'] > maxFreq){
                maxFreq = freq[ch-'A'];
                maxFreqCount = 1 ;
            }
        }


        int partCount = maxFreq-1; // we have leave this many parts in between the character with the maxFreq
        // i.e, the char with maxFreq is arranged alternatively , ch ____part____ ch ___part___ ch ...
        // Actually the ___part___ is the empty slots available between similar task.

        // if there is only one char with maxFreq then part length = n (given mandatory interval between same tasks)
        // but if there is more tasks with maxFreq , the we can keep that characters in the part there by reducing the number of empty slots in the ___part___
        // For Detailed Explanation refer : 
        // { https://leetcode.com/problems/task-scheduler/solutions/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation/comments/967864/ }

        int partLength = n-(maxFreqCount-1);
        int emptySlots = partCount*partLength;
        int placedTasks = maxFreq * maxFreqCount;
        int remainingTasks = tasks.length - placedTasks;
        int idles = Math.max(0,emptySlots - remainingTasks);

        return tasks.length + idles;
        
    }

    // for heap approach check heaps folder
    
}
