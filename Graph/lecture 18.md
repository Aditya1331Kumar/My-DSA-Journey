# Bipartite Graph | DFS Implementation | leetcode 785

* bipartite graph is a graph which can be coloured using 2 colours such that no adjacent nodes have the same colour.
* Any linear graph with no cycle is always a bipartite graph.
- any graph with an even cycle length can also be a bipartite graph
- any graph with an odd cycle length can never be a bipartite graph

- If at any moment of traversal, we find the adjacent nodes to have the same colour, it means that there is an odd cycle, or it cannot be a bipartite graph. 

# approach:

*  visited array but in this case, instead of a visited array, we will take a colour array where all the nodes are initialised to -1 indicating they are not coloured yet

- We will try to colour with 0 and 1, but you can choose other colours as well

- If at any moment, we get an adjacent node from the adjacency list which is already coloured and has the same colour as the current node


# soln:
```Java
import java.util.*;

class Solution
{
    private boolean dfs(int node, int col, int color[], 
    ArrayList<ArrayList<Integer>>adj) {
        
        color[node] = col; 
        
        // traverse adjacent nodes
        for(int it : adj.get(node)) {
            // if uncoloured
            if(color[it] == -1) {
                if(dfs(it, 1 - col, color, adj) == false) return false; 
            }
            // if previously coloured and have the same colour
            else if(color[it] == col) {
                return false; 
            }
        }
        
        return true; 
    }
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        int color[] = new int[V];
	    for(int i = 0;i<V;i++) color[i] = -1; 
	    
	    // for connected components
	    for(int i = 0;i<V;i++) {
	        if(color[i] == -1) {
	            if(dfs(i, 0, color, adj) == false) return false; 
	        }
	    }
	    return true; 
    }
     public static void main(String[] args)
    {
        // V = 4, E = 4
        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        Solution obj = new Solution();
        boolean ans = obj.isBipartite(4, adj);
        if(ans)
            System.out.println("1");
        else System.out.println("0");
    }

}
```
```java
Output:  0
```

* Time Complexity: O(V + 2E), Where V = Vertices, 2E is for total degrees as we traverse all adjacent nodes.

* Space Complexity: O(3V) ~ O(V), Space for DFS stack space, colour array and an adjacency list.


# for leetcode
```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int V= graph.length;
        int color[] = new int[V];
	    for(int i = 0;i<V;i++) color[i] = -1; 
	    
	    // for connected components
	    for(int i = 0;i<V;i++) {
	        if(color[i] == -1) {
	            if(dfs(i, 0, color, graph) == false) return false; 
	        }
	    }
	    return true; 
    }


    
 private boolean dfs(int node, int col, int color[], int[][] graph) {
        
        color[node] = col; 
        
        // traverse adjacent nodes
        for(int it : graph[node]) {
            // if uncoloured
            if(color[it] == -1) {
                if(dfs(it, 1 - col, color, graph) == false) return false; 
            }
            // if previously coloured and have the same colour
            else if(color[it] == col) {
                return false; 
            }
        }
        
        return true; 
    }
      

}

```
# bfs
```java
import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!check(i, graph, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean check(int start, int[][] graph, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : graph[node]) {
                if (color[nei] == -1) {
                    color[nei] = 1 - color[node];
                    q.add(nei);
                } else if (color[nei] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```