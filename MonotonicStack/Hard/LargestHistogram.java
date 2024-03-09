package MonotonicStack.Hard;

import java.util.Stack;

public class LargestHistogram {


    /* 84. Largest Rectangle in a Histogram
     * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
     * 
     * Input: heights = [2,1,5,6,2,3]       Output: 10  
     * Explanation: The above is a histogram where width of each bar is 1.
     * The largest rectangle is shown in the red area, which has an area = 10 units.
     * 
     */

     // Naive Solution , is consider each height and loop through all the heights and find the width of it 

     // 2 pass solution using the concept of previous smaller element
    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 0) return 0 ;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0 ;

        int smallerOnLeft [] = new int[n];
        int smallerOnRight[] = new int[n];

        // calculate smaller on Left
        for(int i = 0 ; i < n ; i++){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            if(st.isEmpty()) smallerOnLeft[i] = 0 ;
            else smallerOnLeft[i] = st.peek()+1;
            // System.out.println("Smaller on left of "+ heights[i] + " is " + heights[smallerOnLeft[i]]);
            st.push(i);
        }

        // clear the stack to reuse
        while(!st.isEmpty())st.pop();

        // calculate smaller on Right
        for(int i = n-1 ; i >= 0 ; i--){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            if(st.isEmpty())smallerOnRight[i] = n-1;
            else smallerOnRight[i] = st.peek()-1;
            // System.out.println("Smaller on Right of "+ heights[i] + " is " + heights[smallerOnRight[i]]);
            st.push(i);
        }

        // calculate area
        for(int i = 0 ; i < n ; i++){
            int w = smallerOnRight[i]-smallerOnLeft[i] + 1 ;
            int h = heights[i];
            maxArea = Math.max(maxArea,h*w);
        }

        return maxArea;
    }

    // one pass solution , bit tricky , figure out left and right smaller in one pass and calculate the area
    private static int largestRectangle(int[] heights){
        int n = heights.length;
        if(n == 0) return 0;
        int maxArea = 0 ;
        Stack<Integer> st = new Stack<>();
        int h = 0 , w = 0 ;

        for(int i = 0 ; i <= n ; i++){
            // by the end i== n also we will have elements in the stack , we have to calculate area for them also
            // dry run to understand or
            // https://www.youtube.com/watch?v=jC_cWLy7jSI
            while(!st.isEmpty() && (i == n || heights[st.peek()] > heights[i]) ){
                h = heights[st.peek()]; 
                // for this guy the the smaller on right , is our curr i
                // the smaller on left is the next guy inside the stack
                // if the stack is empty , there is no left smaller => 0 till i will be our width
                st.pop();
                if(st.isEmpty()) w = i ;
                else w = i - st.peek()-1 ; // if there is a smaller on left , then calc width
                maxArea = Math.max(maxArea , h*w);
            }

            st.push(i);
        }

        return maxArea;
    }

    /* 85. Maximal Rectangle
     * https://leetcode.com/problems/maximal-rectangle/description/
     * 
     * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     * Output: 6
     * Explanation: The maximal rectangle is shown in the above picture.
     * 
     */

    private static int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int heights[] = new int[n];
        int maxArea = 0 ;

        // construct heights array at each row
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == '1'){
                    heights[j] += 1 ;
                }
                else heights[j] = 0 ;
            }
            maxArea = Math.max(maxArea , largestRectangle(heights));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char [][] matrix = {{'1','0','1','0','0'},
                            {'1','0','1','1','1'},
                            {'1','1','1','1','1'},
                            {'1','0','0','1','0'}};
        
        int heights[] = {2,1,5,6,2,3};

        System.out.println(maximalRectangle(matrix));
        System.out.println(largestRectangleArea(heights));
    }
    
}
