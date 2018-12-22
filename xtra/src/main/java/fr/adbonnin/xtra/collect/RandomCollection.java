package fr.adbonnin.xtra.collect;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<T> implements Iterator<T> {

    private final TreeMap<Double, T> map = new TreeMap<>();

    private final Random random;

    private double total = 0;

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection add(double weight, T element) {
        if (weight > 0) {
            total += weight;
            map.put(total, element);
        }
        return this;
    }

    @Override
    public boolean hasNext() {
        return !map.isEmpty();
    }

    @Override
    public T next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
