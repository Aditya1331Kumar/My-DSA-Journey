# Minimum coins



## Given an integer array of coins representing coins of different denominations and an integer amount representing a total amount of money. Return the fewest number of coins that are needed to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1. There are infinite numbers of coins of each type we have an infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes
```

Examples:
Input: coins = [1, 2, 5], amount = 11

Output: 3

Explanation: 11 = 5 + 5 + 1. We need 3 coins to make up the amount 11.

Input: coins = [2, 5], amount = 3

Output: -1

Explanation: It's not possible to make amount 3 with coins 2 and 5. Since we can't combine the coin 2 and 5 to make the amount 3, the output is -1.
```

# soln:
```java
import java.util.*;
public class Main {
  public static void main(String[] args) {

    int V = 49;
    ArrayList < Integer > ans = new ArrayList < > ();
    int coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
    int n = coins.length;
    for (int i = n - 1; i >= 0; i--) {
      while (V >= coins[i]) {
        V -= coins[i];
        ans.add(coins[i]);
      }
    }
    System.out.println("The minimum number of coins is "+ans.size());
    System.out.println("The coins are ");
    for (int i = 0; i < ans.size(); i++) {
      System.out.print(" " + ans.get(i));
    }

  }
}
```
