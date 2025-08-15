# leetcode problem 1356
***very good Question*** 
 https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/submissions/1724116181/

## some important points to leran:
1. sorting mein custom coparator ka use hua hai 
     - badhiya chij h ye

2. some differences
  - int[] is a primitive array — fast but limited. 
  - int[] is a primitive array — fast but limited.

3.  swap ka method yaad hona chayee

4. int bitsA = Integer.bitCount(a);
   - Bit count means the number of 1s in the binary representation of a number.

5. Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
 - Arrays.stream(arr) → Converts int[] to an IntStream (a stream of primitive ints).

- .boxed() → Converts each int in the stream to an Integer object (this is called boxing).

- .toArray(Integer[]::new) → Collects the boxed stream into a new Integer[] array.

