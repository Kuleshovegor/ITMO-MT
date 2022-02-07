import exceptions.LexicalAnalyzerException;
import exceptions.ParserException;
import equations.LexicalAnalyzer;
import equations.Parser;
import org.antlr.v4.runtime.misc.Pair;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EquationsMain {
    public static void main( String[] args) throws IOException, LexicalAnalyzerException, ParserException {
        LexicalAnalyzer lex = new LexicalAnalyzer("""
        a = 2;
        b = a + 1;
        c = a + b * (b - 3);
        a = 3;
        c = a + b * (b - 3);
           """);
        Parser parser1 = new Parser(lex);
        List<Pair<String, Double>> evals = parser1.equations().evals;
        System.out.println(evals.stream().map(pair -> pair.a + " = " + pair.b).collect(Collectors.joining(System.lineSeparator())));

    }
}