package fr.adbonnin.xtra.io

import spock.lang.Specification

class XtraCloseablesSpec extends Specification {

    def "closeQuietly: should close closeable and catch IOException"() {
        given:
        def closeable = Mock(Closeable)

        when:
        XtraCloseables.closeQuietly(closeable)

        then:
        1 * closeable.close() >> { throw new IOException() }
        notThrown(IOException)
    }
}
