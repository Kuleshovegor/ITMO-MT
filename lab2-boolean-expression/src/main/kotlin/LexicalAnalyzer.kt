import exceptions.LexicalAnalyzerException
import java.io.IOException
import java.io.InputStream

class LexicalAnalyzer(private val inputStream: InputStream) {
    companion object {
        val SORTED_BY_SIZE_TOKENS = TypeToken.values().sortedBy { it.asString.length }.reversed()

        private fun isBlank(c: Int): Boolean {
            val symbol: Char = c.toChar()

            return c >= 0 && (symbol.isWhitespace() || symbol.toString() == System.lineSeparator())
        }
    }

    var curChar: Int = nextChar()
    var curPos = 0
    var curToken = Token(TypeToken.VARIABLE)

    private fun nextChar(): Int {
        curPos++
        try {
            curChar = inputStream.read()

            return curChar
        } catch (e: IOException) {
            throw LexicalAnalyzerException(e.message ?: "IOException", curPos)
        }
    }

    private fun tryRead(string: String): Boolean {
        inputStream.mark(string.length)
        val charBefore = curChar
        val posBefore = curPos

        for (c in string) {
            if (curPos >= 0 && curChar.toChar() == c) {
                nextChar()
            } else {
                curChar = charBefore
                curPos = posBefore
                inputStream.reset()

                return false
            }
        }

        return true
    }

    fun nextToken() {
        while (isBlank(curChar)) {
            nextChar()
        }

        if (curChar == -1) {
            curToken = Token(TypeToken.END)

            return
        }

        for (token in SORTED_BY_SIZE_TOKENS) {
            if (token.asString.isNotEmpty() && tryRead(token.asString)) {
                curToken = Token(token)

                return
            }
        }

        val symbol = curChar.toChar()

        if (symbol.isLetter()) {
            curToken = Token(TypeToken.VARIABLE, symbol.toString())
            nextChar()

            return
        }

        throw LexicalAnalyzerException("Illegal symbol: '$curChar'", curPos)
    }
}