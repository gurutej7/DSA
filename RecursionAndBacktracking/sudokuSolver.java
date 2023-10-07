
//37 . Sudoku Solver

/*
 Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

1) Each of the digits 1-9 must occur exactly once in each row.
2) Each of the digits 1-9 must occur exactly once in each column.
3) Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 */

public class sudokuSolver {
    public static void main(String[] args) {
        char [] [] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(solve(board,0));
        System.out.println();
        showBoard(board);
        
    }
    public void solveSudoku(char[][] board) {
            if(board == null || board.length == 0) return;
            solve(board,0);
        }
        public static boolean solve(char[][] board , int row){
            if(row == 9) return true;
            for(int i = row ; i<9 ; i++){
                for(int j = 0; j<9 ; j++){
                    if(board[i][j] == '.'){
                        for(char ch = '1' ; ch<='9' ; ch++){
                            if(isValid(board,i,j,ch)){
                                board[i][j] = ch;
                                if(solve(board,i)) return true;
                                else board[i][j] ='.';      
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }
         private static boolean isValid(char[][] board, int row, int col, char ch){
            for(int i = 0; i < 9; i++) {
               if(board[row][i] == ch) return false;
               if(board[i][col] == ch) return false;
               if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == ch)
               return false;
    
            }
            return true;
        }

        private static void showBoard(char[][] board) {
            for (char[] row : board) {
                for (char ch : row) {
                    System.out.print(ch + " ");
                }
                System.out.println();
            }
        }
    
}


        
        

