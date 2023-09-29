package types;

public class Fail extends Type {
    String fail;

    public Fail(String fail) {
        this.fail = fail;
    }

    public String getFail() {
        return fail;
    }

    public String toString() {
        return fail;
    }
}