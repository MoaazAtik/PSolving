package com.example.l75ace;

import static com.example.jconsoleapp.MyHelper.p;
import static com.example.jconsoleapp.MyHelper.pal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// #L.17
public class LetterCombinationsOfAPhoneNumber {

    public static void preLetterCombinations() {
        getInput(1);

        p("digits " + digits);
        p("expectedOutput ");
        pal(expectedOutput);
        p();
        p(letterCombinations(digits));
        p(letterCombinations2(digits));
        p(letterCombinations3(digits));
        p(letterCombinations4(digits));
    }

    static String digits;
    static String[] expectedOutput;

    private static void getInput(int i) {
        switch (i) {
            case 1:
                digits = "23";
                expectedOutput = new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
                break;
            case 2:
                digits = "";
                expectedOutput = new String[]{};
                break;
            case 3:
                digits = "2";
                expectedOutput = new String[]{"a", "b", "c"};
                break;
        }
    }


    /**
     * AI Recursive Backtracking approach. 5ms - 41.9mb
     */
    private static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return combinations;

        generateCombinations(combinations, "", digits, 0);
        return combinations;
    }

    // Define the mapping of digits to letters
    private static final String[] DIGIT_LETTERS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private static void generateCombinations(List<String> combinations, String currentCombination, String digits, int indexOfDigits) {
        // Base case: If we've processed all digits, add the current combination to the list
        if (indexOfDigits == digits.length()) {
            combinations.add(currentCombination);
            return;
        }

        // Get the letters corresponding to the current digit
        /*
        When 'digits' is "23", and indexOfDigits is 0, digits.charAt(indexOfDigits) gives the char '2' which is 50 as int Unicode value.
        And DIGIT_LETTERS[digits.charAt(indexOfDigits)] means DIGIT_LETTERS[50], and 50 is out of bounds for DIGIT_LETTERS.
        By adding  - '0' we get '2' - '0' -> 50 - 48 which results in the integer value 2.
         */
        String letters = DIGIT_LETTERS[digits.charAt(indexOfDigits) - '0'];

        // Iterate through each letter and recursively generate combinations
        for (char letter : letters.toCharArray())
            generateCombinations(combinations, currentCombination + letter, digits, indexOfDigits + 1);
    }


    /**
     * AI Iterative approach. 5ms - 42.3mb. Queue, Linked List
     */
    private static List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.isEmpty())
            return combinations;

        Queue<String> queue = new LinkedList<>();
        queue.offer(""); // Add an empty string to start with

        // Iterate through each digit in the input string
        for (char digit : digits.toCharArray()) {

            int size = queue.size();
            // Process each combination in the queue for the current digit
            for (int i = 0; i < size; i++) {

                String combination = queue.poll();
                // Append each letter corresponding to the current digit
                String letters = DIGIT_LETTERS2[digit - '0'];
                for (char letter : letters.toCharArray()) {
                    queue.offer(combination + letter);
                }
            }
        }

        // All combinations are stored in the queue
        combinations.addAll(queue);
        return combinations;
    }

    private static final String[] DIGIT_LETTERS2 = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    /**
     * *B L Recursive Backtracking. 0ms - 41.8mb. StringBuilder
     * modified L, and modified {@link #letterCombinations(String)}
     */
    private static List<String> letterCombinations3(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return combinations;

        generateCombinations2(combinations, new StringBuilder(), digits, 0);

        return combinations;
    }

    private static final String[] DIGIT_LETTERS3 = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private static void generateCombinations2(List<String> combinations, StringBuilder stringBuilder, String digits, int indexOfDigits) {
        if (indexOfDigits == digits.length()) {
            combinations.add(stringBuilder.toString());
            return;
        }

        for (char letter : DIGIT_LETTERS3[digits.charAt(indexOfDigits) - '0'].toCharArray()) {
            stringBuilder.append(letter);
            generateCombinations2(combinations, stringBuilder, digits, indexOfDigits + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        ;
    }


    /**
     * L Recursive Backtracking. 0ms - 42mb. StringBuilder, Map.
     * It's a Hash Table (Map) question.
     * modified {@link #letterCombinations3(String)}
     */
    private static List<String> letterCombinations4(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return combinations;

        digitLettersMap.put('2', "abc");
        digitLettersMap.put('3', "def");
        digitLettersMap.put('4', "ghi");
        digitLettersMap.put('5', "jkl");
        digitLettersMap.put('6', "mno");
        digitLettersMap.put('7', "pqrs");
        digitLettersMap.put('8', "tuv");
        digitLettersMap.put('9', "wxyz");

        generateCombinations3(combinations, new StringBuilder(), digits, 0);

        return combinations;
    }

    private static Map<Character, String> digitLettersMap = new HashMap<>();

    private static void generateCombinations3(List<String> combinations, StringBuilder stringBuilder, String digits, int indexOfDigits) {
        if (indexOfDigits == digits.length()) {
            combinations.add(stringBuilder.toString());
            return;
        }

        for (char letter : digitLettersMap.get(digits.charAt(indexOfDigits)).toCharArray()) {
            stringBuilder.append(letter);
            generateCombinations3(combinations, stringBuilder, digits, indexOfDigits + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
