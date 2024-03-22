package com.example.jconsoleapp.l75ace;

import com.example.jconsoleapp.MyHelper;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

// #L.104
public class MaximumDepthOfBinaryTree {

    public static void preMaxDepth() {
        TreeNode root = populateTree();

        printNode(root);
        MyHelper.p("");
        MyHelper.p(maxDepth(root));
        MyHelper.p(maxDepth2(root));
        MyHelper.p(maxDepth3(root));
        MyHelper.p(maxDepth3m(root));
    }

    // Definition for a binary tree node. (from Leetcode)
    public static class TreeNode {
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

    // *B YT Recursive In-order DFS. 0ms - 42.2mb
    private static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

//        int leftDepth = maxDepth(root.left);
//        int rightDepth = maxDepth(root.right);
//        return 1 + Math.max(leftDepth, rightDepth);
        // OR
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // YT Iterative BFS. 1ms - 42.7mb
    private static int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;

        int level = 0;
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
//            for (int i = 0; i < nodes.size(); i++) { // Wrong in Java for [0,2,4,1,null,3,-1,5,1,null,6,null,8] output 5, expected 4
            int currentSize = nodes.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode currentNode = nodes.poll();
                if (currentNode.left != null)
                    nodes.offer(currentNode.left);
                if (currentNode.right != null)
                    nodes.offer(currentNode.right);
            }
            level++;
        }

        return level;
    }

    /**
     * YT Iterative Pre-order DFS (modified for java).
     * package AbstractMap does not exist in Leetcode.
     */
    private static int maxDepth3(TreeNode root) {
        Stack<Map.Entry<TreeNode, Integer>> nodes = new Stack<>();
        // OR
//        Stack<AbstractMap.SimpleEntry<TreeNode, Integer>> nodes = new Stack<>();
        int maxDepth = 0;

        int depth = 1;
        nodes.push(new AbstractMap.SimpleEntry<>(root, depth));

        while (!nodes.isEmpty()) {
            AbstractMap.SimpleEntry<TreeNode, Integer> currentEntry = (AbstractMap.SimpleEntry<TreeNode, Integer>) nodes.pop();
            if (currentEntry.getKey() != null) {
                maxDepth = Math.max(maxDepth, currentEntry.getValue());
                nodes.push(new AbstractMap.SimpleEntry<>(currentEntry.getKey().left, currentEntry.getValue() + 1));
                nodes.push(new AbstractMap.SimpleEntry<>(currentEntry.getKey().right, currentEntry.getValue() + 1));
            }
        }

        return maxDepth;
    }

    /**
     * Modified {@link #maxDepth3(TreeNode) maxDepth3()} YT java Iterative Pre-order DFS. 4ms - 43.9mb
     * Stack
     */
    private static int maxDepth3m(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        // OR
//        Stack<TreeNode> stack = new Stack<>(); // with
//            TreeNode curr = stack.pop();
        LinkedList<Integer> depths = new LinkedList<>();
        stack.add(root);
        depths.add(1);

        int maxDepth = 0;

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollLast();
            int currDepth = depths.pollLast();
            if (curr != null) {
                maxDepth = Math.max(maxDepth, currDepth);
                stack.add(curr.right);
                depths.add(currDepth + 1);
                stack.add(curr.left);
                depths.add(currDepth + 1);
            }
        }

        return maxDepth;
    }


    private static TreeNode populateTree() {
//        3, 9, 20, null, null, 15, 7
        TreeNode a = new TreeNode(7, null, null);
        TreeNode b = new TreeNode(15, null, null);

        TreeNode c = new TreeNode(20, b, a);
        TreeNode d = new TreeNode(9, null, null);

        TreeNode root = new TreeNode(3, d, c);
        return root;

////        1, null, 2
//        TreeNode a = new TreeNode(2, null, null);
//
//        TreeNode root = new TreeNode(1, null, a);
//        return root;


////        1, 7, 2
//        TreeNode a = new TreeNode(2, null, null);
//        TreeNode b = new TreeNode(7, null, null);
//
//        TreeNode root = new TreeNode(1, b, a);
//        return root;

////        1
//        TreeNode root = new TreeNode(1, null, null);
//        return root;

////        null
//        return null;
    }

    private static void printNode(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printNode(root.left);
            printNode(root.right);
        } else
            System.out.print("null ");
    }

}
