grammar Grammar;

@parser::header {
import java.util.regex.Pattern;
import objects.grammar.*;
}

imports returns [String str]
    : IMPRORTS someBrackets
    {$str = $someBrackets.str;}
    ;

parseGrammar returns [Grammar grammar]:
  GRAMMAR NONTERM RULE_END
  {$grammar = new Grammar($NONTERM.text);}
  (imports {$grammar.setImports($imports.str);})?
  (parseComplexRule {$grammar.addComplexRule($parseComplexRule.resRule);}
  | token {$grammar.addToken($token.resToken);})+
  ;

parseComplexRule returns [List<Rule> rules, ComplexRule resRule]:
  {$rules = new ArrayList<>();
   String args = "";
  }
  nonTerm (RETURNS squareBrackets {args = $squareBrackets.str;})? EQ a=parseRule[$nonTerm.resNonTerm] {$rules.add($a.resRule);} (RULE_OR b=parseRule[$nonTerm.resNonTerm] {$rules.add($b.resRule);})* RULE_END
  {$resRule = new ComplexRule($nonTerm.resNonTerm, $rules, $nonTerm.resNonTerm.getParams(), args);}
  ;

parseRule [NonTerm from] returns [List<State> states, Rule resRule]:
  {$states = new ArrayList<>();}
  ((nonTerm {$states.add($nonTerm.resNonTerm);}
  | term {$states.add($term.resTerm);}
  | code {$states.add($code.resCode);})+)
  {$resRule = new Rule($from, $states);}
  ;

token returns [GrammarToken resToken]:
  TERM EQ regex RULE_END
  {$resToken = new GrammarToken(new Term($TERM.text), $regex.str);}
  ;

regex returns [String str]:
  regexp
  {$str = $regexp.text;}
  ;

term returns [Term resTerm]
    : TERM
    {$resTerm = new Term($TERM.text);}
    ;

nonTerm returns [NonTerm resNonTerm]
    : NONTERM {$resNonTerm = new NonTerm($NONTERM.text);} (squareBrackets {$resNonTerm = new NonTerm($NONTERM.text, $squareBrackets.str);})?
    ;

squareBrackets returns [String str]:
    SQUARE_BRACKETS
    {$str = $SQUARE_BRACKETS.text.substring(1, $SQUARE_BRACKETS.text.length() - 1);}
    ;

code returns [Code resCode]:
    someBrackets
    {$resCode = new Code($someBrackets.str);}
    ;

someBrackets returns [String str]
    : SOME_BRACKETS
    {$str = $SOME_BRACKETS.text.substring(1, $SOME_BRACKETS.text.length() - 1);}
    ;

regexp :
  REGEXP
  ;

NONTERM:
  [a-z]([a-zA-Z0-9])*
  ;

REGEXP: '"'.*?'"';
L_SQUARE_BRACKETS: '[';
R_SQUARE_BRACKETS: ']';
SQUARE_BRACKETS: L_SQUARE_BRACKETS.*?R_SQUARE_BRACKETS;
SOME_BRACKETS: '{'.*?'}';

GRAMMAR: '@grammar';

RETURNS: '@returns';

IMPRORTS: '@imports';

TERM:
  [A-Z]([A-Z]|'_'|[0-9])*
  ;

RULE_OR: '|';

RULE_END: ';';

EQ:
  '='
  ;

DOUBLE_QUOTES:
  '"'
  ;

WS: [ \r\n\t]+ -> skip;