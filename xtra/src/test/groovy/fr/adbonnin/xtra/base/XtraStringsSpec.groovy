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
}
