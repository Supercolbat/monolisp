package types;

public class Str extends Type {
    String value;

    public Str(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}