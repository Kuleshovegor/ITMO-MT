// Generated from C:/Users/kules/IdeaProjects/csc-kotlin/MTlab3/src/main/java\Expression.g4 by ANTLR 4.9.2

    import java.util.Map;
    import java.util.List;
    import java.util.HashMap;
    import exceptions.DivisionByZeroException;
    import exceptions.EvaluationException;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN_BRACKET=1, CLOSE_BRACKET=2, PLUS=3, MINUS=4, MULT=5, DIV=6, EQ=7, 
		POW=8, EXPRESSION_END=9, DOUBLE=10, VARIABLE=11, WS=12;
	public static final int
		RULE_equations = 0, RULE_equation = 1, RULE_expression = 2, RULE_moreExpression = 3, 
		RULE_term = 4, RULE_moreTerm = 5, RULE_unaryOperation = 6, RULE_atom = 7, 
		RULE_positiveDoubleNumber = 8, RULE_variable = 9, RULE_eof = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"equations", "equation", "expression", "moreExpression", "term", "moreTerm", 
			"unaryOperation", "atom", "positiveDoubleNumber", "variable", "eof"
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

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class EquationsContext extends ParserRuleContext {
		public List<Pair<String, Double>> evals;
		public Map<String, Double> variableToDouble;
		public EquationContext equation;
		public ExpressionContext expression;
		public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
		public List<TerminalNode> EXPRESSION_END() { return getTokens(ExpressionParser.EXPRESSION_END); }
		public TerminalNode EXPRESSION_END(int i) {
			return getToken(ExpressionParser.EXPRESSION_END, i);
		}
		public List<EquationContext> equation() {
			return getRuleContexts(EquationContext.class);
		}
		public EquationContext equation(int i) {
			return getRuleContext(EquationContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EquationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterEquations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitEquations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitEquations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationsContext equations() throws RecognitionException {
		EquationsContext _localctx = new EquationsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_equations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    ((EquationsContext)_localctx).variableToDouble =  new HashMap<>();
			    ((EquationsContext)_localctx).evals =  new ArrayList<>();
			    
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_BRACKET) | (1L << MINUS) | (1L << DOUBLE) | (1L << VARIABLE))) != 0)) {
				{
				{
				setState(29);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(23);
					((EquationsContext)_localctx).equation = equation(_localctx.variableToDouble);
					 _localctx.evals.add(new Pair<String,Double>(((EquationsContext)_localctx).equation.var, ((EquationsContext)_localctx).equation.value)); 
					}
					break;
				case 2:
					{
					setState(26);
					((EquationsContext)_localctx).expression = expression(_localctx.variableToDouble);
					 _localctx.evals.add(new Pair<String,Double>(null, ((EquationsContext)_localctx).expression.result)); 
					}
					break;
				}
				setState(31);
				match(EXPRESSION_END);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
			match(EOF);
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

	public static class EquationContext extends ParserRuleContext {
		public Map<String, Double> variableToDouble;
		public String var;
		public double value;
		public VariableContext variable;
		public ExpressionContext expression;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode EQ() { return getToken(ExpressionParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EquationContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EquationContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
		}
		@Override public int getRuleIndex() { return RULE_equation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitEquation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitEquation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationContext equation(Map<String, Double> variableToDouble) throws RecognitionException {
		EquationContext _localctx = new EquationContext(_ctx, getState(), variableToDouble);
		enterRule(_localctx, 2, RULE_equation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			((EquationContext)_localctx).variable = variable();
			setState(41);
			match(EQ);
			setState(42);
			((EquationContext)_localctx).expression = expression(_localctx.variableToDouble);

			   ((EquationContext)_localctx).var =  ((EquationContext)_localctx).variable.var;
			   ((EquationContext)_localctx).value =  ((EquationContext)_localctx).expression.result;
			   _localctx.variableToDouble.put(_localctx.var, ((EquationContext)_localctx).expression.result);
			   
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

	public static class ExpressionContext extends ParserRuleContext {
		public Map<String, Double> variableToDouble;
		public double result;
		public TermContext term;
		public MoreExpressionContext moreExpression;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public MoreExpressionContext moreExpression() {
			return getRuleContext(MoreExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpressionContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression(Map<String, Double> variableToDouble) throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState(), variableToDouble);
		enterRule(_localctx, 4, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			((ExpressionContext)_localctx).term = term(_localctx.variableToDouble);
			 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).term.result; 
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(47);
				((ExpressionContext)_localctx).moreExpression = moreExpression(_localctx.variableToDouble, _localctx.result);
				 ((ExpressionContext)_localctx).result =  ((ExpressionContext)_localctx).moreExpression.result; 
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

	public static class MoreExpressionContext extends ParserRuleContext {
		public Map<String, Double> variableToDouble;
		public double currentResult;
		public double result;
		public TermContext term;
		public MoreExpressionContext moreExpression;
		public TerminalNode PLUS() { return getToken(ExpressionParser.PLUS, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public MoreExpressionContext moreExpression() {
			return getRuleContext(MoreExpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(ExpressionParser.MINUS, 0); }
		public MoreExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public MoreExpressionContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble, double currentResult) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
			this.currentResult = currentResult;
		}
		@Override public int getRuleIndex() { return RULE_moreExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMoreExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMoreExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitMoreExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoreExpressionContext moreExpression(Map<String, Double> variableToDouble,double currentResult) throws RecognitionException {
		MoreExpressionContext _localctx = new MoreExpressionContext(_ctx, getState(), variableToDouble, currentResult);
		enterRule(_localctx, 6, RULE_moreExpression);
		int _la;
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(PLUS);
				setState(53);
				((MoreExpressionContext)_localctx).term = term(_localctx.variableToDouble);
				 ((MoreExpressionContext)_localctx).result =  _localctx.currentResult + ((MoreExpressionContext)_localctx).term.result; 
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(55);
					((MoreExpressionContext)_localctx).moreExpression = moreExpression(_localctx.variableToDouble, _localctx.result);
					 ((MoreExpressionContext)_localctx).result =  ((MoreExpressionContext)_localctx).moreExpression.result; 
					}
				}

				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(MINUS);
				setState(61);
				((MoreExpressionContext)_localctx).term = term(_localctx.variableToDouble);
				 ((MoreExpressionContext)_localctx).result =  _localctx.currentResult - ((MoreExpressionContext)_localctx).term.result; 
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(63);
					((MoreExpressionContext)_localctx).moreExpression = moreExpression(_localctx.variableToDouble, _localctx.result);
					 ((MoreExpressionContext)_localctx).result =  ((MoreExpressionContext)_localctx).moreExpression.result; 
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public Map<String, Double> variableToDouble;
		public double result;
		public UnaryOperationContext unaryOperation;
		public MoreTermContext moreTerm;
		public UnaryOperationContext unaryOperation() {
			return getRuleContext(UnaryOperationContext.class,0);
		}
		public MoreTermContext moreTerm() {
			return getRuleContext(MoreTermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TermContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term(Map<String, Double> variableToDouble) throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState(), variableToDouble);
		enterRule(_localctx, 8, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			((TermContext)_localctx).unaryOperation = unaryOperation(_localctx.variableToDouble);
			 ((TermContext)_localctx).result =  ((TermContext)_localctx).unaryOperation.result; 
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MULT || _la==DIV) {
				{
				setState(72);
				((TermContext)_localctx).moreTerm = moreTerm(_localctx.variableToDouble, _localctx.result);
				 ((TermContext)_localctx).result =  ((TermContext)_localctx).moreTerm.result; 
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

	public static class MoreTermContext extends ParserRuleContext {
		public Map<String, Double> variableToDouble;
		public double currentResult;
		public double result;
		public UnaryOperationContext unaryOperation;
		public MoreTermContext moreTerm;
		public TerminalNode MULT() { return getToken(ExpressionParser.MULT, 0); }
		public UnaryOperationContext unaryOperation() {
			return getRuleContext(UnaryOperationContext.class,0);
		}
		public MoreTermContext moreTerm() {
			return getRuleContext(MoreTermContext.class,0);
		}
		public TerminalNode DIV() { return getToken(ExpressionParser.DIV, 0); }
		public MoreTermContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public MoreTermContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble, double currentResult) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
			this.currentResult = currentResult;
		}
		@Override public int getRuleIndex() { return RULE_moreTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMoreTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMoreTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitMoreTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoreTermContext moreTerm(Map<String, Double> variableToDouble,double currentResult) throws RecognitionException {
		MoreTermContext _localctx = new MoreTermContext(_ctx, getState(), variableToDouble, currentResult);
		enterRule(_localctx, 10, RULE_moreTerm);
		int _la;
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULT:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(MULT);
				setState(78);
				((MoreTermContext)_localctx).unaryOperation = unaryOperation(_localctx.variableToDouble);
				 ((MoreTermContext)_localctx).result =  _localctx.currentResult * ((MoreTermContext)_localctx).unaryOperation.result; 
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MULT || _la==DIV) {
					{
					setState(80);
					((MoreTermContext)_localctx).moreTerm = moreTerm(_localctx.variableToDouble, _localctx.result);
					 ((MoreTermContext)_localctx).result =  ((MoreTermContext)_localctx).moreTerm.result; 
					}
				}

				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(DIV);
				setState(86);
				((MoreTermContext)_localctx).unaryOperation = unaryOperation(_localctx.variableToDouble);

				        ((MoreTermContext)_localctx).result =  _localctx.currentResult / ((MoreTermContext)_localctx).unaryOperation.result;
				    
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MULT || _la==DIV) {
					{
					setState(88);
					((MoreTermContext)_localctx).moreTerm = moreTerm(_localctx.variableToDouble, _localctx.result);
					 ((MoreTermContext)_localctx).result =  ((MoreTermContext)_localctx).moreTerm.result; 
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class UnaryOperationContext extends ParserRuleContext {
		public Map<String, Double> variableToDouble;
		public double result;
		public AtomContext atom;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(ExpressionParser.MINUS, 0); }
		public UnaryOperationContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public UnaryOperationContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
		}
		@Override public int getRuleIndex() { return RULE_unaryOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterUnaryOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitUnaryOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitUnaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperationContext unaryOperation(Map<String, Double> variableToDouble) throws RecognitionException {
		UnaryOperationContext _localctx = new UnaryOperationContext(_ctx, getState(), variableToDouble);
		enterRule(_localctx, 12, RULE_unaryOperation);
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACKET:
			case DOUBLE:
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				((UnaryOperationContext)_localctx).atom = atom(_localctx.variableToDouble);
				 ((UnaryOperationContext)_localctx).result =  ((UnaryOperationContext)_localctx).atom.result; 
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(MINUS);
				setState(99);
				((UnaryOperationContext)_localctx).atom = atom(_localctx.variableToDouble);
				 ((UnaryOperationContext)_localctx).result =  -((UnaryOperationContext)_localctx).atom.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class AtomContext extends ParserRuleContext {
		public Map<String, Double> variableToDouble;
		public double result;
		public PositiveDoubleNumberContext positiveDoubleNumber;
		public VariableContext variable;
		public ExpressionContext expression;
		public PositiveDoubleNumberContext positiveDoubleNumber() {
			return getRuleContext(PositiveDoubleNumberContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(ExpressionParser.OPEN_BRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(ExpressionParser.CLOSE_BRACKET, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AtomContext(ParserRuleContext parent, int invokingState, Map<String, Double> variableToDouble) {
			super(parent, invokingState);
			this.variableToDouble = variableToDouble;
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom(Map<String, Double> variableToDouble) throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState(), variableToDouble);
		enterRule(_localctx, 14, RULE_atom);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				((AtomContext)_localctx).positiveDoubleNumber = positiveDoubleNumber();
				 ((AtomContext)_localctx).result =  ((AtomContext)_localctx).positiveDoubleNumber.result; 
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				((AtomContext)_localctx).variable = variable();

				        if (!_localctx.variableToDouble.containsKey(((AtomContext)_localctx).variable.var)) {
				            throw new EvaluationException("no exsits variable " + ((AtomContext)_localctx).variable.var);
				        }

				        ((AtomContext)_localctx).result =  _localctx.variableToDouble.get(((AtomContext)_localctx).variable.var);
				    
				}
				break;
			case OPEN_BRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				match(OPEN_BRACKET);
				setState(111);
				((AtomContext)_localctx).expression = expression(_localctx.variableToDouble);
				setState(112);
				match(CLOSE_BRACKET);
				 ((AtomContext)_localctx).result =  ((AtomContext)_localctx).expression.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class PositiveDoubleNumberContext extends ParserRuleContext {
		public double result;
		public Token DOUBLE;
		public TerminalNode DOUBLE() { return getToken(ExpressionParser.DOUBLE, 0); }
		public PositiveDoubleNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positiveDoubleNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPositiveDoubleNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPositiveDoubleNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitPositiveDoubleNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositiveDoubleNumberContext positiveDoubleNumber() throws RecognitionException {
		PositiveDoubleNumberContext _localctx = new PositiveDoubleNumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_positiveDoubleNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			((PositiveDoubleNumberContext)_localctx).DOUBLE = match(DOUBLE);
			 ((PositiveDoubleNumberContext)_localctx).result =  Double.valueOf((((PositiveDoubleNumberContext)_localctx).DOUBLE!=null?((PositiveDoubleNumberContext)_localctx).DOUBLE.getText():null)); 
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

	public static class VariableContext extends ParserRuleContext {
		public String var;
		public Token VARIABLE;
		public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			((VariableContext)_localctx).VARIABLE = match(VARIABLE);
			 ((VariableContext)_localctx).var =  (((VariableContext)_localctx).VARIABLE!=null?((VariableContext)_localctx).VARIABLE.getText():null); 
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

	public static class EofContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
		public EofContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eof; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterEof(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitEof(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitEof(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EofContext eof() throws RecognitionException {
		EofContext _localctx = new EofContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_eof);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(EOF);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16\u0080\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2 \n\2\3\2\3\2\7\2$\n\2\f"+
		"\2\16\2\'\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4\65"+
		"\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5=\n\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5E\n\5"+
		"\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7V\n"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7^\n\7\5\7`\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\5\bi\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tv\n\t\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\2"+
		"\2\u0081\2\30\3\2\2\2\4*\3\2\2\2\6/\3\2\2\2\bF\3\2\2\2\nH\3\2\2\2\f_\3"+
		"\2\2\2\16h\3\2\2\2\20u\3\2\2\2\22w\3\2\2\2\24z\3\2\2\2\26}\3\2\2\2\30"+
		"%\b\2\1\2\31\32\5\4\3\2\32\33\b\2\1\2\33 \3\2\2\2\34\35\5\6\4\2\35\36"+
		"\b\2\1\2\36 \3\2\2\2\37\31\3\2\2\2\37\34\3\2\2\2 !\3\2\2\2!\"\7\13\2\2"+
		"\"$\3\2\2\2#\37\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%\3"+
		"\2\2\2()\7\2\2\3)\3\3\2\2\2*+\5\24\13\2+,\7\t\2\2,-\5\6\4\2-.\b\3\1\2"+
		".\5\3\2\2\2/\60\5\n\6\2\60\64\b\4\1\2\61\62\5\b\5\2\62\63\b\4\1\2\63\65"+
		"\3\2\2\2\64\61\3\2\2\2\64\65\3\2\2\2\65\7\3\2\2\2\66\67\7\5\2\2\678\5"+
		"\n\6\28<\b\5\1\29:\5\b\5\2:;\b\5\1\2;=\3\2\2\2<9\3\2\2\2<=\3\2\2\2=G\3"+
		"\2\2\2>?\7\6\2\2?@\5\n\6\2@D\b\5\1\2AB\5\b\5\2BC\b\5\1\2CE\3\2\2\2DA\3"+
		"\2\2\2DE\3\2\2\2EG\3\2\2\2F\66\3\2\2\2F>\3\2\2\2G\t\3\2\2\2HI\5\16\b\2"+
		"IM\b\6\1\2JK\5\f\7\2KL\b\6\1\2LN\3\2\2\2MJ\3\2\2\2MN\3\2\2\2N\13\3\2\2"+
		"\2OP\7\7\2\2PQ\5\16\b\2QU\b\7\1\2RS\5\f\7\2ST\b\7\1\2TV\3\2\2\2UR\3\2"+
		"\2\2UV\3\2\2\2V`\3\2\2\2WX\7\b\2\2XY\5\16\b\2Y]\b\7\1\2Z[\5\f\7\2[\\\b"+
		"\7\1\2\\^\3\2\2\2]Z\3\2\2\2]^\3\2\2\2^`\3\2\2\2_O\3\2\2\2_W\3\2\2\2`\r"+
		"\3\2\2\2ab\5\20\t\2bc\b\b\1\2ci\3\2\2\2de\7\6\2\2ef\5\20\t\2fg\b\b\1\2"+
		"gi\3\2\2\2ha\3\2\2\2hd\3\2\2\2i\17\3\2\2\2jk\5\22\n\2kl\b\t\1\2lv\3\2"+
		"\2\2mn\5\24\13\2no\b\t\1\2ov\3\2\2\2pq\7\3\2\2qr\5\6\4\2rs\7\4\2\2st\b"+
		"\t\1\2tv\3\2\2\2uj\3\2\2\2um\3\2\2\2up\3\2\2\2v\21\3\2\2\2wx\7\f\2\2x"+
		"y\b\n\1\2y\23\3\2\2\2z{\7\r\2\2{|\b\13\1\2|\25\3\2\2\2}~\7\2\2\3~\27\3"+
		"\2\2\2\16\37%\64<DFMU]_hu";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}