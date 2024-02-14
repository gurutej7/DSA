package Graphs.BFSorDFS;

import java.util.ArrayList;
import java.util.HashSet;

/* Number of Distinct Islands 
https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1 

Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where 
a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to 
be distinct if and only if one island is not equal to another (not rotated or reflected).

Example 1:

Input:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Output:
1
 */

public class NumberOfDistinctIslands {
    public static void main(String[] args) {
        int grid[][] = {{1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 1}};

        System.out.println( countDistinctIslands(grid) );
        
    }
    
    private static int countDistinctIslands(int[][] grid) {
        // Your Code here
        // We have to some how store the shape of the islands into a set data structure
        // So no matter how many times the same island occurs  , our set will have only occurence of it
        // But the task is how can we store the shape 
        // could not figure that out on my own , but watched the video till only how can we store the shape
        // It it Whatever is our start point , consider i,j these as our base
        // Do a dfs from that index to visit all the connected islands to that , store the co-ordinates in a list
        // whenever you reach a land r,c store in the list of that particular island , as r-baseRow , c-baseCol
        // This makes sure to have same co-ordinates for the identical  Islands
        int n = grid.length;
        int m = grid[0].length;
        boolean [][] vis = new boolean[n][m];
        HashSet<ArrayList<String>> set = new HashSet<>();
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == 1 && !vis[i][j]){
                    ArrayList<String> shape = new ArrayList<>();
                    dfs(grid,vis,shape,i,j,i,j);
                    set.add(shape);
                }
            }
        }
        
        return set.size() ;
    }
    
    static int[] delRow = {0,0,-1,1};
    static int[] delCol = {1,-1,0,0};
    
    private static void dfs(int[][] grid,boolean[][] vis ,ArrayList<String> shape , int r , int c , int baseRow , int baseCol ){
        vis[r][c] = true;
        shape.add(toString(r-baseRow,c-baseCol));
        
        for(int i = 0 ; i< 4 ; i++){
            int row = delRow[i]+r;
            int col = delCol[i]+c;
            if(row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && !vis[row][col] && grid[row][col] == 1){
                dfs(grid,vis,shape,row,col,baseRow,baseCol);
            }
        }
    }
    
    private static String toString(int r , int c){
        return Integer.toString(r) + " " + Integer.toString(c);
    }
    
    // Sc -> O(N*M)
    // TC -> O(N*M*4)
}
