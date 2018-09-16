package fr.adbonnin.xtra.net

import org.jsoup.Jsoup
import spock.lang.Specification
import spock.lang.Unroll

class XtraElementSpec extends Specification {

    @Unroll
    def "should tranform '#html' to lines '#expectedLines'"() {
        given:
        def element = Jsoup.parse(html)

        expect:
        XtraElement.lines(element) == expectedLines

        where:
        html                       || expectedLines
        '<div>a</div><div>b</div>' || 'a\nb'
        'a<div>b</div>'            || 'a\nb'

        'a<span>b</span>'          || 'ab'
        '<span>a</span>b'          || 'ab'
    }
}
