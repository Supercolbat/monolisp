package builtins;

import types.*;
import java.util.ArrayList;

public class Print {
    /**
     * Join arguments by a space and print with a newline
     */
    public static Type exec(ArrayList<Type> args) {
        // Collect children nodes into strings
        ArrayList<String> string = new ArrayList<String>();
        for (Type arg : args) {
            string.add(arg.toString());
        }

        // Print out the space-separated strings
        System.out.print(String.join(" ", string));

        // No return value
        return new None();
    }
}