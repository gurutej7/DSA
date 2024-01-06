package SlidingWindow.Medium;

import java.util.HashMap;

/* 904. Fruits into Baskets

link { https://leetcode.com/problems/fruit-into-baskets/description/ }

Example 1:
Input: fruits = [1,2,1]                         Output: 3
Explanation: We can pick from all 3 trees.

Example 2:
Input: fruits = [0,1,2,2]                       Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Example 3:
Input: fruits = [1,2,3,2,2]                     Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


*/

public class FruitIntoBaskets {
    public static void main(String[] args) {

        int fruits[] = {1,2,3,2,2};

        System.out.println( totalFruit(fruits) );
        
    }

    public static int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int left = 0 , right = 0 , maxFruitCount = 0  ;
        int n = fruits.length , typeCount = 0 ;
        while(right < n){
            // If it is occuring for the first time increase the type count
            if(map.getOrDefault(fruits[right],0) == 0)typeCount++;
            // Store the no. of occurences of a fruit in the current window
            // useful when we want to shrink the window
            map.put(fruits[right],map.getOrDefault(fruits[right],0)+1);

            // If the we are having more than 2 types then we have to leave one type behind and continue with the new type
            while(typeCount > 2){
                // To consider we have removed one type then we have to skip all the occurences of that type in
                // the current window that is until its frequency becomes zero then only we will decrement type count.
                map.put(fruits[left] , map.get(fruits[left])-1);
                if(map.get(fruits[left]) == 0)typeCount--;
                left++;
            }
            // Keeping track of every window length
            maxFruitCount = Math.max(maxFruitCount,right-left+1);
            right++;
        }

        return maxFruitCount;

    }

    // A solution without using a HashMap , because the typeCount is at max can 2 
    // So we can use two variables to keep track of the two types of fruits in the current window
    public static int totalFruit2(int[] tree) {
        // track last two fruits seen
        int lastFruit = -1;
        int secondLastFruit = -1;
        int lastFruitCount = 0;
        int currMax = 0;
        int max = 0;
        
        for (int fruit : tree) {
            if (fruit == lastFruit || fruit == secondLastFruit)
                currMax++;
            else
                currMax = lastFruitCount + 1; // last fruit + new fruit
            
            if (fruit == lastFruit)
                lastFruitCount++;
            else
                lastFruitCount = 1; 
            
            if (fruit != lastFruit) {
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }
            
            max = Math.max(max, currMax);
        }
        
        return max;

        // { https://leetcode.com/problems/fruit-into-baskets/solutions/170745/problem-longest-subarray-with-2-elements/ }
    }
    
}
