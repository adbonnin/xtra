package fr.adbonnin.xtra.base

import spock.lang.Specification
import spock.lang.Unroll

class XtraBooleansSpec extends Specification {

    @Unroll
    def "isTrue: #value is #expected"() {
        expect:
        XtraBooleans.isTrue(value) == expected

        where:
        value || expected
        false || false
        true  || true
        null  || false
    }

    @Unroll
    def "isFalse: #value is #expected"() {
        expect:
        XtraBooleans.isFalse(value) == expected

        where:
        value || expected
        false || true
        true  || false
        null  || false
    }
}
