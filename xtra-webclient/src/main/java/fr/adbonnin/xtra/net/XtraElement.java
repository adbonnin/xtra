package fr.adbonnin.xtra.net;

import fr.adbonnin.xtra.base.XtraStrings;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public final class XtraElement {

    public static Element appendChildren(final Element element, Iterable<Element> children) {

        for (Element child : children) {
            element.appendChild(child);
        }

        return element;
    }

    public static String absUrl(final Element element) {
        final String urlAttr = nodeNameToUrlAttribute(element.nodeName());
        return urlAttr == null ? XtraStrings.EMPTY : element.absUrl(urlAttr);
    }

    public static String nodeNameToUrlAttribute(String nodeName) {
        switch (nodeName) {
            case "a": return "href";
            case "img": return "src";
            default: return null;
        }
    }

    public static String lines(Element element) {
        final LinesNodeVisitor visitor = new LinesNodeVisitor();
        NodeTraversor.traverse(visitor, element);
        return visitor.text();
    }

    private static class LinesNodeVisitor implements NodeVisitor {

        private final StringBuilder buffer = new StringBuilder();

        public String text() {
            return buffer.toString().trim();
        }

        boolean isNewline = true;

        @Override
        public void head(Node node, int depth) {
            if (node instanceof TextNode) {
                final TextNode textNode = (TextNode) node;

                final String text = textNode.text().replace('\u00A0', ' ').trim();
                if (!text.isEmpty()) {
                    buffer.append(text);
                    isNewline = false;
                }
            }
            else if (node instanceof Element) {
                final Element element = (Element) node;

                if (!isNewline) {
                    if ((element.isBlock() || element.tagName().equals("br"))) {
                        buffer.append("\n");
                        isNewline = true;
                    }
                }
            }
        }

        @Override
        public void tail(Node node, int depth) {
        }
    }

    private XtraElement() { /* Cannot be instantiated */ }
}
