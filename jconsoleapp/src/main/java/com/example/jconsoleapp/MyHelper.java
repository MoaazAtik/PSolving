package com.example.jconsoleapp;

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

    /**
     * Get user input
     */
    public static void s() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
    }

    public static void p(int i) {
        System.out.println(i);
    }

    public static void p(Object i) {
        System.out.println(i);
    }

}
