package objects.grammar;

import java.util.List;
import java.util.Objects;

public class Rule {
    private final NonTerm from;
    private final List<State> stateList;

    public Rule(NonTerm from, List<State> stateList) {
        this.from = from;
        this.stateList = stateList;
    }

    public State getFirst() {
        int ind = 0;
        while (ind < stateList.size() && stateList.get(ind) instanceof Code) {
            ind++;
        }
        if (ind == stateList.size()) {
            return Term.EPS;
        }
        return stateList.get(ind);
    }

    public NonTerm getFrom() {
        return from;
    }

    public List<State> getTormList() {
        return stateList;
    }

    @Override
    public String toString() {
        return "objects.grammar.Rule{" +
                "from=" + from +
                ", stateList=" + stateList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(from, rule.from) && Objects.equals(stateList, rule.stateList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, stateList);
    }
}
