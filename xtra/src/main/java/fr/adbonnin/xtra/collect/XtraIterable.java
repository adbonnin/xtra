package fr.adbonnin.xtra.collect;

import fr.adbonnin.xtra.base.Function;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public final class XtraIterable {

    public static <F, T> Iterable<T> transform(final Iterable<F> iterable,
                                               final Function<? super F, ? extends T> function) {
        requireNonNull(iterable);
        requireNonNull(function);
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return XtraIterators.transform(iterable.iterator(), function);
            }
        };
    }

    private XtraIterable() { /* Cannot be instantiated */ }
}
