package booleanExpressions;

import java.util.ArrayList;
import java.util.List;
import exceptions.ParserException;
import exceptions.LexicalAnalyzerException;

import java.util.Map;


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
 public static class ResultExpr {
    public Tree tree;
    
}


public ResultExpr expr() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultExpr result = new ResultExpr();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT, NOT, VAR -> {
            ResultS arg0 = s();
            treeList.add(arg0.tree);

            consume(LexicalAnalyzer.TypeToken.EOF);
            String arg1 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, NOT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("expr", treeList);
    return result;
}

 public static class ResultS {
    public Tree tree;
    
}


public ResultS s() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultS result = new ResultS();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT, NOT, VAR -> {
            ResultE arg0 = e();
            treeList.add(arg0.tree);

            ResultSPrime arg1 = sPrime();
            treeList.add(arg1.tree);

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, NOT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("s", treeList);
    return result;
}

 public static class ResultSPrime {
    public Tree tree;
    
}


public ResultSPrime sPrime() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultSPrime result = new ResultSPrime();
    switch (lex.getCurToken().getTypeToken()) {
        case XOR -> {
            consume(LexicalAnalyzer.TypeToken.XOR);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultE arg1 = e();
            treeList.add(arg1.tree);

            ResultSPrime arg2 = sPrime();
            treeList.add(arg2.tree);

        }

        case RPARENT, EOF -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: EPS, XOR, RPARENT, EOF", lex.getCurPos());
    }
    result.tree = new Tree("sPrime", treeList);
    return result;
}

 public static class ResultE {
    public Tree tree;
    
}


public ResultE e() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultE result = new ResultE();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT, NOT, VAR -> {
            ResultT arg0 = t();
            treeList.add(arg0.tree);

            ResultEPrime arg1 = ePrime();
            treeList.add(arg1.tree);

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, NOT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("e", treeList);
    return result;
}

 public static class ResultEPrime {
    public Tree tree;
    
}


public ResultEPrime ePrime() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultEPrime result = new ResultEPrime();
    switch (lex.getCurToken().getTypeToken()) {
        case OR -> {
            consume(LexicalAnalyzer.TypeToken.OR);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultT arg1 = t();
            treeList.add(arg1.tree);

            ResultEPrime arg2 = ePrime();
            treeList.add(arg2.tree);

        }

        case RPARENT, EOF, XOR -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: OR, EPS, RPARENT, EOF, XOR", lex.getCurPos());
    }
    result.tree = new Tree("ePrime", treeList);
    return result;
}

 public static class ResultT {
    public Tree tree;
    
}


public ResultT t() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultT result = new ResultT();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT, NOT, VAR -> {
            ResultM arg0 = m();
            treeList.add(arg0.tree);

            ResultTPrime arg1 = tPrime();
            treeList.add(arg1.tree);

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, NOT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("t", treeList);
    return result;
}

 public static class ResultTPrime {
    public Tree tree;
    
}


public ResultTPrime tPrime() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultTPrime result = new ResultTPrime();
    switch (lex.getCurToken().getTypeToken()) {
        case AND -> {
            consume(LexicalAnalyzer.TypeToken.AND);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultM arg1 = m();
            treeList.add(arg1.tree);

            ResultTPrime arg2 = tPrime();
            treeList.add(arg2.tree);

        }

        case OR, RPARENT, EOF, XOR -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: AND, EPS, OR, RPARENT, EOF, XOR", lex.getCurPos());
    }
    result.tree = new Tree("tPrime", treeList);
    return result;
}

 public static class ResultM {
    public Tree tree;
    
}


public ResultM m() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultM result = new ResultM();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT, VAR -> {
            ResultH arg0 = h();
            treeList.add(arg0.tree);

        }

        case NOT -> {
            consume(LexicalAnalyzer.TypeToken.NOT);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultM arg1 = m();
            treeList.add(arg1.tree);

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, NOT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("m", treeList);
    return result;
}

 public static class ResultH {
    public Tree tree;
    
}


public ResultH h() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultH result = new ResultH();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT, VAR -> {
            ResultP arg0 = p();
            treeList.add(arg0.tree);

            ResultHPrime arg1 = hPrime();
            treeList.add(arg1.tree);

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("h", treeList);
    return result;
}

 public static class ResultHPrime {
    public Tree tree;
    
}


public ResultHPrime hPrime() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultHPrime result = new ResultHPrime();
    switch (lex.getCurToken().getTypeToken()) {
        case EQEQ -> {
            consume(LexicalAnalyzer.TypeToken.EQEQ);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultP arg1 = p();
            treeList.add(arg1.tree);

            ResultHPrime arg2 = hPrime();
            treeList.add(arg2.tree);

        }

        case OR, AND, RPARENT, EOF, XOR -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: EPS, EQEQ, OR, AND, RPARENT, EOF, XOR", lex.getCurPos());
    }
    result.tree = new Tree("hPrime", treeList);
    return result;
}

 public static class ResultP {
    public Tree tree;
    
}


public ResultP p() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultP result = new ResultP();
    switch (lex.getCurToken().getTypeToken()) {
        case LPARENT -> {
            consume(LexicalAnalyzer.TypeToken.LPARENT);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultS arg1 = s();
            treeList.add(arg1.tree);

            consume(LexicalAnalyzer.TypeToken.RPARENT);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        case VAR -> {
            consume(LexicalAnalyzer.TypeToken.VAR);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            ResultK arg1 = k();
            treeList.add(arg1.tree);

        }

        
        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: LPARENT, VAR", lex.getCurPos());
    }
    result.tree = new Tree("p", treeList);
    return result;
}

 public static class ResultK {
    public Tree tree;
    
}


public ResultK k() throws LexicalAnalyzerException, ParserException {
    List<Tree> treeList = new ArrayList<>();
    ResultK result = new ResultK();
    switch (lex.getCurToken().getTypeToken()) {
        case NOT -> {
            consume(LexicalAnalyzer.TypeToken.NOT);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            consume(LexicalAnalyzer.TypeToken.IN);
            String arg1 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            consume(LexicalAnalyzer.TypeToken.VAR);
            String arg2 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        case IN -> {
            consume(LexicalAnalyzer.TypeToken.IN);
            String arg0 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

            consume(LexicalAnalyzer.TypeToken.VAR);
            String arg1 = lex.getCurToken().getBody();
            treeList.add(new Tree(lex.getCurToken()));
            lex.nextToken();

        }

        case OR, AND, EQEQ, RPARENT, EOF, XOR -> treeList.add(new Tree("EPS"));

        default -> throw new ParserException("Unexpected token:"
            + lex.getCurToken()
            + " Expected: NOT, IN, EPS, OR, AND, EQEQ, RPARENT, EOF, XOR", lex.getCurPos());
    }
    result.tree = new Tree("k", treeList);
    return result;
}

}
