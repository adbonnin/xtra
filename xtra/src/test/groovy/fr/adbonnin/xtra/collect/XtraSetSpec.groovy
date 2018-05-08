package fr.adbonnin.xtra.collect

import spock.lang.Specification

class XtraSetSpec extends Specification {

    def "should create new HashSet"() {
        when:
        def set = XtraSet.newHashSet(values.iterator())

        then:
        set == new HashSet(values)

        where:
        values = [1, 2, 3]
    }

    def "should create new LinkedHashSet"() {
        when:
        def set = XtraSet.newLinkedHashSet(values.iterator())

        then:
        set == new LinkedHashSet(values)

        where:
        values = [1, 2, 3]
    }
}
