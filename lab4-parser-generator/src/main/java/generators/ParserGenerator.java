package generators;

import objects.grammar.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ParserGenerator {
    private final Grammar grammar;
    private final Map<NonTerm, Set<Term>> first = new HashMap<>();
    private final Map<NonTerm, Set<Term>> follow = new HashMap<>();

    public ParserGenerator(Grammar grammar) {
        this.grammar = grammar;
        evalFirst();
        evalFollow();
        checkLL1();
    }

    public void evalFirst() {
        for (NonTerm nonTerm : grammar.getNonTerms()) {
            first.put(nonTerm, new HashSet<>());
        }
        boolean changed = true;
        List<Rule> rules = grammar.getRules();
        while (changed) {
            changed = false;
            for (Rule rule : rules) {
                State state = rule.getFirst();
                if (state instanceof Term) {
                    changed = changed || first.get(rule.getFrom()).add((Term) state);
                } else {
                    Set<Term> t = first.get((NonTerm) state);
                    if (!t.isEmpty()) {
                        changed = changed || first.get(rule.getFrom()).addAll(t);
                    }
                }
            }
        }
    }

    public List<String> getTermNames(Set<Term> set) {
        return set.stream()
                .map(Term::getName)
                .collect(Collectors.toList());
    }

    public String getErrorMessage(Rule rule1, Rule rule2, Set<Term> followFrom) {
        return "Grammar is not LL(1). Wrong rules: " +
                rule1 + " " + rule2 +
                " Follows: " +
                getTermNames(followFrom) + System.lineSeparator() +
                " Firsts: " +
                getTermNames(getFirst(rule1)) + System.lineSeparator() +
                getTermNames(getFirst(rule2)) + System.lineSeparator();
    }

    private void checkLL1() {
        for (ComplexRule complexRule : grammar.getComplexRules()) {
            for (int ind1 = 0; ind1 < complexRule.getRules().size(); ind1++) {
                for (int ind2 = 0; ind2 < ind1; ind2++) {
                    Rule rule1 = complexRule.getRules().get(ind1);
                    Rule rule2 = complexRule.getRules().get(ind2);
                    Set<Term> followFrom = follow.get(complexRule.getFrom());
                    Set<Term> first1 = getFirst(rule1);
                    Set<Term> first2 = getFirst(rule2);
                    if (first1.stream().anyMatch(a -> first2.contains(a) && !a.equals(Term.EPS ))) {
                        throw new IllegalArgumentException(getErrorMessage(rule1, rule2, followFrom));
                    }
                    if (first1.contains(Term.EPS) && followFrom.stream().anyMatch(first2::contains)) {
                        throw new IllegalArgumentException(getErrorMessage(rule1, rule2, followFrom));
                    }
                }
            }
        }
    }

    public void evalFollow() {
        for (NonTerm nonTerm : grammar.getNonTerms()) {
            follow.put(nonTerm, new HashSet<>());
        }
        for (Rule rule : grammar.getRules()) {
            if (rule.getTormList().get(rule.getTormList().size() - 1).equals(Term.EOF)) {
                follow.get(rule.getFrom()).add(Term.EOF);
            }
        }
        boolean changed = true;
        List<Rule> rules = grammar.getRules();
        while (changed) {
            changed = false;
            for (Rule rule : rules) {
                List<State> stateList = rule.getTormList();
                for (int ind = 0; ind < stateList.size(); ind++) {
                    State state = stateList.get(ind);
                    if (state instanceof Term || state instanceof Code) {
                        continue;
                    }
                    int nextInd = ind + 1;
                    while (nextInd < stateList.size() && stateList.get(nextInd) instanceof Code) {
                        nextInd++;
                    }
                    State nextState;

                    if (nextInd == stateList.size()) {
                        nextState = Term.EPS;
                    } else {
                        nextState = stateList.get(nextInd);
                    }

                    Set<Term> firstNext;
                    if (nextState instanceof Term) {
                        firstNext = Set.of((Term) nextState);
                    } else {
                        firstNext = first.get((NonTerm) nextState);
                    }

                    Set<Term> curFollow = follow.get((NonTerm) state);
                    int oldSize = curFollow.size();
                    curFollow.addAll(firstNext);
                    if (firstNext.contains(Term.EPS)) {
                        curFollow.remove(Term.EPS);
                        curFollow.addAll(follow.get(rule.getFrom()));
                    }
                    changed = changed || oldSize != curFollow.size();
                }
            }
        }
    }

    public Set<Term> getFirst(Rule rule) {
        State state = rule.getFirst();
        if (state instanceof Term) {
            return Set.of((Term) state);
        } else {
            return first.get((NonTerm) state);
        }
    }

    private String toUpperCaseFirst(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase(Locale.ROOT) + str.substring(1);
    }

    private String generateResultClass(ComplexRule rule) {
        String values = rule.getSplitReturns()
                .stream()
                .map(a -> "public " + a + ";")
                .collect(Collectors.joining(System.lineSeparator()));
        if (rule.getReturns().isEmpty()) {
            values = "";
        }
        String className = toUpperCaseFirst(rule.getFrom().getName());
        return String.format("""
                             public static class Result%s {
                                public Tree tree;
                                %s
                            }
                            """, className, values);
    }

    private String generateState(State state, int ind) {
        if (state instanceof Term) {
            return String.format("""
                                consume(LexicalAnalyzer.TypeToken.%s);
                                String arg%2$s = lex.getCurToken().getBody();
                                treeList.add(new Tree(lex.getCurToken()));
                                lex.nextToken();
                    """, state.getName(), ind);
        } else if (state instanceof Code) {
            return "            " + ((Code) state).getBody();
        }
        else if (state instanceof NonTerm) {
            return String.format("""
                                Result%s arg%d = %s(%s);
                                treeList.add(arg%2$d.tree);
                    """, toUpperCaseFirst(state.getName()), ind, state.getName(), ((NonTerm) state).getParams());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String generateComplexRule(ComplexRule complexRule) {
        String resultClass = generateResultClass(complexRule);
        String cases = complexRule.getRules()
                .stream()
                .filter(a -> !getFirst(a).contains(Term.EPS))
                .map(this::generateRule)
                .collect(Collectors
                        .joining(System.lineSeparator() + "        "));
        String follows = follow.get(complexRule.getFrom())
                .stream()
                .map(Term::getName)
                .collect(Collectors.joining(", "));
        String caseFollows = String.format("""
                case %s -> treeList.add(new Tree("EPS"));
                """, follows);
        if (complexRule.getRules().stream().noneMatch(a -> getFirst(a).contains(Term.EPS))) {
            caseFollows = "";
        }
        String expected = first.get(complexRule.getFrom())
                .stream()
                .map(Term::getName)
                .collect(Collectors.joining(", "));
        if (!caseFollows.equals("")) {
            expected += ", " + follows;
        }
        return String.format("""
                %1$s
                
                public Result%2$s %3$s(%4$s) throws LexicalAnalyzerException, ParserException {
                    List<Tree> treeList = new ArrayList<>();
                    Result%2$s result = new Result%2$s();
                    switch (lex.getCurToken().getTypeToken()) {
                        %5$s
                        %6$s
                        default -> throw new ParserException("Unexpected token:"
                            + lex.getCurToken()
                            + " Expected: %7$s", lex.getCurPos());
                    }
                    result.tree = new Tree("%3$s", treeList);
                    return result;
                }
                """,
                resultClass,
                toUpperCaseFirst(complexRule.getFrom().getName()),
                complexRule.getFrom().getName(),
                complexRule.getFrom().getParams(),
                cases,
                caseFollows,
                expected
                );
    }

    public String generateRule(Rule rule) {
        Set<Term> terms = getFirst(rule);
        String cases = terms.stream()
                .map(a -> a.equals(Term.EPS) ? "" : a.getName())
                .collect(Collectors.joining(", "));
        List<String> strings = new ArrayList<>();
        int realInd = 0;
        for (int ind = 0; ind < rule.getTormList().size(); ind++) {
            State state = rule.getTormList().get(ind);
            strings.add(generateState(state, realInd));
            if (!(state instanceof Code)) {
                realInd++;
            }
        }
        String rrule = strings.stream()
                .collect(Collectors.joining(System.lineSeparator()));
        return String.format("""
                case %s -> {
                %s
                        }
                """, cases, rrule);
    }

    public String generate() {
        String rules = grammar.getComplexRules().stream()
                .map(this::generateComplexRule)
                .collect(Collectors.joining(System.lineSeparator()));
        return String.format("""
                package %3$s;
                               
                import java.util.ArrayList;
                import java.util.List;
                import exceptions.ParserException;
                import exceptions.LexicalAnalyzerException;
                %2$s
                               
                public class Parser {
                    private final LexicalAnalyzer lex;
                    
                    public Parser(LexicalAnalyzer lex) {
                        this.lex = lex;
                    }
                    
                    private void consume(LexicalAnalyzer.TypeToken type) throws ParserException {
                          if (type != lex.getCurToken().getTypeToken()) {
                               throw new ParserException("expected: " + type +
                                " but found: " + lex.getCurToken(), lex.getCurPos());
                          }
                    }
                %s
                }
                """, rules, grammar.getImports(), grammar.getName());
    }

    public void generateToFile(Path path) throws IOException {
        path.toFile().mkdirs();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path.resolve("Parser.java"))) {
            bufferedWriter.write(generate());
        }
    }

}