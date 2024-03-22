package com.example.jconsoleapp.l75ace;

import static com.example.jconsoleapp.MyHelper.p;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// #L.700
public class SearchInABinarySearchTree {

    public static void preSearchBST() {
//        int[] rootArray = {2, 1, 3, 4, 7}; // this BST is not self-balancing, thus insertion order matters
        int[] rootArray = {4, 2, 7, 1, 3};
        int val = 2;
//        int[] rootArray = {4, 2, 7, 1, 3};
//        int val = 5;
        TreeNode root = populateTree(rootArray);
        printTreeNodesBfsNullsTrimmed(root);

        p(searchBST(root, val));
        printTreeNodesBfsNullsTrimmed(searchBST(root, val));
        p(searchBST2(root, val));
        printTreeNodesBfsNullsTrimmed(searchBST(root, val));
        p(searchBST3(root, val));
        printTreeNodesBfsNullsTrimmed(searchBST3(root, val));
        p(searchBST4(root, val));
        printTreeNodesBfsNullsTrimmed(searchBST4(root, val));
        printTreeNodesBfsNullsTrimmed(root);
    }

    // Recursive 0ms - 44.9mb
    private static TreeNode searchBST(TreeNode root, int val) {
        TreeNode result;
        if (root == null || root.val == val) {
            result = root;
            return result;
        }

        if (val < root.val)
            result = searchBST(root.left, val);
        else
            result = searchBST(root.right, val);

        return result;
    }

    // YT Iterative 0ms - 45.0mb
    private static TreeNode searchBST2(TreeNode root, int val) {
        while (root != null && root.val != val)
            root = val < root.val ? root.left : root.right;
        return root;
    }

    // YT Recursive 0ms - 44.9mb
    private static TreeNode searchBST3(TreeNode root, int val) {
//        if (root == null) return null;
//        if (root.val == val) return root;
        // OR
        if (root == null || root.val == val) return root;

        if (val < root.val)
            return searchBST3(root.left, val);
        else
            return searchBST3(root.right, val);
    }

    // YT Iterative 0ms - 45.3mb
    private static TreeNode searchBST4(TreeNode root, int val) {
        TreeNode currentNode = root;
        while (currentNode != null) {
            if (currentNode.val == val) return currentNode;
            if (val < currentNode.val)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return currentNode;
    }

    // Definition for a binary tree node.
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


    // *B Iterative BFS. insert nodes from array into Binary Search Tree (BST).
    private static TreeNode populateTree(int[] rootArray) {
        if (rootArray == null || rootArray.length == 0) return null;
        TreeNode root = null;
        for (int val : rootArray) {
            TreeNode node = new TreeNode(val);
            root = duelAndInsert(root, node);
        }
        return root;
    }

    // *B Recursive inserting node into Binary Search Tree (BST)
    private static TreeNode duelAndInsert(TreeNode root, TreeNode newNode) {
        if (root == null)
            return newNode;
        if (newNode.val < root.val)
            root.left = duelAndInsert(root.left, newNode);
        else
            root.right = duelAndInsert(root.right, newNode);
        return root;
    }

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

}
