package fr.adbonnin.xtra.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class XtraObjects {

    public static final String NULL_DEFAULT = "null";

    /**
     * Create a copy of {@code array} or throw a {@code java.lang.NullPointerException} if an element is {@code null}.
     */
    @SafeVarargs
    public static <T> List<T> requireNonNullCopy(T... array) {
        return requireNonNullCopy(Arrays.asList(array));
    }

    /**
     * Create a copy of {@code iterable} or throw a {@code java.lang.NullPointerException} if an element is {@code null}.
     */
    public static <T> List<T> requireNonNullCopy(Iterable<T> iterable) {
        final ArrayList<T> list = new ArrayList<>();
        for (T element : iterable) {
            list.add(Objects.requireNonNull(element));
        }
        return list;
    }

    public static void require(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void require(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static String requireNotEmpty(String str) {
        require(!XtraStrings.isEmpty(str));
        return str;
    }

    private XtraObjects() { /* Cannot be instantiated */ }
}
