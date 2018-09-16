package fr.adbonnin.xtra.net

import spock.lang.Specification

class XtraHttpSpec extends Specification {

    def "should encode url"() {
        expect:
        XtraHttp.encodeUrl(url) == exptectedEncodedUrl

        where:
        url               || exptectedEncodedUrl
        'http://abc.net"' || 'http://abc.net%22'
    }
}
