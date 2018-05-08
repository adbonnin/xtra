package fr.adbonnin.xtra.base

import spock.lang.Specification

import static java.util.Arrays.asList

class XtraObjectsSpec extends Specification {

    def "requireNonNullCopy: should return a copy of the array"() {
        given:
        def o1 = new Object()
        def o2 = new Object()
        def array = [o1, o2] as Object[]

        when:
        def copy = XtraObjects.requireNonNullCopy(array)

        then:
        copy == asList(array)
    }

    def "requireNonNullCopy: should return a copy of the iterable"() {
        given:
        def o1 = new Object()
        def o2 = new Object()
        def array = [o1, o2]

        when:
        def copy = XtraObjects.requireNonNullCopy(array)

        then:
        copy == array
        !copy.is(array)
    }

    def "requireNonNullCopy: should throw a NullPointerException if array containts null value"() {
        when:
        XtraObjects.requireNonNullCopy("foo", null)

        then:
        thrown(NullPointerException)
    }
}
