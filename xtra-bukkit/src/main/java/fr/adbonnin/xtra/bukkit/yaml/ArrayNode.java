package fr.adbonnin.xtra.bukkit.yaml;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ArrayNode extends ContainerNode {

    private final List<?> list;

    public ArrayNode(List<?> list) {
        this.list = requireNonNull(list);
    }

    //=========================================================
    // Basic property access
    //=========================================================

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isArray() {
        return true;
    }

    //=========================================================
    // Navigation methods
    //=========================================================

    @Override
    public YamlNode get(int index) {
        final Object value = list.get(index);
        return XtraYaml.toYamlNode(value);
    }

    @Override
    public YamlNode get(String fieldName) {
        return null;
    }

    @Override
    public Iterator<YamlNode> iterator() {
        final Iterator<?> itr = list.iterator();
        return new Iterator<YamlNode>() {
            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public YamlNode next() {
                final Object next = itr.next();
                return XtraYaml.toYamlNode(next);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
