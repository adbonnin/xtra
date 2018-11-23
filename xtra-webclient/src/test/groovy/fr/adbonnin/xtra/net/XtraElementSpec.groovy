package fr.adbonnin.xtra.net

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Tag
import spock.lang.Specification
import spock.lang.Unroll

class XtraElementSpec extends Specification {

    void "should append children to element"() {
        given:
        def element = new Element('a')
        def children = ['b', 'c'].collect { new Element(it) }

        expect:
        element.children().isEmpty()

        when:
        XtraElement.appendChildren(element, children)

        then:
        element.children().size() == 2
        element.child(0).tagName() == 'b'
        element.child(1).tagName() == 'c'
    }

    void "should get absUrl of attribut"() {
        given:
        def elt = new Element(Tag.valueOf(tag), 'http://a.b')
        elt.attr(attributeKey, attributeValue)

        expect:
        XtraElement.absUrl(elt) == expectedUrl

        where:
        tag   | attributeKey | attributeValue || expectedUrl
        'a'   | 'href'       | '/c'           || 'http://a.b/c'
        'img' | 'src'        | '/c'           || 'http://a.b/c'
        'a'   | 'notAttr'    | '/c'           || ''
    }

    @Unroll
    void "should tranform '#html' to lines '#expectedLines'"() {
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
