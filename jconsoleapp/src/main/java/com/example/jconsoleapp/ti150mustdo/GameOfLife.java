package com.example.jconsoleapp.ti150mustdo;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// #L.289
public class GameOfLife {

    public static void preGameOfLife() {
        getInput(5);

        p("board ");
        pa(board);
        p("expectedOutput");
        pa(expectedOutput);
        p();
//        gameOfLife(board); // Disable it before calling another method. modifies the input
//        gameOfLife2(board); // Disable it before calling another method. modifies the input
        gameOfLife3(board); // Disable it before calling another method. modifies the input
        pa(board);
    }

    private static int[][] board;
    private static int[][] expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
                expectedOutput = new int[][]{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
                break;
            case 2:
                board = new int[][]{{1, 1}, {1, 0}};
                expectedOutput = new int[][]{{1, 1}, {1, 1}};
                break;

            case 3:
                board = new int[][]{{0, 1, 0, 1, 0, 1}, {0, 0, 1, 0, 0, 1}, {1, 1, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 1}, {0, 1, 1, 0, 0, 0}, {1, 0, 0, 1, 1, 1}};
                expectedOutput = new int[][]{{0, 0, 1, 0, 1, 0}, {1, 0, 0, 0, 0, 1}, {0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 1}, {0, 1, 1, 1, 1, 0}};
                break;
            case 4:
                board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}, {0, 1, 1}, {1, 0, 0}};
                expectedOutput = new int[][]{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {1, 0, 0}, {0, 1, 0}, {0, 1, 0}};
                break;
            case 5:
                board = new int[][]{{0, 1}, {0, 0}, {1, 1}, {0, 0}, {0, 1}, {1, 0}};
                expectedOutput = new int[][]{{0, 0}, {1, 1}, {0, 0}, {1, 1}, {0, 0}, {0, 0}};
                break;
            case 6:
                board = new int[][]{{0, 1}, {1, 1}, {1, 1}, {0, 0}, {0, 1}, {1, 0}};
                expectedOutput = new int[][]{{1, 1}, {0, 0}, {1, 1}, {1, 1}, {0, 0}, {0, 0}};
                break;
            case 7:
                board = new int[][]{{1}};
                expectedOutput = new int[][]{{0}};
                break;
            case 8:
                board = new int[][]{{0, 1}, {0, 0}};
                expectedOutput = new int[][]{{0, 0}, {0, 0}};
                break;
            case 9:
                board = new int[][]{{1, 0, 0, 1}};
                expectedOutput = new int[][]{{0, 0, 0, 0}};
                break;
        }
    }

//    Constraints:
//    m == board.length
//    n == board[i].length
//    1 <= m, n <= 25
//    board[i][j] is 0 or 1.


    /**
     * 0ms, 42.9mb. Stack.
     * Space O(m x n)
     */
    private static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] result = new int[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Stack<Integer> neighbors = getLiveNeighbors(r, c, board);
                int liveNeighbors = neighbors.size();
                if (board[r][c] == 1) {
                    liveNeighbors--; // because the cell itself was counted too
                    if (liveNeighbors == 2 || liveNeighbors == 3)
                        result[r][c] = 1;
                } else { // board[r][c] == 0
                    if (liveNeighbors == 3)
                        result[r][c] = 1;
                }
            }
        }

//        GameOfLife.board = result;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++)
                board[r][c] = result[r][c];
            // OR
//            System.arraycopy(result[r], 0, board[r], 0, n);
        }
    }

    /**
     * Important: if current cell is live it's added to the neighbors stack too, so it could be subtracted.
     * Stack.
     */
    private static Stack<Integer> getLiveNeighbors(int cellRowIndex, int cellColIndex, int[][] board) {
        Stack<Integer> neighbors = new Stack<>();
        int m = board.length;
        int n = board[0].length;

        for (int r = cellRowIndex - 1; r <= cellRowIndex + 1; r++) {
            if (r < 0 || r >= m) continue;
            for (int c = cellColIndex - 1; c <= cellColIndex + 1; c++) {
                if (c < 0 || c >= n) continue;
                if (board[r][c] == 1)
                    neighbors.push(board[r][c]);
            }
        }

        return neighbors;
    }


    /**
     * 1ms - 42.0mb. Queue.
     * Space O(1) (?), In-place, Stack, Safe distance.
     */
    private static void gameOfLife2(int[][] board) {
        Queue<Integer> queue = new LinkedList<>();
        int m = board.length; // rows
        int n = board[0].length; // cols
        int maxRowIndex = m - 1;
        int maxColIndex = n - 1;
        int backR = -1;
        int backC = -1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                if (c < 2) {
                    if (r >= 2) {
                        backR = r - 2;
                        if (n <= 2)
                            backC = c;
                        else
                            backC = c - 2 + n;
                        board[backR][backC] = queue.poll();
                    }
                } else { // c >= 2
                    if (r >= 1) {
                        backR = r - 1;
                        backC = c - 2;
                        board[backR][backC] = queue.poll();
                    }
                }

                Stack<Integer> neighbors = getLiveNeighbors(r, c, board);
                int liveNeighbors = neighbors.size();
                if (board[r][c] == 1) {
                    liveNeighbors--; // because the cell itself was counted too
                    if (liveNeighbors == 2 || liveNeighbors == 3)
                        queue.offer(1);
                    else
                        queue.offer(0);
                } else { // board[r][c] == 0
                    if (liveNeighbors == 3)
                        queue.offer(1);
                    else
                        queue.offer(0);
                }
            }
        }

        if (n > 2)
            backC++;
        else { // n <= 2
            backC = 0;
            backR++;
        }
        if (backR == -1)
            backR = 0;
        while (backR <= maxRowIndex) {
            while (backC <= maxColIndex) {
                board[backR][backC] = queue.poll();
                backC++;
            }
            backC = 0;
            backR++;
        }
    }


    /**
     * *B YT 0ms - 41.4mb. Encoding, Mapping, Modulus (Modulo, Remainder).
     * It can be encoded as follows: the Single position stores Live neighbors count, and the Tenth position stores the actual current living state. It can be accomplished by Multiplying, Dividing, and Modulo 10. YT
     * Space O(1), In-place.
     */
    private static void gameOfLife3(int[][] board) {
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}; // neighbors
        int m = board.length;
        int n = board[0].length;

        // count live neighbors and encode cell
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                // count live neighbors
                int liveCount = 0;
                for (int[] d : dir) {
                    int neighborR = r + d[0];
                    int neighborC = c + d[1];

                    if (neighborR < 0 || neighborR == m || neighborC < 0 || neighborC == n)
                        continue;

                    if (board[neighborR][neighborC] == 1 || board[neighborR][neighborC] == 2)
                        liveCount++;
                }

                // encode cell
                // 2 : live became dead
                // 3 : dead became live
                if (board[r][c] == 1) {
                    if (liveCount < 2 || liveCount > 3)
                        board[r][c] = 2;
                } else // board[r][c] == 0
                    if (liveCount == 3)
                        board[r][c] = 3;
            }
        }

        // update cells
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] %= 2;
            }
        }
    }


    // 2D array, Matrix, Simulation Problem
}
