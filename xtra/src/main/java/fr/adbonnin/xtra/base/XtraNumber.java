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

    private XtraNumber() { /* Cannot be instantiated */ }
}
