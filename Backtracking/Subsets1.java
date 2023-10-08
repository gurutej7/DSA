import java.util.List;
import java.util.ArrayList;
/*
 78 . Subsets
 Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */

public class Subsets1{
    public static void main(String[] args) {
        int arr[] = {1,2,3};
        System.out.println(subsets(arr));
        
    }
    
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        //Carry one (ds) list to store the subset of one particular call
        //And in the base case add those ds to the main ansList
        List<Integer> ds = new ArrayList<>();
        solve(nums,0,ds,ans);
        return ans;
    }
    //Function to solve the problem with custom arguments
    public static void solve(int [] arr , int index , List<Integer> ds,List<List<Integer>> ans){
        //Base condition when index reaches length then there is no element to pick
        if(index == arr.length){
            ans.add(new ArrayList<>(ds));
            return;
        }
            //you pick the current element
            ds.add(arr[index]);
            //case : you pick
            solve(arr,index+1,ds,ans);
            //Restore the changes that you made in that particular call while returning
            ds.remove(ds.size()-1);
            //case : you do not pick
            solve(arr,index+1,ds,ans); 
    }
}