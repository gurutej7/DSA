import java.util.*;
public class Subsets2 {
    public static void main(String[] args) {
        
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
