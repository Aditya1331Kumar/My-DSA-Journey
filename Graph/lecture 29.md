# Word ladder 1


### Main problems in your code (detailed)

1. **Wrong String comparisons (`==` / `!=`)**

   * You do `list.get(list.size() - 1) != endWord` and `list.get(0) != beginWord` and `list.get(i) == beginWord`.
   * In Java `==`/`!=` compare **references**, not text. Use `equals()` (e.g. `str.equals(other)`) or `list.contains(endWord)`.
   * Consequence: even if two strings have same characters, `==` might be false → your code will wrongly return `0` or continue.

2. **You assume `endWord` is at the last index and `beginWord` at index 0**

   * `if (list.get(list.size() - 1) != endWord) { return 0; }`
   * `endWord` may be anywhere in the `wordList` (or not present at all). Correct check: `if (!list.contains(endWord)) return 0;`
   * Also `beginWord` is typically not included in `wordList`. Your logic trying to compare only `list.get(0)` to `beginWord` is incorrect.

3. **The `cool` method logic is wrong for what you need**

   * `cool(beginWord, endWord, list, i)` compares `list.get(j)` with `list.get(j-1)` and checks mismatches count ≤ 1.
   * This only checks **adjacency of consecutive elements in the list**, not adjacency among **any** two words. The graph’s edges are between **any** two words that differ by one letter, not just neighbors in the provided list order.
   * You need to consider edges between any pair (or generate valid one-letter transformations), not only `j` and `j-1`.

4. **Index out-of-bounds / invalid calls**

   * In loop `for (int i = 0; i < list.size(); i++)` you may call `cool(..., i)` with `i == 0`. Inside `cool` you access `list.get(j-1)` → `list.get(-1)` → **IndexOutOfBoundsException**.
   * You never guard `j > 0` before doing `list.get(j-1)`.

5. **Algorithmic mistake — you’re not finding shortest path**

   * Word Ladder asks for the **shortest transformation length** (smallest number of words in sequence). Your approach is doing counts like `count++` for some matches; that does not compute the shortest path.
   * You need **BFS** (or bidirectional BFS / shortest-path on unweighted graph), because BFS explores by layers (distance). Your code lacks level-by-level traversal.

6. **Meaning of `count` / early returns are wrong**

   * `if (start <= 1) { count++; } else { return 1; }` — returning `1` here makes no sense: you should return `0` if no valid ladder or return computed ladder length. This is inconsistent and incorrect semantics for the problem.

---

### Example: how your code can fail

Input:

```
beginWord = "hit"
endWord = "cog"
list = ["hot","dot","dog","lot","log","cog"]
```

* Your `if (list.get(list.size()-1) != endWord)` may *accidentally* pass if interned strings match, but it's fragile and wrong — if `cog` were at index 2, you'd incorrectly return `0`.
* Your `cool` logic only compares neighbors in `list` order — it will not correctly chain `hit->hot->dot->dog->cog` and will not compute shortest length (which should be 5).

---

### Fix recommendation (what to do)

* Use `if (!wordList.contains(endWord)) return 0;`
* Use **BFS** to find shortest path:

  * Start from `beginWord` at level 1.
  * For each word, generate all one-letter transformations (change each char to `'a'..'z'`).
  * If the transformed word is in dictionary (wordList) and not visited → push to queue and mark visited.
  * When you reach `endWord` return the level.
* Use `equals()` for string equality when needed.

---

### Correct and standard BFS Java solution {76 ms}

This is the recommended solution (works for LeetCode `ladderLength` signature):

```java
import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Put words into a HashSet for O(1) lookup
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;           // endWord must be in list

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1; // beginWord is level 1
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word.equals(endWord)) return level;

                char[] arr = word.toCharArray();
                for (int pos = 0; pos < arr.length; pos++) {
                    char old = arr[pos];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        arr[pos] = c;
                        String newWord = new String(arr);
                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            q.add(newWord);
                        }
                    }
                    arr[pos] = old; // restore
                }
            }
            level++;
        }
        return 0; // no path found
    }
}
```

Key points:

* `level` counts number of words in the path (beginWord at level 1).
* BFS ensures shortest path is found.
* We use `Set` for fast membership and a `visited` set to avoid revisiting.

---

### Optional: faster (bidirectional BFS)

If wordList is large, use bidirectional BFS (start from both begin and end) — but only do that once single-direction BFS is understood and working.

---

### Small debugging tips

* Replace `==`/`!=` by `.equals()` when comparing strings.
* Add `System.out.println(...)` for small inputs to see queue levels, but remove for submission.
* Write small unit tests (like the `hit` → `cog` example).
* If you want to keep a graph-based solution (build adjacency for all pairs), make sure to:

  * Build edges between **any** two words that differ by exactly one char (`O(n^2 * L)`).
  * Then run BFS on that adjacency list. That works but is slower than transform-by-character approach.

---
---

### 1. ❌ Problem ki requirement aur aapka approach

* **Question kya chahta hai?**
  `beginWord` → `endWord` tak shortest transformation chain nikalni hai.
  Har step pe ek hi character change ho sakta hai aur woh intermediate word dictionary (list) ke andar hona chahiye.

* **Aapka code kya kar raha hai?**

  * Pehle check karta hai ki last word `endWord` hai ya nahi → galat assumption (endWord kahin bhi ho sakta hai, last me hi nahi).
  * `.equals()` ke jagah `==` use kiya gaya hai → string comparison galat ho jaata hai Java me.
  * Har word ko sequentially compare kar ke count badha raha hai → matlab aap chain ko **list ki order** pe depend kara rahe ho.

👉 Problem yeh hai ki shortest path **list ke order pe nahi** depend karta, balki har possible word ko explore karke minimum distance nikalna padta hai.
Isiliye brute sequential comparison fail karega.

---

### 2. ❌ BFS ki zarurat

Ye problem ek **graph shortest path** problem hai:

* Words → Graph ke nodes.
* Edge → Do words ek hi letter me differ karte hain.
* Hume shortest path nikalna hai → Isiliye **Breadth-First Search (BFS)** lagta hai.

Aapke current approach me BFS/graph ka concept hi nahi hai → isiliye chahe code correct bhi kar do, output galat hoga.

---

### 3. ❌ Cool function ki problem

```java
if( list.get(j).charAt(i) != list.get(j-1).charAt(i) )
```

* Yeh sirf **adjacent list words** compare karta hai.
* Lekin Word Ladder me adjacency ka matlab hai **har wo word jo ek character differ karta ho, chahe list me kahin bhi ho**.

Matlab aap graph ke edges ko galat define kar rahe ho.

---

### 4. ✅ Iska sahi tareeka

* BFS queue lagani padegi.
* Har word ke saath uska distance rakhna padega.
* Visited set use karna padega taaki cycle na ho.
* Har step me ek character change karke check karna hoga ki naya word list me hai ya nahi.

---






# striver
## Approach:
- Queue: Define a queue data structure to store the BFS traversal.
- Hash set: Create a hash set to store the elements present in the word list to carry out the search and delete operations in O(1) time. 
- We store them in form of {word, steps}

- in order to mark a word as visited here, we simply delete the word from the hash set

- we pop the first element out of the queue and carry out the BFS traversal 
- we also need to delete the word from the wordList if it matches with the transformed word to ensure that we do not reach the same point again in the transformation which would only increase our sequence length

# soln:
```Java
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        String startWord = "der", targetWord = "dfs";
        String[] wordList = {
            "des",
            "der",
            "dfr",
            "dgt",
            "dfs"
        };

        Solution obj = new Solution();
        int ans = obj.wordLadderLength(startWord, targetWord, wordList);

        System.out.print(ans);

        System.out.println();
    }
}

class Pair {
    String first;
    int second;
    Pair(String _first, int _second) {
        this.first = _first;
        this.second = _second;
    }
}
class Solution {
    public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        // Creating a queue ds of type {word,transitions to reach ‘word’}.
        Queue < Pair > q = new LinkedList < > ();
 
        // BFS traversal with pushing values in queue 
        // when after a transformation, a word is found in wordList.
        q.add(new Pair(startWord, 1));

        // Push all values of wordList into a set
        // to make deletion from it easier and in less time complexity.
        Set < String > st = new HashSet < String > ();
        int len = wordList.length;
        for (int i = 0; i < len; i++) {
            st.add(wordList[i]);
        }
        st.remove(startWord);
        while (!q.isEmpty()) {
            String word = q.peek().first;
            int steps = q.peek().second;
            q.remove();
      
            // we return the steps as soon as
            // the first occurence of targetWord is found.
            if (word.equals(targetWord) == true) return steps;

            // Now, replace each character of ‘word’ with char
            // from a-z then check if ‘word’ exists in wordList.
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    // check if it exists in the set and push it in the queue.
                    if (st.contains(replacedWord) == true) {
                        st.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }

            }
        }
        // If there is no transformation sequence possible
        return 0;
    }
}
```
#### Output: 3

* Time Complexity: O(N * M * 26)

* Where N = size of wordList Array and M = word length of words present in the wordList..

### Note that, hashing operations in an unordered set takes O(1) time, but if you want to use set here, then the time complexity would increase by a factor of log(N) as hashing operations in a set take O(log(N)) time.

* Space Complexity:  O( N ) { for creating an unordered set and copying all values from wordList into it }

- Where N = size of wordList Array.


# for leetcode
```java
class Solution {
    public int ladderLength(String startWord, String targetWord, List<String> wordList) {
        
        // Creating a queue ds of type {word,transitions to reach ‘word’}.
        Queue < Pair > q = new LinkedList < > ();
 
        // BFS traversal with pushing values in queue 
        // when after a transformation, a word is found in wordList.
        q.add(new Pair(startWord, 1));

        // Push all values of wordList into a set
        // to make deletion from it easier and in less time complexity.
        Set < String > st = new HashSet < String > ();
        int len = wordList.size();
        for (int i = 0; i < len; i++) {
            st.add(wordList.get(i));
        }
        st.remove(startWord);
        while (!q.isEmpty()) {
            String word = q.peek().first;
            int steps = q.peek().second;
            q.remove();
      
            // we return the steps as soon as
            // the first occurence of targetWord is found.
            if (word.equals(targetWord) == true) return steps;

            // Now, replace each character of ‘word’ with char
            // from a-z then check if ‘word’ exists in wordList.
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    // check if it exists in the set and push it in the queue.
                    if (st.contains(replacedWord) == true) {
                        st.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }

            }
        }
        // If there is no transformation sequence possible
        return 0;
    }
}




class Pair {
    String first;
    int second;
    Pair(String _first, int _second) {
        this.first = _first;
        this.second = _second;
    }
}
```


# highly optimized:

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        int len = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // हमेशा छोटे set को expand करो
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> next = new HashSet<>();

            for (String word : beginSet) {
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String newWord = new String(arr);

                        if (endSet.contains(newWord)) {
                            return len + 1;
                        }

                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            next.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    arr[i] = old;
                }
            }
            beginSet = next;
            len++;
        }
        return 0;
    }
}

```

### Bidirectional BFS** use karta hai: ek taraf se `beginWord` se search, dusri taraf se `endWord` se search — aur jab dono frontiers mil jaate hain tab shortest transformation length mil jati hai.

---

# 2) High-level idea kyu aur kaise

* Problem ko graph ke roop mein socho: **har word = node**, aur do words ke beech edge tab hai jab wo **ek character** se differ karein.
* Single-ended BFS se shortest path milta hai, lekin search space bahut bada ho sakta hai (especiallly jab depth zyada ho).
* **Bidirectional BFS** dono side se same time BFS chalata hai — dono frontiers beech mein kabhi jaldi mil jaate hain — overall work bohot kam ho jata hai.
* Aur **hamesha smaller frontier ko expand** karke branching minimize karte hain.

---

# 3) Code — line by line explanation

```java
Set<String> dict = new HashSet<>(wordList);
if (!dict.contains(endWord)) return 0;
```

* `dict` mein wordList ko HashSet mein rakhte hain for O(1) lookup.
* Agar `endWord` wordList mein nahi hai to koi transformation possible nahi — return 0.

```java
Set<String> beginSet = new HashSet<>();
Set<String> endSet = new HashSet<>();
Set<String> visited = new HashSet<>();

beginSet.add(beginWord);
endSet.add(endWord);

int len = 1;
```

* `beginSet` = current frontier (level) from `beginWord`.
* `endSet` = current frontier from `endWord`.
* `visited` = words jo already add ho chuke hain next frontier mein (duplicate insert avoid karne ke liye).
* `len = 1` ka matlab: current path length counting beginWord as level 1 (LeetCode convention — final answer = number of words in path).

```java
while (!beginSet.isEmpty() && !endSet.isEmpty()) {
    if (beginSet.size() > endSet.size()) {
        Set<String> temp = beginSet;
        beginSet = endSet;
        endSet = temp;
    }
```

* Loop tab tak chalega jab tak dono sides empty nahi ho jati.
* Agar `beginSet` bada ho to swap kar dete hain — hum hamesha **chhote** set ko expand karenge (efficiency reason).

```java
    Set<String> next = new HashSet<>();
```

* `next` will be the next level/frontier after expanding current `beginSet`.

```java
    for (String word : beginSet) {
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char old = arr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                arr[i] = c;
                String newWord = new String(arr);
```

* For each word in current frontier, hum har position `i` pe har possible letter `a..z` try karte hain — is tarah hum saare possible 1-char neighbors generate karte hain.
* We use `char[]` to change a character efficiently and then form `newWord`.

```java
                if (endSet.contains(newWord)) {
                    return len + 1;
                }
```

* Agar generated `newWord` dusre side ke frontier (`endSet`) mein present hai, iska matlab frontiers meet kar gaye — shortest path mil gaya.
* Return `len + 1` (kyunki current frontier words are at distance `len` from `beginWord`, aur `newWord` is at `len+1`).

```java
                if (dict.contains(newWord) && !visited.contains(newWord)) {
                    next.add(newWord);
                    visited.add(newWord);
                }
```

* Agar `newWord` dictionary mein hai (valid intermediate word) aur pehle se visited nahi hua, toh `next` mein add karo.
* `visited` ensure karta hai ki same word same level par multiple times add na ho — isse duplicate exploration avoid hota hai.

```java
            }
            arr[i] = old;
        }
    }
    beginSet = next;
    len++;
}
return 0;
```

* Inner loops ke baad `beginSet = next` — hum next level ko current frontier bana dete hain.
* `len++` — hum level depth ko ek aage badhate hain.
* Agar while loop terminate ho jaye bina match ke, return 0 (no path).

---

# 4) Example trace (classic `hit` → `cog`)

wordList = ["hot","dot","dog","lot","log","cog"]

* init: dict has all words, beginSet={"hit"}, endSet={"cog"}, len=1, visited={}
* Iteration 1 (expand {"hit"}):

  * neighbors generated → "hot" is valid (in dict, not visited) → next={"hot"}, visited={"hot"}
  * endSet doesn't contain "hot" → not found
  * after loop: beginSet={"hot"}, len=2
* Iteration 2 (expand {"hot"}):

  * neighbors → "dot","lot" valid → next={"dot","lot"}, visited={"hot","dot","lot"}
  * after loop: beginSet={"dot","lot"}, len=3
* Now beginSet size (2) > endSet (1) → swap: beginSet={"cog"}, endSet={"dot","lot"}
* Expand {"cog"}:

  * neighbors → "dog","log" (valid) — check if in endSet? not yet.
  * next={"dog","log"}, visited add them
  * beginSet={"dog","log"}, len=4
* Expand {"dog","log"}:

  * When expanding "dog", one neighbor becomes "dot" — and "dot" is in `endSet` → we detect `endSet.contains(newWord)` and return `len+1` = 4+1 = 5.
* Path length = 5 → "hit" -> "hot" -> "dot" -> "dog" -> "cog" (5 words). Correct.

(Trace shows how two fronts meet in the middle and we return the minimal length.)

---

# 5) Why `len + 1` is correct (intuition)

* `len` is number of words from `beginWord` to words currently in `beginSet`.
* When we generate `newWord` it is at distance `len+1` from `beginWord`.
* If `newWord` exists in `endSet`, that means there is a path from `newWord` to `endWord` from the other side — so combined path includes the `len+1` part plus the path from `newWord` to `endWord` (but since `endSet` contains frontier words expanded level-by-level, when they meet first time it guarantees shortest combined length). Standard bidirectional BFS proof shows meeting at first intersection gives shortest path. Hence `len+1` returns shortest transformation length.

---

# 6) Complexity

* Let `N` = number of words in wordList, `L` = length of each word.
* **Time:** Each visited word we try changing each of its `L` positions with 26 letters → `O(N * L * 26)`. 26 is constant → often quoted as `O(N * L)`.

  * Bidirectional approach practically reduces the explored portion significantly (≈ `O(b^(d/2))` vs `O(b^d)`).
* **Space:** `O(N)` for sets (`dict`, `visited`, frontiers).

---

# 7) Edge cases / pitfalls / implementation notes

* **Check `endWord` present** in wordList (code does that).
* **beginWord == endWord**: Problem usually assumes different; if equal and you want to handle, you can return 1 immediately. (Current code does not explicitly handle begin==end — but many problem statements guarantee they differ.)
* **Visited vs removing from dict**: You used `visited` to avoid duplicates. Alternative: immediately `dict.remove(newWord)` when you add it to next, which also works.
* **Why swap sets?** To always expand smaller frontier — reduces branching and makes it much faster in practice.
* **Returning `len+1` vs other formulas**: This code maintains `len` as distance from begin side, so `len+1` on encountering match is correct in this convention.
* **Space for `visited`** ensures same word isn't added twice in same or later levels — avoids exponential blowup.

---

# 8) Further optimizations (optional, interview points)

* **Preprocessing wildcard patterns** (`*ot` → ["hot","dot","lot"]) to get neighbours in O(L * neighbors) instead of trying all 26 letters. Useful when alphabet is large or L large.
* **Bi-directional + wildcard mapping** gives very fast performance in practice.
* Use arrays or ints for visited states if memory/constant factors matter.

---

# 9) Interview-ready summary (short)

* Say: *“Most optimized approach is Bidirectional BFS — we expand from both `beginWord` and `endWord`, always expanding the smaller frontier and generating one-letter neighbours. This reduces search space significantly and finds shortest path faster than single-ended BFS. Complexity roughly O(N * L) with O(N) extra space.”*
* Mention critical implementation details: use `HashSet` for O(1) lookups, `visited` to avoid duplicates, swap frontiers to expand smaller set.
