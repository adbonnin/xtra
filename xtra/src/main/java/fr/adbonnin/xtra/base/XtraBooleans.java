package fr.adbonnin.xtra.base;

public final class XtraBooleans {

    /** Return {@code true} if {@code bool} is not {@code null} and {@code true} */
    public static boolean isTrue(Boolean bool) {
        return bool != null && bool;
    }

    /** Return {@code false} if {@code bool} is not {@code null} and {@code false} */
    public static boolean isFalse(Boolean bool) {
        return bool != null && !bool;
    }

    private XtraBooleans() { /* Cannot be instantiated */ }
}
