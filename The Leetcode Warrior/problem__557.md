# leetcode problem 557 check nhi kiye h
***not that important*** 
- ***just reversing words in sentence***

## some important points to leran:
-   int[] freq = new int[26];
        for (char c: moves.toCharArray()) freq[c - 'A']++;
        return freq['D' - 'A'] == freq['U' - 'A'] && freq['L' - 'A'] == freq['R' - 'A'];

    - is se kya hua ? 
      - sab alphabet ki counting mili

-   static {
        String[] arr = new String[1];
        for (int i = 0; i < 500; i++){
            judgeCircle("");
        }
    }

    - is se kya hua ? 
      - static warmup blocks of the jvm
      - ghat gaya 1 ms
      - its unfair
- char thodi na reverse ho sakta hai
- return x==0 && y==0; boolean hi dega