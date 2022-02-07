import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

public class ParserTest {
    private static final Map<String, Double> EMPTY_MAP = new HashMap<>();

    public static class MyErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                 String msg, RecognitionException e ) {
            System.err.println("line: " + line + " pos: " + charPositionInLine + " " + msg);
            throw new RuntimeException("syntax error occurred");
        }
    }

    private static Stream<Arguments> positiveNumbers() {
        return Stream.of(
                Arguments.of("0", 0),
                Arguments.of("1", 1),
                Arguments.of("123", 123),
                Arguments.of("1.0", 1),
                Arguments.of("1.0123.1", 1.0123),
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
                Arguments.of("0*1", EMPTY_MAP, 0),
                Arguments.of("123*113", EMPTY_MAP, 13899),
                Arguments.of("-1*1", EMPTY_MAP, -1),
                Arguments.of("5*-4", EMPTY_MAP, -20),
                Arguments.of("-5*-4", EMPTY_MAP, 20),
                Arguments.of("0/1", EMPTY_MAP, 0),
                Arguments.of("8/2", EMPTY_MAP, 4),
                Arguments.of("7/2", EMPTY_MAP, 3.5),
                Arguments.of("1/0", EMPTY_MAP, Double.POSITIVE_INFINITY),
                Arguments.of("5/-1", EMPTY_MAP, -5),
                Arguments.of("-5/1", EMPTY_MAP, -5),
                Arguments.of("-5/-1", EMPTY_MAP, 5),
                Arguments.of("5*2/1", EMPTY_MAP, 10),
                Arguments.of("5*2*13", EMPTY_MAP, 130),
                Arguments.of("5*-2*13", EMPTY_MAP, -130),
                Arguments.of("5/-2/13", EMPTY_MAP, -0.19230769230769232),
                Arguments.of("5*a*13", Map.of("a", -2.0), -130),
                Arguments.of("5/-b/13", Map.of("b", 2.0), -0.19230769230769232)
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
                Arguments.of("0+1", EMPTY_MAP, 1),
                Arguments.of("4+5", EMPTY_MAP, 9),
                Arguments.of("20+13", EMPTY_MAP, 33),
                Arguments.of("0+-1", EMPTY_MAP, -1),
                Arguments.of("-1+-1", EMPTY_MAP, -2),
                Arguments.of("-1-1", EMPTY_MAP, -2),
                Arguments.of("-1+-1", EMPTY_MAP, -2),
                Arguments.of("-1+(-2 * 3)", EMPTY_MAP, -7),
                Arguments.of("(-1+-2) * 3", EMPTY_MAP, -9),
                Arguments.of("-1+(-3 / 1)", EMPTY_MAP, -4),
                Arguments.of("(-1+-2) / 3", EMPTY_MAP, -1),
                Arguments.of("-1+-2 * 3 - 32 + 23 -234 *24 / 2", EMPTY_MAP, -2824),
                Arguments.of("-1+-a2 * 3 - (dfs) + 23 -234 *24 / 2", Map.of(
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
                                                
                        """, List.of()),
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

    private static final MyErrorListener errorListener = new MyErrorListener();

    public static ExpressionParser getParser(String string) {
        ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(string));
        lexer.removeErrorListeners();
        lexer.addErrorListener( errorListener );
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener( errorListener );
        return parser;
    }

    public static void afterOkTest(ExpressionParser parser) {
        parser.eof();
        Assertions.assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    @ParameterizedTest
    @MethodSource("positiveNumbers")
    public void testPositiveNumbers(String string, double expected) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(expected, parser.positiveDoubleNumber().result);
        afterOkTest(parser);
    }

    @ParameterizedTest
    @MethodSource("positiveNumbersErrors")
    public void testPositiveNumbersErrors(String string) {
        ExpressionParser parser = getParser(string);
        try {
            parser.positiveDoubleNumber();
            parser.eof();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testRandomPositiveNumbers() {
        Random random = new Random();
        for (double i = 0; i < 1000; i++) {
            double randomDouble = Math.abs(random.nextDouble());
            ExpressionParser parser = getParser(String.valueOf(randomDouble));
            Assertions.assertEquals(randomDouble, parser.positiveDoubleNumber().result);
            afterOkTest(parser);
        }
    }

    @ParameterizedTest
    @MethodSource("variables")
    public void testVariables(String string) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(string, parser.variable().var);
        afterOkTest(parser);
    }

    @ParameterizedTest
    @MethodSource("variablesError")
    public void testVariablesErrors(String string) {
        ExpressionParser parser = getParser(string);
        try {
            parser.variable();
            parser.eof();
        } catch (Exception ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("atoms")
    public void testAtoms(String string, Map<String, Double> map, double expected) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(expected, parser.atom(map).result);
        afterOkTest(parser);
    }

    @ParameterizedTest
    @MethodSource("atomsError")
    public void testAtomsErrors(String string, Map<String, Double> map) {
        ExpressionParser parser = getParser(string);
        try {
            parser.atom(map);
            parser.eof();
        } catch (Exception ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("unaryOperations")
    public void testUnaryOperations(String string, Map<String, Double> map, double expected) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(expected, parser.unaryOperation(map).result);
        afterOkTest(parser);
    }

    @ParameterizedTest
    @MethodSource("unaryOperationsError")
    public void testUnaryOperationsErrors(String string) {
        ExpressionParser parser = getParser(string);
        try {
            parser.unaryOperation(EMPTY_MAP);
            parser.eof();
        } catch (Exception ignored) {
        }
    }


    @ParameterizedTest
    @MethodSource("terms")
    public void testTerm(String string, Map<String, Double> map, double expected) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(expected, parser.term(map).result);
        parser.eof();
        Assertions.assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    @ParameterizedTest
    @MethodSource("termsError")
    public void testTermsErrors(String string) {
        ExpressionParser parser = getParser(string);
        try {
            parser.term(EMPTY_MAP);
            parser.eof();
        } catch (Exception ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("expressions")
    public void testExpression(String string, Map<String, Double> map, double expected) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(expected, parser.expression(map).result);
        afterOkTest(parser);
    }

    @ParameterizedTest
    @MethodSource("expressionsError")
    public void testExpressionsErrors(String string) {
        ExpressionParser parser = getParser(string);
        try {
            parser.expression(EMPTY_MAP);
            parser.eof();
        } catch (Exception ignored) {
        }
    }

    @ParameterizedTest
    @MethodSource("equations")
    public void testEquations(String string, List<Pair<String, Double>> expected) {
        ExpressionParser parser = getParser(string);
        Assertions.assertEquals(expected, parser.equations().evals);
        afterOkTest(parser);
    }

    @ParameterizedTest
    @MethodSource("equationsError")
    public void testEquationsErrors(String string) {
        ExpressionParser parser = getParser(string);
        try {
            parser.equations();
        } catch (Exception ignored) {
        }
    }
}
