import java.util.ArrayList;

/*  GFG
  Rat in a Maze Problem - I
Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to reach the 
destination at (N - 1, N - 1). Find all possible paths that the rat can take to reach from
 source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 
 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked 
 and rat cannot move to it while value 1 at a cell in the matrix represents that rat can
  be travel through it.
Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell.
Example 1:
Input:
N = 4
m[][] = {{1, 0, 0, 0},
         {1, 1, 0, 1}, 
         {1, 1, 0, 0},
         {0, 1, 1, 1}}
Output:
DDRDRR DRDDRR

 */

public class RatInaMaze {
    public static void main(String[] args) {
        int m[][] = {{1, 0, 0, 0},
         {1, 1, 0, 1}, 
         {1, 1, 0, 0},
         {0, 1, 1, 1}};
        int n = 4;
        
        System.out.println(findPath(m, n));
        
    }
    
    public static ArrayList<String> findPath(int[][] m, int n) {
        // Your code here
        ArrayList<String> ans = new ArrayList<>();
         if(m[0][0] == 0) return ans;
         //We should maintain a visited array So that we wont get back to the same path we came from
        boolean vis [][] = new boolean[n][n];
        solve(0,0,vis,m,ans,"");
        return ans;
    }
    

    public static void solve(int row,int col,boolean [][] vis,int[][] m , ArrayList<String> ans,String path){
        //Base condition when we have reached the destination cell
        if(row == m.length-1 && col == m.length-1){
            ans.add(path);
            return;
        }

        //Mark the current index as visited
        vis[row][col] = true;
        
        //Down (Increase row by one if it is inside bound and not visited yet)
        if(row < m.length-1 && m[row+1][col]==1 && !vis[row+1][col])
        solve(row+1,col,vis,m,ans,path +'D');
        //Right
        if(col < m.length-1 && m[row][col+1]==1 && !vis[row][col+1])
        solve(row,col+1,vis,m,ans,path+'R');
         //Up
        if(row >0  && m[row-1][col]==1 && !vis[row-1][col])
        solve(row-1,col,vis,m,ans,path+'U');
          //Left
        if(col >0 && m[row][col-1]==1 && !vis[row][col-1])
        solve(row,col-1,vis,m,ans,path+'L');
        //restore the changes made by this call   
        vis[row][col] = false;
        return;
        
        
    }
}
