package fr.adbonnin.xtra.groovy.base

import fr.adbonnin.xtra.base.XtraDates
import fr.adbonnin.xtra.base.XtraStrings

final class XtraStringsExtension {

    static boolean isEmpty(CharSequence str) {
        return XtraStrings.isEmpty(str)
    }

    static Date toDate(String str, String pattern, Locale locale) {
        return XtraDates.newDate(str, pattern, locale)
    }

    static Date toDate(String str, String pattern) {
        return XtraDates.newDate(str, pattern)
    }

    static String removeEnd(String str, String search, boolean ignoreCase) {
        return XtraStrings.removeEnd(str, search, ignoreCase)
    }

    static String replaceEnd(String str, String search, String replacement, boolean ignoreCase) {
        return XtraStrings.replaceEnd(str, search, replacement, ignoreCase)
    }

    static String removeStart(String str, String search, boolean ignoreCase) {
        return XtraStrings.removeStart(str, search, ignoreCase)
    }

    static String replaceStart(String str, String search, String replacement, boolean ignoreCase) {
        return XtraStrings.replaceStart(str, search, replacement, ignoreCase)
    }

    static String removeBefore(String str, String search, boolean include = false) {
        return XtraStrings.removeBefore(str, search, include)
    }

    static String removeAfter(String str, String search, boolean include = false) {
        return XtraStrings.removeAfter(str, search, include )
    }

    static boolean containsIgnoreCase(String str, String search) {
        return XtraStrings.containsIgnoreCase(str, search)
    }

    static boolean conains(String str, String search, boolean ignoreCase) {
        return ignoreCase ? containsIgnoreCase(str, search) : str.contains(search)
    }

    private XtraStringsExtension() { /* Cannot be instantiated */ }
}
