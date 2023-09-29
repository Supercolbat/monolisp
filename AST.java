import java.util.ArrayList;
import models.Node;

public class AST {
    private ArrayList<Node> nodes;

    // Constructor

    public AST() {
        nodes = new ArrayList<Node>();
    }

    // Getter

    public ArrayList<Node> getChildren() {
        return nodes;
    }

    // Setter

    public void add(Node node) {
        nodes.add(node);
    }
}