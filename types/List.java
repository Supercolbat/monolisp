package types;

import java.util.ArrayList;

public class List extends Type {
    ArrayList<Type> value;

    public List() {
        this.value = new ArrayList<Type>();
    }

    public List(ArrayList<Type> value) {
        this.value = value;
    }

    public void add(Type value) {
        this.value.add(value);
    }

    public ArrayList<Type> getValue() {
        return value;
    }

    public String toString() {
        String str = "( ";

        for (Type val : value)
            str += val.toString() + " ";
        
        return str + ")";
    }
}