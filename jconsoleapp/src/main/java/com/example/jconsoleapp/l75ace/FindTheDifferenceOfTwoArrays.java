package com.example.jconsoleapp.l75ace;

import com.example.jconsoleapp.MyHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// #L.2215
public class FindTheDifferenceOfTwoArrays {

    public static void preFindDifference() {
//        int[] nums1 = {1, 2, 3}, nums2 = {2, 4, 6};
        int[] nums1 = {1, 2, 3, 3}, nums2 = {1, 1, 2, 2};

        MyHelper.p(findDifference(nums1, nums2));
        MyHelper.p(findDifference2(nums1, nums2));
    }

    // 334ms - 45.4mb
    private static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<Integer> answer0 = new ArrayList<>();
        for (int num1 : nums1) {
            if (!answer0.contains(num1)) {
                boolean isDistinct = true;
                for (int num2 : nums2) {
                    if (num1 == num2) {
                        isDistinct = false;
                        break;
                    }
                }
                if (isDistinct)
                    answer0.add(num1);
            }
        }

        List<Integer> answer1 = new ArrayList<>();
        for (int num2 : nums2) {
            if (!answer1.contains(num2)) {
                boolean isDistinct = true;
                for (int num1 : nums1) {
                    if (num1 == num2) {
                        isDistinct = false;
                        break;
                    }
                }
                if (isDistinct)
                    answer1.add(num2);
            }
        }

        List<List<Integer>> answer = new ArrayList<>(2);
        answer.add(answer0);
        answer.add(answer1);

        return answer;
    }


    /**
     * *B YT 14ms - 45.4mb.
     * I used Set instead of Array because Set's are much faster.
     * Sets don't allow duplicates.
     * Maps can be used instead of Sets.
     */
    private static List<List<Integer>> findDifference2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

//        for (int num : nums1)
//            set1.add(num);
        // OR speed and memory a bit worse option
        set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        for (int num : nums2)
            set2.add(num);

        List<List<Integer>> resultList = new ArrayList<>(2);
        resultList.add(new ArrayList<>());
        resultList.add(new ArrayList<>());

        for (int num : set1)
            if (!set2.contains(num))
                resultList.get(0).add(num);

        for (int num : set2)
            if (!set1.contains(num))
                resultList.get(1).add(num);

        return resultList;
    }


    // L has an Expert solution with 1ms
}
