package equations;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.LexicalAnalyzerException;

public class LexicalAnalyzer {
    private final List<ParseToken> parseTokens;
    public static Token EOF = new Token(TypeToken.EOF, null);

    public LexicalAnalyzer(String string) throws LexicalAnalyzerException {
        if (string == null) {
            throw new IllegalArgumentException("Input stream is null.");
        }
        this.string = string;
        parseTokens = List.of(
new ParseToken(TypeToken.MINUS, Pattern.compile("-")), 
new ParseToken(TypeToken.DIV, Pattern.compile("/")), 
new ParseToken(TypeToken.VARIABLE, Pattern.compile("[a-zA-Z][[a-zA-Z][0-9]_]*")), 
new ParseToken(TypeToken.EXPRESSION_END, Pattern.compile(";")), 
new ParseToken(TypeToken.CLOSE_BRACKET, Pattern.compile("\\)")), 
new ParseToken(TypeToken.OPEN_BRACKET, Pattern.compile("\\(")), 
new ParseToken(TypeToken.DOUBLE, Pattern.compile("[0-9]+(\\.[0-9]+(E(-)?[0-9]+)?)?")), 
new ParseToken(TypeToken.MULT, Pattern.compile("\\*")), 
new ParseToken(TypeToken.EQ, Pattern.compile("=")), 
new ParseToken(TypeToken.PLUS, Pattern.compile("\\+"))
);
        nextToken();
    }

    public enum TypeToken {
EOF, 
EPS, 
OPEN_BRACKET, 
CLOSE_BRACKET, 
PLUS, 
MINUS, 
MULT, 
DIV, 
EQ, 
EXPRESSION_END, 
DOUBLE, 
VARIABLE
}

    private class ParseToken {
        private final TypeToken typeToken;
        private final Matcher matcher;

        public ParseToken(TypeToken typeToken, Pattern pattern) {
            this.typeToken = typeToken;
            this.matcher = pattern.matcher(string);
        }
    }

    public static class Token {
        final TypeToken typeToken;
        final String body;

        public Token(TypeToken typeToken, String body) {
            this.typeToken = typeToken;
            this.body = body;
        }

        public TypeToken getTypeToken() {
             return typeToken;
        }

        public String getBody() {
           return body;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Token token = (Token) o;
            return typeToken == token.typeToken && Objects.equals(body, token.body);
        }

        @Override
        public int hashCode() {
            return Objects.hash(typeToken, body);
        }

        @Override
        public String toString() {
            return "Token{" +
                    "typeToken=" + typeToken +
                    ", body='" + body + '\'' +
                    '}';
        }
    }

    private boolean isBlank(int c) {
        return Character.isWhitespace(c);
    }

    private final String string;
    private int curPos = 0;
    private Token curToken;

    public int getCurPos() {
        return curPos;
    }

    public void nextToken() throws LexicalAnalyzerException {
        while (curPos < string.length() && isBlank(string.charAt(curPos))) {
            curPos++;
        }
        if (curPos >= string.length()) {
            curToken = EOF;
            return;
        }
        Token res = null;
        for (ParseToken parseToken : parseTokens) {
            if (parseToken.matcher.find(curPos)) {
                if (parseToken.matcher.start() == curPos && (res == null || res.body.length() <= parseToken.matcher.end() - parseToken.matcher.start())) {
                    res = new Token(parseToken.typeToken, string.substring(parseToken.matcher.start(), parseToken.matcher.end()));
                }
            }
        }
        if (res != null) {
            curPos += res.body.length();
            curToken = res;
        } else {
            throw new LexicalAnalyzerException("unexpected symbol at pos " + curPos, curPos);
        }
    }

    public Token getCurToken() {
        return curToken;
    }
}
