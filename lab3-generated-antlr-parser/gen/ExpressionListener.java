// Generated from C:/Users/kules/IdeaProjects/csc-kotlin/MTlab3/src/main/java\Expression.g4 by ANTLR 4.9.2

    import java.util.Map;
    import java.util.List;
    import java.util.HashMap;
    import exceptions.DivisionByZeroException;
    import exceptions.EvaluationException;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#equations}.
	 * @param ctx the parse tree
	 */
	void enterEquations(ExpressionParser.EquationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#equations}.
	 * @param ctx the parse tree
	 */
	void exitEquations(ExpressionParser.EquationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#equation}.
	 * @param ctx the parse tree
	 */
	void enterEquation(ExpressionParser.EquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#equation}.
	 * @param ctx the parse tree
	 */
	void exitEquation(ExpressionParser.EquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#moreExpression}.
	 * @param ctx the parse tree
	 */
	void enterMoreExpression(ExpressionParser.MoreExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#moreExpression}.
	 * @param ctx the parse tree
	 */
	void exitMoreExpression(ExpressionParser.MoreExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ExpressionParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ExpressionParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#moreTerm}.
	 * @param ctx the parse tree
	 */
	void enterMoreTerm(ExpressionParser.MoreTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#moreTerm}.
	 * @param ctx the parse tree
	 */
	void exitMoreTerm(ExpressionParser.MoreTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#unaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperation(ExpressionParser.UnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#unaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperation(ExpressionParser.UnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(ExpressionParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(ExpressionParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#positiveDoubleNumber}.
	 * @param ctx the parse tree
	 */
	void enterPositiveDoubleNumber(ExpressionParser.PositiveDoubleNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#positiveDoubleNumber}.
	 * @param ctx the parse tree
	 */
	void exitPositiveDoubleNumber(ExpressionParser.PositiveDoubleNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#eof}.
	 * @param ctx the parse tree
	 */
	void enterEof(ExpressionParser.EofContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#eof}.
	 * @param ctx the parse tree
	 */
	void exitEof(ExpressionParser.EofContext ctx);
}