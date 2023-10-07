import java.util.List;
import java.util.ArrayList;

public class CombinationSum1 {
    public static void main(String[] args) {
        int [] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
        
    }
    // 39 . Combination Sum
    /*
    Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

    The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different. 
     */

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        solve(0,candidates,target,ansList,new ArrayList<>());
        return ansList;
    }
    //Function to solve the problem with custom arguments
    public static void solve (int index, int[] arr, int target ,List<List<Integer>> ansList, ArrayList<Integer> ds){
        //Base condition when the index is equal to the length we cannot have options further
        if(index == arr.length){
            //required condition to be a part of the answer
            if(target == 0){
                ansList.add(new ArrayList<>(ds));
            }
            return;
        }
        /*if the current Element is greater then the target then the sum will become 
        greater than target
        */
        if(arr[index] <= target){
            //if it is eligible
            // we pick it
            ds.add(arr[index]);
            /*since the repitition is allowed we wont increase the index and make a call
            with reduced target
            */
            solve(index,arr,target-arr[index],ansList,ds);
            //While returning restore the changes that where made by that function call
            ds.remove(ds.size()-1);
        }
        //we donot pick the current element 
        solve(index+1,arr,target,ansList,ds);
    }

}
