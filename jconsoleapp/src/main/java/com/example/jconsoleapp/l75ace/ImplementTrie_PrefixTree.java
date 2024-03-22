package com.example.jconsoleapp.l75ace;

import static com.example.jconsoleapp.MyHelper.p;

import java.util.HashMap;
import java.util.Set;

// #L.208
public class ImplementTrie_PrefixTree {

    public static void preImplementTrie() {
        Trie obj = new Trie();
        String word = "helium";
        String prefix = "he";
        obj.insert(word);
        printTrieNodesDfsWNulls(obj.root);
        p();
        obj.insert("word");
        printTrieNodesDfsWNulls(obj.root);
        p();
        obj.insert("hello");
        printTrieNodesDfsWNulls(obj.root);
        p();
        obj.insert("hey");
        printTrieNodesDfsWNulls(obj.root);
        p();

        boolean param_2 = obj.search(word);
        boolean param_3 = obj.startsWith(prefix);

        p(param_2);
        p(param_3);
        p();
        p(obj.search("hw"));
        p(obj.search(prefix));
        p(obj.startsWith(prefix));
        p(obj.startsWith("h"));
        p(obj.startsWith("z"));
    }


    /**
     * 47ms - 55.5mb. Trie with HashMap.
     * It can be implemented with an Array or List instead of HashMap.
     * At Leetcode there is a more complex implementation using Array with Runtime 28ms.
     */
    static class Trie {

        static class Node {
            HashMap<Character, Node> children;
            boolean isEndOfWord;

            Node() {
                this.children = new HashMap<>();
            }
        }


        public Trie() {
            root = new Node();
        }

        Node root;

        public void insert(String word) {
            Node currentRoot = root;

            for (char character : word.toCharArray()) {
                if (!currentRoot.children.containsKey(character))
                    currentRoot.children.put(character, new Node());

                currentRoot = currentRoot.children.get(character);
            }
            currentRoot.isEndOfWord = true;
        }

        public boolean search(String word) {
            Node currentRoot = root;

            for (char character : word.toCharArray()) {
                currentRoot = currentRoot.children.get(character);

                if (currentRoot == null)
                    return false;
            }
            return currentRoot.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            Node currentRoot = root;

            for (char newChar : prefix.toCharArray()) {
                currentRoot = currentRoot.children.get(newChar);

                if (currentRoot == null)
                    return false;
            }
            return true;
        }
    }


    // Recursive DFS print Trie with HashMap With null nodes
    public static void printTrieNodesDfsWNulls(Trie.Node root) {
        Set<Character> characters = root.children.keySet();
        for (Character c : characters) {
            System.out.print(c + " ");
            printTrieNodesDfsWNulls(root.children.get(c));
        }
        System.out.print(".");
    }

}
