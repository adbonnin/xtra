package fr.adbonnin.xtra.collect;

import java.util.Collection;
import java.util.Iterator;

import static fr.adbonnin.xtra.base.XtraStrings.EMPTY;

public final class XtraIterators {

    public static String join(Iterator<?> iterator, String separator) {

        if (iterator == null || !iterator.hasNext()) {
            return EMPTY;
        }

        final StringBuilder buf = new StringBuilder();

        if (separator == null) {
            separator = EMPTY;
        }

        boolean first = true;
        do {
            if (first) {
                first = false;
            }
            else {
                buf.append(separator);
            }

            buf.append(iterator.next());
        }
        while (iterator.hasNext());

        return buf.toString();
    }

    public static String join(Iterable<?> iterable, String separator) {
        return iterable == null ? EMPTY : join(iterable.iterator(), separator);
    }

    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator) {

        if (addTo == null || iterator == null) {
            return false;
        }

        boolean modified = false;
        while (iterator.hasNext()) {
            modified |= addTo.add(iterator.next());
        }

        return modified;
    }

    public static <T> boolean addAll(Collection<T> addTo, Iterable<? extends T> iterable) {
        return iterable != null && addAll(addTo, iterable.iterator());
    }

    private XtraIterators() { /* Cannot be instantiated */ }
}
