package com.example.jconsoleapp.ti150mustdo;

import static com.example.jconsoleapp.MyHelper.p;

import java.util.ArrayList;
import java.util.List;

// #L.9
public class PalindromeNumber {

    public static void preIsPalindrome() {
        getInput(1);

        p("x " + x);
        p("expectedOutput " + expectedOutput);
        p();
//        p(isPalindrome(x)); // Disable it before calling another method. It modifies the input
//        p(isPalindrome2(x)); // Disable it before calling another method. It modifies the input
        p(isPalindrome3(x));
//        p(isPalindrome4(x)); // Wrong
    }

    private static int x;
    private static boolean expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                x = 121;
                expectedOutput = true;
                break;
            case 2:
                x = -121;
                expectedOutput = false;
                break;
            case 3:
                x = 10;
                expectedOutput = false;
                break;

            case 4:
                x = 0;
                expectedOutput = true;
                break;
            case 5:
                x = 1;
                expectedOutput = true;
                break;
            case 6:
                x = -1;
                expectedOutput = false;
                break;
            case 7:
                x = 4554;
                expectedOutput = true;
                break;
            case 8:
                x = -4554;
                expectedOutput = false;
                break;

            default:
                x = -4554;
                expectedOutput = false;
                break;
        }
    }


    /**
     * 6ms - 43.8mb. List, Modulus.
     * Important: Disable it before calling another method. It modifies the input
     */
    private static boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        List<Integer> digits = new ArrayList<>();
        while (x != 0) {
            int digit = x % 10;
            digits.add(digit);
            x = x / 10;
        }

        for (int left = 0, right = digits.size() - 1; left < right; left++, right--)
            if (digits.get(left) != digits.get(right))
                // OR
//            if (!digits.get(left).equals(digits.get(right)))
                return false;

        return true;
    }


    /**
     * *B L 4ms - 43.9mb. Modulus
     * Modified {@link #isPalindrome(int)}
     * Important: Disable it before calling another method. It modifies the input
     */
    private static boolean isPalindrome2(int x) {
        if (x < 0)
            return false;

        final int initial = x;
        int reverse = 0;

        while (x > 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        return reverse == initial;
    }


    /**
     * m L 6ms - 44.5mb. String, Characters.
     */
    private static boolean isPalindrome3(int x) {
        if (x < 0)
            return false;

        String numberString = Integer.toString(x); // OR String.valueOf(x)

//        numberString.toCharArray();
        // OR
        for (int left = 0 , right = numberString.length() - 1; left < numberString.length(); left++, right--)
            if (numberString.charAt(left) != numberString.charAt(right))
                return false;

        return true;
    }


    /**
     * Wrong. Integer Overflow
     */
    private static boolean isPalindrome4(int x) {
        p(Integer.reverse(x));
        p(Integer.reverseBytes(x));
        return Integer.reverse(x) == x;
//        return Integer.reverseBytes(x) == x;
    }


    // Math Problem
}
