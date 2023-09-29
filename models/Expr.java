package models;

import types.Type;
import java.util.ArrayList;

public class Expr extends Node {
    private ArrayList<Node> children;

    // Constructor
    public Expr() {
        this.children = new ArrayList<Node>();
    }

    public Expr(ArrayList<Node> children) {
        this.children = children;
    }

    // Getters
    public Node getHead() {
        if (children.size() > 0)
            return children.get(0);
        return null;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public ArrayList<Node> getAllChildren() {
        return children;
    }

    public ArrayList<Node> getChildren() {
        ArrayList<Node> c = new ArrayList<Node>();

        for (int i = 1; i < children.size(); i++)
            c.add(children.get(i));

        return c;
    }
}