# mera tarika :
```java
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
       
        long bad = 0;          // 🔁 yaha long
        int a = 0, b = 0, c = 0;
        int l = 0, r = 0;

        while (r < n) {
            char ch = s.charAt(r);
            if (ch == 'a') a++;
            if (ch == 'b') b++;
            if (ch == 'c') c++;

            while (a != 0 && b != 0 && c != 0) {
                char left = s.charAt(l);
                if (left == 'a') a--;
                else if (left == 'b') b--;
                else if (left == 'c') c--;
                l++;
            }

            bad += (r - l + 1);  // 🔁 long me add kar rahe
            r++;
        }

        long total = (long) n * (n + 1) / 2;  // 🔁 cast to long
        long good = total - bad;

        return (int) good;  // final result int me fit karega
    }
}
```

## ek or khatrnaaak  :
```java
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int a = 0, b = 0, c = 0;
        int l = 0, count = 0;

        for (int r = 0; r < n; r++) {
            char ch = s.charAt(r);
            if (ch == 'a') a++;
            if (ch == 'b') b++;
            if (ch == 'c') c++;

            while (a > 0 && b > 0 && c > 0) {
                // all substrings starting from l..r are valid
                count += (n - r);

                // shrink left side
                char leftChar = s.charAt(l);
                if (leftChar == 'a') a--;
                if (leftChar == 'b') b--;
                if (leftChar == 'c') c--;
                l++;
            }
        }

        return count;
    }
}
```