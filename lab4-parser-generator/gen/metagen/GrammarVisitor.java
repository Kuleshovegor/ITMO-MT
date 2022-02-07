// Generated from C:/Users/kules/IdeaProjects/ITMO-MT/untitled/src/main/java/metagen\Grammar.g4 by ANTLR 4.9.2
package grammarParser;

import objects.*;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#parseGrammar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGram(GrammarParser.GramContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#parseComplexRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseComplexRule(GrammarParser.ParseComplexRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(GrammarParser.TokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#regex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegex(GrammarParser.RegexContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(GrammarParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#nonTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonTerm(GrammarParser.NonTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#regexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexp(GrammarParser.RegexpContext ctx);
}