 # brute force
 ```java
 class Solution {
    public boolean checkValidString(String s) {
        int open=0,index=0;
        return isValid(s,0,0);
    }
   

    // Recursive function to check if the parenthesis string is valid
    public boolean isValid(String s, int index, int open) {

        // If the number of open parentheses becomes negative, return false
        if (open < 0) return false;

        // If we've reached the end of the string, return true if all open brackets are closed
        if (index == s.length()) return open == 0;

        // Get the current character
        char c = s.charAt(index);

        // If it's an opening bracket '(', increase the count of open
        if (c == '(') {
            return isValid(s, index + 1, open + 1);
        }

        // If it's a closing bracket ')', decrease the count of open
        else if (c == ')') {
            return isValid(s, index + 1, open - 1);
        }

        // If it's '*', we try all 3 possibilities:
        // 1. Treat '*' as empty string
        // 2. Treat '*' as '('
        // 3. Treat '*' as ')'
        else {
            return isValid(s, index + 1, open) ||
                   isValid(s, index + 1, open + 1) ||
                   isValid(s, index + 1, open - 1);
        }
       
    }
}

```
### Complexity Analysis

* Time Complexity: O(3n) the worst case, every '*' can be replaced with '(', ')' or an empty string. For each '*', we have 3 choices, so with k '*' characters, we make 3k recursive calls. If the input string has length n, and all are '*', the time complexity becomes exponential.

* Space Complexity: O(n) ,This is due to the maximum depth of the recursive call stack. At most, there are n recursive calls at any time (one for each character).


## sir jo bole the
```java
class Solution {
    int dp[][];
    public boolean checkValidString(String s) {
        int n = s.length();
        dp = new int[n][n];
        for(int i=0;i<n;i++) {
            Arrays.fill(dp[i], -1);
        }
        return isValidPara(s, 0, 0);
    }

    public boolean isValidPara(String s, int i, int opening) {
        if(i == s.length()) {
            return opening==0;
        }
        if(dp[i][opening] != -1) {
            return dp[i][opening]==1;
        }
        boolean isValid = false;
        if(s.charAt(i) == '*') {
            isValid = isValid || isValidPara(s, i+1, opening+1); //treat as (
            if(opening>0) {
                isValid = isValid || isValidPara(s, i+1, opening-1); //treat as )
            }
            isValid = isValid || isValidPara(s, i+1, opening); //treat as empty
        } else if(s.charAt(i) == '(') {
            isValid = isValidPara(s, i+1, opening+1);
        } else if(opening > 0){
            isValid = isValidPara(s, i+1, opening-1);
        }
        dp[i][opening] = isValid?1:0;
        return isValid;
    }
}
```


# sabse optimal
```java
class Solution {
    public boolean checkValidString(String s) {

        // Variable to track minimum possible open brackets at current index
        int minOpen = 0;

        // Variable to track maximum possible open brackets at current index
        int maxOpen = 0;

        // Traverse through each character in the string
        for (int i = 0; i < s.length(); i++) {

            // Get current character
            char c = s.charAt(i);

            // If character is '(', it increases both minOpen and maxOpen
            if (c == '(') {
                minOpen++;
                maxOpen++;
            }

            // If character is ')', it decreases both minOpen and maxOpen
            else if (c == ')') {
                minOpen--;
                maxOpen--;
            }

            // If character is '*', it can be '(', ')' or ''
            else {
                  // if '*' is ')'
                minOpen--;   
                 // if '*' is '('
                maxOpen++;    
            }

            // If maxOpen becomes negative, too many closing brackets : invalid string
            if (maxOpen < 0) return false;

            // minOpen can't go below 0, as we can't have negative unmatched '('
            if (minOpen < 0) minOpen = 0;
        }

        // If minOpen is 0 at the end, it's a valid configuration
        return minOpen == 0;
       
    }
}





```

### Complexity Analysis

* Time Complexity: O(N), where N is the length of the input string.We traverse the string once in a single pass, updating min and max possible open brackets at each character.

* Space Complexity:O(1),constant space. We use only a few integer variables (minOpen and maxOpen) regardless of the input size.

## bhai bhai 
```java
class Solution {
    public boolean checkValidString(String s) {
        int low =0;
        int high =0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='('){
                low++;
                high++;
            }else if(ch==')'){
                low--;
                high--;
            }else{
                low--;
                high++;
            }
            if(high<0){
                return false;
            }
            if(low<0){
                low = 0;
            }
        }
        return low==0;
        
    }
}
```

