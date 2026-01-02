```java
int[] freq = new int[26];

        // Left pointer of sliding window
        int left = 0;

        // Tracks the highest frequency in the window
        int maxFreq = 0;

        // Stores result
        int maxLen = 0;

        // Traverse the string with right pointer
        for (int right = 0; right < s.length(); right++) {

            // Increment count of current character
            freq[s.charAt(right) - 'A']++;

            // Update max frequency in current window
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            // If number of changes exceeds k, shrink window
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update result with valid window length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
 ```

 ## explanation:
 Chalo **LeetCode 424 – Longest Repeating Character Replacement** ka yeh solution step-by-step bilkul easy language mein samajhte hain.
Iska main logic × sliding window + frequency tracking.

---

# 🔥 Problem:

String me se **maximum length ka substring** find karo jise tum **max k replacements** karke *saare characters same* bana sakte ho.

Example:
`AABABBA`, k = 1
Answer = 4 → `"AABA"` (ek 'B' ko 'A' bana diya)

---

# 🧠 MAIN IDEA (intuition)

Hum ek window maintain karenge `[left..right]` jisme:

* `maxFreq` = window me **sabse zyada baar aane wala character**
* Window size = `right - left + 1`

Agar tum window ko “all same character” banana chahte ho, tumhe replace karna padega:

```
replacementsRequired = windowSize - maxFreq
```

If:

```
replacementsRequired <= k
```

→ window valid
Else
→ window ko shrink karo (left++)

---

# ✔️ Step-by-Step Code Explanation

---

## **1. Frequency array**

```java
int[] freq = new int[26];
```

Uppercase letters 'A'–'Z' ke liye freq array.

---

## **2. Left pointer**

```java
int left = 0;
```

---

## **3. maxFreq**

```java
int maxFreq = 0;
```

Window me **sabse zyada baar aaya hua ek character**.

---

## **4. maxLen**

```java
int maxLen = 0;
```

---

## **5. Loop with right pointer**

```java
for (int right = 0; right < s.length(); right++) {
```

---

## **6. Current char count badhao**

```java
freq[s.charAt(right) - 'A']++;
```

---

## **7. maxFreq update karo**

```java
maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
```

Example: Window = "AABA"
A = 3, B = 1
`maxFreq = 3`

---

## **8. Check if window valid hai:**

```java
while ((right - left + 1) - maxFreq > k) {
```

Meaning:

```
windowLength - maxFreq = characters to replace
```

Agar replacements > k
→ Window ko left se shrink karo:

```java
freq[s.charAt(left) - 'A']--;
left++;
```

---

## **9. Valid window length store**

```java
maxLen = Math.max(maxLen, right - left + 1);
```

---

# 💥 DRY RUN Example – s="AABABBA", k=1

Window expand hota jayega:

| Window                     | freq    | maxFreq | size | replace | Valid? | maxLen         |
| -------------------------- | ------- | ------- | ---- | ------- | ------ | -------------- |
| A                          | A=1     | 1       | 1    | 0       | ✔      | 1              |
| AA                         | A=2     | 2       | 2    | 0       | ✔      | 2              |
| AAB                        | A=2,B=1 | 2       | 3    | 1       | ✔      | 3              |
| AABA                       | A=3,B=1 | 3       | 4    | 1       | ✔      | 4              |
| AABAB                      | A=3,B=2 | 3       | 5    | 2       | ❌      | shrink         |
| Shrink left: window → ABAB |         |         |      |         |        |                |
| ABAB                       | A=2,B=2 | 2       | 4    | 2       | ❌      | shrink         |
| Shrink left → BAB          |         |         |      |         |        |                |
| BAB                        | A=1,B=2 | 2       | 3    | 1       | ✔      | maxLen stays 4 |

Continue… maximum kabhi 4 se above nahi jayega.

Final Answer → **4**

---

# ✔️ Final takeaway (super important)

## 👉 Window ko “maxFreq character” ke around expand karte jao.

## 👉 Only shrink jab **replace > k** ho.

## 👉 `maxFreq` ko shrink karte waqt kabhi reset nahi karte — ye optimization trick hai.

---

Agar tum chaho to main tumhari **khud ki code attempt** bhi debug karke samjha dunga.
