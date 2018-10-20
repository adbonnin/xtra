package fr.adbonnin.xtra.base;

import java.util.Objects;

public class EqualsEquivalence<T> implements Equivalence<T> {

    @Override
    public boolean equivalent(T a, T b) {
        return Objects.equals(a, b);
    }
}
