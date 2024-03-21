package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pa;
import static com.example.jconsoleapp.MyHelper.pal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

// #L.435
public class NonOverlappingIntervals {

    public static void preEraseOverlapIntervals() {
        getInput(1);

        p("intervals ");
        pa(intervals);
        p("expectedOutput \n" + expectedOutput);
        p();
        p(eraseOverlapIntervals(intervals));
    }

    private static int[][] intervals;
    private static int expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
                expectedOutput = 1;
                break;
            case 2:
                intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
                expectedOutput = 2;
                break;
            case 3:
                intervals = new int[][]{{1, 2}, {2, 3}};
                expectedOutput = 0;
                break;

            case 4:
                intervals = new int[][]{{1, 4}, {2, 3}, {3, 4}};
                expectedOutput = 1;
                break;
            case 5:
                intervals = new int[][]{{1, 4}, {1, 3}, {3, 4}};
                expectedOutput = 1;
                break;
            case 6:
                intervals = new int[][]{{1, 4}, {1, 4}, {3, 4}};
                expectedOutput = 2;
                break;
            case 7:
                intervals = new int[][]{{1, 4}, {2, 5}};
                expectedOutput = 1;
                break;
            case 8:
                intervals = new int[][]{{1, 4}, {2, 3}, {4, 5}};
                expectedOutput = 1;
                break;
            case 9:
                intervals = new int[][]{{1, 5}, {2, 3}, {4, 5}};
                expectedOutput = 1;
                break;
        }
    }

    /**
     * m YT. 50ms - 73.2mb
     * Sort according to intervals Starts, Quick Sort.
     * It could be solved according to intervals Ends too.
     */
    private static int eraseOverlapIntervals(int[][] intervals) {
        // Sort intervals according to their Starts
        sortIntervals(intervals);
//        pa(intervals);

        // Compare intervals
        int removes = 0;
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start < prevEnd) {
                removes++;
                prevEnd = Math.min(prevEnd, end);
            } else // >= overlap
                prevEnd = end;
        }
        return removes;
    }


    // Sort integers. Sort 2D array. Comparator, comparing integers. Quick Sort
    private static void sortIntervals(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);

        // OR
//        Arrays.sort(intervals,
//                (ints1, ints2) ->
//                        ints1[0] - ints2[0]
//                        // OR
//                        Integer.compare(ints1[0], ints2[0])
//                        // OR
////                        e1.compareTo(e2); // Ascending order. Doesn't take Primitive int
////                        e2.compareTo(e1); // Descending order. Doesn't take Primitive int
//        );


        // OR
//        Arrays.sort(intervals,
//                Comparator.comparingInt(
//                        ints -> ints[0]
//                )
//        );

        // OR
//        Arrays.sort(intervals,
//                Comparator.comparingInt(
//                        new ToIntFunction<int[]>() {
//                            @Override
//                            public int applyAsInt(int[] ints) {
//                                return ints[0];
//                            }
//                        }
//                )
//        );
    }


    // Sort (Quick Sort) 2D Array according to the Starts of sub-arrays
    private static void quickSort(int[][] array, int start, int end) {
        if (start >= end) return; //base case

        /*
        pivot variable is not necessary here since it is end. it can be replaced directly by end.
        it is needed if it was not end like in Quick Select in KthLargestElementInAnArray. it was selected randomly in one method.
         */
        int pivot = end;
        int j = start;
        int i = j - 1;
        int[] temp;

        while (j < pivot) {
            if (array[j][0] < array[pivot][0]) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            j++;
        }
        i++;
        temp = array[j]; // = array[pivot] (because j = pivot)
        array[j] = array[i];
        array[i] = temp;

        //i represents the current pivot location
        quickSort(array, start, i - 1);
        quickSort(array, i + 1, end);
    }


    // It's a Dynamic Programming, Greedy Algorithm, Array, Sorting question.
}
