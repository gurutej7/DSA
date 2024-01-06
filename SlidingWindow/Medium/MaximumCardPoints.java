package SlidingWindow.Medium;


/* 1423. Maximum cards You can Obtain From Cards

link { https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/ }

There are several cards arranged in a row, and each card has an associated number of points. The points are given
 in the integer array cardPoints.
In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
Your score is the sum of the points of the cards you have taken.
Given the integer array cardPoints and the integer k, return the maximum score you can obtain.

Example 1:
Input: cardPoints = [1,2,3,4,5,6,1], k = 3                  Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.


*/

public class MaximumCardPoints {
    public static void main(String[] args) {
        int cardPoints [] = {1,2,3,4,5,6,1};
        int k = 3;

        System.out.println( maxScore(cardPoints, k) );
        
    }
    
    // Approach - 1 
    // Came to this idea after I went through the Hints (provided by the problem)
    public static int maxScore(int[] cardPoints, int k) {
        // Let the sum of all points be total_pts. You need to remove a sub-array from cardPoints with length n - k.
        int n = cardPoints.length;
        int left = 0 , right = 0;
        int maxSum = 0 , totalSum = 0;
        for(int point : cardPoints) totalSum += point;
        int currentWindowSum = 0 ;
        //Keep a window of size n - k over the array. The answer is max(answer, total_pts - sumOfCurrentWindow)
        while(right < n){
            
            if(right - left + 1 > n-k){
                maxSum = Math.max(maxSum , totalSum - currentWindowSum);
                currentWindowSum -= cardPoints[left];
                left++;
            }

            currentWindowSum += cardPoints[right];
            right++;
        }
        maxSum =  Math.max(maxSum , totalSum - currentWindowSum);

        return maxSum;

    }

    // Approach 2  Simple (Need to use more observation Haki to come up with this approach instantly)
    // First consider k elements from left only 
    // Then remove one from left and add one from right 
    // At the you will be having k elements from right only
    // Through the process update max with (leftSum + RightSum )

    // { https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/solutions/597825/simple-clean-intuitive-explanation-with-visualization/ }

    public static int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length, leftSum = 0 , rightSum = 0;
        // First consider k elements from left only 
        for(int i = 0; i < k; ++i){
            leftSum += cardPoints[i];
        }
        int maxSum = leftSum;
        for(int i = 0; i < k; ++i){
            // Then remove one from left and add one from right 
            rightSum += cardPoints[n-i-1];
            leftSum -= cardPoints[k-i-1];
            maxSum = Math.max(maxSum,leftSum+rightSum);
        }
        return maxSum;
    }

}
