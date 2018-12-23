package fr.adbonnin.xtra.base;

public final class XtraNumber {

    public static String toOrdinal(int value) {
        return value + getOrdinalSuffix(value);
    }

    public static String getOrdinalSuffix(int value) {

        if (value < 0) {
            throw new IllegalArgumentException("Ordinal terms are never used in the negative");
        }

        final int mod100 = value % 100;
        final int mod10 = value % 10;

        if (mod100 - mod10 == 10) {
            return "th";
        }

        switch (mod10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }

    public static String removeOrdinal(String str) {
        return str.replaceAll("(?<=\\d)(st|nd|rd|th)", "");
    }

    public static int asInt(String str, int defaultValue) {

        if (str == null) {
            return defaultValue;
        }

        try {
            return Integer.valueOf(str.trim());
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long asLong(String str, long defaultValue) {

        if (str == null) {
            return defaultValue;
        }

        try {
            return Long.valueOf(str);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static float asFloat(String str, float defaultValue) {

        if (str == null) {
            return defaultValue;
        }

        try {
            return Float.valueOf(str.trim());
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double asDouble(String str, double defaultValue) {

        if (str == null) {
            return defaultValue;
        }

        try {
            return Double.valueOf(str.trim());
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private XtraNumber() { /* Cannot be instantiated */ }
}
