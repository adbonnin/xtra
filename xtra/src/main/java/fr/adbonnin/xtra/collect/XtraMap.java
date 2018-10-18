package fr.adbonnin.xtra.collect;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class XtraMap {

    public static <K, V> HashMap<K, V> newHashMap(Iterator<Map.Entry<? extends K, ? extends V>> entries) {
        final HashMap<K, V> map = new HashMap<>();
        XtraIterators.putAll(map, entries);
        return map;
    }

    private XtraMap() { /* Cannot be instantiated */ }
}
