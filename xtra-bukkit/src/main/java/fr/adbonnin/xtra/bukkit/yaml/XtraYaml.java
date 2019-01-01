package fr.adbonnin.xtra.bukkit.yaml;

import fr.adbonnin.xtra.bukkit.yaml.node.*;
import fr.adbonnin.xtra.io.XtraIO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class XtraYaml {

    public static YamlNode read(InputStream input) throws IOException {
        return read(XtraIO.toString(input, StandardCharsets.UTF_8));
    }

    public static YamlNode read(String str) {
        final YamlConfiguration config = new YamlConfiguration();
        try {
            config.loadFromString(str);
            return new ObjectNode(config);
        }
        catch (InvalidConfigurationException e) {
            throw new IllegalArgumentException(str);
        }
    }

    public static YamlNode toYamlNode(Object obj) {
        if (obj == null) {
            return NullNode.instance;
        }
        else if (obj instanceof Boolean) {
            return new BooleanNode((Boolean) obj);
        }
        else if (obj instanceof Integer) {
            return new IntNode((Integer) obj);
        }
        else if (obj instanceof String) {
            return new TextNode((String) obj);
        }
        else if (obj instanceof ConfigurationSection) {
            return new ObjectNode((ConfigurationSection) obj);
        }
        else if (obj instanceof List) {
            return new ArrayNode((List) obj);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private XtraYaml() { /* Cannot be instantiated */ }
}
