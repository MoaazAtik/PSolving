package com.example.jconsoleapp.others;

import static com.example.jconsoleapp.MyHelper.p;

// #LongestWord @ Coderbyte
public class LongestWord {

    public static void preLongestWord() {
        String s = "fun&!! time";

//        p(longestWord(s));
        p(longestWord2(s));
    }

    // #LongestWord @ Coderbyte
    public static String longestWord(String sen) {

        String lowerSen = sen.toLowerCase();
        StringBuilder temp = new StringBuilder();
        String result = "";
        int max = 0;
        int count = 0;

        for (int i = 0; i < sen.length(); i++) {
            char c = lowerSen.charAt(i);

            // fun&!! time
            if (isValid(c)) {
                count++;
                temp.append(c);

                if (i == sen.length() - 1) {
                    if (count > max) {
                        result = temp.toString();
                        max = count;
                    }
                }
            }

            else if (c == ' ') {
                if (count > max) {
                    result = temp.toString();
                    max = count;
                }

                temp.delete(0, temp.length());
                count = 0;
            }
        }

        return result;
    }

    // #LongestWord @ Coderbyte
    public static String longestWord2(String sen) {
        String[] words = sen.split(" ");

        int max = 0;
        String result = "";
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            String lower = word.toLowerCase();
            for (int i = 0; i < word.length(); i++) {
                if (isValid(lower.charAt(i))) {
                    builder.append(word.charAt(i));
                }
            }

            if (builder.length() > max) {
                result = builder.toString();
                max = builder.length();
            }

            builder = new StringBuilder();
        }

        return result;
    }

    // #LongestWord @ Coderbyte
    private static boolean isValid(char c) {
        return "abcdefghijklmnopqrstuvwxyz0123456789".indexOf(c) != -1;
    }
}
