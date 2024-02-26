package Graphs.SCC;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class Kosaraju{
    ArrayList<ArrayList<Integer>> adj , revAdj;
    int n,scc;
    Stack<Integer> st; // Used to get the order of the nodes, according to their finishing time
    boolean[] vis1, vis2; 
    int componentNumber[]; // used to know which node belongs to which component , useful for printing the SCC`s

    // 0 based nodes
    public Kosaraju(int n , int[][] directedEdges){
        this.n = n ;
        this.scc = 0;
        adj = new ArrayList<>();
        revAdj = new ArrayList<>();
        vis1 = new boolean[n];
        vis2 = new boolean[n];
        st = new Stack<>();
        componentNumber = new int[n];
       

        for(int i = 0 ; i <  n ; i++){
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }

        // construct adj and rev Adj
        for(int edge[] : directedEdges){
            int u = edge[0];
            int v = edge[1]; // edge =>  u-->v
            adj.get(u).add(v);
            revAdj.get(v).add(u);
        }

        for(int i = 0 ; i < n ; i++){
            if(!vis1[i]) dfs1(i);
        }

        while(!st.isEmpty()){
            int curr = st.peek();
            st.pop();
            if(!vis2[curr]){
                scc++;
                dfs2(curr);
            }
        }

    }

    private void dfs1(int node){
        vis1[node] = true;

        for(int adjNode : adj.get(node)){
            if(!vis1[adjNode]) dfs1(adjNode);
        }

        st.push(node);
    }

    private void dfs2(int node){
        vis2[node] = true;

        for(int adjNode : revAdj.get(node)){
            if(!vis2[adjNode]) dfs2(adjNode);
        }
        componentNumber[node] = scc; // assigning the component nnumber to all the nodes in the current component
    }

    // returns the number of strongly connected componenets
    public int numberOfStronglyConnectedComponents(){
        return scc;
    }

    public List<List<Integer>> allStronglyConnectedComponents(){
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<scc;i++) res.add(new ArrayList<>());
        for(int i=0;i<n;i++){
            res.get(componentNumber[i]-1).add(i);
        }
        return res;
    }

}

// Tc - O(V+E)

// problem -> { https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1 }