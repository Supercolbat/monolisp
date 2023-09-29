package builtins;

import java.util.ArrayList;
import types.*;

public class ToStr {
    public static Type exec(ArrayList<Type> args) {
        if (args.size() != 1)
            return new Fail("'str' requires 1 argument");

        if (!(args.get(0) instanceof Str))
            return new Fail("'str' expects a string");
        
        return new Str(
            args.get(0).toString()
        );
    }
}