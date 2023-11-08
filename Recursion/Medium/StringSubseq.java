package Recursion.Medium;

import java.util.ArrayList;

public class StringSubseq {
    
    public static void main(String[] args) {
    printSubSeq("", "abc");
    ArrayList<String> list = subseqRet("", "abc");
    
    System.out.println(list);
    }

    public static void printSubSeq(String ans , String in){
        if(in.isEmpty()) {
            System.out.println(ans);
            return;
        }
        char ch = in.charAt(0);
        //You pick the current char to ans
        printSubSeq(ans+ch, in.substring(1));
        //You do not pick the current char to answer
        printSubSeq(ans, in.substring(1));
    }
    // Return a list a subseq
    public static ArrayList<String> subseqRet(String ans, String in){
        if (in.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(ans);
            return list;
        }
        char ch = in.charAt(0);
        //You pick the current char to ans
        ArrayList<String> left = subseqRet(ans + ch, in.substring(1));
        //You do not pick the current char to answer
        ArrayList<String> right = subseqRet(ans, in.substring(1));

        left.addAll(right);
        return left;
    }
   
}
