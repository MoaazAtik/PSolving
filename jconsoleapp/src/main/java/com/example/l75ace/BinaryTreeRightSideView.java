package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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

        p(rightSideView(root));
        p(rightSideView2(root));
        p(rightSideView3(root));
    }

    // probably it can be solved by keeping track of the Level too

    /**
     * 1ms - 41.8mb
     * 2 Queues, first queue element Watcher
     */
    private static List<Integer> rightSideView(TreeNode root) {
        //                 1
        //     2                     3
        //        5                      4
        //      7
        List<Integer> rightView = new ArrayList<>();
        if (root == null)
            return rightView;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);

//        while (!queue1.isEmpty() || !queue2.isEmpty()) { //Condition '!queue2.isEmpty()' is always 'false' when reached
        while (!queue1.isEmpty()) {
            boolean firstElementOfQueue = true;
            while (!queue1.isEmpty()) {
                TreeNode currentRoot = queue1.poll();
                if (firstElementOfQueue) {
                    rightView.add(currentRoot.val);
                    firstElementOfQueue = false;
                }
                if (currentRoot.right != null)
                    queue2.offer(currentRoot.right);
                if (currentRoot.left != null)
                    queue2.offer(currentRoot.left);
            }
            firstElementOfQueue = true;
            while (!queue2.isEmpty()) {
                TreeNode currentRoot = queue2.poll();
                if (firstElementOfQueue) {
                    rightView.add(currentRoot.val);
                    firstElementOfQueue = false;
                }
                if (currentRoot.right != null)
                    queue1.offer(currentRoot.right);
                if (currentRoot.left != null)
                    queue1.offer(currentRoot.left);
            }
        }

        return rightView;
    }

    /**
     * *B 1ms - 42.1mb
     * first queue element Watcher
     */
    private static List<Integer> rightSideView2(TreeNode root) {
        //                 1
        //     2                     3
        //        5                      4
        //      7
        List<Integer> rightView = new ArrayList<>();
        if (root == null)
            return rightView;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            boolean firstElementOfQueue = true;
            for (int i = 0; i < currentSize; i++) {
                TreeNode currentRoot = queue.poll();
                if (firstElementOfQueue) {
                    rightView.add(currentRoot.val);
                    firstElementOfQueue = false;
                }
                if (currentRoot.right != null)
                    queue.offer(currentRoot.right);
                if (currentRoot.left != null)
                    queue.offer(currentRoot.left);
            }
        }

        return rightView;
    }

    /**
     * 1ms - 41.9mb
     * YT modified.
     */
    private static List<Integer> rightSideView3(TreeNode root) {
        List<Integer> rightView = new ArrayList<>();
        if (root == null)
            return rightView;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode rightSide = null;
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode currentRoot = queue.poll();
                rightSide = currentRoot;
                if (currentRoot.left != null)
                    queue.offer(currentRoot.left);
                if (currentRoot.right != null)
                    queue.offer(currentRoot.right);
            }
            rightView.add(rightSide.val);
        }

        return rightView;
    }

    // Definition for a Binary Tree Node. (from Leetcode)
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


    // Recursive DFS print With null nodes
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
