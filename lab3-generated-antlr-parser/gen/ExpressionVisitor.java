// Generated from C:/Users/kules/IdeaProjects/csc-kotlin/MTlab3/src/main/java\Expression.g4 by ANTLR 4.9.2

    import java.util.Map;
    import java.util.List;
    import java.util.HashMap;
    import exceptions.DivisionByZeroException;
    import exceptions.EvaluationException;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#equations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquations(ExpressionParser.EquationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(ExpressionParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ExpressionParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#moreExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreExpression(ExpressionParser.MoreExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ExpressionParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#moreTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreTerm(ExpressionParser.MoreTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#unaryOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperation(ExpressionParser.UnaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(ExpressionParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#positiveDoubleNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositiveDoubleNumber(ExpressionParser.PositiveDoubleNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(ExpressionParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#eof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEof(ExpressionParser.EofContext ctx);
}