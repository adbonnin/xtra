package fr.adbonnin.xtra.jcommander.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;
import org.junit.Test;

import static fr.adbonnin.xtra.predicate.XtraPredicates.isTrue;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PredicateValidatorTest {

    @Test
    public void testEvaluate() {
        // given
        IValueValidator<Boolean> validator = new PredicateValidator<Boolean>(isTrue()) {
            @Override
            protected void throwValidateException(String name, Boolean value) throws ParameterException {
                throw new ParameterException(name + ':' + value);
            }
        };

        // expect no exception
        validator.validate("name", true);

        // when
        try {
            validator.validate("name", false);

            // then
            fail();
        }
        catch (ParameterException e) {
            // should throw an exception
            assertEquals("name:false", e.getMessage());
        }
    }

    @Test
    public void testEveryEvaluate() {
        // given
        IValueValidator<Iterable<Boolean>> validator = new PredicateValidator.Every<Boolean>(isTrue()) {
            @Override
            protected void throwValidateException(String name, Boolean value, int index) throws ParameterException {
                throw new ParameterException(name + ':' + value + ':' + index);
            }
        };

        // expect no exception
        validator.validate("name", null);

        // expect no exception
        validator.validate("name", singletonList(true));

        // when
        try {
            validator.validate("name", asList(true, false));

            // then
            fail();
        }
        catch (ParameterException e) {
            // should throw an exception
            assertEquals("name:false:1", e.getMessage());
        }
    }
}
