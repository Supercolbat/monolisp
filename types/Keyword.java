package types;

public class Keyword extends Type {
    String keyword;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public String toString() {
        return keyword;
    }
}