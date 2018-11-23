package fr.adbonnin.xtra.net

import org.apache.http.client.utils.URIBuilder
import org.apache.http.message.BasicNameValuePair
import spock.lang.Specification

class XtraUriBuilderSpec extends Specification {

    void "should get query param"() {
        given:
        def uriBuilder = new URIBuilder(url)

        when:
        def pair = XtraUriBuilder.getQueryParam(uriBuilder, name)

        then:
        pair == expectedPair

        where:
        url                | name || expectedPair
        'http://a.b/c?d=e' | 'd'  || new BasicNameValuePair('d', 'e')
        'http://a.b/c?d=e' | 'f'  || null
    }
}
