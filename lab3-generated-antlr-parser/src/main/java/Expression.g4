grammar Expression;

@parser::header {
    import java.util.Map;
    import java.util.List;
    import java.util.HashMap;
    import exceptions.DivisionByZeroException;
    import exceptions.EvaluationException;
}

equations returns [List<Pair<String, Double>> evals, Map<String, Double> variableToDouble]
    : {
    $variableToDouble = new HashMap<>();
    $evals = new ArrayList<>();
    } (
    (equation[$variableToDouble] { $evals.add(new Pair<String,Double>($equation.var, $equation.value)); }
    | expression[$variableToDouble] { $evals.add(new Pair<String,Double>(null, $expression.result)); })
    EXPRESSION_END)* EOF
    ;

equation[Map<String, Double> variableToDouble] returns [String var, double value]
   : variable EQ expression[$variableToDouble] {
   $var = $variable.var;
   $value = $expression.result;
   $variableToDouble.put($var, $expression.result);
   }
   ;

expression [Map<String, Double> variableToDouble] returns [double result]
   : term[$variableToDouble] { $result = $term.result; }
    (moreExpression[$variableToDouble, $result] { $result = $moreExpression.result; })?
   ;

moreExpression[Map<String, Double> variableToDouble, double currentResult] returns [double result]
    : PLUS term[$variableToDouble] { $result = $currentResult + $term.result; }
     (moreExpression[$variableToDouble, $result] { $result = $moreExpression.result; })?
    | MINUS term[$variableToDouble] { $result = $currentResult - $term.result; }
     (moreExpression[$variableToDouble, $result] { $result = $moreExpression.result; })?
    ;

term [Map<String, Double> variableToDouble] returns [double result]
   : unaryOperation[$variableToDouble] { $result = $unaryOperation.result; }
    (moreTerm[$variableToDouble, $result] { $result = $moreTerm.result; })?
   ;

moreTerm[Map<String, Double> variableToDouble, double currentResult] returns [double result]
    : MULT unaryOperation[$variableToDouble] { $result = $currentResult * $unaryOperation.result; }
     (moreTerm[$variableToDouble, $result] { $result = $moreTerm.result; })?
    | DIV unaryOperation[$variableToDouble] {
        $result = $currentResult / $unaryOperation.result;
    }
    (moreTerm[$variableToDouble, $result] { $result = $moreTerm.result; })?
    ;

unaryOperation [Map<String, Double> variableToDouble] returns [double result]
    : atom[$variableToDouble] { $result = $atom.result; }
    | MINUS atom[$variableToDouble] { $result = -$atom.result; }
    ;

atom [Map<String, Double> variableToDouble] returns [double result]
    : positiveDoubleNumber { $result = $positiveDoubleNumber.result; }
    | variable {
        if (!$variableToDouble.containsKey($variable.var)) {
            throw new EvaluationException("no exsits variable " + $variable.var);
        }

        $result = $variableToDouble.get($variable.var);
    }
    | OPEN_BRACKET expression[$variableToDouble] CLOSE_BRACKET { $result = $expression.result; }
    ;

positiveDoubleNumber returns [double result]
    : DOUBLE { $result = Double.valueOf($DOUBLE.text); }
    ;

variable returns [String var]
    : VARIABLE { $var = $VARIABLE.text; }
    ;

eof
    : EOF
    ;

OPEN_BRACKET: '(';


CLOSE_BRACKET: ')';

PLUS: '+';

MINUS: '-';

MULT: '*';

DIV: '/';

EQ: '=';

POW: '^';

EXPRESSION_END: ';';

DOUBLE: NUMBER+('.'NUMBER+('E'(MINUS)?NUMBER+)?)?;

VARIABLE: CHAR (CHAR | NUMBER | '_')*;

fragment CHAR: ('a' .. 'z') | ('A' .. 'Z');

fragment NUMBER: [0-9];

WS: [ \r\n\t]+ -> skip;