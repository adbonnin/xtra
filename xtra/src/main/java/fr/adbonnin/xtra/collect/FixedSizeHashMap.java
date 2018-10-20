package fr.adbonnin.xtra.collect;

import java.util.*;

import static fr.adbonnin.xtra.base.XtraObjects.require;
import static fr.adbonnin.xtra.collect.XtraIterators.asValuesIterator;

public class FixedSizeHashMap<K, V> {

    private final Map<K, LinkedEntry<K, V>> values = new HashMap<>();

    private final int maxSize;

    private LinkedEntry<K, V> head;
    private LinkedEntry<K, V> tail;

    public FixedSizeHashMap(int maxSize) {
        require(maxSize >= 0, "max size must be positive");
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int size() {
        return values.size();
    }

    public boolean containsKey(K key) {
        return values.containsKey(key);
    }

    public boolean add(K key, V value) {

        if (values.containsKey(key)) {
            return false;
        }

        final LinkedEntry<K, V> oldHead = head;

        final LinkedEntry<K, V> newHead = new LinkedEntry<>(key, value);
        values.put(key, newHead);

        if (oldHead != null) {
            oldHead.setNext(newHead);
        }

        if (tail == null) {
            tail = newHead;
        }

        if (values.size() > maxSize) {
            final LinkedEntry<K, V> oldTail = tail;

            tail = oldTail.getNext();
            values.remove(oldTail.getKey());
        }

        head = newHead;
        return true;
    }

    public Iterator<Map.Entry<K, V>> entries() {
        final LinkedEntry<K, V> currentTail = tail;
        return currentTail == null ? Collections.<Map.Entry<K, V>>emptyIterator() : entries(currentTail.getKey());
    }

    public Iterator<V> values(K from) {
        return asValuesIterator(entries(from));
    }

    public Iterator<V> values() {
        return asValuesIterator(entries());
    }

    public Iterator<Map.Entry<K, V>> entries(K fromInclusive) {
        final LinkedEntry<K, V> first = values.get(fromInclusive);
        return new Iterator<Map.Entry<K, V>>() {

            private LinkedEntry<K, V> next = first;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Map.Entry<K, V> next() {
                final LinkedEntry<K, V> current = next;
                next = current.getNext();
                return current;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static class LinkedEntry<K, V> implements Map.Entry<K, V> {

        private final K key;

        private final V value;

        private LinkedEntry<K, V> next;

        LinkedEntry(K key, V value) {
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

        public LinkedEntry<K, V> getNext() {
            return next;
        }

        public void setNext(LinkedEntry<K, V> next) {
            this.next = next;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof Map.Entry)) {
                return false;
            }

            final Map.Entry other = (Map.Entry) obj;
            return Objects.equals(key, other.getKey()) &&
                Objects.equals(value, other.getValue());
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
