# Connected Components



### Given a undirected Graph consisting of V vertices numbered from 0 to V-1 and E edges. The ith edge is represented by [ai,bi], denoting a edge between vertex ai and bi. We say two vertices u and v belong to a same component if there is a path from u to v or v to u. Find the number of connected components in the graph.



### A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

```java
Examples:


Input: V=4, edges=[[0,1],[1,2]]

Output: 2

Explanation: Vertices {0,1,2} forms the first component and vertex 3 forms the second component.

```
```java
Input: V = 7, edges = [[0, 1], [1, 2], [2, 3], [4, 5]]

Output: 3

Explanation:

The edges [0, 1], [1, 2], [2, 3] form a connected component with vertices {0, 1, 2, 3}.

The edge [4, 5] forms another connected component with vertices {4, 5}.

Therefore, the graph has 3 connected components: {0, 1, 2, 3}, {4, 5}, and the isolated vertices {6} (vertices 6 and any other unconnected vertices).
```

## soln:

## 🔹 Approach (DFS)

1. Build an **adjacency list** from given edges.
2. Keep a **visited\[] array**.
3. For each vertex `i` from `0 to V-1`:

   * If it’s **not visited**, start a DFS/BFS → this explores one **connected component**.
   * Increment `count` of components.
4. Return `count`.

---

## 🔹 Java Code (DFS)

```java
import java.util.*;

class ConnectedComponents {
    // DFS function
    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    public static int countComponents(int V, int[][] edges) {
        // Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u); // since undirected
        }

        boolean[] visited = new boolean[V];
        int components = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited);
                components++;
            }
        }

        return components;
    }

    public static void main(String[] args) {
        int V1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}};
        System.out.println(countComponents(V1, edges1)); // Output: 2

        int V2 = 7;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {4, 5}};
        System.out.println(countComponents(V2, edges2)); // Output: 3
    }
}
```

---

## 🔹 Output

```
2
3
```

---

👉 This solution runs in **O(V + E)** (DFS traversal).
We can also solve using **BFS** or **Disjoint Set Union (DSU/Union-Find)**.


## 🔹 Approach (BFS)

1. Build adjacency list from edges.
2. Maintain a `visited[]` array.
3. For every node `i` from `0 → V-1`:

   * If not visited → start a **BFS** from it.
   * BFS will mark all vertices in that connected component.
   * Increment the `components` count.

---

## 🔹 Java Code (BFS)

```java
import java.util.*;

class ConnectedComponentsBFS {
    // BFS function
    private static void bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    public static int countComponents(int V, int[][] edges) {
        // Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u); // undirected
        }

        boolean[] visited = new boolean[V];
        int components = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfs(i, adj, visited);
                components++;
            }
        }

        return components;
    }

    public static void main(String[] args) {
        int V1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}};
        System.out.println(countComponents(V1, edges1)); // Output: 2

        int V2 = 7;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {4, 5}};
        System.out.println(countComponents(V2, edges2)); // Output: 3
    }
}
```

---

## 🔹 Output

```
2
3
```

---

- BFS version is also **O(V + E)** time and **O(V + E)** space (for adjacency list).