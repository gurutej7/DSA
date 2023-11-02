package Recursion.Backtracking;

public class MazePaths {
    public static void main(String[] args) {
        boolean maze[][] = {
            {true,true,true},
            {true,true,true},
            {true,true,true}
        };
        System.out.println(countPaths(0,0,3,maze));
        printmazePaths(0, 0, 3, maze, "");
    }
    //Function to return total number of paths
    public static int countPaths(int r,int c,int n,boolean[][] maze){
        if(r==n-1 && c==n-1) return 1;
        //If the current cell is already visited just return zero
        if(maze[r][c]==false) return 0;
        // Else if you are visiting the cell for the first time mark it as visited
        maze[r][c] = false;

        //Move right increase col by one while col is less than n-1
        int left = 0;
        int right = 0;
        int up = 0 ;
        int down = 0;
        if(c<n-1){
            right= countPaths(r, c+1, n, maze);
        }
        //Mave down increase row by one
        if(r<n-1){
            down= countPaths(r+1, c, n, maze);
        }
        //Move Up decrease row by 1 while row >0
        if(r>0){
            up = countPaths(r-1, c, n, maze);
        }
        //Move Left decrease col by one
        if(c>0){
            left= countPaths(r, c-1, n, maze);
        }
        //After completing the function call and while returning , 
        //restore the changes that you made in that particular function call
        maze[r][c] = true;
        return  right+down+up+ left;
    }
    public static void printmazePaths(int r,int c,int n,boolean[][] maze,String path){
        if(r==n-1 && c==n-1) {
            System.out.println(path);
            return;
        }

        //If the current cell is already visited just return zero
        if(maze[r][c]==false) return ;
        // Else if you are visiting the cell for the first time mark it as visited
        maze[r][c] = false;

        //Move right increase col by one while col is less than n-1
        
        if(c<n-1){
           printmazePaths(r, c+1, n, maze,path+'R');
        }
        //Mave down increase row by one
        if(r<n-1){
            printmazePaths(r+1, c, n, maze,path+'D');
        }
        //Move Up decrease row by 1 while row >0
        if(r>0){
            printmazePaths(r-1, c, n, maze,path+'U');
        }
        //Move Left decrease col by one
        if(c>0){
           printmazePaths(r, c-1, n, maze,path+'L');
        }
        //After completing the function call and while returning , 
        //restore the changes that you made in that particular function call
        maze[r][c] = true;
        return ;
    }

    
}
