import java.util.*;

/* 90. Subsets - 2 

Given an integer array nums that may contain duplicates, return all possible subsets(the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 
 */
public class Subsets2 {
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        System.out.println(subsetsWithDup(nums));
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        //To have all the duplicates side by side
        Arrays.sort(nums);
        solve(nums,0,ds,ans);
        return ans;

    }
    public static void solve(int[] nums,int index , List<Integer> ds, List<List<Integer>> ans){
        //Every time the function is called we will have a subset
        ans.add(new ArrayList<>(ds));
        for(int i = index ; i<nums.length ; i++){
            //To check if the current element is already selected or not
            if(i>index && nums[i]==nums[i-1])continue;
            //If it is for the first time then we can add it to our subset
            ds.add(nums[i]);
            solve(nums,i+1,ds,ans);
            ds.remove(ds.size()-1);
        }
    }

    // Brute Force approach same as Subsets 1 using a Set dataStructure
    //Which results in increased Time and Space Complexity
    public static List<List<Integer>> subsetsWithDupBruteForce(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        HashSet<List<Integer>> set= new HashSet<>();
        Arrays.sort(nums);
        solveBruteForce(nums,0,ds,ans,set);
       
        return ans;
    }
    public static void solveBruteForce(int [] arr , int index , List<Integer> ds,List<List<Integer>> ans,HashSet<List<Integer>> set){
        if(index == arr.length){
            ArrayList<Integer> list = new ArrayList<>(ds);
            if(set.add(list)){
            ans.add(new ArrayList<>(ds));
            }
            return;
        }
            ds.add(arr[index]);
            solveBruteForce(arr,index+1,ds,ans,set);
            ds.remove(ds.size()-1);
            solveBruteForce(arr,index+1,ds,ans,set);
            
    }
}
