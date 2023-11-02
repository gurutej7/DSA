package Recursion.Backtracking;
public class NQueens{
    public static void main(String[] args) {
        int n = 4;
        boolean board [][] = new boolean[n][n];
        System.out.println(nQueens(board,0));
        
    }

    public static int nQueens(boolean[][] board , int row){
        if(row == board.length){
            //Function to display the board
            show(board);
            System.out.println();
            return 1;
        }
        int count = 0 ;
        for(int col = 0 ; col < board.length; col++){
            if(isSafe(board,row,col)){
                //mark the place as Queen is placed
                board[row][col] = true;
                count += nQueens(board, row+1);
                //Restore the changes that you made while returning
                board[row][col] = false;
            }
        }
        return count;
    }
    //Function to check if the current position is safe to place the queen or not
    private static boolean isSafe(boolean[][] board, int row, int col) {
        //check vertically up
        for(int i = 0 ;i <row ; i++){
            if(board[i][col]) return false;
        }
        //Check Diagonal left
        int maxleft = Math.min(row,col);
        for (int i = 1; i <= maxleft; i++) {
            //both row and column are deceasing
            if(board[row-i][col-i]) return false;
        }
        //Check Diagonal Right
        int maxRight = Math.min(row, board.length-1-col);
        for (int i = 1; i <=maxRight; i++) {
            //row is decreasing column is increasing
            if(board[row-i][col+i]) return false;
            
        }
        //No need to check below because we are placing row wise from top to bottom
        //So there would be no Queens placed below the current Queen
        return true;
    }

    private static void show(boolean[][] board) {
        for(boolean[] row : board){
            for(boolean box : row){
                //Queen is placed
                if(box)System.out.print("Q ");
                //Queen is not placed
                else System.out.print("_ ");
            }
            System.out.println();
        }
        
    }
}