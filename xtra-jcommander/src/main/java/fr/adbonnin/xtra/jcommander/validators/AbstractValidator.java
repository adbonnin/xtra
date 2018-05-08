package fr.adbonnin.xtra.jcommander.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;
import fr.adbonnin.xtra.predicate.Predicate;

/**
 * Base class for {@code com.beust.jcommander.IValueValidator}.
 */
public abstract class AbstractValidator<T> implements IValueValidator<T>, Predicate<T> {

    /**
     * Throw a {@code com.beust.jcommander.ParameterException} for the invalid @{code value} of the parameter @{code name}.
     */
    protected abstract void throwValidateException(String name, T value) throws ParameterException;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(String name, T value) throws ParameterException {
        if (!evaluate(value)) {
            throwValidateException(name, value);
        }
    }

    public static abstract class Every<T> implements IValueValidator<Iterable<T>> {

        /**
         * Throw a {@code com.beust.jcommander.ParameterException} for the invalid @{code value} of the parameter @{code name} at {@code index}.
         */
        protected abstract void throwValidateException(String name, T value, int index) throws ParameterException;

        /**
         * Evaluate if the {@code value} at {@code index} is valid.
         */
        public abstract boolean evaluate(T value, int index);

        /**
         * {@inheritDoc}
         */
        @Override
        public void validate(String name, Iterable<T> values) throws ParameterException {

            if (values == null) {
                return;
            }

            int index = 0;
            for (T value : values) {
                if (!evaluate(value, index)) {
                    throwValidateException(name, value, index);
                }
                ++index;
            }
        }
    }
}
