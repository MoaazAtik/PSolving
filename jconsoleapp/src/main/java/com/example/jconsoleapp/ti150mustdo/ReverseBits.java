package com.example.jconsoleapp.ti150mustdo;

import com.example.jconsoleapp.MyHelper;

import java.math.BigInteger;
import java.util.BitSet;

// #L190
public class ReverseBits {

    // Search for Right and Left Shifting, Unsigned and Signed integers in Java, and 2's Complement.
    public static int preReverseBits() {
        MyHelper.p("all 32 bits of" +
                "\n 1: " + "00000000000000000000000000000001" +
                "\n 0: " + "00000000000000000000000000000000" +
                "\n-1: " + "11111111111111111111111111111111" +
                "\n-2: " + "11111111111111111111111111111110" +
                "\n-3: " + "11111111111111111111111111111101" +
                "\n-4: " + "11111111111111111111111111111100" + "\n");

        // e.g 1
        int n = 43261596;
        MyHelper.p("n   " + n +
                "\n    " + Integer.toBinaryString(n) +
                "\nr should be \n    " + 964176192 +
                "\n    " + "00111001011110000010100101000000");
        // input  0000 0010 1001 0100    0001 1110 1001 1100
        // output 1010 0101 0000 0111    1010 0111 00
        // output 964 176 192
        // output 0011 1001 0111 1000    0010 1001 0100 0000

        // e.g 2
//        int n = -3;
//        MyHelper.p("n   " + n +
//                "\n    " + Integer.toBinaryString(n) +
//                "\nr should be \n    " + -1073741825 +
//                "\n    " + "10111111111111111111111111111111");
        // input represents the signed integer -3
        // input  1111 1111 1111 1111    1111 1111 1111 1101
        // output -1 073 741 825
        // output 1011 1111 1111 1111    1111 1111 1111 1111

        // e.g 3...
////        int n = 0;
////        int n = -1;
////        int n = 1;
//        int n = 3;
//        int n = -1;
//        int n = 20;
//        int n = -21;
//        int n = -671088641;
//        MyHelper.p("n   " + n +
//                "\n    " + Integer.toBinaryString(n) +
//                "\nr should be \n    " + Integer.reverse(n) +
//                "\n    " + Integer.toBinaryString(Integer.reverse(n)));


        return reverseBits(n);
//        return reverseBitsDebug(n);
//        return reverseBits2(n);
//        return reverseBits3(n);
    }

    // *B (Best)
    /**
     * Runtime: 0ms (Beats 100.00% of users with Java) - Memory: 41.6mb.
     * It doesn't change the original n.
     */
    private static int reverseBits(int n) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            int fn = (n >> i) & 1;
            r = (r << 1) | fn;
            // OR
//            r = r | (fn << (31 - i));
        }
        return r;
    }

    /**
     * Debug {@link #reverseBits(int)}
     */
    private static int reverseBitsDebug(int n) {
        String ff = "";
        String nn = "";
        String rr = "";

        int r = 0;
        for (int i = 0; i < 32; i++) {
            rr = Integer.toBinaryString(r);
            int fn = (n >> i) & 1;
            r = (r << 1) | fn;
            // OR
//            r = r | (fn << (31 - i));
            ff = Integer.toBinaryString(fn);
            nn = Integer.toBinaryString(n);
            rr = Integer.toBinaryString(r);
        }

        MyHelper.p("r = " + Integer.toBinaryString(r));
        MyHelper.p("    " + r);
        MyHelper.p("n = " + Integer.toBinaryString(n));
        MyHelper.p("    " + n);

        return r;
    }

    /**
     * Runtime: 0ms (Beats 100.00% of users with Java) - Memory: 41.5mb
     * It changes the original n.
     */
    private static int reverseBits2(int n) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r = r << 1;
            r = r | (n & 1);
            // OR
//            r = r + (n & 1); // because & 1 is 0 or 1
            n = n >> 1;
        }
        return r;
    }

    /**
     * Integer.reverse() reverses the Binary Bits that represent the Integer, not the real Numbers of the integer
     */
    private static int reverseBits3(int n) {
        return Integer.reverse(n);
    }

}
