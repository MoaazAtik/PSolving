package com.example.jconsoleapp;

import java.util.Arrays;

// #L125
public class ValidPalindrome {

    public static boolean preIsPalindrome() {
        String s = "A man, a plan, a canal: Panama";
//        String s = "race a car";
//        String s = " ";

//        char[] a = {'0', '9', 'A', 'Z', 'a', 'z'}; // 48  57  65  90  97  122

        MyHelper.p(s);
//        isPalindrome(s);
//        MyHelper.p(s);
//        return true;

//        return isPalindrome(s);
//        return isPalindrome2(s);
//        return isPalindrome2m(s);
        return isPalindrome3(s);
    }

    /**
     * *M Runtime: 15ms - Memory: 44.85mb
     * {@code [^a-zA-Z0-9]} is a <b>Regular Expression</b>.
     */
    static public boolean isPalindrome(String s) {
        // Remove non-alphanumeric characters and Convert all uppercase letters into lowercase letters.
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
//        MyHelper.p("s " + s);

        // Compare characters
        char[] chars = s.toCharArray();
//        MyHelper.p("chars " + Arrays.toString(chars));
//        MyHelper.pal(chars);
        for (int f = 0, l = chars.length - 1; f < l; f++, l--) {
            if (chars[f] != chars[l])
                return false;
        }

        return true;
    }

    /**
     * L - Runtime: 1ms.
     */
    public static boolean isPalindrome2(String s) {
        char[] arr = s.toCharArray();
        int l = 0, r = arr.length - 1;

        while (l < r) {
            if (arr[l] >= 'A' && arr[l] <= 'Z')
                arr[l] += 32;
            if (arr[r] >= 'A' && arr[r] <= 'Z')            // To lower case
                arr[r] += 32;

            if (!((arr[l] >= '0' && arr[l] <= '9') || (arr[l] >= 'a' && arr[l] <= 'z')))    // Not letter, not number -> skip
                l++;
            else if (!((arr[r] >= '0' && arr[r] <= '9') || (arr[r] >= 'a' && arr[r] <= 'z')))
                r--;
            else if (arr[l] != arr[r])
                return false;                // Is letter or number but isn't equal -> not palindrome
            else {
                l++;
                r--;
            }
        }
        return true;
    }

    /**
     * Modified {@link #isPalindrome2(String)} - Runtime: 3ms
     */
    public static boolean isPalindrome2m(String s) {
        s = s.toLowerCase();
        int l = 0, r = s.length() - 1;
//        MyHelper.p(r);

        while (l < r) {
            if (!((s.charAt(l) >= '0' && s.charAt(l) <= '9') || (s.charAt(l) >= 'a' && s.charAt(l) <= 'z')))    // Not letter, not number -> skip
                l++;
            else if (!((s.charAt(r) >= '0' && s.charAt(r) <= '9') || (s.charAt(r) >= 'a' && s.charAt(r) <= 'z')))
                r--;
            else if (s.charAt(l) != s.charAt(r))
                return false;                // Is letter or number but isn't equal -> not palindrome
            else {
                l++;
                r--;
            }
        }
        return true;
    }

    /**
     * L - similar to {@link #isPalindrome2(String)} - Memory: 40.8mb
     */
    public static boolean isPalindrome3(String s) {
        int start = 0, end = s.length() - 1;
        s = s.toLowerCase();
        while (start <= end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
                continue;
            }
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

}
