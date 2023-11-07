package Recursion.Medium;

import java.util.ArrayList;

public class DiceCombinations {
    public static void main(String[] args) {
        diceCombo("", 4);
        System.out.println(diceCombRet("", 5));
    }
    //in = input
    public static void diceCombo(String ans , int target){
        if(target==0){
            System.out.println(ans);
            return;
        }

        for(int i = 1; i<=6 && i<=target ; i++){
            diceCombo(ans+i, target-i);
        }
    }
     public static ArrayList<String> diceCombRet(String ans,int target){
        if(target==0){
            //Local ans that is going to be returned for that function call
            ArrayList<String> list = new ArrayList<>();
            list.add(ans);
            return list;
        }

        ArrayList<String> ansList = new ArrayList<>();
    
         for(int i = 1; i<=6 && i<=target ; i++){
            ansList.addAll(diceCombRet(ans+i, target-i));
        }
        return ansList;
    }
    
}
