## 1. Bridges in Graph
* An edge is called as a bridge , if on removal of that edge the graph breaks down into 2 or more components.
* [Problem](https://leetcode.com/problems/critical-connections-in-a-network/description/)
* [video reference](https://www.youtube.com/watch?v=qrAub5z8FeA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=55)

```java
public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < n ; i++) adj.add(new ArrayList<>());
        // construct adjacency
        for(List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1); // edge  u <--> v
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int tin[] = new int[n] ; // time of insertion, during the dfs
        int low[] = new int[n] ; // min time of insertion, that my adjacent nodes have (apart from parent)
        boolean vis[] = new boolean[n];
        List<List<Integer>> res = new ArrayList<>(); // to store the bridges

        for(int i = 0 ; i < n ; i++){
            if(!vis[i]) 
                dfs(i,-1,1,tin,low,vis,adj,res);
        }
        return res;
    }

    private void dfs(int node,int parent,int timer,int[]tin,int[] low , boolean vis[] , ArrayList<ArrayList<Integer>> adj , List<List<Integer>> res){
        tin[node] = timer;
        low[node] = timer;
        vis[node] = true;
        timer++;
        for(int adjNode : adj.get(node)){
            if(adjNode == parent) continue;
            if(!vis[adjNode]){
                dfs(adjNode,node,timer,tin,low,vis,adj,res);
                // update lowest , i.e, if the nodes below this(curr) can be reached in 'x' steps means then curr can also be reached in x steps
                // low[node] , should have the minimum steps to reach node , if x is less than , already existing low , then update it
                low[node] = Math.min(low[node],low[adjNode]);
                // If the time taken to reach a node is less , than the lowest time taken to reach the adjNode
                // Meaning if we break that edge , then we can`t reach that adjNode , resulting in a separate component
                if(tin[node] < low[adjNode]) res.add(Arrays.asList(node,adjNode)); // if we break the edge , the adjNode will never be able to reach that node
            }
            // even though it is visited , update low[node]
            else  low[node] = Math.min(low[node],low[adjNode]);
        }
    }
    // TC => O(V+2E)
```

* `tin[]` time of insertion , the step at which we reach a node during the dfs.
* `low[]` min, lowest time of insertion of all adjacent nodes, apart from parent.

## 2. Articulation Point in Graph
* Nodes on whose removal the graph breaks down into multiple components.
* [video referenece](https://www.youtube.com/watch?v=j1QDfU21iZk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=57)