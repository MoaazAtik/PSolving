package com.example.jconsoleapp;

// #L189
public class RotateArray {

    public static void preRotate() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        int k = 3;
        int k = 2;
//        int k = 1;
//        int k = 6;
//        int k = 0;
//        int k = 7;
//        int k = 8;
//        int[] nums = {-1, -100, 3, 99};
//        int k = 2;

//        int[] nums = {1, 2, 3};
//        int k = 2;
//        int[] nums = {1, 2};
//        int k = 3;

//        wRotate(nums, k);
//        rotate(nums, k);
//        eRotate(nums, k);
//        e2Rotate(nums, k);
//        wRotate2(nums, k);
//        rotate2(nums, k);
        rotate2v2(nums, k);
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

    // Runtime: 2ms - Memory: 57.0mb
    // Enhanced rotate
    public static void eRotate(int[] nums, int k) {

        k = k % nums.length;

        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            a[i] = nums[i];

        for (int i = 0; i < nums.length; i++) {
            int p = (i + k) % nums.length;
            nums[p] = a[i];
        }

        MyHelper.pal(nums);
    }

    // Runtime: 2ms - Memory: 56.7mb
    // Enhanced rotate
    public static void e2Rotate(int[] nums, int k) {

        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            a[(i + k) % nums.length] = nums[i];

        for (int i = 0; i < nums.length; i++)
            nums[i] = a[i];

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

    // Runtime: 2ms - Memory: 57.4mb
    public static void rotate2(int[] nums, int k) {

        k = k % nums.length;

        int f = 0;
        int l = nums.length - 1;
        while (f < l) {
            int temp = nums[f];
            nums[f] = nums[l];
            nums[l] = temp;
            f++;
            l--;
        }

        f = 0;
        l = k - 1;
        while (f < l) {
            int temp = nums[f];
            nums[f] = nums[l];
            nums[l] = temp;
            f++;
            l--;
        }

        f = k;
        l = nums.length - 1;
        while (f < l) {
            int temp = nums[f];
            nums[f] = nums[l];
            nums[l] = temp;
            f++;
            l--;
        }

        MyHelper.pal(nums);
    }

    // Runtime: 0ms (Beats 100.00% of users with Java) O(n) - Memory: 57.4mb O(1)
    // *B (Best approach)

    /**
     * Reverse the array, reverse the first half, reverse the last half.
     */
    public static void rotate2v2(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

        MyHelper.pal(nums);
    }

    /**
     * for {@link #rotate2v2(int[], int)}
     */
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
