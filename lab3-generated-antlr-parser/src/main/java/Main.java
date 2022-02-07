import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main( String[] args) {
        ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(
        """
        a = 2;
        b = a + 2;
        c = a + b * (b - 3);
        a = 3;
        c = a + b * (b - 3);
           """));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        List<Pair<String, Double>> evals = parser.equations().evals;
        System.out.println(evals.stream().map(pair -> pair.a + " = " + pair.b).collect(Collectors.joining(System.lineSeparator())));
    }
}