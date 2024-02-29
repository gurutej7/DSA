package Heaps.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
        System.out.println(leastInterval2(tasks, n));
    }

    private static int leastInterval2(char[] tasks, int n) {
        HashMap<Character,Integer> map = new HashMap<>();

        for(char ch : tasks) map.put(ch, map.getOrDefault(ch,0)+1);

        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
        // sorting based on frequency
        // the case where there are two tasks with the same frequency.
        // If you leave this out, then for cases like A 3 B 2 D 3, n = 2, you are calculating it as A D B D A B .....
        // So, although for this question the result is correct, the logic of this code hasn't covered all the cases.
        pq.addAll(map.entrySet());

        int time = 0 ;

        while(!pq.isEmpty()){
            int k = n + 1 ;
            // n is for the empty slots and the 1 for the current task itself
            List<Map.Entry<Character,Integer>> tempList = new ArrayList<>();
            // to store the value after using it(means i remove it till i place another n characters )
            while(k > 0 && !pq.isEmpty()){
                Map.Entry<Character,Integer> top = pq.poll();// most frequency task
                top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
                tempList.add(top); // collect task to add back to queue
                k--;
                time++; //successfully executed task
            }

            // again put the tasks into the q
            for(Map.Entry<Character,Integer> curr : tempList){
                if(curr.getValue() > 0 ) pq.offer(curr);
            }

            // if there is no task left
            if(pq.isEmpty()) break;
            time += k ; // for the current interval the second while got broke because of less tasks , but we have to compulsoryly wait k intervals , since we have performed a task and to do the same again
        }
        return time;
    }

    // using pair class 
    private static int leastInterval(char[] tasks, int n) {
        HashMap<Character,Integer> map = new HashMap<>();

        for(char ch : tasks) map.put(ch, map.getOrDefault(ch,0)+1);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.getFreq() != b.getFreq() ? b.getFreq() - a.getFreq() : a.getChar() - b.getChar());
        // sorting based on frequency
        // the case where there are two tasks with the same frequency.
        // If you leave this out, then for cases like A 3 B 2 D 3, n = 2, you are calculating it as A D B D A B .....
        // So, although for this question the result is correct, the logic of this code hasn't covered all the cases.
        for(char ch : map.keySet()) pq.offer(new Pair(ch, map.get(ch)));

        int time = 0 ;

        while(!pq.isEmpty()){
            int k = n + 1 ;
            // n is for the empty slots and the 1 for the current task itself
            List<Pair> tempList = new ArrayList<>();
            // to store the value after using it(means i remove it till i place another n characters )
            while(k > 0 && !pq.isEmpty()){
                Pair top = pq.poll();// most frequency task
                top.setFreq(top.getFreq()-1); // decrease frequency, meaning it got executed
                tempList.add(top); // collect task to add back to queue
                k--;
                time++; //successfully executed task
            }

            // again put the tasks into the q
            for(Pair curr : tempList){
                if(curr.getFreq() > 0 ) pq.offer(curr);
            }

            // if there is no task left
            if(pq.isEmpty()) break;
            time += k ; // for the current interval the second while got broke because of less tasks , but we have to compulsoryly wait k intervals , since we have performed a task and to do the same again
        }
        return time;
    }

    private static class Pair{
        char ch ;
        int f ;
        public Pair(char ch , int f){
            this.ch = ch ;
            this.f = f ;
        }
    
        public char getChar(){return this.ch;}
        public int getFreq(){return this.f;}
        public void setFreq(int val) {this.f = val;}
    }
    
    // for greedy approach check in the Greedy folder
}
