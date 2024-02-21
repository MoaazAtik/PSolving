package com.example.jconsoleapp;

// #L169
public class MajorityElement {

    public static int preMajorityElement() {
        int[] nums = {3, 2, 3};
//        int[] nums = {2, 2, 1, 1, 1, 2, 2};

//        int[] nums = {9};
//        int[] nums = {1, 2, 1, 1, 3, 2, 3};
//        int[] nums = {6, 6, 6, 7, 7};
//        int[] nums = {1, 2, 1, 1, 1, 2, 2};
//        int[] nums = {2, 3, 1, 1, 1, 1, 2};
//        int[] nums = {2, 3, 1, 1, 1};

        return majorityElement(nums);
    }

    public static int majorityElement(int[] nums) {

        // 4 (My fourth approach)
        // Runtime: 2003ms - Memory: 53.0mb
        /*
        m reflects the occurrences of majority element (me)
        c reflects the occurrences of the current number
        me is majority element
         */
        int m = 0;
        int me = 0;
        for (int i = 0; i < nums.length; i++) {
            int c = 0;
            for (int n = i; n < nums.length; n++)
                if (nums[n] == nums[i])
                    c++;

            if (c > m) {
                m = c;
                me = nums[i];
            }

        }

        return me;


        // *X L Runtime:5ms
        // * It Fails for {1, 2, 1, 1, 3, 2, 3}
//        return getMajor(nums, nums[0], 0);


        // *X L Memory: 45.2mb
        // * It Fails for {1, 2, 1, 1, 3, 2, 3}
//        int major = nums[0];
//        int count = 1;
//        for (int i = 1; i < nums.length; i++) {
//            if (major == nums[i])
//                count++;
//            else {
//                count--;
//            }
//            if (count == 0) {
//                major = nums[i];
//                count = 1;
//            }
//        }
//
//        return major;


        // 3 (My third approach)
        // * Time Limit Exceeded
        // m here doesn't reflect the actual occurrences of majority element (me), it is just greater than c by 1
//        int m = 0;
//        int me = 0;
//        for (int num : nums) {
//
//            int c = 0;
//            for (int num2 : nums)
//                if (num2 == num) {
//                    c++;
//                    if (c > m) {
//                        m = c;
//                        me = num;
//                        break;
//                    }
//                }
//        }
//        return me;


        // 2 *X (My second approach)
        // * Fails {6, 6, 6, 7, 7}
        // m here doesn't reflect the actual occurrences of majority element (me), it is just greater than c by 1
//        int m = 0;
//        int me = 0;
//        for (int num : nums) {
//            if (num != me) {
//
//                int c = 0;
//                for (int num2 : nums)
//                    if (num2 == num) {
//                        c++;
//                        if (c > m) {
//                            m = c;
//                            me = num;
//                            break;
//                        }
//                    }
//            }
//        }
//        return me;


        // 1 (My first approach)
        // * Time Limit Exceeded
        // m reflects the occurrences of majority element (me)
        // c reflects the occurrences of the current number
        // me is majority element
//        int m = 0;
//        int c = 0;
//        int me = 0;
//        for (int num : nums) {
//            for (int num2 : nums)
//                if (num2 == num)
//                    c++;
//
//            if (c > m) {
//                m = c;
//                me = num;
//            }
//
//            c = 0;
//        }
//        return me;
    }


    // *X for L Runtime:5ms
    // * It Fails for {1, 2, 1, 1, 3, 2, 3}
    public static int getMajor(int[] nums, int major, int index) {
        int count = 0;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == major) {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                return getMajor(nums, nums[i], i);
            }
        }
        return major;
    }
}
