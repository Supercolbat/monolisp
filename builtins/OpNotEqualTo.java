package builtins;

import java.util.ArrayList;
import types.*;

public class OpNotEqualTo {
    public static Type exec(ArrayList<Type> args) {
        if (args.size() == 0)
            return new Fail("'!=' requires at least 1 argument");

        for (int i = 1; i < args.size(); i++) {
            double a = ((Num) args.get(i - 1)).getValue();
            double b = ((Num) args.get(i)).getValue();
            if (!(a != b))
                return new Bool(false);
        }
        
        return new Bool(true);
    }
}