package fr.adbonnin.xtra.groovy.collect

import fr.adbonnin.xtra.collect.XtraCollection

final class XtraCollectionExtension {

    static <T> void reset(Collection<T> collection, Iterator<? extends T> newValues) {
        XtraCollection.reset(collection, newValues)
    }

    static <T> void reset(Collection<T> collection, Iterable<? extends T> newValues) {
        XtraCollection.reset(collection, newValues)
    }

    private XtraCollectionExtension() { /* Cannot be instantiated */ }
}
