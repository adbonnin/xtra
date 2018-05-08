package fr.adbonnin.xtra.net

import spock.lang.Shared
import spock.lang.Specification

class XtraResourcesSpec extends Specification {

    @Shared
    def dir = new File('dir')

    def "uriResourceName"() {
        expect:
        XtraResources.uriResourceName(new URI(uri)) == expected

        where:
        uri                  || expected
        'http://foo/bar'     || 'bar'
        'http://foo/bar/'    || ''
        'http://foo'         || ''
        'http://abc/a%20b.c' || 'a b.c'
    }

    def "newDownloadFile: "() {
        expect:
        XtraResources.newDownloadFile(new URI(uri), dir, emptyDefault) == expected

        where:
        uri               | emptyDefault || expected
        'http://foo/bar'  | null         || new File(dir, 'bar')
        'http://foo/bar/' | null         || dir
        'http://foo'      | null         || dir
        'http://foo/:'    | 'bar'        || new File(dir, 'bar')
    }
}
