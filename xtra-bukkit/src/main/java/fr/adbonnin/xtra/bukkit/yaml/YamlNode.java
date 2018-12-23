package fr.adbonnin.xtra.bukkit.yaml;

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

    public double asDouble(double defaultValue) {
        return defaultValue;
    }

    //=========================================================
    // Navigation methods
    //=========================================================

    public abstract YamlNode get(int index);

    public abstract YamlNode get(String fieldName);

    public boolean has(String fieldName) {
        return get(fieldName) != null;
    }

    public boolean has(int index) {
        return get(index) != null;
    }

    @Override
    public Iterator<YamlNode> iterator() {
        return Collections.emptyIterator();
    }

    public Iterator<String> fieldNames() {
        return Collections.emptyIterator();
    }

    public Iterator<Map.Entry<String, YamlNode>> fields() {
        return Collections.emptyIterator();
    }
}
