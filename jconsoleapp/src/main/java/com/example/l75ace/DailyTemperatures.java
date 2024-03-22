package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pa;
import static com.example.jconsoleapp.MyHelper.pal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

// #L.739
public class DailyTemperatures {

    public static void preDailyTemperatures() {
        getInput(1);

        p("temperatures ");
        pal(temperatures);
        p("expectedOutput ");
        pal(expectedOutput);
        p();
        pal(dailyTemperatures(temperatures));
        pal(dailyTemperatures2(temperatures));
        pal(dailyTemperatures3(temperatures));
        pal(dailyTemperatures4(temperatures));
        pal(dailyTemperatures5(temperatures));
        pal(dailyTemperatures6(temperatures));
    }

//    Constraints:
//        1 <= temperatures.length <= 105
//        30 <= temperatures[i] <= 100

    private static int[] temperatures;
    private static int[] expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
                expectedOutput = new int[]{1, 1, 4, 2, 1, 1, 0, 0};
                break;
            case 2:
                temperatures = new int[]{30, 40, 50, 60};
                expectedOutput = new int[]{1, 1, 1, 0};
                break;
            case 3:
                temperatures = new int[]{30, 60, 90};
                expectedOutput = new int[]{1, 1, 0};
                break;
        }
    }


    /**
     * Time Limit Exceeded - 47 / 48 testcases passed. Brute Force Approach
     * Time complexity O(n^2)
     */
    private static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        for (int current = 0; current < temperatures.length; current++) {
            for (int next = current + 1; next < temperatures.length; next++) {
                if (temperatures[current] < temperatures[next]) {
                    result[current] = next - current;
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 451ms - 58.3mb. Map
     * get temperatures[i] instead of i because values are less than length
     */
    private static int[] dailyTemperatures2(int[] temperatures) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        // Key temperature, Value indexes

        for (int i = 0; i < temperatures.length; i++) {
            if (!map.containsKey(temperatures[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(0); // first index of list is reserved to store last used index in this map list, and not temperatures array for optimization.
                list.add(i); // other indexes in this map list refer to indexes in temperature array
                map.put(temperatures[i], list);
            } else {
                List<Integer> list = map.get(temperatures[i]);
                list.add(i);
                map.put(temperatures[i], list);
            }
        }

        int[] res = new int[temperatures.length]; // not necessary

        for (int i = 0; i < temperatures.length - 1; i++) {
            int currentTemperature = temperatures[i];
            int minDays = Integer.MAX_VALUE; // or 100000 (constraints)

            int lookFor = currentTemperature;
            while (lookFor < 100) {
                lookFor++; // higher temperature
                List<Integer> indexesList = map.get(lookFor);
                if (indexesList != null) { // the number (temperature) exist in days.
                    int lastUsedIndex = indexesList.get(0); // refers to index in map list
                    for (int j = lastUsedIndex; j < indexesList.size(); j++) {
                        int days = indexesList.get(j) - i; // indexesList.get(j)
                        if (days > 0) { // aka. indexesList.get(j) > i. Upcoming days
                            minDays = Math.min(days, minDays);
                            indexesList.set(0, j); // update last used index (optimization)
                            break;
                        }
                    }
                }
            }
            res[i] = minDays == Integer.MAX_VALUE ? 0 : minDays;
        }

        return res;
    }


    /**
     * 766ms - 63.6mb. Map
     * get temperatures[i] instead of i because values are less than length
     * enhanced {@link #dailyTemperatures2(int[])} by iterating through stored temperatures in map instead of iterating through temperatures all the way to 100.
     */
    private static int[] dailyTemperatures3(int[] temperatures) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        // Key temperature, Value indexes

        for (int i = 0; i < temperatures.length; i++) {
            if (!map.containsKey(temperatures[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(0); // first index of list is reserved to store last used index in this map list, and not temperatures array for optimization.
                list.add(i); // other indexes in this map list refer to indexes in temperature array
                map.put(temperatures[i], list);
            } else {
                List<Integer> list = map.get(temperatures[i]);
                list.add(i);
                map.put(temperatures[i], list);
            }
        }

        int[] res = new int[temperatures.length]; // not necessary

        Set<Integer> keysSet = map.keySet();
        for (int i = 0; i < temperatures.length - 1; i++) {
            int currentTemperature = temperatures[i];
            int minDays = Integer.MAX_VALUE; // or 100000 (constraints)

            Iterator<Integer> keysIterator = keysSet.iterator();
            int lookFor;
            while (keysIterator.hasNext()) {
                lookFor = keysIterator.next();
                if (lookFor <= currentTemperature) // lower temperature
                    continue;
                List<Integer> indexesList = map.get(lookFor);
                if (indexesList != null) { // the number (temperature) exist in days.
                    int lastUsedIndex = indexesList.get(0); // refers to index in map list
                    for (int j = lastUsedIndex; j < indexesList.size(); j++) {
                        int days = indexesList.get(j) - i; // indexesList.get(j)
                        if (days > 0) { // aka. indexesList.get(j) > i. Upcoming days
                            minDays = Math.min(days, minDays);
                            indexesList.set(0, j); // update last used index (optimization)
                            break;
                        }
                    }
                }
            }
            res[i] = minDays == Integer.MAX_VALUE ? 0 : minDays;
        }

        return res;
    }


    /**
     * *B L 6ms - 60.7mb. Array
     */
    private static int[] dailyTemperatures4(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        int hotest = 0;

        for (int currDay = n - 1; currDay >= 0; currDay--) {
            int currTemperature = temperatures[currDay];
            if (currTemperature >= hotest) {
                hotest = currTemperature;
                continue;
            }

            int days = 1;
            // current day's temperature is greater then the previous day's temperature
            while (temperatures[currDay + days] <= currTemperature)
                days += res[currDay + days];

            res[currDay] = days;
        }

        return res;
    }


    /**
     * YT, L 62ms - 63.2mb. Best Monotonic Stack.
     * in this solution it's Strictly Decreasing Stack.
     * Time and Space Complexity O(n) (Linear)
     */
    private static int[] dailyTemperatures5(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * L 77ms - 59.8mb. Monotonic Stack.
     * in this solution it's Decreasing Stack, and probably not Strictly Decreasing Stack.
     * Time and Space Complexity O(n) (Linear)
     */
    private static int[] dailyTemperatures6(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                res[i] = stack.peek() - i;
            }

            stack.push(i);
        }

        return res;
    }


    // It's an Array, Stack, Monotonic Stack problem.
}
