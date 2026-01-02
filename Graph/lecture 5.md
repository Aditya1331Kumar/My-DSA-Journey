# BFS

- Given an undirected connected graph with V vertices numbered from 0 to V-1, the task is to implement both Depth First Search (DFS) and Breadth First Search (BFS) traversals starting from the 0th vertex. 
- adjacency list where adj[i] contains a list of vertices connected to vertex i. Visit nodes in the order they appear in the adjacency list.





```java

Input: V = 5, adj = [[2, 3, 1], [0], [0, 4], [0], [2]]

Output:[0, 2, 4, 3, 1], [0, 2, 3, 1, 4]

Explanation:

DFS: Start from vertex 0. Visit vertex 2, then vertex 4, backtrack to vertex 0, then visit vertex 3, and finally vertex 1. The traversal is 0 → 2 → 4 → 3 → 1.

BFS: Start from vertex 0. Visit vertices 2, 3, and 1 (in the order they appear in the adjacency list). Then, visit vertex 4 from vertex 2. The traversal is 0 → 2 → 3 → 1 → 4.
```
```java
Input: V = 4, adj = [[1, 3], [2, 0], [1], [0]]

Output: [0, 1, 2, 3], [0, 1, 3, 2]

Explanation:

DFS: Start from vertex 0. Visit vertex 1, then vertex 2, backtrack to vertex 0, then visit vertex 3. The traversal is 0 → 1 → 2 → 3.

BFS: Start from vertex 0. Visit vertices 1 and 3, then visit vertex 2 from vertex 1. The traversal is 0 → 1 → 3 → 2.
```


# soln


- Queue data structure: follows FIFO, and will always contain the starting.
- Visited array: an array initialized to 0
- In BFS, we start with a “starting” node, mark it as visited, and push it into the queue data structure.
- In every iteration, we pop out the node ‘v’ and put it in the solution vector, as we are traversing this node.
- All the unvisited adjacent nodes from ‘v’ are visited next and are pushed into the queue. The list of adjacent neighbors of the node can be accessed from the adjacency list.
- Repeat steps 2 and 3 until the queue becomes empty, and this way you can easily traverse all the nodes in the graph.

```java
import java.util.*;
class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, 
    ArrayList<ArrayList<Integer>> adj) {
        
        ArrayList < Integer > bfs = new ArrayList < > ();
        boolean vis[] = new boolean[V];
        Queue < Integer > q = new LinkedList < > ();

        q.add(0);
        vis[0] = true;

        while (!q.isEmpty()) {
            Integer node = q.poll();
            bfs.add(node);

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (Integer it: adj.get(node)) {
                if (vis[it] == false) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }

        return bfs;
    }
    
    public static void main(String args[]) {

        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);
        
        Solution sl = new Solution(); 
        ArrayList < Integer > ans = sl.bfsOfGraph(5, adj);
        int n = ans.size(); 
        for(int i = 0;i<n;i++) {
            System.out.print(ans.get(i)+" "); 
        }
    }
}
```


- Output: 0 1 4 2 3

- Time Complexity: O(N) + O(2E), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes.

- Space Complexity: O(3N) ~ O(N), Space for queue data structure visited array and an adjacency list