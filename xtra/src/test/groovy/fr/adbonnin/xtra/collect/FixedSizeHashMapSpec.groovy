package fr.adbonnin.xtra.collect

import spock.lang.Specification

import static fr.adbonnin.xtra.collect.XtraList.newArrayList
import static fr.adbonnin.xtra.collect.XtraMap.newHashMap

class FixedSizeHashMapSpec extends Specification {

    void "should throw an exception when construct with negative max size"() {
        when:
        new FixedSizeHashMap<>(maxSize)

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == 'max size must be positive'

        where:
        maxSize = -1
    }

    void "should get max size"() {
        given:
        def map = new FixedSizeHashMap(maxSize)

        expect:
        map.maxSize == maxSize

        where:
        maxSize = 1
    }

    void "should add value"() {
        given:
        def map = new FixedSizeHashMap(maxSize)

        expect:
        map.size() == 0
        !map.containsKey(key)

        when:
        map.add(key, value)

        then:
        map.size() == 1
        map.containsKey(key)

        newHashMap(map.entries()) == [(key): value]
        newArrayList(map.values()) == [value]

        where:
        maxSize = 1
        key = 2
        value = 3
    }

    void "should add more entries than max size"() {
        given:
        def map = new FixedSizeHashMap(maxSize)
        map.add(key1, value1)

        expect:
        map.size() == 1
        newHashMap(map.entries()) == [(key1): value1]
        newArrayList(map.values()) == [value1]

        when:
        map.add(key2, value2)

        then:
        map.size() == 1
        newHashMap(map.entries()) == [(key2): value2]
        newArrayList(map.values()) == [value2]

        where:
        maxSize = 1

        key1 = 1
        value1 = 1

        key2 = 2
        value2 = 2
    }

    void "should return false when adding key that already exists"() {
        given:
        def map = new FixedSizeHashMap(maxSize)

        and:
        map.add(key, value1)

        expect:
        map.size() == 1
        newHashMap(map.entries()) == [(key): value1]
        newArrayList(map.values()) == [value1]

        when:
        map.add(key, value2)

        then:
        map.size() == 1
        newHashMap(map.entries()) == [(key): value1]
        newArrayList(map.values()) == [value1]

        where:
        maxSize = 2

        key = 3
        value1 = 1
        value2 = 2
    }
}
