package fr.adbonnin.xtra.base;

public final class XtraStrings {

    public static final String EMPTY = "";

    public static final int INDEX_NOT_FOUND = -1;

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static String removeEnd(String str, String search, boolean ignoreCase) {
        return replaceEnd(str, search, null, ignoreCase);
    }

    public static String replaceEnd(String str, String search, String replacement, boolean ignoreCase) {

        final int removeIndex = lastIndexOf(str, search, ignoreCase);
        if (removeIndex == INDEX_NOT_FOUND) {
            return str;
        }

        final String sub = str.substring(0, removeIndex);
        return isEmpty(replacement) ? sub : sub + replacement;
    }

    public static int lastIndexOf(String str, String search, boolean ignoreCase) {

        if (isEmpty(str) || isEmpty(search)) {
            return INDEX_NOT_FOUND;
        }

        final int removeIndex = str.length() - search.length();
        if (removeIndex < 0) {
            return INDEX_NOT_FOUND;
        }

        final boolean endsWith;
        if (ignoreCase) {
            endsWith = str.substring(removeIndex).equalsIgnoreCase(search);
        }
        else {
            endsWith = str.startsWith(search, removeIndex);
        }

        return endsWith ? removeIndex : INDEX_NOT_FOUND;
    }

    public static String removeStart(String str, String search, boolean ignoreCase) {
        return replaceStart(str, search, null, ignoreCase);
    }

    public static String replaceStart(String str, String search, String replacement, boolean ignoreCase) {

        final int removeLength = indexOf(str, search, ignoreCase);
        if (removeLength == INDEX_NOT_FOUND) {
            return str;
        }

        final String sub = str.substring(removeLength);
        return isEmpty(replacement) ? sub : replacement + sub;
    }

    public static int indexOf(String str, String search, boolean ignoreCase) {

        if (isEmpty(str) || isEmpty(search)) {
            return INDEX_NOT_FOUND;
        }

        final int removeLength = search.length();
        if (str.length() < removeLength) {
            return INDEX_NOT_FOUND;
        }

        final boolean startsWith;
        if (ignoreCase) {
            startsWith = str.substring(0, removeLength).equalsIgnoreCase(search);
        }
        else {
            startsWith = str.startsWith(search);
        }

        return startsWith ? removeLength : INDEX_NOT_FOUND;
    }

    public static String removeBefore(String str, String search, boolean include) {

        final int index = indexOf(str, search);
        if (index == INDEX_NOT_FOUND) {
            return str;
        }

        return str.substring(index + (include ? search.length() : 0));
    }

    public static String removeAfter(String str, String search, boolean include) {

        final int index = indexOf(str, search);
        if (index == INDEX_NOT_FOUND) {
            return str;
        }

        return str.substring(0, index + (include ? 0 : search.length()));
    }

    public static int indexOf(String str, String search) {
        return str == null || search == null ? INDEX_NOT_FOUND : str.indexOf(search);
    }

    public static boolean containsIgnoreCase(String str, String search) {

        if (str == null || search == null) {
            return false;
        }

        final int len = search.length();
        final int max = str.length() - len;

        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, search, 0, len)) {
                return true;
            }
        }

        return false;
    }

    public  static boolean equals(CharSequence cs1, CharSequence cs2) {

        if (cs1 == cs2) {
            return true;
        }

        if (cs1 == null || cs2 == null) {
            return false;
        }

        int length = cs1.length();
        if (length != cs2.length()) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private XtraStrings() { /* Cannot be instantiated */ }
}
