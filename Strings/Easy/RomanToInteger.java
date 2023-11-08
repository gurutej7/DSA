package Strings.Easy;

import java.util.HashMap;

/* 13. Roman To Integer
Example 1:                      Example 2:
Input: s = "III"                Input: s = "LVIII"
Output: 3                       Output: 58
Explanation: III = 3.           Explanation: L = 50, V= 5, III = 3.

* Example 3:
Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
   
 */
public class RomanToInteger {
	public static void main(String[] args) {

		System.out.println(romanToInt("MCMXCIV"));

		System.out.println(romanToIntWithoutMap("MCMXCIV"));
		
	}

	/*The key intuition lies in the fact that in Roman numerals, when a smaller value appears before a larger 
	value, it represents subtraction, while when a smaller value appears after or equal to a larger value, it 
	represents addition.
	 
	 */
	public static int romanToInt(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int n = s.length();
        int ans = 0;
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        for(int i = 0 ; i<n ; i++ ){
            if(i<n-1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1)))
            ans -= map.get(s.charAt(i));
            else ans += map.get(s.charAt(i));
        }
        return ans;
    }


	//By observing the logic we can solve without using a map by using a extra variable
	//And traversing from the back (can be understood why dry running some examples)
	public static int romanToIntWithoutMap(String s) {
		int ans = 0, num = 0, prev=0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (num < prev) ans -= num;
            else ans += num;
            prev = num;
        }
        return ans;
	}
  
}
