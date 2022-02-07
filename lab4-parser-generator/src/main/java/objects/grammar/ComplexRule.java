package objects.grammar;

import java.util.List;

public class ComplexRule {
    private final List<Rule> rules;
    private final NonTerm from;
    private final String arguments;
    private final List<String> splitReturns;
    private final String returns;


    public ComplexRule( NonTerm from, List<Rule> rules, String arguments, String returns) {
        this.rules = rules;
        this.from = from;
        this.arguments = arguments;
        this.returns = returns.replace(';', ',');
        this.splitReturns = List.of(returns.split(";"));
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public NonTerm getFrom() {
        return from;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public String getArguments() {
        return arguments;
    }

    public List<String> getSplitReturns() {
        return splitReturns;
    }

    public String getReturns() {
        return returns;
    }

    @Override
    public String toString() {
        return "ComplexRule{" +
                "rules=" + rules +
                ", from=" + from +
                ", arguments='" + arguments + '\'' +
                ", splitReturns=" + splitReturns +
                ", returns='" + returns + '\'' +
                '}';
    }
}
