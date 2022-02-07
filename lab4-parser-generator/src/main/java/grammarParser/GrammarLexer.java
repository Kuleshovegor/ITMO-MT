// Generated from C:/Users/kules/IdeaProjects/ITMO-MT/untitled/src/main/java\Grammar.g4 by ANTLR 4.9.2
package grammarParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NONTERM=1, REGEXP=2, L_SQUARE_BRACKETS=3, R_SQUARE_BRACKETS=4, SQUARE_BRACKETS=5, 
		SOME_BRACKETS=6, GRAMMAR=7, RETURNS=8, IMPRORTS=9, TERM=10, RULE_OR=11, 
		RULE_END=12, EQ=13, DOUBLE_QUOTES=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NONTERM", "REGEXP", "L_SQUARE_BRACKETS", "R_SQUARE_BRACKETS", "SQUARE_BRACKETS", 
			"SOME_BRACKETS", "GRAMMAR", "RETURNS", "IMPRORTS", "TERM", "RULE_OR", 
			"RULE_END", "EQ", "DOUBLE_QUOTES", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'['", "']'", null, null, "'@grammar'", "'@returns'", 
			"'@imports'", null, "'|'", "';'", "'='", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NONTERM", "REGEXP", "L_SQUARE_BRACKETS", "R_SQUARE_BRACKETS", 
			"SQUARE_BRACKETS", "SOME_BRACKETS", "GRAMMAR", "RETURNS", "IMPRORTS", 
			"TERM", "RULE_OR", "RULE_END", "EQ", "DOUBLE_QUOTES", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\7\2$\n\2\f\2"+
		"\16\2\'\13\2\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\7\68\n\6\f\6\16\6;\13\6\3\6\3\6\3\7\3\7\7\7A\n\7\f\7\16\7D\13\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\7\13e\n\13\f"+
		"\13\16\13h\13\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20s\n\20\r"+
		"\20\16\20t\3\20\3\20\5,9B\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21\3\2\7\3\2c|\5\2\62;C\\c|\3\2C\\\5\2"+
		"\62;C\\aa\5\2\13\f\17\17\"\"\2}\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\3!\3\2\2\2\5(\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\65\3"+
		"\2\2\2\r>\3\2\2\2\17G\3\2\2\2\21P\3\2\2\2\23Y\3\2\2\2\25b\3\2\2\2\27i"+
		"\3\2\2\2\31k\3\2\2\2\33m\3\2\2\2\35o\3\2\2\2\37r\3\2\2\2!%\t\2\2\2\"$"+
		"\t\3\2\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\4\3\2\2\2\'%\3\2\2"+
		"\2(,\7$\2\2)+\13\2\2\2*)\3\2\2\2+.\3\2\2\2,-\3\2\2\2,*\3\2\2\2-/\3\2\2"+
		"\2.,\3\2\2\2/\60\7$\2\2\60\6\3\2\2\2\61\62\7]\2\2\62\b\3\2\2\2\63\64\7"+
		"_\2\2\64\n\3\2\2\2\659\5\7\4\2\668\13\2\2\2\67\66\3\2\2\28;\3\2\2\29:"+
		"\3\2\2\29\67\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\5\t\5\2=\f\3\2\2\2>B\7}\2\2"+
		"?A\13\2\2\2@?\3\2\2\2AD\3\2\2\2BC\3\2\2\2B@\3\2\2\2CE\3\2\2\2DB\3\2\2"+
		"\2EF\7\177\2\2F\16\3\2\2\2GH\7B\2\2HI\7i\2\2IJ\7t\2\2JK\7c\2\2KL\7o\2"+
		"\2LM\7o\2\2MN\7c\2\2NO\7t\2\2O\20\3\2\2\2PQ\7B\2\2QR\7t\2\2RS\7g\2\2S"+
		"T\7v\2\2TU\7w\2\2UV\7t\2\2VW\7p\2\2WX\7u\2\2X\22\3\2\2\2YZ\7B\2\2Z[\7"+
		"k\2\2[\\\7o\2\2\\]\7r\2\2]^\7q\2\2^_\7t\2\2_`\7v\2\2`a\7u\2\2a\24\3\2"+
		"\2\2bf\t\4\2\2ce\t\5\2\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\26\3"+
		"\2\2\2hf\3\2\2\2ij\7~\2\2j\30\3\2\2\2kl\7=\2\2l\32\3\2\2\2mn\7?\2\2n\34"+
		"\3\2\2\2op\7$\2\2p\36\3\2\2\2qs\t\6\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2"+
		"tu\3\2\2\2uv\3\2\2\2vw\b\20\2\2w \3\2\2\2\n\2%,9Bdft\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}