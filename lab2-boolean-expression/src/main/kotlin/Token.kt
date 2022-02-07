data class Token(val type: TypeToken, val body: String = type.asString)

enum class TypeToken(val asString: String = "") {
    EQUALS("=="),
    AND("and"),
    OR("or"),
    XOR("xor"),
    NOT("not"),
    IN("in"),
    OPEN_BRACKET("("),
    CLOSE_BRACKET(")"),
    VARIABLE,
    END
}