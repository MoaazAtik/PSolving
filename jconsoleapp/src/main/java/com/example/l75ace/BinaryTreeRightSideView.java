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
        TreeNode root = populateTree();
        printNode(root);
        p();
        printNode2(root);
        p();
        printNode3(root);
        p();
        printNode4(root);
        p();
        printNode5(root);
        p();
        p(printTree(root));
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


    private static TreeNode populateTree() {
////        [1, 2, 3, null, 5, null, 4]
//
//        TreeNode d = new TreeNode(4);
//        TreeNode c = new TreeNode(5);
//
//        TreeNode b = new TreeNode(3, null, d);
//        TreeNode a = new TreeNode(2, null, c);
//
//        TreeNode root = new TreeNode(1, a, b);
//        return root;
//        Integer[] nodes = {1, 2, 3, -1, 5, -1, 4};
//        Integer[] nodes = {1, 2, 3, null, 5, null, 4};
//        return insertNodes(nodes);
//        return insertFromArray(nodes);


////        [1, null, 3]
//        TreeNode a = new TreeNode(3);
//
//        TreeNode root = new TreeNode(1, null, a);
//        return root;
//        Integer[] nodes = {1, null, 3};
//        return insertNodes(nodes);
//        Integer[] nodes = {1, null, 3};
//        return insertFromArray(nodes);

////        []
//        TreeNode root = null;
//        return root;
//        Integer[] nodes = {};
//        return insertNodes(nodes); //not working
//        Integer[] nodes = {};
//        return insertFromArray(nodes);

////        [1, 2, null]
//        TreeNode a = new TreeNode(2);
//
//        TreeNode root = new TreeNode(1, a, null);
//        return root;
//        Integer[] nodes = {1, 2, null};
//        return insertNodes(nodes);
//        Integer[] nodes = {1, 2, null};
//        return insertFromArray(nodes);

////        [1, 2, 3, null, null, 4, null]
//        TreeNode c = new TreeNode(4);
//
//        TreeNode b = new TreeNode(3, c, null);
//        TreeNode a = new TreeNode(2);
//
//        TreeNode root = new TreeNode(1, a, b);
//        return root;
//        Integer[] nodes = {1, 2, 3, null, null, 4, null}; //
//        return insertNodes(nodes);
//        Integer[] nodes = {1, 2, 3, null, null, 4, null};
//        return insertFromArray(nodes);

//        [1, 2, 3, null, 4, null, null]
//        Integer[] nodes = {1, 2, 3, null, 4, null, null};
//        return insertNodes(nodes);
//        Integer[] nodes = {1, 2, 3, null, 4, null, null};
//        return insertFromArray(nodes);

//        [1, 2, 3, 4, null, null, null]
//        Integer[] nodes = {1, 2, 3, 4, null, null, null};
//        return insertNodes(nodes);
//        Integer[] nodes = {1, 2, 3, 4, null, null, null};
//        return insertFromArray(nodes);

//        [1, 2, 3, 4, 5, null, null, null, null, null, 6, 7, 8, 9, null, 10]
//
//        TreeNode i = new TreeNode(10);
//
//        TreeNode h = new TreeNode(9);
//
//        TreeNode g = new TreeNode(8, i, null);
//        TreeNode f = new TreeNode(7, h, null);
//
//        TreeNode e = new TreeNode(6, f, g);
//
//        TreeNode d = new TreeNode(5, null, e);
//        TreeNode c = new TreeNode(4);
//
//        TreeNode b = new TreeNode(3);
//        TreeNode a = new TreeNode(2, c, d);
//
//        TreeNode root = new TreeNode(1, a, b);
//        return root;
        Integer[] nodes = {1, 2, 3, 4, 5, null, null, null, null, null, 6, 7, 8, 9, null, 10};
//        return insertNodes(nodes);
        return insertFromArray(nodes);
    }

    // wrong
//    private static TreeNode insertNode(Integer[] nodes) {
//        Queue<TreeNode> nodesQueue = new LinkedList<>();
//        int i = 0;
////        Integer[] nodes = {1, 2, 3, null, 5, null, 4};
//
//        TreeNode treeRoot = new TreeNode(nodes[i]);
//        nodesQueue.offer(treeRoot);
//
//        while (!nodesQueue.isEmpty()) {
//            TreeNode currentNode = nodesQueue.poll();
//            TreeNode root = null;
////            p(currentNode);
//            if (currentNode != null) {
//                root = currentNode;
//                i++;
//                if (i < nodes.length) {
//                    TreeNode left = null;
//                    if (nodes[i] != null) {
//                        left = new TreeNode(nodes[i]);
//                    }
//                    root.left = left;
//                    nodesQueue.offer(left);
//                    i++;
//                    if (i < nodes.length) {
//                        TreeNode right = null;
//                        if (nodes[i] != null) {
//                            right = new TreeNode(nodes[i]);
//                        }
//                        root.right = right;
//                        nodesQueue.offer(right);
////                        i++;
//                    }
//                }
//            } else {
//                i++;
//                if (i < nodes.length) {
//                    if (nodes[i] != null)
//                        nodesQueue.offer(new TreeNode(nodes[i]));
//                    else
//                        nodesQueue.offer(null);
//                }
//            }
//
////            if (i < 4)
////                treeRoot = root;
//        }
//
//        return treeRoot;
//    }



//    public static TreeNode insertFromArray(int[] arr) {
    public static TreeNode insertFromArray(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode curr = queue.poll();
            if (curr != null) {
//                TreeNode leftChild = (i < arr.length && arr[i] != -1) ? new TreeNode(arr[i]) : null;
//                TreeNode rightChild = (i + 1 < arr.length && arr[i + 1] != -1) ? new TreeNode(arr[i + 1]) : null;
                TreeNode leftChild = (i < arr.length && arr[i] != null) ? new TreeNode(arr[i]) : null;
                TreeNode rightChild = (i + 1 < arr.length && arr[i + 1] != null) ? new TreeNode(arr[i + 1]) : null;
                curr.left = leftChild;
                curr.right = rightChild;
                queue.offer(leftChild);
                queue.offer(rightChild);
                i += 2;
            }
        }
        return root;
    }

    // wrong
    private static TreeNode insertNode(Integer[] nodes) {
        Queue<TreeNode> nodesQueue = new LinkedList<>();
        int i = 0;
//        Integer[] nodes = {1, 2, 3, null, 5, null, 4};
//        [1, 2, 3, 4, 5, null, null, null, null, null, 6, 7, 8, 9, null, 10]

        TreeNode treeRoot = new TreeNode(nodes[i]);
        nodesQueue.offer(treeRoot);

        while (!nodesQueue.isEmpty()) {
            TreeNode currentRoot = nodesQueue.poll();
//            p(currentRoot);
            if (currentRoot != null) {
                i++;
                if (i < nodes.length) {
                    TreeNode left = null;
                    if (nodes[i] != null) {
                        left = new TreeNode(nodes[i]);
                    }
                    currentRoot.left = left;
                    nodesQueue.offer(left);
                    i++;
                    if (i < nodes.length) {
                        TreeNode right = null;
                        if (nodes[i] != null) {
                            right = new TreeNode(nodes[i]);
                        }
                        currentRoot.right = right;
                        nodesQueue.offer(right);
                    }
                }
            } else {
                i++;
                if (i < nodes.length) {
                    if (nodes[i] != null)
                        nodesQueue.offer(new TreeNode(nodes[i]));
                    else // may not be needed
                        nodesQueue.offer(null);
                }
            }

        }

        return treeRoot;
    }

    // wrong
    private static TreeNode insertNodes(Integer[] nodes) {
        return insertNode(nodes);
    }

    // DFS With null nodes
    private static void printNode(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printNode(root.left);
            printNode(root.right);
        } else
            System.out.print("null ");
    }

    // BFS print Without null nodes
    private static void printNode2(TreeNode root) {
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

    // wrong
    private static void printNode3(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            TreeNode currentNode = nodes.poll();
            if (currentNode != null) {
                pl(currentNode.val);
                if (currentNode.left != null || currentNode.right != null) {
                    nodes.offer(currentNode.left);
                    nodes.offer(currentNode.right);
                }
            } else
                pl(null);
        }
    }

    // wrong
    private static void printNode4(TreeNode root) {
        if (root == null)
            return;
//        Integer[] nodes = {1, 2, 3, null, null, 4, null}; /////

        Queue<TreeNode> nodesQueue = new LinkedList<>();
        nodesQueue.offer(root);

        while (!nodesQueue.isEmpty()) {
            TreeNode currentNode = nodesQueue.poll();
            if (currentNode != null) {
                pl(currentNode.val);
                if (currentNode.left != null || currentNode.right != null) {
//                if (currentNode.left != null || currentNode.right != null || !nodesQueue.isEmpty()) {
                    nodesQueue.offer(currentNode.left);
                    nodesQueue.offer(currentNode.right);
                }
            } else
                pl(null);
        }
    }

    // BFS print With null nodes and Without Trimming null nodes at the end
    private static void printNode5(TreeNode root) {
        if (root == null)
            return;
//        Integer[] nodes = {1, 2, 3, null, null, 4, null}; /////
//        [1, 2, 3, null, 5, null, 4]

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

    // *B BFS print With null nodes and With Trimming trailing null nodes
    public static List<Integer> printTree(TreeNode root) {
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

}
