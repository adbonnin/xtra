package fr.adbonnin.xtra.net

import spock.lang.Shared
import spock.lang.Specification

class XtraResourcesSpec extends Specification {

    def "uriResourceName"() {
        expect:
        XtraResources.toUriResourceName(new URI(uri)) == expected

        where:
        uri                  || expected
        'http://foo/bar'     || 'bar'
        'http://foo/bar/'    || ''
        'http://foo'         || ''
        'http://abc/a%20b.c' || 'a b.c'
    }

    def "newDownloadFile: "() {
        expect:
        XtraResources.toFilename(new URI(uri), emptyDefault) == expected

        where:
        uri               | emptyDefault || expected
        'http://foo/abc'  | null         || 'abc'
        'http://foo/abc/' | 'def'        || 'def'
        'http://foo'      | 'def'        || 'def'
        'http://foo/:'    | 'bar'        || 'bar'
    }
}
