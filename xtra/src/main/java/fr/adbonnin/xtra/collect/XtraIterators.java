package fr.adbonnin.xtra.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static fr.adbonnin.xtra.base.XtraStrings.EMPTY;
import static java.util.Collections.emptyIterator;

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

    public static <K, V> void putAll(Map<K, V> putTo, Iterator<Map.Entry<? extends K, ? extends V>> iterator) {

        if (putTo == null || iterator == null) {
            return;
        }

        while (iterator.hasNext()) {
            final Map.Entry<? extends K, ? extends V> next = iterator.next();
            putTo.put(next.getKey(), next.getValue());
        }
    }

    public static <V> Iterator<V> asValuesIterator(final Iterator<? extends Map.Entry<?, ? extends V>> iterator) {

        if (iterator == null) {
            return emptyIterator();
        }

        return new Iterator<V>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public V next() {
                final Map.Entry<?, ? extends V> next = iterator.next();
                return next == null ? null : next.getValue();
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    public static <T> T getNext(Iterator<? extends T> iterator, T defaultValue) {
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }

    private XtraIterators() { /* Cannot be instantiated */ }
}
