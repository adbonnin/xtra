package fr.adbonnin.xtra.collect

import spock.lang.Specification

class XtraIterableSpec extends Specification {

    void "should transform an iterable"() {
        given:
        def iterable = [1, 2, 3]

        when:
        def transformed = XtraIterable.transform(iterable) { it * 2 }

        then:
        transformed.toList() == [2, 4, 6]
    }

    void "should filter an interator"() {
        given:
        def iterable = [1, 2, 3]

        when:
        def filtered = XtraIterable.filter(iterable) { it != 2 }

        then:
        filtered.toList() == [1, 3]
    }
}
