package com.example.jconsoleapp;

// #L191
public class NumberOf1Bits {

    public static int preHammingWeight() {
        MyHelper.p("all 32 bits of" +
                "\n 1: " + "00000000000000000000000000000001" +
                "\n 0: " + "00000000000000000000000000000000" +
                "\n-1: " + "11111111111111111111111111111111" +
                "\n-2: " + "11111111111111111111111111111110" +
                "\n-3: " + "11111111111111111111111111111101" +
                "\n-4: " + "11111111111111111111111111111100" + "\n");


        // e.g 1
        // Binary 0b Hexadecimal 0x
//        int n = 0b1011;
//        int n = new BigInteger("00000000000000000000000000001011", 2).intValue();
//        MyHelper.p("n   " + n +
//                "\n    " + Integer.toBinaryString(n) +
//                "\nhw should be \n    " + 3);

        // e.g 2
//        int n = 0b10000000; // 00000000000000000000000010000000
//        MyHelper.p("n   " + n +
//                "\n    " + Integer.toBinaryString(n) +
//                "\nhw should be \n    " + 1);

        // e.g 3
        // with NOT ~
        int n = ~0b10; // 11111111111111111111111111111101
        MyHelper.p("n   " + n +
                "\n    " + Integer.toBinaryString(n) +
                "\nhw should be \n    " + 31);

        // e.g 4...
//        int n = ~0b10111;
//        MyHelper.p("n   " + n +
//                "\n    " + Integer.toBinaryString(n));


        return hammingWeight(n);
//        return wHammingWeight2(n);
//        return hammingWeight2(n);
//        return hammingWeight3(n);
    }

    // *M (My Solution) Runtime: 0ms (Beats 100.00% of users with Java) - Memory: 40.4mb (Beats 82.31% of users with Java).
    private static int hammingWeight(int n) {
        int hw = 0;
        for (int i = 0; i < 32; i++) {
            hw = hw + ((n >> i) & 1);
        }

        return hw;
    }

    /**
     * Time Limit Exceeded.
     * n % 2 is causing infinite loop for Negative numbers.
     * n >> 1 is causing infinite loop for Negative numbers.
     */
    private static int wHammingWeight2(int n) {
        int hw = 0;
        while (n != 0) {
            hw += n % 2;
            n = n >> 1;
        }
        return hw;
    }

    // *M Runtime: 0ms (Beats 100.00% of users with Java) - Memory: 40.5mb
    private static int hammingWeight2(int n) {
        int hw = 0;
        while (n != 0) {
            hw += n & 1;
            n = n >>> 1;
        }
        return hw;
    }

    // Runtime: 0ms - Memory: 40.4mb
    private static int hammingWeight3(int n) {
        int hw = 0;
        while (n != 0) {
            n = n & (n - 1);
            // OR
//            n &= (n - 1);
            hw += 1;
        }
        return hw;
    }
}
