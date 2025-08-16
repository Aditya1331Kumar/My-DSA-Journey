# leetcode problem  977

***two pointer solution is good***
---
*** *** 
---

## some important points to leran:

---

1.two pointer solution is good
```
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1, pos = n - 1;

        while (left <= right) {
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            if (leftSq > rightSq) {
                res[pos] = leftSq;
                left++;
            } else {
                res[pos] = rightSq;
                right--;
            }
            pos--;
        }
        return res;
    }
}
 
```
---
---

2. mera solution
```
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;

        // Step 1: find index with minimum absolute value
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (Math.abs(nums[i]) < Math.abs(nums[minIndex])) {
                minIndex = i;
            }
        }

        // Step 2: Two-pointer merge starting from middle
        int left = minIndex - 1;
        int right = minIndex;
        int[] res = new int[n];
        int m = 0;

        // Step 3: merge process
        while (left >= 0 && right < n) {
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            if (leftSq < rightSq) {
                res[m++] = leftSq;
                left--;
            } else {
                res[m++] = rightSq;
                right++;
            }
        }

        // Step 4: add remaining left elements
        while (left >= 0) {
            res[m++] = nums[left] * nums[left];
            left--;
        }

        // Step 5: add remaining right elements
        while (right < n) {
            res[m++] = nums[right] * nums[right];
            right++;
        }

        return res;
    }
}

```
---
---

3. LEFT side ke remaining elements
```
while (left >= 0) {
    res[m++] = nums[left] * nums[left];
    left--;
}
```

- RIGHT side ke remaining elements
```
while (right < n) {
    res[m++] = nums[right] * nums[right];
    right++;
}

```
---
---
```
4.boolean leftOk  = (i == 0) || (nums[i] > nums[i - 1]);


it means i==0 hua to aisi hi true
```
---
---

5. o(n) complexity------ peak element find karne ka tarika 
```
public int findPeakElement(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        boolean leftOk  = (i == 0) || (nums[i] > nums[i - 1]);
        boolean rightOk = (i == n-1) || (nums[i] > nums[i + 1]);
        if (leftOk && rightOk) return i;
    }
    return 0; // safety (array always has at least one peak)
}

```
---
---
```
6.
```
---
---
```
7.
```
---
---
```
8.
```
---

---
```
9.
```
---
---
```
10.
```
---
