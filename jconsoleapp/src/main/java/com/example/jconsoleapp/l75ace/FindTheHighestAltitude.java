package com.example.jconsoleapp.l75ace;

import com.example.jconsoleapp.MyHelper;

// #L.1732
public class FindTheHighestAltitude {

    public static void preLargestAltitude() {
//        int[] gain = {-5, 1, 5, 0, -7};
        int[] gain = {-4, -3, -2, -1, 4, 3, 2};

        MyHelper.p(largestAltitude(gain));
    }

    // 0ms - 40.9mb
    private static int largestAltitude(int[] gain) {
        int largestAlt = 0;
        int currentAlt = 0;
        for (int g : gain) {
            currentAlt += g;
            if (largestAlt < currentAlt)
                largestAlt = currentAlt;
        }

        return largestAlt;
    }

}
