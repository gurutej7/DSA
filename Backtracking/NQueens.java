import java.util.ArrayList;

import java.util.List;

//51 . N Queens
/*
 The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 */

public class NQueens {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
        
    }

    public static List<List<String>> solveNQueens(int n) {
        //the question asks to return the board as List
        List<List<String>> ans = new ArrayList<List<String>>();
        //create a chess board of size NxN
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
            //fill all the boxes initially with '.'
                board[i][j] = '.';
               
        solve(board,0,ans);
        return ans;
    }
    //Function that actually solves
    public static void solve(char[][] board,int row ,List<List<String>> ans ){
        //Base condition if the row reaches N , It means we have placed Queens in all rows(N-1) row exixts in the board as row indexing starts from zero
        if(row==board.length){
            //Function to convert the current board to List of String
            ans.add(create(board));
            return;
        }
        for(int col = 0 ; col<board.length;col++){
            //If there is no Queen in the current position
            if(board[row][col]=='.'){
                //Check if the position is safe to place the Queen
                if(isValid(board,row,col)){
                    board[row][col]='Q';
                    solve(board,row+1,ans);
                    //Restore the changes that you made while returning back (Backtracking)
                    board[row][col]='.';
                }
            }
        }
    }
    public static boolean isValid(char[][] board , int row ,int col){
        //check vertical up
        for(int i= 0 ; i<row ; i++){
            if(board[i][col]=='Q') return false;
        }
        //check horizontal left
        for(int i= 0 ; i<col ; i++){
            if(board[row][i]=='Q') return false;
        }

         //Check Diagonal left
        int maxleft = Math.min(row,col);
        for (int i = 1; i <= maxleft; i++) {
            //both row and column are deceasing
            if(board[row-i][col-i]=='Q') return false;
        }
        //Check Diagonal Right
        int maxRight = Math.min(row, board.length-1-col);
        for (int i = 1; i <=maxRight; i++) {
            //row is decreasing column is increasing
            if(board[row-i][col+i]=='Q') return false;
            
        }
        //No need to check below because we are placing row wise from top to bottom
        //So there would be no Queens placed below the current Queen
        return true;
    }

    //Function to convert the current board to List of String
    public static List<String> create(char[][] board){
        List<String> list = new ArrayList<String>();
        for(int i= 0 ;i <board.length;i++){
            String s = new String(board[i]);
            list.add(s);
        }
        return list;
    }
    
}
