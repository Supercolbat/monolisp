package builtins;

import java.util.ArrayList;
import types.*;

public class OpDivide {
    public static Type exec(ArrayList<Type> args) {
        if (args.size() == 0)
            return new Fail("'/' requires at least 1 argument");

        double total = ((Num) args.get(0)).getValue();

        for (int i = 1; i < args.size(); i++)
            total /= ((Num) args.get(i)).getValue();
        
        return new Num(total);
    }
}