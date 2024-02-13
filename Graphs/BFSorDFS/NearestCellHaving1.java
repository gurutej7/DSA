package Graphs.BFSorDFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/* Distance of nearest cell having 1

Example 1:
Input: grid = {{0,1,1,0},{1,1,0,0},{0,0,1,1}}
Output: {{1,0,0,1},{0,0,1,1},{1,1,0,0}}
Explanation: The grid is-
0 1 1 0 
1 1 0 0 
0 0 1 1 
0's at (0,0), (0,3), (1,2), (1,3), (2,0) and
(2,1) are at a distance of 1 from 1's at (0,1),
(0,2), (0,2), (2,3), (1,0) and (1,1)
respectively.
 */

public class NearestCellHaving1 {
    public static void main(String[] args) {
        int grid[][] = {{0,1,1,0},
                        {1,1,0,0},
                        {0,0,1,1}};

        int res[][] = nearest(grid);

        for(int row [] : res){
            System.out.println(Arrays.toString(row));
        }
        
    }
    private static  int[][] nearest(int[][] grid)
    {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        boolean vis[][] = new boolean[n][m];
        
        Queue<Pair> q = new LinkedList<>();
        // nearest cell having 1 , the distance for cells with value 1 will be zero because they themselves are 1`s
        // now we will start from all 1`s a BFS and then assign distance for the cells with non-1 values
        // Based on the level they are from their respective starting 1 , we will be increasing distance and assigning them
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j< m ; j++){
                if(grid[i][j] == 1){
                    q.offer(new Pair(i,j,0));
                    vis[i][j] = true;
                }
            }
        }
        
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        
        while(!q.isEmpty()){
            Pair curr = q.poll();
            int i = curr.getFirst();
            int j = curr.getSecond();
            int dist = curr.getThird();
            res[i][j] = dist;
            
            for(int k = 0 ; k<4 ; k++){
                int row = drow[k]+i;
                int col = dcol[k]+j;
                
                if(row>=0 && col >= 0 && row <n && col < m && !vis[row][col] ){
                    vis[row][col] = true;
                    q.offer(new Pair(row,col,dist+1));
                }   
            } 
        }
        return res;
    }
}


