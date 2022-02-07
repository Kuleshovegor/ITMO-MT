package exceptions

import Token
import TypeToken
import java.text.ParseException

data class ParserException(override val message: String, val pos: Int): ParseException(message, pos) {
    constructor(pos: Int, currentToken: Token, expectedTypeTokens: List<TypeToken>):
            this("exceptions.ParserException at pos $pos: type of tokens expected: ${expectedTypeTokens.joinToString(" ")}, but found: $currentToken", pos)
}