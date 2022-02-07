import exceptions.ParserException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ParserTest {
    companion object {
        var count = 0

        @JvmStatic
        fun sequences() = listOf(
            Arguments.of("A", "A"),
            Arguments.of("notA", "notA"),
            Arguments.of("notnotA", "notnotA"),
            Arguments.of("A or B", "AorB"),
            Arguments.of("AinB", "AinB"),
            Arguments.of("    A    in    B  ", "AinB"),
            Arguments.of("(a or b) and c", "(aorb)andc"),
            Arguments.of("(a in b) and c", "(ainb)andc"),
            Arguments.of("(a not in b) and c", "(anotinb)andc"),
            Arguments.of("(a not in b) and (b not in a)", "(anotinb)and(bnotina)"),
            Arguments.of("(a)", "(a)"),
            Arguments.of("(a) and (a)", "(a)and(a)"),
            Arguments.of("a xor c or a", "axorcora"),
            Arguments.of("(a) == (a)", "(a)==(a)"),
            Arguments.of("a == c or a", "a==cora"),
            Arguments.of("a == a", "a==a"),
            Arguments.of("c == a in m", "c==ainm")
        )

        @JvmStatic
        fun parseExceptionSequences() = listOf(
            Arguments.of(""),
            Arguments.of("and"),
            Arguments.of("a and or b"),
            Arguments.of("a in or"),
            Arguments.of("and c"),
            Arguments.of("a in v in a"),
            Arguments.of("a f"),
            Arguments.of("asd"),
            Arguments.of("(a or b) or q)"),
            Arguments.of("(a or (b or q)"),
            Arguments.of(")"),
            Arguments.of("("),
            Arguments.of("a not not in"),
            Arguments.of("a xor ( xor in"),
            Arguments.of("=="),
            Arguments.of("== a"),
            Arguments.of("a =="),
            Arguments.of("a == ( xor in"),
        )
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun test(string: String, expected: String) {
        val tree = Parser(string.byteInputStream()).parse()

        tree.writeGraphviz("test_graphs\\${count++}")
        Assertions.assertEquals(tree.toString(), expected)
    }

    @ParameterizedTest
    @MethodSource("parseExceptionSequences")
    fun failedTest(string: String) {
        Assertions.assertThrows(ParserException::class.java) { Parser(string.byteInputStream()).parse() }
    }
}