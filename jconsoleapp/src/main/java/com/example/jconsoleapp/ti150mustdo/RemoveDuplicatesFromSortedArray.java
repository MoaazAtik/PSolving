package com.example.jconsoleapp.ti150mustdo;

import com.example.jconsoleapp.MyHelper;

// #Leetcode-26 (#L26)
public class RemoveDuplicatesFromSortedArray {

    public static int preRemoveDuplicates() {

        int[] nums = {1, 1, 2};
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

//        int[] nums = {1, 2, 2};
//        int[] nums = {2, 2, 2};
//        int[] nums = {2};

        return removeDuplicates(nums);
    }

    public static int removeDuplicates(int[] nums) {

        // Runtime: 1ms - Memory: 44.5mb
        int n = 0;
        for (int i = 1; i < nums.length; i++) {

            while (nums[i] != nums[n]) {
                n++;
                nums[n] = nums[i];
            }
        }

        MyHelper.pa(nums);
        MyHelper.p(11111);
        MyHelper.p(n + 1);

        return n + 1;


        // From Leetcode • Runtime: 0ms
//        int i, k = 1;
//        for (i = 1; i < nums.length; i++)
//            if (nums[i] != nums[i - 1])
//                nums[k++] = nums[i];
//
//        MyHelper.pa(nums);
//        MyHelper.p(11111);
//
//        return k;

        // From Leetcode • Memory: 44.7mb
//        if (nums.length == 1) {
//            return 1;
//        }
//
//        int i = 0;
//        int r = 0;
//        while (i < nums.length) {
//            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
//                i++;
//            }
//
//            nums[r++] = nums[i++];
//
//        }
//
//        MyHelper.pa(nums);
//        MyHelper.p(11111);
//
//        return r;

    }

}
