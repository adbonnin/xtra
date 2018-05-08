package fr.adbonnin.xtra.predicate;

import fr.adbonnin.xtra.base.XtraBooleans;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public final class XtraPredicates {

    /** Returns a predicate that always evaluates to {@code true}. */
    public static <T> Predicate<T> alwaysTrue() {
        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
    }

    /** Returns a predicate that always evaluates to {@code false}. */
    public static <T> Predicate<T> alwaysFalse() {
        return ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
    }

    /**
     * Return a predicate that evaluate to {@code true} if the value is not {@code null} and {@code true}
     * @see XtraBooleans#isTrue(Boolean)
     */
    public static Predicate<Boolean> isTrue() {
        return BooleanPredicate.IS_TRUE;
    }

    /**
     * Return a predicate that evaluate to {@code true} if the value is not {@code null} and {@code false}
     * @see XtraBooleans#isFalse(Boolean)
     */
    public static Predicate<Boolean> isFalse() {
        return BooleanPredicate.IS_FALSE;
    }

    enum ObjectPredicate implements Predicate<Object> {
        /** @see XtraPredicates#alwaysTrue() */
        ALWAYS_TRUE {
            @Override
            public boolean evaluate(Object value) {
                return true;
            }

            @Override
            public String toString() {
                return "XtraPredicates.alwaysTrue()";
            }
        },
        /** @see XtraPredicates#alwaysFalse() */
        ALWAYS_FALSE {
            @Override
            public boolean evaluate(Object value) {
                return false;
            }

            @Override
            public String toString() {
                return "XtraPredicates.alwaysFalse()";
            }
        };

        @SuppressWarnings("unchecked")
        <T> Predicate<T> withNarrowedType() {
            return (Predicate<T>) this;
        }
    }

    enum BooleanPredicate implements Predicate<Boolean> {
        /** @see XtraPredicates#isTrue() */
        IS_TRUE {
            @Override
            public boolean evaluate(Boolean value) {
                return XtraBooleans.isTrue(value);
            }
        },
        /** @see XtraPredicates#isFalse() */
        IS_FALSE {
            @Override
            public boolean evaluate(Boolean value) {
                return XtraBooleans.isFalse(value);
            }
        }
    }

    public static <T> Predicate<T> or(final Predicate<T> left, final Predicate<T> right) {
        return new OrPredicate<>(left, right);
    }

    public static <T> boolean evaluateAnd(T value, Iterator<? extends Predicate<T>> predicates) {
        while (predicates.hasNext()) {
            final Predicate<T> predicate = predicates.next();
            if (!predicate.evaluate(value)) {
                return false;
            }
        }
        return true;
    }

    /** @see  XtraPredicates#or(Predicate, Predicate) */
    private static class OrPredicate<T> implements Predicate<T> {

        private final Predicate<T> left;
        private final Predicate<T> right;

        private OrPredicate(Predicate<T> left, Predicate<T> right) {
            this.left = requireNonNull(left);
            this.right = requireNonNull(right);
        }

        @Override
        public boolean evaluate(T value) {
            return left.evaluate(value) || right.evaluate(value);
        }
    }


    private XtraPredicates() { /* Cannot be instantiated */ }
}
