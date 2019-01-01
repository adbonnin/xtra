package fr.adbonnin.xtra.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<T> implements Iterable<T> {

    private final TreeMap<Double, T> map = new TreeMap<>();

    private double total = 0;

    public RandomCollection add(T element, double weight) {
        if (weight > 0) {
            total += weight;
            map.put(total, element);
        }
        return this;
    }

    public RandomCollection addAll(Iterator<? extends Map.Entry<? extends T, ? extends Number>> iterator) {
        while (iterator.hasNext()) {
            final Map.Entry<? extends T, ? extends Number> next = iterator.next();
            add(next.getKey(), next.getValue().doubleValue());
        }
        return this;
    }

    public T get(Random random) {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    public Iterator<T> values() {
        return XtraIterators.unmodifiableIterator(map.values().iterator());
    }

    @Override
    public Iterator<T> iterator() {
        return iterator(new Random());
    }

    public Iterator<T> iterator(final Random random) {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !map.isEmpty();
            }

            @Override
            public T next() {
                return RandomCollection.this.get(random);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
