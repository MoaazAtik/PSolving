package com.example.jconsoleapp.l75ace;

import static com.example.jconsoleapp.MyHelper.p;

// #L.374
public class GuessNumberHigherOrLower {

    public static void preGuessNumber() {
        getInput(6);

        p("n " + n);
        p("pick " + pick);
//        p(guess(6));
//        p(guessNumber(n)); // infinite loop!
//        p(guessNumber2(n)); // infinite loop, integer overflow!
        p(guessNumber3(n));
        p(guessNumber4(n));
    }

    static int n, pick;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                n = 10;
                pick = 6;
                break;
            case 2:
                n = 1;
                pick = 1;
                break;
            case 3:
                n = 2;
                pick = 1;
                break;

            case 4:
                n = 4;
                pick = 1;
                break;
            case 5:
                n = 3;
                pick = 1;
                break;
            case 6:
                n = 2126753390;
                pick = 1702766719;
                break;
        }
    }

    /**
     * Pre-defined by Leetcode
     * Forward declaration of guess API.
     *
     * @param num your guess
     * @return -1 if num is higher than the picked number
     * 1 if num is lower than the picked number
     * otherwise return 0
     * int guess(int num);
     */
    private static int guess(int num) {
//        if (num > pick)
//            return -1;
//        else if (num < pick)
//            return 1;
//        else // (num == pick)
//            return 0;
        // OR
//        return pick < num ? -1 : (pick == num ? 0 : 1);
        // OR
        return Integer.compare(pick, num);
    }

    /**
     * wrong, Time Limit Exceeded - 7 / 25 testcases passed
     * fails for example 4.
     * Binary Search
     * <p></p>
     * <b>It may produce infinite loop!</b>
     */
    private static int guessNumber(int n) {
        int lowest = 1;
        int highest = n;
        int myGuess = (highest - lowest + 1) / 2;
        int guessResult = guess(myGuess);

        while (guessResult != 0) {
            if (guessResult == -1) {
                highest = myGuess;
                myGuess = lowest + ((highest - lowest + 1) / 2);
                guessResult = guess(myGuess);
            } else if (guessResult == 1) {
                lowest = myGuess;
                myGuess = lowest + ((highest - lowest + 1) / 2);
                guessResult = guess(myGuess);
            }
        }
        return myGuess;
    }

    /**
     * wrong, Time Limit Exceeded - 14 / 25 testcases passed
     * fails for example 6 because of Integer Overflow.
     * Binary Search
     * <p></p>
     * <b>It may produce infinite loop!</b>
     */
    private static int guessNumber2(int n) {
        int lowest = 1;
        int highest = n;

        while (lowest <= highest) {
            int myGuess = (highest + lowest) / 2;
            int guessResult = guess(myGuess);

            if (guessResult == 0)
                return myGuess;
            else if (guessResult == -1)
                highest = myGuess - 1;
            else // (guessResult == 1)
                lowest = myGuess + 1;
        }
        return -1; // optional
    }

    /**
     * AI 0ms - 39.9mb
     * Binary Search
     */
    private static int guessNumber3(int n) {
        int lowest = 1;
        int highest = n;

        while (lowest <= highest) {
            int myGuess = lowest + (highest - lowest + 1) / 2;
            int guessResult = guess(myGuess);

            if (guessResult == 0)
                return myGuess;
            else if (guessResult == -1)
                highest = myGuess - 1;
            else // (guessResult == 1)
                lowest = myGuess + 1;
        }
        return -1; // optional
    }

    /**
     * *B AI. 0ms - 40.6mb
     * Binary Search
     * Also, it can be solved with Interpolation Search Algorithm.
     */
    private static int guessNumber4(int n) {
        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2; // my guess
            int result = guess(mid);

            if (result == 0)
                return mid;
            else if (result < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }

        // This line should not be reached if n is a valid range.
        return -1; // Optional: Handle case when the number is not found
    }
}
