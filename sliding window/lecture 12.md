# Brute Force
```java

class Solution {
    public String minWindow(String s, String t) {

        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";

        // Make freq map for string t
        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        // Check all substrings
        for (int start = 0; start < n; start++) {
            int[] freq = new int[128];

            for (int end = start; end < n; end++) {

                // Add character to freq
                freq[s.charAt(end)]++;

                // If current substring contains all chars of t
                if (containsAll(freq, need)) {
                    int windowLen = end - start + 1;

                    if (windowLen < minLen) {
                        minLen = windowLen;
                        result = s.substring(start, end + 1);
                    }
                    break;  // No need to increase end because longer window won't be minimum
                }
            }
        }

        return result;
    }

    // Check if freq of substring satisfies need of t
    private boolean containsAll(int[] freq, int[] need) {
        for (int c = 0; c < 128; c++) {
            if (freq[c] < need[c]) return false;
        }
        return true;
    }
}

```
# cleansest code 
```java
class Solution {
    public String minWindow(String s, String t) {

        if (t.length() > s.length()) return "";

        // Frequency of characters needed from t
        int[] need = new int[128];
        for (char c : t.toCharArray()) need[c]++;

        int left = 0, count = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);
            if (need[c] > 0) count--;  
            need[c]--; 

            // When window contains all chars of t
            while (count == 0) {

                // update minimum window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Now try to shrink from left
                char leftChar = s.charAt(left);
                need[leftChar]++;

                if (need[leftChar] > 0) count++;

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```
## Explanation:

