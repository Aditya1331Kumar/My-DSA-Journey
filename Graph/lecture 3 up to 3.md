# lecture up to 6
here are two types of data structures

### Linear

- arrays, stacks, queues, and linked lists
- They are called linear because data elements are arranged in a linear or sequential manner.

### Non - linear 
- Tree.
- tree is a special type of graph with some restrictions
- Graphs 
- A graph is a non-linear data structure consisting of nodes that have data and are connected to other nodes through edges.

### Nodes
- circles represented by numbers. Nodes are also referred to as vertices. They store the data. The numbering of the nodes can be done in any order, no specific order needs to be followed.

### edges
- Two nodes are connected by a horizontal line called Edge. Edge can be directed or undirected. Basically, pairs of vertices are called edges.

### Types of Graphs


1. An undirected graph
- graph where edges are bidirectional, with no direction associated with them, i.e, there will be an undirected edge. In an undirected graph, the pair of vertices representing any edge is unordered. Thus, the pairs (u, v) and (v, u) represent the same edge.

2. A directed graph
- graph where all the edges are directed from one vertex to another, i.e, there will be a directed edge. It contains an ordered pair of vertices. It implies each edge is represented by a directed pair <u, v>. Therefore, <u, v> and <v, u> represent two different edges.


### Structure of Graph 
- Does every graph have a cycle? The answer is No!


- does not form a cycle but still, it is a graph. 

- binary tree. It can also be called a graph

- graph  an enclosed structure , open structure as well. 



- If there is at least one cycle present in the graph then it is called an Undirected Cyclic Graph.

-  directed graph is not cyclic as we can’t start from a node and end at the same node. Hence it is called Directed Acyclic Graph, commonly called DAG.



- If we just add an edge to the directed graph, then at least one cycle is present in the graph, hence it becomes Directed Cyclic Graph.

### path
- path contains a lot of nodes and each of them is reachable.

- not a path, because a node can’t appear twice in a path.

- adjacent nodes must have an edge and there is no edge between 1 and 3

### Degree of Graph

- It is the number of edges that go inside or outside that node.

- For undirected graphs, the degree is the number of edges attached to a node.



### Property: It states that the total degree of a graph is equal to twice the number of edges. This is because every edge is associated/ connected to two nodes.



- Total Degree of a graph = 2 x E


### For directed graphs
- The indegree of a node is the number of incoming edges
- outdegree of a node is the number of outgoing edges.

### Edge Weight
- A graph may have weights assigned on its edges. It is often referred to as the cost of the edge.

- If weights are not assigned then we assume the unit weight,

### Input Format 
- The first line contains two space-separated integers n and m denoting the number of nodes and the number of edges respectively. 
- Next m lines contain two integers u and v representing an edge between u and v. 
- In the case of an undirected graph if there is an edge between u and v, it means there is an edge between v and u as well. 

### Graph Representations

1.Adjacency Matrix
- An adjacency matrix of a graph is a two-dimensional array of size n x n, where n is the number of nodes in the graph, with the property that a[ i ][ j ] = 1 if the edge (vᵢ, vⱼ) is in the set of edges, and a[ i ][ j ] = 0 if there is no such edge.

#### Input:
5 6
1 2
1 3
2 4
3 4 
3 5 
4 5

#### Explanation:
Number of nodes, n = 5
Number of edges, m = 6
Next m lines represent the edges.



```java
import java.io.*;

class GFG {
    public static void main(String[] args) {
        int n = 3, m = 3;
        int adj[][] = new int[n + 1][n + 1];

        // edge 1---2
        adj[1][2] = 1;
        adj[2][1] = 1;

        // edge 2---3
        adj[2][3] = 1;
        adj[3][2] = 1;

        // edge 1---3
        adj[1][3] = 1;
        adj[3][1] = 1;
    }
}


```


- Space complexity = (n*n), It is a costly method as n² locations are consumed


### Adjacency Lists
- we associate with each node a list of nodes adjacent to it. Normally an array is used to store the nodes. The array provides random access to the adjacency list for any particular node.



- To create an adjacency list, we will create an array of size n+1 where n is the number of nodes. This array will contain a list, so in C++ list is nothing but the vector of integers.

### What is the motive of the list?

- to store its immediate neighbors in any order, we use the list.



- we stored all the neighbors in the particular indexes. In this representation, for an undirected graph, each edge data appears twice.
```java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        int n = 3, m = 3;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        // n + 1 (for 1-based indexing)
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // edge 1---2
        adj.get(1).add(2);
        adj.get(2).add(1);

        // edge 2---3
        adj.get(2).add(3);
        adj.get(3).add(2);

        // edge 1---3
        adj.get(1).add(3);
        adj.get(3).add(1);

        // print adjacency list
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}

```

- Space complexity = O(2xE)

- For directed graphs, if there is an edge between u and v it means the edge only goes from u to v, i.e., v is the neighbor of u, but vice versa is not true. The space needed to represent a directed graph using its adjacency list is E locations, where E denotes the number of edges, as here each edge data appears only once.

Space complexity = O(E)


### Weighted Graph Representation

For the adjacency matrix, it is much simpler

- we were storing a list of integers in each index, but for weighted graphs, we will store pairs (node, edge weight) in it.

- vector< pair <int,int> > adjList[n+1];
