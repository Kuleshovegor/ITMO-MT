package lab3;

import exceptions.EvaluationException;
import exceptions.LexicalAnalyzerException;
import exceptions.ParserException;
import equations.LexicalAnalyzer;
import equations.Parser;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class EquationsTest {
    private static final Map<String, Double> EMPTY_MAP = new HashMap<>();

    private static Stream<Arguments> positiveNumbers() {
        return Stream.of(
                Arguments.of("0", 0),
                Arguments.of("1", 1),
                Arguments.of("123", 123),
                Arguments.of("1.0", 1),
                Arguments.of("1.0123", 1.0123),
                Arguments.of("1.234E2", 123.4),
                Arguments.of("123.4E-2", 1.234),
                Arguments.of(String.valueOf(Double.MAX_VALUE), Double.MAX_VALUE)
        );
    }

    private static Stream<Arguments> positiveNumbersErrors() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("-1"),
                Arguments.of("лол"),
                Arguments.of("sf123"),
                Arguments.of("1.0123.1", 1.0123),
                Arguments.of(String.valueOf(-Double.MAX_VALUE))
        );
    }

    private static Stream<Arguments> variables() {
        return Stream.of(
                Arguments.of("a"),
                Arguments.of("A"),
                Arguments.of("b"),
                Arguments.of("b2"),
                Arguments.of("b1_13e"),
                Arguments.of("OK_VAR")
        );
    }

    private static Stream<Arguments> variablesError() {
        return Stream.of(
                Arguments.of("_a"),
                Arguments.of(""),
                Arguments.of("...!"),
                Arguments.of("_"),
                Arguments.of("1"),
                Arguments.of("русская_переменная"),
                Arguments.of("a a")
        );
    }

    private static Stream<Arguments> atoms() {
        return Stream.of(
                Arguments.of("0", EMPTY_MAP, 0),
                Arguments.of("1", EMPTY_MAP, 1),
                Arguments.of(String.valueOf(Double.MAX_VALUE), EMPTY_MAP, Double.MAX_VALUE),
                Arguments.of("a", Map.of("a", 1.0), 1),
                Arguments.of("b", Map.of("b", 2.0), 2),
                Arguments.of("abc", Map.of("abc", Double.MAX_VALUE), Double.MAX_VALUE),
                Arguments.of("(a+1)", Map.of("a", 1.5), 2.5),
                Arguments.of("(  a +  1  )", Map.of("a", 1.5), 2.5)
        );
    }

    private static Stream<Arguments> atomsError() {
        return Stream.of(
                Arguments.of("a", Map.of("b", 1)),
                Arguments.of("a", EMPTY_MAP),
                Arguments.of("(  2 +  1  ", EMPTY_MAP),
                Arguments.of("  2 +  1  )", EMPTY_MAP),
                Arguments.of(" )", EMPTY_MAP),
                Arguments.of("( )", EMPTY_MAP)
        );
    }

    private static Stream<Arguments> unaryOperations() {
        return Stream.of(
                Arguments.of("0", EMPTY_MAP, 0),
                Arguments.of("-1", EMPTY_MAP, -1),
                Arguments.of("a", Map.of("a", 1.0), 1),
                Arguments.of("-b", Map.of("b", 2.0), -2),
                Arguments.of("-b", Map.of("b", -2.0), 2),
                Arguments.of("abc", Map.of("abc", Double.MAX_VALUE), Double.MAX_VALUE),
                Arguments.of("(a+1)", Map.of("a", 1.0), 2),
                Arguments.of("(  a +  1  )", Map.of("a", 1.0), 2)
        );
    }

    private static Stream<Arguments> unaryOperationsError() {
        return Stream.of(
                Arguments.of("--2")
        );
    }

    private static Stream<Arguments> terms() {
        return Stream.of(
                Arguments.of("0*1;", EMPTY_MAP, 0),
                Arguments.of("123*113;", EMPTY_MAP, 13899),
                Arguments.of("-1*1;", EMPTY_MAP, -1),
                Arguments.of("5*-4;", EMPTY_MAP, -20),
                Arguments.of("-5*-4;", EMPTY_MAP, 20),
                Arguments.of("0/1;", EMPTY_MAP, 0),
                Arguments.of("8/2;", EMPTY_MAP, 4),
                Arguments.of("7/2;", EMPTY_MAP, 3.5),
                Arguments.of("1/0;", EMPTY_MAP, Double.POSITIVE_INFINITY),
                Arguments.of("5/-1;", EMPTY_MAP, -5),
                Arguments.of("-5/1;", EMPTY_MAP, -5),
                Arguments.of("-5/-1;", EMPTY_MAP, 5),
                Arguments.of("5*2/1;", EMPTY_MAP, 10),
                Arguments.of("5*2*13;", EMPTY_MAP, 130),
                Arguments.of("5*-2*13;", EMPTY_MAP, -130),
                Arguments.of("5/-2/13;", EMPTY_MAP, -0.19230769230769232),
                Arguments.of("5*a*13;", Map.of("a", -2.0), -130),
                Arguments.of("5/-b/13;", Map.of("b", 2.0), -0.19230769230769232)
        );
    }

    private static Stream<Arguments> termsError() {
        return Stream.of(
                Arguments.of("*"),
                Arguments.of(" /"),
                Arguments.of("1*"),
                Arguments.of("*1"),
                Arguments.of("/1"),
                Arguments.of("1/"),
                Arguments.of("1**1")
        );
    }

    private static Stream<Arguments> expressions() {
        return Stream.of(
                Arguments.of("0+1;", EMPTY_MAP, 1),
                Arguments.of("4+5;", EMPTY_MAP, 9),
                Arguments.of("20+13;", EMPTY_MAP, 33),
                Arguments.of("0+-1;", EMPTY_MAP, -1),
                Arguments.of("-1+-1;", EMPTY_MAP, -2),
                Arguments.of("-1-1;", EMPTY_MAP, -2),
                Arguments.of("-1+-1;", EMPTY_MAP, -2),
                Arguments.of("-1+(-2 * 3);", EMPTY_MAP, -7),
                Arguments.of("(-1+-2) * 3;", EMPTY_MAP, -9),
                Arguments.of("-1+(-3 / 1);", EMPTY_MAP, -4),
                Arguments.of("(-1+-2) / 3;", EMPTY_MAP, -1),
                Arguments.of("-1+-2 * 3 - 32 + 23 -234 *24 / 2;", EMPTY_MAP, -2824),
                Arguments.of("-1+-a2 * 3 - (dfs) + 23 -234 *24 / 2;", Map.of(
                        "dfs", 32.0,
                        "a2", 2.0
                ), -2824)
        );
    }

    private static Stream<Arguments> expressionsError() {
        return Stream.of(
                Arguments.of("+1", EMPTY_MAP, 1),
                Arguments.of("1+", EMPTY_MAP, 1),
                Arguments.of("1-", EMPTY_MAP, 1),
                Arguments.of("1--", EMPTY_MAP, 1)
        );
    }

    private static Stream<Arguments> equations() {
        return Stream.of(
                Arguments.of("""
                        1;
                        """, List.of(
                        new Pair<>(null, 1.0)
                )),
                Arguments.of("""
                        1 + 2;
                        """, List.of(
                        new Pair<>(null, 3.0)
                )),
                Arguments.of("""
                        a = 1;
                        """, List.of(
                        new Pair<>("a", 1.0)
                )),
                Arguments.of("""
                        a = 1;
                        a = a + 1;
                        """, List.of(
                        new Pair<>("a", 1.0),
                        new Pair<>("a", 2.0)
                )),
                Arguments.of("""
                        a = 1;
                        b = a + 3;
                        c = a + b * (b - 3);
                                                    
                        a = 3;
                        c = a + b * (b - 3);
                        """, List.of(
                        new Pair<>("a", 1.0),
                        new Pair<>("b", 4.0),
                        new Pair<>("c", 5.0),
                        new Pair<>("a", 3.0),
                        new Pair<>("c", 7.0)
                ))
        );
    }

    private static Stream<Arguments> equationsError() {
        return Stream.of(Arguments.of("""
                a = b + 2;
                """, List.of(
                new Pair<>(null, 1.0)
        )));
    }

    public static Parser getParser(String string) throws exceptions.LexicalAnalyzerException {
        LexicalAnalyzer lexer = new LexicalAnalyzer(string);
        return new Parser(lexer);
    }

    @ParameterizedTest
    @MethodSource("positiveNumbers")
    public void testPositiveNumbers(String string, double expected) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(expected, parser.positiveDoubleNumber().result);
    }

    @ParameterizedTest
    @MethodSource("positiveNumbersErrors")
    public void testPositiveNumbersErrors(String string) {
        try {
            Parser parser = getParser(string);
            parser.positiveDoubleNumber();
        } catch (ParserException | LexicalAnalyzerException ignored) {
        }
    }

    @Test
    public void testRandomPositiveNumbers() throws LexicalAnalyzerException, ParserException {
        Random random = new Random();
        for (double i = 0; i < 1000; i++) {
            double randomDouble = Math.abs(random.nextDouble());
            Parser parser = getParser(String.valueOf(randomDouble));
            Assertions.assertEquals(randomDouble, parser.positiveDoubleNumber().result);
        }
    }

    @ParameterizedTest
    @MethodSource("variables")
    public void testVariables(String string) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(string, parser.variable().var);
    }

    @ParameterizedTest
    @MethodSource("variablesError")
    public void testVariablesErrors(String string) {
        try {
            Parser parser = getParser(string);
            parser.variable();
            parser.eof();
        } catch (ParserException | LexicalAnalyzerException ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("atoms")
    public void testAtoms(String string, Map<String, Double> map, double expected) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(expected, parser.atom(map).result);
    }

    @ParameterizedTest
    @MethodSource("atomsError")
    public void testAtomsErrors(String string, Map<String, Double> map) {
        try {
            Parser parser = getParser(string);
            parser.atom(map);
        } catch (ParserException | LexicalAnalyzerException | EvaluationException ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("unaryOperations")
    public void testUnaryOperations(String string, Map<String, Double> map, double expected) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(expected, parser.unaryOperation(map).result);
    }

    @ParameterizedTest
    @MethodSource("unaryOperationsError")
    public void testUnaryOperationsErrors(String string) {
        try {
            Parser parser = getParser(string);
            parser.unaryOperation(EMPTY_MAP);
        } catch (ParserException | LexicalAnalyzerException ignored) {
        }
    }


    @ParameterizedTest
    @MethodSource("terms")
    public void testTerm(String string, Map<String, Double> map, double expected) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(expected, parser.term(map).result);
    }

    @ParameterizedTest
    @MethodSource("termsError")
    public void testTermsErrors(String string) {
        try {
            Parser parser = getParser(string);
            parser.term(EMPTY_MAP);
        } catch (ParserException | LexicalAnalyzerException ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("expressions")
    public void testExpression(String string, Map<String, Double> map, double expected) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(expected, parser.expressionWithoutVar(map).result);
    }

    @ParameterizedTest
    @MethodSource("expressionsError")
    public void testExpressionsErrors(String string) {
        try {
            Parser parser = getParser(string);
            parser.expression(EMPTY_MAP);
        } catch (ParserException | LexicalAnalyzerException ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("equations")
    public void testEquations(String string, List<Pair<String, Double>> expected) throws LexicalAnalyzerException, ParserException {
        Parser parser = getParser(string);
        Assertions.assertEquals(expected, parser.equations().evals);
    }

    @ParameterizedTest
    @MethodSource("equationsError")
    public void testEquationsErrors(String string) {
        try {
            Parser parser = getParser(string);
            parser.equations();
        } catch (ParserException | LexicalAnalyzerException | EvaluationException ignored) {
        }
    }
}
