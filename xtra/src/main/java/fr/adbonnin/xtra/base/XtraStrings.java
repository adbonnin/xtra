package fr.adbonnin.xtra.base;

public final class XtraStrings {

    public static final String EMPTY = "";

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String removeEnd(String str, String search, boolean ignoreCase) {
        return replaceEnd(str, search, null, ignoreCase);
    }

    public static String replaceEnd(String str, String search, String replacement, boolean ignoreCase) {

        if (isEmpty(str) || isEmpty(search)) {
            return str;
        }

        final int removeIndex = str.length() - search.length();
        if (removeIndex < 0) {
            return str;
        }

        final boolean endsWith;
        if (ignoreCase) {
            endsWith = str.substring(removeIndex).equalsIgnoreCase(search);
        }
        else {
            endsWith = str.startsWith(search, removeIndex);
        }

        if (!endsWith) {
            return str;
        }

        final String sub = str.substring(0, removeIndex);
        return isEmpty(replacement) ? sub : sub + replacement;
    }

    public static String removeStart(String str, String search, boolean ignoreCase) {
        return replaceStart(str, search, null, ignoreCase);
    }

    public static String replaceStart(String str, String search, String replacement, boolean ignoreCase) {

        if (isEmpty(str) || isEmpty(search)) {
            return str;
        }

        final int removeLength = search.length();
        if (str.length() < removeLength) {
            return str;
        }

        final boolean startsWith;
        if (ignoreCase) {
            startsWith = str.substring(0, removeLength).equalsIgnoreCase(search);
        }
        else {
            startsWith = str.startsWith(search);
        }

        if (!startsWith) {
            return str;
        }

        final String sub = str.substring(removeLength);
        return isEmpty(replacement) ? sub : replacement + sub;
    }

    public static String removeBefore(String str, String search, boolean include) {

        if (isEmpty(str) || isEmpty(search)) {
            return str;
        }

        final int start = str.indexOf(search);
        if (start == -1) {
            return str;
        }

        return str.substring(start + (include ? search.length() : 0));
    }

    public static String removeAfter(String str, String search) {

        if (isEmpty(str) || isEmpty(search)) {
            return str;
        }

        final int end = str.indexOf(search);
        if (end == -1) {
            return str;
        }

        return str.substring(0, end);
    }

    private XtraStrings() { /* Cannot be instantiated */ }
}
