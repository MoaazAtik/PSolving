package com.example.jconsoleapp.l75ace;

import com.example.jconsoleapp.MyHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//#L.2390
public class RemovingStarsFromAString {

    public static void preRemoveStars() {
        String s = "leet**cod*e";
//        String s = "erase*****";

//        String s = "*leet**cod*e";
//        String s = "l**cod*e";

        MyHelper.p("s = " + s);
//        MyHelper.p(removeStars(s));
//        MyHelper.p(removeStars2(s));
        MyHelper.p(removeStars2e(s));
        MyHelper.p(removeStars3(s));
    }

    // Time Limit Exceeded - 44 / 65 testcases passed
    private static String removeStars(String s) {
        char[] chars = s.toCharArray();

        List<Character> list = new ArrayList<>(chars.length);
        for (char c : chars) {
            list.add(c);
        }

        int i = 0;
        while (i < list.size()) {
            if (list.get(i) == '*') {
                list.remove(i);
                if (i > 0) {
                    list.remove(i - 1);
                    i--;
                }
                continue;
            }
            i++;
        }

        StringBuilder builder = new StringBuilder(list.size());
        for (char c : list)
            builder.append(c);

        return builder.toString();
    }

    /**
     * 94ms - 46.1mb.
     * Stack
     */
    private static String removeStars2(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                stack.push(s.charAt(i));
            } else if (!stack.isEmpty()) { // && s.charAt(i) == '*'
                stack.pop();
            }
        }

        while (!stack.isEmpty())
            stringBuilder.append(stack.pop());

        return stringBuilder.reverse().toString();
    }

    /**
     * *B2 (2nd best) YT 69ms - 46.2mb.
     * * no need to check if the stack is empty because of the problem's rule "The input will be generated such that the operation is always possible."
     */
    private static String removeStars2e(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                stack.push(s.charAt(i));
            } else { // *
                stack.pop();
            }
        }

        for (Character c : stack)
            stringBuilder.append(c);

        return stringBuilder.toString();
    }

    /**
     * *B - although it's a Stack question - 38ms - 45.3mb
     * no Stack
     * * no need to check if the stack is empty because of the problem's rule "The input will be generated such that the operation is always possible."
     */
    private static String removeStars3(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                stringBuilder.append(s.charAt(i));
            } else { // *
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }

        return stringBuilder.toString();
    }
}
