package fr.adbonnin.xtra.predicate

import spock.lang.Specification
import spock.lang.Unroll

class XtraPredicatesSpec extends Specification {

    def "alwaysTrue: should always evaluate to true"() {
        given:
        def predicate = XtraPredicates.alwaysTrue()

        expect:
        predicate.evaluate(value)
        predicate.toString() == "XtraPredicates.alwaysTrue()"

        where:
        value         | _
        Boolean.TRUE  | _
        Boolean.FALSE | _
        null          | _
    }

    def "alwaysFalse: should always evaluate to false"() {
        given:
        def predicate = XtraPredicates.alwaysFalse()

        expect:
        !predicate.evaluate(value)
        predicate.toString() == "XtraPredicates.alwaysFalse()"

        where:
        value         | _
        Boolean.TRUE  | _
        Boolean.FALSE | _
        null          | _
    }

    @Unroll
    def "isTrue: should evaluate #value to #expected"() {
        expect:
        XtraPredicates.isTrue().evaluate(value) == expected

        where:
        value         || expected
        Boolean.TRUE  || true
        Boolean.FALSE || false
        null          || false
    }

    @Unroll
    def "isFalse: should evaluate #value to #expected"() {
        expect:
        XtraPredicates.isFalse().evaluate(value) == expected

        where:
        value         || expected
        Boolean.TRUE  || false
        Boolean.FALSE || true
        null          || false
    }
}
