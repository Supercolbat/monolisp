package builtins;

import types.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static Type exec(ArrayList<Type> args) {
        Print.exec(args);

        // No return value
        return new Str(scanner.nextLine());
    }
}