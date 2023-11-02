package Recursion.Medium;

import java.util.ArrayList;

public class StringPermutations {
    public static void main(String[] args) {
        printPerm("", "abc");
        ArrayList<String> list = permutationsList("", "abc");
        System.out.println(list);
        System.out.println(permutationsCount("", "abcde"));
    }

    public static void printPerm(String ans , String in){
        //Base Condition
        if(in.isEmpty()){
            System.out.println(ans);
            return;
        }
        // Debug once for more understanding
        char ch = in.charAt(0);
        for(int i = 0 ; i<=ans.length() ; i++){
            String firstPart = ans.substring(0,i);
            String secondPart = ans.substring(i, ans.length());
            printPerm(firstPart+ch+secondPart, in.substring(1));
        }
    }

    public static ArrayList<String> permutationsList(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);

        // local to this call
        ArrayList<String> ans = new ArrayList<>();

        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            ans.addAll(permutationsList(f + ch + s, up.substring(1)));
        }
        return ans;
    }

    public static int permutationsCount(String p, String up) {
        if (up.isEmpty()) {
            return 1;
        }
        int count = 0;
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i);
            String s = p.substring(i, p.length());
            count = count + permutationsCount(f + ch + s, up.substring(1));
        }
        return count;
    }
    
}
