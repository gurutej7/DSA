package Strings.Easy;

import java.util.Scanner;

public class ReverseAString {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String str = input.next();

        System.out.println(reverseAstringMethodOne(str));
        System.out.println(reverseAStringMethodTwo(str));
        //System.out.println(reverseAstringMethodThree(str));
       
        input.close();
    }

    private static String reverseAstringMethodOne(String str){
        String newStr = "";
        for(int i = 0 ; i<str.length() ; i++){
            newStr = str.charAt(i) + newStr;
        }
        return newStr;
    }
    private static String reverseAStringMethodTwo(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        return sb.toString();
    }
    
}
