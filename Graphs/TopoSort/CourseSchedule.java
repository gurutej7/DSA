package Graphs.TopoSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* Course Schedule 1 and 2
{ https://leetcode.com/problems/course-schedule/description/ } 
{  https://leetcode.com/problems/course-schedule-ii/description/ }

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are 
given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course 
bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
0 -> 1

Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
0->1 , 0->2  , 1->3 , 2-> 3
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both 
courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 */

public class CourseSchedule {
    public static void main(String[] args) {
        int numCourses = 4 ;
        int [][] prerequisites = {{1,0},
                                  {2,0},
                                  {3,1},
                                  {3,2}};
        System.out.println(canFinish(numCourses, prerequisites));

        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
        
    }

    /* Observation :
    This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, 
    no topological ordering exists and therefore it will be impossible to take all courses. 
     */
    private static int[] findOrder(int numCourses, int[][] prerequisites) {
        int V = numCourses; // vertices
        int e = prerequisites.length; // edges
        // kahn`s Algo to find topoSort
        Queue<Integer> q = new LinkedList<>();
        int indegree[] = new int[V];
        int cnt = 0 ;
        int topoSort [] = new int[V];
        // construct Adjacency List
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());

        for(int i = 0 ; i < e ; i++){
            // according to problem edge is edges[i][1] to edges[i][0]
            int a = prerequisites[i][1];
            int b = prerequisites[i][0]; // edge a -> b
            adj.get(a).add(b);
            indegree[b]++;
        }

        for(int i = 0 ; i < V ; i++) {
            if(indegree[i] == 0){
                cnt++;
                q.offer(i);
            }
        }

        int index =  0 ;
        // Standard Algorithm to find TopoSort
        while(!q.isEmpty()){
            int curr = q.poll();
            topoSort[index++] = curr;

            for(int i : adj.get(curr)){
                indegree[i]--;
                if(indegree[i] == 0){
                    cnt++;
                    q.offer(i);
                }
            }
        }
        // if we can`t finish all The courses return an Empty Array
        if(cnt != V) return new int[]{};

        return topoSort ; 
    }

    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        int V = numCourses; // vertices
        int e = prerequisites.length; // edges
        // kahn`s Algo to find whether there is a cycle or not
        Queue<Integer> q = new LinkedList<>();
        int indegree[] = new int[V];
        int cnt = 0 ;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < V ; i++) adj.add(new ArrayList<>());

        for(int i = 0 ; i < e ; i++){
            // according to problem edge is edges[i][1] to edges[i][0]
            int a = prerequisites[i][1];
            int b = prerequisites[i][0]; // edge a -> b
            adj.get(a).add(b);
            indegree[b]++;
        }

        for(int i = 0 ; i < V ; i++) {
            if(indegree[i] == 0){
                cnt++;
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int i : adj.get(curr)){
                indegree[i]--;
                if(indegree[i] == 0){
                    cnt++;
                    q.offer(i);
                }
            }
        }

        return cnt == V ; // means no cycle 
    }

    // TC -> O(V + E)
    // SC -> O(N+N+N)
}
