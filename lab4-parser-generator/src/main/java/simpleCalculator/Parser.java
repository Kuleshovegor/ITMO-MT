package simpleCalculator;

import java.util.ArrayList;
import java.util.List;
import exceptions.ParserException;
import exceptions.LexicalAnalyzerException;

    import exceptions.EvaluationException;


public class Parser {
    private final LexicalAnalyzer lex;

    public Parser(LexicalAnalyzer lex) {
        this.lex = lex;
    }

    private void consume(LexicalAnalyzer.TypeToken type) throws ParserException {
          if (type != lex.getCurToken().getTypeToken()) {
               throw new ParserException("expected: " + type +
                " but found: " + lex.getCurToken(), lex.getCurPos());
          }
    }
 public static class ResultExpression {
    public Tree tree;
    public Integer result;
}


public ResultExpression expression() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultExpression result = new ResultExpression();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, OPEN_BRACKET, INT -> {
            ResultTerm arg0 = term();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMoreExpression arg1 = moreExpression(result.result);
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.EOF);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            
        if (arg1.result != null)
            result.result = arg1.result;
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, INT", lex.getCurPos());
    }
    result.tree = new Tree("expression", treeList);
    return result;
}

 public static class ResultMoreExpression {
    public Tree tree;
    public Integer result;
}


public ResultMoreExpression moreExpression(Integer currentResult) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultMoreExpression result = new ResultMoreExpression();
    switch (lex.getCurToken().getTypeToken()) {
        case PLUS -> {
            consume(LexicalAnalyzer.TypeToken.PLUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultTerm arg1 = term();
            treeList.add(arg1.tree);

             result.result = currentResult + arg1.result; 
            ResultMoreExpression arg2 = moreExpression(result.result);
            treeList.add(arg2.tree);

            
     if (arg2.result != null)
        result.result = arg2.result;
     
        }

        case MINUS -> {
            consume(LexicalAnalyzer.TypeToken.MINUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultTerm arg1 = term();
            treeList.add(arg1.tree);

             result.result = currentResult - arg1.result; 
            ResultMoreExpression arg2 = moreExpression(result.result);
            treeList.add(arg2.tree);

            
     if (arg2.result != null)
             result.result = arg2.result;
     
        }

        case EOF -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, EPS, PLUS, EOF", lex.getCurPos());
    }
    result.tree = new Tree("moreExpression", treeList);
    return result;
}

 public static class ResultTerm {
    public Tree tree;
    public Integer result;
}


public ResultTerm term() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultTerm result = new ResultTerm();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, OPEN_BRACKET, INT -> {
            ResultPow arg0 = pow();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMoreTerm arg1 = moreTerm(result.result);
            treeList.add(arg1.tree);

            
        if (arg1.result != null)
            result.result = arg1.result;
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, INT", lex.getCurPos());
    }
    result.tree = new Tree("term", treeList);
    return result;
}

 public static class ResultMoreTerm {
    public Tree tree;
    public Integer result;
}


public ResultMoreTerm moreTerm(Integer currentResult) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultMoreTerm result = new ResultMoreTerm();
    switch (lex.getCurToken().getTypeToken()) {
        case MULT -> {
            consume(LexicalAnalyzer.TypeToken.MULT);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultPow arg1 = pow();
            treeList.add(arg1.tree);

             result.result = currentResult * arg1.result; 
            ResultMoreTerm arg2 = moreTerm(result.result);
            treeList.add(arg2.tree);

             if (arg2.result != null) result.result = arg2.result; 
        }

        case DIV -> {
            consume(LexicalAnalyzer.TypeToken.DIV);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultPow arg1 = pow();
            treeList.add(arg1.tree);

            
        if (arg1.result.equals(0))
            throw new IllegalArgumentException("div by zero");
        result.result = currentResult / arg1.result;
    
            ResultMoreTerm arg2 = moreTerm(result.result);
            treeList.add(arg2.tree);

             if (arg2.result != null) result.result = arg2.result; 
        }

        case MINUS, CLOSE_BRACKET, EOF, PLUS -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: DIV, EPS, MULT, MINUS, CLOSE_BRACKET, EOF, PLUS", lex.getCurPos());
    }
    result.tree = new Tree("moreTerm", treeList);
    return result;
}

 public static class ResultPow {
    public Tree tree;
    public Integer result;
}


public ResultPow pow() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultPow result = new ResultPow();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, OPEN_BRACKET, INT -> {
            ResultUnaryOperation arg0 = unaryOperation();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMorePow arg1 = morePow();
            treeList.add(arg1.tree);

             if (arg1.result == null)
                                 result.result = arg0.result;
                            else
                                result.result = (int) Math.pow(arg0.result, arg1.result);
                                        
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, INT", lex.getCurPos());
    }
    result.tree = new Tree("pow", treeList);
    return result;
}

 public static class ResultMorePow {
    public Tree tree;
    public Integer result;
}


public ResultMorePow morePow() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultMorePow result = new ResultMorePow();
    switch (lex.getCurToken().getTypeToken()) {
        case POW -> {
            consume(LexicalAnalyzer.TypeToken.POW);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultUnaryOperation arg1 = unaryOperation();
            treeList.add(arg1.tree);

            ResultMorePow arg2 = morePow();
            treeList.add(arg2.tree);

             if (arg2.result == null)
                       result.result = arg1.result;
                  else
                      result.result = (int) Math.pow(arg1.result, arg2.result);
                              
        }

        case MINUS, DIV, CLOSE_BRACKET, EOF, MULT, PLUS -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: POW, EPS, MINUS, DIV, CLOSE_BRACKET, EOF, MULT, PLUS", lex.getCurPos());
    }
    result.tree = new Tree("morePow", treeList);
    return result;
}

 public static class ResultUnaryOperation {
    public Tree tree;
    public Integer result;
}


public ResultUnaryOperation unaryOperation() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultUnaryOperation result = new ResultUnaryOperation();
    switch (lex.getCurToken().getTypeToken()) {
        case OPEN_BRACKET, INT -> {
            ResultAtom arg0 = atom();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
        }

        case MINUS -> {
            consume(LexicalAnalyzer.TypeToken.MINUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultUnaryOperation arg1 = unaryOperation();
            treeList.add(arg1.tree);

             result.result = -arg1.result; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, INT", lex.getCurPos());
    }
    result.tree = new Tree("unaryOperation", treeList);
    return result;
}

 public static class ResultAtom {
    public Tree tree;
    public Integer result;
}


public ResultAtom atom() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultAtom result = new ResultAtom();
    switch (lex.getCurToken().getTypeToken()) {
        case INT -> {
            ResultPositiveIntNumber arg0 = positiveIntNumber();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
        }

        case OPEN_BRACKET -> {
            consume(LexicalAnalyzer.TypeToken.OPEN_BRACKET);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultExpression arg1 = expression();
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.CLOSE_BRACKET);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

             result.result = arg1.result; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: OPEN_BRACKET, INT", lex.getCurPos());
    }
    result.tree = new Tree("atom", treeList);
    return result;
}

 public static class ResultPositiveIntNumber {
    public Tree tree;
    public Integer result;
}


public ResultPositiveIntNumber positiveIntNumber() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultPositiveIntNumber result = new ResultPositiveIntNumber();
    switch (lex.getCurToken().getTypeToken()) {
        case INT -> {
            consume(LexicalAnalyzer.TypeToken.INT);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

             result.result = Integer.valueOf(arg0); 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: INT", lex.getCurPos());
    }
    result.tree = new Tree("positiveIntNumber", treeList);
    return result;
}

}
