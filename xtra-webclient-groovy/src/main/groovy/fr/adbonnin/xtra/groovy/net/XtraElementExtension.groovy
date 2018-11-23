package fr.adbonnin.xtra.groovy.net

import fr.adbonnin.xtra.net.XtraElement
import org.jsoup.nodes.Element

final class XtraElementExtension {

    static String lines(Element element) {
        return XtraElement.lines(element)
    }

    static String absUrl(Element element) {
        return XtraElement.absUrl(element)
    }

    static Element firstChild(Element element) {
        return element.children().first()
    }

    static Element appendChildren(Element element, Iterable<Element> children) {
        return XtraElement.appendChildren(element, children)
    }

    private XtraElementExtension() { /* Cannot be instantiated */ }
}
