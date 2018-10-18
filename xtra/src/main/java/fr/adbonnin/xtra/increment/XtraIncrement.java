package fr.adbonnin.xtra.increment;

import fr.adbonnin.xtra.base.Equivalence;

import java.util.*;

public final class XtraIncrement {

    public static <Rev, K, V> MapIncrement<Rev, K, V> newPartialMapIncrement(Rev revision, Map<? extends K, ? extends V> oldValues, Map<? extends K, ? extends V> newValues, Equivalence<? super V> equivalence) {
        final Map<K, V> created = new HashMap<>(newValues);
        final Map<K, V> updated = new HashMap<>();
        final Set<K> removed = new HashSet<>();

        for (Map.Entry<? extends K, ? extends V> entry : oldValues.entrySet()) {
            final K key = entry.getKey();
            final V oldValue = entry.getValue();

            if (created.containsKey(key)) {
                final V newValue = created.remove(key);
                if (!equivalence.equivalent(oldValue, newValue)) {
                    updated.put(key, newValue);
                }
            }
            else {
                removed.add(key);
            }
        }

        return MapIncrement.newPart(revision, created, updated, removed);
    }

    public static <Rev, K, V> MapIncrement<Rev, K, V> concatMapIncrements(Iterator<? extends MapIncrement<? extends Rev, ? extends K, ? extends V>> iterator) {

        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("'iterator' must have at least one increment otherwise revision can't be found.");
        }

        final MapIncrement<? extends Rev, ? extends K, ? extends V> first = iterator.next();

        Rev revision = first.getRevision();
        boolean full = first.isFull();

        Map<K, V> created = new HashMap<>(first.getCreated());
        Map<K, V> updated = new HashMap<>(first.getUpdated());
        Set<K> removed = new HashSet<>(first.getRemoved());

        while (iterator.hasNext()) {
            final MapIncrement<? extends Rev, ? extends K, ? extends V> increment = iterator.next();

            final boolean fullIncrement = increment.isFull();
            final Map<? extends K, ? extends V> createdIncrement = increment.getCreated();
            final Map<? extends K, ? extends V> updatedIncrement = increment.getUpdated();
            final Set<? extends K> removedIncrement = increment.getRemoved();

            revision = increment.getRevision();
            full |= fullIncrement;

            if (fullIncrement) {
                created = new HashMap<>(createdIncrement);
                updated = null;
                removed = null;
            }
            else if (full) {
                for (Map.Entry<? extends K, ? extends V> cr : createdIncrement.entrySet()) {
                    created.put(cr.getKey(), cr.getValue());
                }

                for (Map.Entry<? extends K, ? extends V> up : updatedIncrement.entrySet()) {
                    created.put(up.getKey(), up.getValue());
                }

                for (K rm : removedIncrement) {
                    created.remove(rm);
                }
            }
            else {
                for (Map.Entry<? extends K, ? extends V> cr : createdIncrement.entrySet()) {
                    final K key = cr.getKey();
                    final V value = cr.getValue();

                    created.put(key, value);
                    updated.remove(key);
                    removed.remove(key);
                }

                for (Map.Entry<? extends K, ? extends V> up : updatedIncrement.entrySet()) {
                    final K key = up.getKey();
                    final V value = up.getValue();

                    if (created.containsKey(key)) {
                        created.put(key, value);
                    }
                    else {
                        updated.put(key, value);
                    }

                    removed.remove(key);
                }

                for (K rm : removedIncrement) {
                    created.remove(rm);
                    updated.remove(rm);
                    removed.add(rm);
                }
            }
        }

        if (full) {
            return MapIncrement.newFull(revision, created);
        }
        else {
            return MapIncrement.newPart(revision, created, updated, removed);
        }
    }

    private XtraIncrement() { /* Cannot be instantiated */ }
}
