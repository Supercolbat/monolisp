package types;

import models.Node;

public class Bool extends Type {
    boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public String toString() {
        return value ? "true" : "false";
    }
}