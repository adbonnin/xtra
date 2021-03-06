package fr.adbonnin.xtra.collect;

import java.util.ArrayList;
import java.util.Iterator;

public final class XtraList {

    public static <T> ArrayList<T> newArrayList(Iterator<T> iterator) {
        final ArrayList<T> set = new ArrayList<>();
        XtraIterators.addAll(set, iterator);
        return set;
    }

    public static <T> ArrayList<T> newArrayList(Iterable<T> iterable) {
        return newArrayList(iterable.iterator());
    }

    private XtraList() { /* Cannot be instantiated */ }
}
