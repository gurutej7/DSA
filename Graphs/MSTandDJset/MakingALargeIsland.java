package Graphs.MSTandDJset;

import java.util.HashMap;
import java.util.HashSet;

/* 827. Making a Large Island
{ https://leetcode.com/problems/making-a-large-island/description/ } 
{ https://www.geeksforgeeks.org/problems/maximum-connected-group/1 }

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.

Example 1:  Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 */

public class MakingALargeIsland {
    public static void main(String[] args) {
        int grid[][] = {{1,0},{0,1}};

        System.out.println(MaxConnection(grid));
        System.out.println(largestIsland(grid));
        
    }

    static int delRow[] = {1,-1,0,0};
    static int delCol[] = {0,0,1,-1};
    private static int MaxConnection(int grid[][]) {
       int n = grid.length;
       int m = grid[0].length;
       // every time we try to replace a 0 with 1 the graph changes dynamically
       // Think about Disjoint set
       // But in Dj set we can`t work with i,j => so we have to somehow covert it to a single Integer , which we will be calling it as a node
       // formula => r*m + c => m = number of columns
       // At each 0 , check in 4 directions if there exist a connected component , if exist then add the size of it
       // We can use DJ set initially to find and store the sizes of the all the connected components
       
       UnionFind uf = new UnionFind(n*m);
       int max = 1 ;
       
       for(int i = 0 ; i < n ; i++){
           for(int j = 0 ; j < m ; j++){
               if(grid[i][j] == 1){
                   int node = i*m + j;
                   // check in 4 directions , if there exist`s a node that we can connect to
                   for(int k = 0 ; k < 4 ; k++){
                       int r = delRow[k]+i;
                       int c = delCol[k]+j;
                       // check if it is a valid index or not
                       if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                           int adjNode = r*m + c;
                           int up = uf.find(adjNode);// find the ultimate parent and then add the size
                           uf.unionBySize(node,up);
                        //    max = Math.max(uf.size[uf.find(node)],max);
                           max = Math.max(uf.size[up],max); // if there is no 0`s in the grid we will end up not at all looking at the size of the any connected component
                       }
                   }
               }
           }
       }
       
       for(int i = 0 ; i < n ; i++){
           for(int j = 0 ; j < m ; j++){
               if(grid[i][j] == 0 ){
                   int currSize = 1 ;
                   HashSet<Integer> seen = new HashSet<>(); // to avoid adding the size of the same component twice , because a 0 can be surrounded by a single component in more than one direction
                   // check in 4 directions 
                   for(int k = 0 ; k < 4 ; k++){
                       int r = delRow[k]+i;
                       int c = delCol[k]+j;
                       // check if it is a valid index or not
                       if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                           int adjNode = r*m + c;
                           int up = uf.find(adjNode); // find the ultimate parent and then add the size
                           if(!seen.contains(up)) currSize += uf.size[up];
                           seen.add(up);
                       }
                   }
                   
                   max = Math.max(currSize,max); 
               }
           }
       }
       
       return max; 
       // Tc - O(n^2)
    }

    // Approach - 2 using DFS
    // refer for visualization = { https://leetcode.com/problems/making-a-large-island/solutions/127015/c-with-picture-o-n-m/ }
    private static int largestIsland(int[][] grid) {
        int n = grid.length;
        HashMap<Integer,Integer> area = new HashMap<>();//Key: color, Val: size of island painted of that color
        area.put(0, 0); //We won't paint island 0, hence make its size 0, we will use this value later   
        int max = 1 , color = 2 ; // same color represents a single island , 0,1 already exist in the grid so starting with 2

        // calculate are for all the possible islands
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ;j++){
                if(grid[i][j] == 1){
                    int size = dfs(i,j,grid,color); // painting all connected lands to the current land as a single island
                    area.put(color,size); // storing the size of this land
                    color++;
                }
            }
        }
        //If there is no island 0 from grid, max should be the size of islands of first color
        //If there is no island 1 from grid, max should be 0 
        max = area.getOrDefault(2,0);

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 0){
                    int curr = 1 ;
                    HashSet<Integer> seen = new HashSet<>();
                    for(int k = 0  ; k < 4 ; k++){
                        int r = delRow[k]+i;
                        int c = delCol[k]+j;
                        if(r >= 0 && c >= 0 && r < n && c < n){
                            int currColor = grid[r][c];
                            if(!seen.contains(currColor)) {
                                curr += area.get(currColor);
                                seen.add(currColor); // to avoid duplicates of the same island
                            }
                        }
                    }
                    max = Math.max(max,curr);
                }
            }
        }
        return max;
    }

    private static int dfs(int r , int c , int[][] grid , int color){
        grid[r][c] = color;
        int size = 1 ;
        for(int i = 0 ; i < 4 ; i++){
            int x = delRow[i]+r;
            int y = delCol[i]+c;
            if(x >= 0 && y >= 0 && x < grid.length && y < grid.length &&  grid[x][y] == 1){
                size +=  dfs(x,y,grid,color);
            }
        }

        return size;
    }
}
