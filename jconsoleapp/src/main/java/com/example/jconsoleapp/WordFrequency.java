package com.example.jconsoleapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;

// #L192
public class WordFrequency {

    public static void preWordFrequency() {
        String input = "the day is sunny the the\n" +
                "the   sunny is is";
//                "the   sunny is is";

        MyHelper.p("Original input: " + input + "\n");

        wordFrequency(input);
//        wordFrequency2(input);
    }

    private static void wordFrequency(String input) {
        char[] chars = input.toCharArray();
        Map<String, Integer> words = new HashMap<>();

//        Map<String, Integer> words = new Hashtable<>(); // Doesn't maintain Order (like HashSet)
//        Map<String, Integer> words = new HashMap<>(); // Doesn't maintain Order (like HashSet)
//        Map<String, Integer> words = new LinkedHashMap<>(); // Maintains Adding Order of Keys (like LinkedHashSet)
//        Map<String, Integer> words = new TreeMap<>(); // Maintains Natural Order (sorts Alphabetically or numeric) of Keys (like TreeSet)
        for (int i = 0; i < chars.length; i++) {
            // get word
            String word = "";
            while (i < chars.length && !Character.isWhitespace(chars[i])) {
                MyHelper.pl(Character.valueOf(chars[i]));
                word = String.format("%s%s", word, chars[i]);
//                word = word + chars[i];
                i++;
            }
            // put word in words map
            if (!Objects.equals(word, ""))
                if (words.get(word) == null) {
                    words.put(word, 1);
                } else {
                    words.put(word, words.get(word) + 1);
                }
            MyHelper.p(words.get(word));

//            Collections.synchronizedSortedMap(words);
//            Collections.unmodifiableSortedMap(words);
//            Collections.sort(list);

//            words.get(word); //get value
//            words.entrySet(); // get all keys and values as a Set of Map.Entry
//            words.keySet(); // get all keys as a Set
//            words.values(); // get all values as a Collection

//            words.containsKey(word);
//            words.containsValue(1);

//        Character.isWhitespace();
//        Character.valueOf();

//        Character.isSpaceChar();
//        Character.LINE_SEPARATOR
//        Character.SPACE_SEPARATOR
//        Character.PARAGRAPH_SEPARATOR
//        Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR
// the Unicode (in ASCII) value of the space character is 32, newline character is 10 (creates line breaks)

//                MyHelper.pl(chars[i]);
        }
        
        MyHelper.p(words.entrySet());

        int[] valuesArray = new int[words.size()];
//        for (String w : words.keySet()) {
//        for (int v : words.values()) {
//        for (Iterator<Integer> iterator = words.values().iterator(); iterator.hasNext(); ) {

        // get the Values into an array. sort the array
        for (int i = 0; i < words.size(); i++) {
//            int v = iterator.next();

//            MyHelper.pl(v);
        }

//        words.keySet().toArray();
//        words.values().toArray();

    }

    private static void wordFrequency2(String input) {
        // get words
        /*
         " " only normal spaces. Can be checked with Character.isSpaceChar(char)
         "\\s" considers all space types: spaces, tabs, and newline characters
         "\\s+" matches sequences of one or more of all space types (Whitespace characters). Can be checked with Character.isWhitespace(char)

        // input is no longer referring to the same variable of the passed argument since it has been reassigned by the following line
        // remove line breaks
//        input = input.replace('\n', ' ').replace('\r', ' ');
        // multiple spaces in input wont work
//        String[] words = input.split(" ");
        // or
         */
        String[] words = input.split("\\s+");
        MyHelper.pal(words);

        // count words
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word))
                map.put(word, 1);
            else
                map.put(word, map.get(word) + 1);
        }
        // OR
//        for (String word : words) {
//            if (!map.containsKey(word)) {
//                int count = 0;
//                for (String w : words)
//                    if (word.equals(w))
//                        count++;
//                map.put(word, count);
//            }
//        }
        MyHelper.p(map);

        // sort words
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // print words
        for (Map.Entry<String, Integer> entry: sortedEntries){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // Second solution: use TreeMap because it is sorted instead of HashMap, with <Integer, String>

    // Bash
    /*
    Solution 1
    cat words.txt | tr " " "\n" | tr -s "\n" | sort | uniq -c | sort -r | awk '{print $2" "$1}'
     */
    /*
    Solution 2
    cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{print $2, $1}'
     */
    /*
    Solution 3 - LeetCode
    cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{ print $2, $1 }'
     */
}
