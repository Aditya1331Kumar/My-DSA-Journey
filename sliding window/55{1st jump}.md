# classic greedy 
```java
class Solution {
    public boolean canJump(int[] nums) {
        int maxJump = 0;
        int n = nums.length;

        for(int i=0;i<=Math.min(n-1,maxJump);i++) {
            maxJump = Math.max(maxJump , i+nums[i]);
        }
        System.out.println(maxJump);

        return maxJump >= n-1;
    }
}
```
## or
```java
class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0; // farthest index we can reach
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) return false; // if we are stuck
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true;
    }
}
```
```java
public static boolean canJump(int[] nums) {
        // Initialize the maximum
        // index that can be reached
        int maxIndex = 0;

        // Iterate through each
        // index of the array
        for (int i = 0; i < nums.length; i++) {
            // If the current index is greater
            // than the maximum reachable index
            // it means we cannot move forward
            // and should return false
            if (i > maxIndex) {
                return false;
            }

            // Update the maximum index
            // that can be reached by comparing
            // the current maxIndex with the sum of
            // the current index and the
            // maximum jump from that index
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }

        // If we complete the loop,
        // it means we can reach the
        // last index
        return true;
    }
```