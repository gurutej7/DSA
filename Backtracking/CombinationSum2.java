import java.util.*;

// 39. Combination Sum - 2
/*
 Given a collection of candidate numbers (candidates) and a target number (target),find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[ [1,1,6],[1,2,5],[1,7],[2,6] ]
 */

public class CombinationSum2 {
    public static void main(String[] args) {
        int [] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(candidates,target));

        System.out.println(combinationSum2Brute(candidates, target));
    }

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        //To make the problem a bit easier , we will sort the given Array
        Arrays.sort(candidates);
        solve(0,candidates,target,ansList,new ArrayList<>());
        return ansList;
        
    }
    //Function to solve the given problem with custom arguments
    private static void solve(int index,int[] arr,int target,List<List<Integer>> ansList, List<Integer> ds){
        //Base Condition when we found a combination i.e, their sum = target
        if(target==0){
            ansList.add(new ArrayList<>(ds));
            return;
        }
        //Checkout notes for the Better understanding of the below loop
        for(int i = index ; i<arr.length ; i++){
            /*check 'i' greater than index because we should avoid duplicates at the same 
              index but we can have duplicate element in the different index . In Example 
            output see 1st combination 1 will be missed if we dont check i>index.
            */
            //if it is a duplicate element skip it
            if(i>index && arr[i]==arr[i-1]) continue;
            //we cannot obtain sum ,so just return , because Array is sorted we cannot find smaller numbers further
            if(arr[i] > target) return;
            //we pick the element
            ds.add(arr[i]);
            //Check recursion tree once to understand better
            solve(i+1,arr,target-arr[i],ansList,ds);
            //Restore the changes while returning back
            ds.remove(ds.size()-1);
        }
    }



    //Brute Force approach which takes more time and space
    private static List<List<Integer>> combinationSum2Brute(int[] candidates, int target) {
        List<List<Integer>> ansList = new ArrayList<List<Integer>>();
        HashSet<List<Integer>> set = new HashSet<>();
        solveBrute(0,candidates,target,ansList,new ArrayList<>(),set);
        return ansList;
    }
    private static void solveBrute (int index, int[] arr, int target ,List<List<Integer>> ansList, ArrayList<Integer> ds,HashSet<List<Integer>> set){
        if(index == arr.length){
            if(target == 0){
                ArrayList<Integer> list = new ArrayList<>(ds);
                Collections.sort(list);
                if(set.add(list)) ansList.add(list);
             }
            return;
        }
        if(arr[index] <= target){
            ds.add(arr[index]);
            solveBrute(index+1,arr,target-arr[index],ansList,ds,set);
            ds.remove(ds.size()-1);
        }
        solveBrute(index+1,arr,target,ansList,ds,set);
    }

}
