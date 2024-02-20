package Graphs.ShortestPathAlgos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* Minimum Multiplications to Reach End
{  https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1 }

Given start, end and an array arr of n numbers. At each step, start is multiplied with any number in the array 
and then mod operation with 100000 is done to get the new start.
Your task is to find the minimum steps in which end can be achieved starting from start. If it is not possible 
to reach end, then return -1.

Example 1:  Input:  arr[] = {2, 5, 7}   start = 3, end = 30
Output: 2
Explanation:
Step 1: 3*2 = 6 % 100000 = 6 
Step 2: 6*5 = 30 % 100000 = 30
 */

public class MinimumMultiplicationsToReachEnd {
    public static void main(String[] args) {
        int start = 3 , end = 30 ;
        int[] arr = {2,5,7};

        System.out.println( minimumMultiplications(arr, start, end) );
        
    }

    private static int minimumMultiplications(int[] arr, int start, int end) {

        // Your code here
        int MOD = (int)1e5;
        int dist[] = new int[100000];
        int INF = (int)1e9;
        Arrays.fill(dist,INF);
        dist[start] = 0 ;
        // dist[x] has minimum number of multiplications to make x
        Queue<int[]> pq = new LinkedList<>();
        // a[0] = steps , a[2] = number  -> prioritize based on steps
        pq.offer(new int[]{0,start});
        
        while(!pq.isEmpty()){
            int [] curr = pq.poll();
            int currStep = curr[0];
            int currNum = curr[1];
            
            if(currNum == end) return currStep;
            
            for(int i : arr){
                int mul = (i * currNum)%MOD;
                if(dist[mul] > currStep + 1) {
                    dist[mul] = currStep + 1;
                    if(mul == end) return dist[mul];
                    pq.offer(new int[]{currStep+1,mul});
                }
            }
        }
        
        return -1;
        
        
        /* If someone's thinking here we can improve on space by declaring dist array of size (end + 1) and 
        then  we simply don't push the nodes into the queue which are greater then end because nodes greater than
        end multiplied by any integer can't lead to end. Now, This optimization could have been picked if we don't
        have to mod the node. Since, we need to mod the value of node, There may be a case where node is greater than
        end but after mod it becomes such a number say 'num' that 'num' multiplied by any particular array element will
        make our num value equal to end. Basically greater than end values can again reach to a value that is lesser than
        end after mod. So, this optimization can't be picked.
        */
        
        // before knowing/observing the above thing made 5 wrong submissions (:
        
    }


    
}
