package lab2;

import booleanExpressions.LexicalAnalyzer;
import booleanExpressions.Parser;
import booleanExpressions.Tree;
import exceptions.LexicalAnalyzerException;
import exceptions.ParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

class ParserTest {
    static int count = 0;

    private static Stream<Arguments> sequences() {
        return Stream.of(
                Arguments.of("A", "A"),
                Arguments.of("notA", "notA"),
                Arguments.of("notnotA", "notnotA"),
                Arguments.of("A or B", "AorB"),
                Arguments.of("AinB", "AinB"),
                Arguments.of("    A    in    B  ", "AinB"),
                Arguments.of("(A or B) and C", "(AorB)andC"),
                Arguments.of("(A in B) and C", "(AinB)andC"),
                Arguments.of("(A not in B) and C", "(AnotinB)andC"),
                Arguments.of("(A not in B) and (B not in A)", "(AnotinB)and(BnotinA)"),
                Arguments.of("(A)", "(A)"),
                Arguments.of("(A) and (A)", "(A)and(A)"),
                Arguments.of("A xor C or A", "AxorCorA"),
                Arguments.of("(A) == (A)", "(A)==(A)"),
                Arguments.of("A == C or A", "A==CorA"),
                Arguments.of("A == A", "A==A"),
                Arguments.of("C == A in M", "C==AinM")
        );
    }


    private static Stream<Arguments> parseExceptionSequences() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("and"),
            Arguments.of("A and or B"),
            Arguments.of("A in or"),
            Arguments.of("and C"),
            Arguments.of("A in V in A"),
            Arguments.of("A F"),
            Arguments.of("asd"),
            Arguments.of("(A or B) or Q)"),
            Arguments.of("(A or (B or Q)"),
            Arguments.of(")"),
            Arguments.of("("),
            Arguments.of("A not not in"),
            Arguments.of("A xor ( xor in"),
            Arguments.of("=="),
            Arguments.of("== A"),
            Arguments.of("A =="),
            Arguments.of("A == ( xor in")
        );
    }

    @ParameterizedTest
    @MethodSource("sequences")
    public void test(String string, String expected) throws LexicalAnalyzerException, ParserException, IOException {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(string);
        Tree tree = new Parser(lexicalAnalyzer).s().tree;

        tree.writeGraphviz("test_graphs\\" + count++);
        Assertions.assertEquals(tree.toString(), expected);
    }

    @ParameterizedTest
    @MethodSource("parseExceptionSequences")
    public void failedTest(String string) {
        try {
            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(string);
            Tree tree = new Parser(lexicalAnalyzer).s().tree;
        } catch (LexicalAnalyzerException | ParserException ignored) {
        }
    }
}