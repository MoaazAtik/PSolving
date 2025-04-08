package com.example.jconsoleapp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MyHelper {

    public static void p() {
        System.out.println();
    }

    public static void p(int i) {
        System.out.println(i);
    }

    public static void p(Object i) {
        System.out.println(i);
    }

    public static void pl(int i) {
        System.out.print(i + "  ");
    }

    public static void pl(Object i) {
        System.out.print(i + "  ");
    }

    /**
     * print indexes of array in one line
     */
    public static void pi(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    /**
     * Print array elements
     *
     * @param nums int array
     */
    public static void pa(int[] nums) {
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void pa(Object[] objects) {
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    /**
     * print Matrix / 2D array
     */
    public static void paPlain(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + "  ");
            }
            System.out.println();
        }
    }

    /**
     * print Matrix / 2D array with headers
     */
    public static void pa(char[][] matrix) {
        System.out.print("   ");
        for (int col = 0; col < matrix[0].length; col++) {
            System.out.print(col + "  ");
        }
        p("");

        for (int r = 0; r < matrix.length; r++) {
            System.out.print(r + "  ");
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + "  ");
            }
            p();
        }
    }

    public static void pa(Integer[][] matrix) {
        System.out.print("   ");
        for (int col = 0; col < matrix[0].length; col++) {
            System.out.print(col + "    ");
        }
        p("");

        for (int r = 0; r < matrix.length; r++) {
            System.out.print(r + "  ");
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + "  ");
            }
            p();
        }
    }

    public static void pa(int[][] matrix) {
        System.out.print("   ");
        for (int col = 0; col < matrix[0].length; col++) {
            System.out.print(col + "  ");
        }
        p("");

        for (int r = 0; r < matrix.length; r++) {
            System.out.print(r + "  ");
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + "  ");
            }
            p();
        }
    }

    /**
     * Print array elements in one line
     *
     * @param nums int array
     */
    public static void pal(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "  ");
        }
        System.out.println();
    }

    public static void pal(char[] chars) {
//        for (int c : chars)
        for (char c : chars)
            System.out.print(c + "  ");
        System.out.println();
    }

    public static void pal(Object[] objects) {
        for (Object o : objects) {
            System.out.print(o + "_");
        }
        System.out.println();
    }


    // Binary Tree Methods from BinaryTreeRightSideView Start:
    // Recursive DFS print With null nodes
    // Iterative BFS print Without null nodes
    // Iterative BFS print With null nodes and Without Trimming null nodes at the end
    /**
     * *B Iterative BFS print With null nodes and With Trimming trailing null nodes (like Leetcode)
     */
    public static void printTreeNodesBfsNullsTrimmed(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            p(null);
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                result.add(curr.val);
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                result.add(null);
            }
        }
        // Trim trailing nulls
        int lastIndex = result.size() - 1;
        while (lastIndex >= 0 && result.get(lastIndex) == null) {
            result.remove(lastIndex);
            lastIndex--;
        }
        p(result);
    }

    // Iterative BFS. Get Tree nodes as List With null nodes and With Trimming trailing null nodes

    // Definition for a Binary Tree Node. (from Leetcode)
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    // Binary Tree Methods from BinaryTreeRightSideView End.


    // Trie method at ImplementTrie_PrefixTree:
    // Recursive DFS print Trie with HashMap With null nodes


    // Method to Convert 2D Array to 2D List in KeysAndRooms

    // Comparing and Sorting methods in NonOverlappingIntervals and WordFrequency


    /**
     * General-purpose scanner
     */
    public static String s(String purposeOfCall) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter your string for: " + purposeOfCall + "...");

        String userInput = myObj.nextLine();

        System.out.println("User input is: " + userInput);
        return userInput;
    }

    /**
     * Get user input
     */
    public static void s() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
    }


    /**
     * Convert Binary to Negative or Positive integer.
     * The 32th bit can be 1.
     * Utilizes BigInteger class.
     */
    public static int negOrPosBinaryToInt(String binaryString) {
//        int i = new BigInteger("11111111111111111111111111111111", 2).intValue();
        // OR
//        int i = ~0b0;

//        int i = new BigInteger("00000000000000000000000000000001", 2).intValue();
        // OR
//        int i = 0b1;

//        int i = new BigInteger("11111111111111111111111111111110", 2).intValue();
        // OR
//        int i = ~0b1;
//        MyHelper.p("i = " + i);

        return new BigInteger(binaryString, 2).intValue();
    }

    /**
     * Convert Binary to Positive integer. It can't convert a Negative int.
     * The 32th bit can't be 1.
     * Utilizes Integer class.
     */
    public static int posBinaryToInt(String binaryString) {
//        int i = Integer.parseInt("00000000000000000000000000000001", 2);
//        int i = Integer.parseInt("10010", 2); // the rest of the 32 bits to the left are 0's, so it's a positive number
//        MyHelper.p("i = " + i);

        return Integer.parseInt(binaryString, 2);
    }
}
