package fr.adbonnin.xtra.base

import spock.lang.Specification

class CircularCharArraySpec extends Specification {

    void "should add char with size 1"() {
        given:
        def cs = new CircularCharArray(1)

        expect:
        cs.size() == 0

        and:
        cs.addChar('a' as char) == -1
        cs.size() == 1

        and:
        cs.addChar('b' as char) == 'a' as char
        cs.size() == 1

        and:
        cs.addChar('c' as char) == 'b' as char
        cs.size() == 1
    }

    void "should add char with size 2"() {
        given:
        def cs = new CircularCharArray(2)

        expect:
        cs.size() == 0

        and:
        cs.addChar('a' as char) == -1
        cs.size() == 1

        cs.addChar('b' as char) == -1
        cs.size() == 2

        and:
        cs.addChar('c' as char) == 'a' as char
        cs.size() == 2

        cs.addChar('d' as char) == 'b' as char
        cs.size() == 2

        and:
        cs.addChar('e' as char) == 'c' as char
        cs.size() == 2
    }

    void "should throw an exception when char is get out of bounds"() {
        given:
        def cs = new CircularCharArray(2)

        when:
        cs.charAt(0)

        then:
        thrown(StringIndexOutOfBoundsException)

        when:
        cs.charAt(1)

        then:
        thrown(StringIndexOutOfBoundsException)
    }

    void "should get char at"() {
        given:
        def cs = new CircularCharArray(2)

        when:
        cs.addChar('a' as char)

        then:
        cs.charAt(0) == 'a' as char

        when:
        cs.addChar('b' as char)

        then:
        cs.charAt(0) == 'a' as char
        cs.charAt(1) == 'b' as char

        when:
        cs.addChar('c' as char)

        then:
        cs.charAt(0) == 'b' as char
        cs.charAt(1) == 'c' as char
    }

    void "should get simple sub sequence"() {
        given:
        def cs = new CircularCharArray(2)

        expect:
        cs.subSequence(0, 0).toString() == ''

        when:
        cs.addChar('a' as char)

        then:
        cs.subSequence(0, 1).toString() == 'a'

        when:
        cs.addChar('b' as char)

        then:
        cs.subSequence(0, 1).toString() == 'a'
        cs.subSequence(0, 2).toString() == 'ab'
        cs.subSequence(1, 2).toString() == 'b'
    }

    void "should get complex sub sequence"() {
        given:
        def cs = new CircularCharArray(4)
        cs.addChar('a' as char)
        cs.addChar('b' as char)
        cs.addChar('c' as char)
        cs.addChar('d' as char)
        cs.addChar('e' as char)
        cs.addChar('f' as char)

        expect:
        cs.subSequence(start, end).toString() == expectedSubSequence

        where:
        start | end || expectedSubSequence
        0     | 1   || 'c'
        0     | 3   || 'cde'
        3     | 4   || 'f'
    }

    void "should clear"() {
        given:
        def cs = new CircularCharArray(2)
        cs.addChar('a' as char)

        when:
        cs.clear()

        then:
        cs.length() == 0
        cs.toString() == ''

        when:
        cs.addChar('a' as char)
        cs.addChar('b' as char)
        cs.addChar('c' as char)

        and:
        cs.clear()

        then:
        cs.length() == 0
        cs.toString() == ''
    }
}
