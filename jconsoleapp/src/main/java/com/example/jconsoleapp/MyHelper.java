package com.example.jconsoleapp;

import java.util.Scanner;

public class MyHelper {

    /**
     * Print array elements
     *
     * @param nums int array
     */
    public static void p(int[] nums) {
        for (int num : nums) {
            System.out.println(num);
        }
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


}
