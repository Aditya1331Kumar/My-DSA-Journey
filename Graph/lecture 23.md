Detect a cycle in a directed graph

Mark as Completed

0


Problem Statement: Given a directed graph with V vertices labeled from 0 to V-1. The graph is represented using an adjacency list where adj[i] lists all nodes connected to node. Determine if the graph contains any cycles.

Examples
Input: V = 6, adj= [[1], [2, 5], [3], [4], [1], []]
Output: True
Explanation: The graph contains a cycle: 1 -> 2 -> 3 -> 4 -> 1.

Input:V = 4, adj= [[1,2], [2], [], [0,2]]
Output: False
Explanation: The graph does not contain a cycle.
            
Disclaimer: Here is the practice link to help you assess your knowledge better. It's highly recommend trying to solve it before looking at the solution.

Approach
Algorithm
A cycle in a directed graph can also be detected using BFS (Kahn’s Algorithm). The key idea is that in a Directed Acyclic Graph (DAG), we can generate a valid topological ordering that includes all vertices. If there is a cycle, at least one vertex will never have an in-degree of 0, and we will not be able to process all vertices.
Compute the in-degree (number of incoming edges) for every vertex.
Push all vertices with zero incoming edges into a queue.
While the queue is not empty:
Remove a vertex and mark it as processed.
For each neighbor of this vertex, reduce its in-degree.
If any neighbor’s in-degree becomes 0, push it into the queue.
If all the verteices are processed once, then there is no cycle.
If some vertices were not processed, it means a cycle exists.
Image 1
Image 2
Image 3
Image 4


Code
Cpp
Java
Python
Javascript


import java.util.*;

class Solution {
    // Function to detect a cycle in a directed graph
    public boolean isCyclic(int V, List<List<Integer>> adj) {
        // Initialize indegree array to store incoming edge count
        int[] indegree = new int[V];

        // Calculate indegree of each node
        for (int i = 0; i < V; i++) {
            for (int nbr : adj.get(i)) {
                indegree[nbr]++;
            }
        }

        // Initialize queue with nodes having 0 indegree
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        // Counter to track processed nodes
        int count = 0;

        // Perform BFS using Kahn's algorithm
        while (!q.isEmpty()) {
            // Get current node
            int node = q.poll();

            // Increment processed node count
            count++;

            // Reduce indegree of neighbors
            for (int nbr : adj.get(node)) {
                indegree[nbr]--;

                // If indegree becomes 0, add to queue
                if (indegree[nbr] == 0)
                    q.add(nbr);
            }
        }

        // Return true if cycle exists
        return count != V;
    }
}

public class Main {
    public static void main(String[] args) {
        // Number of vertices
        int V = 4;

        // Adjacency list representing directed graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        // Add edges
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1); 

        // Create Solution object
        Solution sol = new Solution();

        // Call isCyclic and print result
        System.out.println(sol.isCyclic(V, adj) ? "Graph contains a cycle" : "No cycle");
    }
}
Complexity Analysis
Time Complexity: O(V+E), Each vertex and edge is processed exactly once while calculating in-degrees and during the BFS traversal.
Space Complexity: O(V+E), We store the adjacency list, an in-degree array and a queue.
