# leetcode 733
## 🔴 Problems in your code

1. **Starting wrong position**

   * Tumne `floodFill` ke andar call kiya hai:

     ```java
     return flood(image, sr, sc-1, color);
     ```

     Yani tum left wale pixel (`sc-1`) se start kar rahe ho.
     ✅ Correct: Tumhe **exact starting pixel `(sr, sc)`** se hi recursion start karna chahiye.

---

2. **No tracking of original color**

   * Flood fill ka logic hai: *replace all connected cells which have the **original starting color***.
   * Tumhare code mein tum `image[sr][sc]==image[sr][sc-1]` compare kar rahe ho har bar.
   * ✅ Correct: Ek variable `oldColor = image[sr][sc]` lena hoga aur usko pass karna hoga recursion mein.

---

3. **No visited / overwrite check**

   * Tum bar-bar `image[sr][sc] = color;` kar rahe ho without checking agar wo pixel pehle hi `color` ban chuka hai.
   * Isse **infinite recursion** ho sakta hai (loop ban jaata hai).
   * ✅ Correct: Pehle check karo agar `image[sr][sc] == color` already hai, toh return kar do.

---

4. **Mixing `floodFill` and `flood`**

   * Tumne `flood()` ke andar wapas `floodFill()` call kar diya hai.
   * ✅ Correct: Sirf ek helper method (say `dfs`) rakho jo recursion kare. Nahi toh confusing recursion ho jaata hai.

---

## ✅ Corrected Code (DFS approach)

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor != newColor) { // avoid infinite loop
            dfs(image, sr, sc, oldColor, newColor);
        }
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        int m = image.length, n = image[0].length;

        // base conditions
        if (r < 0 || r >= m || c < 0 || c >= n) return; // out of bounds
        if (image[r][c] != oldColor) return; // not same color as starting

        // fill with new color
        image[r][c] = newColor;

        // recursive calls in 4 directions
        dfs(image, r - 1, c, oldColor, newColor); // up
        dfs(image, r + 1, c, oldColor, newColor); // down
        dfs(image, r, c - 1, oldColor, newColor); // left
        dfs(image, r, c + 1, oldColor, newColor); // right
    }
}
```

---

## 🔹 Example Run

Input:

```
image = [[1,1,1],
         [1,1,0],
         [1,0,1]]
sr = 1, sc = 1, newColor = 2
```

* Starting pixel color = `1` → replace whole connected component of `1`s.
* Output:

```
[[2,2,2],
 [2,2,0],
 [2,0,1]]
```

---

Yes 👍 flood fill can be done with **BFS (Breadth First Search)** instead of DFS.
In BFS we use a **queue** to explore all neighbors level by level.

Here’s the BFS approach for **Flood Fill**:

```java
import java.util.*;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];

        // If already filled with the same color, just return
        if (oldColor == color) return image;

        // Directions for 4-connected neighbors (up, down, left, right)
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        image[sr][sc] = color; // fill starting point

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                // Check valid neighbor and same color as old
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == oldColor) {
                    image[nr][nc] = color;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return image;
    }
}
```

### ✅ Key points in BFS solution:

1. Use a queue to store cells to be processed.
2. Change color when you **push** it into the queue (to avoid re-visiting).
3. Check boundary conditions carefully.
4. Stop if the starting pixel is already the new color.

This approach avoids stack overflow (which DFS recursion can cause if the image is very large).






# striver soln:{ DFS approch}
```java
import java.util.*;

class Solution
{
    private void dfs(int row, int col, 
     int[][] ans,
     int[][] image, 
     int newColor, int delRow[], int delCol[],
     int iniColor) {
        // color with new color
        ans[row][col] = newColor; 
        int n = image.length;
        int m = image[0].length; 
        // there are exactly 4 neighbours
        for(int i = 0;i<4;i++) {
            int nrow = row + delRow[i]; 
            int ncol = col + delCol[i]; 
            // check for valid coordinate 
            // then check for same initial color and unvisited pixel
            if(nrow>=0 && nrow<n && ncol>=0 && ncol < m && 
            image[nrow][ncol] == iniColor && ans[nrow][ncol] != newColor) {
                dfs(nrow, ncol, ans, image, newColor, delRow, delCol, iniColor); 
            }
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        // get initial color
        int iniColor = image[sr][sc]; 
        int[][] ans = image; 
        // delta row and delta column for neighbours
        int delRow[] = {-1, 0, +1, 0};
        int delCol[] = {0, +1, 0, -1}; 
        dfs(sr, sc, ans, image, newColor, delRow, delCol, iniColor); 
        return ans;  
    }
    public static void main(String[] args)
    {
        int[][] image =  {
	        {1,1,1},
	        {1,1,0},
	        {1,0,1}
	    };

        // sr = 1, sc = 1, newColor = 2       
        Solution obj = new Solution();
        int[][] ans = obj.floodFill(image, 1, 1, 2);
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++)
                System.out.print(ans[i][j] + " ");
            System.out.println();
        }
    }

}
```
```java
Output:

2 2 2
2 2 0 
2 0 1
```
- Time Complexity: O(NxM + NxMx4) ~ O(N x M)

- nFor the worst case, all of the pixels will have the same colour, so DFS function will be called for (N x M) nodes and for every node we are traversing for 4 neighbours, so it will take O(N x M x 4) time.

- Space Complexity: O(N x M) + O(N x M)

- O(N x M) for copied input array and recursive stack space takes up N x M locations at max. 

# one more :
```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (color != image[sr][sc]) {
            int origColor = image[sr][sc];
            Deque<int[]> queue = new ArrayDeque();

            queue.add(new int[] {sr, sc});

            for (int[] pixel = queue.poll(); pixel != null; pixel = queue.poll()) {
                if (image[pixel[0]][pixel[1]] == origColor) {
                    image[pixel[0]][pixel[1]] = color;

                    if (pixel[0] != 0) {
                        queue.add(new int[] {pixel[0]-1, pixel[1]});
                    }
                    if (pixel[0] != image.length-1) {
                        queue.add(new int[] {pixel[0]+1, pixel[1]});
                    }
                    if (pixel[1] != 0) {
                        queue.add(new int[] {pixel[0], pixel[1]-1});
                    }
                    if (pixel[1] != image[0].length-1) {
                        queue.add(new int[] {pixel[0], pixel[1]+1});
                    }
                }
            }
        }

        return image;
    }
}
```

