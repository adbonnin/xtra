package fr.adbonnin.xtra.base;

import java.util.Map;
import java.util.Objects;

public class Pair<K, V> implements Map.Entry<K, V> {

    private final K key;

    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Map.Entry)) {
            return false;
        }

        final Map.Entry<?, ?> other = (Map.Entry<?, ?>) obj;
        return Objects.equals(key, other.getKey()) && Objects.equals(value, other.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
