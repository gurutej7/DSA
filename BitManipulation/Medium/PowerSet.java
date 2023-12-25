package BitManipulation.Medium;

import java.util.ArrayList;
/*You are Given a String str which has a-z lower case letters. return the all possible subsequences.
Sample Input 1: abc
Sample Output 1:  a ab abc ac b bc c
 */

public class PowerSet {
    public static void main(String[] args) {
        String str = "abc";

        System.out.println(subsequences(str));
    }

    //For a String of Length n we have 2^n subsequences (^ - power)
    public static ArrayList<String> subsequences(String str) {
        // Write your code here
        int n = str.length();
        ArrayList<String> ans = new ArrayList<>();
        //Number of subsequences is pow(2,n) = (1<<n)
        int tot = (1<<n);
        for(int num = 0 ; num < tot ; num++){
            //FOr each subsequence if the bit is set in num then add the char at index of that bit
            //number of bits to check is the length of the string
            StringBuilder temp = new StringBuilder();
            for(int i = 0 ; i < n ; i++){
                //If the bit is set add the current char
                if( ((num) & (1<<i))  != 0)temp.append(str.charAt(i));
            }
            ans.add(temp.toString());
        }
        return ans;
    }
    // TC = (2^n)*n
    /*     num         (2)c     (1)b     (0)a`         subsequence
           0            0      0         0              ""
           1            0      0         1              "a"
           2            0      1         0              "b"
           3            0      1         1              "ab"
           4            1      0         0              "c"
           5            1      0         1              "ac"
           6            1      1         0              "bc"
           7            1      1         1              "abc"
     */
}
