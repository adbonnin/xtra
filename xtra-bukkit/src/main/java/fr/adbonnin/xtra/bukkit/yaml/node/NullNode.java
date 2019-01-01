package fr.adbonnin.xtra.bukkit.yaml.node;

public class NullNode extends ValueNode {

    public static final NullNode instance = new NullNode();

    public static NullNode getInstance() {
        return instance;
    }

    private NullNode() { /* Cannot be instantiated */ }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return o == this;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
