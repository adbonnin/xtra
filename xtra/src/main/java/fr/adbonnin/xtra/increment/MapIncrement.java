package fr.adbonnin.xtra.increment;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

public class MapIncrement<Rev, K, V> {

    private final Rev revision;

    private final boolean full;

    private final Map<K, V> created;

    private final Map<K, V> updated;

    private final Set<K> removed;

    public static <Rev, K, V> MapIncrement<Rev, K, V> newFull(Rev revision, Map<? extends K, ? extends V> created) {
        return new MapIncrement<>(revision, true, created, Collections.<K, V>emptyMap(), Collections.<K>emptySet());
    }

    public static <Rev, K, V> MapIncrement<Rev, K, V> newPart(Rev revision, Map<? extends K, ? extends V> created, Map<? extends K, ? extends V> updated, Set<K> removed) {
        return new MapIncrement<>(revision, false, created, updated, removed);
    }

    private MapIncrement(Rev revision, boolean full, Map<? extends K, ? extends V> created, Map<? extends K, ? extends V> updated, Set<K> removed) {
        this.revision = requireNonNull(revision);
        this.full = full;
        this.created = unmodifiableMap(created);
        this.updated = unmodifiableMap(updated);
        this.removed = unmodifiableSet(removed);
    }

    public Rev getRevision() {
        return revision;
    }

    public boolean isFull() {
        return full;
    }

    public Map<K, V> getCreated() {
        return created;
    }

    public Map<K, V> getUpdated() {
        return updated;
    }

    public Set<K> getRemoved() {
        return removed;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(revision);
        result = 31 * result + (full ? 1 : 0);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        result = 31 * result + Objects.hashCode(removed);
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof MapIncrement)) {
            return false;
        }

        final MapIncrement other = (MapIncrement) obj;
        return full == other.isFull() &&
            Objects.equals(revision, other.getRevision()) &&
            Objects.equals(created, other.getCreated()) &&
            Objects.equals(updated, other.getUpdated()) &&
            Objects.equals(removed, other.getRemoved());
    }

    @Override
    public String toString() {
        return revision + " => full: " + full + ", created: " + created + ", updated: " + updated + ", removed: " + removed;
    }
}
