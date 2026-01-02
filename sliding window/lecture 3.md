Fruit Into Baskets

Mark as Completed

0


Problem Statement: There is only one row of fruit trees on the farm, oriented left to right. An integer array called fruits represents the trees, where fruits[i] denotes the kind of fruit produced by the ith tree.
The goal is to gather as much fruit as possible, adhering to the owner's stringent rules :

There are two baskets available, and each basket can only contain one kind of fruit. The quantity of fruit each basket can contain is unlimited.
Start at any tree, but as you proceed to the right, select exactly one fruit from each tree, including the starting tree. One of the baskets must hold the harvested fruits.
Once reaching a tree with fruit that cannot fit into any basket, stop.
Return the maximum number of fruits that can be picked.

Examples
Input :fruits = [1, 2, 1]
Output :3
Explanation : We will start from first tree.
The first tree produces the fruit of kind '1' and we will put that in the first basket.
The second tree produces the fruit of kind '2' and we will put that in the second basket.
The third tree produces the fruit of kind '1' and we have first basket that is already holding fruit of kind '1'. So we will put it in first basket.
Hence we were able to collect total of 3 fruits.


Input : fruits = [1, 2, 3, 2, 2]
Output : 4
Explanation : we will start from second tree.
The first basket contains fruits from second , fourth and fifth.
The second basket will contain fruit from third tree.
Hence we collected total of 4 fruits.

Disclaimer: Here is the practice link to help you assess your knowledge better. It's highly recommend trying to solve it before looking at the solution.

Brute force Approach
Algorithm
This brute force method checks each possible subarray starting from every index and determines how many fruits can be collected from there. We are allowed to pick fruits from any starting point and move only to the right, using two baskets. Each basket can only hold fruits of one type. To simulate this naively, we start from every index and collect fruits until we either: Reach the end of the array, or Encounter a third unique fruit type that can't go into the two baskets.
Initialize a variable to track the maximum number of fruits collected.
Iterate over all possible starting points of the array.
Use a HashSet (or a frequency map) to keep track of the fruit types in the current window.
For each start point, move the end pointer to the right while there are at most 2 fruit types.
Update the maximum fruits collected based on the window length.
Return the maximum number after checking all starting points.
Image 1
Image 2


Code
Cpp
Java
Python
Javascript


import java.util.*;

class Solution {
    // Function to calculate maximum fruits collected  
    // with at most two distinct types from any start point 
    public int totalFruit(int[] fruits) {

        // Variable to store the maximum fruits collected
        int maxFruits = 0;

        // Loop over each possible starting point
        for (int start = 0; start < fruits.length; ++start) {

            // Map to store the count of fruit types
            Map<Integer, Integer> basket = new HashMap<>();

            // Variable to track current number of fruits collected
            int currentCount = 0;

            // Traverse from current start to the end
            for (int end = start; end < fruits.length; ++end) {

                // Add current fruit to the basket
                basket.put(fruits[end], basket.getOrDefault(fruits[end], 0) + 1);

                // If basket has more than 2 types, break
                if (basket.size() > 2) {
                    break;
                }

                // Increase current fruit count
                currentCount++;
            }

            // Update the maximum fruits collected
            maxFruits = Math.max(maxFruits, currentCount);
        }

        // Return the result
        return maxFruits;
    }
}

// Driver code
class Main {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] fruits = {1, 2, 1};
        System.out.println(obj.totalFruit(fruits)); // Output: 3
    }
}
Complexity Analysis

Time Complexity: O(N²), where N is the number of trees (length of the input array). We check every possible starting index and extend the window to the right until we encounter a third type of fruit.

Space Complexity: O(1), because we only store a frequency map for at most 3 types of fruits at a time (2 allowed + 1 breaking the rule), and the size of this map does not grow with the input size.

Better Approach
Algorithm
In the brute force approach, we checked every possible starting point and extended the subarray as far as possible while satisfying the necessary condition But this was inefficient because we repeatedly recalculated the same things.

We can do better by applying the sliding window technique. The idea is to maintain a window that contains at most 2 distinct fruit types, and whenever the condition is violated, shrink the window from the left until it's valid again. We use a hash map to track the count of each fruit in the current window. This allows us to scan the array only once from left to right, and adjust the window dynamically in linear time.
Initialize two pointers for the window: start and end.
Use a hash map to store the frequency of each fruit type within the current window.
Iterate through the array using the end pointer.
Add the current fruit to the map and update its count.
If the map size exceeds 2 (more than 2 fruit types in window), shrink the window from the start pointer until the map becomes valid (size ≤ 2).
At each step, track the maximum length of the valid window.


Code
Cpp
Java
Python
Javascript


import java.util.*;

class Solution {
    // Function to find the maximum number of fruits we can collect with at most two fruit types
    public int totalFruit(int[] fruits) {
        // HashMap to track count of each fruit in current window
        Map<Integer, Integer> basket = new HashMap<>();

        // Initialize pointers and max result
        int left = 0;
        int maxFruits = 0;

        // Traverse the fruits array using right pointer
        for (int right = 0; right < fruits.length; right++) {
            // Include current fruit in the map
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If more than 2 fruit types, shrink window from left
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);

                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }

                left++;
            }

            // Update maximum valid window length
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        // Return the final result
        return maxFruits;
    }
}

// Driver code
public class Main {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] fruits = {1, 2, 1, 2, 3};
        System.out.println(obj.totalFruit(fruits));
    }
}
Complexity Analysis

Time Complexity: O(n), where n is the length of the input array. The sliding window expands and contracts over the array. Each element is processed at most twice, once when the right pointer includes it in the window and possibly again when the left pointer removes it. Hence, the overall traversal is linear in time.

Space Complexity: O(1), constant auxiliary space. Although we use a hash map to keep track of the count of fruit types in the current window, it holds at most two keys (since we’re allowed only two types of fruits). Therefore, the space usage remains constant and does not scale with input size.

Optimal Approach
Algorithm
In this optimal approach, instead of storing counts of all fruit types using a map, we keep track of just the last two fruit types seen and their last positions. This allows us to avoid using any auxiliary space like a hash map. The idea is simple: we maintain a sliding window that only contains two types of fruits and expand it as long as the new fruit belongs to either of those two types. If a new third type appears, we shrink the window to start right after the last occurrence of the second last fruit type.
Initialize two variables to store the two recent fruit types seen and their last positions.
Traverse the array while expanding the window to include current fruits as long as they match the two types.
If a third fruit type appears, update the window to start just after the last occurrence of one of the older fruit types.
Track the maximum length of valid windows throughout the traversal.
Image 1
Image 2


Code
Cpp
Java
Python
Javascript


import java.util.*;

class Solution {
    // Function to find the maximum number of fruits we can collect
    // with at most two types of fruits in the baskets.
    public int totalFruit(int[] fruits) {
        
        // Variables to track max window size
        int maxlen = 0;
        
        // Track last and second last fruit types
        int lastFruit = -1, secondLastFruit = -1;
        
        // Count of current window and streak of last fruit
        int currCount = 0, lastFruitStreak = 0;

        // Traverse through each fruit
        for (int fruit : fruits) {
            
            // If fruit matches last two, expand window
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currCount++;
            } else {
                // Reset window size to streak + 1
                currCount = lastFruitStreak + 1;
            }

            // Update lastFruit streak and fruit types
            if (fruit == lastFruit) {
                lastFruitStreak++;
            } else {
                lastFruitStreak = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            // Update max window size
            maxlen = Math.max(maxlen, currCount);
        }

        return maxlen;
    }
}

// Driver code
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] fruits = {1,2,1,2,3};
        System.out.println(sol.totalFruit(fruits));
    }
}
Complexity Analysis

Time Complexity: O(n), where n is the total number of elements in the input array.

Space Complexity: O(1), constant auxiliary space. Only a fixed number of integer variables are maintained.

