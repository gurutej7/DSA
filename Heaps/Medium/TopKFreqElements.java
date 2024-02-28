package Heaps.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/* 347. Top K Frequent Elements
{ https://leetcode.com/problems/top-k-frequent-elements/description/ } 

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:  Input: nums = [1,1,1,2,2,3], k = 2
            Output: [1,2]
Example 2:  Input: nums = [1], k = 1
            Output: [1]

 */

public class TopKFreqElements {
    public static void main(String[] args) {
        int nums[] = {1,1,1,2,2,3};
        int k = 2 ;

        System.out.println( Arrays.toString(topKFrequent(nums, k)) );
    }
    
    private static int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> (b[1]-a[1]));// prioritize based on frequency
        Map<Integer,Integer> map = new HashMap<>();// to prioritize based on frequency , first we have to know the frequency
        for(int i : nums){
            map.put(i, map.getOrDefault(i,0)+1);
        }

        for(int i : map.keySet()) // if k == n && there are n distinct elements , the TC - O(NlogN)
            pq.offer(new int[]{i,map.get(i)});
            // we can use min heap also , as follows
            // if(pq.size() > k) pq.poll();

        int ans[] = new int[k];
        for(int i = 0 ; i < k ; i++){
            ans[i] = pq.peek()[0];
            pq.poll();
        }
        return ans;
    }
    // without using array inside pq , we can initialize maxHeap based on freq as the following also
    // PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));

    // Another Approach using Bucket Sort O(N)
    // will add it in the separate BucketSort Folder
    // A lot of usefull resources in the discussion section
}
