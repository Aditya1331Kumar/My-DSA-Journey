# Question:
* Determine if the graph contains any cycles.
# Approach:

### The algorithm steps are as follows:

* In the DFS function call make sure to store the parent data along with the source node, create a visited array, and initialize to 0. The parent is stored so that while checking for re-visited nodes, we don’t check for parents. 
* We run through all the unvisited adjacent nodes using an adjacency list and call the recursive dfs function. Also, mark the node as visited.
* If we come across a node that is already marked as visited and is not a parent node, then keep on returning true indicating the presence of a cycle; otherwise return false after all the adjacent nodes have been checked and we did not find a cycle.
### NOTE: We can call it a cycle only if the already visited node is a non-parent node because we cannot say we came to a node that was previously the parent node

### 🔹 Snippet 1 (DFS function)

```cpp
bool dfs(int node, int parent) {
    vis[node] = 1;
    // visit the neighbours
    for (auto it : adj[node]) {
        if (vis[it] == 0) {
            if (dfs(it, node) == true)
                return true;
        }
        // already visited but is not parent node
        else if (it != parent)
            return true;
    }
    // not a cycle
    return false;
}
```




* A graph can have connected components as well.

---

### 🔹 Snippet 2 (Checking connected components)

```cpp
// check for connected components in a graph
for (int i = 1; i <= n; i++) {
    if (!vis[i]) {
        if (dfs(i, -1) == true)
            return true;
    }
}
return false;
```


# soln:
```java
import java.util.*;

class Solution {
    private boolean dfs(int node, int parent, int vis[], ArrayList<ArrayList<Integer>> 
    adj) {
        vis[node] = 1; 
        // go to all adjacent nodes
        for(int adjacentNode: adj.get(node)) {
            if(vis[adjacentNode]==0) {
                if(dfs(adjacentNode, node, vis, adj) == true) 
                    return true; 
            }
            // if adjacent node is visited and is not its own parent node
            else if(adjacentNode != parent) return true; 
        }
        return false; 
    }
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
       int vis[] = new int[V]; 
       for(int i = 0;i<V;i++) {
           if(vis[i] == 0) {
               if(dfs(i, -1, vis, adj) == true) return true; 
           }
       }
       return false; 
    }
    public static void main(String[] args)
    {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
                
        Solution obj = new Solution();
        boolean ans = obj.isCycle(4, adj);
        if (ans)
            System.out.println("1");    
        else
            System.out.println("0");
    }

}
```
```java
Output:  0
```

* Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.

* Space Complexity: O(N) + O(N) ~ O(N), Space for recursive stack space and visited array.

* Special thanks to Vanshika Singh Gour for contributing to this article on takeUforward.

