package fr.adbonnin.xtra.base

import spock.lang.Specification
import spock.lang.Unroll

class XtraStringsSpec extends Specification {

    @Unroll
    def "should eval #str to #expectedLabel"() {
        expect:
        XtraStrings.isEmpty(str) == expected

        where:
        str   || expected
        null  || true
        ''    || true
        'foo' || false

        expectedLabel = expected ? 'empty' : 'not empty'
    }

    def "should replace end"() {
        expect:
        XtraStrings.replaceEnd(str, (String) search, (String) replacement, false) == expected
        XtraStrings.replaceEnd(str, (String) search, (String) replacement, true) == expectedIgnoreCase

        where:
        str   | search | replacement || expected | expectedIgnoreCase
        null  | _      | null        || null     | null
        ''    | _      | null        || ''       | ''
        'bar' | null   | null        || 'bar'    | 'bar'
        'bar' | ''     | null        || 'bar'    | 'bar'
        'bar' | 'r'    | null        || 'ba'     | 'ba'
        'BAR' | 'r'    | null        || 'BAR'    | 'BA'
        'bar' | 'a'    | null        || 'bar'    | 'bar'
        'bar' | 'barr' | null        || 'bar'    | 'bar'
        'bar' | 'r'    | 'foo'       || 'bafoo'  | 'bafoo'
    }

    def "should replace start"() {
        expect:
        XtraStrings.replaceStart(str, (String) search, (String) replacement, false) == expected
        XtraStrings.replaceStart(str, (String) search, (String) replacement, true) == expectedIgnoreCase

        where:
        str   | search | replacement || expected | expectedIgnoreCase
        null  | _      | null        || null     | null
        ''    | _      | null        || ''       | ''
        'bar' | null   | null        || 'bar'    | 'bar'
        'bar' | ''     | null        || 'bar'    | 'bar'
        'bar' | 'b'    | null        || 'ar'     | 'ar'
        'BAR' | 'b'    | null        || 'BAR'    | 'AR'
        'bar' | 'a'    | null        || 'bar'    | 'bar'
        'bar' | 'barr' | null        || 'bar'    | 'bar'
        'bar' | 'b'    | 'foo'       || 'fooar'  | 'fooar'
    }

    @Unroll
    def "should check that '#str' #labelExpectedContains '#search' by ignoring case"() {
        expect:
        XtraStrings.containsIgnoreCase(str, search) == expectedContains

        where:
        str   | search || expectedContains
        'abc' | 'b'    || true
        'abc' | 'A'    || true
        'ABC' | 'c'    || true
        'b'   | 'abc'  || false
        'abc' | 'def'  || false

        labelExpectedContains = expectedContains ? 'contains' : 'not contains'
    }

    @Unroll
    def "should find in '#str' first index of '#search' at #expectedIndex"() {
        when:
        def index = XtraStrings.indexOf(str, search)

        then:
        index == expectedIndex

        where:
        str   | search || expectedIndex
        null  | "a"    || -1
        "a"   | null   || -1
        "abc" | "b"    || 1
    }

    @Unroll
    def "should remove before#labelInclude '#search' in '#str' to '#expectedStr'"() {
        when:
        def result = XtraStrings.removeBefore(str, search, include)

        then:
        result == expectedStr

        where:
        str   | search | include || expectedStr
        null  | 'a'    | false   || null
        'a'   | null   | false   || 'a'
        'a'   | 'b'    | false   || 'a'
        'abc' | 'b'    | true    || 'c'
        'abc' | 'b'    | false   || 'bc'

        labelInclude = include ? ' and include' : ''
    }

    @Unroll
    def "should remove#labelInclude after '#search' in '#str' to '#expectedStr'"() {
        when:
        def result = XtraStrings.removeAfter(str, search, include)

        then:
        result == expectedStr

        where:
        str   | search | include || expectedStr
        null  | 'a'    | false   || null
        'a'   | null   | false   || 'a'
        'a'   | 'b'    | false   || 'a'
        'abc' | 'b'    | true    || 'a'
        'abc' | 'b'    | false   || 'ab'

        labelInclude = include ? ' and include' : ''
    }
}
