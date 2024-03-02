package com.example.l75ace;

import com.example.jconsoleapp.MyHelper;

import java.util.ArrayList;
import java.util.List;

// #L643
public class MaximumAverageSubarrayI {

    public static double preFindMaxAverage() {
//        int[] nums = {1, 12, -5, -6, 50, 3};
//        int k = 4;
//        int[] nums = {5};
//        int k = 1;

        int[] nums = setNumsArray();
        int k = 139;
        MyHelper.p("Expected \n558.29496");

//        int[] nums = {1, 12, -5, -6, 50, 3};
//        int k = 2;

//        MyHelper.p("k " + k);

//        MyHelper.p(findMaxAverage(nums, k));
//        MyHelper.p(findMaxAverage2(nums, k));
//        MyHelper.p(findMaxAverage3(nums, k));
//        MyHelper.p(findMaxAverage4(nums, k));
        MyHelper.p(findMaxAverage5(nums, k));

        return findMaxAverage(nums, k);
//        return findMaxAverage2(nums, k);
//        return findMaxAverage2e(nums, k);
    }

    // Time Limit Exceeded - 72 / 127 testcases passed
    public static double findMaxAverage(int[] nums, int k) {

        int l = k - 1;
//        float maxAverage = 0; // *Wrong answer
        // float  result 558.2949829101562,
        // double result 558.294964028777
        double maxAverage = 0;

        while (l < nums.length) {
            int f = l - k + 1;

            List<Integer> subArray = new ArrayList<>(k);
            for (int i = f; i < f + k; i++) {
                subArray.add(nums[i]);
            }

//            float average = subArray.get(0); // *Wrong answer
            double average = subArray.get(0);
            for (int i = 1; i < subArray.size(); i++) {
                average = average + subArray.get(i);
            }
            average = average / k;

            if (f == 0) // first subArray
                maxAverage = average;
            else
                maxAverage = maxAverage < average ? average : maxAverage;
            // OR
//                maxAverage = Math.max(maxAverage, average);

            l++;
        }

        return maxAverage;
    }

    // Time Limit Exceeded - 125 / 127 testcases passed
    public static double findMaxAverage2(int[] nums, int k) {

        int l = k - 1;
        double maxAverage = 0;

        while (l < nums.length) {
            int f = l - k + 1;

            double average = nums[f];
            int i = f + 1;
            while (i <= l) {
                average = average + nums[i];
                i++;
            }
            average = average / k;


            if (f == 0) // first subArray
                maxAverage = average;
            else
                maxAverage = maxAverage < average ? average : maxAverage;
            // OR
//                maxAverage = Math.max(maxAverage, average);

            l++;
        }

        return maxAverage;
    }

    // Time Limit Exceeded - 125 / 127 testcases passed
    public static double findMaxAverage3(int[] nums, int k) {
        double maxAverage = 0;
        for (int i = 0; i < k; i++) {
            maxAverage = maxAverage + nums[i];
        }
        maxAverage = maxAverage / k;

        int l = k;
        while (l < nums.length) {
            int f = l - k + 1;

            double average = nums[f];
            f++;
            while (f <= l) {
                average = average + nums[f];
                f++;
            }
            average = average / k;

            maxAverage = maxAverage < average ? average : maxAverage;

            l++;
        }

        return maxAverage;
    }

    // YT Runtime 4ms - Memory 55.1mb
    public static double findMaxAverage4(int[] nums, int k) {
        // window's first position
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
            // OR
//            sum = sum + nums[i];
        }

        // slide window and compare
        double res = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            res = Math.max(sum, res);
        }

        return res / k;
    }

    /**
     * L 8ms.
     * Track the beginning of the max average sub array items.
     */
    public static double findMaxAverage5(int[] nums, int k) {
        int maxStart = 0;

        int max = 0;
        for (int i = 0; i < k; i++)
            max += nums[i];

        int total = max;
        for (int i = 1; i < (nums.length - k + 1); i++) {
            total -= nums[i - 1];
            total += nums[i - 1 + k];
            if (total > max) {
                max = total;
                maxStart = i;
            }
        }

        return (double) max / k;
    }

    //todo
    // solution
//            subArray.subList()
    // Q: float and double difference in the first method


    // for findMaxAverage
    private static int[] setNumsArray() {
        return new int[]{527, 122, 983, 61, 986, 421, 839, 321, 443, 224, 548, 408, 728, 590, 64, 621, 724, 937, 914, 273, 838, 987, 788, 123, 197, 504, 335, 155, 463, 372, 569, 343, 847, 905, 756, 833, 678, 595, 155, 122, 819, 55, 882, 899, 997, 946, 520, 74, 235, 434, 347, 73, 774, 487, 549, 323, 992, 884, 478, 807, 609, 400, 502, 456, 657, 258, 289, 335, 205, 796, 809, 376, 203, 691, 275, 553, 989, 147, 627, 225, 934, 326, 650, 708, 166, 199, 31, 158, 436, 862, 317, 397, 262, 820, 853, 919, 78, 494, 606, 284, 643, 416, 660, 198, 107, 288, 751, 97, 435, 730, 674, 369, 409, 324, 77, 575, 876, 109, 733, 664, 323, 50, 61, 585, 222, 266, 856, 301, 112, 462, 585, 755, 878, 597, 306, 338, 885, 57, 787, 321, 140, 813, 690, 549, 137, 120, 124, 365, 581, 857, 29, 904, 259, 442, 841, 482, 708, 697, 135, 821, 511, 72, 928, 742, 669, 234, 432, 555, 644, 571, 228, 784, 384, 918, 333, 873, 390, 457, 239, 971, 666, 620, 875, 277, 63, 716, 111, 771, 765, 246, 592, 629, 318, 873, 723, 340, 107, 155, 247, 751, 78, 475, 535, 462, 745, 220, 335, 136, 677, 574, 459, 695, 195, 335, 973, 258, 403, 436, 381, 521, 683, 326, 502, 353, 199, 225, 45, 306, 732, 292, 410, 810, 119, 297, 624, 865, 518, 959, 353, 547, 534, 812, 243, 81, 499, 568, 691, 255, 4, 72, 128, 687, 398, 630, 393, 949, 207, 438, 608, 291, 83, 18, 101, 202, 667, 725, 419, 537, 36, 772, 85, 922, 937, 680, 3, 436, 248, 46, 691, 252, 471, 819, 292, 221, 801, 37, 171, 8, 827, 779, 299, 910, 149, 752, 465, 168, 829, 884, 706, 866, 9, 791, 788, 946, 471, 144, 382, 71, 190, 426, 323, 661, 597, 967, 235, 399, 4, 406, 759, 832, 537, 411, 94, 686, 163, 559, 854, 345, 796, 912, 211, 805, 703, 351, 751, 526, 847, 485, 597, 38, 263, 273, 51, 861, 240, 286, 612, 597, 44, 723, 429, 581, 134, 875, 619, 298, 435, 826, 995, 583, 738, 206, 388, 794, 909, 491, 672, 757, 976, 622, 147, 592, 895, 550, 453, 487, 189, 417, 84, 233, 140, 865, 167, 627, 741, 786, 277, 528, 964, 272, 111, 55, 830, 851, 849, 739, 342, 873, 848, 318, 495, 995, 910, 742, 898, 715, 230, 87, 484, 666, 672, 977, 884, 191, 604, 977, 330, 881, 505, 294, 153, 968, 349, 983, 819, 550, 74, 161, 424, 923, 831, 271, 270, 94, 14, 520, 161, 596, 607, 998, 614, 632, 975, 498, 823, 931, 827, 505, 164, 332, 800, 317, 652, 501, 652, 823, 404, 78, 336, 828, 353, 520, 451, 976, 966, 817, 496, 127, 765, 104, 477, 380, 88, 804, 230, 263, 735, 58, 769, 899, 742, 921, 568, 395, 422, 572, 570, 826, 3, 907, 6, 356, 427, 458, 332, 393, 627, 829, 872, 393, 285, 702, 773, 725, 506, 355, 988, 242, 413, 757, 493, 508, 30, 414, 255, 453, 986, 825, 631, 989, 732, 638, 346, 511, 448, 30, 256, 75, 211, 481, 468, 496, 183, 593, 573, 689, 949, 562, 283, 714, 671, 777, 222, 702, 191, 477, 507, 529, 303, 138, 519, 387, 776, 217, 899, 576, 599, 507, 4, 811, 988, 472, 659, 171, 418, 233, 861, 367, 147, 496, 81, 818, 273, 304, 872, 816, 133, 379, 346, 436, 870, 217, 824, 998, 786, 75, 575, 385, 934, 579, 196, 923, 403, 856, 446, 821, 441, 307, 540, 588, 156, 974, 758, 781, 630, 983, 598, 763, 714, 296, 552, 584, 513, 376, 583, 299, 803, 510, 36, 737, 89, 233, 12, 844, 441, 459, 18, 234, 118, 558, 822, 274, 884, 932, 56, 514, 915, 654, 278, 630, 302, 182, 214, 167, 558, 149, 466, 713, 659, 502, 802, 100, 87, 815, 945, 880, 274, 315, 114, 744, 225, 288, 19, 110, 221, 427, 624, 136, 433, 254, 118, 735, 436, 333, 902, 346, 834, 720, 411, 494, 574, 214, 594, 662, 381, 891, 894, 7, 558, 361, 751, 784, 649, 122, 894, 870, 549, 518, 359, 982, 125, 477, 717, 913, 162, 971, 612, 997, 691, 23, 491, 266, 589, 437, 280, 970, 681, 174, 329, 239, 535, 81, 23, 537, 555, 269, 407, 105, 140, 118, 87, 265, 596, 157, 530, 758, 128, 142, 107, 172, 166, 598, 438, 755, 388, 718, 78, 421, 244, 407, 12, 132, 840, 36, 669, 396, 657, 428, 853, 797, 547, 292, 62, 495, 801, 945, 253, 282, 87, 361, 454, 253, 311, 892, 361, 51, 962, 791, 472, 558, 550, 485, 42, 391, 873, 711, 139, 530, 492, 344, 680, 391, 636, 94, 886, 790, 39, 491, 72, 127, 852, 526, 732, 516, 418, 445, 919, 732, 236, 392, 290, 787, 229, 685, 530, 454, 748, 669, 336, 240, 365, 16, 631, 1, 111, 517, 143, 150, 361, 215, 629, 213, 741, 362, 81, 511, 807, 1, 243, 396, 745, 886, 183, 974, 571, 713, 780, 319, 734, 116, 912, 99, 485, 895, 452, 596, 413, 596, 98, 126, 811, 728, 339, 905, 442, 773, 416, 601, 126, 12, 997, 871, 898, 180, 197, 821, 245, 977, 492, 331, 445, 404, 430, 930, 300, 235, 878, 65, 183, 977, 191, 994, 57, 882, 251, 499, 7, 668, 100, 133, 680, 450, 4, 930, 982, 553, 103, 228, 882, 595, 559, 680, 0, 342, 610, 652, 577, 841, 717, 112, 818, 260, 458, 227, 142, 710, 726, 502, 378, 178, 635, 410, 628, 992, 692, 611, 545, 795, 191, 780, 390, 102, 460, 742, 796, 422, 394, 725, 263, 463, 837, 433, 723, 296, 660, 218, 6, 386, 720, 736, 917, 707, 498, 897, 699, 190, 860, 597, 985, 51, 377, 727, 506, 837, 470, 302, 611, 216, 28, 875, 680, 865, 308, 755, 161, 969, 325, 519, 707, 397, 255, 976, 105, 753, 874, 804, 295, 734, 401, 632};
    }
}
