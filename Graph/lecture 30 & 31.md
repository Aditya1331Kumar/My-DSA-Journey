# Word Ladder-II

## Intuition:
The intuition behind using the BFS traversal technique for these kinds of problems is that if we notice carefully, we go on replacing the characters one by one which seems just like we’re moving level-wise in order to reach the destination i.e. the targetWord. Here, in the example below we can notice there are two possible paths in order to reach the targetWord.


Contrary to the previous problem, here we do not stop the traversal on the first occurrence of the targetWord, but rather continue it for as many occurrences of the word as possible as we need all the shortest possible sequences in order to reach the destination word. The only trick here is that we do not have to delete a particular word immediately from the wordList even if during the replacement of characters it matches with the transformed word. Instead, we delete it after the traversal for a particular level when completed which allows us to explore all possible paths. This allows us to discover multiple sequences in order to reach the targetWord involving similar words. 

From the above figure, we can configure that there can be 2 shortest possible sequences in order to reach the word ‘cog’.

Approach:
This problem uses the BFS traversal technique for finding out all the shortest possible transformation sequences by exploring all possible ways in which we can reach the targetWord.

Initial configuration:

Queue: Define a queue data structure to store the level-wise formed sequences. The queue will be storing a List of strings, which will be representing the path till now. The last word in the list will be the last converted word. 
Hash set: Create a hash set to store the elements present in the word list to carry out the search and delete operations in O(1) time. 
Vector: Define a 1D vector ‘usedOnLevel’ to store the words which are currently being used for transformation on a particular level and a 2D vector ‘ans’ for storing all the shortest sequences of transformation.
The Algorithm for this problem involves the following steps:

Firstly, we start by creating a hash set to store all the elements present in the wordList which would make the search and delete operations faster for us to implement.
Next, we create a Queue data structure for storing the successive sequences/ path in the form of a vector which on transformation would lead us to the target word.
Now, we add the startWord to the queue as a List and also push it into the usedOnLevel vector to denote that this word is currently being used for transformation in this particular level.
Pop the first element out of the queue and carry out the BFS traversal, where for each word that popped out from the back of the sequence present at the top of the queue, we check for all of its characters by replacing them with ‘a’ - ‘z’ if they are present in the wordList or not. In case a word is present in the wordList, we simply first push it onto the usedOnLevel vector and do not delete it from the wordList immediately.
Now, push that word into the vector containing the previous sequence and add it to the queue. So we will get a new path, but we need to explore other paths as well, so pop the word out of the list to explore other paths.
After completion of traversal on a particular level, we can now delete all the words that were currently being used on that level from the usedOnLevel vector which ensures that these words won’t be used again in the future, as using them in the later stages will mean that it won’t be the shortest path anymore.
If at any point in time we find out that the last word in the sequence present at the top of the queue is equal to the target word, we simply push the sequence into the resultant vector if the resultant vector ‘ans’ is empty.
If the vector is not empty, we check if the current sequence length is equal to the first element added in the ans vector or not. This has to be checked because we need the shortest possible transformation sequences.
In case, there is no transformation sequence possible, we return an empty 2D vector.
Note: If you wish to see the dry run of the above approach, you can watch the video attached to this article. 
# soln: 
```Java
import java.util.*;
import java.lang.*;
import java.io.*;

// A comparator function to sort the answer.
class comp implements Comparator < ArrayList < String >> {

    public int compare(ArrayList < String > a, ArrayList < String > b) {
        String x = "";
        String y = "";
        for (int i = 0; i < a.size(); i++)
            x += a.get(i);
        for (int i = 0; i < b.size(); i++)
            y += b.get(i);
        return x.compareTo(y);
    }
}

public class Main {

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
        ArrayList < ArrayList < String >> ans = obj.findSequences(startWord, targetWord, wordList);
        
        // If no transformation sequence is possible.
        if (ans.size() == 0)
            System.out.println(-1);
        else {

            Collections.sort(ans, new comp());
            for (int i = 0; i < ans.size(); i++) {
                for (int j = 0; j < ans.get(i).size(); j++) {
                    System.out.print(ans.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
    }
}

class Solution {
    public ArrayList < ArrayList < String >> findSequences(String startWord, String targetWord,
        String[] wordList) {

        // Push all values of wordList into a set
        // to make deletion from it easier and in less time complexity.
        Set < String > st = new HashSet < String > ();
        int len = wordList.length;
        for (int i = 0; i < len; i++) {
            st.add(wordList[i]);
        }

        // Creating a queue ds which stores the words in a sequence which is
        // required to reach the targetWord after successive transformations.
        Queue < ArrayList < String >> q = new LinkedList < > ();
        ArrayList < String > ls = new ArrayList < > ();
        ls.add(startWord);
        q.add(ls);
        ArrayList < String > usedOnLevel = new ArrayList < > ();
        usedOnLevel.add(startWord);
        int level = 0;

        // A vector to store the resultant transformation sequence.
        ArrayList < ArrayList < String >> ans = new ArrayList < > ();
        int cnt = 0;

        // BFS traversal with pushing the new formed sequence in queue 
        // when after a transformation, a word is found in wordList.
        while (!q.isEmpty()) {
            cnt++;
            ArrayList < String > vec = q.peek();
            q.remove();

             // Now, erase all words that have been
             // used in the previous levels to transform
             if (vec.size() > level) {
                level++;
                for (String it: usedOnLevel) {
                    st.remove(it);
                }
            }

            String word = vec.get(vec.size() - 1);

            // store the answers if the end word matches with targetWord.
            if (word.equals(targetWord)) {
                // the first sequence where we reached the end.
                if (ans.size() == 0) {
                    ans.add(vec);
                } else if (ans.get(0).size() == vec.size()) {
                    ans.add(vec);
                }
            }
            for (int i = 0; i < word.length(); i++) {

                // Now, replace each character of ‘word’ with char
                // from a-z then check if ‘word’ exists in wordList.
                for (char c = 'a'; c <= 'z'; c++) {
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = c;
                    String replacedWord = new String(replacedCharArray);
                    if (st.contains(replacedWord) == true) {
                        vec.add(replacedWord);
                        // Java works by reference, so enter the copy of vec
                        // otherwise if you remove word from vec in next lines, it will 
                        // remove from everywhere 
                        ArrayList < String > temp = new ArrayList < > (vec);
                        q.add(temp);
                        // mark as visited on the level 
                        usedOnLevel.add(replacedWord);
                        vec.remove(vec.size() - 1);
                    }
                }

            }
        }
        return ans;
    }
}
```
```java
Output: 

der des dfs 

der dfr dfs
```

* Time Complexity and Space Complexity: It cannot be predicted for this particular algorithm because there can be multiple sequences of transformation from startWord to targetWord depending upon the example, so we cannot define a fixed range of time or space in which this program would run for all the test cases.

* Note: This approach/code will give TLE when solved on the Leetcode platform due to the strict time constraints being put up there. So, you need to optimize it to a greater extent in order to pass all the test cases for LeetCode.



# most optimized 
```java
class Solution {
    HashMap<String, Integer> mpp;
    List<List<String>> ans;
    String b;

    private void dfs(String word, List<String> seq) {
        if(word.equals(b)) {
            Collections.reverse(seq);
            ans.add(new ArrayList<>(seq));
            Collections.reverse(seq);
            return;
        }

        int steps = mpp.get(word);
        int sz = word.length();
        for(int i = 0; i < sz; i++) {
            char[] arr = word.toCharArray();
            char original = arr[i];
            for(char ch = 'a'; ch <= 'z'; ch++) {
                arr[i] = ch;
                word = new String(arr);
                if(mpp.containsKey(word)
                   && mpp.get(word) + 1 == steps) {
                    seq.add(word);
                    dfs(word, seq);
                    seq.remove(seq.size() - 1);
                }
            }
            arr[i] = original;
            word = new String(arr);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        ans = new ArrayList<>();
        HashSet<String> st = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        b = beginWord;
        q.add(beginWord);
        mpp = new HashMap<>();
        mpp.put(beginWord, 1);
        int size = beginWord.length();
        st.remove(beginWord);
        while(!q.isEmpty()) {
            String word = q.peek();
            int steps = mpp.get(word);
            q.poll();
            if(word.equals(endWord)) break;
            for(int i = 0; i < size; i++) {
                char[] arr = word.toCharArray();
                char original = arr[i];
                for(char ch = 'a'; ch <= 'z'; ch++) {
                    arr[i] = ch;
                    word = new String(arr);
                    if(st.contains(word)) {
                        q.add(word);
                        st.remove(word);
                        mpp.put(word, steps + 1);
                    }
                }
                arr[i] = original;
                word = new String(arr);
            }
        }
        for(Map.Entry<String, Integer> it : mpp.entrySet()) {
            System.out.println(it.getKey() + " " + it.getValue());
        }

        if(mpp.containsKey(endWord)) {
            List<String> seq = new ArrayList<>();
            seq.add(endWord);
            dfs(endWord, seq);
        }

        return ans;
    }
}

```


# most optimized :

```java
import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        if (!dict.contains(endWord)) return ans;

        // BFS: distance map + parents map
        Map<String, Integer> dist = new HashMap<>();
        Map<String, List<String>> parents = new HashMap<>();
        bfs(beginWord, endWord, dict, dist, parents);

        // Backtrack DFS: from endWord to beginWord
        if (!dist.containsKey(endWord)) return ans; // no path
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, parents, path, ans);
        return ans;
    }

    private void bfs(String begin, String end, Set<String> dict,
                     Map<String, Integer> dist, Map<String, List<String>> parents) {
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        dist.put(begin, 0);

        while (!q.isEmpty()) {
            int size = q.size();
            Set<String> visitedThisLevel = new HashSet<>();
            for (int k = 0; k < size; k++) {
                String word = q.poll();
                int steps = dist.get(word);

                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char orig = arr[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == orig) continue;
                        arr[i] = ch;
                        String next = new String(arr);

                        if (dict.contains(next)) {
                            // parents map
                            parents.computeIfAbsent(next, x -> new ArrayList<>()).add(word);

                            if (!dist.containsKey(next)) {
                                dist.put(next, steps + 1);
                                q.add(next);
                                visitedThisLevel.add(next);
                            }
                        }
                    }
                    arr[i] = orig;
                }
            }
            dict.removeAll(visitedThisLevel); // remove after each level
        }
    }

    private void dfs(String word, String begin, Map<String, List<String>> parents,
                     List<String> path, List<List<String>> ans) {
        if (word.equals(begin)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            ans.add(temp);
            return;
        }

        if (!parents.containsKey(word)) return;

        for (String p : parents.get(word)) {
            path.add(p);
            dfs(p, begin, parents, path, ans);
            path.remove(path.size() - 1);
        }
    }
}

```

Perfect 👌 Ye code actually **LeetCode Word Ladder II (126)** ka ek optimized solution hai.
Main tumhe step-by-step samjhata hoon **brief mein**, taki tumhe pura flow clear ho jaye.

---

# 🔹 Step 1: Input Setup

```java
Set<String> dict = new HashSet<>(wordList);
List<List<String>> ans = new ArrayList<>();
if (!dict.contains(endWord)) return ans;
```

* Word list ko `HashSet` me convert kiya taaki lookup **O(1)** ho.
* Agar `endWord` dictionary me hi nahi hai, toh direct return (no path).

---

# 🔹 Step 2: BFS Preparation

```java
Map<String, Integer> dist = new HashMap<>();
Map<String, List<String>> parents = new HashMap<>();
bfs(beginWord, endWord, dict, dist, parents);
```

* `dist`: har word tak ka **shortest distance** store karega (steps from beginWord).
* `parents`: har word ke liye uske **possible predecessors** store honge.
  👉 Ye isliye ki backtracking me easily path generate ho.

---

# 🔹 Step 3: BFS Traversal

```java
Queue<String> q = new LinkedList<>();
q.add(begin);
dist.put(begin, 0);
```

* Normal BFS start from `beginWord`.
* `dist[beginWord] = 0` matlab distance 0.

### Inside BFS

```java
while (!q.isEmpty()) {
    int size = q.size();
    Set<String> visitedThisLevel = new HashSet<>();
```

* BFS level-by-level chalega.
* Ek temporary `visitedThisLevel` rakha gaya taaki ek hi level me same word bar-bar use na ho.

---

### Generate neighbors

```java
for (int i = 0; i < arr.length; i++) {
    char orig = arr[i];
    for (char ch = 'a'; ch <= 'z'; ch++) {
        if (ch == orig) continue;
        arr[i] = ch;
        String next = new String(arr);
```

* Har position pe `'a' → 'z'` tak replace karke new words generate karte hain.
* Example: `"hit"` → `"hot"`, `"hat"`, `"hut"`, etc.

---

### Check valid neighbors

```java
if (dict.contains(next)) {
    parents.computeIfAbsent(next, x -> new ArrayList<>()).add(word);

    if (!dist.containsKey(next)) {
        dist.put(next, steps + 1);
        q.add(next);
        visitedThisLevel.add(next);
    }
}
```

* Agar word dictionary me hai to:

  * **Parents map update** karo (`next` ka ek parent = `word`).
  * Agar `next` pehle visit nahi hua, to queue me add karo aur distance store karo.

👉 Important: `dict.removeAll(visitedThisLevel)` loop ke baad hota hai, matlab ek level finish hone tak sabko chance milta hai.

---

# 🔹 Step 4: DFS Backtracking

```java
if (!dist.containsKey(endWord)) return ans;
List<String> path = new ArrayList<>();
path.add(endWord);
dfs(endWord, beginWord, parents, path, ans);
```

* Agar `endWord` ka distance hi nahi mila, toh answer empty hai.
* Otherwise `dfs` se backtrack karte hain `endWord` → `beginWord`.

---

# 🔹 Step 5: DFS Function

```java
if (word.equals(begin)) {
    List<String> temp = new ArrayList<>(path);
    Collections.reverse(temp);
    ans.add(temp);
    return;
}
```

* Agar DFS me `beginWord` tak pohonch gaye, path reverse karke answer me add kar do.

### Otherwise explore parents:

```java
for (String p : parents.get(word)) {
    path.add(p);
    dfs(p, begin, parents, path, ans);
    path.remove(path.size() - 1);
}
```

* Har parent ko try karo aur recursion se aur paths explore karo.
* Backtracking se `path` clean hota hai after each call.

---

# 🔹 Example Flow

Input:
`begin = "hit"`, `end = "cog"`, `wordList = ["hot","dot","dog","lot","log","cog"]`

1. BFS distances:

   * `hit = 0`, `hot = 1`, `dot/lot = 2`, `dog/log = 3`, `cog = 4`

2. Parents map (shortest paths only):

   * `hot ← hit`
   * `dot ← hot`, `lot ← hot`
   * `dog ← dot`, `log ← lot`
   * `cog ← dog`, `cog ← log`

3. DFS backtrack:

   * `cog → dog → dot → hot → hit`
   * `cog → log → lot → hot → hit`

Final output:
`[["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]]` ✅

---

# 🔹 Why Optimized?

* BFS ensures **shortest distance only**.
* Parents map avoids redundant recomputation.
* DFS works only on shortest-path DAG (no extra exploration).
* Level-wise visited removal = minimal expansions.

---

👉 Ye code **time complexity** ~ `O(N × L × 26)` (N = number of words, L = word length) hai — jo iss problem ke liye best possible hai.

---

Bilkul — step-by-step aur detail mein samjhata hoon. Main pehle high-level idea doonga, fir code ko line-by-line walkthrough + ek chhota example trace, aur finally complexity + edge cases/optimizations bataunga.

# 1) Short summary (one-line)

Ye code **Bidirectional BFS** use karta hai: ek taraf se `beginWord` se search, dusri taraf se `endWord` se search — aur jab dono frontiers mil jaate hain tab shortest transformation length mil jati hai.

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

---

Agar chaho to main is explanation ko **short 2-3 sentence interview script** mein bhi convert kar du — tum directly bol sako. Ya phir ek aur example ke saath **detailed trace** chahiye to batao — main seedha woh bhi kar dunga.
