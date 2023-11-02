package Recursion.Medium;

import java.util.ArrayList;

public class KeyPadCombinations {
    public static void main(String[] args) {
        ArrayList<String> list = keypadComb("", "12345");
        System.out.println(list);
    }
    /* 1     2     3
     abc    def   ghi
       4     5     6
     jkl    mno   pqr
       7     8     9
     stu    vwx    yz
     
     Example: input = "12"
            output = [ae , ad , af , be , bd , bf , cd , ce , cf];
      
     */
    //in = input
    public static ArrayList<String> keypadComb(String ans,String in){
        if(in.isEmpty()){
            //Local ans that is going to be returned for that function call
            ArrayList<String> list = new ArrayList<>();
            list.add(ans);
            return list;
        }

        int digit = in.charAt(0)-'0'; // To covert character to int
        //List that is going to be returned after all function calls
        ArrayList<String> ansList = new ArrayList<>();
        //refer notes for i value logic
        for (int i = (digit-1)*3; i <digit*3; i++) {
            char ch = (char)('a'+i);
            ansList.addAll(keypadComb(ans+ch, in.substring(1)));
            
        }
        return ansList;
        
    }
    
}
