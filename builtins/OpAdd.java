package builtins;

import java.util.ArrayList;
import types.*;

public class OpAdd {
    public static Type exec(ArrayList<Type> args) {
        if (args.size() == 0)
            return new Fail("'+' requires at least 1 argument");

        if (args.get(0) instanceof Str)
            return addStrings(args);
        
        return addNumbers(args);
    }

    private static Type addNumbers(ArrayList<Type> args) {
        double total = 0;

        for (Type n : args)
            total += ((Num) n).getValue();
        
        return new Num(total);
    }

    private static Type addStrings(ArrayList<Type> args) {
        String result = "";

        for (Type n : args)
            result += n.toString();
        
        return new Str(result);
    }
}