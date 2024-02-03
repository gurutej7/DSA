package Greedy.Medium;

import java.util.Arrays;

/* 135. Candy
{ https://leetcode.com/problems/candy/description/ }

There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
You are giving candies to these children subjected to the following requirements:
*   Each child must have at least one candy.
*   Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:      Input: ratings = [1,0,2]            Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 */

public class Candy {
    public static void main(String[] args) {
        int ratings[] = {1,0,2};

        System.out.println( candyCount(ratings) );
        
    }

    private static int candyCount(int[] ratings) {
        int n = ratings.length ;
        int candies[] = new int[n];
        Arrays.fill(candies,1);

        // left to right check if current child is greater than neighbour on left
        for(int i = 1 ; i < n ; i++){
            if(ratings[i] > ratings[i-1]) 
                candies[i] = candies[i-1] + 1;
        }

        // right to left to if current child is greater than neighbour on right
        for(int i = n-2 ; i>= 0 ; i--){
            if(ratings[i] > ratings[i+1]){
                // check it may have already greater candies than it`s right if not then increase by one
                if(candies[i] <= candies[i+1]) candies[i] = candies[i+1]+1;
            }
        }

        // We have checked for both right and left neighbours and stored the candie in the array
        int sum = 0 ;
        for(int candy : candies) sum += candy ;

        return sum;
    }

    /* Detailed Explanation of the Approach
    arr[i] : no of candies ith child have

    1. First give every child 1 candy so arr[i] = 1 for all i .

    2.Traverse from left to right and if :

      *  ratings[i] > ratings[i-1] : we must give ith child atleast one more candy than (i-1)th child :
        arr[i] = arr[i-1] +1 ;

    3. Traverse from Right to Left and if :
      *  ratings[i] > ratings[i+1] : we must give ith child atleast one more candy than (i+1)th child:
        if(arr[i]<=arr[i+1])arr[i]=arr[i+1]+1 ;

    4. During the second traversal , we can observe that changing the values of arr doesn't affects the relation maintained in the first traversal

    5. Hence, both the right and left neighbours relation are maintained, without affecting each other.

    6. We must observe that this is the minimum number of increament we can perform
     */
    
}
