# leetcode problem 1859

***good one***
---
***https://leetcode.com/problems/sorting-the-sentence/description/*** 
---

### some important points to leran:

---
```
1.   class Solution {
    public String sortSentence(String s) {
        String[] arr = s.split(" ");
        int i = 0;

        while (i < arr.length) {
            int l = arr[i].length();
            int correctIndex = (arr[i].charAt(l - 1) - '0') - 1; // position → 0-based index

            if (correctIndex != i) {
                swap(arr, i, correctIndex);
            } else {
                i++;
            }
        }

        // Remove trailing digits and join
        for (int k = 0; k < arr.length; k++) {
            arr[k] = arr[k].substring(0, arr[k].length() - 1);
        }
        return String.join(" ", arr);
    }

    private void swap(String[] arr, int first, int second) {
        String temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}

```
---
---
```
2. String[] res = new String[arr.length];

   ----res = [null, null, null, null]
```
---
---
```
3.  String[] arr = {"This", "is", "a", "sentence"};
String result = String.join(" ", arr);
System.out.println(result); 
// Output: This is a sentence

```
---
---
```
4.last  mein extra space na aaye 

  for (int i = 0; i < arr.length; i++) {
    sb.append(arr[i]);
    if (i < arr.length - 1) { // last me extra space na aaye
        sb.append(" ");
    }
}


String.join() → Short, readable, clean.

Manual loop → Zyada control (agar tu special formatting chaahe).

Dono ka time complexity O(n) hi hai.
  

```
---
---
```
5.class Solution {
    public String sortSentence(String s) {
        String[] arr = s.split(" ");       // 1️⃣
        String[] res = new String[arr.length]; // 2️⃣

        for (String element : arr) {       // 3️⃣
            int l = element.length();
            
            // 🔍 Find where digits start from end
            int j = l - 1;  
            while (j >= 0 && Character.isDigit(element.charAt(j))) j--;  // 4️⃣
            
            // 🎯 Extract position number
            int pos = Integer.parseInt(element.substring(j + 1)); // 5️⃣
            
            // ✂ Remove the digits from the word
            String word = element.substring(0, j + 1);            // 6️⃣
            
            // 📌 Place the word in correct position
            res[pos - 1] = word;                                  // 7️⃣
        }

        return String.join(" ", res);     // 8️⃣
    }
}

```
---
---
```
6.   String arr[] = new String[str.length];
```
---
---
```
7. String [] str = s.trim().split("\\s+");
trim() → Start/end ke extra spaces hata deta hai.

split("\\s+") → 1 ya zyada spaces par split karta hai (\\s+ means "one or more spaces").
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
