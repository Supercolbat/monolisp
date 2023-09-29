package builtins;

import java.util.ArrayList;
import models.Node;
import models.Frames;
import types.Type;
import types.Fail;

public class Builtins {
    public static String[] KEYWORDS = {
        // Functions
        "print", "list", "range",
        "setvar",

        // Operators
        "+", "-", "*", "/", "**",
        "==", "!=", "<", "<=", ">", ">="
    };

    private Frames frames;

    public Builtins(Frames frames) {
        this.frames = frames;
    }

    public Type execute(String keyword, ArrayList<Type> args) {
        switch (keyword) {
            // Functions
            case "print":
                return Print.exec(args);

            case "println":
                return PrintLn.exec(args);

            case "input":
                return Input.exec(args);

            case "list":
                return ListFn.exec(args);

            case "range":
                return Range.exec(args);

            case "num":
                return ToNum.exec(args);

            case "str":
                return ToStr.exec(args);

            // Operators
            case "+":
                return OpAdd.exec(args);

            case "-":
                return OpSubtract.exec(args);
                
            case "*":
                return OpMultiply.exec(args);
                
            case "**":
                return OpPower.exec(args);
                
            case "/":
                return OpDivide.exec(args);
                
            case "//":
                return OpFloorDivide.exec(args);
                
            case "%":
                return OpMod.exec(args);
                
            case "<":
                return OpLessThan.exec(args);
                
            case "<=":
                return OpLessEqualTo.exec(args);
                
            case ">":
                return OpGreaterThan.exec(args);
                
            case ">=":
                return OpGreaterEqualTo.exec(args);
                
            case "==":
                return OpEqualTo.exec(args);
                
            case "!=":
                return OpNotEqualTo.exec(args);

            default:
                if (frames.currentFrame().hasVariable(keyword))
                    return frames.currentFrame().getVariable(keyword);

                return new Fail(String.format("Variable '%s' not found", keyword));
        }
        // return new Fail(String.format("Cannot execute '%s'", keyword));
    }

    // Getters
    public boolean isKeyword(String keyword) {
        for (String word : KEYWORDS) {
            if (word.equals(keyword))
                return true;
        }
        
        return false;
    };
}