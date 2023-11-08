package Recursion.Medium;


public class RemoveAlla {
    public static void main(String[] args) {
        System.out.println(removeA("","abcdaab"));
        System.out.println(removeApp("", "abcdappbnd"));
        System.out.println(removeA("abcdaab"));
        System.out.println(removeApp("abcdappbnd"));
    }
    public static String removeA(String ans , String in){
        if(in.isEmpty()) return ans;
        char ch = in.charAt(0);
        if(ch == 'a') return removeA(ans,in.substring(1));
        else return removeA(ans+ch,in.substring(1));
    }
    public static String removeApp(String ans , String in){
        if(in.isEmpty()) return ans;
        char ch = in.charAt(0);
        if(in.startsWith("app")) return removeApp(ans,in.substring(3));
        else return removeApp(ans+ch,in.substring(1));
    }
    //With only one parameter / argument
     public static String removeA(String in){
        if(in.isEmpty()) return "";
        char ch = in.charAt(0);
        if(ch == 'a') return removeA(in.substring(1));
        else return ch + removeA(in.substring(1));
    }
    public static String removeApp(String in){
        if(in.isEmpty()) return "";
        char ch = in.charAt(0);
        if(in.startsWith("app")) return removeApp(in.substring(3));
        else return ch +removeApp(in.substring(1));
    }
    
    
}
