# candy problem
### my soln:
```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] arr = new int[n];
        arr[0] = 1;
        int i = 1, l = 0, candy = 0;

        while (i < n) {
            arr[i] = 1;
            if (ratings[i - 1] > ratings[i]) {
                // pichle elements ko +1 karke fix karna (reverse slope ke liye)
                for (int j = i; j > 0 && ratings[j - 1] > ratings[j] && arr[j - 1] <= arr[j]; j--) {
                    arr[j - 1] = arr[j] + 1;
                }
            } else if (ratings[i - 1] == ratings[i]) {
                arr[i] = 1; // equal case: dono ko 1 candy minimum
            } else {
                arr[i] = arr[i - 1] + 1;
                l = i;
            }
            i++;
        }

        for (int k = 0; k < n; k++) {
            candy += arr[k];
        }
        return candy;
    }
}
```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---
---
 #
```java


```
---