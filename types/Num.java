package types;

public class Num extends Type {
    double value;

    public Num(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        // Integers do not have decimals
        if (value % 1 == 0)
            return "" + (int) value;

        // Doubles have decimals
        return "" + value;
    }
}