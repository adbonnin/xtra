package fr.adbonnin.xtra.collect;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public final class XtraSet {

    public static <T> HashSet<T> newHashSet(Iterator<T> iterator) {
        final HashSet<T> set = new HashSet<>();
        XtraIterators.addAll(set, iterator);
        return set;
    }

    public static <T> LinkedHashSet<T> newLinkedHashSet(Iterator<T> iterator) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        XtraIterators.addAll(set, iterator);
        return set;
    }

    private XtraSet() { /* Cannot be instantiated */ }
}
