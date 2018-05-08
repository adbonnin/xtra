package fr.adbonnin.xtra.jcommander.validators

import com.beust.jcommander.ParameterException
import spock.lang.Specification

class AbstractValidatorSpec extends Specification {

    void "should evaluate value"() {
        given:
        def validator = Spy(AbstractValidator) {
            evaluate(_) >> { it[0] }
            throwValidateException(_, _) >> { name, value -> throw new ParameterException("$name:$value") }
        }

        when:
        validator.validate('name', true)

        then:
        notThrown(ParameterException)

        when:
        validator.validate('name', false)

        then:
        def ex = thrown(ParameterException)
        ex.message == "name:false"
    }

    void "should evaluate every values"() {
        given:
        def validator = Spy(AbstractValidator.Every) {
            evaluate(_, _) >> { it[0] }
            throwValidateException(_, _, _) >> { name, value, index -> throw new ParameterException("$name:$value:$index") }
        }

        when:
        validator.validate('name', null)

        then:
        notThrown(ParameterException)

        when:
        validator.validate('name', [true])

        then:
        notThrown(ParameterException)

        when:
        validator.validate('name', [true, false])

        then:
        def ex = thrown(ParameterException)
        ex.message == "name:false:1"
    }
}
