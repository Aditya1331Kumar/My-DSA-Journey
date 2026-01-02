## sikhgne wali baat 
### 1st soln:
```java

    class Solution {
    public int numSubarraysWithSum(int[] s, int k) {




        return ans(s,k)-ans(s,k-1);
    }

     public int ans(int[] s, int k) {
        if (k < 0) return 0;
         int n = s.length;
        int maxLen = 0;
      int count =0;
int m=0;
        
        int l = 0, r = 0;
        while (r < n) {
            m+=s[r];
       
           while(m>k){
            m-=s[l];
            l++;
           } 
           
            
           count += (r - l + 1);
            r++;
        }
        return count;
    }
}

```

### 2nd sir ka soln 
```java

public int numSubarraysWithSum(int[] nums, int goal) {
        // Return difference between atMost(goal) and atMost(goal - 1)
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    // Helper method to count subarrays with sum at most k
    private int atMost(int[] nums, int k) {
        // No valid subarray for negative sum
        if (k < 0) return 0;

        int left = 0;
        int sum = 0;
        int count = 0;

        // Traverse array using right pointer
        for (int right = 0; right < nums.length; right++) {
            // Add current element to sum
            sum += nums[right];

            // Shrink window if sum exceeds k
            while (sum > k) {
                sum -= nums[left];
                left++;
            }

            // Add number of valid subarrays ending at right
            count += (right - left + 1);
        }

        return count;
    }
}

```