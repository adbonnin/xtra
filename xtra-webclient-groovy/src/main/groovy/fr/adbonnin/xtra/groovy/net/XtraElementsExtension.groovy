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

    static Iterable<String> absUrls(Elements elements, String attributeKey) {
        return XtraElements.absUrls(elements, attributeKey)
    }

    static Iterable<String> absUrls(Elements elements) {
        return XtraElements.absUrls(elements)
    }

    static Iterable<String> texts(Elements elements) {
        return XtraElements.texts(elements)
    }

    private XtraElementsExtension() { /* Cannot be instantiated */ }
}
