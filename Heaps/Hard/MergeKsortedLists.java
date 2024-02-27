package Heaps.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import Graphs.BFSorDFS.Pair;

/*  23. Merge K sorted Lists
{ https://leetcode.com/problems/merge-k-sorted-lists/description/ }

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:  Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
 */

public class MergeKsortedLists {
    // Approach -
    // we will use a min heap to keep track of the current minimum element
    // first put the lists in to the minHeap
    // the top element in the minHeap has the min value among the 3 lists
    // every time you take a element out then add it`s next to the heap
    // simple and straight forward approach
    // take minimum of all and add it to the final list
    private static ListNode mergeKLists(ListNode [] lists){

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b)->(a.val - b.val));

        for(ListNode list : lists){ 
            if (list != null) {
                minHeap.offer(list);
            }
        }
           
        ListNode dummy = new ListNode(0); // dummy.next will be pointing to our ans list head
        ListNode curr = dummy; // we will be moving the curr

        while(!minHeap.isEmpty()){
            ListNode currSmallest = minHeap.poll();
            curr.next = currSmallest;
            curr = curr.next;

            // add the next element of the popped node , if it exists
            if(currSmallest.next != null) 
                minHeap.offer(currSmallest.next);
        }

        return dummy.next;

    }

    private static ArrayList<Integer> mergeKArrays(List<List<Integer>> arr , int k){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> a.getFirst() - b.getFirst());

		// Pair has 3 things , pair.first = value , pair.second = list index , pair.third = value index in the correspomding list


		for(int i = 0 ; i < k ; i++){
			pq.offer(new Pair(arr.get(i).get(0) , i , 0));
		}

		ArrayList<Integer> res = new ArrayList<>();

		while(!pq.isEmpty()){
			Pair curr = pq.poll();
			int value = curr.getFirst();
			int listIndex = curr.getSecond();
			int valueIndex = curr.getThird();

			res.add(value);

			if(valueIndex + 1 < arr.get(listIndex).size())
				pq.offer(new Pair(arr.get(listIndex).get(valueIndex+1),  listIndex ,  valueIndex+1 ));
		}

		return res;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.insert(4);
        head1.insert(5);
        ListNode head2 = new ListNode(1);
        head2.insert(3);
        head2.insert(4);
        ListNode head3 = new ListNode(2);
        head3.insert(6);


        ListNode sortedList = mergeKLists(new ListNode[] {head1,head2,head3});

        for( ; sortedList != null ; sortedList = sortedList.next) System.out.print(sortedList.val + " -> ");

        System.out.println(" null");

        List<List<Integer>> arr = new ArrayList<>();

        arr.add(Arrays.asList(1,4,5));
        arr.add(Arrays.asList(1,3,4));
        arr.add(Arrays.asList(2,6));

        System.out.println(  mergeKArrays(arr, 3) );

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void insert(int val){
        ListNode newNode = new ListNode(val);

        ListNode curr = this;

        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = newNode;
    }
}
