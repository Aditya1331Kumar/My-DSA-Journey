# leetcode problem 1331

***achha hi hai***
---
*** *** 
---

## some important points to leran:

---

1.new solution using hashmap
```
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if (n == 0) return arr;

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // map element -> rank
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int val : sorted) {
            if (!rankMap.containsKey(val)) {
                rankMap.put(val, rank);
                rank++;
            }
        }

        // replace values in arr with ranks
        for (int i = 0; i < n; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        return arr;
    }
}

```
---
---

2. kuchhhadh tak mera solution
```
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if (n == 0) return arr;

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // remove duplicates
        int m = 1;
        for (int i = 1; i < n; i++) {
            if (sorted[i] != sorted[i - 1]) {
                sorted[m++] = sorted[i];
            }
        }

        // binary search to assign ranks
        for (int i = 0; i < n; i++) {
            arr[i] = binarySearch(sorted, 0, m - 1, arr[i]) + 1;
        }
        return arr;
    }

    private int binarySearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1; // won't happen here
    }
}

```
---
---

3.   int pos = Arrays.binarySearch(sorted, 0, m, arr[i]); 
```
 search only  from 0 to m-1
```
---
---

4. tree se solution bana h 
```
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if (n == 0) return arr;

        TreeSet<Integer> set = new TreeSet<>();
        for (int v : arr) set.add(v);

        Map<Integer,Integer> rank = new HashMap<>();
        int r = 1;
        for (int v : set) rank.put(v, r++);

        for (int i = 0; i < n; i++) arr[i] = rank.get(arr[i]);
        return arr;
    }
}

```
---
---

5. Counting / bucket approach (only if value range small)
Idea: if values are small / bounded (e.g. 1..10^5), use a boolean/frequency array to mark presence and build rank array by scanning value-range. This can be O(n + range). Use only when range is reasonable.
```
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if (n==0) return arr;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int v : arr) { min = Math.min(min, v); max = Math.max(max, v); }
        int range = max - min + 1;
        if (range > 1_000_000) { // heuristic: avoid huge range
            // fallback to sort-based solution (use one above)
        }

        int[] present = new int[range];
        for (int v : arr) present[v - min] = 1;

        int[] rank = new int[range];
        int r = 1;
        for (int i = 0; i < range; i++) {
            if (present[i] == 1) rank[i] = r++;
        }

        for (int i = 0; i < n; i++) arr[i] = rank[arr[i] - min];
        return arr;
    }
}

```
---
---

6. Counting / Bucket Approach kya hai?
```
Tum ek array (bucket) banaoge jiska size ho max_value - min_value + 1.
```
```
Is bucket mein tum store karoge ki kaunse values array mein present hain.
``` 
```
Fir tum ek rank array banao jisme har value ko uske rank assign karoge (jo unique values ko sorted order mein represent kare).
```
```
Finally, original array ke har element ko uske rank se replace kar doge using bucket indices.
```

Complexity:
```
Time: O(n + range) (range can be up to 10^5, which is okay)
```
```
Space: O(range)
```
---
---
```
7. Counting/Bucket Approach ka idea
```
- Range dhundo: sabse chhota aur sabse bada element array me kya hai?
Example: [40, 10, 20, 40] me min=10, max=40
Is range ko use karenge ek array banane ke liye jisme har value ki presence mark karenge.
---
- Bucket array banao: size = (max - min + 1)
Kyunki value min se max ke beech me hi hoti,
Toh present naam ka ek array banao jisme har index ek unique value ko represent karega.
Example: min=10 → index 0 represent karega value 10 ko,
index 1 represent karega 11 ko, aur aise aage.
---
- Presence mark karo:
For each value v in original array, mark karo present[v - min] = 1.
Matlab wo value array me hai.
---
- Rank assign karo:
Ab present array ko left se right loop me chalo.
Jab bhi present[i] == 1 mile, rank assign karo rank[i] = current_rank aur current_rank++ karo.
---
- Replace kar do original array ke elements:
For each element v in original array, uska rank hoga rank[v - min].
Replace kar do arr[i] = rank[v - min].

---
---
```
8. where using bucket approch
```
- Tumko sorting nahi karni padti → no O(n log n).

- Tum direct presence mark kar rahe ho aur ranks assign kar rahe ho in O(n + range).

- Agar range (max-min) chhota ya moderate hai (jaise 10^5 tak), toh ye approach bahut fast ho jaata hai.

- Extra space thoda lagta hai (range ka size), but it’s acceptable for limited value range.

---

---

9. one more solution 


```
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if (n == 0) return arr;

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (a,b) -> Integer.compare(arr[a], arr[b]));

        int[] ans = new int[n];
        int rank = 1;
        boolean first = true;
        int prev = 0;
        for (int id : idx) {
            if (first || arr[id] != prev) {
                prev = arr[id];
                first = false;
                ans[id] = rank++;
            } else {
                ans[id] = rank - 1;
            }
        }
        return ans;
    }
}

```
---
---
```
10.   what it means  ?????
Arrays.sort(idx, (a,b) -> Integer.compare(arr[a], arr[b]));
```
-  idx is an array of indices
- This is a lambda function: a short way to write a method for comparison.

   - a and b are indices from idx
```
 Integer.compare(x, y):
 ```

 - Returns negative if x < y

 - Returns zero if x == y

- Returns positive if x > y  


Sort these indices based on the values they point to in arr, from smallest to largest.
```
example 
arr = [40, 10, 20]
idx = [0, 1, 2]
```
- 10  → index 1  
20  → index 2  
40  → index 0  

- final ans
[1, 2, 0] = idx
arrr to wahi rahega or ye acsending k liye tha 

```
If you wanted descending order (largest → smallest), then the comparator would be reversed:
```
- Arrays.sort(idx, (a, b) -> Integer.compare(arr[b], arr[a]));


---
---
```
10. class Solution {
    public int[] arrayRankTransform(int[] arr) 
    {
        int rank[]=new int[arr.length];
        int sorted[]=arr.clone();
        Arrays.sort(sorted);
        HashMap<Integer,Integer> hm = new HashMap<>();
        int r=1;
        for(int i=0;i<sorted.length;i++)
        {
            if(!hm.containsKey(sorted[i]))
            {
                hm.put(sorted[i],r);
                r++;
            }
        } 

        for(int i=0;i<arr.length;i++)
        {
            rank[i]=hm.get(arr[i]);
        }
        return rank;
    }
}
```
---
---
```
10. class Solution {
    public int[] arrayRankTransform(int[] arr) {
         if(arr.length == 0) {
            return new int[]{};
        }
        
        int min = arr[0];
        int max = arr[0];
        for(int num : arr) {
            if(min > num) {
                min = num;
            } else if(max < num) {
                max = num;
            }
        }
        
        if(max - min > 1000000) { return getRanks(arr); }

        int[] ranks = new int[max - min + 1];
        for(int num : arr) {
            ranks[num - min] = 1;
        }
        for(int id = 1; id < ranks.length; id++) {
            ranks[id] += ranks[id - 1];
        }
        int []result = new int[arr.length];
        int id = 0;
        for(int num : arr) {
            result[id++] = ranks[num - min];
        }
        return result;
    }

    private int[] getRanks(int[] arr) {
        Integer[] indexes = new Integer[arr.length];
        int[] ranks = new int[arr.length];

        for(int index = 0; index < arr.length; index++) {
            indexes[index] = index;
        }

        Arrays.sort(indexes, (i1, i2) -> arr[i1] - arr[i2]);

        int rank = 0;
        int prev = arr[0] ^ 1;
        for(int index = 0; index < indexes.length; index++) {
            if(prev != arr[indexes[index]]) {
                ++rank;
            }
            ranks[indexes[index]] = rank;
            prev = arr[indexes[index]];
        }

        return ranks;
    }
}
```
---
---
```
10.
```
---
---
```
10.
```
---
---
```
10.
```
---
---
```
10.
```
---
