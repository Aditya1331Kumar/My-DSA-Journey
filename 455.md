# **sliding window**
---
*** *** 
---

## some important points to leran:

---
 # gyan
 * Any number XOR with itself is always 0
  * if a⊕x=1 then x=a⊕1
  * XOR with 1 → flips the bit.

* XOR with 0 → keeps the bit same
* So when you do a xor 1, only the last bit (LSB) of a flips
 *a^1=awith its last bit toggled (even ↔ odd)

  - So:

  - If a is even → result = a+1

  - If a is odd → result = a-1
### xor property
x ^ x = 0

x ^ 0 = x

 a ^ b = c

is equals to

 a ^ c = b

is equals to

 b ^ c = a

### now
```java
a = b ^ c

a ^ b = (b ^ c) ^ b

a ^ b = c ^ (b ^ b)

a ^ b = c ^ 0

a ^ b = c
```
### if b =c

a = b ^ b

a = 0

```java
int n = 29;
int count = Integer.bitCount(n);  // returns 4


a ^ a = 0

int n = 29;
String binaryStr = Integer.toBinaryString(n);
System.out.println(binaryStr);  // prints 11101


String bitStr = "11101";
int num = Integer.parseInt(bitStr, 2);  // base 2
System.out.println(num);  // Output: 29


String str = "hello";
StringBuilder sb = new StringBuilder(str);
sb.append("text");    // Add at end
sb.insert(2, "X");    // Insert at index 2
sb.delete(2, 4);      // Delete from index 2 to 3
sb.reverse();         // Reverse the string
sb.charAt(0);         // Access character at index 0
for (int i = 0; i < sb.length(); i++) {
    char c = sb.charAt(i);
    System.out.println(c);
}
// Convert to char array
for (char c : sb.toString().toCharArray()) {
    System.out.println(c);
}
sb.chars().forEach(c -> System.out.println((char)c));
sb.chars() → IntStream of character codes
// Cast int to char while printing

sb.setCharAt(1, 'a');
// setCharAt(index, char) → replaces the character at the given index.

// Replace character at index 1
sb.deleteCharAt(1);          // remove 'e'
sb.insert(1, 'a');           // insert 'a' at index 1


sb.replace(1, 3, "ey");   

// replace(startIndex, endIndex, String) → replaces substring

char[] chars = str.toCharArray();

String str = new String(chars);

String str2 = String.valueOf(chars);



```
* XOR flips bits. To turn a into a + 1, you need to flip the rightmost 0 to 1 and all trailing 1s to 0.

* Formula
```java
a + 1 = a ^ ((~a) & (a + 1))


// ~a → flips all bits of a

// (~a) & (a + 1) → isolates the rightmost 0 bit that needs to flip
```
---
---
 # bitwise ka gyan

##  Flip a single bit at position `k`**

Use **XOR with a mask**:

```java
int n = 5;          // 0101 (binary)
int k = 1;          // flip the 1st bit (counting from 0, rightmost)

n = n ^ (1 << k);

System.out.println(n);  // Output: 7 (0111)
```

* `1 << k` → creates a mask with `1` at position `k`.
* `^` (XOR) flips that bit only.

---

### **2️⃣ Flip all bits (bitwise NOT)**

```java
int n = 5;              // 000...0101
int flipped = ~n;

System.out.println(flipped);
```

⚠️ This flips *every bit*, including leading 0s (so in Java you’ll get a negative number because integers are 32-bit signed).

If you only want to flip **within certain number of bits**, use a mask:

```java
int n = 5;              // 0101
int bits = 4;           // say we want only 4-bit flip

int mask = (1 << bits) - 1;  // 1111
int flipped = (~n) & mask;

System.out.println(flipped); // 1010 (decimal 10)
```

---
 # Assign cookies
```java
class Solution {
    public int findContentChildren(int[] student, int[] cookie) {
        
        int n = student.length;
        int m = cookie.length;

        // Sort both arrays
        Arrays.sort(student);
        Arrays.sort(cookie);

        

        int i=0 ;int j=0; int skip = 0;
        while(i<n&& j<m){
if(student[i]<=cookie[j]){
    i++;
}
j++;

        } 
                


        return i;

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