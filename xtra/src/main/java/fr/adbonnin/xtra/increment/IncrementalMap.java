package fr.adbonnin.xtra.increment;

import fr.adbonnin.xtra.base.Equivalence;
import fr.adbonnin.xtra.collect.FixedSizeHashMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class IncrementalMap<Rev, K, V> {

    private final FixedSizeHashMap<Rev, MapIncrement<Rev, K, V>> increments;

    private final Equivalence<V> equivalence;

    private Rev latestRevision;

    private Map<K, V> latestValues;

    public IncrementalMap(Rev initialRevision, Map<? extends K, ? extends V> initialValues, int maxSize, Equivalence<V> equivalence) {
        this.equivalence = requireNonNull(equivalence);

        this.latestRevision = initialRevision;
        this.latestValues = new HashMap<>(initialValues);

        this.increments = newIncrements(maxSize, initialRevision, initialValues);
    }

    private FixedSizeHashMap<Rev, MapIncrement<Rev, K, V>> newIncrements(int maxSize, Rev revision, Map<? extends K, ? extends V> values) {
        final FixedSizeHashMap<Rev, MapIncrement<Rev, K, V>> increments = new FixedSizeHashMap<>(maxSize);
        increments.add(revision, MapIncrement.newFull(revision, values));
        return increments;
    }

    public Rev getRevision() {
        return latestRevision;
    }

    public Map<K, V> getValues() {
        return Collections.unmodifiableMap(latestValues);
    }

    public MapIncrement<Rev, K, V> set(Rev revision, Map<? extends K, ? extends V> values) {
        requireNonNull(values);

        final MapIncrement<Rev, K, V> increment = XtraIncrement.newPartialMapIncrement(revision, latestValues, values, equivalence);
        if (!increments.add(revision, increment)) {
            throw new IllegalArgumentException("revision already exists");
        }

        latestRevision = revision;
        latestValues = new HashMap<>(values);
        return increment;
    }

    public MapIncrement<Rev, K, V> full() {
        return MapIncrement.newFull(latestRevision, latestValues);
    }

    public MapIncrement<Rev, K, V> from(Rev key) {
        final Iterator<MapIncrement<Rev, K, V>> itr = increments.values(key);
        return itr.hasNext() ? XtraIncrement.concatMapIncrements(itr) : full();
    }
}
