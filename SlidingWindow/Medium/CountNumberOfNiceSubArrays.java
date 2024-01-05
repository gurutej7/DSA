package SlidingWindow.Medium;

import java.util.HashMap;

/* 1248. Count Number of Nice SUb Arrays

link { https://leetcode.com/problems/count-number-of-nice-subarrays/description/ }
Given an array of integers nums and an integer k. A continuous subarray is called nice if there 
are k odd numbers on it.
Return the number of nice sub-arrays.

Example 1:
Input: nums = [1,1,2,1,1], k = 3                Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:
Input: nums = [2,4,6], k = 1                    Output: 0
Explanation: There is no odd numbers in the array.

Example 3:
Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2      Output: 16

 */

public class CountNumberOfNiceSubArrays {
    public static void main(String[] args) {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        int k = 2;

        System.out.println(numberOfSubarrays2(nums, k));
    }
 

    //Approach - 1          TC - O(N)     , SC - O(N)
    // By using Observation Haki, we can make this problem down to SUb Array Sum equals K
    // Just replace odd numbers with 1 and even numbers with 0
    // Now the number of subarrays with K odd numbers is same as Number of subArrays with K sum
    // Because we have codsidered odd numbers as 1 , so, the number of ones in a subarray is equal to the sum of the subarray
     public static int numberOfSubarrays1(int[] nums, int k) {
        int n = nums.length;
        int prefix[] = new int[n];
        int sum = 0 , count = 0 ; 
        for(int i = 0 ; i < n ; i++){
            if(nums[i]%2 == 0) nums[i]=0; // Replace even with 0
            else nums[i] = 1; // Replace Odd with 1
            sum += nums[i];
            prefix[i] = sum;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i<n ; i++){
            if(prefix[i] == k)count++;
            if(map.containsKey(prefix[i] - k) ) count += map.get(prefix[i]-k) ;

            map.put(prefix[i],map.getOrDefault(prefix[i],0)+1);
        }
        return count;
    }


        //Approach -2
// SubArrays with K number of odd Numbers  = (SubArrays with Atmost K odd Numbers) - (SubArrays with Atmost K-1 odd Numbers)
// The above logic is really useful in many problems

        public static int numberOfSubarrays2(int[] nums,int k){
            return Atmost(nums,k) - Atmost(nums,k-1);
        }
        // Exactly K times = at most K times - at most K - 1 times

        private static int Atmost(int[] nums, int k) {
            int left = 0 , right = 0 , oddCount = 0 , res = 0;

            while(right < nums.length){

                if(nums[right] %2 !=0) oddCount++;

                while(left<= right &&  oddCount > k){
                    if(nums[left]%2 !=0){
                        oddCount--;
                    }
                    left++;
                }

                res += right-left+1;
                right++;
            }
            return res;
        }

        // res += right-left+1
/*
how I interpret j-i+1 idea

step 1. find all subarrays that has most K odd number   (1,2,3 ...K)
step 2. find all subarrays that has most K-1 odd number  (1,2,3,...K-1)
step 3. find step 1 minus step 2 will result in subarrays only has K odd numbers


example
arr=[1,2,1,2,3]
K=2

right = j , left = i
when K=2
j           j-i+1          contiguous subarray
0            1               [1]
1            2               [1,2], [2] 
2            3               [1,2,1], [2,1], [1]
3            4               [1,2,1,2], [2,1,2], [1,2], [2]
4            4               [2,1,2,3], [1,2,3], [2,3], [3]
total                         14 subarrays

when K=1
j           j-i+1          contiguous subarray
0            1               [1]
1            2               [1,2], [2] 
2            3               [2,1], [1]
3            4               [2,1,2], [1,2], [2]
4            4               [2,3], [3]
total                        10 subarrays

when K=2 minus when K=1 is 4 subarrays
[1,2,1], [1,2,1,2], [2,1,2,3], [1,2,3]

*/
}
