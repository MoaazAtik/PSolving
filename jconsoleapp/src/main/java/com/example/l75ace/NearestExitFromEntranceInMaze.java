package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pa;
import static com.example.jconsoleapp.MyHelper.pal;
import static com.example.jconsoleapp.MyHelper.pl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// #L.1926
public class NearestExitFromEntranceInMaze {

    public static void preNearestExit() {
        // Matrix, Graph
//        char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
//        int[] entrance = {1, 2};
//        char[][] maze = {{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
//        int[] entrance = {1, 0};
//        char[][] maze = {{'.', '+'}};
//        int[] entrance = {0, 0};
//        char[][] maze = {{'+', '.', '+', '+', '+', '+', '+'}, {'+', '.', '+', '.', '.', '.', '+'}, {'+', '.', '+', '.', '+', '.', '+'}, {'+', '.', '.', '.', '+', '.', '+'}, {'+', '+', '+', '+', '+', '.', '+'}}; // example 4
//        int[] entrance = {0, 1};
//        char[][] maze = {{'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}, {'+', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '.', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '.', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '+', '+', '+'}, {'+', '.', '+', '+', '+', '.', '+', '+', '+', '+'}, {'+', '.', '.', '.', '.', '.', '+', '+', '+', '+'}, {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}}; // example 5
//        int[] entrance = {8, 5};
        char[][] maze = {{'+', '.', '+', '+', '+', '+', '+'}, {'+', '.', '+', '.', '.', '.', '+'}, {'+', '.', '+', '.', '+', '.', '+'}, {'+', '.', '.', '.', '.', '.', '+'}, {'+', '+', '+', '+', '.', '+', '.'}}; // example 6
        int[] entrance = {0, 1};

        pl("entrance ");
        pal(entrance);
        p("maze ");
        pa(maze);

//        p(nearestExit(maze, entrance)); // wrong
//        p(nearestExit2(maze, entrance));
        p(nearestExit3(maze, entrance));
//        p(nearestExit4(maze, entrance)); // wrong
//        p(nearestExit5(maze, entrance)); // Important Note! comment it before calling other methods because it changes the structure of the maze.
        p(nearestExit6(maze, entrance));
//        p(nearestExit7(maze, entrance)); // wrong
//        p(nearestExit8(maze, entrance)); // wrong
    }

    // wrong for example 4, wrong counting steps
    /*
    0 in 'maze[0].length' because all rows have the same length according to the rule in the puzzle: maze[i].length == n.
    + value is 43
    . value is 46
     */
    private static int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> neighborCells = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return bfs(maze, entrance, neighborCells, visited);
    }

    private static int bfs(char[][] maze, int[] entrance, Queue<int[]> neighborCells, boolean[][] visited) {
        int[] exit = null;

        neighborCells.offer(entrance);

        while (!neighborCells.isEmpty()) {
            int[] currentCell = neighborCells.poll();

            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            boolean atEdge = currentRow == 0 || currentRow == maze.length - 1 || currentCol == 0 || currentCol == maze[currentRow].length - 1;
//            p("adEdge "+atEdge);

            if (visited[currentRow][currentCol])
                continue;
            else if (currentCell != entrance && atEdge) {
                exit = new int[]{currentRow, currentCol};
                break;
            }

            visited[currentRow][currentCol] = true;

            int rowAbove = currentRow - 1;
            int rowBelow = currentRow + 1;
            int colBehind = currentCol - 1;
            int colNext = currentCol + 1;

            boolean isRowAbove = rowAbove > -1; // there is a row above
            boolean isRowBelow = rowBelow < maze.length;
            boolean isColBehind = colBehind > -1;
            boolean isColNext = colNext < maze[currentRow].length;

            if (isRowAbove && maze[rowAbove][currentCol] == '.') {
                int[] emptyCell = {rowAbove, currentCol};
                neighborCells.offer(emptyCell);
            }
            if (isRowBelow && maze[rowBelow][currentCol] == '.') {
                int[] emptyCell = {rowBelow, currentCol};
                neighborCells.offer(emptyCell);
            }
            if (isColNext && maze[currentRow][colNext] == '.') {
                int[] emptyCell = {currentRow, colNext};
                neighborCells.offer(emptyCell);
            }
            if (isColBehind && maze[currentRow][colBehind] == '.') {
                int[] emptyCell = {currentRow, colBehind};
                neighborCells.offer(emptyCell);
            }
        }

//        pal(exit);
        if (exit != null)
            return Math.abs(exit[0] - entrance[0]) + Math.abs(exit[1] - entrance[1]);
        else
            return -1;
    }


    /**
     * Iterative BFS. 11ms - 45.5mb.
     * <p> {@link #nearestExit3(char[][], int[]) nearestExit3()} {@link #explore2(char[][], boolean[][], int[], Queue, Queue)}  explore2()} is the cleaned-up version.
     *
     * @see #nearestExit3(char[][], int[]) nearestExit3()
     * @see #explore2(char[][], boolean[][], int[], Queue, Queue) explore()
     */
    private static int nearestExit2(char[][] maze, int[] entrance) {
        Queue<int[]> neighborCells = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return bfs2(maze, entrance, neighborCells, visited);
    }

    private static int bfs2(char[][] maze, int[] entrance, Queue<int[]> neighborCells, boolean[][] visited) {
        int[] exit = null;
        int steps = 0;

        Queue<int[]> q2 = new LinkedList<>();
        neighborCells.offer(entrance);

//        while (exit == null && (!neighborCells.isEmpty() || !q2.isEmpty())) { // i.e
        while (exit == null && !neighborCells.isEmpty()) {
            while (exit == null && !neighborCells.isEmpty())
                exit = explore(maze, visited, entrance, exit, neighborCells, q2);

            if (exit == null)
                steps++;

            while (exit == null && !q2.isEmpty())
                exit = explore(maze, visited, entrance, exit, q2, neighborCells);

            if (exit == null)
                steps++;
        }

//        pal(exit);
        if (exit != null)
            return steps;
        else
            return -1;
    }

    private static int[] explore(char[][] maze, boolean[][] visited, int[] entrance, int[] exit, Queue<int[]> queue1, Queue<int[]> queue2) {
        int[] currentCell = queue1.poll();

        int currentRow = currentCell[0];
        int currentCol = currentCell[1];
        boolean atEdge = currentRow == 0 || currentRow == maze.length - 1 || currentCol == 0 || currentCol == maze[currentRow].length - 1;

        if (visited[currentRow][currentCol])
            return null;
        else if (currentCell != entrance && atEdge) {
            exit = new int[]{currentRow, currentCol};
            return exit;
        }

        visited[currentRow][currentCol] = true;

        int rowAbove = currentRow - 1;
        int rowBelow = currentRow + 1;
        int colBehind = currentCol - 1;
        int colNext = currentCol + 1;

        boolean isRowAbove = rowAbove > -1; // there is a row above
        boolean isRowBelow = rowBelow < maze.length;
        boolean isColBehind = colBehind > -1;
        boolean isColNext = colNext < maze[currentRow].length;

        if (isRowAbove && maze[rowAbove][currentCol] == '.') {
            int[] emptyCell = {rowAbove, currentCol};
            queue2.offer(emptyCell);
        }
        if (isRowBelow && maze[rowBelow][currentCol] == '.') {
            int[] emptyCell = {rowBelow, currentCol};
            queue2.offer(emptyCell);
        }
        if (isColNext && maze[currentRow][colNext] == '.') {
            int[] emptyCell = {currentRow, colNext};
            queue2.offer(emptyCell);
        }
        if (isColBehind && maze[currentRow][colBehind] == '.') {
            int[] emptyCell = {currentRow, colBehind};
            queue2.offer(emptyCell);
        }
        return null;
    }


    /**
     * Iterative BFS. 10ms - 45.7mb
     * 2 Queues for step counting.
     * cleaned-up {@link #nearestExit2(char[][], int[]) nearestExit2()}
     */
    private static int nearestExit3(char[][] maze, int[] entrance) {
        Queue<int[]> level1Queue = new LinkedList<>();
        Queue<int[]> level2Queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        int[] exit = null;
        int steps = 0;

        level1Queue.offer(entrance);

        while (!level1Queue.isEmpty()) {
            exit = explore2(maze, visited, entrance, level1Queue, level2Queue);
            if (exit == null) steps++;
            else break;

            exit = explore2(maze, visited, entrance, level2Queue, level1Queue);
            if (exit == null) steps++;
            else break;
        }

//        if (exit != null)
//            pal(exit);
        if (exit != null)
            return steps;
        else
            return -1;
    }

    private static int[] explore2(char[][] maze, boolean[][] visited, int[] entrance, Queue<int[]> currentLevelQueue, Queue<int[]> nextLevelQueue) {

        while (!currentLevelQueue.isEmpty()) {
            int[] currentCell = currentLevelQueue.poll();

            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            boolean atEdge = currentRow == 0 || currentRow == maze.length - 1 || currentCol == 0 || currentCol == maze[currentRow].length - 1;

            if (visited[currentRow][currentCol])
                continue;
            else if (currentCell != entrance && atEdge) {
                int[] exit = new int[]{currentRow, currentCol};
                return exit;
            }

            visited[currentRow][currentCol] = true;

            int rowAbove = currentRow - 1;
            int rowBelow = currentRow + 1;
            int colBehind = currentCol - 1;
            int colNext = currentCol + 1;

            boolean isRowAbove = rowAbove > -1; // there is a row above
            boolean isRowBelow = rowBelow < maze.length;
            boolean isColBehind = colBehind > -1;
            boolean isColNext = colNext < maze[currentRow].length;

            if (isRowAbove && maze[rowAbove][currentCol] == '.') {
                int[] emptyCell = {rowAbove, currentCol};
                nextLevelQueue.offer(emptyCell);
            }
            if (isRowBelow && maze[rowBelow][currentCol] == '.') {
                int[] emptyCell = {rowBelow, currentCol};
                nextLevelQueue.offer(emptyCell);
            }
            if (isColNext && maze[currentRow][colNext] == '.') {
                int[] emptyCell = {currentRow, colNext};
                nextLevelQueue.offer(emptyCell);
            }
            if (isColBehind && maze[currentRow][colBehind] == '.') {
                int[] emptyCell = {currentRow, colBehind};
                nextLevelQueue.offer(emptyCell);
            }
        }

        return null;
    }


    /**
     * Recursive DFS. YT - Wrong for example 5, output 29, expected 11.
     * Uses a 2D Array memo[][] as Memoization for Optimization
     */
    private static int nearestExit4(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int min = Integer.MAX_VALUE;

        Integer[][] memo = new Integer[m][n];

        // mark start as visited
        maze[entrance[0]][entrance[1]] = '+';

        // going in all four directions
        for (int i = 0; i < 4; i++) {

            int result = stepsToReachExit(maze, new int[]{entrance[0] + row_dir[i], entrance[1] + col_dir[i]}, memo, m, n);

            if (result != Integer.MAX_VALUE) // not necessary
                min = Math.min(min, result);
        }

//        pa(memo);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static int[] row_dir = {1, -1, 0, 0};
    private static int[] col_dir = {0, 0, 1, -1};

    private static int stepsToReachExit(char[][] maze, int[] enter, Integer[][] memo, int m, int n) {

        int row = enter[0];
        int col = enter[1];

        // this cell should be inside the matrix boundary
        if (row < 0 || row >= m || col < 0 || col >= n)
            return Integer.MAX_VALUE;

//        p(memo[row][col]);
//        p("row "+row);
//        p("col "+col);

        // if this cell is already visited or is wall
        if (maze[row][col] == '+')
            return Integer.MAX_VALUE;
            // found the exit cell
        else if (row == 0 || row == m - 1 || col == 0 || col == n - 1)
            // we will count this as a one step
            return 1;

        else if (memo[row][col] == null) {
            maze[row][col] = '+';
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                int res = stepsToReachExit(maze, new int[]{row + row_dir[i], col + col_dir[i]}, memo, m, n);

                if (res != Integer.MAX_VALUE) // not necessary
                    min = Math.min(min, res);
            }

            if (min != Integer.MAX_VALUE)
                min++;

//            p("min      "+min);
            memo[row][col] = min;
            // this is for back-tracking so that we can explore the remaining paths from this cell
            maze[row][col] = '.';
        }

        return memo[row][col];
    }


    /**
     * *B L Iterative BFS. 3ms - 45.3mb
     * Although we used a Queue as a LinkedList (offer()), we used pop(). poll() also could have been used.
     * side note: pop() throws NoSuchElementException, while poll() doesn't.
     * <p>
     * <b>Important Note</b>: comment it before calling other methods because it changes the structure of the maze!
     */
    private static int nearestExit5(char[][] maze, int[] entrance) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        LinkedList<int[]> list = new LinkedList<>();
        list.add(entrance);

        maze[entrance[0]][entrance[1]] = '+'; // I can add this to skip checking the entrance twice

        int steps = 0;

        while (!list.isEmpty()) {
            int size = list.size();

            while (size-- > 0) {
                int[] pop = list.pop();
                int row = pop[0];
                int col = pop[1];
//                pl("pop ");
//                pal(pop);

                // at Edge
                if (row == maze.length - 1 || col == maze[0].length - 1 || row == 0 || col == 0) {
                    if (row != entrance[0] || col != entrance[1]) { // not the Entrance
                        return steps;
                    }
                }

                /*
                Check if the next cells are valid, and not the current cell.
                Have a look at the next cells, and check if they are valid before adding them.
                 */
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    // out of bounds
                    if (nextRow > maze.length - 1 || nextCol > maze[0].length - 1 || nextRow < 0 || nextCol < 0) {
                        continue;
                    }

                    // not wall and not visited
                    /*
                    Instead of setting current as visited, set next as visited before actually visiting it.
                     */
                    if (maze[nextRow][nextCol] == '.') {
                        list.offer(new int[]{nextRow, nextCol});
//                        pal(new int[]{nextRow, nextCol});
                        maze[nextRow][nextCol] = '+';
                    }
                }
            }

            steps++;

        }

        return -1;
    }


    /**
     * AI Iterative BFS. 5ms - 46.4mb
     * Doesn't change empty cells '.' to walls '+' as visiting Flag.
     * The queue stores the coordination with the Steps used to reach that cell.
     */
    private static int nearestExit6(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{entrance[0], entrance[1], 0});
        visited[entrance[0]][entrance[1]] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int steps = cell[2];

            if ((row != entrance[0] || col != entrance[1]) && (row == 0 || col == 0 || row == m - 1 || col == n - 1)) {
                return steps;
            }

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (isValid(newRow, newCol, m, n) && maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol, steps + 1});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }


    /**
     * AI Recursive BFS. wrong for example 6. output: 13, expected: 7
     */
    private static int nearestExit7(char[][] maze, int[] entrance) {
        NearestExitFromEntranceInMaze.entrance = entrance;
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        return bfs3(maze, entrance[0], entrance[1], visited, 0);
    }

    private static int[] entrance;

    private static int bfs3(char[][] maze, int row, int col, boolean[][] visited, int steps) {
        int m = maze.length;
        int n = maze[0].length;

        // Check if the current cell is an exit
        if ((row != entrance[0] || col != entrance[1]) && (row == 0 || row == m - 1 || col == 0 || col == n - 1)) {
            return steps;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        int minSteps = Integer.MAX_VALUE;

        // Check neighboring cells
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            // Check if the neighboring cell is valid and not visited
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == '.' && !visited[nextRow][nextCol]) {
                int result = bfs3(maze, nextRow, nextCol, visited, steps + 1);
                if (result != -1) {
                    minSteps = Math.min(minSteps, result);
                }
            }
        }

        // Return the minimum steps found so far
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }


    /**
     * AI Recursive BFS. wrong for example 6. output: 13, expected: 7
     */
    private static int nearestExit8(char[][] maze, int[] entrance) {
        NearestExitFromEntranceInMaze.entrance2 = entrance;
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[entrance[0]][entrance[1]] = true; // Mark the entrance cell as visited
        return bfs4(maze, entrance[0], entrance[1], visited, 0);
    }

    private static int[] entrance2;

    private static int bfs4(char[][] maze, int row, int col, boolean[][] visited, int steps) {
        int m = maze.length;
        int n = maze[0].length;

        // Check if the current cell is an exit (excluding the entrance)
        if (row != entrance2[0] || col != entrance2[1]) {
            if (row == 0 || row == m - 1 || col == 0 || col == n - 1) {
                return steps;
            }
        }

        int minSteps = Integer.MAX_VALUE;

        // Check neighboring cells
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            // Check if the neighboring cell is valid and not visited
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == '.' && !visited[nextRow][nextCol]) {
                // Mark the neighboring cell as visited
                visited[nextRow][nextCol] = true;
                int result = bfs4(maze, nextRow, nextCol, visited, steps + 1);
                if (result != -1) {
                    minSteps = Math.min(minSteps, result);
                }
            }
        }

        // Return the minimum steps found so far
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

}
