package com.example.jconsoleapp;

// #L80
public class RemoveDuplicatesFromSortedArrayII {

    public static int preRemoveDuplicates() {

        int nums[] = {1, 1, 1, 2, 2, 3};
//        int nums[] = {0, 0, 1, 1, 1, 1, 2, 3, 3};

//        int nums[] = {1, 1, 1, 1, 2, 2, 3};
//        int nums[] = {1, 2, 2, 2, 3};
//        int nums[] = {1, 2, 3, 3, 3};
//        int nums[] = {1, 2, 3, 3, 4};
//        int nums[] = {1, 1, 3, 4, 4};
//        int nums[] = {1, 1, 2, 2, 2, 2, 3};


        return removeDuplicates(nums);
    }

    public static int removeDuplicates(int[] nums) {

        // Runtime: 0ms (Beats 100.00% of users with Java) - Memory: 44.5mb
        int n = 0;
        int d = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                d++;
            } else {
                d = 0;
            }

            if (d < 2) {
                n++;
                nums[n] = nums[i];
                // 2 lines above are equivalent to
//                nums[++n] = nums[i];
            }
        }

        MyHelper.pa(nums);
        MyHelper.p(11111);

        return n + 1;

        // From Leetcode • Runtime: 0ms
        // it will fail if nums.length == 1 or 2
//        int k = 2;
//        for (int i = 2; i < nums.length; i++) {
//            if (nums[i] != nums[k - 2]) {
//                nums[k] = nums[i];
//                k++;
//            }
//        }
//        return k;


        // From Leetcode • Memory: 43.1mb
//        int i = 0;
//
//        for (final int num : nums)
//            if (i < 2 || num > nums[i - 2])
//                nums[i++] = num;
//
//        return i;


        // From Leetcode • Memory: 42.2mb
//        int i = 0;
//        int count = 1;
//        for (int j = 1; j < nums.length; j++) {
//            if (nums[j] != nums[i]) {
//                count = 1;
//                nums[++i] = nums[j];
//            } else if (count < 2) {
//                count++;
//                nums[++i] = nums[j];
//            }
//        }
//        System.gc();
//        return ++i;
    }

}
