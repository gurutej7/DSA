package Strings.StringMatching;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    static class StringHash{
        private static final long A = 911382323L;
        private static final long B = 972663749L;
        private long[] hash;
        private long[] powersOfA;
        private int n;
    
        public StringHash(String s){
            this.n = s.length();
            this.hash = new long[n];
            this.powersOfA = new long[n];
            hash[0] = (long)s.charAt(0);
            powersOfA[0] = 1L;
            for(int i=1;i<n;i++){
                hash[i] = (hash[i-1]*A + (long)s.charAt(i))%B;
                powersOfA[i] = (powersOfA[i-1]*A)%B;
            }
        }
        public long getHash(int left, int right){ // inclusive indices
            if(left<0 || right>=powersOfA.length){
                System.out.println("Index out bounds for substring [" + left + " ," +right+"]");
                return -1L;
            }
            if(left == 0) return this.hash[right];
            return Math.floorMod((hash[right]-hash[left-1]*powersOfA[right-left+1]),B); //Adding B is very important, else use Math.floorMod
        }
    }

    /* 28. Find the Index of First Occurence in a String
     * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
     * 
     * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Example 1:   Input: haystack = "sadbutsad", needle = "sad"
     * Output: 0
     * Explanation: "sad" occurs at index 0 and 6.
     * The first occurrence is at index 0, so we return 0.
     */

     private static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        StringHash text = new StringHash(haystack);
        StringHash pattern = new StringHash(needle);

        long patternHash = pattern.getHash(0,m-1);

        for(int i = 0 ; i <= n-m ; i++){
            long currHash = text.getHash(i,i+m-1);
            if(patternHash == currHash) return i;
        }
        return -1;
    }

    private static ArrayList<Integer> strStrList(String haystack, String needle){
        ArrayList<Integer> indices = new ArrayList<>();
        int n = haystack.length();
        int m = needle.length();
        StringHash text = new StringHash(haystack);
        StringHash pattern = new StringHash(needle);

        long patternHash = pattern.getHash(0,m-1);

        for(int i = 0 ; i <= n-m ; i++){
            long currHash = text.getHash(i,i+m-1);
            if(patternHash == currHash) indices.add(i);
        }

        return indices;
    }

    /* 3008. Find Beautiful Indices in the Given Array
     * https://leetcode.com/problems/find-beautiful-indices-in-the-given-array-ii/description/
     * 
     * Example 1:       Input: s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel", k = 15
     * Output: [16,33]
     * 
     * Explanation: There are 2 beautiful indices: [16,33].
     * - The index 16 is beautiful as s[16..17] == "my" and there exists an index 4 with s[4..11] == "squirrel" and |16 - 4| <= 15.
     * - The index 33 is beautiful as s[33..34] == "my" and there exists an index 18 with s[18..25] == "squirrel" and |33 - 18| <= 15.
     * Thus we return [16,33] as the result.
     */

    private static List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();
        ArrayList<Integer> aInd = strStrList(s,a);
        ArrayList<Integer> bInd = strStrList(s,b); 

        int n = aInd.size() , m = bInd.size() ;

        // iterated through aInd and bInd and checked for condition , but got TLE

        // since the arrays are sorted we can use 2 pointers

        for(int i = 0 , j = 0 ; i < n ; i++){
            while(j < m && bInd.get(j) + k < aInd.get(i)){ // if curr j is not valid for this i , then it cannot be valid for the upcoming i`s also so just skip them
                j++; 
            }
            if(j < m && Math.abs(aInd.get(i) - bInd.get(j)) <= k)  res.add(aInd.get(i));
        }
        return res;
    }

    /* 686. Repeated String Match
     * https://leetcode.com/problems/repeated-string-match/description/
     * 
     * Example 1:   Input: a = "abcd", b = "cdabcdab"       
     * Output: 3
     * Explanation: We return 3 because by repeating a three times "ab`cdabcdab`cd", b is a substring of it.
     */

    private static int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();
        if(m == 0) return 0 ;
        int repeatedCount = 1 ;
        // atleast len(A) >= len(B) , to contain B in it as a SubString
        int lenA = n ;
        while(lenA < m){
            repeatedCount++;
            lenA += n;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < repeatedCount ; i++){
            sb.append(a);
        }
        // case 1 : if B exists in A or not , when len(A) >= len(B)
        int res1 = strStr(sb.toString() , b);
        if(res1 != -1) return repeatedCount;
        // case 2 : If B exists in 2A or not , when len(A) >= len(B)
        sb.append(a);
        repeatedCount++;
        int res2 = strStr(sb.toString() , b);
        return res2 == -1 ?  -1 : repeatedCount;
        // 3A , 4A ... will result in the same combinations of substring as in 2A
        // so , if we can`t find in 2A , then we can`t find in any of the further repetetion
    }


    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";

        System.out.println(strStr(haystack, needle));
        System.out.println(strStrList(haystack, needle));

        String s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel";
        int k = 15;

        System.out.println(beautifulIndices(s, a, b, k));

        String A = "abcd" , B = "cdabcdab" ;
        System.out.println(repeatedStringMatch(A, B));
    }
}
