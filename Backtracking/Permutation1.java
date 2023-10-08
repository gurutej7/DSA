import java.util.*;

/* 46. Permutations

   Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
   Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */

public class Permutation1 {
    public static void main(String[] args) {
        
        int [] nums ={1,2,3};
        System.out.println(permute1(nums));
        System.out.println(permute(nums));
    }

    //Approach -1 using a map Array
    //Check notes for better understanding
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<Integer>();
        boolean map [] = new boolean[nums.length];
        recursion1(nums,ds,ans,map);
        return ans;
    }
    public static void recursion1(int[] nums,List<Integer> ds ,List<List<Integer>> ans , boolean [] map ){
        //Base condition when we have picked all the elements then the ds size will be equal to nums.length
        if(ds.size() == nums.length){
            ans.add(new ArrayList<Integer>(ds));
            return;
        }
        for(int i = 0 ;i<nums.length ; i++){
            // If the number is not picked for the current permutation
            // Below Line can be used instead of map Array (Time will be increased)
            // if(ds.contains(nums[i])) continue; 
            if(!map[i]){
                //Marke it as picked
                map[i] = true;
                //add it to the current permutation
                ds.add(nums[i]);
                recursion1(nums,ds,ans,map);
                //Restore the changes while coming back
                ds.remove(ds.size()-1);
                map[i] = false;
            }
        }
    }
    //Approach - 2 using swaping of the elements
    // Extra space used for map Array is reduced
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        solve(0,nums,ans);
        return ans;
    }
     public static void solve(int index,int[] nums,List<List<Integer>> ansList){
        if(index==nums.length){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i= 0 ;i<nums.length;i++){
                temp.add(nums[i]);
            }
            ansList.add(temp);
            return;
        }
        for(int i = index ; i<nums.length ; i++){
            swap(index,i,nums);
            solve(index+1,nums,ansList);
            swap(index,i,nums);
        }
    }
    public static void swap(int a , int b , int[] nums){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
        return;
    }
    
}
