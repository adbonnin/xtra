package fr.adbonnin.xtra.net;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class XtraElements {

    public static Element removeFist(Elements elements) {
        return elements.remove(0);
    }

    public static Element removeLast(Elements elements) {
        return elements.remove(elements.size() - 1);
    }

    public static Element nextElement(Elements elements, Element search) {

        final int index = elements.indexOf(search);
        if (index == -1) {
            return null;
        }

        final int nextIndex = index + 1;
        if (nextIndex >= elements.size()) {
            return null;
        }

        return elements.get(nextIndex);
    }

    private XtraElements() { /* Cannot be instantiated */ }
}
