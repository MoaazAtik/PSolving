package com.example.jconsoleapp;

// #Leetcode-88 #L88
public class MergeSortedArray {

    /**
     * Call merge method and pass values
     */
    public static void preMerge() {
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums2 = {2, 5, 6};
//        int m = 3;
//        int n = 3;
//        int[] nums1 = {1};
//        int[] nums2 = {};
//        int m = 1;
//        int n = 0;
        int[] nums1 = {0};
        int[] nums2 = {1};
        int m = 0;
        int n = 1;

        merge(nums1, m, nums2, n);
    }

    /**
     * Merge sorted arrays
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        if (m == 0) {
            nums1 = nums2;
            MyHelper.pa(nums1);
            return;
        }

        int[] result = new int[m + n];

        int r = 0;
        int i = 0;
        int j = 0;

        while (r < (m + n)) {

            if (i < m) {
                if (j < n) {
                    if (nums1[i] <= nums2[j]) {
                        result[r] = nums1[i];
                        i++;
                    } else {
                        result[r] = nums2[j];
                        j++;
                    }
                } else {
                    result[r] = nums1[i];
                    i++;
                }
            } else {
                result[r] = nums2[j];
                j++;
            }

            r++;
        }

        nums1 = result;
        MyHelper.pa(nums1);
    }

}
