package fr.adbonnin.xtra.base;

import java.util.Map;

public enum SystemProperties implements Map.Entry<String, String> {
    USER_DIR("user.dir");

    private final String key;

    SystemProperties(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return System.getProperty(key);
    }

    @Override
    public String setValue(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
