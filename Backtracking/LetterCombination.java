import java.util.ArrayList;
import java.util.List;

/* 17. Letter Combinations of a Phone Number
Example 1:
Input: digits = "23"            Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:
Input: digits = ""              Output: []
 */

public class LetterCombination {
    public static void main(String[] args) {
        String digits = "23";

        System.out.println(letterCombinations(digits));

        System.out.println(letterCombinations2(digits));
    }

    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length() == 0) return ans;
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        
        backtrack(ans,digits,"",0,map);
        
        return ans;
    }
    /*step no.     i      res   
        1          0      ""
        2          1      "a"      j=0   (j is str1 index)
        3          2      "ad"     j=0   (j is str2 index)   //Base case i == digits.lenght() or res.length() == digits.length()
        4          2      "ae"     j=1  //return
        5          2      "af"     j=2  //return
        6          1      "b"      j=1  // j at step 2 is incremented here (str1 index)
        7          2      "bd"     j=0  //j is now again started for str2
        8          2      "be"     j=1  // return
        9          2      "bf"     j=2  // return
        10         1      "c"      j=2  // j at step 6 is incremented here (str1 index)
        11         2      "cd"     j=0  // j is now again stared for str2
        12         2      "ce"     j=1  
        13         2      "cf"     j=2
     */
    private static void backtrack(List<String> ans,String digits,String res , int i , String[] map){
        if(res.length() == digits.length()){
            ans.add(res);
            return;
        }
        if(i >= digits.length()) return;
        String strAtDigit = map[digits.charAt(i)-'0'];
        for(int j = 0 ; j<strAtDigit.length() ; j++){
            //below line is to remember about strings while passing as arguments // res = res + strAtDigit(j);  XXX
            String newStr = res+strAtDigit.charAt(j);
            backtrack(ans,digits,newStr,i+1,map);
        }

    }
    public static List<String> letterCombinations2(String digits) {
        List<String> list = new ArrayList<>();
        if(digits == null || digits.length() == 0) return list;
        char[][] map = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        backtrack(digits,list,map,new StringBuilder(),0);
        return list;
    }
    private static void backtrack(String digits, List<String> list,char[][] map, StringBuilder sb, int start){
        if(start == digits.length()) {
            list.add(new String(sb));
            return;
        }
        int num = digits.charAt(start) - '0';
        for(int i = 0;i< map[num].length;i++){
            sb.append(map[num][i]);
            backtrack(digits,list,map,sb,start+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
