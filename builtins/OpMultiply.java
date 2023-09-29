package builtins;

import java.util.ArrayList;
import types.*;

public class OpMultiply {
    public static Type exec(ArrayList<Type> args) {
        if (args.size() == 0)
            return new Fail("'*' requires at least 1 argument");

        double total = 1;

        for (Type n : args)
            total *= ((Num) n).getValue();
        
        return new Num(total);
    }
}