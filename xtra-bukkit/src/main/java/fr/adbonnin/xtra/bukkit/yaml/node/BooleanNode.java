package fr.adbonnin.xtra.bukkit.yaml.node;

public class BooleanNode extends ValueNode {

    private final boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    //=========================================================
    // Straight property access
    //=========================================================

    @Override
    public boolean booleanValue() {
        return value;
    }

    //=========================================================
    // General type coercions
    //=========================================================

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return booleanValue();
    }
}
