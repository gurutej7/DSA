package Graphs.BFSorDFS;

import java.util.LinkedList;
import java.util.Queue;

/* 1020. Number of Enclaves
{ https://leetcode.com/problems/number-of-enclaves/description/ } 
Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 */
public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0},
                        {1,0,1,0},
                        {0,1,1,0},
                        {0,0,0,0}};

        System.out.println(numEnclaves(grid));
        System.out.println(numEnclavesDFS(grid));

    }

    private static int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        boolean vis[][] = new boolean[n][m];

        // BFS with multiple starting points , will start from all the cells which are
        // one
        // and go to nodes which are connected to them and has a value 1 and mark them
        // as visisted
        // after all the visisting if there are 1`s which are not visisted from the
        // border then they are enclaves
        // top row
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1 && !vis[0][j]) {
                q.offer(new Pair(0, j));
                vis[0][j] = true;
            }
            // bottom row
            if (grid[n - 1][j] == 1 && !vis[n - 1][j]) {
                q.offer(new Pair(n - 1, j));
                vis[n - 1][j] = true;
            }
        }

        // left column
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1 && !vis[i][0]) {
                q.offer(new Pair(i, 0));
                vis[i][0] = true;
            }
            // right column
            if (grid[i][m - 1] == 1 && !vis[i][m - 1]) {
                q.offer(new Pair(i, m - 1));
                vis[i][m - 1] = true;
            }
        }
        int delRow[] = { 1, 0, -1, 0 };
        int delCol[] = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int r = curr.getFirst();
            int c = curr.getSecond();

            for (int i = 0; i < 4; i++) {
                int row = delRow[i] + r;
                int col = delCol[i] + c;
                if (row >= 0 && col >= 0 && row < n && col < m && !vis[row][col] && grid[row][col] == 1) {
                    vis[row][col] = true;
                    q.offer(new Pair(row, col));
                }
            }

        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j])
                    cnt++;
            }
        }

        return cnt;
    }
    // Sc = O(N*M) + O(N)
    // TC = O(N*M) * 4

    // DFS solution
    private static int numEnclavesDFS(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        // observation is if there is O and if it is in any path connected to a 'O' on
        // the boundary then it cant be surrounded
        // but for every 'O' checking if it it is connected to 'O' on the boundary takes
        // time
        // so reverse engineering
        // Traverse the boundaries if you find any 'O' then mark all it`s connected 'O's
        // as cannot be surrounded
        boolean vis[][] = new boolean[n][m];
        // we will check the boundaries , if there is a 'O'
        // top row
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 1 && !vis[0][j])
                dfs(board, vis, 0, j);
            // bottom row
            if (board[n - 1][j] == 1 && !vis[n - 1][j])
                dfs(board, vis, n - 1, j);
        }

        // left column
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 1 && !vis[i][0])
                dfs(board, vis, i, 0);
            // right column
            if (board[i][m - 1] == 1 && !vis[i][m - 1])
                dfs(board, vis, i, m - 1);
        }

        // now traverse through the board if there exist any 'O' which is not visited
        // meaning it is not connected to a boundary 'O' , change all such them to X

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && !vis[i][j])
                    cnt++;
            }
        }

        return cnt;

    }

    static int delRow[] = { 1, 0, -1, 0 };
    static int delCol[] = { 0, 1, 0, -1 };

    private static void dfs(int[][] board , boolean[][] vis , int r , int c){
        vis[r][c] = true;
        for(int i = 0 ; i < 4 ; i++){
            int row = delRow[i]+r;
            int col = delCol[i]+c;
            if(row >=0 && col >= 0 && row < board.length && col < board[0].length && !vis[row][col] && board[row][col] == 1)
                dfs(board,vis,row,col);
        }
    }

}
