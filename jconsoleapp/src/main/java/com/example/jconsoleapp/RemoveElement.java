package com.example.jconsoleapp;

// #Leetcode-27
public class RemoveElement {

    public static int preRemoveElement() {

//        int val = 3;
//        int[] nums = {3, 2, 2, 3};
        int val = 2;
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};

        return removeElement(nums, val);
    }

    public static int removeElement(int[] nums, int val) {
        int v = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                int l = nums.length - 1;
                while (l > i && nums[l] == val) {
                    l--;
                }
                nums[i] = nums[l];
                nums[l] = val;
                v++;
            }
        }
//
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == val) {
//                for (int l = nums.length - 1; l > i; l--) {
//                    if (nums[l] != val) {
//                        nums[i] = nums[l];
//                    }
//                }
//                v++;
//            }
//        }


        MyHelper.pa(nums);
        MyHelper.p(11111);
        MyHelper.p(nums.length - v);
        return nums.length - v;
    }
}
