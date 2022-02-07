import exceptions.ParserException;
import exceptions.LexicalAnalyzerException;
import booleanExpressions.LexicalAnalyzer;
import booleanExpressions.Parser;
import booleanExpressions.Tree;

import java.io.IOException;

public class BooleanExpressionsMain {
    public static void main( String[] args) throws IOException, LexicalAnalyzerException, ParserException {
        LexicalAnalyzer lex = new LexicalAnalyzer("(A not in B) and (B not in A)");
        Parser parser1 = new Parser(lex);
        Parser.ResultS result = parser1.s();
        Tree tree = result.tree;
        tree.writeGraphviz("src/main/resources/tmp.jpg");
        System.out.println(tree);
    }
}