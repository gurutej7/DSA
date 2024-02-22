package Graphs.MSTandDJset;

public class UnionFind{
    int[] rank;
    int[] parent;
    int[] size;
    int numOfComponents;

    public UnionFind(int n){
        this.parent = new int[n+1];
        this.rank = new int[n+1];
        this.size = new int[n+1];
        this.numOfComponents = n ;

        for(int i=0;i<=n;i++){ // i<=n , to make it work for both 1 based and 0 based graphs
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1 ;

        }
    }
    public int find(int u){ // find Ultimate parent and path compression
        if(u == parent[u]) return u;
        return parent[u] = find(parent[u]);
    }

    public boolean isConnected(int x, int y){ // does two nodes belong to the same component or not
        return find(x) == find(y);
    }

    public boolean unionByRank(int u, int v){ // union by rank
        int pu = find(u); // ultimate parent of u
        int pv = find(v); // ultimate parent of v
        if(pu == pv) return false; // if already they have a same parent
        if(rank[pu] > rank[pv]) parent[pv] = pu;
        else if(rank[pv] > rank[pu]) parent[pu] = pv;
        else{ // if both the ranks are equal then we can combine 
            rank[pu]++;
            parent[pv] = pu;
        }
        numOfComponents--; // when one component is attached to another , total decreases
        return true;

    }
    public boolean onlyOneRoot(){
        int root1 = find(0);
        for(int i=1;i<parent.length;i++) if(root1!=find(i)) return false;
        return true;
    }

    public boolean unionBySize(int u , int v){
        int pu = find(u);
        int pv = find(v);
        if(pu == pv) return false; 

        if(size[pv] > size[pu]){
            parent[pu] = pv;
            size[pv] += size[pu];
        }
        // else if(size[pu] > size[pv]){
        //     parent[pv] = pu ;
        //     size[pu] += size[pv];
        // }
        else{
            size[pu] += size[pv];
            parent[pv] = pu;
        }
        return true;
    }

    public int countComponents(){
        return this.numOfComponents;
    }
    // While using Union find either use by rank or by size 
}
// Time complexity of one operation O(4*alpha)

// https://leetcode.com/problems/number-of-provinces/description/

