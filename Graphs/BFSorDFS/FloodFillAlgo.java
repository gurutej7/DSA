package Graphs.BFSorDFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* { https://leetcode.com/problems/flood-fill/description/ }
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all
 pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are 
 colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 */

public class FloodFillAlgo {
    public static void main(String[] args) {
        int[][] image = {{1,1,1},
                         {1,1,0},
                         {1,0,1}};
        int sc = 1 , sr = 1 , color = 2 ;

        int res1[][] = floodFillBFS(image, sr, sc, color);
        int res2[][] = floodFillDFS(image, sr, sc, color);

        for(int row[] : res1) System.out.println(Arrays.toString(row));
        System.out.println();
        for(int row[] : res2) System.out.println(Arrays.toString(row));

    }
    
    private static  int[][] floodFillBFS(int[][] grid, int sr, int sc, int color) {
        Queue<Pair> q = new LinkedList<>();

        int n = grid.length , m = grid[0].length;
        int vis[][] = new int[n][m];
        int ori = grid[sr][sc];
        int res[][] = new int[n][m];
        res[sr][sc] = color;
        vis[sr][sc] = 1;
        
        q.offer(new Pair(sr,sc)); // starting point

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int i = curr.getFirst();
            int j = curr.getSecond();
            // check up
            if(i-1 >=0 && grid[i-1][j] == ori && vis[i-1][j] == 0){
                vis[i-1][j] = 1 ;
                res[i-1][j] = color;
                q.offer(new Pair(i-1,j)); // time increased by 1
            }
            // check down
            if(i+1 < n && grid[i+1][j] == ori && vis[i+1][j] == 0){
                vis[i+1][j] = 1 ;
                res[i+1][j] = color;
                q.offer(new Pair(i+1,j));
            }
            // check left
            if(j-1 >=0 && grid[i][j-1] == ori && vis[i][j-1] == 0){
                vis[i][j-1] = 1 ;
                res[i][j-1] = color;
                q.offer(new Pair(i,j-1)); // time increased by 1
            }
            // check down
            if(j+1 < m && grid[i][j+1] == ori && vis[i][j+1] == 0){
                vis[i][j+1] = 1 ;
                res[i][j+1] = color;
                q.offer(new Pair(i,j+1));
            }
        }

        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(vis[i][j] == 0) res[i][j] = grid[i][j];
            }
        }

        return res;
    }

    // SC -> O(N*M)
    // TC -> O(N*M)


    // DFS approach
    private static int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor)
    {
        // Code here 
        int n = image.length;
        int m = image[0].length;
        
        int[][] vis = new int[n][m];
        int[][] res = new int[n][m];
        
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j< m ;j++){
                res[i][j] = image[i][j];
            }
        }
        
        int initialColor = image[sr][sc];
        
        dfs(res,sr,sc,image,vis,initialColor,newColor);
        
        return res;
    }
    
    static int drow[] = {-1,0,1,0} ;
    static int dcol[] = {0,-1,0,1} ;
    
    private static void dfs(int[][] res,int i , int j , int[][] image ,int[][] vis , int initialColor , int newColor ){
        res[i][j] = newColor;
        
        vis[i][j] = 1 ;
        
        for(int k = 0 ; k < 4 ; k++){
            int row = drow[k]+i;
            int col = dcol[k]+j;
            
            if(row >=0 && col >=0 && row <res.length && col < res[0].length && vis[row][col] == 0 && image[row][col] == initialColor){
                dfs(res,row,col,image,vis,initialColor,newColor);
            }
        }
        
        
    }
}
// TC - O(N*M)*4

