package Math.Easy;

import java.util.ArrayList;


public class PrintAllDivisors {
    public static void main(String[] args) {
        
        System.out.println(printAllDivisors(10));

        System.out.println(printDivisorsNaive(10));
    }


    //O(sqrt(N))
    private static ArrayList<Integer> printAllDivisors(int n){
        ArrayList<Integer> ans = new ArrayList<>();     
        int last = (int)Math.sqrt(n);

        for(int i = 1 ; i<= last ; i++){
            if( n % i ==0 ) {
                ans.add(i);                        
                if(n/i != i) ans.add(n/i);
            }
        }
        return ans;

    }


    //O(N)
    public static ArrayList< Integer > printDivisorsNaive(int n) {
        // Write your code here
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 1 ; i <= n ; i++){
            if(n % i == 0) ans.add(i);
        }
        return ans;
    }
    
}
