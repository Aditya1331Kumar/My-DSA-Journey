# best solution:
```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int K) {
        return atMost(nums, K) - atMost(nums, K - 1);
    }

    private int atMost(int[] nums, int K) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            if (freq.get(nums[right]) == 1) K--;

            while (K < 0) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) K++;
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }
}

```
###Ramban

```java
exactly K = atMost(K) - atMost(K - 1)
```



### `Map<Integer, Integer> freq = new HashMap<>();`

This map stores:

```
key   → number
value → frequency (count inside window)
```

Example window: `[1,2,1]`
map:

```
{1:2, 2:1}
```

Iska matlab:

* 1 → 2 baar aaya
* 2 → 1 baar aaya

---

# 🔵 PART 2 — Adding element to map

### `freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);`



* `nums[right]` number ko map me daalna
* `freq.getOrDefault(x, 0)` → agar x map me nahi hai to default 0 do
* `+1` → uski frequency ek badha do


# 🔵 PART 3 — NEW element enter hua → DISTINCT count badha



```java
if (freq.get(nums[right]) == 1) {
    K--;
}
```

 “Agar frequency **1 ho gayi**, matlab yeh number **pehli baar window me enter hua**.”

Iska matlab:

**distinct elements +1**

```

 `K` represent kitne new distinct aur allow hain
```


# 🔵 PART 4 — Window shrink jab distinct zyada ho jaye

```java
while (K < 0)
```

Meaning:

```
window me allowed se zyada new distinct aa gaye
→ window ko chhota karo (left side se)
```

Now look at this:

```java
freq.put(nums[left], freq.get(nums[left]) - 1);
```

Left element ki frequency reduce karo.

---

## 🔵 PART 5 — If frequency becomes 0 → number window se bahar gaya

```java
if (freq.get(nums[left]) == 0) {
    K++;
}
```