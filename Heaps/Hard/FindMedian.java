package Heaps.Hard;

import java.util.PriorityQueue;

/* 295. Find Median from Data Stream
{ https://leetcode.com/problems/find-median-from-data-stream/description/ }
 
The median is the middle value in an ordered integer list. If the size of the list is even, there is 
no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Example 1:  Input  :["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
                    [[], [1], [2], [], [3], []]
Output             :[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 */

public class FindMedian {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian()); 
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
    
}

class MedianFinder {
    private PriorityQueue<Integer> left ; // has the left half of the array in the sorted order
    private PriorityQueue<Integer> right ; // has right half of the array in the sorted order
    private boolean even ; // to keep track of the current length of the stream/array
    public MedianFinder() {
        this.left = new PriorityQueue<>((a,b)-> (b-a));
        this.right = new PriorityQueue<>();
        this.even = true;

    }

    // we have two cases when it comes to adding a new num
    // case 1: left size == right size // the stream is of even length 
    // after adding a new one the length becomes odd , we tend to add it to the left half of the stream
    // but before adding it directly to the left , before add in right , the right half may change the order after that pop the min from there and put it into the left half
    // it is just a thing to have both the halves sorted
    // same when we have left.size > right.size // the stream has odd length , to make it even we tend to add it to the right half
    // but directly adding it to the right may effect the sorted order of the both halves
    // so first put it into the left , get max and put it into the right
    // To have this functionality use , maxHeap for left half and minHeap for the right half
    // To avoid every time comparing the size , we can use a boolean variable to keep track of length of the stream , and keep on updating it every time a new value is added

    public void addNum(int num) {
        // 3*O(logN) for add operation
        if(even){
            right.offer(num);
            int minOnRight = right.poll();
            left.offer(minOnRight);
        }
        else{
            left.offer(num);
            int maxOnLeft = left.poll();
            right.offer(maxOnLeft);
        }
        even = !even; // updating length
    }
    
    public double findMedian() {
        // O(1)
        if(even){
            return (double)(left.peek()+right.peek())/(double)2;
        }
        else return (double)(left.peek());
    }
}

/* Other resources  taken from discussion section

I was asked this question in an interview today. I told about the minHeap and maxHeap approach. 
The interviewer asked what if the data is very very large. How would you handle it? Any thoughts?!
https://leetcode.com/problems/find-median-from-data-stream/discuss/228442/Java-Solution-with-Tree-O(Log-N)-Insertion-and-Lookup-+-Explanation : Java Solution with Tree - O(Log N) Insertion and Lookup + Explanation

https://leetcode.com/problems/find-median-from-data-stream/discuss/652498/Good-for-interviews:-Python-general-sort-greater-insertion-sort-greater-two-heaps-greater-follow-ups : Good for interviews: Python general sort -> insertion sort -> two heaps -> follow-ups 

 */