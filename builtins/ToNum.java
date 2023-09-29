package builtins;

import java.util.ArrayList;
import java.lang.NumberFormatException;
import types.*;

public class ToNum {
    public static Type exec(ArrayList<Type> args) {
        if (args.size() != 1)
            return new Fail("'num' requires 1 argument");

        if (!(args.get(0) instanceof Str))
            return new Fail("'num' expects a string");
        
        String arg = ((Str) args.get(0)).getValue();

        try {
            return new Num(
                Double.parseDouble(arg)
            );
        } catch (NumberFormatException e) {
            return new Fail("Cannot convert '" +arg + "' into a num");
        }
    }
}