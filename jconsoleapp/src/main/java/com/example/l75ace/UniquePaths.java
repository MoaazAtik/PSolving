package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;

// #L.62
public class UniquePaths {

    public static void preUniquePaths() {
        for (int i = 1; i <= 14; i++) {
            getInput(i);

            p("m " + m);
            p("n " + n);
            p("expectedOutput \n" + expectedOutput);
            p();
//            p(uniquePaths2(m, n)); // wrong
            p(uniquePaths3(m, n)); // uses long integer
//            p(uniquePaths4(m, n)); // wrong
            p(uniquePaths5(m, n)); // uses double
            p(uniquePaths6(m, n)); // recursive
            p(uniquePaths7(m, n)); // recursive
            p(uniquePaths8(m, n)); // iterative
            p(uniquePaths9(m, n)); // iterative
            p("----------------------");
        }
//        getInput(14);
//        p(uniquePaths5(m, n));
    }

    /*
    To get the actual expected output, use double, without Math.round. But it will produce wrong answers sometimes.
     */
    private static int m, n, expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                m = 3;
                n = 7;
                expectedOutput = 28;
                break;
            case 2:
                m = 3;
                n = 2;
                expectedOutput = 3;
                break;

            case 3:
                m = 1; // edge case
                n = 1; // edge case
                expectedOutput = 1;
                break;
            case 4:
                m = 100; // maximum constraint
                n = 100; // maximum constraint
                expectedOutput = 1111111111; // 765767669 (?)
                break;
            case 5:
                m = 1;
                n = 100;
                expectedOutput = 1;
                break;
            case 6:
                m = 2;
                n = 100;
                expectedOutput = 100;
                break;
            case 7:
                m = 3;
                n = 100;
                expectedOutput = 5050;
                break;
            case 8:
                m = 50;
                n = 50;
                expectedOutput = 1111111111;
                break;
            case 9:
                m = 6;
                n = 100;
                expectedOutput = 91962520;
                break;
            case 10:
                m = 7;
                n = 100;
                expectedOutput = 1609344100;
                break;
            case 11:
                m = 8;
                n = 100;
                expectedOutput = 1111111111;
                break;
            case 12:
                m = 9;
                n = 100;
                expectedOutput = 1111111111;
                break;

            case 13:
                m = 25;
                n = 9;
                expectedOutput = 10518300;
                break;
            case 14:
                m = 75;
                n = 19;
                expectedOutput = 10518300;
                break;
        }
    }


    // m AI wrong. Throws exception for m = 100, n = 100, and gives wrong results for large cases
    private static int uniquePaths(int m, int n) {

        int totalMoves = (m - 1) + (n - 1);
        int totalMovesFactorial = factorial(totalMoves, 1);
        // OR
//        int totalMovesFactorial = 1;
//        for (int i = 2; i <= totalMoves; i++)
//            totalMovesFactorial *= i;

        int chooseMoves = Math.min(m - 1, n - 1);
        int chooseMovesFactorial = 1;
        for (int i = 2; i <= chooseMoves; i++)
            chooseMovesFactorial *= i;

        int totalMinusChooseMoves = totalMoves - chooseMoves;
        int totalMinusChooseMovesFactorial = 1;
        for (int i = 2; i <= totalMinusChooseMoves; i++)
            totalMinusChooseMovesFactorial *= i;

//        int result = totalMovesFactorial / (chooseMovesFactorial * totalMinusChooseMovesFactorial);
        int result = totalMovesFactorial / chooseMovesFactorial / totalMinusChooseMovesFactorial;

//        p(totalMoves);
        return result;
    }

    /**
     * Recursive method to get factorial.
     *
     * @param num   to get the factorial for.
     * @param start pass 1.
     */
    private static int factorial(int num, int start) {
        if (start > num) // last multiplication, OR if num is 0
            return 1;

        return start * factorial(num, start + 1);
    }


    // AI wrong result m 7, n 100, Integer Overflow m 9, n 100
    private static int uniquePaths2(int m, int n) {
        int totalMoves = (m - 1) + (n - 1);
        int minMoves = Math.min(m, n) - 1;

        int result = 1;

        for (int i = totalMoves, j = 1; i > totalMoves - minMoves; i--, j++) {
            result *= i;
            result /= j;
        }

        return (int) result;
    }


    /*
    "C(n, k)" or "n choose k." The formula for combinations is:
    "n! / (k! * (n - k)!)".
    Let's say we want to choose (m-1) moves from a total of (m-1) + (n-1) moves.
    "C(m-1 + n-1, m-1)"
     */

    /**
     * AI 0ms - 40.3mb. Combinatorial (Combinations) Approach.
     * modified {@link #uniquePaths4(int, int)}.
     * wrong Result Integer Overflow m 8, n 100 But such cases are out of question constraints.
     * <br>"n! / (k! * (n - k)!)".
     * <br>"C(m-1 + n-1, Math.min(m-1, n-1))"
     */
    private static int uniquePaths3(int m, int n) {
        int totalMoves = (m - 1) + (n - 1);
        int chooseMoves = Math.min(m - 1, n - 1);

        long result = 1;

        for (int i = 1; i <= chooseMoves; i++) {
            result *= totalMoves--;
            result /= i;
        }

        // OR
//        for (int i = totalMoves, j = 1; i > totalMoves - chooseMoves; i--, j++) {
//            result *= i;
//            result /= j;
//        }

        return (int) result;
    }


    /**
     * Wrong for m 25, n 9. output 10518299, expected 10518300. 61 / 63 testcases passed.
     * AI Combinatorial (Combinations) Approach.
     * Result reaches max int value (2147483647) starting from m 8, n 100.
     */
    private static int uniquePaths4(int m, int n) {
        // Calculate total moves and moves in one direction
        int totalMoves = (m - 1) + (n - 1);
        int chooseMoves = Math.min(m - 1, n - 1);

        // Initialize result to 1
        double result = 1;

        // Calculate combination using a loop
        for (int i = 1; i <= chooseMoves; i++) {
            result /= i; // division before multiplying
            result *= totalMoves--;
        }

        // Return the result as an integer
        return (int) result;
    }


    /**
     * AI 0ms - 39.9mb. Best Combinatorial Maths (Combinations) Approach.
     * modified {@link #uniquePaths4(int, int)}.
     * Result reaches Integer Overflow starting from m 8, n 100.
     * <br>"n! / (k! * (n - k)!)".
     * <br>"C(m-1 + n-1, Math.min(m-1, n-1))"
     */
    private static int uniquePaths5(int m, int n) {
        // Calculate total moves and moves in one direction
        int totalMoves = (m - 1) + (n - 1);
        int chooseMoves = Math.min(m - 1, n - 1);

        // Initialize result to 1
        double result = 1;

        // Calculate combination using a loop
        for (int i = 1; i <= chooseMoves; i++) {
            result /= i; // division before multiplying
            result *= totalMoves--;
            // OR
//            result = (result / i) * (totalMoves - chooseMoves + i); // without totalMoves--
        }

//        p(result);

        // OR
//        for (int i = totalMoves, j = 1; i > totalMoves - chooseMoves; i--, j++) {
//            result *= i;
//            result /= j;
//        }

        // Return the result as an integer
        return (int) Math.round(result);
    }


    /**
     * Best Recursion, Dynamic Programming. 0ms - 40.1mb.
     * Memoization, 2D Array.
     * Doesn't store last row and column paths in memoization array unlike {@link #uniquePaths7(int, int)}, so it is probably better.
     */
    private static int uniquePaths6(int m, int n) {
        /*
        needed for m 1, n 100, because last row and column paths are not stored.
         */
        if (m == 1 || n == 1)
            return 1;
        int[][] paths = new int[m][n]; // memoization
        getPaths(m - 1, n - 1, paths);
        return paths[m - 1][n - 1]; // max index m-1, n-1
    }

    private static int getPaths(int m, int n, int[][] paths) {
        if (m == 0 || n == 0) { // min index 0, 0
            /*
            paths[m][n] = 1;
            return paths[m][n]; // base
//            OR better without storing last row and column paths (1's)
             */
            return 1; // base
        }

        if (paths[m][n] == 0) // Memoization
            paths[m][n] = getPaths(m - 1, n, paths) + getPaths(m, n - 1, paths);

        return paths[m][n];
    }


    /**
     * Recursion, Dynamic Programming. 0ms - 40.2mb.
     * Memoization, 2D Array.
     * modified {@link #uniquePaths6(int, int)}
     */
    private static int uniquePaths7(int m, int n) {
        int[][] paths = new int[m][n];
        getPaths2(m - 1, n - 1, paths);
        return paths[m - 1][n - 1]; // max index m-1, n-1
    }

    private static void getPaths2(int m, int n, int[][] paths) {
        if (m == 0 || n == 0) { // min index 0, 0
            paths[m][n] = 1;
            return;
        }

        if (paths[m][n] == 0) { // Memoization
            getPaths2(m - 1, n, paths);
            getPaths2(m, n - 1, paths);
        }

        paths[m][n] = paths[m - 1][n] + paths[m][n - 1];
    }


    /**
     * m YT. Iteration. Dynamic Programming (DP) approach. 0ms - 40.0mb.
     * 2D Array, Optimal Substructure.
     * Start index is 0, 0.
     * {@link #uniquePaths9(int, int)} is a better version.
     */
    private static int uniquePaths8(int m, int n) {
        int[][] paths = new int[m][n];

        for (int column = 0; column < n; column++) // fill last row
            paths[m - 1][column] = 1;
        for (int row = 0; row < m; row++) // fill last column
            paths[row][n - 1] = 1;

        for (int row = m - 2; row >= 0; row--)
            for (int column = n - 2; column >= 0; column--)
                paths[row][column] = paths[row + 1][column] + paths[row][column + 1];

        return paths[0][0];
    }


    /**
     * m YT. Best Iteration. Dynamic Programming (DP) approach. 0ms - 40.4mb.
     * 2D Array, Optimal Substructure.
     * Reversed, and better version of {@link #uniquePaths8(int, int)}.
     * Start index is m - 1, n - 1.
     */
    private static int uniquePaths9(int m, int n) {
        int[][] paths = new int[m][n];

        for (int column = 0; column < n; column++) // fill last row
            paths[0][column] = 1;
        for (int row = 0; row < m; row++) // fill last column
            paths[row][0] = 1;

        for (int row = 1; row < m; row++)
            for (int column = 1; column < n; column++)
                paths[row][column] = paths[row - 1][column] + paths[row][column - 1];

//        p(paths[0][0]);

        return paths[m - 1][n - 1];
    }


    // It's a Math, Dynamic Programming, Combinatorics (Combinatorial, Combinations) problem
}
