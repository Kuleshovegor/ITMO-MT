import generators.LexerGenerator;
import generators.ParserGenerator;
import generators.TreeGenerator;
import grammarParser.GrammarLexer;
import grammarParser.GrammarParser;
import objects.grammar.Grammar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Generator {
    public static void main(String[] args) throws IOException {
        if (args.length > 2 || args.length == 0) {
            System.err.println("Illegal number of arguments. Expected 1 or 2.");
            return;
        }
        Path grammarPath = Paths.get(args[0]);
        Path dirPath = Paths.get("");
        if (args.length == 2) {
            dirPath = Paths.get(args[1]);
        }
        String grammarDescription;
        try (BufferedReader bufferedReader = new BufferedReader(Files.newBufferedReader(grammarPath))) {
            grammarDescription = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(grammarDescription));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        Grammar grammar = parser.parseGrammar().grammar;
        ParserGenerator parserGenerator = new ParserGenerator(grammar);
        LexerGenerator lexerGenerator = new LexerGenerator(grammar);
        TreeGenerator treeGenerator = new TreeGenerator(grammar.getName());
        Path packagePath = dirPath.resolve(Paths.get(grammar.getName()));
        try {
            lexerGenerator.generateToFile(packagePath);
            parserGenerator.generateToFile(packagePath);
            treeGenerator.generateToFile(packagePath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("SUCCESSFUL GENERATION");
    }
}
