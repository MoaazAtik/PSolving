package com.example.jconsoleapp.ti150mustdo;

import com.example.jconsoleapp.MyHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

// #L192
public class WordFrequency {

    public static void preWordFrequency() {
//        String input = "the day is sunny the the\nthe sunny is is";

        String input = "the day is sunny is is the the\nthe   sunny is is";
//        String input = "the day is sunny is is the the\nthe   sunny is ";

        MyHelper.p("Original input: \n" + input + "\n");

//        wordFrequency(input);
//        wordFrequency2(input);
        wordFrequencyC(input);
    }

    /**
     * {@code input.toCharArray()}, {@code StringBuilder.append}, {@code TreeSet}, {@code Comparator}, {@code Character.isWhitespace}, {@code map.merge(word, 1, Integer::sum);}, {@code Map.Entry<String, Integer>}, {@code .compareTo}
     */
    private static void wordFrequency(String input) {
        // turn string input into char array
        char[] chars = input.toCharArray();

        // split words from char array, and fill them into a HashMap to count them
        Map<String, Integer> words = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            // get word
            StringBuilder wordBuilder = new StringBuilder();
            while (i < chars.length && !Character.isWhitespace(chars[i])) {
//                MyHelper.pl(Character.valueOf(chars[i]));
                wordBuilder.append(chars[i]);
                i++;
            }
            String word = wordBuilder.toString();
            // put word in words map
            if (!Objects.equals(word, ""))
                words.merge(word, 1, Integer::sum);
            // OR
//                if (words.get(word) == null)
//                    words.put(word, 1);
//                else
//                    words.put(word, words.get(word) + 1);

//            MyHelper.p(words.get(word));
        }
//        MyHelper.p(words.entrySet());


        // sort words (with a List or TreeSet)
        TreeSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
//                int res = e1.getValue().compareTo(e2.getValue()); // Ascending order
                int res = e2.getValue().compareTo(e1.getValue()); // Descending order
                return res != 0 ? res : 1;
                /*
                The returned value (res) from Integer.compare(), x.compareTo() (from Integer class) or compare() (from Comparator interface:
                if e2 > e1 res is positive, if e2 = e1 res is 0, if e2 > e1 res is negative.

                If the values are equal aka, res is 0, I ensured a stable ordering by returning 1.
                Otherwise, if I returned 0 when the values are equals (return res != 0 ? res : 0   i.e.,  return res), only one of them will be added to the map, because the other would be considered as Duplicates.
                If values are equal, another way to handle them is to compare *Keys, but it is not what I actually want in this map.
//                return res == 0 ? e2.getKey().compareTo(e1.getKey()) : res;
                 */
            }
        });
        sortedEntries.addAll(words.entrySet());

        // OR
//        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(words.entrySet());
//        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));  // Descending order
//        sortedEntries.sort((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));  // Ascending order


        // print words
        for (Map.Entry<String, Integer> entry : sortedEntries)
            System.out.println(entry.getKey() + " " + entry.getValue());
    }


    /**
     * {@link String#split(String)}, {@link HashMap}, {@link HashMap#containsKey(Object)}, {@link List#sort(Comparator)}, {@link Comparator}, {@link Map.Entry}, {@link ArrayList#stream()} {@code list.stream().map(entry -> ...).forEach(System.out::println);}
     */
    private static void wordFrequency2(String input) {
        // get words
        /*
         " " only normal spaces. Can be checked with Character.isSpaceChar(char)
         "\\s" considers all space types: spaces, tabs, and newline characters
         "\\s+" matches sequences of one or more of all space types (Whitespace characters). Can be checked with Character.isWhitespace(char)

        // input is no longer referring to the same variable of the passed argument since it has been reassigned by the following line
        // remove line breaks
//        input = input.replace('\n', ' ').replace('\r', ' ');
        // multiple spaces in input wont work if " " or "\\s" is used instead of "\\s+"
//        String[] words = input.split(" ");
        // or
         */
        String[] words = input.split("\\s+");
//        MyHelper.pal(words);

        // count words
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (!map.containsKey(word))
                map.put(word, 1);
            else
                map.put(word, map.get(word) + 1);

            // OR
//            map.merge(word, 1, Integer::sum);
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

//        MyHelper.p(map);

        // sort words
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // print words
        for (Map.Entry<String, Integer> entry : sortedEntries)
            System.out.println(entry.getKey() + " " + entry.getValue());
        // OR
//        sortedEntries.stream().map(entry -> entry.getKey() + " " + entry.getValue()).forEach(System.out::println);
    }

    /**
     * A better Combo of {@link WordFrequency#wordFrequency(String)} and {@link WordFrequency#wordFrequency2(String)}
     */
    private static void wordFrequencyC(String input) {
        // get words
        String[] words = input.split("\\s+");

        // count words
        Map<String, Integer> wordsCounted = new HashMap<>();
        for (String word : words)
            wordsCounted.merge(word, 1, Integer::sum);

        // sort words
        List<Map.Entry<String, Integer>> wordsSorted = new ArrayList<>(wordsCounted.entrySet());
        wordsSorted.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // print words
        for (Map.Entry<String, Integer> word : wordsSorted)
            System.out.println(word.getKey() + " " + word.getValue());
    }


    // Bash (Not tested)
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
