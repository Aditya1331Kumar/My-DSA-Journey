// Leetcode: Two Sum
// Problem Link: https://leetcode.com/problems/two-sum/
// Level: Easy

public class Two_Sum {
   
 public static void main(String[] args) {
        Two_Sum solution = new Two_Sum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }
    public int[] twoSum(int[] nums, int target) {//gghgh
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};//jhjghf
                }
            }
        }
        return new int[]{-1, -1};
    }
}
