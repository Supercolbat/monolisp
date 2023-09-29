import models.Node;
import models.Atom;
import models.Expr;
import types.*;

public class Parser {
    private final String WHITESPACE = " \t\n\r";

    private AST ast;
    private String source;

    private Node root;

    private int ptr;

    public Parser(String source) {
        ast = new AST();
        this.source = source;
        this.root = new Expr();
    }

    public AST parse() throws Exception {
        consumeWhitespace();

        while (!isEOF()) {
            ast.add(parseExpr());
            consumeWhitespace();
        }
        
        return ast;
    }

    public Expr parseExpr() throws Exception {
        Expr node = new Expr();

        expectConsume('(');
        consumeWhitespace();

        while (peekChar() != ')') {
            if (peekChar() == '(') {
                node.addChild(parseExpr());
                consumeWhitespace();
                continue;
            }

            if (peekChar() == '"') {
                String token = consumeString();
                node.addChild(
                    new Atom(
                        new Str(token)
                    )
                );
                consumeWhitespace();
                continue;
            }

            String token = consumeWord();
            node.addChild(new Atom(intoType(token)));

            consumeWhitespace();

            // DEBUG:
            // System.out.println(source.substring(ptr));
        }

        consumeWhitespace();
        expectConsume(')');
        
        return node;
    }

    // Transform functions

    private Type intoType(String token) {
        // Booleans
        if (token.equals("true"))
            return new Bool(true);

        if (token.equals("false"))
            return new Bool(false);
        
        // None
        if (token.equals("none"))
            return new None();
        
        // String - should be unused
        if (token.charAt(0) == '"' && token.charAt(token.length() - 1) == '"')
            return new Str(token.substring(1, token.length() - 1));
        
        // Number
        if (isNumber(token))
            return new Num(Double.parseDouble(token));

        return new Keyword(token);
    }

    // Parsing functions

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isEOF() {
        return ptr >= source.length() - 1;
    }

    private char peekChar() {
        return source.charAt(ptr);
    }

    private String consumeString() throws Exception {
        expectConsume('"');

        String str = "";

        while (peekChar() != '"') {
            char c = consumeChar();
            
            // Escape the next character in string
            if (c == '\\') {
                char nextChar = consumeChar();

                if (nextChar == 'n')
                    str += '\n';
                else if (nextChar == 't')
                    str += '\t';
                else if (nextChar == 'r')
                    str += '\r';
                else if (nextChar == 'r')
                    str += '\r';
                else if (nextChar == '0') // ansi escape codes
                    str += "\\0";
                else
                    str += nextChar;
                
                continue;
            }

            str += c;
        }

        expectConsume('"');

        return str;
    }

    private String consumeWord() {
        String str = "";
        while (!isEOF() && peekChar() != ' ' && peekChar() != ')') {
            str += consumeChar();
        }
        return str;
    }

    private void consumeWhitespace() {
        while (!isEOF() && WHITESPACE.indexOf(peekChar()) != -1) {
            consumeChar();
        }
    }

    private void expectConsume(char c) throws Exception {
        if (consumeChar() != c)
            throw new Exception(
                String.format(
                    "Expected '%s' but got '%s'",
                    c, source.charAt(ptr - 1)
                )
            );
    }

    private char consumeChar() {
        return source.charAt(ptr++);
    }
}