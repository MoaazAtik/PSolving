package com.example.jconsoleapp.ti150mustdo;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// #L.53
public class MaximumSubarray {

    public static void preMaxSubArray() {
        getInput(1);

        p("nums ");
        pal(nums);
        p("expectedOutput " + expectedOutput);
        p();
        p(maxSubArray(nums));
        p(maxSubArray2(nums)); // Wrong
        p(maxSubArray3(nums)); // Wrong
        p(maxSubArray4(nums));
        p(maxSubArray5(nums));
        p(maxSubArray6(nums));
    }

    private static int[] nums;
    private static int expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
                expectedOutput = 6;
                break;
            case 2:
                nums = new int[]{1};
                expectedOutput = 1;
                break;
            case 3:
                nums = new int[]{5, 4, -1, 7, 8};
                expectedOutput = 23;
                break;

            case 4:
                nums = new int[]{-1}; // edge
                expectedOutput = -1;
                break;
            case 5:
                nums = new int[]{2, -1, -3, 4, 1, -2, 1, -5, 4};
                expectedOutput = 5;
                break;
            case 6:
                nums = new int[]{-2, -3, -5}; // edge all negatives
                expectedOutput = -2;
                break;
            case 7:
                nums = new int[]{-3, -3, -5, -1}; // edge all negatives
                expectedOutput = -1;
                break;
            case 8:
                nums = new int[]{-3, -3, -5, -1, 0}; // edge all negatives
                expectedOutput = 0;
                break;
            case 9:
                nums = new int[]{-2, 0, -5}; // edge all negatives and zero
                expectedOutput = 0;
                break;
            case 10:
                nums = new int[]{0, -2, -5}; // edge all negatives and zero
                expectedOutput = 0;
                break;
            case 11:
                nums = new int[]{-2, 0, 5}; // negative, zero, positive
                expectedOutput = 5;
                break;
        }
    }

//    Constraints:
//    1 <= nums.length <= 10^5
//    -10^4 <= nums[i] <= 10^4


    /**
     * Time Limit Exceeded - 205 / 210 testcases passed. Brute Force Approach
     * It covers when nums doesn't contain positive numbers
     * Time O(n^2), Space O(1).
     * Follow up: It can provide first and last index of the last maximum subarray.
     */
    private static int maxSubArray(int[] nums) {
        int max = -10000; // constraints
        // OR
//        int max = nums[0];
//        int first = 0; // for follow up
//        int last = 0; // for follow up
        for (int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for (int j = i; j < nums.length; j++) {
                currSum += nums[j];
//                if (currSum > max) {
//                    max = currSum;
//                    first = i;
//                    last = j;
//                }
                // OR
                max = Math.max(max, currSum);
            }
        }
//        p("first " + first + ", last " + last);
        return max;
    }


    // Wrong
    private static int maxSubArray2(int[] nums) {
        int positiveCount = 0; // count of positive numbers

        for (int num : nums)
            if (num > 0)
                positiveCount++;

        int max = -10000; // constraints

        if (positiveCount > 0) {
            int currSum = 0;
            for (int i = 0; i < positiveCount; i++) { // interval is as long as positiveCount
                currSum += nums[i];
            }
            max = currSum;
            for (int l = 0, r = positiveCount; r < nums.length; l++, r++) {
                currSum = currSum - nums[l] + nums[r];
                max = Math.max(max, currSum);
            }
        } else { // max is 0 or largest negative number
            for (int num : nums)
                max = Math.max(max, num);
        }


        return max;
    }


    // Wrong
    private static int maxSubArray3(int[] nums) {
        int max = -10000; // constraints

        for (int num : nums)
            max = Math.max(num, max);

        if (max <= 0)
            return max;

        List<Integer> list = new ArrayList<>();
        int currSum = 0;
        for (int num : nums) { // interval is as long as positiveCount
            if (num > 0) {
                list.add(currSum);
                currSum = 0;
            }
            currSum += num;
        }
        if (nums[nums.length - 1] > 0)
            list.add(nums[nums.length - 1]);

        int intervalsSum = 0; //
        for (int i = 0; i < list.size(); i++) {
            intervalsSum += list.get(i);
            if (intervalsSum < 0) {
                intervalsSum = 0;
            }
            max = Math.max(max, intervalsSum);
        }

        return max;
    }


    /**
     * *B 1ms - 56.2mb. Kadane's Algorithm, Sliding window.
     * Time O(n), Space O(1)
     * Check {@link #maxSubArray5(int[])} for Follow up.
     * Tried Indexed for loop, And int len = nums.length, And int max = nums[0]. They gave the same runtime and memory results.
     */
    private static int maxSubArray4(int[] nums) {
        int currSum = 0;
        int max = -10000;
        // OR
//        int max = nums[0];
        for (int num : nums) {
            currSum += num;
            max = Math.max(max, currSum);
            if (currSum < 0)
                currSum = 0;
        }
        return max;
    }


    /**
     * 1ms - 57.0mb. Kadane's Algorithm, Sliding window.
     * Modified {@link #maxSubArray4(int[])} for Follow up.
     * Follow up: It can provide first and last index of the last maximum subarray.
     * Time O(n), Space O(1)
     */
    private static int maxSubArray5(int[] nums) {
        int currSum = 0;
        int max = -10000;
        int first = -1; // for Follow up
        int probableFirst = -1; // for Follow up
        int last = -1; // for Follow up
        if (nums[0] >= 0)
            probableFirst = 0;

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (max < currSum) {
                max = currSum;
                last = i;
                first = probableFirst;
            }
            if (currSum < 0) {
                currSum = 0;
                probableFirst = i + 1;
            }
        }

        if (first == -1) // when nums doesn't contain positive numbers
            first = last;
        p("first " + first + ", last " + last);
        return max;
    }


    /**
     * GFG 14ms - 56.8mb. Recursion, Divide and conquer.
     */
    private static int maxSubArray6(int[] nums) {
        return maxSubArraySum(nums, 0, nums.length - 1);
    }

    // Returns sum of maximum sum subarray in aa[l..h]
    private static int maxSubArraySum(int[] arr, int l, int h) {
        //Invalid Range: low is greater than high
        if (l > h)
            return Integer.MIN_VALUE;
        // Base Case: Only one element
        if (l == h)
            return arr[l];

        // Find middle point
        int m = (l + h) / 2;

        /* Return maximum of following three
        possible cases:
        a) Maximum subarray sum in left half
        b) Maximum subarray sum in right half
        c) Maximum subarray sum such that the
        subarray crosses the midpoint */
        return Math.max(
                Math.max(maxSubArraySum(arr, l, m - 1), // left
                        maxSubArraySum(arr, m + 1, h)), // right
                maxCrossingSum(arr, l, m, h)); // crossing
    }

    // Find the maximum possible sum in arr[] such that arr[m] is part of it
    private static int maxCrossingSum(int[] arr, int l, int m, int h) {
        // Include elements on left of mid.
        int sum = 0;
        int left_sum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum = sum + arr[i];
            if (sum > left_sum)
                left_sum = sum;
        }

        // Include elements on right of mid
        sum = 0;
        int right_sum = Integer.MIN_VALUE;
        for (int i = m; i <= h; i++) {
            sum = sum + arr[i];
            if (sum > right_sum)
                right_sum = sum;
        }

        // Return sum of elements on left and right of mid
        // returning only left_sum + right_sum will fail for [-2, 1]
        return Math.max(left_sum + right_sum - arr[m],
                Math.max(left_sum,
                        right_sum));
    }


    // Divide and Conquer, Dynamic Programming Problem
}
