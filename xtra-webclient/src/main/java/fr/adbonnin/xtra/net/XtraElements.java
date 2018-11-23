package fr.adbonnin.xtra.net;

import fr.adbonnin.xtra.base.Function;
import fr.adbonnin.xtra.collect.XtraIterable;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class XtraElements {

    private static TextElementFunction TEXT_ELEMENT_FUNCTION = new TextElementFunction();

    private static AbsUrlElementFunction ABS_URL_ELEMENT_FUNCTION = new AbsUrlElementFunction();

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

    public static Iterable<String> absUrls(final Elements elements, final String attributeKey) {
        final AttributeAbsUrlElementFunction function = new AttributeAbsUrlElementFunction(attributeKey);
        return XtraIterable.transform(elements, function);
    }

    public static Iterable<String> absUrls(final Elements elements) {
        return XtraIterable.transform(elements, ABS_URL_ELEMENT_FUNCTION);
    }

    public static Iterable<String> texts(final Elements elements) {
        return XtraIterable.transform(elements, TEXT_ELEMENT_FUNCTION);
    }

    private static class AttributeAbsUrlElementFunction implements Function<Element, String> {

        private final String attributeKey;

        public AttributeAbsUrlElementFunction(String attributeKey) {
            this.attributeKey = attributeKey;
        }

        @Override
        public String apply(Element input) {
            return input.absUrl(attributeKey);
        }
    }

    private static class AbsUrlElementFunction implements Function<Element, String> {

        @Override
        public String apply(Element input) {
            return XtraElement.absUrl(input);
        }
    }

    private static class TextElementFunction implements Function<Element, String> {

        @Override
        public String apply(Element input) {
            return input.text();
        }
    }

    private XtraElements() { /* Cannot be instantiated */ }
}
