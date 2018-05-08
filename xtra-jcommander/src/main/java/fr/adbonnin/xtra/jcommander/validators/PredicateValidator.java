package fr.adbonnin.xtra.jcommander.validators;

import fr.adbonnin.xtra.predicate.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Base class for {@code com.beust.jcommander.IValueValidator} that use a {@code fr.adbonnin.xtra.predicate.Predicate}.
 */
public abstract class PredicateValidator<T> extends AbstractValidator<T> {

    private final Predicate<T> predicate;

    public PredicateValidator(Predicate<T> predicate) {
        this.predicate = requireNonNull(predicate);
    }

    @Override
    public boolean evaluate(T value) {
        return predicate.evaluate(value);
    }

    public static abstract class Every<T> extends AbstractValidator.Every<T> {

        private final Predicate<T> predicate;

        public Every(Predicate<T> predicate) {
            this.predicate = requireNonNull(predicate);
        }

        @Override
        public boolean evaluate(T value, int index) {
            return predicate.evaluate(value);
        }
    }
}
