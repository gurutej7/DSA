import java.util.List;
import java.util.ArrayList;

/*  131 . Palindrome Partitioning 

    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
    Example 1:
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]
 
 */

public class PalindromePartitioning {
    public static void main(String[] args) {

        System.out.println(partition("aabb"));
        
    }
    public static List<List<String>> partition(String s) {
        List<List<String>> ansList = new ArrayList<>();
        List<String> ds = new ArrayList<>();
        solve(s,0,ds,ansList);
        return ansList;
    }
    //Function to solve the given problem with custom arguments
    public static void solve(String s,int idx,List<String> ds,List<List<String>> ansList){
        //Base condition when idx reaches the end we can add the current list to the final ans
        if(idx==s.length()){
            ansList.add(new ArrayList<>(ds));
            return;
        }
        //We can partition at any index , So iterate through the string
        for(int i = idx ; i<s.length(); i++){
            //check if we partition at the current idx it is a palindrome or not
            //We can only partition only if it is a palindrome
            if(isPalindrome(s,idx,i)){
                //add it to the current ans list and call the function for the remaining string
                ds.add(s.substring(idx,i+1));
                solve(s,i+1,ds,ansList);
                //Restore the changes that were made by that function call
                ds.remove(ds.size()-1);
            }
        }
    }
    public static boolean isPalindrome(String s,int start,int end){
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end))return false;
            start++;
            end--;
        }
        return true;
    }
    
}
