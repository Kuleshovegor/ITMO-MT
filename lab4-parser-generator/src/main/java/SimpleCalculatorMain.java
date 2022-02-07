import exceptions.LexicalAnalyzerException;
import exceptions.ParserException;
import simpleCalculator.LexicalAnalyzer;
import simpleCalculator.Parser;
import simpleCalculator.Tree;

import java.io.IOException;

public class SimpleCalculatorMain {
    public static void main( String[] args) throws IOException, LexicalAnalyzerException, ParserException {
        LexicalAnalyzer lex = new LexicalAnalyzer("--3");
        Parser parser1 = new Parser(lex);
        Parser.ResultExpression result = parser1.expression();
        System.out.println(result.result);
        Tree tree = result.tree;
        tree.writeGraphviz("src/main/resources/simple.jpg");
    }
}