package MonotonicStack;
import java.util.Stack;
import java.util.Arrays;
/* 
For Example

Input 1:
    A = [4, 5, 2, 10, 8]
Output 1:
    G = [-1, 4, -1, 2, 2]
Explaination 1:
    index 1: No element less than 4 in left of 4, G[1] = -1
    index 2: A[1] is only element less than A[2], G[2] = A[1]
    index 3: No element less than 2 in left of 2, G[3] = -1
    index 4: A[3] is nearest element which is less than A[4], G[4] = A[3]
    index 4: A[3] is nearest element which is less than A[5], G[5] = A[3]
*/

public class PreviousSmallerElement {
    public static void main(String[] args) {
        int[] nums = {4,5,2,10,8};

        System.out.println(  Arrays.toString( prevSmallerElement(nums) )  );
    }
    
    public static int[] prevSmallerElement(int nums[]){
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i< n ; i++){
            // We will remove previous elements in the stack which are greater than the current element
            while(!st.isEmpty() && st.peek() >= nums[i]) 
                st.pop(); 
            // If stack is empty means there is no previos smaller element for current element
            if(st.isEmpty()) ans[i] = -1;
            // else the Top element will be the previous smallest element of current element
            else  ans[i] = st.peek();
            // Push the current element because in future it may become the prev smaller element for other element
            st.push(nums[i]);

        }


        return ans;
    }
}
