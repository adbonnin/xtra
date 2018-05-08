package fr.adbonnin.xtra.groovy.collect

import fr.adbonnin.xtra.collect.XtraIterators

final class XtraIteratorsExtension {

    static String join(Iterator<?> iterator, String separator) {
        return XtraIterators.join(iterator, separator)
    }

    static String join(Iterable<?> iterable, String separator) {
        return XtraIterators.join(iterable, separator)
    }

    private XtraIteratorsExtension() { /* Cannot be instantiated */ }
}
