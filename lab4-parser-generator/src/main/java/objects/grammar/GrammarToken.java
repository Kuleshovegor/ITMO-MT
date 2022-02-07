package objects.grammar;

import java.util.Objects;

public class GrammarToken {
    private final Term term;
    private final String regexp;

    public GrammarToken(Term term, String regexp) {
        this.term = term;
        this.regexp = regexp;
    }

    public Term getTerm() {
        return term;
    }

    public String getRegexp() {
        return regexp;
    }

    @Override
    public String toString() {
        return "Token{" +
                "term=" + term +
                ", regexp=" + regexp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrammarToken grammarToken = (GrammarToken) o;
        return Objects.equals(term, grammarToken.term) && Objects.equals(regexp, grammarToken.regexp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, regexp);
    }
}
