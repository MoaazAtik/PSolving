package com.example.jconsoleapp;

import java.math.BigInteger;
import java.util.Scanner;

public class MyHelper {

    /**
     * Print array elements
     *
     * @param nums int array
     */
    public static void pa(int[] nums) {
        for (int num : nums) {
            System.out.println(num);
        }
    }

    /**
     * Print array elements in one line
     *
     * @param nums int array
     */
    public static void pal(int[] nums) {
        for (int num : nums) {
            System.out.print(num + "  ");
        }
        System.out.println();
    }

    public static void p(int i) {
        System.out.println(i);
    }

    public static void p(Object i) {
        System.out.println(i);
    }


    /**
     * Get user input
     */
    public static void s() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
    }


    /**
     * Convert Binary to Negative or Positive integer.
     * The 32th bit can be 1.
     * Utilizes BigInteger class.
     */
    public static int negOrPosBinaryToInt(String binaryString) {
//        int i = new BigInteger("11111111111111111111111111111111", 2).intValue();
//        int i = new BigInteger("00000000000000000000000000000001", 2).intValue();
//        MyHelper.p("i = " + i);

        return new BigInteger(binaryString, 2).intValue();
    }

    /**
     * Convert Binary to Positive integer. It can't convert a Negative int.
     * The 32th bit can't be 1.
     * Utilizes Integer class.
     */
    public static int posBinaryToInt(String binaryString) {
//        int i = Integer.parseInt("00000000000000000000000000000001", 2);
//        int i = Integer.parseInt("10010", 2); // the rest of the 32 bits to the left are 0's, so it's a positive number
//        MyHelper.p("i = " + i);

        return Integer.parseInt(binaryString, 2);
    }
}
