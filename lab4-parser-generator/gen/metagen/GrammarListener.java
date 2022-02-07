// Generated from C:/Users/kules/IdeaProjects/ITMO-MT/untitled/src/main/java/metagen\Grammar.g4 by ANTLR 4.9.2
package grammarParser;

import objects.*;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#parseGrammar}.
	 * @param ctx the parse tree
	 */
	void enterGram(GrammarParser.GramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#parseGrammar}.
	 * @param ctx the parse tree
	 */
	void exitGram(GrammarParser.GramContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#parseComplexRule}.
	 * @param ctx the parse tree
	 */
	void enterParseComplexRule(GrammarParser.ParseComplexRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#parseComplexRule}.
	 * @param ctx the parse tree
	 */
	void exitParseComplexRule(GrammarParser.ParseComplexRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void enterParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void exitParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(GrammarParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(GrammarParser.TokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#regex}.
	 * @param ctx the parse tree
	 */
	void enterRegex(GrammarParser.RegexContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#regex}.
	 * @param ctx the parse tree
	 */
	void exitRegex(GrammarParser.RegexContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#nonTerm}.
	 * @param ctx the parse tree
	 */
	void enterNonTerm(GrammarParser.NonTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#nonTerm}.
	 * @param ctx the parse tree
	 */
	void exitNonTerm(GrammarParser.NonTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#regexp}.
	 * @param ctx the parse tree
	 */
	void enterRegexp(GrammarParser.RegexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#regexp}.
	 * @param ctx the parse tree
	 */
	void exitRegexp(GrammarParser.RegexpContext ctx);
}