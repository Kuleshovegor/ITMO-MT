import exceptions.LexicalAnalyzerException
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.text.ParseException
import kotlin.random.Random

class LexicalAnalyzerTest {
    companion object {
        @JvmStatic
        fun sequences() = listOf(
            Arguments.of(
                "A", listOf(
                    Token(TypeToken.VARIABLE, "A"),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "A or B", listOf(
                    Token(TypeToken.VARIABLE, "A"),
                    Token(TypeToken.OR),
                    Token(TypeToken.VARIABLE, "B"),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "A or B and", listOf(
                    Token(TypeToken.VARIABLE, "A"),
                    Token(TypeToken.OR),
                    Token(TypeToken.VARIABLE, "B"),
                    Token(TypeToken.AND),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "Aor\nBand", listOf(
                    Token(TypeToken.VARIABLE, "A"),
                    Token(TypeToken.OR),
                    Token(TypeToken.VARIABLE, "B"),
                    Token(TypeToken.AND),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "aorband",
                listOf(
                    Token(TypeToken.VARIABLE, "a"),
                    Token(TypeToken.OR),
                    Token(TypeToken.VARIABLE, "b"),
                    Token(TypeToken.AND),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "and", listOf(
                    Token(TypeToken.AND),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "(a or b) and c", listOf(
                    Token(TypeToken.OPEN_BRACKET),
                    Token(TypeToken.VARIABLE, "a"),
                    Token(TypeToken.OR), Token(TypeToken.VARIABLE, "b"),
                    Token(TypeToken.CLOSE_BRACKET),
                    Token(TypeToken.AND),
                    Token(TypeToken.VARIABLE, "c"),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "a AND b", listOf(
                    Token(TypeToken.VARIABLE, "a"),
                    Token(TypeToken.VARIABLE, "A"),
                    Token(TypeToken.VARIABLE, "N"),
                    Token(TypeToken.VARIABLE, "D"),
                    Token(TypeToken.VARIABLE, "b"),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "andandand", listOf(
                    Token(TypeToken.AND),
                    Token(TypeToken.AND),
                    Token(TypeToken.AND),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of(
                "   andorand", listOf(
                    Token(TypeToken.AND),
                    Token(TypeToken.OR),
                    Token(TypeToken.AND),
                    Token(TypeToken.END)
                )
            ),
            Arguments.of("", listOf(Token(TypeToken.END))),
        )

        @JvmStatic
        fun sequencesAsserts() = listOf(
            Arguments.of("1"),
            Arguments.of("A.F")
        )

        fun generateRandomTest(len: Int, separator: String): Pair<String, List<Token>> {
            val tokens = mapOf(
                "xor" to Token(TypeToken.XOR),
                "not" to Token(TypeToken.NOT),
                "in" to Token(TypeToken.IN),
                "and" to Token(TypeToken.AND),
                "or" to Token(TypeToken.OR),
                "a" to Token(TypeToken.VARIABLE, "a"),
                "b" to Token(TypeToken.VARIABLE, "b"),
                "c" to Token(TypeToken.VARIABLE, "c"),
                "x" to Token(TypeToken.VARIABLE, "x"),
                "y" to Token(TypeToken.VARIABLE, "y"),
                "A" to Token(TypeToken.VARIABLE, "A"),
                "f" to Token(TypeToken.VARIABLE, "f"),
                "(" to Token(TypeToken.OPEN_BRACKET),
                ")" to Token(TypeToken.CLOSE_BRACKET)
            )
            val expected = mutableListOf<Token>()
            val test = mutableListOf<String>()
            val entries = tokens.entries

            for (i in 0..len) {
                val randomEntry = entries.random()

                test.add(randomEntry.key)
                expected.add(randomEntry.value)
            }
            expected.add(Token(TypeToken.END))

            return test.joinToString(separator) to expected
        }
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun test(string: String, expected: List<Token>) {
        val analyzer = LexicalAnalyzer(string.byteInputStream())

        for (expectedToken in expected) {
            analyzer.nextToken()
            Assertions.assertEquals(expectedToken, analyzer.curToken)
        }
        Assertions.assertEquals(analyzer.curToken, Token(TypeToken.END))
    }

    @Test
    fun randomTest() {
        for (i in 0..1000) {
            val arguments = generateRandomTest(Random.nextInt(100), "   ")

            test(arguments.first, arguments.second)
        }
    }

    @ParameterizedTest
    @MethodSource("sequencesAsserts")
    fun failTest(string: String) {
        val analyzer = LexicalAnalyzer(string.byteInputStream())

        Assertions.assertThrows(LexicalAnalyzerException::class.java) {
            do {
                analyzer.nextToken()
            } while (analyzer.curToken != Token(TypeToken.END))
        }
    }

}