package fr.adbonnin.xtra.predicate;

public interface Predicate<T> {

    boolean evaluate(T value);
}
