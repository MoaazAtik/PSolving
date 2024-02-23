package com.example.jconsoleapp;

// #L189
public class RotateArray {

    public static void preRotate() {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        int k = 3;
//        int k = 2;
//        int k = 1;
//        int k = 6;
//        int k = 0;
//        int k = 7;
//        int k = 8;
//        int[] nums = {-1, -100, 3, 99};
//        int k = 2;

        int[] nums = {1, 2, 3};
        int k = 2;

//        wRotate(nums, k);
//        rotate(nums, k);
//        wRotate2(nums, k);
        rotate2(nums, k);
    }

    // X* Wrong Rotate: Fails {1, 2} k = 3. Expected output {2, 1}
    public static void wRotate(int[] nums, int k) {

        if (k > nums.length) {
            MyHelper.pal(nums);
            MyHelper.p("k > ");
            return;
        }

        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            a[i] = nums[i];
        // OR
//        System.arraycopy(nums, 0, a, 0, nums.length);

        int l = nums.length - k;
        for (int i = 0; l < nums.length; i++) {
            nums[i] = a[l];
            l++;
            // OR
//            nums[i] = a[l++];
        }
        for (int j = 0; k < nums.length; j++) {
            nums[k] = a[j];
            k++;
            // OR
//            nums[k++] = a[j];
        }

        MyHelper.pal(nums);
    }

    // Runtime: 1ms - Memory: 57.1mb
    public static void rotate(int[] nums, int k) {

        k = k % nums.length;

        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            a[i] = nums[i];
        // OR
//        System.arraycopy(nums, 0, a, 0, nums.length);

        int l = nums.length - k;
        for (int i = 0; l < nums.length; i++) {
            nums[i] = a[l];
            l++;
            // OR
//            nums[i] = a[l++];
        }
        for (int j = 0; k < nums.length; j++) {
            nums[k] = a[j];
            k++;
            // OR
//            nums[k++] = a[j];
        }

        MyHelper.pal(nums);
    }

    // Time Limit Exceeded
    public static void wRotate2(int[] nums, int k) {

        k = k % nums.length;

        while (k > 0) {
            int last = nums.length - 1;
            int temp = nums[last];
            while (last > 0) {
                nums[last] = nums[last - 1];
                last--;
                // Or
//                nums[last] = nums[last-- - 1];
            }
            nums[0] = temp;
            k--;
        }

        MyHelper.pal(nums);
    }

    // Runtime: -ms - Memory: -.-mb
    public static void rotate2(int[] nums, int k) {

        k = k % nums.length;

        int k1 = k;
        int l = nums.length - 1;

        while (k1 > 0) {
            int temp = nums[k1 - 1];
            nums[k1 - 1] = nums[l];
            nums[l] = temp;
            k1--;
            l--;
        }

        k1 = k;
        while (k1 > 0) {
            l = nums.length - 1;
            int temp = nums[l];
            while (l > k) {
                nums[l] = nums[l - 1];
                l--;
            }
            nums[l] = temp;
            k1--;
        }

        MyHelper.pal(nums);
    }

    // Runtime: -ms - Memory: -.-mb
    public static void rotate2v2(int[] nums, int k) {

        k = k % nums.length;

        for (int k1 = k, l = nums.length - 1; k1 > 0; k1--, l--) {
            int temp = nums[k1 - 1];
            nums[k1 - 1] = nums[l];
            nums[l] = temp;
        }

        for (int k1 = k; k1 > 0; k1--) {
            int l = nums.length - 1;
            int temp = nums[l];
            while (l > k) {
                nums[l] = nums[l - 1];
                l--;
            }
            nums[l] = temp;
        }

        MyHelper.pal(nums);
    }

}
