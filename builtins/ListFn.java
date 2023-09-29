package builtins;

import java.util.ArrayList;
import types.*;

public class ListFn {
    /**
     * Returns a list with the arguments
     */
    public static Type exec(ArrayList<Type> args) {
        return new List(args);
    }
}