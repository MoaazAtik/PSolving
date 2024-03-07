package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// #L.199
public class BinaryTreeRightSideView {

    public static void preRightSideView() {
        TreeNode root = prePopulateTree();
//        printTreeNodesDfsWNulls(root);
//        p();
//        printTreeNodesBfsNoNulls(root);
//        p();
//        printTreeNodesBfsWNulls(root);
//        p();
//        p(getTreeNodesToListNullsTrimmed(root));

        printTreeNodesBfsNullsTrimmed(root);
        p();

//        p(rightSideView(root));
    }

    private static List<Integer> rightSideView(TreeNode root) {

        return null;
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


    private static TreeNode prePopulateTree() {
//        Integer[] nodes = {1, 2, 3, null, 5, null, 4};
//        return populateTreeBfs(nodes);

//        Integer[] nodes = {1, null, 3};
//        return populateTreeBfs(nodes);

//        Integer[] nodes = {};
//        return populateTreeBfs(nodes);


//        Integer[] nodes = {1, 2, null};
//        return populateTreeBfs(nodes);

//        Integer[] nodes = {1, 2, 3, null, null, 4, null};
//        return populateTreeBfs(nodes);

//        Integer[] nodes = {1, 2, 3, null, 4, null, null};
//        return populateTreeBfs(nodes);

//        Integer[] nodes = {1, 2, 3, 4, null, null, null};
//        return populateTreeBfs(nodes);

        Integer[] nodes = {1, 2, 3, 4, 5, null, null, null, null, null, 6, 7, 8, 9, null, 10};
        return populateTreeBfs(nodes);
    }

    // *B Iterative BFS. insert nodes from array to tree.
    public static TreeNode populateTreeBfs(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode currentRoot = queue.poll();
            if (currentRoot != null) {
                TreeNode leftChild = (i < arr.length && arr[i] != null) ? new TreeNode(arr[i]) : null;
                TreeNode rightChild = (i + 1 < arr.length && arr[i + 1] != null) ? new TreeNode(arr[i + 1]) : null;
                currentRoot.left = leftChild;
                currentRoot.right = rightChild;
                queue.offer(leftChild);
                queue.offer(rightChild);
                i += 2;
            }
        }
        return root;
    }


    // Recursive DFS With null nodes
    private static void printTreeNodesDfsWNulls(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printTreeNodesDfsWNulls(root.left);
            printTreeNodesDfsWNulls(root.right);
        } else
            System.out.print("null ");
    }

    // Iterative BFS print Without null nodes
    private static void printTreeNodesBfsNoNulls(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            int currentSize = nodes.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode currentNode = nodes.poll();
                pl(currentNode.val);
                if (currentNode.left != null)
                    nodes.offer(currentNode.left);
                if (currentNode.right != null)
                    nodes.offer(currentNode.right);
            }
        }
    }

    // Iterative BFS print With null nodes and Without Trimming null nodes at the end
    private static void printTreeNodesBfsWNulls(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> nodesQueue = new LinkedList<>();
        nodesQueue.offer(root);

        while (!nodesQueue.isEmpty()) {
            TreeNode currentNode = nodesQueue.poll();
            if (currentNode == null) {
                pl(null);
            } else {
                pl(currentNode.val);
                if (currentNode.left == null) {
                    nodesQueue.offer(null);
                } else
                    nodesQueue.offer(currentNode.left);
                if (currentNode.right == null) {
                    nodesQueue.offer(null);
                } else
                    nodesQueue.offer(currentNode.right);
            }
        }
    }

    // Iterative BFS. Get Tree nodes as List With null nodes and With Trimming trailing null nodes
    public static List<Integer> getTreeNodesToListNullsTrimmed(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
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
        return result;
    }

    // *B Iterative BFS print With null nodes and With Trimming trailing null nodes (like Leetcode)
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
