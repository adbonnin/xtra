package fr.adbonnin.xtra.groovy.net

import fr.adbonnin.xtra.net.XtraElements
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

final class XtraElementsExtension {

    static String removeFirst(Elements elements) {
        return XtraElements.removeFist(elements)
    }

    static String removeLast(Elements elements) {
        return XtraElements.removeLast(elements)
    }

    static Element nextElement(Elements elements, Element search) {
        return XtraElements.nextElement(elements, search)
    }

    private XtraElementsExtension() { /* Cannot be instantiated */ }
}
