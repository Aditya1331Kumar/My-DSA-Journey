# Problem Statement:
- Given a grid of size NxM (N is the number of rows and M is the number of columns in the grid) consisting of '0's (Water) and ‘1's(Land). Find the number of islands.

- Note: An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.

# examples

- There are 3 islands as the different components are surrounded by water (i.e. 0), and there is no land connectivity in either of the 8 directions hence separating them into 3 islands.

- All lands are connected. So, only 1 island is present


# Solution:


### Intuition:
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.




## Approach: breadth First Search (BFS) and Depth First Search (DFS). 


- (<row, column>) will represent the node number && queue data structure and a visited array. 

- While BFS traversal, pop out an element from the queue and travel to all its neighbours

- Start from (0, 0), if you get water (i.e., 0) move to the next index, otherwise, if you find land (i.e., 1) then call BFS traversal for that pair (<row, column>), which will be a starting node. Repeat the step, and call BFS traversal only for unvisited land pairs (<row, column>). 

# soln:
```java
import java.util.*;
class Solution {
    private void bfs(int ro, int co, int[][] vis, char[][] grid) {
      vis[ro][co] = 1; 
      Queue<Pair> q = new LinkedList<Pair>();
      q.add(new Pair(ro, co)); 
      int n = grid.length; 
      int m = grid[0].length; 
      
      // until the queue becomes empty
      while(!q.isEmpty()) {
          int row = q.peek().first; 
          int col = q.peek().second; 
          q.remove(); 
          
          // traverse in the neighbours and mark them if its a land 
          for(int delrow = -1; delrow<=1;delrow++) {
              for(int delcol = -1; delcol <= 1; delcol++) {
                  int nrow = row + delrow; 
                  int ncol = col + delcol; 
          // check if neighbour row and column is valid, and is an unvisited land
                  if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m 
                  && grid[nrow][ncol] == '1' && vis[nrow][ncol] == 0) {
                      vis[nrow][ncol] = 1; 
                      q.add(new Pair(nrow, ncol)); 
                  }
              }
          }
      }
  }

    // Function to find the number of islands.
    public int numIslands(char[][] grid) {
        int n = grid.length; 
        int m = grid[0].length; 
        int[][] vis = new int[n][m];
        int cnt = 0; 
        for(int row = 0; row < n ; row++) {
            for(int col = 0; col < m ;col++) {
                // if not visited and is a land
                if(vis[row][col] == 0 && grid[row][col] == '1') {
                    cnt++; 
                    bfs(row, col, vis, grid); 
                }
            }
        }
        return cnt; 
    }
    
    public static void main(String[] args)
    {
        char[][] grid =  {
            {'0', '1', '1', '1', '0', '0', '0'},
            {'0', '0', '1', '1', '0', '1', '0'}
        };
                
        Solution obj = new Solution();
        System.out.println(obj.numIslands(grid));
    }

}
class Pair {
    int first;
    int second; 
    public Pair(int first, int second) {
        this.first = first; 
        this.second = second; 
    }
}
```
* Output: 2

* Time Complexity ~ O(N² + NxMx9), N² for the nested loops, and NxMx9 for the overall DFS of the matrix, that will happen throughout if all the cells are filled with 1.

* Space Complexity: O(N²) for visited array max queue space O(N²), If all are marked as 1 then the maximum queue space will be N².
