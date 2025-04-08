package com.example.jconsoleapp.others;

import java.util.Stack;

// Problem by The Recurse Center
public class CracklePop {

    /*
    The Problem:
    Write a program that prints out the numbers 1 to 100 (inclusive).
    If the number is divisible by 3, print Crackle instead of the number.
    If it's divisible by 5, print Pop.
    If it's divisible by both 3 and 5, print CracklePop.
     */
    /*
    Depending on the understanding of the problem we can solve it in multiple ways.
    - The question doesn't explicitly apply the rule of 'instead of the number' on all three cases. Therefore, we could:
    1. Print a word instead of its number in all three cases as implemented in popScan and popScan2 functions.
    2. Print a word without the number only for the first case while in the second and third case we print the number with its word as implemented in popScan3 function.
    - Since it's not determined by the question, I decided to give each number (and/or its word) a separate line.
     */
    public static void prePopScan() {
//        popScan();
//        popScan2();
        popScan3();
    }

    /**
     * Basic solution.
     * Print a word instead of its number in all three cases.
     */
    public static void popScan() {
        for (int number = 1; number <= 100; number++) {
            boolean numberIsDivisibleBy3 = number % 3 == 0;
            boolean numberIsDivisibleBy5 = number % 5 == 0;

            if (!numberIsDivisibleBy3 && !numberIsDivisibleBy5) {
                System.out.println(number);
                continue;
            }

            if (numberIsDivisibleBy3)
                System.out.print("Crackle");
            if (numberIsDivisibleBy5)
                System.out.print("Pop");

            System.out.println();
        }
    }


    /**
     * Solution implementing predefined math division rules.
     * Print a word instead of its number in all three cases.
     *
     * Note: Though it might not make a significant impact on this problem particularly,
     * it can improve the performance when large numbers and more division checks get involved.
     */
    public static void popScan2() {
        for (int number = 1; number <= 100; number++) {
            boolean numberIsDivisibleBy3 = isDivisibleBy3(number);
            boolean numberIsDivisibleBy5 = isDivisibleBy5(number);

            if (!numberIsDivisibleBy3 && !numberIsDivisibleBy5) {
                System.out.println(number);
                continue;
            }

            if (numberIsDivisibleBy3)
                System.out.print("Crackle");
            if (numberIsDivisibleBy5)
                System.out.print("Pop");

            System.out.println();
        }
    }

    /**
     * Check if the provided number is divisible by 3 by checking this predefined math rule:
     * If the sum of all numbers of a number is divisible by 3, then the number itself is divisible by 3.
     * @return true if it's divisible, and false otherwise
     */
    private static boolean isDivisibleBy3(int number) {
        int remainder;
        Stack<Integer> numbers = new Stack<>();
        int sum = 0;

        while (number != 0) {
            remainder = number % 10;
            numbers.push(remainder);
            number /= 10;
        }

        while (!numbers.isEmpty())
            sum += numbers.pop();

        return sum % 3 == 0;
    }

    /**
     * Check if the provided number is divisible by 5 by checking this predefined math rule:
     * If the number in the ones position of a number is 0 or 5, then the number itself is divisible by 5.
     * @return true if it's divisible, and false otherwise
     */
    private static boolean isDivisibleBy5(int number) {
        int remainder = number % 10;

        return remainder % 5 == 0;
    }


    /**
     * Print a word without the number only for the first case
     * while in the second and third case we print the number with its word.
     */
    public static void popScan3(){
        for (int number = 1; number <= 100; number++) {
            boolean numberIsDivisibleBy3 = number % 3 == 0;
            boolean numberIsDivisibleBy5 = number % 5 == 0;

            if (numberIsDivisibleBy5 && numberIsDivisibleBy3) {
                System.out.println(number + "CracklePop");
                continue;
            }

            if (numberIsDivisibleBy5) { // && !numberIsDivisibleBy3
                System.out.println(number + "Pop");
                continue;
            }

            if (numberIsDivisibleBy3) { // && !numberIsDivisibleBy5
                System.out.println("Crackle");
                continue;
            }

            System.out.println(number);
        }
    }

}
