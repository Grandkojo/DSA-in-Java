/*
*   Question 1: Maximum Subarray Sum: Kadane's Algorithm
    Problem: Given an integer array nums, find the subarray (a contiguous sequence of elements) that has the largest sum, and return its sum.
    Your solution should aim for O(n) time complexity, where n is the length of the array.
    Constraints:
    Input Type: int[]
    Time Complexity: O(n)
*/


public class KadaneAlgorithm {
    
    
    public static int KadaneAlgo (int[] nums)
    {
        int currentSum = nums[0];
        int maxSum = nums[0];

        //start looping from the second element since with have the first element recorded
        for (int i = 1; i < nums.length; i++)
        {
            //find the current sum with the current element, if the new sum is lesser, use the earlier sum
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            //if the new current sum is greater, upade maxSum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
        
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 3, 4, 1, 2, 5, 4};

        int sum = KadaneAlgo(nums);
        System.out.println("Sum of the largest subarray is: " + sum);
    }
}