package com.example.l75ace;

import com.example.jconsoleapp.MyHelper;

import java.util.LinkedList;

// #L.2095
public class DeleteTheMiddleNodeOfALinkedList {

    public static void preDeleteMiddle() {
        int[] headArray = {1, 3, 4, 7, 1, 2, 6};
//        int[] headArray = {1, 2, 3, 4};
//        int[] headArray = {2, 1};

//        int[] headArray = {2};

        ListNode head = populateListNodes(headArray);

        printListNodes(head);
//        MyHelper.p("new head: " + deleteMiddle(head).val); // not for {2}
//        MyHelper.p(deleteMiddle(head)); // for {2}
        MyHelper.p("new head: " + deleteMiddle2(head).val); // not for {2}
//        MyHelper.p(deleteMiddle2(head)); // for {2}
        printListNodes(head);

        // for example 1
//        MyHelper.p(head.val + " next: " + head.next.val);
//        MyHelper.p(head.val + " next: " + head.next.val + " " + head.next.next.val + " " + head.next.next.next.val + " " + head.next.next.next.next.val + " " + head.next.next.next.next.next.val + " " + head.next.next.next.next.next.next.val);
//        MyHelper.p(head.next.next.next.next.next.next.next); // max
    }

    private static ListNode populateListNodes(int[] listNodes) {
        return insertNode(listNodes, 0);
    }

    // Recursive Linked List node inserting
    private static ListNode insertNode(int[] listNodes, int currentNodeIndex) {
        if (currentNodeIndex == listNodes.length - 1)
            return new ListNode(listNodes[currentNodeIndex]);
        // OR
//        if (currentNodeIndex == listNodes.length)
//            return null;

        return new ListNode(listNodes[currentNodeIndex], insertNode(listNodes, currentNodeIndex + 1));
    }

    /**
     * Iterative Linked List node traversing.
     * head = head.next; // when head is re-assigned here it got a new Reference different from the head passed to method (probably)
     */
    private static void printListNodes(ListNode head) {
        while (head != null) {
            MyHelper.pl(head.val);
            head = head.next; // when head is re-assigned here it has a new reference different from the head passed to method?
        }
        MyHelper.p("");
    }


    // Definition for singly-linked list. (from Leetcode)
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 4ms - 63.6mb
    public static ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }

        int listLength = 0;
        ListNode node = head;
        while (node != null) {
            listLength++;
            node = node.next;
        }
//        MyHelper.p(listLength);

        int currentPointer1Position = 0;
        ListNode pointer1 = head;
        ListNode pointer2 = pointer1.next;
        while (currentPointer1Position != (listLength / 2) - 1) {
//        while (currentPointer1Position < (listLength / 2) - 1) {
            pointer1 = pointer1.next;
            pointer2 = pointer1.next;
            currentPointer1Position++;
        }
//        MyHelper.p(pointer1.val);
//        MyHelper.p(pointer2.val);
//        MyHelper.p(currentPointer1Position);

//        printListNodes(head);
        pointer1.next = pointer2.next;

        return head;
    }

    // *B 4ms - 63.5mb
    public static ListNode deleteMiddle2(ListNode head) {
        if (head.next == null)
            return null;

        int listLength = 0;
        ListNode node = head;
        while (node != null) {
            listLength++;
            node = node.next;
        }

        int currentNodePosition = 0;
        node = head;
        while (currentNodePosition != (listLength / 2) - 1) {
            node = node.next;
            currentNodePosition++;
        }

        node.next = node.next.next;

        return head;
    }

}
