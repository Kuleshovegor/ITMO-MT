import exceptions.ParserException
import java.io.InputStream

class Parser(inputStream: InputStream) {
    companion object {
        val FIRST_STATE_TOKENS = listOf(TypeToken.OPEN_BRACKET, TypeToken.NOT, TypeToken.VARIABLE)
    }

    private val lexicalAnalyzer: LexicalAnalyzer

    init {
        lexicalAnalyzer = LexicalAnalyzer(inputStream)
        lexicalAnalyzer.nextToken()
    }

    fun parse(): Tree {
        val result = s()

        if (lexicalAnalyzer.curToken.type != TypeToken.END) {
            throw getParserException(listOf(TypeToken.END))
        }

        return result
    }

    private fun getParserException(expectedTokenTypes: List<TypeToken>): ParserException {
        return ParserException(lexicalAnalyzer.curPos, lexicalAnalyzer.curToken, expectedTokenTypes)
    }

    private fun firstState(nodeLabel: String, nextLevelFirstState: () -> Tree, moreSameStates: () -> Tree): Tree {
        when (lexicalAnalyzer.curToken.type) {
            in FIRST_STATE_TOKENS -> {
                val state = nextLevelFirstState()
                val moreStates = moreSameStates()

                return Tree(nodeLabel, listOf(state, moreStates))
            }
            else -> throw getParserException(FIRST_STATE_TOKENS)
        }
    }

    private fun moreSameStates(
        nodeLabel: String,
        firstState: () -> Tree,
        firstType: TypeToken,
        listFollow: List<TypeToken>
    ): Tree {
        return when (lexicalAnalyzer.curToken.type) {
            firstType -> {
                lexicalAnalyzer.nextToken()
                val state = firstState()
                val moreState = moreSameStates(nodeLabel, firstState, firstType, listFollow)

                Tree(nodeLabel, listOf(Tree(firstType.asString), state, moreState))
            }
            in listFollow -> Tree(nodeLabel, listOf(Tree("eps")))
            TypeToken.CLOSE_BRACKET -> {
                Tree(nodeLabel, listOf(Tree("eps")))
            }
            else -> {
                val expected = mutableListOf(firstType, TypeToken.CLOSE_BRACKET)

                expected.addAll(listFollow)
                throw getParserException(expected)
            }
        }
    }

    private fun s(): Tree {
        return firstState("S", { e() }, { moreS() })
    }

    private fun moreS(): Tree {
        return moreSameStates("S'", { e() }, TypeToken.XOR, listOf(TypeToken.END))
    }

    private fun e(): Tree {
        return firstState("E", { t() }, { moreE() })
    }

    private fun moreE(): Tree {
        return moreSameStates("E'", { t() }, TypeToken.OR, listOf(TypeToken.XOR, TypeToken.END))
    }

    private fun t(): Tree {
        return firstState("T", { m() }, { moreT() })
    }

    private fun moreT(): Tree {
        return moreSameStates("T'", { m() }, TypeToken.AND, listOf(TypeToken.OR, TypeToken.XOR, TypeToken.END))
    }

    private fun m(): Tree {
        when (lexicalAnalyzer.curToken.type) {
            TypeToken.OPEN_BRACKET,
            TypeToken.VARIABLE -> {
                val state = h()

                return Tree("M", listOf(state))
            }
            TypeToken.NOT -> {
                lexicalAnalyzer.nextToken()

                val m = m()

                return Tree("M", listOf(Tree("not"), m))
            }
            else -> throw getParserException(FIRST_STATE_TOKENS)
        }
    }

    private fun h(): Tree {
        return firstState("H", { p() }, { moreH() })
    }

    private fun moreH(): Tree {
        return moreSameStates("H'", { p() }, TypeToken.EQUALS, listOf(TypeToken.AND ,TypeToken.OR, TypeToken.XOR, TypeToken.END))
    }

    private fun p(): Tree {
        when (lexicalAnalyzer.curToken.type) {
            TypeToken.OPEN_BRACKET -> {
                lexicalAnalyzer.nextToken()

                val s = s()

                if (lexicalAnalyzer.curToken.type != TypeToken.CLOSE_BRACKET) {
                    throw getParserException(listOf(TypeToken.CLOSE_BRACKET))
                }

                lexicalAnalyzer.nextToken()

                return Tree("P", listOf(Tree("("), s, Tree(")")))
            }

            TypeToken.VARIABLE -> {
                val variable = lexicalAnalyzer.curToken

                lexicalAnalyzer.nextToken()

                when (lexicalAnalyzer.curToken.type) {
                    TypeToken.IN -> {
                        lexicalAnalyzer.nextToken()

                        return withSecondVariable(variable.body, false)
                    }

                    TypeToken.NOT -> {
                        lexicalAnalyzer.nextToken()

                        when (lexicalAnalyzer.curToken.type) {
                            TypeToken.IN -> {
                                lexicalAnalyzer.nextToken()

                                return withSecondVariable(variable.body, true)
                            }
                            else -> throw getParserException(listOf(TypeToken.IN))
                        }
                    }

                    TypeToken.EQUALS,
                    TypeToken.XOR,
                    TypeToken.AND,
                    TypeToken.OR,
                    TypeToken.END,
                    TypeToken.CLOSE_BRACKET -> return Tree("P", listOf(Tree(variable.body)))
                    else -> throw getParserException(
                        listOf(
                            TypeToken.EQUALS,
                            TypeToken.XOR,
                            TypeToken.AND,
                            TypeToken.OR,
                            TypeToken.END,
                            TypeToken.CLOSE_BRACKET,
                            TypeToken.IN
                        )
                    )
                }
            }
            else -> throw getParserException(listOf(TypeToken.NOT, TypeToken.VARIABLE, TypeToken.OPEN_BRACKET))
        }
    }

    private fun withSecondVariable(firstVariableName: String, isNegate: Boolean): Tree {
        when (lexicalAnalyzer.curToken.type) {
            TypeToken.VARIABLE -> {
                val secondVariableName = lexicalAnalyzer.curToken.body

                lexicalAnalyzer.nextToken()

                return Tree(
                    "P",
                    if (isNegate) {
                        listOf(Tree(firstVariableName), Tree("not"), Tree("in"), Tree(secondVariableName))
                    } else {
                        listOf(Tree(firstVariableName), Tree("in"), Tree(secondVariableName))
                    }
                )
            }
            else -> throw getParserException(listOf(TypeToken.VARIABLE))
        }
    }
}