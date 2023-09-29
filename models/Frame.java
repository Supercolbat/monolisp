package models;

import java.util.Map;
import java.util.HashMap;
import types.Type;
import types.None;

public class Frame {
    // Variables
    private HashMap<String, Type> inherited;
    private HashMap<String, Type> locals;

    // Return value
    // private Type returnValue;

    public Frame() {
        inherited = new HashMap<String, Type>();
        locals = new HashMap<String, Type>();
    }

    public Frame(HashMap<String, Type> variables) {
        inherited = new HashMap<String, Type>();
        locals = new HashMap<String, Type>();

        // Inherit variables from upper frames
        inherited.putAll(variables);
        // System.out.println("inherited: " + inherited);
        // System.out.println("locals: " + locals);
        // System.out.println("all: " + getVariables());
    }

    /**
     * Update variable states from lower frames
     */
    public void extendVariables(Frame frame) {
        HashMap<String, Type> variables = frame.getAllInherited();
        // System.out.println("extending " + frame + " with " + variables);
        for (Map.Entry<String, Type> entry : variables.entrySet()) {
            String name = entry.getKey();
            Type value = entry.getValue();

            // System.out.println(name + " inherited? "  +hasInherited(name));
            if (hasInherited(name))
                setInherited(name, value);
            else
                setLocal(name, value);
        }
    }

    /**
     * Check if a variable exists or not
     * @return success
     */
    public Type getVariable(String key) {
        return getVariables().getOrDefault(key, new None());
    }

    /**
     * Check if a variable exists or not
     * @return success
     */
    public boolean hasVariable(String key) {
        return getVariables().containsKey(key);
    }

    /**
     * Check if a inherited variable exists or not
     * @return success
     */
    public boolean hasInherited(String key) {
        return inherited.containsKey(key);
    }

    /**
     * Check if an local variable exists or not
     * @return success
     */
    public boolean hasLocal(String key) {
        return locals.containsKey(key);
    }

    // Getters/Setters

    /**
     * @returns All inherited variables
     */
    public HashMap<String, Type> getAllInherited() {
        return inherited;
    }

    /**
     * Gets an inherited variable in the frame or returns None
     * @returns variable value or None
     */
    public Type getInherited(String key) {
        return inherited.getOrDefault(key, new None());
    }

    /**
     * Sets an inherited variable in the frame
     */
    public void setInherited(String key, Type value) {
        inherited.put(key, value);
    }

    /**
     * @returns All local variables
     */
    public HashMap<String, Type> getAllLocal() {
        return locals;
    }

    /**
     * Gets a local variable in the frame or returns None
     * @returns variable value or None
     */
    public Type getLocal(String key) {
        return locals.getOrDefault(key, new None());
    }

    /**
     * Sets a variable in the frame
     */
    public void setLocal(String key, Type value) {
        locals.put(key, value);
    }

    /**
     * @returns The variables HashMap
     */
    public HashMap<String, Type> getVariables() {
        HashMap<String, Type> allVariables = new HashMap<String, Type>();
        allVariables.putAll(inherited);
        allVariables.putAll(locals);

        return allVariables;
    }
}