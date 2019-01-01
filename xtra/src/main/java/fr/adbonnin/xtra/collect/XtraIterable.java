package fr.adbonnin.xtra.collect;

import fr.adbonnin.xtra.base.Function;
import fr.adbonnin.xtra.predicate.Predicate;

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

    public static <T> Iterable<T> filter(final Iterable<T> iterable,
                                         final Predicate<? super T> predicate) {
        requireNonNull(iterable);
        requireNonNull(predicate);
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return XtraIterators.filter(iterable.iterator(), predicate);
            }
        };
    }

    private static <T> Iterator<Iterator<? extends T>> iterators(final Iterable<? extends Iterable<? extends T>> iterables) {
        final Iterator<? extends Iterable<? extends T>> itr = iterables.iterator();

        return new AbstractIterator<Iterator<? extends T>>() {

            @Override
            protected Iterator<? extends T> computeNext() {
                return itr.hasNext() ? itr.next().iterator() : this.endOfData();
            }
        };
    }

    public static <T> Iterable<T> concat(final Iterable<? extends Iterable<? extends T>> iterables) {
        requireNonNull(iterables);

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return XtraIterators.concat(iterators(iterables));
            }
        };
    }

    private XtraIterable() { /* Cannot be instantiated */ }
}
