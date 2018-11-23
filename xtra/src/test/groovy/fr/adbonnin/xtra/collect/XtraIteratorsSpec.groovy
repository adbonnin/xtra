package fr.adbonnin.xtra.collect

import spock.lang.Specification
import spock.lang.Unroll

class XtraIteratorsSpec extends Specification {

    @Unroll
    def "should join #labelIterable with #labelSeparator to #expected"() {
        expect:
        XtraIterators.join(iterable, (String) separator) == expected

        where:
        iterable        | separator || expected
        []              | _         || ''
        [null]          | _         || 'null'
        ['a', 'b', 'c'] | '--'      || 'a--b--c'

        labelIterable = iterable.toString()
        labelSeparator = separator != _ ? separator : 'every separator'
    }

    void "should transform an iterator"() {
        given:
        def iterable = [1, 2, 3]

        when:
        def transformed = XtraIterators.transform(iterable.iterator()) { it * 2 }

        then:
        transformed.toList() == [2, 4, 6]
    }
}
