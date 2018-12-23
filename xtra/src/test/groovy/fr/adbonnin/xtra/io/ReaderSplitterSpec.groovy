package fr.adbonnin.xtra.io

import spock.lang.Specification

class ReaderSplitterSpec extends Specification {

    void "should split reader"() {
        given:
        def reader = new StringReader('a:b')
        def splitter = new ReaderSplitter(reader, ':')

        expect:
        splitter.hasNext()
        splitter.next() == "a"

        and:
        splitter.hasNext()
        splitter.next() == "b"

        when:
        !splitter.hasNext()
        splitter.next()

        then:
        thrown(NoSuchElementException)
    }
}
