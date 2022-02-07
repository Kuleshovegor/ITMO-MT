// Generated from C:/Users/kules/IdeaProjects/csc-kotlin/MTlab3/src/main/java\Expression.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN_BRACKET=1, CLOSE_BRACKET=2, PLUS=3, MINUS=4, MULT=5, DIV=6, EQ=7, 
		POW=8, EXPRESSION_END=9, DOUBLE=10, VARIABLE=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"OPEN_BRACKET", "CLOSE_BRACKET", "PLUS", "MINUS", "MULT", "DIV", "EQ", 
			"POW", "EXPRESSION_END", "DOUBLE", "VARIABLE", "CHAR", "NUMBER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'='", "'^'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OPEN_BRACKET", "CLOSE_BRACKET", "PLUS", "MINUS", "MULT", "DIV", 
			"EQ", "POW", "EXPRESSION_END", "DOUBLE", "VARIABLE", "WS"
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


	public ExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16^\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\6\13\63\n\13\r\13\16\13"+
		"\64\3\13\3\13\6\139\n\13\r\13\16\13:\3\13\3\13\5\13?\n\13\3\13\6\13B\n"+
		"\13\r\13\16\13C\5\13F\n\13\5\13H\n\13\3\f\3\f\3\f\3\f\7\fN\n\f\f\f\16"+
		"\fQ\13\f\3\r\5\rT\n\r\3\16\3\16\3\17\6\17Y\n\17\r\17\16\17Z\3\17\3\17"+
		"\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\2\33\2\35"+
		"\16\3\2\5\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2e\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\35\3\2\2\2\3\37\3\2"+
		"\2\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17+\3\2"+
		"\2\2\21-\3\2\2\2\23/\3\2\2\2\25\62\3\2\2\2\27I\3\2\2\2\31S\3\2\2\2\33"+
		"U\3\2\2\2\35X\3\2\2\2\37 \7*\2\2 \4\3\2\2\2!\"\7+\2\2\"\6\3\2\2\2#$\7"+
		"-\2\2$\b\3\2\2\2%&\7/\2\2&\n\3\2\2\2\'(\7,\2\2(\f\3\2\2\2)*\7\61\2\2*"+
		"\16\3\2\2\2+,\7?\2\2,\20\3\2\2\2-.\7`\2\2.\22\3\2\2\2/\60\7=\2\2\60\24"+
		"\3\2\2\2\61\63\5\33\16\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64"+
		"\65\3\2\2\2\65G\3\2\2\2\668\7\60\2\2\679\5\33\16\28\67\3\2\2\29:\3\2\2"+
		"\2:8\3\2\2\2:;\3\2\2\2;E\3\2\2\2<>\7G\2\2=?\5\t\5\2>=\3\2\2\2>?\3\2\2"+
		"\2?A\3\2\2\2@B\5\33\16\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2"+
		"\2\2E<\3\2\2\2EF\3\2\2\2FH\3\2\2\2G\66\3\2\2\2GH\3\2\2\2H\26\3\2\2\2I"+
		"O\5\31\r\2JN\5\31\r\2KN\5\33\16\2LN\7a\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2"+
		"\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\30\3\2\2\2QO\3\2\2\2RT\t\2\2\2SR\3\2"+
		"\2\2T\32\3\2\2\2UV\t\3\2\2V\34\3\2\2\2WY\t\4\2\2XW\3\2\2\2YZ\3\2\2\2Z"+
		"X\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\b\17\2\2]\36\3\2\2\2\r\2\64:>CEGMOS"+
		"Z\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}