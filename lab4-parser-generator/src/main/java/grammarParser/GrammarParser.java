// Generated from C:/Users/kules/IdeaProjects/ITMO-MT/untitled/src/main/java\Grammar.g4 by ANTLR 4.9.2
package grammarParser;

import java.util.regex.Pattern;
import objects.grammar.*;

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
		NONTERM=1, REGEXP=2, L_SQUARE_BRACKETS=3, R_SQUARE_BRACKETS=4, SQUARE_BRACKETS=5, 
		SOME_BRACKETS=6, GRAMMAR=7, RETURNS=8, IMPRORTS=9, TERM=10, RULE_OR=11, 
		RULE_END=12, EQ=13, DOUBLE_QUOTES=14, WS=15;
	public static final int
		RULE_imports = 0, RULE_parseGrammar = 1, RULE_parseComplexRule = 2, RULE_parseRule = 3, 
		RULE_token = 4, RULE_regex = 5, RULE_term = 6, RULE_nonTerm = 7, RULE_squareBrackets = 8, 
		RULE_code = 9, RULE_someBrackets = 10, RULE_regexp = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"imports", "parseGrammar", "parseComplexRule", "parseRule", "token", 
			"regex", "term", "nonTerm", "squareBrackets", "code", "someBrackets", 
			"regexp"
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

	public static class ImportsContext extends ParserRuleContext {
		public String str;
		public SomeBracketsContext someBrackets;
		public TerminalNode IMPRORTS() { return getToken(GrammarParser.IMPRORTS, 0); }
		public SomeBracketsContext someBrackets() {
			return getRuleContext(SomeBracketsContext.class,0);
		}
		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imports; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterImports(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitImports(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitImports(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_imports);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(IMPRORTS);
			setState(25);
			((ImportsContext)_localctx).someBrackets = someBrackets();
			((ImportsContext)_localctx).str =  ((ImportsContext)_localctx).someBrackets.str;
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

	public static class ParseGrammarContext extends ParserRuleContext {
		public Grammar grammar;
		public Token NONTERM;
		public ImportsContext imports;
		public ParseComplexRuleContext parseComplexRule;
		public TokenContext token;
		public TerminalNode GRAMMAR() { return getToken(GrammarParser.GRAMMAR, 0); }
		public TerminalNode NONTERM() { return getToken(GrammarParser.NONTERM, 0); }
		public TerminalNode RULE_END() { return getToken(GrammarParser.RULE_END, 0); }
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class,0);
		}
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
		public ParseGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parseGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParseGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParseGrammar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParseGrammar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseGrammarContext parseGrammar() throws RecognitionException {
		ParseGrammarContext _localctx = new ParseGrammarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_parseGrammar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(GRAMMAR);
			setState(29);
			((ParseGrammarContext)_localctx).NONTERM = match(NONTERM);
			setState(30);
			match(RULE_END);
			((ParseGrammarContext)_localctx).grammar =  new Grammar((((ParseGrammarContext)_localctx).NONTERM!=null?((ParseGrammarContext)_localctx).NONTERM.getText():null));
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPRORTS) {
				{
				setState(32);
				((ParseGrammarContext)_localctx).imports = imports();
				_localctx.grammar.setImports(((ParseGrammarContext)_localctx).imports.str);
				}
			}

			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(43);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NONTERM:
					{
					setState(37);
					((ParseGrammarContext)_localctx).parseComplexRule = parseComplexRule();
					_localctx.grammar.addComplexRule(((ParseGrammarContext)_localctx).parseComplexRule.resRule);
					}
					break;
				case TERM:
					{
					setState(40);
					((ParseGrammarContext)_localctx).token = token();
					_localctx.grammar.addToken(((ParseGrammarContext)_localctx).token.resToken);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(45); 
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
		public List<Rule> rules;
		public ComplexRule resRule;
		public NonTermContext nonTerm;
		public SquareBracketsContext squareBrackets;
		public ParseRuleContext a;
		public ParseRuleContext b;
		public NonTermContext nonTerm() {
			return getRuleContext(NonTermContext.class,0);
		}
		public TerminalNode EQ() { return getToken(GrammarParser.EQ, 0); }
		public TerminalNode RULE_END() { return getToken(GrammarParser.RULE_END, 0); }
		public List<ParseRuleContext> parseRule() {
			return getRuleContexts(ParseRuleContext.class);
		}
		public ParseRuleContext parseRule(int i) {
			return getRuleContext(ParseRuleContext.class,i);
		}
		public TerminalNode RETURNS() { return getToken(GrammarParser.RETURNS, 0); }
		public SquareBracketsContext squareBrackets() {
			return getRuleContext(SquareBracketsContext.class,0);
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
		enterRule(_localctx, 4, RULE_parseComplexRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ParseComplexRuleContext)_localctx).rules =  new ArrayList<>();
			   String args = "";
			  
			setState(48);
			((ParseComplexRuleContext)_localctx).nonTerm = nonTerm();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(49);
				match(RETURNS);
				setState(50);
				((ParseComplexRuleContext)_localctx).squareBrackets = squareBrackets();
				args = ((ParseComplexRuleContext)_localctx).squareBrackets.str;
				}
			}

			setState(55);
			match(EQ);
			setState(56);
			((ParseComplexRuleContext)_localctx).a = parseRule(((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm);
			_localctx.rules.add(((ParseComplexRuleContext)_localctx).a.resRule);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RULE_OR) {
				{
				{
				setState(58);
				match(RULE_OR);
				setState(59);
				((ParseComplexRuleContext)_localctx).b = parseRule(((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm);
				_localctx.rules.add(((ParseComplexRuleContext)_localctx).b.resRule);
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(RULE_END);
			((ParseComplexRuleContext)_localctx).resRule =  new ComplexRule(((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm, _localctx.rules, ((ParseComplexRuleContext)_localctx).nonTerm.resNonTerm.getParams(), args);
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
		public NonTerm from;
		public List<State> states;
		public Rule resRule;
		public NonTermContext nonTerm;
		public TermContext term;
		public CodeContext code;
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
		public List<CodeContext> code() {
			return getRuleContexts(CodeContext.class);
		}
		public CodeContext code(int i) {
			return getRuleContext(CodeContext.class,i);
		}
		public ParseRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ParseRuleContext(ParserRuleContext parent, int invokingState, NonTerm from) {
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

	public final ParseRuleContext parseRule(NonTerm from) throws RecognitionException {
		ParseRuleContext _localctx = new ParseRuleContext(_ctx, getState(), from);
		enterRule(_localctx, 6, RULE_parseRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ParseRuleContext)_localctx).states =  new ArrayList<>();
			{
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(80);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NONTERM:
					{
					setState(71);
					((ParseRuleContext)_localctx).nonTerm = nonTerm();
					_localctx.states.add(((ParseRuleContext)_localctx).nonTerm.resNonTerm);
					}
					break;
				case TERM:
					{
					setState(74);
					((ParseRuleContext)_localctx).term = term();
					_localctx.states.add(((ParseRuleContext)_localctx).term.resTerm);
					}
					break;
				case SOME_BRACKETS:
					{
					setState(77);
					((ParseRuleContext)_localctx).code = code();
					_localctx.states.add(((ParseRuleContext)_localctx).code.resCode);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NONTERM) | (1L << SOME_BRACKETS) | (1L << TERM))) != 0) );
			}
			((ParseRuleContext)_localctx).resRule =  new Rule(_localctx.from, _localctx.states);
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
		public GrammarToken resToken;
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
		enterRule(_localctx, 8, RULE_token);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((TokenContext)_localctx).TERM = match(TERM);
			setState(87);
			match(EQ);
			setState(88);
			((TokenContext)_localctx).regex = regex();
			setState(89);
			match(RULE_END);
			((TokenContext)_localctx).resToken =  new GrammarToken(new Term((((TokenContext)_localctx).TERM!=null?((TokenContext)_localctx).TERM.getText():null)), ((TokenContext)_localctx).regex.str);
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
		enterRule(_localctx, 10, RULE_regex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
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
		public Term resTerm;
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
		enterRule(_localctx, 12, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
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
		public NonTerm resNonTerm;
		public Token NONTERM;
		public SquareBracketsContext squareBrackets;
		public TerminalNode NONTERM() { return getToken(GrammarParser.NONTERM, 0); }
		public SquareBracketsContext squareBrackets() {
			return getRuleContext(SquareBracketsContext.class,0);
		}
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
		enterRule(_localctx, 14, RULE_nonTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			((NonTermContext)_localctx).NONTERM = match(NONTERM);
			((NonTermContext)_localctx).resNonTerm =  new NonTerm((((NonTermContext)_localctx).NONTERM!=null?((NonTermContext)_localctx).NONTERM.getText():null));
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SQUARE_BRACKETS) {
				{
				setState(100);
				((NonTermContext)_localctx).squareBrackets = squareBrackets();
				((NonTermContext)_localctx).resNonTerm =  new NonTerm((((NonTermContext)_localctx).NONTERM!=null?((NonTermContext)_localctx).NONTERM.getText():null), ((NonTermContext)_localctx).squareBrackets.str);
				}
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

	public static class SquareBracketsContext extends ParserRuleContext {
		public String str;
		public Token SQUARE_BRACKETS;
		public TerminalNode SQUARE_BRACKETS() { return getToken(GrammarParser.SQUARE_BRACKETS, 0); }
		public SquareBracketsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareBrackets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSquareBrackets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSquareBrackets(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSquareBrackets(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SquareBracketsContext squareBrackets() throws RecognitionException {
		SquareBracketsContext _localctx = new SquareBracketsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_squareBrackets);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			((SquareBracketsContext)_localctx).SQUARE_BRACKETS = match(SQUARE_BRACKETS);
			((SquareBracketsContext)_localctx).str =  (((SquareBracketsContext)_localctx).SQUARE_BRACKETS!=null?((SquareBracketsContext)_localctx).SQUARE_BRACKETS.getText():null).substring(1, (((SquareBracketsContext)_localctx).SQUARE_BRACKETS!=null?((SquareBracketsContext)_localctx).SQUARE_BRACKETS.getText():null).length() - 1);
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

	public static class CodeContext extends ParserRuleContext {
		public Code resCode;
		public SomeBracketsContext someBrackets;
		public SomeBracketsContext someBrackets() {
			return getRuleContext(SomeBracketsContext.class,0);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			((CodeContext)_localctx).someBrackets = someBrackets();
			((CodeContext)_localctx).resCode =  new Code(((CodeContext)_localctx).someBrackets.str);
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

	public static class SomeBracketsContext extends ParserRuleContext {
		public String str;
		public Token SOME_BRACKETS;
		public TerminalNode SOME_BRACKETS() { return getToken(GrammarParser.SOME_BRACKETS, 0); }
		public SomeBracketsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_someBrackets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSomeBrackets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSomeBrackets(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitSomeBrackets(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SomeBracketsContext someBrackets() throws RecognitionException {
		SomeBracketsContext _localctx = new SomeBracketsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_someBrackets);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			((SomeBracketsContext)_localctx).SOME_BRACKETS = match(SOME_BRACKETS);
			((SomeBracketsContext)_localctx).str =  (((SomeBracketsContext)_localctx).SOME_BRACKETS!=null?((SomeBracketsContext)_localctx).SOME_BRACKETS.getText():null).substring(1, (((SomeBracketsContext)_localctx).SOME_BRACKETS!=null?((SomeBracketsContext)_localctx).SOME_BRACKETS.getText():null).length() - 1);
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
		enterRule(_localctx, 22, RULE_regexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21w\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3&\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\6\3.\n\3\r\3\16\3/\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"8\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4A\n\4\f\4\16\4D\13\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5S\n\5\r\5\16\5T\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t"+
		"j\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\2\2\2s\2\32\3\2\2\2\4\36\3\2\2\2\6\61\3\2\2\2"+
		"\bH\3\2\2\2\nX\3\2\2\2\f^\3\2\2\2\16a\3\2\2\2\20d\3\2\2\2\22k\3\2\2\2"+
		"\24n\3\2\2\2\26q\3\2\2\2\30t\3\2\2\2\32\33\7\13\2\2\33\34\5\26\f\2\34"+
		"\35\b\2\1\2\35\3\3\2\2\2\36\37\7\t\2\2\37 \7\3\2\2 !\7\16\2\2!%\b\3\1"+
		"\2\"#\5\2\2\2#$\b\3\1\2$&\3\2\2\2%\"\3\2\2\2%&\3\2\2\2&-\3\2\2\2\'(\5"+
		"\6\4\2()\b\3\1\2).\3\2\2\2*+\5\n\6\2+,\b\3\1\2,.\3\2\2\2-\'\3\2\2\2-*"+
		"\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\5\3\2\2\2\61\62\b\4\1\2\62"+
		"\67\5\20\t\2\63\64\7\n\2\2\64\65\5\22\n\2\65\66\b\4\1\2\668\3\2\2\2\67"+
		"\63\3\2\2\2\678\3\2\2\289\3\2\2\29:\7\17\2\2:;\5\b\5\2;B\b\4\1\2<=\7\r"+
		"\2\2=>\5\b\5\2>?\b\4\1\2?A\3\2\2\2@<\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2"+
		"\2\2CE\3\2\2\2DB\3\2\2\2EF\7\16\2\2FG\b\4\1\2G\7\3\2\2\2HR\b\5\1\2IJ\5"+
		"\20\t\2JK\b\5\1\2KS\3\2\2\2LM\5\16\b\2MN\b\5\1\2NS\3\2\2\2OP\5\24\13\2"+
		"PQ\b\5\1\2QS\3\2\2\2RI\3\2\2\2RL\3\2\2\2RO\3\2\2\2ST\3\2\2\2TR\3\2\2\2"+
		"TU\3\2\2\2UV\3\2\2\2VW\b\5\1\2W\t\3\2\2\2XY\7\f\2\2YZ\7\17\2\2Z[\5\f\7"+
		"\2[\\\7\16\2\2\\]\b\6\1\2]\13\3\2\2\2^_\5\30\r\2_`\b\7\1\2`\r\3\2\2\2"+
		"ab\7\f\2\2bc\b\b\1\2c\17\3\2\2\2de\7\3\2\2ei\b\t\1\2fg\5\22\n\2gh\b\t"+
		"\1\2hj\3\2\2\2if\3\2\2\2ij\3\2\2\2j\21\3\2\2\2kl\7\7\2\2lm\b\n\1\2m\23"+
		"\3\2\2\2no\5\26\f\2op\b\13\1\2p\25\3\2\2\2qr\7\b\2\2rs\b\f\1\2s\27\3\2"+
		"\2\2tu\7\4\2\2u\31\3\2\2\2\n%-/\67BRTi";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}