package Strings.StringMatching;

import java.util.ArrayList;

public class ZAlgorithm {

    /* Search Pattern (Z-algorithm)
     * https://www.geeksforgeeks.org/problems/search-pattern-z-algorithm--141631/1
     * 
     * Given two strings, one is a text string and the other is a pattern string. 
     * The task is to print the indexes of all the occurrences of the pattern string in the text string.
     * For printing, Starting Index of a string should be taken as 1.
     */

    private static ArrayList<Integer> zSearch(String text , String pattern){
        ArrayList<Integer> res = new ArrayList<>();
        int n = text.length() , m = pattern.length();
        char str [] = new char[n+m+1];
        // combine pattern and text
        int ind = 0 ;
        for(int i = 0 ; i < m ; i++) str[ind++] = pattern.charAt(i);
        str[ind++] = '$';
        for(int i = 0 ; i < n ; i++) str[ind++] = text.charAt(i);

        int z[] = ZArray(str);

        for(int i = m ; i<n+m+1 ; i++){
            // System.out.println("i->  "+i+"  z[i]"+  z[i]);
            if(z[i] == m) {
                // System.out.println("Inside if -> "+ z[i]);
                res.add(i-m-1);
            }
        }

        return res;
    }
    /* Example :
     * text = "xabcabzabc" 
     * pattern = "abc"
     * 
     *          0 1 2 3 4 5 6 7 8 9 10 11 12 13
     *          a b c $ x a b c a b  z  a  b  c  
     * Z[]  =   {0 0 0 0 0 3 0 0 2 0  0  3  0  0}
     * let , len(text) = n , len(pattern) = m
     * idx , of pattern in original text
     * if(Z[k] == m) ind = n-k-1
     * 
     * Time and Space => O(n+m)
     */

    private static int[] ZArray(char[] str){
        int n = str.length;
        int Z[] = new int[n];
        int left = 0 , right = 0;
        for(int k = 1; k < n; k++) {
            if(k > right) {
                left = right = k;
                while(right < n && str[right] == str[right - left]) {
                    right++;
                }
                Z[k] = right - left;
                right--;
            } else {
                //we are operating inside box
                int k1 = k - left;
                //if value does not stretches till right bound then just copy it.
                if(Z[k1] < right - k + 1) {
                    Z[k] = Z[k1];
                } else { //otherwise try to see if there are more matches.
                    left = k;
                    while(right < n && str[right] == str[right - left]) {
                        right++;
                    }
                    Z[k] = right - left;
                    right--;
                }
            }
        }
        return Z;
    }
    public static void main(String[] args) {
        String pattern = "bat" , text = "batmanandrobinarebat" ;
        System.out.println(zSearch(text, pattern));
    }

}
