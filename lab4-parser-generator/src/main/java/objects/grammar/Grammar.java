package objects.grammar;

import java.util.*;

public class Grammar {
    private final String name;
    private final List<Rule> rules = new ArrayList<>();
    private final List<ComplexRule> complexRules = new ArrayList<>();
    private final Map<Term, String> tokens = new HashMap<>();
    private final Set<NonTerm> nonTerms = new HashSet<>();
    private final List<Term> terms = new ArrayList<>();
    private String imports = "";

    public Grammar(String name) {
        this.name = name;
        terms.add(Term.EOF);
        terms.add(Term.EPS);
    }

    public String getName() {
        return name;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getImports() {
        return imports;
    }

    public void addComplexRule(ComplexRule complexRule) {
        if (!nonTerms.add(complexRule.getFrom())) {
            throw new IllegalArgumentException("There are the same rules for non terminal " + complexRule.getFrom().getName());
        }
        complexRules.add(complexRule);
        rules.addAll(complexRule.getRules());
    }

    public List<ComplexRule> getComplexRules() {
        return complexRules;
    }

    public Set<NonTerm> getNonTerms() {
        return nonTerms;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Map<Term, String> getTokens() {
        return tokens;
    }

    public void addToken(GrammarToken grammarToken) {
        terms.add(grammarToken.getTerm());
        tokens.put(grammarToken.getTerm(), grammarToken.getRegexp());
    }

    public List<Term> getTerms() {
        return terms;
    }

    @Override
    public String toString() {
        return "gens.objects.grammar.Grammar{" +
                "rules=" + rules +
                ", tokens=" + tokens +
                '}';
    }
}
