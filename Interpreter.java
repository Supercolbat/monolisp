import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import models.Node;
import models.Atom;
import models.Expr;
import models.Frame;
import models.Frames;
import types.*;
import builtins.Builtins;

public class Interpreter {
    private Frames frames;
    private Builtins builtins;

    public Interpreter() {
        // Initialize global frame
        frames = new Frames();
        builtins = new Builtins(frames);
    }

    public Type evalAST(AST ast) {
        for (Node node : ast.getChildren()) {
            Type result = eval(node);

            if (result instanceof Fail)
                return result;
        }

        return null;
    }

    // Evaluate method
    public Type eval(Node node) {
        // Return value if the node is an Atom
        if (node instanceof Atom) {
            Atom atom = (Atom) node;
            Type value = atom.getValue();
            
            if (value instanceof Keyword) {
                String name = ((Keyword) value).getKeyword();

                Frame frame = frames.currentFrame();

                if (frame.hasVariable(name))
                    return frame.getVariable(name);
                
                return new Fail("Variable '" + name + "' not defined");
            }

            return value;
        }

        // Get basic detail from the assumed Expr
        Expr expr = (Expr) node;
        Node head = expr.getHead();
        ArrayList<Node> children = expr.getChildren();

        // Check that the head node is an Atom
        if (!(head instanceof Atom) || !(nodeGetValue(head) instanceof Keyword))
            return new Fail(String.format("Expected keyword, found '%s'", head.toString()));
        
        // Convert the head node into a String
        Keyword headKeyword = (Keyword) nodeGetValue(head);
        String keyword = headKeyword.toString();

        // Check if the expression is a control flow
        if (keyword.equals("while")) {
            frames.newFrame();

            Type result = performWhile(children);

            frames.dropFrame();

            return result;
        }

        else if (keyword.equals("for")) {
            frames.newFrame();

            Type result = performFor(children);

            frames.dropFrame();

            return result;
        }

        else if (keyword.equals("if")) {
            frames.newFrame();

            Type result = performIf(children);

            frames.dropFrame();

            return result;
        }

        // Check if the expression is setvar
        else if (keyword.equals("setvar")) {
            return performSetVar(children);
        }

        // Check if the expression is statement
        else if (keyword.equals("stmt")) {
            return performStmt(children);
        }

        // Parse children into Types
        ArrayList<Type> args = new ArrayList<Type>();

        for (Node child : children) {
            Type result = eval(child);

            if (result instanceof Fail)
                return result;

            args.add(result);
        }
        
        // Execute function or operator
        return builtins.execute(keyword, args);
    }

    // Helper functions

    private Type nodeGetValue(Node node) {
        return ((Atom) node).getValue();
    }

    /**
     * Control flow
     */

    public Type performWhile(ArrayList<Node> args) {
        if (args.size() == 0)
            return new Fail("'while' requires at least 1 parameter");

        while (true) {
            Type condition = eval(args.get(0));

            // Propagate error
            if (condition instanceof Fail)
                return condition;
            
            // Break if condition is false
            if (!((Bool) condition).getValue())
                break;

            // Evaluate children
            for (int i = 1; i < args.size(); i++) {
                Type result = eval(args.get(i));
                
                // Propagate error
                if (result instanceof Fail)
                    return result;
            }
        }

        return new None();
    }

    public Type performFor(ArrayList<Node> args) {
        if (args.size() < 2)
            return new Fail("'for' requires at least 2 parameters");

        String elementVar = ((Keyword) ((Atom) args.get(0)).getValue()).getKeyword();
        Type iterableResult = eval(args.get(1));

        // Propagate error
        if (iterableResult instanceof Fail)
            return iterableResult;

        ArrayList<Type> iterable = ((List) iterableResult).getValue();

        Frame frame = frames.currentFrame();

        for (Type value : iterable) {
            // Set variable
            if (frame.hasInherited(elementVar))
                frame.setInherited(elementVar, value);
            else
                frame.setLocal(elementVar, value);

            // Evaluate children
            for (int i = 2; i < args.size(); i++) {
                Type result = eval(args.get(i));
                
                // Propagate error
                if (result instanceof Fail)
                    return result;
            }
        }

        return new None();
    }

    public Type performIf(ArrayList<Node> args) {
        if (args.size() != 2 && args.size() != 3)
            return new Fail("'if' requires 2 parameters");

        Type condition = eval(args.get(0));

        if (condition instanceof Fail)
            return condition;

        // Run first expression if condition is true
        if (((Bool) condition).getValue())
            return eval(args.get(1));

        // Run the second expression otherwise
        if (args.size() == 3)
            return eval(args.get(2));
        
        return new None();
    }

    // Setvar
    public Type performSetVar(ArrayList<Node> args) {
        if (args.size() != 2)
            return new Fail("'setvar' requires 2 parameters");

        String name = ((Keyword) ((Atom) args.get(0)).getValue()).getKeyword();
        Type value = eval(args.get(1));

        // Propagate error
        if (value instanceof Fail)
            return value;

        Frame frame = frames.currentFrame();

        if (frame.hasInherited(name))
            frame.setInherited(name, value);
        else
            frame.setLocal(name, value);
        
        return new None();
    }

    // Statement
    public Type performStmt(ArrayList<Node> args) {
        Type result = new None();
        for (Node arg : args) {
            result = eval(arg);

            if (result instanceof Fail)
                return result;
        }
        
        return result;
    }
}
