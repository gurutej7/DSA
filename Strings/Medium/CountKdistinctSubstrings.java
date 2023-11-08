package Strings.Medium;
/*
Sample Input 1 :
aacfssa    
3
Sample Output 1 :
5    
Explanation of The Sample Input 1:
Given 'str' = “aacfssa”. We can see that the substrings with only 3 distinct characters are {aacf, acf, cfs, cfss, fssa}. 

Therefore, the answer will be 5.
Sample Input 2 :
qffds
4
Sample Output 2 :
1
 */

public class CountKdistinctSubstrings {
	public static void main(String[] args) {
		String str ="aacfssa";

		System.out.println(countSubStrings(str, 3));
	}

	public static int countSubStrings(String str, int k) {
        // Write your code here.
        int n = str.length();
        int ans = 0;
        for(int i = 0 ; i<n ; i++){
            int map [] = new int[26];
            int distinctCharCount = 0;
            for(int j = i ; j<n ; j++){
                //First occurrence of the character (distinct)
                if(map[str.charAt(j)-'a']==0){
                    distinctCharCount++;
                    //mark it as Already occured
                    map[str.charAt(j)-'a']++;
                }
				//Found one Substring with given condition
                if(distinctCharCount==k) ans++;

                if(distinctCharCount >k) break;
            }
        }
        return ans;
    }
  
}
