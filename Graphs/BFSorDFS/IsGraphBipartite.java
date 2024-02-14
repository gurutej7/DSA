package Graphs.BFSorDFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* is Graph Bipartite ? 
 https://leetcode.com/problems/is-graph-bipartite/description/
 * 
 * If you can color a graph with 2 colors such that no two neighbouring Node have the same color.
 * Linear Graph`s (with no Cycle) are always bipartite
 * Any Graph with a Even Cycle length is always bipartite
 * Any Graph with a Odd Cycle length is not bipatite
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge 
connects a node in one and a node in the other.
 */

public class IsGraphBipartite {
    public static void main(String[] args) {
        int[][] graph = {{1,2,3},
                          {0,2},
                          {0,1,3},
                          {0,2}};
        
        System.out.println(isBipartiteBFS(graph));
        System.out.println(isBipartiteDFS(graph));
    }

    // Approach : 
    // We will have a visited array which has initially values as -1 , meaning not colored
    // The we start by coloring with any of the color
    // Then while traversing Check if any of the neighbouring node is colored or not
    // If not colored then assign it a color(mark in the visited array) a color opposite of the current node.
    // if it is already colored then check if it is same color or not
    // if it is already colored before and it is same color as current color then there by return false.
    // vis array configuration -> -1 = not colored , 0-> one type of color , 1-> second type of color
    private static boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int vis[] = new int[n];

        Arrays.fill(vis, -1);
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                if (!bfs(graph, vis, i))
                    return false;
            }
        }

        return true;
    }

    private static boolean bfs(int[][] graph, int[] vis, int start) {
        vis[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            int currColor = vis[curr];

            for (int j : graph[curr]) {
                if (vis[j] == -1) {
                    vis[j] = currColor == 0 ? 1 : 0;
                    q.offer(j);
                } else {
                    if (vis[j] == currColor)
                        return false;
                }
            }
        }

        return true;
    }

    private static boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int vis[] = new int[n];

        Arrays.fill(vis, -1);
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                if (!dfs(i, 0, vis, graph))
                    return false;
            }
        }

        return true;
    }

    private static boolean dfs(int node, int color, int[] vis, int[][] graph) {
        vis[node] = color;

        for (int j : graph[node]) {
            if (vis[j] == -1) {
                if (!dfs(j, 1 - color, vis, graph))
                    return false;
            } else if (vis[j] == color)
                return false;
        }
        return true;
    }

    // TC - O(V + 2E)

}
