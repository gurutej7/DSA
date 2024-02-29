package Heaps.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/* 846. Hand of Straights
{ https://leetcode.com/problems/hand-of-straights/description/ } 

Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size 
groupSize, and consists of groupSize consecutive cards.
Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return
 true if she can rearrange the cards, or false otherwise.

Example 1:  Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
            Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]

Exact same problem : { https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/ }
 */

public class HandOfStraights {
    public static void main(String[] args) {
        int nums[] = {1,2,3,6,2,3,4,7,8};
        int k = 3 ;

        System.out.println(isNStraightHand(nums, k));
        System.out.println(isNStraightHand2(nums, k));
        isNStraightHandPrint(nums, k);
    }

    private static boolean isNStraightHand(int[] nums, int k) {
        int n = nums.length;
        if(n % k != 0) return false;

        HashMap<Integer,Integer> freq = new HashMap<>();
        for(int i : nums) freq.put(i , freq.getOrDefault(i,0)+1);

        /*
        For every element, we get the frequency ("smallestNumFreq") from the "freq" map. If smallestNumFreq > 0, 
        it means that it's a starting element of a group. So the remaining (k-1) next elements 
        should exist in the map and their freq has to be >= smallestNumFreq.
        */
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // minHeap
        for(int i : freq.keySet()) pq.offer(i); 

        // O(nlogN) , the k loop won`t run for every time 
        while(!pq.isEmpty()){
            int smallestNum = pq.poll();
            if(!freq.containsKey(smallestNum)) continue;
            int smallestNumFreq = freq.get(smallestNum);
            freq.remove(smallestNum); // we have to form the all the sequences of smallestNum here only , so no further use 

            for(int i = 1 ; i < k ; i++){
                int curr = smallestNum + i ; // next Elements that should occur in the current sequence
                if(!freq.containsKey(curr) || freq.get(curr) < smallestNumFreq) 
                    return false; // if the current sequence cannot be formed because of any one of the above reasons
                else{
                    freq.put(curr, freq.get(curr) - smallestNumFreq);
                    if(freq.get(curr) == 0) freq.remove(curr); // if we have used all it`s occurences remove it
                }
            }
        }

        return true;
    }
    // Approach - 2 , using TreeMap
    // O(nlogn) time where n == hand.length, because in the worst case all n elements are distinct so inserting n elements into a TreeMap costs O(nlogn) time, and it costs O(nlogn) time to remove all elements again while creating the groups.
    // O(n) space because in the worst case all n elements are distinct so we have n entries in the TreeMap.
    private static  boolean isNStraightHand2(int[] hand, int W) {
        if (hand == null || hand.length == 0) return true;
        if (hand.length % W != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int curr : hand) map.put(curr, map.getOrDefault(curr, 0) + 1);     // O(nlogn) time.
        while (map.size() > 0) {
            // Try creating the next group of W consecutive cards.
            int start = map.firstKey();                 // O(logn) time.
            for (int i = start; i < start + W; ++i) {
                if (!map.containsKey(i)) return false;  // O(logn) time.
                map.put(i, map.get(i) - 1);             // O(logn) time.
                if (map.get(i) == 0) map.remove(i);     // O(logn) time.
            }
        }
        return true;
    }

    // Follow up print the straights
    private static boolean isNStraightHandPrint(int nums[] , int k){
        int n = nums.length;
        if(n%k != 0) return false;
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i : nums) map.put(i, map.getOrDefault(i,0)+1);
        // sorting because ,  to find consecutive , starting with smaller is the optimal , or i can call it as a common sense
        // we can either start with the smallest or the largest , depending on which the further search will vary
        Arrays.sort(nums);
         // https://stackoverflow.com/questions/22571586/will-arrays-sort-increase-time-complexity-and-space-time-complexity
         // Arrays.sort(int[]) in all Java standard library implementations that I know,
         // is an example of a comparison-based sort and thus must have worst-case complexity Ω(n log n).
         // In particular, Oracle Java 7 uses a dual-pivot quicksort variant for the integer overloads, which actually has an Ω(n2) worst case.
        List<List<Integer>> res = new ArrayList<>();
         for(int i : nums){
             int currNum = i ;
             if(!map.containsKey(currNum)) continue; // it has been previosly used
             
             int limit = 0 ;
             List<Integer> temp = new ArrayList<>();
             while(map.containsKey(currNum) && limit < k){
                 limit++;
                 temp.add(currNum);
                 map.put(currNum ,map.get(currNum)-1);// rreduce freq for used oness
                 if(map.get(currNum) == 0) map.remove(currNum);
                 currNum++;
             }

             if(limit != k) return false; // we was not able to find a valid sequence
             res.add(temp);

         }

         // printing the sequence
         System.out.println(res);
         return true;
    }
    
}
