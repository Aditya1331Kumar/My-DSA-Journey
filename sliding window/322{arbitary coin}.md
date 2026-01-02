# leetcode problem 

***cool !!!***
---
*** *** 
---

## some important points to leran:

---
 # the dynamic soln
```java


import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        final int INF = amount + 1;          // any large number > amount,  amount+1 represents “unreachable”.
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);
        // dp[i] = minimum number of coins needed to make amount i
        dp[0] = 0; // 0 coins to make amount 0

        for (int i = 1; i <= amount; i++) {
            for (int c : coins) { // you can use c as the last coin
                if (c <= i) {
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                    // If you already know the best way to form i-c (dp[i-c]), then by adding coin c you get a candidate solution with dp[i-c] + 1 coins.
                    // take min then
                }
                // Agar hum coin c use karte hain to:

//Pehle hume amount (i - c) banana padega.

 //Uske liye coins lagenge dp[i - c].

//Ab ek coin c aur le liya → total coins = dp[i - c] + 1.
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
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