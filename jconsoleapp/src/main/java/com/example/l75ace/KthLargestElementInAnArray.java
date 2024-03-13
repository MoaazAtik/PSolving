package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pal;
import static com.example.jconsoleapp.MyHelper.pi;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

// #L.215
public class KthLargestElementInAnArray {

    public static void preFindKthLargest() {
        getInput(1);

        p("k " + k);
        pi(nums.length);
        pal(nums);
        p(findKthLargest(nums, k));
//        p(findKthLargest2(nums, k)); // changes the data of the input!
        p(findKthLargest3(nums, k));
//        p(findKthLargest4(nums, k)); // changes the data of the input!
//        p(findKthLargest5(nums, k)); // changes the data of the input!
//        p(findKthLargest6(nums, k)); // changes the data of the input!
//        p(findKthLargest7(nums, k)); // changes the data of the input!
        p(findKthLargest8(nums, k)); // changes the data of the input!
        pal(nums);
    }

    static int[] nums = null;
    static int k = 0;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                nums = new int[]{3, 2, 1, 5, 6, 4};
                k = 2;
                break;
            case 2:
                nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
                k = 4;
                break;

            case 3:
                nums = new int[]{1};
                k = 1;
                break;
            case 4:
                nums = new int[]{3, 2, 1, 5, 0, 6, 4};
                k = 2;
                break;
            case 5:
                nums = new int[]{9, 7, 6, 5, 8, 10, 2, 1, 4, 3};
                k = 5;
                break;
        }
    }

    /**
     * Time Limit Exceeded - 36 / 41 testcases passed
     */
    private static int findKthLargest(int[] nums, int k) {
        int previousMax = Integer.MAX_VALUE;
        boolean[] visited = new boolean[nums.length];

        for (int count = 0; count < k; count++) {
            int tempMax = Integer.MIN_VALUE;
            int positionVisited = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > tempMax && nums[i] <= previousMax)
                    if (!visited[i]) {
                        tempMax = nums[i];
                        positionVisited = i;
                    }
            }
            visited[positionVisited] = true;
            previousMax = tempMax;
        }

        return previousMax;
    }

    /**
     * Time Limit Exceeded - 38 / 41 testcases passed
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the data of the input!
     */
    private static int findKthLargest2(int[] nums, int k) {
        int count = 0;
        while (count < k) {
            int tempMax = Integer.MIN_VALUE;
            int positionVisited = 0;
            for (int i = count; i < nums.length; i++) {
                if (nums[i] > tempMax) {
                    tempMax = nums[i];
                    positionVisited = i;
                }
            }
            int tempSkip = nums[count];
            nums[count] = nums[positionVisited];
            nums[positionVisited] = tempSkip;
            count++;
        }

        return nums[count - 1];
    }

    /**
     * *B AI 39ms - 56.6mb. Priority Queue / Min-Heap
     */
    private static int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++)
            minHeap.offer(nums[i]);

        for (int i = k; i < nums.length; i++)
            if (nums[i] > minHeap.peek()) {
                // Remove the root (smallest element) from the min-heap
                minHeap.poll();
                minHeap.offer(nums[i]);
            }

        return minHeap.peek();
    }

    /**
     * L 22ms - 57.2mb. Sorting. sort()
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the data of the input!
     */
    private static int findKthLargest4(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * L 23ms - 57.5mb. Sorting. parallelSort()
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the data of the input!
     */
    private static int findKthLargest5(int[] nums, int k) {
        Arrays.parallelSort(nums);
        return nums[nums.length - k];
    }

    /**
     * AI Time Limit Exceeded - 40 / 41 testcases passed.
     * Iterative QuickSelect Algorithm, Pivot, 2 Pointers.
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the data of the input!
     */
    private static int findKthLargest6(int[] nums, int k) {

        // Initialize left and right pointers
        int left = 0;
        int right = nums.length - 1;

        // Loop until left and right pointers meet
        while (left <= right) {
            // Choose a pivot element (e.g., the last element)
            int pivotIndex = partition(nums, left, right);

            // Check if the pivot is the kth largest element
            if (pivotIndex == nums.length - k) {
                return nums[pivotIndex];
            } else if (pivotIndex < nums.length - k) {
                // If pivot index is less than k, search in the right subarray
                left = pivotIndex + 1;
            } else {
                // If pivot index is greater than k, search in the left subarray
                right = pivotIndex - 1;
            }
        }

        return -1; // Not found (should not reach here)
    }

    private static int partition(int[] nums, int left, int right) {
        // Choose pivot element (e.g., the last element)
        int pivot = nums[right];
        int i = left - 1; // Index of the smaller element

        // Partitioning loop
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                // Swap nums[i] and nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        // Swap nums[i+1] and nums[right] (pivot)
        int temp = nums[i + 1];
        nums[i + 1] = nums[right];
        nums[right] = temp;

        // Return the index of the pivot element
        return i + 1;
    }


    /**
     * AI Time Limit Exceeded - 40 / 41 testcases passed.
     * Recursive QuickSelect Algorithm, Pivot, 2 Pointers.
     * {@link #findKthLargest7(int[], int)} method, acts as a <b>Wrapper</b> for the Recursive quickSelect method.
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the data of the input!
     */
    private static int findKthLargest7(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private static int quickSelect(int[] nums, int left, int right, int k) {
        // Partition the array
        int pivotIndex = partition2(nums, left, right);

        // Check if the pivot is the kth largest element
        if (pivotIndex == nums.length - k) {
            return nums[pivotIndex];
        } else if (pivotIndex < nums.length - k) {
            // If pivot index is less than k, search in the right subarray
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            // If pivot index is greater than k, search in the left subarray
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition2(int[] nums, int left, int right) {
        // Choose pivot element (e.g., the last element)
        int pivot = nums[right];
        int i = left - 1; // Index of the smaller element

        // Partitioning loop
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                // Swap nums[i] and nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        // Swap nums[i+1] and nums[right] (pivot)
        int temp = nums[i + 1];
        nums[i + 1] = nums[right];
        nums[right] = temp;

        // Return the index of the pivot element
        return i + 1;
    }


    /**
     * AI Time Limit Exceeded - 40 / 41 testcases passed.
     * Recursive QuickSelect Algorithm, Pivot, Randomized Pivot Selection, 2 Pointers, Random.
     * {@link #findKthLargest8(int[], int)} method, acts as a <b>Wrapper</b> for the Recursive quickSelect method.
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the data of the input!
     */
    private static int findKthLargest8(int[] nums, int k) {
        return quickSelect2(nums, 0, nums.length - 1, k);
    }

    private static Random random = new Random();

    private static int quickSelect2(int[] nums, int left, int right, int k) {
        // Randomized pivot selection
        /*
        This line generates a random pivot index within the range [left, right].
        random.nextInt(n) generates a random integer between 0 (inclusive) and n (exclusive). Here, n is right - left + 1, which represents the size of the current subarray being considered.
        Since we want the pivot index to be within the range [left, right], we add left to the result to shift the range accordingly.
         */
        int pivotIndex = random.nextInt(right - left + 1) + left;
        /*
        This line swaps the element at the randomly selected pivot index with the last element (rightmost element) of the array.
        By doing this swap, we ensure that the chosen pivot element is placed at the end of the subarray, ready for partitioning.
         */
        swap(nums, pivotIndex, right);

        // Partition the array
        pivotIndex = partition3(nums, left, right);

        // Check if the pivot is the kth largest element
        if (pivotIndex == nums.length - k) {
            return nums[pivotIndex];
        } else if (pivotIndex < nums.length - k) {
            // If pivot index is less than k, search in the right subarray
            return quickSelect2(nums, pivotIndex + 1, right, k);
        } else {
            // If pivot index is greater than k, search in the left subarray
            return quickSelect2(nums, left, pivotIndex - 1, k);
        }
    }

    private static int partition3(int[] nums, int left, int right) {
        // Choose pivot element (randomly selected)
        int pivot = nums[right];
        int i = left - 1; // Index of the smaller element

        // Partitioning loop
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                // Swap nums[i] and nums[j]
                swap(nums, i, j);
            }
        }

        // Swap nums[i+1] and nums[right] (pivot)
        swap(nums, i + 1, right);

        // Return the index of the pivot element
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
