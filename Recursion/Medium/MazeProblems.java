package Recursion.Medium;

import java.util.ArrayList;

public class MazeProblems {
    public static void main(String[] args) {
        System.out.println(countMazePaths(0, 0, 3));
        printMazePaths(0, 0, 3, "");
        System.out.println(mazePathsReturn(0, 0, 3, ""));
        
    }
    // Function to count paths to reach the end
    // We can either Move Right or Down
    // r=row and c=column
    //initially r,c = 0;
    //If we have reached n-1 then we have found one path
    public static int countMazePaths(int r, int c, int n ){
        if(r==n-1 || c==n-1) return 1;
        //to move right increase col by one
        int right = countMazePaths(r, c+1, n);
        //to move down increase row by one
        int down = countMazePaths(r+1, c, n);
        //total paths is sum of right + down
        return right+down;

    }
    //To print the path
    //R = move right and D = move Down
    public static void printMazePaths(int r, int c, int n,String path ){
        if(r==n-1 && c==n-1){
            System.out.println(path);
            return;
        }
        //to move right increase col by one
        if(c<n)printMazePaths(r, c+1, n,path+'L');
        if(r<n)printMazePaths(r+1,c, n,path+'D');
        
    }
    //Returning paths in the form of a ArrayList
    public static ArrayList<String> mazePathsReturn(int r,int c,int n,String path){
        if(r==n-1 && c==n-1){
           ArrayList<String> ans = new ArrayList<>();
           //When you find one of the ans return it in the form of a list
           ans.add(path);
           return ans;

        }
        // Local list to store the ans`s made from this function
         ArrayList<String> ansList = new ArrayList<>();
        //add the ans from the further calls to the list and return it to above calls
        if(c<n)ansList.addAll(mazePathsReturn(r, c+1, n, path+'R'));
        if(r<n)ansList.addAll(mazePathsReturn(r+1, c, n, path+'L'));

        return ansList;
    }
    
}
