package MonotonicStack;

import java.util.HashMap;
import java.util.Stack;
import java.util.Arrays;

/* 496. Next Greater Element 1
The next greater element of some element x in an array is the first greater element that is to the right of x 
in the same array.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.


*/
public class NextGreaterElement1{
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};

        int ans[] = nextGreaterElement(nums1, nums2);

        System.out.println(Arrays.toString(ans));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length , m = nums2.length ;
        int ans[] = new int[n];
        //We are going to find next greater elements for all nums in nums2 array and store it in the map
        // Then traverse through nums1 array and check in the map for the next greater element
        HashMap<Integer,Integer> map = new HashMap<>();

        Stack<Integer> st = new Stack<>();
        for(int i = m-1 ; i>=0 ; i--){
            //Monotonic stack operation to have max element at the top
            // Remove all the lesser elements in the stack , with respect to the current element
            while(!st.isEmpty() && st.peek() < nums2[i]){
                st.pop();
            }
            //After removing all the less than elements to the current element
            //and the stack is not empty then the top element will be the next greater element for the current element
            
            if(!st.isEmpty())map.put(nums2[i] , st.peek());
            //Add the current element to the stack because it may/can be the next greater element for the upcoming elements
            st.push(nums2[i]);
        }

        for(int i = 0 ; i<n ; i++){
            // If map does`nt have means -1 ( No greater element exists)
            ans[i] = map.getOrDefault(nums1[i],-1);
        }
        return ans;
    }

}