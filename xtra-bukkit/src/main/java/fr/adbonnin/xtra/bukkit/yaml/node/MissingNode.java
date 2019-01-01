package fr.adbonnin.xtra.bukkit.yaml.node;

public class MissingNode extends ValueNode {

    private final static MissingNode instance = new MissingNode();

    public static MissingNode getInstance() {
        return instance;
    }

    private MissingNode() { /* Cannot be instantiated */ }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
