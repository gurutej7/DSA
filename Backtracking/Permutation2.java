import java.util.*;
/* 47. Permutation - 2

 Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 Example 1:
    Input: nums = [1,1,2]
    Output: [[1,1,2],[1,2,1],[2,1,1]]
    */



public class Permutation2 {
    public static void main(String[] args) {
        int [] nums = {1,1,2};

        System.out.println(permuteWithDup(nums));

        System.out.println(permuteUnique(nums));

    }
    //Appproach using a Boolean used Array to check
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        //Sorting the given Array So that we can Have Duplicates together
        Arrays.sort(nums);
        //Boolean Array will mark the Element if it is picked or not
        boolean taken [] = new boolean[nums.length]; 
        solve(nums,new ArrayList<>(),ansList,taken);
        return ansList;        
    }

    public static void solve(int[] nums,List<Integer> ds,List<List<Integer>> ansList,boolean[] taken){
        //Base Case
        if(ds.size()==nums.length){
            ansList.add(new ArrayList<>(ds));
            return;
        }
        for(int i = 0 ; i<nums.length ; i++){
            //If already taken just skip it
            if(taken[i]) continue;
            //If the element is equal to the previous element 
            if(i>0 && nums[i]==nums[i-1] && !taken[i-1])continue;
            taken[i] = true;
            ds.add(nums[i]);
            solve(nums,ds,ansList,taken);
            taken[i]=false;
            ds.remove(ds.size()-1);

        }
    }



    //Approach using Set data Structure
    //Same as permutations -1 but Whenever we find one Permutation We will put it into the set to avoid Duplicate permutations
    public static List<List<Integer>> permuteWithDup(int [] nums){
        List<List<Integer>> ansList = new ArrayList<>();
        //To have Duplicates side by side
        Arrays.sort(nums);
        HashSet<List<Integer>> set = new HashSet<>();
        solve(0,nums,ansList,set);
        return ansList;
    }
    public static void solve(int index,int[] nums,List<List<Integer>> ansList,HashSet<List<Integer>> set){
        if(index==nums.length){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i= 0 ;i<nums.length;i++){
                temp.add(nums[i]);
            }
            if(set.add(temp))
            ansList.add(temp);
            return;
        }
        for(int i = index ; i<nums.length ; i++){
            swap(index,i,nums);
            solve(index+1,nums,ansList,set);
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
