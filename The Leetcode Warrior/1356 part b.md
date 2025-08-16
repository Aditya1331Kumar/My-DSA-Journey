# leetcode problem 1356
***very good Question*** 
 https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/submissions/1724116181/

## some important points to leran:

## solution 
```java
import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        // Convert int[] to Integer[] to use custom comparator
        Integer[] boxedArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        // Sort using custom comparator
        Arrays.sort(boxedArr, (a, b) -> {
            int bitCountA = Integer.bitCount(a);
            int bitCountB = Integer.bitCount(b);
            if (bitCountA == bitCountB) {
                return a - b;
            }
            return bitCountA - bitCountB;
        });

        // Convert Integer[] back to int[]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = boxedArr[i];
        }

        return arr;
    }
}
```

* boxed() converts IntStream → Stream<Integer>, then toArray(Integer[]::new) collects into Integer[]

1. sorting mein custom coparator ka use hua hai 
     - badhiya chij h ye

2. some differences
  - int[] is a primitive array — fast but limited. 
  - int[] is a primitive array — fast but limited.

3.  swap ka method yaad hona chayee

4. int bitsA = Integer.bitCount(a);
   - Bit count means the number of 1s in the binary representation of a number.

5. Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
 - Arrays.stream(arr) → Converts int[] to an IntStream (a stream of primitive ints).

- .boxed() → Converts each int in the stream to an Integer object (this is called boxing).

- .toArray(Integer[]::new) → Collects the boxed stream into a new Integer[] array.


---
6. detail mein break karte hain comparator ko:


### Case 1: `bitCountA == bitCountB`

* Matlab `a` aur `b` ke binary mein 1s ki ginti barabar hai.
* Is situation mein comparator `a - b` return karega.
* Agar `a < b` → negative return hoga → **`a` pehle aayega**
* Agar `a > b` → positive return hoga → **`b` pehle aayega**
  ➡️ Matlab tie-break **ascending order of values**.

---

### Case 2: `bitCountA != bitCountB`

* Comparator `bitCountA - bitCountB` return karega.
* Agar `bitCountA < bitCountB` → negative → **`a` pehle aayega**
* Agar `bitCountA > bitCountB` → positive → **`b` pehle aayega**
  ➡️ Matlab sort **ascending order of bit counts**.

---
---
``` java
Integer[] boxed = new Integer[n];
        for (int i = 0; i < n; i++) {
            boxed[i] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            arr[i] = boxed[i];
        }
        return arr; 
```
---
---
``` java
2 << 16 = 2 * 65536 = 131072

```
# Schwartzian transform approach
```java
Arrays.sort(boxed, (a, b) -> {
    int ka = (Integer.bitCount(a) << 16) + a;
    int kb = (Integer.bitCount(b) << 16) + b;
    return Integer.compare(ka, kb);
});


```
---

---

9.  # Sabse Achha solution
```java
class Solution {
    public int[] sortByBits(int[] arr) {
        //ok so for this enginner rule is applied where each number is updated by a the number itself + number of bits in number*(10001) and then we will sort it and then we will take the modulo.
        
        for(int i=0;i<arr.length;i++)
        {
            arr[i]+=Integer.bitCount(arr[i])*10001;
        }
        
        Arrays.sort(arr);
        
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=arr[i]%10001;
        }
        return arr;
    }
}
```

### 🔹 Step 1: Modify each number

```java
arr[i] += Integer.bitCount(arr[i]) * 10001;
```

* Multiply by `10001` → ye ek **large offset** hai jisse bitcount ka weight value se alag rahe.
* Then add the number itself.


```
newValue = originalValue + (bitCount * 10001)
```

---

### 🔹 Why `10001`?

* Array ke values `0 <= arr[i] <= 10000` (LC constraints).
* Agar hum bitcount \* 10001 karein, to bitcount ka contribution **always value se bada hoga**.

* Aur agar bitcount same hai to `+ originalValue` decide karega (tie-breaker).

---

### 🔹 Step 2: Sort

```java
Arrays.sort(arr);
```

* Ab array sorted ho jayega according to `newValue`.
---

### 🔹 Step 3: Restore original numbers

```java
arr[i] = arr[i] % 10001;
```


* Kyunki `bitCount*10001` divisible by 10001 hota hai, bacha hua part sirf `originalValue` hota hai.


**Time complexity**: `O(n log n)` (same as normal sort)

**Space**: `O(1)` extra (only reusing same array).


---
 # Another fadu solution 
```java
import java.util.*;

class Solution {
    public int[] sortByBits(int[] arr) {
        long[] decorated = new long[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            // high 32 bits = bitcount, low 32 bits = value
            decorated[i] = ((long) Integer.bitCount(arr[i]) << 32) | (arr[i] & 0xffffffffL);
        }
        
        Arrays.sort(decorated);
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) decorated[i];
        }
        
        return arr;
    }
}

```
---
