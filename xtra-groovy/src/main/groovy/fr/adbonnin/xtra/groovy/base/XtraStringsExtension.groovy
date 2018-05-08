package fr.adbonnin.xtra.groovy.base

import fr.adbonnin.xtra.base.XtraStrings

final class XtraStringsExtension {

    static boolean isEmpty(CharSequence str) {
        return XtraStrings.isEmpty(str)
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

    static String removeBefore(String str, String search, boolean include) {
        return XtraStrings.removeBefore(str, search, include)
    }

    static String removeAfter(String str, String search) {
        return XtraStrings.removeAfter(str, search)
    }

    private XtraStringsExtension() { /* Cannot be instantiated */ }
}
