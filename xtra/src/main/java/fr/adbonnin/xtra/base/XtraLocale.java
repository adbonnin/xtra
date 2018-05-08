package fr.adbonnin.xtra.base;

import java.util.Locale;

public final class XtraLocale {

    public static Locale getDefaultFormatLocale() {
        return Locale.getDefault(Locale.Category.FORMAT);
    }

    private XtraLocale() { /* Cannot be instantiated */}
}
