package fr.adbonnin.xtra.collect

import spock.lang.Specification

class XtraListSpec extends Specification {

    void "should create ArrayList from iterator"() {
        when:
        def arrayList = XtraList.newArrayList(iterable.iterator())

        then:
        arrayList == iterable

        where:
        iterable = [1, 2, 3]
    }

    void "should create ArrayList from iterable"() {
        when:
        def arrayList = XtraList.newArrayList(iterable)

        then:
        arrayList == iterable

        where:
        iterable = [1, 2, 3]
    }
}
