package equations;

import java.util.ArrayList;
import java.util.List;
import exceptions.ParserException;
import exceptions.LexicalAnalyzerException;

    import java.util.Map;
    import java.util.List;
    import java.util.HashMap;
    import org.antlr.v4.runtime.misc.Pair;
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
 public static class ResultEquations {
    public Tree tree;
    public List<Pair<String, Double>> evals;
public  Map<String, Double> variableToDouble;
}


public ResultEquations equations() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultEquations result = new ResultEquations();
    switch (lex.getCurToken().getTypeToken()) {
        case VARIABLE -> {
            
    result.variableToDouble = new HashMap<>();
    result.evals = new ArrayList<>();
    
            ResultVariable arg0 = variable();
            treeList.add(arg0.tree);

            ResultEquationsAfterVar arg1 = equationsAfterVar(arg0.var, result.evals, result.variableToDouble);
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.EXPRESSION_END);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultMoreEquations arg3 = moreEquations(result.evals, result.variableToDouble);
            treeList.add(arg3.tree);

            consume(LexicalAnalyzer.TypeToken.EOF);
            String arg4 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        case MINUS, OPEN_BRACKET, DOUBLE -> {
            
         result.variableToDouble = new HashMap<>();
         result.evals = new ArrayList<>();
     
            ResultExpressionWithoutVar arg0 = expressionWithoutVar(result.variableToDouble);
            treeList.add(arg0.tree);

            consume(LexicalAnalyzer.TypeToken.EXPRESSION_END);
            String arg1 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            result.evals.add(new Pair<String,Double>(null, arg0.result)); 
            ResultMoreEquations arg2 = moreEquations(result.evals, result.variableToDouble);
            treeList.add(arg2.tree);

            consume(LexicalAnalyzer.TypeToken.EOF);
            String arg3 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, VARIABLE, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("equations", treeList);
    return result;
}

 public static class ResultEquationsAfterVar {
    public Tree tree;
    
}


public ResultEquationsAfterVar equationsAfterVar(String var, List<Pair<String, Double>> evals, Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultEquationsAfterVar result = new ResultEquationsAfterVar();
    switch (lex.getCurToken().getTypeToken()) {
        case EQ -> {
            ResultEquationAfterVar arg0 = equationAfterVar(var, variableToDouble);
            treeList.add(arg0.tree);

             evals.add(new Pair<String,Double>(var, arg0.value)); 
        }

        case EXPRESSION_END -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: DIV, EPS, EQ, MULT, EXPRESSION_END", lex.getCurPos());
    }
    result.tree = new Tree("equationsAfterVar", treeList);
    return result;
}

 public static class ResultMoreEquations {
    public Tree tree;
    
}


public ResultMoreEquations moreEquations(List<Pair<String, Double>> evals, Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultMoreEquations result = new ResultMoreEquations();
    switch (lex.getCurToken().getTypeToken()) {
        case VARIABLE -> {
            ResultVariable arg0 = variable();
            treeList.add(arg0.tree);

            ResultEquationsAfterVar arg1 = equationsAfterVar(arg0.var, evals, variableToDouble);
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.EXPRESSION_END);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultMoreEquations arg3 = moreEquations(evals, variableToDouble);
            treeList.add(arg3.tree);

        }

        case MINUS, OPEN_BRACKET, DOUBLE -> {
            ResultExpressionWithoutVar arg0 = expressionWithoutVar(variableToDouble);
            treeList.add(arg0.tree);

            consume(LexicalAnalyzer.TypeToken.EXPRESSION_END);
            String arg1 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            evals.add(new Pair<String,Double>(null, arg0.result)); 
            ResultMoreEquations arg2 = moreEquations(evals, variableToDouble);
            treeList.add(arg2.tree);

        }

        case EOF -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, VARIABLE, EPS, OPEN_BRACKET, DOUBLE, EOF", lex.getCurPos());
    }
    result.tree = new Tree("moreEquations", treeList);
    return result;
}

 public static class ResultEquationAfterVar {
    public Tree tree;
    public Double value;
}


public ResultEquationAfterVar equationAfterVar(String var, Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultEquationAfterVar result = new ResultEquationAfterVar();
    switch (lex.getCurToken().getTypeToken()) {
        case EQ -> {
            consume(LexicalAnalyzer.TypeToken.EQ);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultExpression arg1 = expression(variableToDouble);
            treeList.add(arg1.tree);

            
    result.value = arg1.result;
    variableToDouble.put(var, arg1.result);
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: EQ", lex.getCurPos());
    }
    result.tree = new Tree("equationAfterVar", treeList);
    return result;
}

 public static class ResultExpressionAfterVar {
    public Tree tree;
    public Double result;
}


public ResultExpressionAfterVar expressionAfterVar(Double tek, Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultExpressionAfterVar result = new ResultExpressionAfterVar();
    switch (lex.getCurToken().getTypeToken()) {
        
        case EXPRESSION_END -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: DIV, EPS, MULT, EXPRESSION_END", lex.getCurPos());
    }
    result.tree = new Tree("expressionAfterVar", treeList);
    return result;
}

 public static class ResultExpression {
    public Tree tree;
    public Double result;
}


public ResultExpression expression(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultExpression result = new ResultExpression();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, VARIABLE, OPEN_BRACKET, DOUBLE -> {
            ResultTerm arg0 = term(variableToDouble);
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMoreExpression arg1 = moreExpression(variableToDouble, result.result);
            treeList.add(arg1.tree);

            
        if (arg1.result != null)
            result.result = arg1.result;
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, VARIABLE, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("expression", treeList);
    return result;
}

 public static class ResultMoreExpression {
    public Tree tree;
    public Double result;
}


public ResultMoreExpression moreExpression(Map<String, Double> variableToDouble, Double currentResult) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultMoreExpression result = new ResultMoreExpression();
    switch (lex.getCurToken().getTypeToken()) {
        case PLUS -> {
            consume(LexicalAnalyzer.TypeToken.PLUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultTerm arg1 = term(variableToDouble);
            treeList.add(arg1.tree);

             result.result = currentResult + arg1.result; 
            ResultMoreExpression arg2 = moreExpression(variableToDouble, result.result);
            treeList.add(arg2.tree);

            
     if (arg2.result != null)
        result.result = arg2.result;
     
        }

        case MINUS -> {
            consume(LexicalAnalyzer.TypeToken.MINUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultTerm arg1 = term(variableToDouble);
            treeList.add(arg1.tree);

             result.result = currentResult - arg1.result; 
            ResultMoreExpression arg2 = moreExpression(variableToDouble, result.result);
            treeList.add(arg2.tree);

            
     if (arg2.result != null)
             result.result = arg2.result;
     
        }

        case EXPRESSION_END, CLOSE_BRACKET -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, EPS, PLUS, EXPRESSION_END, CLOSE_BRACKET", lex.getCurPos());
    }
    result.tree = new Tree("moreExpression", treeList);
    return result;
}

 public static class ResultTerm {
    public Tree tree;
    public Double result;
}


public ResultTerm term(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultTerm result = new ResultTerm();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, VARIABLE, OPEN_BRACKET, DOUBLE -> {
            ResultUnaryOperation arg0 = unaryOperation(variableToDouble);
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMoreTerm arg1 = moreTerm(variableToDouble, result.result);
            treeList.add(arg1.tree);

            
        if (arg1.result != null)
            result.result = arg1.result;
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, VARIABLE, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("term", treeList);
    return result;
}

 public static class ResultMoreTerm {
    public Tree tree;
    public Double result;
}


public ResultMoreTerm moreTerm(Map<String, Double> variableToDouble, Double currentResult) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultMoreTerm result = new ResultMoreTerm();
    switch (lex.getCurToken().getTypeToken()) {
        case MULT -> {
            consume(LexicalAnalyzer.TypeToken.MULT);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultUnaryOperation arg1 = unaryOperation(variableToDouble);
            treeList.add(arg1.tree);

             result.result = currentResult * arg1.result; 
            ResultMoreTerm arg2 = moreTerm(variableToDouble, result.result);
            treeList.add(arg2.tree);

             if (arg2.result != null) result.result = arg2.result; 
        }

        case DIV -> {
            consume(LexicalAnalyzer.TypeToken.DIV);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultUnaryOperation arg1 = unaryOperation(variableToDouble);
            treeList.add(arg1.tree);

            
        result.result = currentResult / arg1.result;
    
            ResultMoreTerm arg2 = moreTerm(variableToDouble, result.result);
            treeList.add(arg2.tree);

             if (arg2.result != null) result.result = arg2.result; 
        }

        case MINUS, EXPRESSION_END, CLOSE_BRACKET, PLUS -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: DIV, EPS, MULT, MINUS, EXPRESSION_END, CLOSE_BRACKET, PLUS", lex.getCurPos());
    }
    result.tree = new Tree("moreTerm", treeList);
    return result;
}

 public static class ResultUnaryOperation {
    public Tree tree;
    public Double result;
}


public ResultUnaryOperation unaryOperation(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultUnaryOperation result = new ResultUnaryOperation();
    switch (lex.getCurToken().getTypeToken()) {
        case VARIABLE, OPEN_BRACKET, DOUBLE -> {
            ResultAtom arg0 = atom(variableToDouble);
            treeList.add(arg0.tree);

             result.result = arg0.result; 
        }

        case MINUS -> {
            consume(LexicalAnalyzer.TypeToken.MINUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultAtom arg1 = atom(variableToDouble);
            treeList.add(arg1.tree);

             result.result = -arg1.result; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, VARIABLE, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("unaryOperation", treeList);
    return result;
}

 public static class ResultAtom {
    public Tree tree;
    public Double result;
}


public ResultAtom atom(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultAtom result = new ResultAtom();
    switch (lex.getCurToken().getTypeToken()) {
        case DOUBLE -> {
            ResultPositiveDoubleNumber arg0 = positiveDoubleNumber();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
        }

        case VARIABLE -> {
            ResultVariable arg0 = variable();
            treeList.add(arg0.tree);

            
        if (!variableToDouble.containsKey(arg0.var))
            throw new EvaluationException("Variable does not exists " + arg0.var);


        result.result = variableToDouble.get(arg0.var);
    
        }

        case OPEN_BRACKET -> {
            consume(LexicalAnalyzer.TypeToken.OPEN_BRACKET);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultExpression arg1 = expression(variableToDouble);
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.CLOSE_BRACKET);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

             result.result = arg1.result; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: VARIABLE, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("atom", treeList);
    return result;
}

 public static class ResultExpressionWithoutVar {
    public Tree tree;
    public Double result;
}


public ResultExpressionWithoutVar expressionWithoutVar(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultExpressionWithoutVar result = new ResultExpressionWithoutVar();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, OPEN_BRACKET, DOUBLE -> {
            ResultTermWithoutVar arg0 = termWithoutVar(variableToDouble);
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMoreExpression arg1 = moreExpression(variableToDouble, result.result);
            treeList.add(arg1.tree);

            
        if (arg1.result != null)
            result.result = arg1.result;
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("expressionWithoutVar", treeList);
    return result;
}

 public static class ResultTermWithoutVar {
    public Tree tree;
    public Double result;
}


public ResultTermWithoutVar termWithoutVar(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultTermWithoutVar result = new ResultTermWithoutVar();
    switch (lex.getCurToken().getTypeToken()) {
        case MINUS, OPEN_BRACKET, DOUBLE -> {
            ResultUnaryOperationWithoutVar arg0 = unaryOperationWithoutVar(variableToDouble);
            treeList.add(arg0.tree);

             result.result = arg0.result; 
            ResultMoreTerm arg1 = moreTerm(variableToDouble, result.result);
            treeList.add(arg1.tree);

            
        if (arg1.result != null)
            result.result = arg1.result;
    
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("termWithoutVar", treeList);
    return result;
}

 public static class ResultUnaryOperationWithoutVar {
    public Tree tree;
    public Double result;
}


public ResultUnaryOperationWithoutVar unaryOperationWithoutVar(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultUnaryOperationWithoutVar result = new ResultUnaryOperationWithoutVar();
    switch (lex.getCurToken().getTypeToken()) {
        case OPEN_BRACKET, DOUBLE -> {
            ResultAtomWithoutVar arg0 = atomWithoutVar(variableToDouble);
            treeList.add(arg0.tree);

             result.result = arg0.result; 
        }

        case MINUS -> {
            consume(LexicalAnalyzer.TypeToken.MINUS);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultAtomWithoutVar arg1 = atomWithoutVar(variableToDouble);
            treeList.add(arg1.tree);

             result.result = -arg1.result; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: MINUS, OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("unaryOperationWithoutVar", treeList);
    return result;
}

 public static class ResultAtomWithoutVar {
    public Tree tree;
    public Double result;
}


public ResultAtomWithoutVar atomWithoutVar(Map<String, Double> variableToDouble) throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultAtomWithoutVar result = new ResultAtomWithoutVar();
    switch (lex.getCurToken().getTypeToken()) {
        case DOUBLE -> {
            ResultPositiveDoubleNumber arg0 = positiveDoubleNumber();
            treeList.add(arg0.tree);

             result.result = arg0.result; 
        }

        case OPEN_BRACKET -> {
            consume(LexicalAnalyzer.TypeToken.OPEN_BRACKET);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultExpression arg1 = expression(variableToDouble);
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.CLOSE_BRACKET);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

             result.result = arg1.result; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: OPEN_BRACKET, DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("atomWithoutVar", treeList);
    return result;
}

 public static class ResultPositiveDoubleNumber {
    public Tree tree;
    public Double result;
}


public ResultPositiveDoubleNumber positiveDoubleNumber() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultPositiveDoubleNumber result = new ResultPositiveDoubleNumber();
    switch (lex.getCurToken().getTypeToken()) {
        case DOUBLE -> {
            consume(LexicalAnalyzer.TypeToken.DOUBLE);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

             result.result = Double.valueOf(arg0); 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: DOUBLE", lex.getCurPos());
    }
    result.tree = new Tree("positiveDoubleNumber", treeList);
    return result;
}

 public static class ResultVariable {
    public Tree tree;
    public String var;
}


public ResultVariable variable() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultVariable result = new ResultVariable();
    switch (lex.getCurToken().getTypeToken()) {
        case VARIABLE -> {
            consume(LexicalAnalyzer.TypeToken.VARIABLE);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

             result.var = arg0; 
        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: VARIABLE", lex.getCurPos());
    }
    result.tree = new Tree("variable", treeList);
    return result;
}

 public static class ResultEof {
    public Tree tree;
    
}


public ResultEof eof() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultEof result = new ResultEof();
    switch (lex.getCurToken().getTypeToken()) {
        case EOF -> {
            consume(LexicalAnalyzer.TypeToken.EOF);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: EOF", lex.getCurPos());
    }
    result.tree = new Tree("eof", treeList);
    return result;
}

}
