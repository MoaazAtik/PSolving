package com.example.jconsoleapp.l75ace;

import com.example.jconsoleapp.MyHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//#L.933
public class NumberOfRecentCalls {

    public static void preRecentCounter() {
//        RecentCounter obj = new RecentCounter();
//        RecentCounter2 obj = new RecentCounter2();
        RecentCounter3 obj = new RecentCounter3();
        MyHelper.p(obj.ping(1));
        MyHelper.p(obj.ping(100));
        MyHelper.p(obj.ping(3001));
        MyHelper.p(obj.ping(3002));

//    "RecentCounter", "ping", "ping", "ping", "ping"
//            [], [1], [100], [3001], [3002]
    }

    // 183ms - 55.4mb
    static class RecentCounter {

        private List<Integer> requests;

        public RecentCounter() {
            requests = new ArrayList<>();
        }

        public int ping(int t) {
            requests.add(t);

            int requestsCount = 0;
            int countRangeMinValue = t - 3000;

            for (int i = requests.size() - 1; i >= 0; i--) {
                if (requests.get(i) >= countRangeMinValue)
                    requestsCount++;
                else {
                    break;
                    // OR 218ms - 55.7mb
//                    requests.remove(i);
                }
            }

            return requestsCount;
        }
    }


    /**
     * *B - although it's a Queue question - L 27ms. when I submitted it 19ms - 52.5mb
     * no Queue.
     * * a LeetCode problem's rule/Constraint "At most 104 calls will be made to ping." -> new int[10000]
     */
    static class RecentCounter2 {
        int left;
        int right;
        final int[] arr = new int[10000];

        public RecentCounter2() {
            left = right = 0;
        }

        public int ping(final int t) {
            arr[right++] = t;
//            final var min = t - 3000;
            final int min = t - 3000;
            while (arr[left] < min)
                left++;
            return right - left;
        }
    }


    /**
     * YT 22ms - 53.7mb
     */
    static class RecentCounter3 {
        Queue<Integer> queue;

        public RecentCounter3() {
            this.queue = new LinkedList<>();
        }

        public int ping(final int t) {
            queue.add(t);

            while (queue.peek() < t - 3000)
                queue.poll();

            return queue.size();
        }
    }
}
