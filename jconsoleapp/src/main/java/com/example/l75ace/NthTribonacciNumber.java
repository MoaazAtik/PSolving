package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// #L.1137
public class NthTribonacciNumber {

    public static void preTribonacci() {
        getInput(7);

        p("n " + n);
        p("expectedOutput \n" + expectedOutput);
        p();
        p(tribonacci(n));
//        p(tribonacci2(n)); // Time limit exceeded
        p(tribonacci3(n));
        p(tribonacci4(n));
        p(tribonacci5(n));
        p(tribonacci6(n));
    }

    private static int n;
    private static int expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                n = 4;
                expectedOutput = 4;
                break;
            case 2:
                n = 25;
                expectedOutput = 1389537;
                break;

            case 3:
                n = 0; // edge case
                expectedOutput = 0;
                break;
            case 4:
                n = 1; // edge case
                expectedOutput = 1;
                break;
            case 5:
                n = 2; // edge case
                expectedOutput = 1;
                break;
            case 6:
                n = 3;
                expectedOutput = 2;
                break;
            case 7:
                n = 37; // maximum constraint
                expectedOutput = 2;
                break;
        }
    }


    /**
     * Iterative Sliding Window. 0ms - 39.9mb
     */
    private static int tribonacci(int n) {
        int zth = 0;
        int first = 1;
        int second = 1;

        if (n == 0) return zth;
        if (n == 1) return first;
        if (n == 2) return second;

        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = zth + first + second;
            zth = first;
            first = second;
            second = res;
        }

        return res;
    }


    /**
     * Recursive Time Limit Exceeded - 36 / 39 testcases passed
     */
    private static int tribonacci2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        return tribonacci2(n - 1) + tribonacci2(n - 2) + tribonacci2(n - 3);
    }


    /**
     * *B2 Recursive 0ms - 39.9mb. Hash Map.
     * Utilizes the Memoization technique
     */
    private static int tribonacci3(int n) {
        calculated.put(0, 0);
        calculated.put(1, 1);
        calculated.put(2, 1);

        return calculate(n);
    }

    private static final Map<Integer, Integer> calculated = new HashMap<>();

    private static int calculate(int n) {
        Integer res = calculated.get(n);
        if (res != null) return res;

        res = calculate(n - 1) + calculate(n - 2) + calculate(n - 3);

        calculated.put(n, res);

        return res;
    }


    /**
     * *B1 AI Iterative Sliding Window. 0ms - 40.6mb
     * modified {@link #tribonacci(int)}
     */
    private static int tribonacci4(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        // Initialize variables to store the first three Tribonacci numbers
        int prevPrev = 0; // T(0)
        int prev = 1;     // T(1)
        int curr = 1;     // T(2)

        // Compute the N-th Tribonacci number iteratively
        for (int i = 3; i <= n; i++) {
            int next = prevPrev + prev + curr;
            prevPrev = prev;
            prev = curr;
            curr = next;
        }

        // Return the result
        return curr;
    }


    /**
     * L Iterative. Array.
     */
    private static int tribonacci5(int n) {
        if (n <= 1) return n;
        if (n == 2) return 1;

        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }


    /**
     * L Recursive. Arrays
     * Arrays.fill(dp, -1), and || n < 0 are not needed, and (dp[n] != -1) can be replaced with (dp[n] != 0).
     */
    private static int tribonacci6(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return fun(n, dp);
    }

    private static int fun(int n, int[] dp) {
        if (n == 0 || n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = fun(n - 1, dp) + fun(n - 2, dp) + fun(n - 3, dp);
        return dp[n];
    }


    // Other approaches: Matrix Exponentiation, Closed-Form Formula, Binet's Formula, Using Binomial Coefficients.
}
