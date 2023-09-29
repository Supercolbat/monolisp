import types.*;
import models.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MonoLisp {
    public static void main(String[] args) {
        // System.out.println("Monolisp demo\n");

        String code = readFile("program.mono");

        // System.out.println("Code:\n" + code + "\n");

        Interpreter interpreter = new Interpreter();
        Parser parser = new Parser(code);
        AST ast;

        try {
            ast = parser.parse();

            // System.out.println("AST Tree:");
            // printAST(ast);

            // System.out.println("\nExecution:");
            try {
                Type result = interpreter.evalAST(ast);

                if (result instanceof Fail) {
                    System.out.println("[!] RUNTIME ERROR:");
                    System.out.println(result.toString());
                }
            } catch (Exception e) {
                System.out.println("[!] JAVA RUNTIME ERROR:");
                e.printStackTrace(System.out);
            }

        } catch (Exception e) {
            System.out.println("[!] PARSE ERROR:");
            e.printStackTrace(System.out);
        }
    }

    private static void printAST(AST ast) {
        for (Node child : ast.getChildren()) {
            printNode(child, 0);
        }
    }

    private static void printNode(Node node, int indent) {
        indentBy(indent);

        if (node instanceof Atom) {
            Atom atom = (Atom) node;
            System.out.println(atom.toString());
            return;
        }

        Expr expr = (Expr) node;
        
        System.out.println(expr.getHead());
        
        for (Node child : expr.getChildren()) {
            printNode(child, indent + 2);
        }
    }

    private static void indentBy(int indent) {
        for (int i = 0; i < indent; i++) System.out.print(" ");
    }

    private static String readFile(String path) {
        String data = "";

        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            
            data += reader.nextLine();

            while (reader.hasNextLine()) {
                data += "\n" + reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }
}
