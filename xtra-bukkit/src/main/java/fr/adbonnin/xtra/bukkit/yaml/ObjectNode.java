package fr.adbonnin.xtra.bukkit.yaml;

import org.bukkit.configuration.ConfigurationSection;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class ObjectNode extends ContainerNode {

    private final ConfigurationSection section;

    public ObjectNode(ConfigurationSection section) {
        this.section = requireNonNull(section);
    }

    //=========================================================
    // Basic property access
    //=========================================================

    public int size() {
        return section.getKeys(false).size();
    }

    public boolean isObject() {
        return true;
    }

    //=========================================================
    // Navigation methods
    //=========================================================

    public YamlNode get(int index) {
        return null;
    }

    public YamlNode get(String fieldName) {
        final Object value = section.get(fieldName);
        return XtraYaml.toYamlNode(value);
    }

    public boolean has(int index) {
        return get(index) != null;
    }

    public boolean has(String fieldName) {
        return get(fieldName) != null;
    }

    @Override
    public Iterator<YamlNode> iterator() {
        final Iterator<Map.Entry<String, YamlNode>> fields = fields();
        return new Iterator<YamlNode>() {
            @Override
            public boolean hasNext() {
                return fields.hasNext();
            }

            @Override
            public YamlNode next() {
                return fields.next().getValue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterator<String> fieldNames() {
        return section.getKeys(false).iterator();
    }

    public Iterator<Map.Entry<String, YamlNode>> fields() {
        final Iterator<Map.Entry<String, Object>> itr = section.getValues(false).entrySet().iterator();
        return new Iterator<Map.Entry<String, YamlNode>>() {
            @Override
            public boolean hasNext() {
                return itr.hasNext();
            }

            @Override
            public Map.Entry<String, YamlNode> next() {
                final Map.Entry<String, Object> next = itr.next();
                final YamlNode value = XtraYaml.toYamlNode(next.getValue());
                return new AbstractMap.SimpleEntry<>(next.getKey(), value);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
