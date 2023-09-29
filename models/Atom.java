package models;

import types.Type;

public class Atom extends Node {
    private Type value;

    // Constructor
    public Atom(Type value) {
        this.value = value;
    }

    // Getters
    public Type getValue() {
        return value;
    }
    
    public String toString() {
        return value.getClass().getSimpleName() + "(" + value.toString() + ")";
    }
}