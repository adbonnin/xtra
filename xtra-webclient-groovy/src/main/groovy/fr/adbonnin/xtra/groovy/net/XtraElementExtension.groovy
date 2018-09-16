package fr.adbonnin.xtra.groovy.net

import fr.adbonnin.xtra.net.XtraElement
import org.jsoup.nodes.Element

final class XtraElementExtension {

    static String lines(Element element) {
        return XtraElement.lines(element)
    }

    static String absUrl(Element element) {
        def attr = nodeNameToAttrUrl(element.nodeName())
        return attr ? element.absUrl(attr) : null
    }

    private static String nodeNameToAttrUrl(String nodeName) {
        switch (nodeName) {
            case 'a': return 'href'
            case 'img': return 'src'
            default: null
        }
    }

    static Element firstChild(Element element) {
        return element.children().first()
    }

    private XtraElementExtension() { /* Cannot be instantiated */ }
}
