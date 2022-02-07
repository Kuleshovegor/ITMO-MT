package objects.grammar;

import java.util.Objects;

public class Term implements State {
    public static final Term EPS = new Term("EPS");
    public static final Term EOF = new Term("EOF");
    private final String name;

    public Term(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "objects.grammar.Term{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Objects.equals(name, term.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
