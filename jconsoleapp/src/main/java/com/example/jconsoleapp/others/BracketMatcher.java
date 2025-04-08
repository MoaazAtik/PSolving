package com.example.jconsoleapp.others;

import java.util.Stack;

import static com.example.jconsoleapp.MyHelper.p;

// #BracketMatcher @ Coderbyte
public class BracketMatcher {

    public static void preBracketMatcher() {
//        String str = "d())";
        String str = "(d())";
//        String str = "(d(())";
//        String str = ")(d())";

        p(bracketMatcher(str));
    }

    public static String bracketMatcher(String str) {
        int b = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') // right-facing brackets
                b++;
            else if (c == ')')
                b--;

            if (b == -1) // or < 0
                return "0";
        }

        return b == 0 ? "1" : "0";
    }

    public static String bracketMatcher2(String str) {
        Stack<Integer> stack = new Stack<>();

        for (char c : str.toCharArray()) {

            if (c == '(')
                stack.push(0);

            else if (c == ')')
                if (!stack.isEmpty())
                    stack.pop();
                else return "0";
        }

        return stack.isEmpty() ? "1" : "0";
    }

}
