package Graphs.BFSorDFS;

import java.util.Arrays;

/* 130. Surrounded Regions
{ https://leetcode.com/problems/surrounded-regions/description/ }

 Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally 
 surroundeby 'X'.A region is captured by flipping all 'O's into 'X's in that surrounded region. 

Example : 
Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.
  
 */

public class SurroundedRegions {
    public static void main(String[] args) {
        char board[][] = {{'X','X','X','X'},
                          {'X','O','O','X'},
                          {'X','X','O','X'},
                          {'X','O','X','X'}};
        
        solve(board);

        for(char[] row : board) System.out.println(Arrays.toString(row));
    }
    
    private static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        // observation is if there is O and if it is in any path connected to a 'O' on the boundary then it cant be surrounded
        // but for every 'O' checking if it it is connected to 'O' on the boundary takes time 
        // so reverse engineering
        // Traverse the boundaries if you find any 'O' then mark all it`s connected 'O's as cannot be surrounded
        boolean vis[][] = new boolean[n][m];
        // we will check the boundaries , if there is a 'O'
        // top row
        for(int j = 0 ; j < m ; j++){
            if(board[0][j] == 'O' && !vis[0][j])
                dfs(board,vis,0,j);
            // bottom row
            if(board[n-1][j] == 'O' && !vis[n-1][j])
                dfs(board,vis,n-1,j);
        }
       
      
        // left column
        for(int i = 0 ; i< n ;i++){
            if(board[i][0] == 'O' && !vis[i][0])
                dfs(board,vis,i,0);
            // right column
            if(board[i][m-1] == 'O' && !vis[i][m-1])
                dfs(board,vis,i,m-1);
        }
       
        // now traverse through the board if there exist any 'O' which is not visited
        // meaning it is not connected to a boundary 'O' , change all such them to X

        for(int i = 0 ; i< n ;i++){
            for(int j = 0 ; j<m ;j++){
                if(board[i][j] == 'O' && !vis[i][j])
                    board[i][j] = 'X';
            }
        }

    }

    static int delRow[] = {1,0,-1,0};
    static int delCol[] = {0,1,0,-1};

    private static void dfs(char[][] board , boolean[][] vis , int r , int c){
        vis[r][c] = true;
        for(int i = 0 ; i < 4 ; i++){
            int row = delRow[i]+r;
            int col = delCol[i]+c;
            if(row >=0 && col >= 0 && row < board.length && col < board[0].length && !vis[row][col] && board[row][col] == 'O')
                dfs(board,vis,row,col);
        }

    }

    // Tc - O(N*M)
}
