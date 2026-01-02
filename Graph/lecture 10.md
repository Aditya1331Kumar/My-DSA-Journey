# leetcode  994
## Approach: 

-> First of all we will create a Queue data structure to store coordinate of Rotten Oranges

### We will also have variables as:

- Total_oranges - It will store total number of oranges in the grid ( Rotten + Fresh )
- Count - It will store the total number of oranges rotten by us . 
- Total_time - total time taken to rotten.


#### After this, we will traverse the whole grid and count the total number of oranges in the grid and store it in Total_oranges. Then we will also push the rotten oranges in the Queue data structure as well.

- Now while our queue is not empty,  we will pick up each Rotten Orange and check in all its 4 directions whether a Fresh orange is present or not. If it is present we will make it rotten and push it in our queue data structure and pop out the Rotten Orange which we took up as its work is done now.

- Also we will keep track of the count of rotten oranges we are getting.

- If we rotten some oranges, then obviously our queue will not be empty. In that case, we will increase our total time. This goes on until our queue becomes empty. 

- After it becomes empty, We will check whether the total number of oranges initially is equal to the current count of oranges. If yes, we will return the total time taken, else will return -1 because some fresh oranges are still left and can’t be made rotten.
```java
class Solution {
   
public static int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for(int i = 0 ; i < rows ; i++) {
            for(int j = 0 ; j < cols ; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(new int[]{i , j});
                }
                if(grid[i][j] != 0) {
                    count_fresh++;
                }
            }
        }
       
        if(count_fresh == 0) return 0;
        int countMin = 0, cnt = 0;
        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};
        
        //bfs starting from initially rotten oranges
        while(!queue.isEmpty()) {
            int size = queue.size();
            cnt += size; 
            for(int i = 0 ; i < size ; i++) {
                int[] point = queue.poll();
                for(int j = 0;j<4;j++) {
                    int x = point[0] + dx[j];
                    int y = point[1] + dy[j];
                    
                    if(x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || 
                    grid[x][y] == 2) continue;
                    
                    grid[x][y] = 2;
                    queue.offer(new int[]{x , y});
                }
            }
            if(queue.size() != 0) {
                countMin++;
            }
        }
        return count_fresh == cnt ? countMin : -1;
    }
}

```
```java
Output:

Minimum Number of Minutes Required 4
```
- Time Complexity: O ( n x n ) x 4    

  - Reason: Worst-case - We will be making each fresh orange rotten in the grid and for each rotten orange will check in 4 directions

- Space Complexity: O ( n x n )

  - Reason: worst-case -  If all oranges are Rotten, we will end up pushing all rotten oranges into the Queue data structure

  # 2 step mein diff:

```java
class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>(); 
        int fresh = 0; 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) q.add(new int[]{i, j});
                if (grid[i][j] == 1) fresh++; 
            }
        }

        if (fresh == 0) return 0; 

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minutes = 0; 
        while(!q.isEmpty()) {
            int q_size = q.size(); 

            for (int i = 0; i < q_size; i++) {
                int[] process = q.poll(); 
                int x = process[0]; 
                int y = process[1]; 

                for (int[] dir : directions) {
                    int newX = x + dir[0]; 
                    int newY = y + dir[1]; 

                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] != 2) {
                        if (grid[newX][newY] == 1) {
                            q.add(new int[]{newX, newY});
                            grid[newX][newY] = 2; 
                        }
                    }
                }
            }

            minutes++; 
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) return -1; 
            }
        }

        return minutes - 1; 
    }
}
```