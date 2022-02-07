package exceptions

import java.text.ParseException

class LexicalAnalyzerException(s: String, pos: Int): ParseException(s, pos)