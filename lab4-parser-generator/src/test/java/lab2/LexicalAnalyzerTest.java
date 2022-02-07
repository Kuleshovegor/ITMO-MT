package lab2;

import booleanExpressions.LexicalAnalyzer;
import exceptions.LexicalAnalyzerException;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class LexicalAnalyzerTest {
        private static Stream<Arguments> sequences() {
            return Stream.of(
                    Arguments.of(
                            "A", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "A or B", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "B"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "A or B and", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "B"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "Aor\nBand", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "B"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "AorBand",
                            List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "B"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "and", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "(A or B) and C", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.LPARENT, "("),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "B"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.RPARENT, ")"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "C"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "A AND B", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "N"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "D"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "B"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "andandand", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of(
                            "   andorand", List.of(
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                                    new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                                    LexicalAnalyzer.EOF
                            )
                    ),
                    Arguments.of("", List.of(LexicalAnalyzer.EOF))
            );
        }

        private static Stream<Arguments> sequencesAsserts() {
            return Stream.of(Arguments.of("1"),
            Arguments.of("A.F")
        );
        }

        private static final Random random = new Random();

        private static Pair<String, List<LexicalAnalyzer.Token>> generateRandomTest(int len, String separator) {
            Map<String, LexicalAnalyzer.Token> tokens = Map.of(
                "xor" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.XOR, "xor"),
                "not" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.NOT, "not"),
                "in" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.IN, "in"),
                "and" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.AND, "and"),
                "or" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.OR, "or"),
                "C" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "C"),
                "A" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "A"),
                "F" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.VAR, "F"),
                "(" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.LPARENT, "("),
                ")" , new LexicalAnalyzer.Token(LexicalAnalyzer.TypeToken.RPARENT, ")")
            );
            List<LexicalAnalyzer.Token> expected = new ArrayList<>();
            List<String> test = new ArrayList<>();
            List<Map.Entry<String, LexicalAnalyzer.Token>> entries = tokens.entrySet().stream().toList();

            for (int i = 0; i < len; i++) {
                int randomInd = random.nextInt(10);
                Map.Entry<String, LexicalAnalyzer.Token> randomEntry = entries.get(randomInd);

                test.add(randomEntry.getKey());
                expected.add(randomEntry.getValue());
            }
            expected.add(LexicalAnalyzer.EOF);

            return new Pair<>(String.join(separator, test), expected);
        }

    @ParameterizedTest
    @MethodSource("sequences")
    public void test(String string, List<LexicalAnalyzer.Token> expected) throws LexicalAnalyzerException {
        LexicalAnalyzer analyzer = new LexicalAnalyzer(string);

        for (LexicalAnalyzer.Token expectedToken : expected) {
            Assertions.assertEquals(expectedToken, analyzer.getCurToken());
            analyzer.nextToken();
        }
        Assertions.assertEquals(analyzer.getCurToken(), LexicalAnalyzer.EOF);
    }

    @Test
    public void randomTest() throws LexicalAnalyzerException {
        for (int i = 0; i < 1000; i++) {
            Pair<String, List<LexicalAnalyzer.Token>> arguments = generateRandomTest(random.nextInt(100), "   ");

            test(arguments.a, arguments.b);
        }
    }

    @ParameterizedTest
    @MethodSource("sequencesAsserts")
    public void failTest(String string) {
        try {
            LexicalAnalyzer analyzer = new LexicalAnalyzer(string);
            do {
                analyzer.nextToken();
            } while (analyzer.getCurToken() != LexicalAnalyzer.EOF);
        } catch (LexicalAnalyzerException ignored) {
        }
    }

}