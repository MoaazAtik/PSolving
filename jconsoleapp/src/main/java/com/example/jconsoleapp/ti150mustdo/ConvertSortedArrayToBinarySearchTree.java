package com.example.jconsoleapp.ti150mustdo;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pa;
import static com.example.jconsoleapp.MyHelper.pal;
import static com.example.jconsoleapp.MyHelper.printTreeNodesBfsNullsTrimmed;
import static com.example.jconsoleapp.MyHelper.TreeNode;

// #L.108
public class ConvertSortedArrayToBinarySearchTree {

    public static void preSortedArrayToBST() {
        getInput(1);

        p("nums ");
        pal(nums);
        p("expectedOutput");
        pal(expectedOutput);
        p("expectedOutput2");
        pal(expectedOutput2);
        p();
//        printTreeNodesBfsNullsTrimmed(sortedArrayToBST(nums)); // Crash, Disable it
        printTreeNodesBfsNullsTrimmed(sortedArrayToBST2(nums));
    }

    private static int[] nums;
    private static Integer[] expectedOutput;
    private static Integer[] expectedOutput2;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                nums = new int[]{-10, -3, 0, 5, 9};
                expectedOutput = new Integer[]{0, -3, 9, -10, null, 5};
                expectedOutput2 = new Integer[]{0, -10, 5, null, -3, null, 9};
                break;
            case 2:
                nums = new int[]{1, 3};
                expectedOutput = new Integer[]{3, 1};
                expectedOutput2 = new Integer[]{1, null, 3};
                break;

            case 3:
                nums = new int[]{3}; // edge case
                expectedOutput = new Integer[]{3};
                expectedOutput2 = new Integer[]{3};
                break;
            case 4:
                nums = new int[]{1, 3, 5};
                expectedOutput = new Integer[]{0};
                expectedOutput2 = new Integer[]{3, 1, 5};
                break;
            case 5:
                nums = new int[]{-1, 0, 1, 3}; // edge case
                expectedOutput = new Integer[]{0};
                expectedOutput2 = new Integer[]{0, -1, 1, null, null, null, 3};
                break;
        }
    }


    // Definition for a binary tree node from Leetcode
    /*
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    */

    /**
     * Wrong, Crash. Recursive DFS.
     */
    private static TreeNode sortedArrayToBST(int[] nums) {
        int middle = nums.length / 2;
        TreeNode root = new TreeNode(nums[middle]);
        rec(nums, 0, nums.length - 1, root);
        return root;
    }

    private static TreeNode rec(int[] nums, int left, int right, TreeNode root) {
        if (left >= right)
            return new TreeNode(nums[left]);
        int middle = left + ((right - left) / 2);
//        int middle = ( (right + left) / 2);
        root.left = rec(nums, left, middle - 1, root);
        root.right = rec(nums, middle, right, root);

        return null;
    }


    /**
     * YT 0ms - 42.9mb. Recursive DFS.
     * Similar to my {@link #sortedArrayToBST(int[])}.
     * Gets {@link #expectedOutput2}
     */
    private static TreeNode sortedArrayToBST2(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private static TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) // base case
            return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, start, mid - 1);
        root.right = dfs(nums, mid + 1, end);
        return root;
    }


    // Array, Divide and Conquer, Binary Search Tree Problem
}
