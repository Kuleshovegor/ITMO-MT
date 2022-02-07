// Generated from C:/Users/kules/IdeaProjects/ITMO-MT/untitled/src/main/java/metagen\Grammar.g4 by ANTLR 4.9.2
package grammarParser;

import objects.*;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NONTERM=1, REGEXP=2, SQUARE_BRACKETS=3, SOME_BRACKETS=4, TERM=5, RULE_OR=6, 
		RULE_END=7, EQ=8, DOUBLE_QUOTES=9, WS=10;
	public static final int
		RULE_gram = 0, RULE_parseComplexRule = 1, RULE_parseRule = 2, RULE_token = 3, 
		RULE_regex = 4, RULE_term = 5, RULE_nonTerm = 6, RULE_regexp = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"gram", "parseComplexRule", "parseRule", "token", "regex", "term", "nonTerm", 
			"regexp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'|'", "';'", "'='", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NONTERM", "REGEXP", "SQUARE_BRACKETS", "SOME_BRACKETS", "TERM", 
			"RULE_OR", "RULE_END", "EQ", "DOUBLE_QUOTES", "WS"
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

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GramContext extends ParserRuleContext {
		public objects.Grammatic grammatic;
		public ParseComplexRuleContext parseComplexRule;
		public TokenContext token;
		public List<ParseComplexRuleContext> parseComplexRule() {
			return getRuleContexts(ParseComplexRuleContext.class);
		}
		public ParseComplexRuleContext parseComplexRule(int i) {
			return getRuleContext(ParseComplexRuleContext.class,i);
		}
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public GramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gram; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GramContext gram() throws RecognitionException {
		GramContext _localctx = new GramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_gram);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((GramContext)_localctx).grammatic =  new objects.Grammatic();
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(23);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NONTERM:
					{
					setState(17);
					((GramContext)_localctx).parseComplexRule = parseComplexRule();
					_localctx.grammatic.addComplexRule(((GramContext)_localctx).parseComplexRule.resRule);
					}
					break;
				case TERM:
					{
					setState(20);
					((GramContext)_localctx).token = token();
					_localctx.grammatic.addToken(((GramContext)_localctx).token.resToken);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NONTERM || _la==TERM );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseComplexRuleContext extends ParserRuleContext {
		public List<objects.Rule> rules;
		public objects.ComplexRule resRule;
		public NonTermContext nonTerm;
		public Token SQUARE_BRACKETS;
		public ParseRuleContext a;
		public ParseRuleContext b;
		public NonTermContext nonTerm() {
			return getRuleContext(NonTermContext.class,0);
		}
		public TerminalNode SQUARE_BRACKETS() { return getToken(GrammarParser.SQUARE_BRACKETS, 0); }
		public TerminalNode EQ() { return getToken(GrammarParser.EQ, 0); }
		public TerminalNode RULE_END() { return getToken(GrammarParser.RULE_END, 0); }
		public List<ParseRuleContext> parseRule() {
			return getRuleContexts(ParseRuleContext.class);
		}
		public ParseRuleContext parseRule(int i) {
			return getRuleContext(ParseRuleContext.class,i);
		}
		public List<TerminalNode> RULE_OR() { return getTokens(GrammarParser.RULE_OR); }
		public TerminalNode RULE_OR(int i) {
			return getToken(GrammarParser.RULE_OR, i);
		}
		public ParseComplexRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseComplexRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParseComplexRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParseComplexRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParseComplexRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseComplexRuleContext parseComplexRule() throws RecognitionException {
		ParseComplexRuleContext _localctx = new ParseComplexRuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parseComplexRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ParseComplexRuleContext)_localctx).rules =  new ArrayList<>();
			setState(28);
			((ParseComplexRuleContext)_localctx).nonTerm = nonTerm();
			setState(29);
			((ParseComplexRuleContext)_localctx).SQUARE_BRACKETS = match(SQUARE_BRACKETS);
			setState(30);
			match(EQ);
			setState(31);
			((ParseComplexRuleContext)_localctx).a = parseRule(((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm);
			_localctx.rules.add(a);
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RULE_OR) {
				{
				{
				setState(33);
				match(RULE_OR);
				setState(34);
				((ParseComplexRuleContext)_localctx).b = parseRule(((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm);
				_localctx.rules.add(b);
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
			match(RULE_END);
			((ParseComplexRuleContext)_localctx).resRule =  new objects.ComplexRule(((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm, _localctx.rules, ((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm.params, (((ParseComplexRuleContext)_localctx).SQUARE_BRACKETS!=null?((ParseComplexRuleContext)_localctx).SQUARE_BRACKETS.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseRuleContext extends ParserRuleContext {
		public objects.NonTerm from;
		public List<objects.Torm> torms;
		public objects.Rule resRule;
		public NonTermContext nonTerm;
		public TermContext term;
		public TerminalNode RULE_END() { return getToken(GrammarParser.RULE_END, 0); }
		public List<NonTermContext> nonTerm() {
			return getRuleContexts(NonTermContext.class);
		}
		public NonTermContext nonTerm(int i) {
			return getRuleContext(NonTermContext.class,i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ParseRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ParseRuleContext(ParserRuleContext parent, int invokingState, objects.NonTerm from) {
			super(parent, invokingState);
			this.from = from;
		}
		@Override public int getRuleIndex() { return RULE_parseRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParseRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParseRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParseRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseRuleContext parseRule(objects.NonTerm from) throws RecognitionException {
		ParseRuleContext _localctx = new ParseRuleContext(_ctx, getState(), from);
		enterRule(_localctx, 4, RULE_parseRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ParseRuleContext)_localctx).torms =  new ArrayList<>();
			{
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(52);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NONTERM:
					{
					setState(46);
					((ParseRuleContext)_localctx).nonTerm = nonTerm();
					_localctx.torms.add(((ParseRuleContext)_localctx).nonTerm.resNonTerm);
					}
					break;
				case TERM:
					{
					setState(49);
					((ParseRuleContext)_localctx).term = term();
					_localctx.torms.add(((ParseRuleContext)_localctx).term.resTerm);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NONTERM || _la==TERM );
			}
			setState(56);
			match(RULE_END);
			((ParseRuleContext)_localctx).resRule =  new objects.Rule(_localctx.from, _localctx.torms);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokenContext extends ParserRuleContext {
		public objects.MyToken resToken;
		public Token TERM;
		public RegexContext regex;
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public TerminalNode EQ() { return getToken(GrammarParser.EQ, 0); }
		public RegexContext regex() {
			return getRuleContext(RegexContext.class,0);
		}
		public TerminalNode RULE_END() { return getToken(GrammarParser.RULE_END, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_token);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			((TokenContext)_localctx).TERM = match(TERM);
			setState(60);
			match(EQ);
			setState(61);
			((TokenContext)_localctx).regex = regex();
			setState(62);
			match(RULE_END);
			((TokenContext)_localctx).resToken =  new objects.MyToken(new objects.Term((((TokenContext)_localctx).TERM!=null?((TokenContext)_localctx).TERM.getText():null)), ((TokenContext)_localctx).regex.str);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegexContext extends ParserRuleContext {
		public String str;
		public RegexpContext regexp;
		public RegexpContext regexp() {
			return getRuleContext(RegexpContext.class,0);
		}
		public RegexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRegex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRegex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRegex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexContext regex() throws RecognitionException {
		RegexContext _localctx = new RegexContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_regex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			((RegexContext)_localctx).regexp = regexp();
			((RegexContext)_localctx).str =  (((RegexContext)_localctx).regexp!=null?_input.getText(((RegexContext)_localctx).regexp.start,((RegexContext)_localctx).regexp.stop):null);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public objects.Term resTerm;
		public Token TERM;
		public TerminalNode TERM() { return getToken(GrammarParser.TERM, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((TermContext)_localctx).TERM = match(TERM);
			((TermContext)_localctx).resTerm =  new Term((((TermContext)_localctx).TERM!=null?((TermContext)_localctx).TERM.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonTermContext extends ParserRuleContext {
		public objects.NonTerm resNonTerm;
		public Token NONTERM;
		public Token SQUARE_BRACKETS;
		public TerminalNode NONTERM() { return getToken(GrammarParser.NONTERM, 0); }
		public TerminalNode SQUARE_BRACKETS() { return getToken(GrammarParser.SQUARE_BRACKETS, 0); }
		public NonTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNonTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNonTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNonTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonTermContext nonTerm() throws RecognitionException {
		NonTermContext _localctx = new NonTermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nonTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			((NonTermContext)_localctx).resNonTerm =  null;
			setState(72);
			((NonTermContext)_localctx).NONTERM = match(NONTERM);
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(73);
				((NonTermContext)_localctx).SQUARE_BRACKETS = match(SQUARE_BRACKETS);
				((NonTermContext)_localctx).resNonTerm =  new objects.NonTerm((((NonTermContext)_localctx).NONTERM!=null?((NonTermContext)_localctx).NONTERM.getText():null), (((NonTermContext)_localctx).SQUARE_BRACKETS!=null?((NonTermContext)_localctx).SQUARE_BRACKETS.getText():null));
				}
				break;
			}
			if (resNonTerm == null) {
			        ((NonTermContext)_localctx).resNonTerm =  new NonTerm((((NonTermContext)_localctx).NONTERM!=null?((NonTermContext)_localctx).NONTERM.getText():null));
			     }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegexpContext extends ParserRuleContext {
		public TerminalNode REGEXP() { return getToken(GrammarParser.REGEXP, 0); }
		public RegexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRegexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRegexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRegexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexpContext regexp() throws RecognitionException {
		RegexpContext _localctx = new RegexpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_regexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(REGEXP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\fT\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\6\2\32\n\2\r\2\16\2\33\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\7\3(\n\3\f\3\16\3+\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4"+
		"\67\n\4\r\4\16\48\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\5\bN\n\b\3\b\3\b\3\t\3\t\3\t\2\2\n\2\4\6\b\n"+
		"\f\16\20\2\2\2Q\2\22\3\2\2\2\4\35\3\2\2\2\6/\3\2\2\2\b=\3\2\2\2\nC\3\2"+
		"\2\2\fF\3\2\2\2\16I\3\2\2\2\20Q\3\2\2\2\22\31\b\2\1\2\23\24\5\4\3\2\24"+
		"\25\b\2\1\2\25\32\3\2\2\2\26\27\5\b\5\2\27\30\b\2\1\2\30\32\3\2\2\2\31"+
		"\23\3\2\2\2\31\26\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34"+
		"\3\3\2\2\2\35\36\b\3\1\2\36\37\5\16\b\2\37 \7\5\2\2 !\7\n\2\2!\"\5\6\4"+
		"\2\")\b\3\1\2#$\7\b\2\2$%\5\6\4\2%&\b\3\1\2&(\3\2\2\2\'#\3\2\2\2(+\3\2"+
		"\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7\t\2\2-.\b\3\1\2.\5\3"+
		"\2\2\2/\66\b\4\1\2\60\61\5\16\b\2\61\62\b\4\1\2\62\67\3\2\2\2\63\64\5"+
		"\f\7\2\64\65\b\4\1\2\65\67\3\2\2\2\66\60\3\2\2\2\66\63\3\2\2\2\678\3\2"+
		"\2\28\66\3\2\2\289\3\2\2\29:\3\2\2\2:;\7\t\2\2;<\b\4\1\2<\7\3\2\2\2=>"+
		"\7\7\2\2>?\7\n\2\2?@\5\n\6\2@A\7\t\2\2AB\b\5\1\2B\t\3\2\2\2CD\5\20\t\2"+
		"DE\b\6\1\2E\13\3\2\2\2FG\7\7\2\2GH\b\7\1\2H\r\3\2\2\2IJ\b\b\1\2JM\7\3"+
		"\2\2KL\7\5\2\2LN\b\b\1\2MK\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\b\b\1\2P\17\3"+
		"\2\2\2QR\7\4\2\2R\21\3\2\2\2\b\31\33)\668M";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}