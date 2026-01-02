# leetcode 547


***good questons***

# soln:

```java
import java.util.*;

class Solution {
    // dfs traversal function 
    private static void dfs(int node, 
       ArrayList<ArrayList<Integer>> adjLs , 
       int vis[]) {
        vis[node] = 1; 
        for(Integer it: adjLs.get(node)) {
            if(vis[it] == 0) {
                dfs(it, adjLs, vis); 
            }
        }
    }
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<ArrayList<Integer>>(); 
        for(int i = 0;i<V;i++) {
            adjLs.add(new ArrayList<Integer>()); 
        }
        
        // to change adjacency matrix to list 
        for(int i = 0;i<V;i++) {
            for(int j = 0;j<V;j++) {
                // self nodes are not considered 
                if(adj.get(i).get(j) == 1 && i != j) {
                    adjLs.get(i).add(j); 
                    adjLs.get(j).add(i); 
                }
            }
        }
        int vis[] = new int[V]; 
        int cnt = 0; 
        for(int i = 0;i<V;i++) {
            if(vis[i] == 0) {
               cnt++;
               dfs(i, adjLs, vis); 
            }
        }
        return cnt; 
    }
    public static void main(String[] args)
    {

        // adjacency matrix 
        ArrayList<ArrayList<Integer> > adj = new ArrayList<ArrayList<Integer> >();

        adj.add(new ArrayList<Integer>());
        adj.get(0).add(0, 1);
        adj.get(0).add(1, 0);
        adj.get(0).add(2, 1);
        adj.add(new ArrayList<Integer>());
        adj.get(1).add(0, 0);
        adj.get(1).add(1, 1);
        adj.get(1).add(2, 0);
        adj.add(new ArrayList<Integer>());
        adj.get(2).add(0, 1);
        adj.get(2).add(1, 0);
        adj.get(2).add(2, 1);
                
        Solution ob = new Solution();
        System.out.println(ob.numProvinces(adj,3));
    }
}
```

- Output: 2

- Time Complexity: O(N) + O(V+2E), Where O(N) is for outer loop and inner loop runs in total a single DFS over entire graph, and we know DFS takes a time of O(V+2E). 

- Space Complexity: O(N) + O(N),Space for recursion stack space and visited array.


# asli soln:

```java
import java.util.*;

class Solution {
    // DFS traversal
    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        visited[node] = 1;
        for (Integer neighbor : adjList.get(node)) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, adjList, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;

        // Step 1: Convert adjacency matrix to adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        // Step 2: Visited array
        int[] visited = new int[V];
        int provinces = 0;

        // Step 3: Run DFS for each unvisited node
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                provinces++;
                dfs(i, adjList, visited);
            }
        }

        return provinces;
    }
}
```

# most optimised
```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];
        
        // Initialize parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // Union operation for all connections
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        
        // Count unique parents
        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) provinces++;
        }
        return provinces;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]); // path compression
        }
        return parent[x];
    }
    
    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}


```


# dfs
```java

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[city][j] == 1 && !visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }
}
```

- Time: O(n^2) in worst case (kyonki for each visited node we scan its row of length n; with adjacency matrix input this becomes n×n).

- Space: O(n) for visited + O(n) recursion stack in worst case.


# gyan 

### copying a matrix
```java
int[][] copy = new int[original.length][];
for (int i = 0; i < original.length; i++) {
    copy[i] = Arrays.copyOf(original[i], original[i].length);
}
```