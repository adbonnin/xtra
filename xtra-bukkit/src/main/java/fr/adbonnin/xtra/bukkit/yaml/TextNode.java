package fr.adbonnin.xtra.bukkit.yaml;

import fr.adbonnin.xtra.base.XtraNumber;

import static java.util.Objects.requireNonNull;

public class TextNode extends ValueNode {

    private final String value;

    public TextNode(String value) {
        this.value = requireNonNull(value);
    }

    @Override
    public boolean isTextual() {
        return true;
    }

    //=========================================================
    // Straight property access
    //=========================================================

    public String textValue() {
        return value;
    }

    //=========================================================
    // General type coercions
    //=========================================================

    public double asDouble(double defaultValue) {
        return XtraNumber.asDouble(value, defaultValue);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof TextNode)) {
            return false;
        }

        return value.equals(((TextNode) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
