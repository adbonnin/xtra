package fr.adbonnin.xtra.collect;

import java.util.Collection;
import java.util.Iterator;

public final class XtraCollection {

    public static <T> void reset(Collection<T> toReset, Iterable<T> newValues) {
        reset(toReset, newValues.iterator());
    }

    public static <T> void reset(Collection<T> toReset, Iterator<T> newValues) {
        toReset.clear();
        XtraIterators.addAll(toReset, newValues);
    }

    private XtraCollection() { /* Cannot be instantiated */ }
}
