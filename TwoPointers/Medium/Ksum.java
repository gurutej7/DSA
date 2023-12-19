package TwoPointers.Medium;
import java.util.*;

/*
Every K sum problem is at the end is solved as 2-Sum problem 
So a generalized template to solve k-SUm problems
* Reduce K sum problem to K – 1 sum Problem till K=2
* We can use backtracking for this          TC = O(N^(K-1))

Example 1:
Input: nums = [-1,0,1,2,-1,-4]              Output: [[-1,-1,2],[-1,0,1]]

*/

public class Ksum {
    public static void main(String[] args) {
        int [] nums = {-1,0,1,2,-1,-4};
        int target = 0;

        System.out.println(kSum(nums,target));
    }
    
    public static  List<List<Integer>> kSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null){
            return result;
        }
        Arrays.sort(nums);
        helper(result, nums, target, 3, 0, new ArrayList<>());
        return result;
    }
    private static void helper( List<List<Integer>> result , int[] nums, int target, int K, int idx, List<Integer> subR){
        if(idx > nums.length-K+1) return;//With the current index we cannot form K elements
        if(K==2){
            //General Two Pointer Approach used in 2-Sum
                int l = idx;
                int r = nums.length-1;
                while(l<r){
                    int sum = nums[l]+nums[r];
                    if(sum == target){
                        subR.add(nums[l]);
                        subR.add(nums[r]);
                        //We have found the answer add it to the final result
                        result.add(new ArrayList<>(subR));
                        //Revert the changes as we are using the same list
                        subR.remove(subR.size()-1);
                        subR.remove(subR.size()-1);
                        l++;
                        r--;
                        while(l<r && nums[l]==nums[l-1]){ //skip duplicates
                            l++;
                        }
                        while(l<r && nums[r]==nums[r+1]){
                            r--;
                        }
                    } else if(sum>target){
                        r--;
                    } else {
                        l++;
                    }
                }
            
        } else {
            //If it is not a Two sum
            for(int i=idx; i<nums.length-K+1; i++){
                if(i != idx && nums[i]==nums[i-1]){
                    //Skip same results / If the value of a[i] is same as prev then we get same results but they asked for unique results
                    continue;
                }
                //Add the current element to current list
                subR.add(nums[i]);
                //decrease target by current element and increase the starting index and reduce k
                helper(result, nums, target-nums[i], K-1, i+1, subR);
                //Revert the changes that were made in the above function call
                subR.remove(subR.size()-1);
            }
        }  
    }
}
