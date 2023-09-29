package builtins;

import java.util.ArrayList;
import types.*;

public class Range {
    /**
     * Return a range of numbers
     */
    public static Type exec(ArrayList<Type> args) {
        if (args.size() != 2 && args.size() != 3)
            return new Fail("'range' requires 2 or 3 parameters");

        List range = new List();

        double min = ((Num) args.get(0)).getValue();
        double max = ((Num) args.get(1)).getValue();
        double step = 1;

        if (args.size() == 3)
            step = ((Num) args.get(2)).getValue();
        
        for (double i = min; i < max; i += step)
            range.add(new Num(i));
        
        return range;
    }
}