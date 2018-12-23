package fr.adbonnin.xtra.bukkit.yaml;

public class IntNode extends ValueNode {

    private final int value;

    public IntNode(int value) {
        this.value = value;
    }

    //=========================================================
    // Straight property access
    //=========================================================

    @Override
    public int intValue() {
        return value;
    }

    //=========================================================
    // General type coercions
    //=========================================================

    public double asDouble(double defaultValue) {
        return intValue();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof IntNode)) {
            return false;
        }

        return ((IntNode) o).value == value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
