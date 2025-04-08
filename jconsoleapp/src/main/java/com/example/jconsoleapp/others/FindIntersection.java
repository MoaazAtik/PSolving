package com.example.jconsoleapp.others;

import static com.example.jconsoleapp.MyHelper.p;

// #FindIntersection @ Coderbyte
public class FindIntersection {

    public static void preFindIntersection() {
        String[] arr = new String[]{"1, 3, 4, 7, 13",
                "1, 2, 4, 13, 15"};

        p(findIntersection(arr));
    }

    public static String findIntersection(String[] strArr) {
        String[] left = strArr[0].split(", ");
        String[] right = strArr[1].split(", ");
        String result = "";

        int l = 0, r = 0;
        while (l < left.length && r < right.length) {
            int lNo = Integer.parseInt(left[l]);
            int rNo = Integer.parseInt(right[r]);

            if (lNo < rNo) {
                l++;
            } else if (lNo > rNo) {
                r++;
            } else { // ==
                result += lNo + ",";
                l++;
                r++;
            }
        }

        if (result.isEmpty())
            return "false";
        else
            return result.substring(0, result.length() - 1);
    }
}
