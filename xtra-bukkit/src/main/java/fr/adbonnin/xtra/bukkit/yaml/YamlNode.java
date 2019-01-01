package fr.adbonnin.xtra.bukkit.yaml;

import fr.adbonnin.xtra.base.Function;
import fr.adbonnin.xtra.base.Pair;
import fr.adbonnin.xtra.bukkit.yaml.node.MissingNode;
import fr.adbonnin.xtra.collect.XtraIterators;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public abstract class YamlNode implements Iterable<YamlNode> {

    //=========================================================
    // Basic property access
    //=========================================================

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isObject() {
        return false;
    }

    public boolean isTextual() { return false; }

    public boolean isNull() {
        return false;
    }

    //=========================================================
    // Straight property access
    //=========================================================

    public String textValue() {
        return null;
    }

    public boolean booleanValue() {
        return false;
    }

    public int intValue() {
        return 0;
    }

    //=========================================================
    // General type coercions
    //=========================================================

    public boolean asBoolean(boolean defaultValue)  {
        return defaultValue;
    }

    public boolean asBoolean(String fieldName, boolean defaultValue) {
        return defaultValue;
    }

    public int asInt(int defaultValue) {
        return defaultValue;
    }

    public int asInt(String fieldName, int defaultValue) {
        return defaultValue;
    }

    public double asDouble(double defaultValue) {
        return defaultValue;
    }

    public double asDouble(String fieldName, double defaultValue) {
        return defaultValue;
    }

    //=========================================================
    // Navigation methods
    //=========================================================

    public boolean has(int index) {
        return false;
    }

    public YamlNode path(int index) {
        return MissingNode.getInstance();
    }

    @Override
    public Iterator<YamlNode> iterator() {
        return Collections.emptyIterator();
    }

    public <T> Iterator<T> mapElements(Function<YamlNode, ? extends T> function) {
        return XtraIterators.transform(iterator(), function);
    }

    public boolean has(String fieldName) {
        return false;
    }

    public YamlNode path(String fieldName) {
        return MissingNode.getInstance();
    }

    public Iterator<String> fieldNames() {
        return Collections.emptyIterator();
    }

    public Iterator<Map.Entry<String, YamlNode>> fields() {
        return Collections.emptyIterator();
    }

    public <T> Iterator<Map.Entry<String, T>> mapFiels(final Function<YamlNode, ? extends T> function) {
        final Iterator<Map.Entry<String, YamlNode>> fields = fields();
        return new Iterator<Map.Entry<String, T>>() {

            @Override
            public boolean hasNext() {
                return fields.hasNext();
            }

            @Override
            public Map.Entry<String, T> next() {
                final Map.Entry<String, YamlNode> next = fields.next();
                final T mappedValue = function.apply(next.getValue());
                return new Pair<>(next.getKey(), mappedValue);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
