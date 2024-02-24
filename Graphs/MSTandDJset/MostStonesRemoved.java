package Graphs.MSTandDJset;

import java.util.HashMap;

/* 947. Most Stones removed with same row or column
{ https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/ } 

Example 1:  Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 */

public class MostStonesRemoved {
    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};

        System.out.println(removeStones(stones));
    }
    private static int removeStones(int[][] stones) {
        // Total stones - number of connected components
        int n = stones.length;

        UnionFind uf = new UnionFind(n);

        for(int i = 0 ; i < n ; i++){ // this is like a parent 
            for(int j = 0 ; j < n ; j++){ // traversing to all other nodes if any of them share a same column or row then we can consider there is an edge from parent to that node
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) 
                    uf.unionByRank(i,j);
            }
        }

        return n - uf.countComponents();
    }

    // Approach - 2 using DFS
    public int removeStone(int[][] stones) {
        boolean[] visited = new boolean[stones.length];
        int islands = 0;
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(stones[i], visited, stones);
                islands++;
            }
        }
        return stones.length - islands;
    }
    
    private void dfs(int[] stone, boolean[] visited, int[][] stones) {
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i] && (stone[0] == stones[i][0] || stone[1] == stones[i][1])) {
                visited[i] = true;
                dfs(stones[i], visited, stones);
            }
        }
    }// Tc -> O(N^2) , SC -> O(N)

    // Approach - 3 , playing with the index 
    public int maxRemove(int[][] stones, int n) {
        // int n = stones.length;
        int maxRow = 0 ; 
        int maxCol = 0 ;
        // since the rows and col`s will overlap , we will need to map the col index to the values which is not in the range of the row values
        for(int i = 0 ; i < n ; i++){
            maxRow = Math.max(maxRow , stones[i][0]);
            maxCol = Math.max(maxCol , stones[i][1]);
        }
        
        // col = 0  => maxRow + 1 ....
        UnionFind uf = new UnionFind(maxRow + maxCol + 1);
        HashMap<Integer,Integer> map = new HashMap<>();
        // usually in the parent array if parent[i] == i , then it is a single component 
        // But here we have initialized with maxRow + maxCol components , so if we follow the above line to find components , the ans will be wrong
        // So that only we are storing the valid nodes in the map        
        // So now check the above condition for the valid nodes only
        for(int i = 0 ; i < n ; i++){
            // if we are at a row . i then connect all the stones in that row , and the col
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1 ;
            uf.unionBySize(nodeRow,nodeCol); // all the nodes int the same row and col came under one component
            map.put(nodeRow,1);
            map.put(nodeCol,1);
        }
        
        int cnt = 0  ;
        for(int i : map.keySet()) if(uf.parent[i] == i) cnt++;
        
        return n - cnt;
        
    }
}

// solutions section they have did some trick on the index refer
// { https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/solutions/197668/count-the-number-of-islands-o-n/ }
 /*
    We need some way to separate the column and row, as a value j could be value of both some column 
    and row. And that's deadly because the dictionary we use for union-find has j as key and has no way
     to tell if j is being processed as row or col. So ~j or j+10000 is just a encode-decode function in
      some sense. All are explained in lee answer */
