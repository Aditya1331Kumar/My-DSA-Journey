Length of Longest Substring without any Repeating Character

Mark as Completed

355


Problem Statement: Given a string, S. Find the length of the longest substring without repeating characters.

Examples
Example 1:  
Input: S = "abcddabac"  
Output: 4  
Explanation: The longest substring with distinct characters is "abcd", which has a length of 4.

Example 2:  
Input: S = "aaabbbccc"  
Output: 2  
Explanation: The longest substrings with distinct characters are "ab" and "bc", both having a length of 2.
Disclaimer: Here is the practice link to help you assess your knowledge better. It's highly recommend trying to solve it before looking at the solution.

Practice:
Solve Problem
Brute Force
Algorithm
Iterate through the array using a for loop from 0th index to sizeofArray - 1, to take all possible starting points of the substring into consideration.
Check if the current character is already in the hash array, if so, break out of the loop. Otherwise, as it is not visited yet, mark the character as 1 in the hash array, signifying that the current character is now visited.
Now, calculate the length of current substring and update the maximum length of the substrings found so far. Finally, return the maximum length.

# soln 
```java

import java.util.*;

class Solution {
    public int longestNonRepeatingSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        // Iterate through all possible starting points
        for (int i = 0; i < n; i++) {
            int[] hash = new int[256]; // For extended ASCII
            Arrays.fill(hash, 0);

            for (int j = i; j < n; j++) {
                if (hash[s.charAt(j)] == 1) break; // Found a repeat
                hash[s.charAt(j)] = 1;

                int len = j - i + 1;
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}

// Separate main class
public class Main {
    public static void main(String[] args) {
        String input = "cadbzabcd";

        Solution sol = new Solution();
        int length = sol.longestNonRepeatingSubstring(input);

        System.out.println("Length of longest substring without repeating characters: " + length);
    }
}
```
Complexity Analysis
Time Complexity: O(n^2), where n is the length of the string. This is because we are using a nested loop to check all possible substrings, leading to a quadratic time complexity.

Space Complexity: O(1), as we are using a fixed-size hash array of size 256 (for extended ASCII characters) and not using any additional data structures that grow with input size.

Optimal Approach
Algorithm
Initialize few variables as: l and r pointers to 0. These pointers will define the current window [l, r] that contains characters without repetition, maxLen to 0 to store the maximum length of substring found without repeating character.
Use an array hash of size 256 (assuming ASCII characters) to store the last occurrence index of each character in the string. Initialize all elements of hash to -1, indicating that no characters have been encountered yet.
Now, while r pointer is less than sizeOfArray - 1, iterate in the array. While iterating, check if current character has occured before using hash array. If so, updadate the left pointer to index of current character plus 1. This ensures that l moves past the last occurrence of of repeated character, effectively removing the repeated character from the window.
Calculate the length of the current substring as len = r - l + 1. Update maximum length of the substring found so far.
Update hash array with the current index r, indicating the most recent occurrence of character at pointer r in the string. Finally, return maximum length of the substring.

# soln

```java

import java.util.*;

class Solution {
    /* Function to find the longest substring
       without repeating characters */
    public int longestNonRepeatingSubstring(String s) {
        int n = s.length();

        // Assuming all ASCII characters
        int HashLen = 256;

        /* Hash table to store last
           occurrence of each character */
        int[] hash = new int[HashLen];

        /* Initialize hash table with
           -1 (indicating no occurrence) */
        Arrays.fill(hash, -1);

        int l = 0, r = 0, maxLen = 0;
        while (r < n) {
            /* If current character s.charAt(r) 
               is already in the substring */
            if (hash[s.charAt(r)] >= l) {
                /* Move left pointer to the right
                   of the last occurrence of s.charAt(r) */
                l = Math.max(hash[s.charAt(r)] + 1, l);
            }

            // Calculate the current substring length
            int len = r - l + 1;

            // Update maximum length found so far
            maxLen = Math.max(len, maxLen);

            /* Store the index of the current
               character in the hash table */
            hash[s.charAt(r)] = r;

            // Move right pointer to next position
            r++;
        }

        // Return the maximum length found
        return maxLen;
    }
}

// Separate main class
public class Main {
    public static void main(String[] args) {
        String s = "cadbzabcd";

        // Create an instance of the Solution class
        Solution sol = new Solution();

        int result = sol.longestNonRepeatingSubstring(s);

        // Output the maximum length
        System.out.println("The maximum length is:");
        System.out.println(result);
    }
}

```
Complexity Analysis
Time Complexity: O(n), where n is the length of the string. This is because we are using a single pass through the string with two pointers, leading to linear time complexity.

Space Complexity: O(1), as we are using a fixed-size hash array of size 256 (for ASCII characters) and not using any additional data structures that grow with input size.

