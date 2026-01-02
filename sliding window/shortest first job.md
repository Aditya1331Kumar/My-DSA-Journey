# Shortest Job First




A software engineer is tasked with using the shortest job first (SJF) policy to calculate the average waiting time for each process. The shortest job first also known as shortest job next (SJN) scheduling policy selects the waiting process with the least execution time to run next.



You are given an array of integers bt of size n representing the burst times (execution times) of n processes.



Your task is to calculate the average waiting time for all processes when scheduled using the SJF policy. The waiting time of a process is the total time a process has to wait before its execution starts, which is the sum of burst times of all previously executed processes.



Return the floor of the average waiting time, i.e., the largest whole number less than or equal to the actual average.


Examples:
Input : bt = [4, 1, 3, 7, 2]

Output : 4

Explanation : The total waiting time is 20.

So the average waiting time will be 20/5 => 4.

Input : bt = [1, 2, 3, 4]

Output : 2

Explanation : The total waiting time is 10.

So the average waiting time will be 10/4 => 2.



soln
```java

// Function to calculate average
    // waiting time using Shortest
    // Job First algorithm
    static float shortestJobFirst(int[] jobs) {
        // Sort the jobs in ascending order
        Arrays.sort(jobs);

        // Initialize total waiting time
        float waitTime = 0;
        // Initialize total time taken
        int totalTime = 0;
        // Get the number of jobs
        int n = jobs.length;

        // Iterate through each job
        // to calculate waiting time
        for (int i = 0; i < n; ++i) {

            // Add current total
            // time to waiting time
            waitTime += totalTime;

            // Add current job's
            // time to total time
            totalTime += jobs[i];
        }

        // Return the average waiting time
        return waitTime / n;
    }

```
* Once the jobs are sorted, we iterate through each job in the sorted list. For each iteration the waiting time is the sum of the total time taken by all previous jobs. We calculate the waiting time for each job and update the total time taken by adding the duration of the current job to the total waiting time.

## Complexity Analysis

* Time Complexity: O(N logN + N) where N is the length of the jobs array. We sort the jobs array giving complexity O(N log N) and to calculate the waiting time we iterate through the sorted array taking O(N) complexity.

## Example 1:
```java
                Input:jobs = [3, 1, 4, 2, 5]
                
                Output: 4           
                Explanation: 
 ```
                
The first job that will be executed is of duration 1 and the waiting time for it will be 0.
After the first job, the next shortest job with a duration of 2 will be executed with a waiting time of 1.
Following the completion of the first two jobs, the next shortest job with a duration of 3 will be executed with a waiting time of 3 (1 + 2).
Then, the job with a duration of 4 will be executed with a waiting time of 6 (1 + 2 + 3).
Finally, the job with the longest duration of 5 will be executed with a waiting time of 10 (1 + 2 + 3 + 4).

*  Hence, the average waiting time is calculated as (0 + 1 + 3 + 6 + 10) / 5 = 20 / 5 = 4

## Example 2:
  * Input: jobs = [4, 3, 7, 1, 2]
 *    Output: 4
* Explanation: The first job that will be executed is of duration 1, and the waiting time for it will be 0.
                
After the first job, the next shortest job with a duration of 2 will be executed with a waiting time of 1.
Following the completion of the first two jobs, the next shortest job with a duration of 3 will be executed with a waiting time of 3 (1 + 2).
Then, the job with a duration of 4 will be executed with a waiting time of 6 (1 + 2 + 3).
Finally, the job with the longest duration of 7 will be executed with a waiting time of 10 (1 + 2 + 3 + 4).

* Hence, the average waiting time is calculated as (0 + 1 + 3 + 6 + 10) / 5 = 20 / 5 = 4.