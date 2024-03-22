package com.example.jconsoleapp.ti150mustdo;

import com.example.jconsoleapp.MyHelper;

// #Leetcode-27 #L27
public class RemoveElement {

    public static int preRemoveElement() {

//        int val = 3;
//        int[] nums = {3, 2, 2, 3};
        int val = 2;
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};

//        int val = 2;
//        int[] nums = {0, 1, 2, 2, 2, 0, 4, 2};

        return removeElement(nums, val);
    }

    public static int removeElement(int[] nums, int val) {
        int v = 0;
        int l = nums.length - 1;

        for (int i = 0; i <= l; i++) {
            if (nums[i] == val) {
                while (l > i && nums[l] == val) {
                    v++;
                    l--;
                }
                nums[i] = nums[l];
                nums[l] = val; // not needed
                v++;
                l--;
            }
        }


        MyHelper.pa(nums);
        MyHelper.p(11111);
        MyHelper.p(nums.length - v);
        return nums.length - v;

        // From Leetcode
//        int n = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != val) {
//                nums[n] = nums[i];
//                n++;
//            }
//        }
//        return n;
    }
}
