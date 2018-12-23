package fr.adbonnin.xtra.groovy.collect

import fr.adbonnin.xtra.base.Function
import fr.adbonnin.xtra.collect.XtraIterators
import fr.adbonnin.xtra.predicate.Predicate

final class XtraIteratorsExtension {

    static String join(Iterator<?> iterator, String separator) {
        return XtraIterators.join(iterator, separator)
    }

    static String join(Iterable<?> iterable, String separator) {
        return XtraIterators.join(iterable, separator)
    }

    static <T> String next(Iterator<? extends T> iterator, T defaultValue) {
        return XtraIterators.next(iterator, defaultValue)
    }

    static <F, T> Iterator<T> transform(final Iterator<F> iterator,
                                         final Function<? super F, ? extends T> function) {
        return XtraIterators.transform(iterator, function)
    }

    static <T> Iterator<T> filter(final Iterator<? extends T> iterator, final Predicate<? super T> predicate) {
        return XtraIterators.filter(iterator, predicate)
    }

    private XtraIteratorsExtension() { /* Cannot be instantiated */ }
}
